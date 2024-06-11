package com.example.glife.common;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    /**
     * raw->encode
     * @param rawPassword
     * @return
     */
    public String encodePassword(String rawPassword) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(rawPassword, salt);
    }

    /**
     * decode
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }

}
