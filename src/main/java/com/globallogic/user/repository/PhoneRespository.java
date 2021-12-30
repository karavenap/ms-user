package com.globallogic.user.repository;

import com.globallogic.user.model.Phone;
import com.globallogic.user.model.ResponseLoginPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PhoneRespository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int savePhone(Phone phone){
        return jdbcTemplate.update("insert into phone (number,citycode,countrycode,uuid) values(?,?,?,?)",
                phone.getNumber(), phone.getCitycode(), phone.getCountrycode(), phone.getUuid());
    }
    public  List<ResponseLoginPhone> getPhoneByToken(String token){
        String  query = "SELECT number, citycode, countrycode, uuid FROM PHONE  where uuid  = '".concat(token.concat("'"));
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<ResponseLoginPhone> listResponseLoginPhone = mapperPhones(rows);
        return listResponseLoginPhone;
    }

    private List<ResponseLoginPhone> mapperPhones(List<Map<String, Object>> rows) {
        List<ResponseLoginPhone> responseLoginPhoneList = new ArrayList<>();
        rows.forEach(phone->
                responseLoginPhoneList.add(ResponseLoginPhone.
                        builder().
                        number(Long.valueOf(phone.get("number").toString())).
                        citycode(Integer.valueOf(phone.get("citycode").toString())).
                        countrycode(phone.get("countrycode").toString()).
                        build())
        );
        return responseLoginPhoneList;
    }
}
