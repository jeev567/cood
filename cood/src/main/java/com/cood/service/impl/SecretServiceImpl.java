package com.cood.service.impl;

import com.cood.controller.SecretController;
import com.cood.modal.Secret;
import com.cood.service.PasswordService;
import com.cood.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretServiceImpl implements SecretService {

    @Autowired
    private PasswordService passwordService;

    @Override
    public String createSecret(Secret mySecret) {
        return passwordService.encode(mySecret.getRawSecret());
    }

    @Override
    public String getSecret(Secret mySecret) {
        return passwordService.decode(mySecret.getEncryptedSecret());
    }
}
