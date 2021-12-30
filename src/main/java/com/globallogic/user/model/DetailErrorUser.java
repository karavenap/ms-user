package com.globallogic.user.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailErrorUser {
    private Timestamp timestamp;
    private int code;
    private String detail;
}
