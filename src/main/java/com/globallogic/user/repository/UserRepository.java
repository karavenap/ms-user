package com.globallogic.user.repository;

import com.globallogic.user.model.ResponseLoginUser;
import com.globallogic.user.model.User;
import com.globallogic.user.model.UserNotFoundException;
import com.globallogic.user.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Mapper mapper;

    public int saveUser(User user){
        int value = jdbcTemplate.update("insert into user (uuid,name,email,password,created,isActive, token,lastLogin) values(?,?,?,?,?,?,?,?)",
                user.getUuid(), user.getName(), user.getEmail(), user.getPassword(), user.getCreated(), user.getIsActive(), user.getToken(), user.getLastLogin());
        return value;
    }

    public int verifyMailIsNotExist(User user){
        String query = "select count(*) from user where email = '".concat(user.getEmail().concat("'"));
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return count;
    }

    public ResponseLoginUser getUserByToken(String token) throws UserNotFoundException {
        String  query = "SELECT uuid, name, email, password, created, isactive, token,lastlogin FROM USER where token  = '".concat(token.concat("'"));
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        if(rows.isEmpty()){
            throw new UserNotFoundException("TOKEN NO EXISTE");
        }
        return mapper.mapperUser(rows);
    }


}
