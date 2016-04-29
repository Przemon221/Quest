package start_window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import my_method.Check_amount;
import my_method.Send_answer;



public class Quest_applet extends JApplet implements ActionListener{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private JLabel lName,lSurname,lSolution,lTime;
    private JTextField tName,tSurname;
    private JTextArea taAnswer;
    private JButton bSend,bClear;
    private String name,surname,content;
    
    
    JApplet Q_app = this; 
    
    private int nr_answer = 0;
    private int max_answer = 0;
 
    
    
    public void init()
    {
     
     Q_app.setSize(600,700);
     Q_app.getContentPane().setBackground(Color.WHITE);
     Q_app.setLayout(null);
     
     
     //TextField
     tName = new JTextField();
     tName.setBounds(110,32,160,18);
     tName.setBackground(Color.LIGHT_GRAY);
     add(tName);
     
     tSurname = new JTextField();
     tSurname.setBounds(395,32,160,18);
     tSurname.setBackground(Color.LIGHT_GRAY);
     add(tSurname);
   
     Check_amount max_ans = new Check_amount(lTime, bSend, taAnswer);
    
     try {
        max_ans.set_max_answer(max_answer);
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
     
     max_answer = max_ans.getIntMax_memory();
     
     //Label
     lTime = new JLabel("Number of replies sent:" +" " + nr_answer +"/"+ max_answer); 
     lTime.setBounds(50,650,300,40);
     lTime.setFont(new Font("Arial",Font.PLAIN,20));
     add(lTime);
     
     Check_amount amount = new Check_amount(lTime,bSend,taAnswer);
     amount.check(nr_answer,max_answer);
     
     lName = new JLabel("Name:");
     lName.setBounds(50, 20, 100, 40);
     lName.setFont(new Font("Arial",Font.PLAIN,20));
     add(lName);
     
     lSurname = new JLabel("Surname:");
     lSurname.setBounds(310, 20, 100, 40);
     lSurname.setFont(new Font("Arial",Font.PLAIN,20));
     add(lSurname);
     
     lSolution = new JLabel("Solution task (past solution):");
     lSolution.setBounds(50, 100, 250, 40);
     lSolution.setFont(new Font("Arial", Font.PLAIN, 20));
     add(lSolution);
     
     
     //TextArea
     taAnswer = new JTextArea();
     JScrollPane scrollPane = new JScrollPane(taAnswer);
     scrollPane.setBounds(50,150,350,455);
     taAnswer.setBounds(50,150,350,455);
     add(scrollPane);
     
     
     //buttons
     
     bSend = new JButton("Send");
     bSend.setBounds(50, 620, 100, 20);
     add(bSend);
     bSend.addActionListener(this);
     
     
     bClear = new JButton("Clear");
     bClear.setBounds(160, 620, 100, 20);
     add(bClear);
     bClear.addActionListener(this);
     
     
 
     
     
    }
    public void start() {}
    public void stop() {}
    public void destry() {}
  
    
  
    @Override
    public void actionPerformed(ActionEvent x)
    {
        Object souruce = x.getSource();
        
        if(souruce==bSend)
        {
            name = tName.getText();
            surname = tSurname.getText();
            content = taAnswer.getText();
            
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(currentDate);
            System.out.println(dateString);
          
            Send_answer answ = new Send_answer(taAnswer,bSend);
            Check_amount amount = new Check_amount(lTime,bSend,taAnswer);
            try {
                answ.sender(name, surname, content, dateString);
              
                
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            amount.check(nr_answer,max_answer);
       
        }
        else if(souruce==bClear)
        {
            taAnswer.setText("");
        }
        
        
    }
   
    
    
    
}
