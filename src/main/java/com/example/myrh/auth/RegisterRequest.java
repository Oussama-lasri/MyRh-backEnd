package com.example.myrh.auth;

import com.example.myrh.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName ;
    private String lastName ;
    private String email ;
    private String password ;
    private Role role ;
}
