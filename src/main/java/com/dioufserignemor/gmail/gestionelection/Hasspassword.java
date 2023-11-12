package com.dioufserignemor.gmail.gestionelection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hasspassword {
    public static String genSHAS512(String text){
       try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(text.getBytes());
            // encoder le mot de en base 64
            String resultat = Base64.getEncoder().encodeToString(hash);
            return resultat ;
       } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e) ;
       }
    }
}
