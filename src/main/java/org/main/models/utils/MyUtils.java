package org.main.models.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyUtils {

    public String generateTimeStamp (){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return     now.format(formatter);

    }
}
