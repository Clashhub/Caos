import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class enterProcesses extends JPanel{
	private JTextField textField;
	private int[][] process;
	private int var = 0;
	private String error = "no";
	private main ms;
	private static int noOfProcesses;
	private int x=0, y=0;
	private JTextField textField_1;
	private int option;
	
	public enterProcesses(main ms, int noOfprocesses, int opt) {
		
		this.option = opt;
		this.ms = ms;
		this.noOfProcesses= noOfprocesses;
		this.process =  new int[this.noOfProcesses][3];
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Process 1");
		lblNewLabel.setBounds(153, 102, 117, 20);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Arrival time:");
		lblNewLabel_1.setBounds(56, 131, 95, 20);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(153, 128, 75, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
	
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  if(textField.getText().isEmpty())
			    process[x][0] = 0;
			  else
			    process[x][0] = Integer.parseInt(textField.getText());
			  
			  process[x][1] = Integer.parseInt(textField_1.getText());
			  process[x][2] = x+1;
			  textField.setText("");
			  textField_1.setText("");
			  x++;
			  lblNewLabel.setText("");
			  lblNewLabel.setText("process " + (x+1));
			  if(x == noOfprocesses)
			  {
				 switch(option)
				 {
				   case 1:
					       ms.setProcesses(process);
					       ms.showEPriority(noOfProcesses);
					       break;
				   case 2:
					       ms.setProcesses(process);
	                       ms.FCFS();
	                       ms.showGCS();
	                       break;
				   case 3:
					       ms.BF();
				           break;
				   case 4: 
					       ms.WorstFit();
					       break;
				   case 5: 
					       ms.setProcesses(process);
					       ms.sjfPreemptive();
					       ms.showSJFP();
	                       break;
				 }
			  }
			}
		});
		btnEnter.setBounds(244, 172, 115, 29);
		add(btnEnter);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 173, 75, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBurstTime = new JLabel("Burst time:");
		lblBurstTime.setBounds(66, 176, 79, 20);
		add(lblBurstTime);
	}
}
