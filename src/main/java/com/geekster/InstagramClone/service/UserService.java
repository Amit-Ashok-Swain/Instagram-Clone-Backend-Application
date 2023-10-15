package com.geekster.InstagramClone.service;

import com.geekster.InstagramClone.dto.SignInInput;
import com.geekster.InstagramClone.dto.SignInOutput;
import com.geekster.InstagramClone.dto.SignUpOutput;
import com.geekster.InstagramClone.model.AuthenticationToken;
import com.geekster.InstagramClone.model.PostLike;
import com.geekster.InstagramClone.model.User;
import com.geekster.InstagramClone.repository.ITokenRepo;
import com.geekster.InstagramClone.repository.IUserRepo;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    PostService postService;

    @Autowired
    ITokenRepo tokenRepo;

    @Autowired
    FollowingService followingService;

    @Autowired
    FollowerService followerService;

    @Autowired
    LikeService likeService;

    @Autowired TokenService tokenService;
    public SignUpOutput signUp(User signUpDto) {


        //check if user exists or not based on email
        User user = userRepo.findFirstByEmail(signUpDto.getEmail());

        if(user != null)
        {
            throw new IllegalStateException("Instagram user already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        signUpDto.setPassword(encryptedPassword);
        userRepo.save(signUpDto);

        return new SignUpOutput("Instagram user registered","Instagram account created successfully");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

    public SignInOutput signIn(SignInInput signInDto) {
        //check if user exists or not based on email
        User user = userRepo.findFirstByEmail(signInDto.getEmail());

        if(user == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        AuthenticationToken token = new AuthenticationToken(user);

        tokenService.saveToken(token);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!", token.getToken());


    }


    public void updateUser(User user , String token) {
        User originalUser = tokenRepo.findFirstByToken(token).getUser();


        if(!(user.getFirstName().isEmpty())){
            originalUser.setFirstName(user.getFirstName());
        }
        if((user.getLastName()!=null)){
            originalUser.setLastName(user.getLastName());
        }
        if((user.getPassword()!=null)){
            String encryptedPassword = null;

            try {
                encryptedPassword = encryptPassword(user.getPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            originalUser.setPassword(encryptedPassword);
        }

        if((user.getPhoneNumber()!=null)){
            Pattern p = Pattern.compile("\\d{2}-\\d{10}");

            Matcher m = p.matcher(user.getPhoneNumber());
            if( (m.find() && m.group().equals(user.getPhoneNumber()))){
                originalUser.setPhoneNumber(user.getPhoneNumber());

            }else{
                throw new IllegalStateException("Enter correct details");
            }

        }

        if((user.getEmail()!=null)){
            Pattern p = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}");

            Matcher m = p.matcher(user.getEmail());
            if( (m.find() && m.group().equals(user.getEmail()))){
                originalUser.setEmail(user.getEmail());

            }else{
                throw new IllegalStateException("Enter correct details");
            }
        }

        userRepo.save(originalUser);




    }

    @Transactional
    public String followUser(Long myId, Long otherId) {

        if(myId == otherId)
        {
            return "Cant follow yourself!!!!";
        }
       User myUser = userRepo.findByUserId(myId);
       User otherUser = userRepo.findByUserId(otherId);

        if(myUser!=null && otherUser!=null) {

            //todo : check if already follows or not

            //follow from my side
            followingService.saveFollowing(myUser,otherUser);

            //follower from other side
            followerService.saveFollower(otherUser, myUser);

            return "Followed Successfully!!!!!";
        }
        else
        {
            return "Users are invalid!!!";
        }
    }

    public String toggleBlueTick(Long id, boolean blueTick) {
        User user = userRepo.findByUserId(id);

        if(user!=null) {
           user.setBlueTicked(blueTick);
           userRepo.save(user);
            return "Blue tick was set to.." + blueTick;
        }
        else
        {
            return "user doesn't exist";
        }

    }

    public void like(PostLike postLike) {
        likeService.like(postLike);
    }

}
