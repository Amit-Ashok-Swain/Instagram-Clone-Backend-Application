package com.example.InstagramClone.service;

import com.example.InstagramClone.dto.SignInInput;
import com.example.InstagramClone.dto.SignInOutput;
import com.example.InstagramClone.dto.SignUpInput;
import com.example.InstagramClone.dto.SignUpOutput;
import com.example.InstagramClone.model.AuthenticationToken;
import com.example.InstagramClone.model.User;
import com.example.InstagramClone.repository.ITokenRepository;
import com.example.InstagramClone.repository.IUserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    ITokenRepository tokenRepository;

    public SignUpOutput signup(SignUpInput signUpDto) {

        // check if user exists or not based on email
        User user = userRepository.findFirstByEmail(signUpDto.getEmail());
        if ( user != null ) {
            throw new IllegalStateException("User already exists!!!! Sign in instead");
        }


        // encryption
        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // save the user
        user = new User(signUpDto.getFirstName(), signUpDto.getLastName(),
                signUpDto.getAge(),  signUpDto.getEmail(), encryptedPassword, signUpDto.getPhoneNumber());

        userRepository.save(user);

        return new SignUpOutput("User Registered","Welcome to Instagram");
    }




    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        // md5 message digest 5 --- encryption algorithm
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signin(SignInInput signInDto) {

        User user = userRepository.findFirstByEmail(signInDto.getEmail());

        if ( user == null ) {
            throw new IllegalStateException("User Invalid!!!! Sign up instead");
        }


        // encrypt the password

        String encryptedPassword = null;
        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        // match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if (!isPasswordValid) {
            throw new IllegalStateException("User Invalid!!!! Sign up instead");
        }


        // token creation and saving

        AuthenticationToken token = new AuthenticationToken(user);

        tokenService.saveToken(token);

        return new SignInOutput("SignIn successful", token.getToken());
    }

    public void updateUser(User user, String token) {
        User user1 = tokenRepository.findFirstByToken(token).getUser();
        if ( user.getFirstName() != null) {
            user1.setFirstName(user.getFirstName());
        }
        if ( user.getLastName() != null) {
            user1.setLastName(user.getLastName());
        }
        if ( user.getEmail() != null) {
            Pattern p = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}");

            Matcher m = p.matcher(user.getEmail());
            if( (m.find() && m.group().equals(user.getEmail()))){
                user1.setEmail(user.getEmail());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
//            user1.setEmail(user.getEmail());
        }
        if ( user.getAge() != null ) {
            user1.setAge(user.getAge());
        }
        if( user.getPhoneNumber() != null){
            Pattern p = Pattern.compile("\\d{2}-\\d{10}");

            Matcher m = p.matcher(user.getPhoneNumber());
            if( (m.find() && m.group().equals(user.getPhoneNumber()))){
                user1.setPhoneNumber(user.getPhoneNumber());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
//            user1.setPhoneNumber(user.getPhoneNumber());

        }


        if ( user.getPassword() != null) {
            String encryptedPassword = null;
            try {
                encryptedPassword = encryptPassword(user.getPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            user1.setPassword(encryptedPassword);
        }
        userRepository.save(user1);
    }
}
