package com.globallogic.user.controller;

import com.globallogic.user.controller.BaseTestCase;
import com.globallogic.user.model.Phone;
import com.globallogic.user.model.User;
import com.globallogic.user.repository.PhoneRespository;
import com.globallogic.user.repository.UserRepository;
import com.globallogic.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


class UserControllerTest extends BaseTestCase {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PhoneRespository phoneRespository;

    @Test
    public void testBadRequestMailFormatIncorrect() throws Exception {
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(Phone.builder().
                citycode(100).
                countrycode("+569").
                number(88888888).
                uuid("123").
                build());
        User user = User
                .builder().
                        name("kathy").
                        email("tesgmail.com").
                        password("W12asdasdasdas").
                        phones(phoneList).
                        build();
        when(userRepository.saveUser(user)).thenReturn(anyInt());
        ResponseEntity responseEntity = userService.insertUser(user);
        assertEquals(responseEntity.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testSaveSuccessfull() throws Exception {
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(Phone.builder().
                citycode(100).
                countrycode("+569").
                number(88888888).
                uuid("123").
                build());
        User user = User
                .builder().
                        name("kathy").
                        email("tes@gmail.com").
                        password("W12asdasdasdas").
                        phones(phoneList).
                        build();
        when(userRepository.saveUser(user)).thenReturn(anyInt());
        ResponseEntity responseEntity = userService.insertUser(user);
        assertEquals(responseEntity.getStatusCode().value(), HttpStatus.CREATED.value());
    }

    @Test
    public void testEmailExits() throws Exception {
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(Phone.builder().
                citycode(100).
                countrycode("+569").
                number(88888888).
                uuid("123").
                build());
        User user = User
                .builder().
                        name("kathy").
                        email("tes@gmail.com").
                        password("W12asdasdasdas").
                        phones(phoneList).
                        build();
        when(userRepository.saveUser(user)).thenReturn(anyInt());
        when(userRepository.verifyMailIsNotExist(user)).thenReturn(1);
        when(userRepository.verifyMailIsNotExist(user)).thenReturn(1);


        ResponseEntity responseEntity = userService.insertUser(user);
        assertEquals(responseEntity.getStatusCode().value(), HttpStatus.BAD_REQUEST.value());
    }
}