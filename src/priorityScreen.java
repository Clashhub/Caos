import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class priorityScreen extends JPanel{
	 
	  private main mf;
	  private int[][] priorityArr;
	  private int apw1[];
	  private int pr[];
	  private int pr1[];
	  private int p[];
	  private int turn[];	
      private int bt[];
      int x;
	  public priorityScreen(main mf)
	  {
		this.mf = mf; 
		
		priorityArr = mf.getPriorityArrs();
		
		apw1 = priorityArr[0];
		pr = priorityArr[1];
		pr1 =priorityArr[2];
		p = priorityArr[3];
		turn = priorityArr[4];
		bt = priorityArr[5];
		
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mf.showMainScreen();
			}
		});
		btnBack.setBounds(490, 381, 115, 29);
		add(btnBack);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 141, 611, 224);
		add(textArea);
		
        textArea.append("\n\tProcess\tBurst Time\tWait Time\tTurn Around Time   Priority\n");
        for (int i=0;i<p.length;i++)
        {
          textArea.append("\n"+ "\t"+ p[i] + "\t" + bt[p[i]-1] + "\t" + apw1[i]+ "\t" +turn[i]+ "\t    " +pr[i]+ "\n");
        }
	  }
		
	
	  public void paintComponent(Graphics g){   
		  	Graphics2D g2 = (Graphics2D) g;  
		  	super.paintComponent(g);
		  	int ct = 0;
		  	for(x=0; x<this.p.length; x++)
		  	{
		  	   ct = bt[p[x]-1] + this.apw1[x];
		       g2.drawLine(ct* 50, 0, ct* 50, 100);
		       g2.drawString("P" + Integer.toString(p[x]), (apw1[x]*50 + ct*50)/2 , 50);
		       g2.drawString(Integer.toString(ct), ct*50, 110);
		       //g2.drawString(Integer.toString(a), a*50, 110);
		  	}
		  	ct = (bt[p[x-1]-1] + this.apw1[x-1]) * 50;
		  	g2.drawLine(0, 0, 0, 100);
		  	g2.drawLine(0, 100, ct, 100);
		  	g2.drawLine(0, 0, ct, 0);
		  }
}
