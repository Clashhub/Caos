import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class memoryMap extends JPanel {
	 
	private main mf;
	private Integer[] psa;
	private Integer[] iPsa;
	private int pos = 0;
	private Integer[] memMap;
	private Integer[] processArr;
	private int partitionSize;
	private int[] hotel;
	private Integer[] temp;
	private boolean allProcessAllocated = true;
	private JTextArea textArea;
	
	public memoryMap(main mf)
	 {
		 this.mf = mf;
		 this.psa = mf.getPsa();
		 this.iPsa = mf.getIpsa();
		 this.memMap = mf.getMemMap();
		 this.processArr = mf.getMemProcess();
		 this.partitionSize = 0;
		 this.hotel = new int[this.iPsa.length];  //Indicate which process is in which partition
		 setLayout(null);
		 
		 JButton btnBack = new JButton("Back");
		 btnBack.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		mf.showMainScreen();
		 	}
		 });
		 btnBack.setBounds(490, 260, 115, 29);
		 add(btnBack);
		 
		 textArea = new JTextArea();
		 textArea.setBounds(490, 0, 577, 244);
		 add(textArea);
		 
		/* if(this.iPsa[0] == null)
		 {
			 this.psa = new Integer[this.psa.length-1];
			 this.iPsa = new Integer[this.iPsa.length-1];
			 this.memMap = new Integer[this.memMap.length-1];
			 this.processArr = new Integer[this.processArr.length-1];
			 this.hotel = new int[this.iPsa.length];
			 
			 temp = mf.getPsa();
			 for(int d = 0; d<psa.length; d++)
			   this.psa[d] = temp[d+1];
			 
			 temp = mf.getIpsa();
			 for(int a=0; a<iPsa.length; a++)
			   this.iPsa[a] = temp[a+1];
			 
			 temp = mf.getMemProcess();
			 for(int b=0; b<processArr.length; b++)
			   this.processArr[b] = temp[b+1];
			 
			 temp = mf.getMemMap();
			 for(int c=0; c<memMap.length; c++)
			   this.memMap[c] = temp[c+1]-1;
			   
		 }*/
	 }
	 
	 public void paintComponent(Graphics g){   
		  	Graphics2D g2 = (Graphics2D) g;  

		  	super.paintComponent(g);
		  	
		  	for(pos=0; pos<iPsa.length; pos++)
		  	{
		  	  partitionSize = partitionSize +iPsa[pos];		  	  
		  	  g2.drawLine(0, partitionSize/2, 400, partitionSize/2);	
              if(pos == 0)
			    g2.drawString(iPsa[pos].toString()+"KB", 425, (iPsa[pos]/2)/2);
              else
            	g2.drawString(iPsa[pos].toString()+"KB", 425, (partitionSize - (iPsa[pos]/2))/2);
		  	  for(int m=0; m<this.memMap.length; m++)
		  	  {
		  	    if(pos == memMap[m])
		  	    {
		  	      if(pos == 0)	
		  	      {
		  	   	    g2.drawString(processArr[m].toString()+"KB", 200, ((iPsa[pos]/2)+(hotel[pos]*20))/2);
		  	   	    if(hotel[pos] == 0)
		  	   	      textArea.append("\nProcess no. "+(m+1)+" (Process Size : "+processArr[m]+") is allocated to Partition "+(pos+1)+" (Current Partition Size : "+psa[pos]+"/"+iPsa[pos]+")");	
		  	        hotel[pos]++;
		  	      }
		  	      else
		  	      {
                    g2.drawString(processArr[m].toString()+"KB", 200, ((partitionSize - (iPsa[pos]/2)) + (hotel[pos]*20))/2);
                    textArea.append("\nProcess no. "+(m+1)+" (Process Size : "+processArr[m]+") is allocated to Partition "+(pos+1)+" (Current Partition Size : "+psa[pos]+"/"+iPsa[pos]+")");
                    hotel[pos]++;
		  	      }
		  	    }		  	    
		  	  }
		  	}
		  	
		  	for(int m=0; m<this.memMap.length; m++)
		  	  {
		  	    if(memMap[m] == -1)
		  	    {	
		  	   	  g2.drawString(processArr[m].toString()+"KB" + " waiting", 5, (partitionSize/2)+25 );
		  	      textArea.append("\n\n\nProcess no. "+(m+1)+" (Process Size : "+processArr[m]+") can't be allocated");
                  allProcessAllocated = false;
		  	    }		  	    
		  	  }
		  	
		  	g2.drawLine(0, 0, 400, 0);
		  	g2.drawLine(0, 0, 0, partitionSize/2);
		  	g2.drawLine(400, 0, 400, partitionSize/2);
		  }
}
