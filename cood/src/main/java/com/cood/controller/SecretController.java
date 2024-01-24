package com.cood.controller;


import com.cood.modal.Secret;
import com.cood.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/")
public class SecretController {

    @Autowired
    private SecretService secretService;


    @GetMapping("secret/{encoded-my-secret}")
    public String getSecret(@RequestParam String encodedMySecret){
        return secretService.getSecret(new Secret(encodedMySecret));
    }

    @PostMapping("secret/")
    public String createSecret(@RequestBody Secret secret){
        return secretService.createSecret(secret);
    }
}
