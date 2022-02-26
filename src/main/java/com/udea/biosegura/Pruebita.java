package com.udea.biosegura;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pruebita {
    public static void main(String[] args) {

        try {
            Date dt = new SimpleDateFormat("dd/MM/yyyy HH:mm")
                    .parse("27/10/2020 00:03");
            Date dt2 = new SimpleDateFormat("dd/MM/yyyy HH:mm")
                    .parse("27/10/2020 00:04");
            if (dt.before(dt2)) {
                System.out.println(dt + " es menor que " + dt2);
            } else {
                System.out.println(dt2 + " es menor que " + dt);
            }
            //System.out.println(dt);
        } catch (ParseException ex) {
            Logger.getLogger(Pruebita.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
