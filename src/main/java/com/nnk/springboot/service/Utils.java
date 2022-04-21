package com.nnk.springboot.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public LocalDate parseStringToLocalDate(String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        LocalDate localDate = LocalDate.parse(date, formatter);
        
        return localDate;
    }

}
