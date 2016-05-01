package my_method;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;

import javax.swing.JTextArea;

import org.joda.time.DateTime;
import org.joda.time.Days;



public class Send_answer {
    private JTextArea taArea_handle;
    private JButton bSend_handle;
    
    public Send_answer(JTextArea taArea,JButton bSend) {
        taArea_handle = taArea;
        bSend_handle = bSend;
    }
    
  
    public void sender(String name,String surname, String content,String date) throws FileNotFoundException
    {   
     
        String date_memory="";
        int date_difference;
        File file = new File("Config\\date.txt");
        Scanner in = new Scanner(file);
        
        date_memory = in.nextLine();
        System.out.println(date_memory);
        in.close();
        Date d1 = null;
        Date d2 = null;
        String dateStart = date;
        String dateStop = date_memory;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            d1 = sdf.parse(dateStart);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            d2 = sdf.parse(dateStop);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        DateTime jodaT1 = new DateTime(d1);
        DateTime jodaT2 = new DateTime(d2);

        System.out.println(Days.daysBetween(jodaT1, jodaT2).getDays());
        
        date_difference= (int)Days.daysBetween(jodaT1, jodaT2).getDays();
        
        if(date_difference >= 0)
        {   
            PrintWriter save = new PrintWriter("Answer\\"+name+surname+".txt");
            Scanner skaner = new Scanner(content);
            
            while (skaner.hasNext())
            {
                save.println(skaner.nextLine());
            }
            
            skaner.close();
            save.close();
            
            taArea_handle.setText("Send Complet");
        } else
        {   
            bSend_handle.setVisible(false);
            taArea_handle.setText("Time to answer complete" +" "+ date_memory);
        }
    

    }
    
    
}
    
 
    
    
    

