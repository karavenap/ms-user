package com.globallogic.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Phone {
    private long number;
    private int citycode;
    private String countrycode;
    private String uuid;
}
