import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Mainscreen extends JPanel{
	
   private main ms;
   
   public Mainscreen(main mf)
   {
	   this.ms = mf;
	   setLayout(null);  
   
	   JButton btnOne = new JButton("1) Non-Preemptive priority");
	   btnOne.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent arg0) {
	   		ms.showInputNo(1);
	   	}
	   });
   	   btnOne.setBounds(47, 41, 223, 29);
       this.add(btnOne);
   	
       JButton btnTwo = new JButton("2) FCFS");
   	   btnTwo.addActionListener(new ActionListener() {
   		 public void actionPerformed(ActionEvent e) {
   			ms.showInputNo(2);
   		}
   	   });
   	   btnTwo.setBounds(47, 97, 223, 29);
   	   this.add(btnTwo);
   	
       JButton btnThree = new JButton("3) Best fit");
       btnThree.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		  ms.showEnterNoOfpartitionsAndProcesses(3);
       		  //ms.BF();
       		  //ms.showMemoryMapScreen();
       		//ms.showInputNo(3);
       	}
       });
   	   btnThree.setBounds(47, 154, 223, 29);
   	   this.add(btnThree);
   	
   	   JButton btnFour = new JButton("4) worst fit");
   	   btnFour.addActionListener(new ActionListener() {
   	   	public void actionPerformed(ActionEvent e) {
   	   		ms.showEnterNoOfpartitionsAndProcesses(4);
   	   	}
   	   });
   	   btnFour.setBounds(47, 213, 223, 29);
   	   this.add(btnFour); 
   	   
   	   JButton btnFive = new JButton("5) SJF-Preemptive");
   	   btnFive.addActionListener(new ActionListener() {
   	   	public void actionPerformed(ActionEvent arg0) {
   	   		ms.showInputNo(5);
   	   	}
   	   });
   	   btnFive.setBounds(47, 271, 223, 29);
   	   add(btnFive);
   	   
   	   JButton btnExit = new JButton("Exit");
   	   btnExit.addActionListener(new ActionListener() {
   	   	public void actionPerformed(ActionEvent e) {
   	   		System.exit(0);
   	   	}
   	   });
   	   btnExit.setBounds(45, 340, 225, 29);
   	   add(btnExit);
	   
   }

   public void gcs()
   {
     this.ms.showGCS();
   }
}
