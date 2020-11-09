package com.example.testnizadatak.utility;

import com.example.testnizadatak.models.Token;
import com.example.testnizadatak.models.TokenCryptoInfo;

import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {

    private static final String ALGO = "AES"; // Default uses ECB PKCS5Padding

    public static String encrypt(String Data, String secret) throws Exception {
        Key key = generateKey(secret);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    public static String decrypt(String strToDecrypt, String secret) {

        try {
            Key key = generateKey(secret);
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    private static Key generateKey(String secret) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
        Key key = new SecretKeySpec(decoded, ALGO);
        return key;
    }

    public static String decodeKey(String str) {
        byte[] decoded = Base64.getDecoder().decode(str.getBytes());
        return new String(decoded);
    }

    public static String encodeKey(String str) {
        byte[] encoded = Base64.getEncoder().encode(str.getBytes());
        return new String(encoded);
    }


    public static List<TokenCryptoInfo> encryptedTokens(List<Token> tokens) {
        List<TokenCryptoInfo> encryptedTokens = new ArrayList<>();
        String secret = encodeKey("MrHankeyHowdyHo!");

        for(Token token : tokens){
            try{
                String encryptedTokenMark = encrypt(token.getMark(), secret);
                token.setMark(encryptedTokenMark);
                encryptedTokens.add(new TokenCryptoInfo(token, secret));
                System.out.println(decrypt(encryptedTokenMark, secret) + " enc: " + encryptedTokenMark);
            }
            catch (Exception e){
                System.out.println("Crypt, Line 63: Exception during encryption.");
            }
        }
        return encryptedTokens;
    }
    
}