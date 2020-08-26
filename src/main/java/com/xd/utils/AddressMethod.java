package com.xd.utils;

import java.io.File;

public class AddressMethod {

    public static String GeneratorAddress(Long user_id, String fileName){
        if (System.getProperties().getProperty("os.name").equals("Windows 10")){
            return "D:\\picture\\" + user_id + "\\" + fileName;
        }else{
            return "/home/picture/" + user_id + "/" + fileName;
        }
    }

    public static String GeneratorAddressOut(Long user_id, String fileName) {
        if (System.getProperties().getProperty("os.name").equals("Windows 10")){
            return "D:\\picture\\" + user_id + "\\" + fileName;
        }else{
            return "39.100.100.198/picture/" + user_id +"/" + fileName;
        }
    }
}
