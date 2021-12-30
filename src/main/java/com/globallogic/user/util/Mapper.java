package com.globallogic.user.util;

import com.globallogic.user.model.ResponseLoginUser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Mapper {
    public ResponseLoginUser mapperUser(List<Map<String, Object>> rows) {
        Map<String,Object> value = rows.stream().findFirst().get();
        return ResponseLoginUser.
                builder().
                id(value.get("uuid").toString()).
                created(value.get("created").toString()).
                lastLogin(value.get("lastLogin").toString()).
                token(value.get("token").toString()).
                isActive(Boolean.valueOf(value.get("isActive").toString())).
                name(value.get("name").toString()).
                email(value.get("email").toString()).
                password(value.get("password").toString()).
                build();
    }
}
