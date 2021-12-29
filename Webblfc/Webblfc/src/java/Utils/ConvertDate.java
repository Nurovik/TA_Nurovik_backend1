/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author teguh
 */
public class ConvertDate {
    
    public String convertlongtodate(long LONG){
        
        Date date = new Date(LONG);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String dateText = df2.format(date);
                
      return dateText;
     }
    
        public String convertlongtodatenews(long LONG){
        
        Date date = new Date(LONG);
        SimpleDateFormat df2 = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss");
        String dateText = df2.format(date);
                
      return dateText;
     }
    
    public String convertTanggalbuat(long LONG){
        
         Date date = new Date(LONG);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String dateText = df2.format(date);
                
      return dateText;
    
    
    }
    
    
    public long convertDateToLong(String date){
//        long milliseconds = 0;
//             String string_date = "12-December-2012";

//                SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//                try {
//                    Date d = f.parse(date);
//                    milliseconds = d.getTime();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }

            long msec = 0;
                try{
                  SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                        Date date2 = f.parse(date);//new Date(date);

                        System.out.println(date);

                        msec = date2.getTime();
                        
                    }catch(Exception e){
                        msec = 0;

                }
    
        return msec;
    }
    
    
    public String converdate (String input) throws ParseException{
        
        Date date = new Date(input);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(date);
        
        return dateText;
    }
}
