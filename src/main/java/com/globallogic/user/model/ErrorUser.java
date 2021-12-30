package com.globallogic.user.model;

import lombok.*;

import java.util.List;
@Data @Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class ErrorUser {
    private List<DetailErrorUser> error;
}
