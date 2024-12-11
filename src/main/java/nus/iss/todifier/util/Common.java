package nus.iss.todifier.util;

import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Common {

    public static String generateId(){

        return UUID.randomUUID().toString();

    }

    public static String getCurrentTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MM/dd/yyyy"); //Sun, 10/15/2023
        return LocalDate.now().format(formatter);

    }
    
}
