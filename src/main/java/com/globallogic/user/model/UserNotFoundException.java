package com.globallogic.user.model;

import javax.sound.midi.Track;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(String message, Throwable throwable){
        super(message,throwable);
    }
}
