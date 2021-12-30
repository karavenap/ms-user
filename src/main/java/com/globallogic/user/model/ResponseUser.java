package com.globallogic.user.model;

import lombok.*;

import java.util.Date;

@Data
@Builder @NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseUser {
    private String uuid;
    private Date created;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
}
