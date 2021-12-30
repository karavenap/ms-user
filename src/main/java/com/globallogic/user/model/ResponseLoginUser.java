package com.globallogic.user.model;

import lombok.*;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class ResponseLoginUser {
    private String id;
    private String created;
    private String lastLogin;
    private String token;
    private Boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<ResponseLoginPhone> phones;
}
