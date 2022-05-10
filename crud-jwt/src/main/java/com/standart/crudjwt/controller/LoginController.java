package com.standart.crudjwt.controller;

import javax.validation.Valid;

import com.standart.crudjwt.model.MyUser;
import com.standart.crudjwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final PasswordEncoder encoder;

    public LoginController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping("/welcome")
    public ResponseEntity welcome() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MyUser user) {

        if (encoder.matches(user.getPassword(),
                userRepository.findByUserName(user.getUserName()).get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
