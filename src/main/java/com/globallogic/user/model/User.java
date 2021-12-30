package com.globallogic.user.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class User {
    private String uuid;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private Date created;
    private Date lastLogin;
    private Boolean isActive;
    private String token;
}
