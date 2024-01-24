package com.cood.service;

public interface PasswordService {
    String encode(String rawPassword);
    String decode(String rawPassword);

}
