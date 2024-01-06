package com.example.myrh.Security;

import com.example.myrh.Service.Impl.OffreServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class.getName());
    private final JwtService jwtService ;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader =  request.getHeader("Authorization");
        final String jwt ;
        final String userEmail ;

        logger.warn(String.valueOf(request.getLocalName()));
        logger.warn(String.valueOf(response));
        logger.warn(String.valueOf(filterChain));

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request , response);
            return;
        }

        jwt = authHeader.substring(7);
        // todo extract the userEmail from JWT token
        userEmail = jwtService.extractUserEmail(jwt);
        // params 2 means the user is not has Authentication
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt , userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        logger.info("salaaat");
        filterChain.doFilter(request, response);
    }
}
