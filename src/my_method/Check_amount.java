package my_method;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Check_amount {

   private JLabel lTime_handle;
   private JButton bSend_handle;
   private JTextArea taArea_handle;
  
   private int intMax_memory=0;
   public Check_amount(JLabel lTime, JButton bSend,JTextArea taArea)
   {
       lTime_handle = lTime;
       bSend_handle = bSend;
       taArea_handle = taArea;
   }
    
    
    
    public void check(int nr_answer,int max_answer)
    {
        File catalog = new File("Answer\\");
        String files[] = catalog.list();
        
        
        for (int i=0;i<files.length;i++)
        {
            System.out.println(files[i]);
            nr_answer++;
        }
        lTime_handle.setText("Number of replies sent:" +" " + nr_answer +"/"+ max_answer);
        if(nr_answer>= max_answer-2 )
        {
            lTime_handle.setForeground(Color.RED);
            if(nr_answer > max_answer)
            {
            bSend_handle.setVisible(false);
            taArea_handle.setText("To late :(");
            }
            
        }else if(nr_answer <= max_answer-2)
        {
            lTime_handle.setForeground(Color.BLACK);
        }
        
        
            
            
        
    }
    
    public void set_max_answer(int max_answer) throws FileNotFoundException
    {
        String max_memory="";
      
        File file = new File("Config\\how_many.txt");
        Scanner in = new Scanner(file);
        
        max_memory = in.nextLine();
        intMax_memory = Integer.parseInt(max_memory);
        in.close();
    }


    public int getIntMax_memory() {
        return intMax_memory;
    }
    
}
