package by.epam.project.util;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptPasswordUtil {
    private EncryptPasswordUtil() {
    }

    public static String encryption(String password) {
        String encryptPassword = DigestUtils.md5Hex(password);
        return encryptPassword;
    }

//    public static void main(String[] args) {
//        System.out.println(encryption("Aa123"));
//    }
}
