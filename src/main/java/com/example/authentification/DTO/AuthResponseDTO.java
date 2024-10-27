package com.example.authentification.DTO;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String tokenType = "Bearer";
    private int userId;

    public AuthResponseDTO(String token,int userId ){
        this.token = token;
        this.userId = userId;
    }

}
