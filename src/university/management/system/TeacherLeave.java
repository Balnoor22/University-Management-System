package university.management.system;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class TeacherLeave extends JFrame implements ActionListener
{   
    Choice cEmpId, ctime;
    JDateChooser jdate;
    JButton submit,cancel;
    
    TeacherLeave()
    {
        setSize(500,500);
        setLocation(550,100);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Apply Leave (Teacher)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);
        
        JLabel lrollno = new JLabel("Search by Employee Id");
        lrollno.setBounds(60,100, 200, 20);
        lrollno.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lrollno);

        cEmpId = new Choice();
        cEmpId.setBounds(60, 130, 200, 20);
        add(cEmpId);

        //gets roll no fom databse and adds to dropdown
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");

            while (rs.next())
            {
                cEmpId.add(rs.getString("empId"));
            }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        
        JLabel lbdate = new JLabel("Date");
        lbdate.setBounds(60,180, 200, 20);
        lbdate.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lbdate);
        
        jdate=new JDateChooser();
        jdate.setBounds(60,210,200,25);
        add(jdate);
        
         JLabel lbtime = new JLabel("Time Duration");
        lbtime.setBounds(60,260, 200, 20);
        lbtime.setFont(new Font("Tahoma",Font.PLAIN,18));
        add(lbtime);

        ctime = new Choice();
        ctime.setBounds(60, 290, 200, 20);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);
        
        //SUBMIT
        submit=new JButton("Submit");
        submit.setBounds(60,350,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Tachoma",Font.BOLD,14));
        submit.addActionListener(this);   //calls actionPerformed fxn
        add(submit);
        
        //CANCEL
        cancel=new JButton("Cancel");
        cancel.setBounds(200,350,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tachoma",Font.BOLD,14));
        cancel.addActionListener(this);   //calls actionPerformed fxn
        add(cancel);

        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==submit)
        {
            String rollno=cEmpId.getSelectedItem();
            String date= ((JTextField) jdate.getDateEditor().getUiComponent()).getText();
            String duration=ctime.getSelectedItem();
            
            String query="insert into teacherLeave values('"+rollno+"','"+date+"','"+duration+"')";
            
            try
            {
                Conn c=new Conn();
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null,"Leave Confirmed");
                setVisible(false);
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            setVisible(false);
        }
    }
    
    public static void main(String[] args)
    {
        new TeacherLeave();
    }
}
