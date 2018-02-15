import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class FCFSScreen extends JPanel{
	
  //ArrayList<Integer> time = new ArrayList<Integer>();
  int i;
  private main mf;
  private int[] c_time;
  private int[] w_time; 
  private int[] p_id;
  private int[] a_time;
  private int[] b_time;
  private float t_tt;
  private float t_wt;
  private int[] TT;
  private int noOfProcesses;
  private int x;
  
  public FCFSScreen(main mf, int[] c_time, int[] w_time, int[] p_id, int[] a_time, int[] b_time, float t_tt, float t_wt, int[] tt)
  {  
	 this.mf = mf;
	 this.a_time = a_time;
	 this.c_time = c_time;
	 this.w_time = w_time;
	 this.p_id = p_id;
	 this.b_time = b_time;
	 this.t_tt = t_tt;
	 this.t_wt = t_wt;
	 this.TT = tt;
	 this.noOfProcesses = mf.getNoOfProcesses();
	 
	 setLayout(null);
	 
	 JButton btnBackOne = new JButton("Back");
	 btnBackOne.addActionListener(new ActionListener() {
	 	public void actionPerformed(ActionEvent arg0) {
	 		backToMainScreen();
	 	}
	 });
	 btnBackOne.setBounds(258, 282, 115, 29);
	 this.add(btnBackOne);
	 
	 JTextArea textArea = new JTextArea();
	 textArea.setBounds(0, 111, 373, 143);
	 add(textArea);
    
	textArea.append("\n\nPID\tAT\tBT\tCT\tTT\tWT");
	for(int x=0;x<noOfProcesses;x++){
		
		textArea.append("\n" + p_id[x]+"\t"+a_time[x]+"\t"+b_time[x]+"\t"+c_time[x]+"\t"+TT[x]+"\t"+w_time[x]);
	}
	textArea.append("\n" + "Total turn around time : "+t_tt+"\tAverage turnaround time : "+(t_tt/noOfProcesses));
	textArea.append("\n"+ "Total waiting time : "+t_wt+"\tAverage waitng time : "+(t_wt/noOfProcesses));
    
  }
  
  public void paintComponent(Graphics g){   	  
  	Graphics2D g2 = (Graphics2D) g;  
  
  	super.paintComponent(g);
	    
  	for(x=0; x<this.c_time.length; x++)
  	{
  	   int a = this.a_time[x] + this.w_time[x];
       g2.drawLine(this.c_time[x]* 50, 0, this.c_time[x]* 50, 100);
       g2.drawLine(a*50, 0, a*50, 100);
       g2.drawString("P" + Integer.toString(this.p_id[x]), (a*50+this.c_time[x]*50)/2 , 50);
       g2.drawString(Integer.toString(this.c_time[x]), this.c_time[x]*50, 110);
       g2.drawString(Integer.toString(a), a*50, 110);
  	}
  	g2.drawLine(0, 0, 0, 100);
  	g2.drawLine(0, 100, this.c_time[x-1]*50, 100);
  	g2.drawLine(0, 0, this.c_time[x-1]*50, 0);
  }
  
  public void backToMainScreen()
  {
     this.mf.showMainScreen();
  }
}
