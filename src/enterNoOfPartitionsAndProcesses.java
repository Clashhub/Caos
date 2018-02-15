import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class enterNoOfPartitionsAndProcesses extends JPanel{
	
	private main mf;
	private JTextField textField;
	private JTextField textField_1;
    private int noOfprocesses = 0;
    private int noOfpartitions = 0;
 //   private int option = 0;
	public enterNoOfPartitionsAndProcesses(main mf, int opt)
	{
		this.mf = mf;
		setLayout(null);
		
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				noOfprocesses = Integer.parseInt(textField_1.getText());
				noOfpartitions = Integer.parseInt(textField.getText());
				mf.showEnterPartitions(noOfpartitions, noOfprocesses, opt);
			}
		});
		btnEnter.setBounds(184, 169, 115, 29);
		add(btnEnter);
		
		JLabel lblEnterPartitions = new JLabel("Enter number of partitions and processes");
		lblEnterPartitions.setBounds(71, 16, 300, 20);
		add(lblEnterPartitions);
		
		JLabel lblPartitions = new JLabel("Partitions");
		lblPartitions.setBounds(71, 75, 69, 20);
		add(lblPartitions);
		
		textField = new JTextField();
		textField.setBounds(69, 100, 98, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblProcesses = new JLabel("Processes");
		lblProcesses.setBounds(71, 142, 69, 20);
		add(lblProcesses);
		
		textField_1 = new JTextField();
		textField_1.setBounds(71, 170, 98, 26);
		add(textField_1);
		textField_1.setColumns(10);
	}
}
