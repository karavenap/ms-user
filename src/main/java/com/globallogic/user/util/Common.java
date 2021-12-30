package com.globallogic.user.util;

import com.globallogic.user.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
    public static Matcher validMail(User user) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcherEmail = pattern.matcher(user.getEmail());
        return matcherEmail;
    }
}
