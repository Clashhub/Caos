import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class inputNoOfProcesses extends JPanel{
	private JTextField textField;
	private main ms;
	private int opt;
	public inputNoOfProcesses(main ms, int opt) {
		
		this.opt = opt;
		this.ms = ms;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Input no. of processes");
		lblNewLabel.setBounds(137, 64, 167, 20);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(137, 114, 167, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int noOfprocesses = Integer.parseInt(textField.getText());
				ms.setNoOfprocesses(noOfprocesses);
				showEPscreen(noOfprocesses, opt);
			}
		});
		btnEnter.setBounds(164, 156, 115, 29);
		add(btnEnter);
	}
	
	public void showEPscreen(int noOfprocess, int opt)
	{
		ms.showEnterProcesses(noOfprocess, opt);
	}
}
