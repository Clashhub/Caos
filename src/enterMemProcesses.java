import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class enterMemProcesses extends JPanel {
    
	private main mf;
	private static JTextField textField;
    private int x = 0;
    private Integer[] proSa;
    private static int noOfprocess;
    private static int option = 0;
    JLabel lblProcess;
    
	public enterMemProcesses(main mf, int process, int opt)
	{
		this.mf = mf;
		setLayout(null);
		this.option = opt;
		this.noOfprocess = process;
		this.proSa = new Integer[process];
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
			     proSa[x] = Integer.parseInt(textField.getText());
			     textField.setText("");
			     x++;
			     lblProcess.setText("");
			     lblProcess.setText("Partition " + (x+1));
			     if(x == noOfprocess)
			     {
			    	 mf.setMemProcess(proSa);
			    	 
			    	 switch(option)
			    	 {
			    	   case 3:
			    		      mf.BF();
			    		      mf.showMemoryMapScreen();
			    		      break;
			    	   case 4:
			    		      mf.WorstFit();
			    		      mf.showMemoryMapScreen();
			    		      break;
			    	 }
			     }
			}
		});
		btnEnter.setBounds(243, 90, 115, 29);
		add(btnEnter);
		
		JLabel lblEnterProcesses = new JLabel("Enter processes");
		lblEnterProcesses.setBounds(98, 64, 123, 20);
		add(lblEnterProcesses);
		
	    lblProcess = new JLabel("process 1:");
		lblProcess.setBounds(15, 99, 73, 20);
		add(lblProcess);
		
		textField = new JTextField();
		textField.setBounds(98, 91, 146, 26);
		add(textField);
		textField.setColumns(10);
	}
}
