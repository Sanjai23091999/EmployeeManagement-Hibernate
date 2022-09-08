package com.i2i.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public  class Validation {
         
    public static LocalDate convertStringToDate(String date) throws DateTimeParseException,ParseException {
        
        LocalDate newDate = LocalDate.now();
        date = date.replaceAll("[*/*@#$%^&]","-"); 
        if (date.charAt(2)== '-') {
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            date = simpleDateFormat2.format(simpleDateFormat1.parse(date));
            System.out.println(date);
            newDate = LocalDate.parse(date);
          
        } else {
            newDate = LocalDate.parse(date);
        }   
     return newDate;
    }

    public static boolean isValidMobileNumber(Long mobile) {

       String phone = String.valueOf(mobile);
       String mobileNew = "[7-9][0-9]{9}";
       if (phone.matches(mobileNew)) {
           return true;
       } else {
           return false;
       }
    }
}

