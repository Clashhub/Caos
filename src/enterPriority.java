import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class enterPriority extends JPanel{
	private JTextField textField;
    private int x=0;
    private static int noOfprocesses = 0;
    private int[] priority;
	private main mf;
    
	public enterPriority(main mf,int noOfprocess)
	{
		setLayout(null);
		this.mf = mf;
		this.noOfprocesses = noOfprocess;
		this.priority = new int[noOfprocesses];
		
		JLabel lblProcess = new JLabel("Process 1:");
		lblProcess.setBounds(50, 119, 82, 20);
		add(lblProcess);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				priority[x] = Integer.parseInt(textField.getText());
				textField.setText("");
				x++;
				lblProcess.setText("");
				lblProcess.setText("Process " + (x+1));
				if(x == noOfprocesses)
				{
					mf.setPriority(priority);
					mf.nonPreemptivePriority();
					mf.showPriorityScreen();
				}
			}
		});
		btnEnter.setBounds(232, 115, 69, 29);
		add(btnEnter);
		
		textField = new JTextField();
		textField.setBounds(129, 113, 102, 29);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterPriority = new JLabel("Enter priority");
		lblEnterPriority.setBounds(129, 83, 102, 20);
		add(lblEnterPriority);
	}
}
