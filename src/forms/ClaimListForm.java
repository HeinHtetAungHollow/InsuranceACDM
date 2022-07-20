package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ClaimListForm {

	 JFrame claimListFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClaimListForm window = new ClaimListForm();
					window.claimListFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClaimListForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		claimListFrame = new JFrame();
		claimListFrame.setBounds(100, 100, 450, 300);
		claimListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
