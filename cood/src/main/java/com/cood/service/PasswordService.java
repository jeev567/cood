package com.cood.service;

import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    String encode(String rawPassword);
    String decode(String rawPassword);

}
