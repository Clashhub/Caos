import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class enterPartitions extends JPanel{
      
	 private main mf;
	 private JTextField textField;
     private static int noOfpartitions;
     private static int noOfprocesses;
     private int x=0;
     private static Integer[] pSa;
	public enterPartitions(main mf, int partitions, int processes, int opt)
	 {
		this.mf = mf; 
		setLayout(null);
		
		this.noOfpartitions = partitions;
		this.noOfprocesses = processes;
		this.pSa = new Integer[this.noOfpartitions];
				
		JLabel lblEnterPartition = new JLabel("Enter partition");
		lblEnterPartition.setBounds(97, 63, 111, 20);
		add(lblEnterPartition);
		
		JLabel lblPartition = new JLabel("Partition 1:");
		lblPartition.setBounds(15, 90, 79, 20);
		add(lblPartition);
		
		textField = new JTextField();
		textField.setBounds(97, 87, 111, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			     pSa[x] = Integer.parseInt(textField.getText());
			     textField.setText("");
			     x++;
			     lblPartition.setText("");
			     lblPartition.setText("Partition " + (x+1));
			     if(x == noOfpartitions)
			     {
			    	 mf.setPartitions(pSa);
			    	 mf.showEnterMemProcesses(noOfprocesses, opt);
			     }
			}
		});
		btnEnter.setBounds(211, 86, 115, 29);
		add(btnEnter);
	 }
}
