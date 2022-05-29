package com.WebService.Webservice.project.dto.Auth;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
