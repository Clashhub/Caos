import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SJFPScreen extends JPanel{
	
	private main mf;
	private List<List<String>> plotGcs;
    private int x;
    private int noOfProcesses;
	private int[][] processes;
    private int[] w_time;
    private int[] t_t;
    private float t_tt;
    private float t_wt;
    private int y = 0;
    private int a,b,c;
	public SJFPScreen(main mf)
	{
		this.mf = mf;
		this.noOfProcesses = mf.getNoOfProcesses();
		this.processes = mf.getProcesses();
		this.w_time = mf.getWaitTime();
		this.t_t = mf.getTurnTime();
		this.plotGcs = mf.getNoGCS();
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mf.showMainScreen();
			}
		});
		btnBack.setBounds(402, 255, 99, 29);
		add(btnBack);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 126, 402, 158);
		add(textArea);
		
		
		textArea.append("\n\nPID\tAT\tBT\tCT\tTT\tWT");
		for(int x=0;x<noOfProcesses;x++)
		{
		  t_tt = t_tt + (float)t_t[x];
		  t_wt = t_wt + (float)w_time[x];
		  int pid = x+1;
		  int ct = processes[x][0] + t_t[x];
		  textArea.append("\n" + pid +"\t"+processes[x][0]+"\t"+processes[x][1]+"\t"+ ct +"\t"+t_t[x]+"\t"+w_time[x]);
		}
	
		textArea.append("\n" + "Total turn around time : "+t_tt+"\tAverage turnaround time : "+(t_tt/noOfProcesses));
		textArea.append("\n"+ "Total waiting time : "+t_wt+"\tAverage waitng time : "+(t_wt/noOfProcesses));
	}

public void paintComponent(Graphics g){   	  
  	Graphics2D g2 = (Graphics2D) g;  
  
  	super.paintComponent(g);
	    
  	for(x=0; x<this.plotGcs.size(); x++)
  	{
  	  if(Integer.parseInt(plotGcs.get(x).get(1)) == Integer.parseInt(plotGcs.get(x).get(2)))
  	     continue;
  	  else if(plotGcs.get(x).get(0).equals("IDLE"))
   	  {
  		if(plotGcs.get(x-1).get(0).equals("IDLE"))
           continue;
        else
        {
  		  y=x;
  		  while(plotGcs.get(y+1).get(0).equals("IDLE"))
  		  {
  			  y++;
  		  }
  		  a = Integer.parseInt(plotGcs.get(x).get(1)) * 50;
  		  b = Integer.parseInt(plotGcs.get(y).get(2)) * 50;
  		  g2.drawLine(b, 0, b, 100);
  		  g2.drawString("IDLE", (a+b)/2, 50);
        }
  	  }
  	  else
  	  {
  	   a = Integer.parseInt(plotGcs.get(x).get(1)) * 50;
  	   b = Integer.parseInt(plotGcs.get(x).get(2)) * 50;
  	   c =  Integer.parseInt(plotGcs.get(x).get(0));
  	   
       g2.drawLine(b, 0, b, 100);
       g2.drawLine(a, 0, a, 100);
       g2.drawString("P" + c, (a+b)/2 , 50);
       g2.drawString(Integer.toString(b/50), b, 110);
       g2.drawString(Integer.toString(a/50), a, 110);
  	  }
  	}
  	g2.drawLine(0, 0, 0, 50);
  	g2.drawLine(0, 100, Integer.parseInt(plotGcs.get(x-1).get(2))*50, 100);
  	g2.drawLine(0, 0, Integer.parseInt(plotGcs.get(x-1).get(2))*50, 0);
  }
}