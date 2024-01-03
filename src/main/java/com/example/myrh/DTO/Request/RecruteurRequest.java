package com.example.myrh.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruteurRequest {


    @Email
    @NotEmpty
    private String login ;
    @NotEmpty
    @NotNull
    private String password ;
    @Email
    @NotEmpty
    private String email ;
    @NotEmpty
    @NotNull
    //@Pattern(regexp = " ^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$",message = "not phone number")
    private String phone ;
    //@NotEmpty
    //@NotNull
    private MultipartFile image ;



}
