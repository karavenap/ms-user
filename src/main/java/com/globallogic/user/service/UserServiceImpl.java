package com.globallogic.user.service;

import com.globallogic.user.model.*;
import com.globallogic.user.repository.PhoneRespository;
import com.globallogic.user.repository.UserRepository;
import com.globallogic.user.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRespository phoneRespository;

    public ResponseEntity<?> insertUser(User user) throws Exception {
        fillUser(user);
        Matcher matcherEmail = Common.validMail(user);
        return getResponseEntity(user, matcherEmail);

    }

    private void fillUser(User user) {
        user.setUuid(UUID.randomUUID().toString());
        user.setCreated(new Date());
        user.setLastLogin(new Date());
        user.setIsActive(Boolean.TRUE);
    }

    private ResponseEntity<?> getResponseEntity(User user, Matcher matcherEmail) {
        ResponseEntity<?> responseEntity;
        if (matcherEmail.matches()) {
            int validUser = userRepository.verifyMailIsNotExist(user);
            if(validUser == 0){
                user.setToken(user.getUuid());
                saveEntity(user);
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(
                        ResponseUser.builder().
                        uuid(user.getUuid()).
                        created(user.getCreated()).
                        lastLogin(user.getLastLogin()).
                        token(user.getToken()).
                        isActive(user.getIsActive()).
                        build());
            }else {
                responseEntity = getResponseEntity("Este Mail ya existe");

            }
        }else{
            responseEntity = getResponseEntity("Formato de email incorrecto");

        }
        return responseEntity;
    }

    private void saveEntity(User user) {
        userRepository.saveUser(user);
        user.getPhones().forEach((phone) -> {
            phone.setUuid(user.getUuid());
            phoneRespository.savePhone(phone);
        });
    }

    private ResponseEntity<?> getResponseEntity(String s) {
        ResponseEntity<?> responseEntity;
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());

        List<DetailErrorUser> detailErrorUserList = new ArrayList<>();
        detailErrorUserList.add(
                DetailErrorUser.builder().
                        code(HttpStatus.BAD_REQUEST.value()).
                        timestamp(timestamp2).
                        detail(s).
                        build());

        responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorUser.builder().
                        error(detailErrorUserList).
                        build());
        return responseEntity;
    }



    @Override
    public ResponseEntity<?> getUserToken(String token) throws Exception {
        ResponseLoginUser responseLoginUser = new ResponseLoginUser();
        try{
            responseLoginUser = userRepository.getUserByToken(token);
            responseLoginUser.setPhones(phoneRespository.getPhoneByToken(responseLoginUser.getId()));
        }catch (UserNotFoundException userNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundException.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseLoginUser);
    }

}
