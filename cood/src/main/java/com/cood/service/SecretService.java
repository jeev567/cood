package com.cood.service;

import com.cood.modal.Secret;
import org.springframework.stereotype.Service;

@Service
public interface SecretService {

    String createSecret(Secret mySecret);
    String getSecret(Secret mySecret);

}
