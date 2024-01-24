package com.cood.service.impl;

import com.cood.config.Properties;
import com.cood.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;


@Service
public class PasswordServiceImpl implements PasswordService {


    private Properties properties;
    byte[] salt;
    private String password;
    @Autowired
    public PasswordServiceImpl(Properties properties){
        this.properties = properties;
        this.salt = properties.getApplicationSecretSalt().getBytes();
        this.password = properties.getApplicationEncodeDecodePassword();
    }





    @Override
    public String encode(String rawPassword) {
        try {
            String data = rawPassword;
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            byte[] combined = new byte[salt.length + encryptedData.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(encryptedData, 0, combined, salt.length, encryptedData.length);
            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }



    @Override
    public String decode(String encryptedData) {
        try {

            byte[] combined = Base64.getDecoder().decode(encryptedData);

            byte[] extractedSalt = new byte[properties.getApplicationSecretSalt().length()];
            byte[] extractedData = new byte[combined.length - properties.getApplicationSecretSalt().length()];

            System.arraycopy(combined, 0, extractedSalt, 0, properties.getApplicationSecretSalt().length());
            System.arraycopy(combined, properties.getApplicationSecretSalt().length(), extractedData, 0, combined.length - properties.getApplicationSecretSalt().length());

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), extractedSalt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            byte[] decryptedData = cipher.doFinal(extractedData);

            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

}
