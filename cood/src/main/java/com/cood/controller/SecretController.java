package com.cood.controller;


import com.cood.modal.Secret;
import com.cood.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SecretController {

    @Autowired
    private SecretService secretService;

    @GetMapping("/secret/{encoded-secret}")
    public String getSecret(@PathVariable(value ="encoded-secret")  String encodedSecret){
        System.out.println(encodedSecret);
        return secretService.getSecret(new Secret(encodedSecret));
    }

    @PostMapping("/secret")
    public String createSecret(@RequestBody Secret secret){
        return secretService.createSecret(secret);
    }
}
