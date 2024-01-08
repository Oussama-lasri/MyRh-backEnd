package com.example.myrh.Service.Impl;

import com.example.myrh.DTO.RecruteurDTO;
import com.example.myrh.DTO.Request.RecruteurRequest;
import com.example.myrh.Entity.Agent;
import com.example.myrh.Entity.FileEntity;
import com.example.myrh.Entity.Recruteur;
import com.example.myrh.Enum.Role;
import com.example.myrh.Error.ErrorMessagesRecruteur;
import com.example.myrh.Exception.RecruteurException;
import com.example.myrh.Repository.AgentRepository;
import com.example.myrh.Repository.RecruteurRepository;
import com.example.myrh.Security.JwtService;
import com.example.myrh.Service.IFileService;
import com.example.myrh.Service.IRecruteurService;
import com.example.myrh.Utiles.EmailService;
import com.example.myrh.auth.AuthenticationResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(rollbackFor = Exception.class)
public class RecruteurServiceImpl implements IRecruteurService {
    private static final Logger logger = LoggerFactory.getLogger(RecruteurServiceImpl.class.getName());

    private final RecruteurRepository recruteurRepository;
    private final AgentRepository agentRepository ;

    private final PasswordEncoder passwordEncoder ;
    private final ModelMapper modelMapper;
    private final IFileService fileService;
    private final JwtService jwtService ;
    private  final EmailService emailService ;

    RecruteurServiceImpl(RecruteurRepository recruteurRepository, AgentRepository agentRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, IFileService fileService, JwtService jwtService, EmailService emailService){
        this.recruteurRepository = recruteurRepository ;
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }
    @Override
    public RecruteurDTO create(RecruteurRequest  recruteurRequest) {
        Optional<Recruteur> recruteur = recruteurRepository.findByEmail(recruteurRequest.getEmail());
        if (recruteur.isPresent()){
            throw new RecruteurException(ErrorMessagesRecruteur.EMAIL_ALREADY_EXIST.getErrorMessage());
        }
        FileEntity image = null;
        try{
             image = fileService.storeFile(recruteurRequest.getImage());
            logger.info("created image successfully");
        }catch (Exception ex){
            logger.warn("error image => " + ex.getMessage());
        }
        Recruteur recruteurSaved = modelMapper.map(recruteurRequest,Recruteur.class);
        recruteurSaved.setImage(image);
        try{
            recruteurSaved = recruteurRepository.save(recruteurSaved);
            logger.info("saved successfully");
        }catch(Exception ex){
            logger.warn(ex.getMessage());
        }
        return modelMapper.map(recruteurSaved, RecruteurDTO.class);
    }

    @Override
    public RecruteurDTO update(long id,RecruteurRequest  recruteurRequest) {
        return null;
    }

    @Override
    public RecruteurDTO findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<RecruteurDTO> findAll() {
        return null;
    }


    public AuthenticationResponse register(RecruteurRequest recruteurRequest){

        Optional<Recruteur> recruteurCheck = recruteurRepository.findByEmail(recruteurRequest.getEmail());
        //Optional<Recruteur> userCheck = recruteurRepository.findByEmail(recruteurRequest.getEmail());
        Optional<Agent> agentCheck = agentRepository.findByEmail(recruteurRequest.getEmail());
        if (recruteurCheck.isPresent() && agentCheck.isPresent()){
            throw new RecruteurException(ErrorMessagesRecruteur.EMAIL_ALREADY_EXIST.getErrorMessage());
        }
        FileEntity image = null;
        try{
            image = fileService.storeFile(recruteurRequest.getImage());
            logger.info("created image successfully");
        }catch (Exception ex){
            logger.warn("error image => " + ex.getMessage());
        }
        var recruteur = Recruteur.builder()
                .email(recruteurRequest.getEmail())
                .password(passwordEncoder.encode(recruteurRequest.getPassword()))
                .login(recruteurRequest.getLogin())
                .image(image)
                .role(Role.RECRUTEUR)
                .Phone(recruteurRequest.getPhone())
                .isValid(false)
                .codeValidation((int)Math.floor(Math.random() * (10000 - 100 + 1) + 100))
                .build();
        // Todo send email
        emailService.sendSimpleMessage(recruteur.getEmail(),"code confirmation","code confirmation : "+recruteur.getCodeValidation());
        logger.warn("send code => " + recruteur.getCodeValidation() );

        recruteurRepository.save(recruteur);
        var jwtToken = jwtService.generateToken(recruteur);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public boolean validationEmail(String email , int codeValidation){
        Optional<Recruteur> recruteur = recruteurRepository.findByEmail(email);
        if (recruteur.isEmpty()) {
            throw new RecruteurException(ErrorMessagesRecruteur.NO_RECORD_FOUND.getErrorMessage());
        }
        LocalDateTime expirationTime = recruteur.get().getUpdatedOn().plusMinutes(3);
        LocalDateTime currentTime = LocalDateTime.now();
        logger.info("expiration time: " + expirationTime);
        logger.info("current time: " + currentTime);


        if (expirationTime.isAfter(currentTime) && Integer.parseInt(String.valueOf(recruteur.get().getCodeValidation())) == codeValidation) {            recruteur.get().setCodeValidation(null);
            recruteur.get().setIsValid(true);
            logger.info("update succesfully");
            return true;
        }

        return false ;
    }


}
