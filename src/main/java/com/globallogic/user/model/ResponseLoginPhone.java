package com.globallogic.user.model;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor @ToString
public class ResponseLoginPhone {
    private long number;
    private int citycode;
    private String countrycode;
}
