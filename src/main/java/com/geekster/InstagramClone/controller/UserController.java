package com.geekster.InstagramClone.controller;

import com.geekster.InstagramClone.dto.SignInInput;
import com.geekster.InstagramClone.dto.SignInOutput;
import com.geekster.InstagramClone.dto.SignUpOutput;
import com.geekster.InstagramClone.model.PostLike;
import com.geekster.InstagramClone.model.User;
import com.geekster.InstagramClone.repository.IFollowingRepo;
import com.geekster.InstagramClone.service.TokenService;
import com.geekster.InstagramClone.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService authService;

    @Autowired
    IFollowingRepo followRepo;

    @PostMapping("/signup")
    public SignUpOutput signUp(@Valid @RequestBody User signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@Valid @RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }

    @DeleteMapping("/signout")
    public ResponseEntity<String> signOut(@RequestParam String email , @RequestParam String token){
        HttpStatus status;
        String msg=null;

        if(authService.authenticate(email,token))
        {
            authService.deleteToken(token);
            msg = "Signout Successful";
            status = HttpStatus.OK;

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @PutMapping()
    public ResponseEntity<String> updateUser(@RequestParam String email , @RequestParam String token , @RequestBody User user){
        HttpStatus status;
        String msg=null;

        if(authService.authenticate(email,token))
        {
            try{
                userService.updateUser(user , token);
                status = HttpStatus.OK;
                msg = "User updated sucessfully";
            }catch (Exception e){
                msg = "Enter valid information";
                status = HttpStatus.BAD_REQUEST;
            }

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    /**
     *
     * todo : add authentication pass email and token of myid : and perform authentication
     */
    @PostMapping("/follow/{myId}/{otherId}")
    String followUser(@PathVariable Long myId, @PathVariable Long otherId)
    {
        return userService.followUser( myId, otherId);
    }


    @PostMapping("/like")
    void likePost(@RequestBody PostLike postLike)
    {
        //todo : validation
        userService.like(postLike);

    }







}
