package com.example.InstagramClone.service;

import com.example.InstagramClone.model.AuthenticationToken;
import com.example.InstagramClone.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    ITokenRepository tokenRepository;

    public void saveToken(AuthenticationToken token) {
        tokenRepository.save(token);
    }
    public boolean authenticate(String userEmail, String token) {

        AuthenticationToken authToken = tokenRepository.findFirstByToken(token);//find token object via token string
        String expectedEmail = authToken.getUser().getEmail();
        return expectedEmail.equals(userEmail);

    }

    public void deleteToken(String token) {
        AuthenticationToken token1 = tokenRepository.findFirstByToken(token);
        tokenRepository.deleteById(token1.getTokenId());
    }

}
