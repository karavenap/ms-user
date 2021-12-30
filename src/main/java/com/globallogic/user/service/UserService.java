package com.globallogic.user.service;

import com.globallogic.user.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> insertUser(User user) throws Exception;

    ResponseEntity<?> getUserToken(String token) throws Exception;
}
