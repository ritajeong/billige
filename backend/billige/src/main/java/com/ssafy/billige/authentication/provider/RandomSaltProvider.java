package com.ssafy.billige.authentication.provider;

import java.security.SecureRandom;
import java.util.Random;

public class RandomSaltProvider {

    private static final Random RANDOM = new SecureRandom();

    public static byte[] getNextSalt(){
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }
}
