package com.cood.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
    @Value("${application-secret-salt}")
    private String applicationSecretSalt;
    @Value("${application-encode-decode-password}")
    private String applicationEncodeDecodePassword;


    public String getApplicationSecretSalt() {
        return applicationSecretSalt;
    }

    public String getApplicationEncodeDecodePassword() {
        return applicationEncodeDecodePassword;
    }
}
