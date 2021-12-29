package com.blfc.backend.Utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {

    public String convert (String input) {
        String dateText;

            try {
                Date date = new Date(input);
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                dateText= df2.format(date);

                        return dateText;
                }catch (Exception e){
                    e.printStackTrace();
                    dateText = null;

                    return dateText;
        }


    }
}
