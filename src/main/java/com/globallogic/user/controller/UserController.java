package com.globallogic.user.controller;

import com.globallogic.user.model.User;
import com.globallogic.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Helthcheck
     */
    @GetMapping
    public ResponseEntity getHealthCheck() {
        return ResponseEntity.ok().build();
    }
    /**
     * Este servicio inserta informacion de usuario
     * Metodo POST
     * @userModel
     * @return ResponseEntity
     */
    @PostMapping("/sign-up")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws Exception {
        return userService.insertUser(user);
    }
    /**
     * Este servicio consulta informacion de usuario por el token
     * Metodo GET
     * String token
     * @return ResponseEntity
     */
    @GetMapping("/login")
    public ResponseEntity<?> getUserToken(@RequestParam(required = true) String token)throws Exception {
        return userService.getUserToken(token);
    }
}
