package com.wisdom.Ecom_project.dto.ResponseDto;

public class AuthResponsePayload {
    private final String accessToken;
    private final String refreshToken;

    public AuthResponsePayload(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
