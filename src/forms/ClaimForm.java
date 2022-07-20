package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent.Cause;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ClaimForm {

	private JFrame frame;
	private JTextField textField;
	public Cause cause;
	private JComboBox<models.Cause> comboBox; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClaimForm window = new ClaimForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClaimForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 25, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel DefaultPanel = new JPanel();
		DefaultPanel.setLayout(null);
		DefaultPanel.setBackground(new Color(153, 204, 255));
		DefaultPanel.setBounds(0, 0, 1064, 129);
		frame.getContentPane().add(DefaultPanel);
		
		JLabel lblTitle = new JLabel("ACDM Life Insurance");
		lblTitle.setForeground(new Color(34, 139, 34));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setBounds(416, 12, 243, 70);
		DefaultPanel.add(lblTitle);
		
		JLabel lblLogo = new JLabel("for logo");
		lblLogo.setBounds(319, 25, 63, 56);
		DefaultPanel.add(lblLogo);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm mainForm = new MainForm();
				mainForm.mainFrame.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		btnHome.setForeground(new Color(34, 139, 34));
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHome.setBounds(46, 95, 89, 23);
		DefaultPanel.add(btnHome);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfoForm customerInfoForm = new CustomerInfoForm();
				customerInfoForm.customerInfoFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnRegister.setForeground(new Color(34, 139, 34));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(145, 97, 89, 23);
		DefaultPanel.add(btnRegister);
		
		JButton btnClaimlist = new JButton("Claim List");
		btnClaimlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClaimListForm claimListForm = new ClaimListForm();
				claimListForm.claimListFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnClaimlist.setForeground(new Color(34, 139, 34));
		btnClaimlist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClaimlist.setBounds(244, 97, 89, 23);
		DefaultPanel.add(btnClaimlist);
		
		JButton btnInsurance = new JButton("Insurance");
		btnInsurance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnInsurance.setForeground(new Color(34, 139, 34));
		btnInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsurance.setBounds(343, 97, 99, 23);
		DefaultPanel.add(btnInsurance);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeForm employeeForm = new EmployeeForm();
				employeeForm.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnEmployee.setForeground(new Color(34, 139, 34));
		btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEmployee.setBounds(452, 97, 99, 23);
		DefaultPanel.add(btnEmployee);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(34, 139, 34));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(955, 97, 99, 23);
		DefaultPanel.add(btnLogout);
		
		JPanel customerPanel = new JPanel();
		customerPanel.setLayout(null);
		customerPanel.setBackground(SystemColor.controlHighlight);
		customerPanel.setBounds(235, 175, 644, 418);
		frame.getContentPane().add(customerPanel);
		
		JLabel lblClaimInformation = new JLabel("Claim Information");
		lblClaimInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblClaimInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClaimInformation.setBounds(234, 11, 157, 22);
		customerPanel.add(lblClaimInformation);
		
		JLabel lblRegisterID = new JLabel("Register ID");
		lblRegisterID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegisterID.setBounds(40, 67, 97, 20);
		customerPanel.add(lblRegisterID);
		
		JLabel lblRegisterIDInfo = new JLabel(".....");
		lblRegisterIDInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegisterIDInfo.setBounds(174, 67, 157, 18);
		customerPanel.add(lblRegisterIDInfo);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerName.setBounds(40, 111, 97, 20);
		customerPanel.add(lblCustomerName);
		
		JLabel lblCustomerNameInfo = new JLabel(".....");
		lblCustomerNameInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerNameInfo.setBounds(174, 112, 157, 18);
		customerPanel.add(lblCustomerNameInfo);
		
		JLabel lblCustomerNrc = new JLabel("Customer NRC");
		lblCustomerNrc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerNrc.setBounds(40, 152, 97, 20);
		customerPanel.add(lblCustomerNrc);
		
		JLabel lblCustomerNRCInfo = new JLabel(".....");
		lblCustomerNRCInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerNRCInfo.setBounds(174, 153, 157, 18);
		customerPanel.add(lblCustomerNRCInfo);
		
		JLabel lblCustomerPhone = new JLabel("Customer Phone");
		lblCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerPhone.setBounds(40, 194, 97, 20);
		customerPanel.add(lblCustomerPhone);
		
		JLabel lblCustomerPhoneInfo = new JLabel(".....");
		lblCustomerPhoneInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCustomerPhoneInfo.setBounds(174, 195, 157, 18);
		customerPanel.add(lblCustomerPhoneInfo);
		
		JLabel lblBenefitiaryName = new JLabel("Beneftiary Name");
		lblBenefitiaryName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBenefitiaryName.setBounds(40, 238, 97, 20);
		customerPanel.add(lblBenefitiaryName);
		
		JLabel lblBenefitiaryNameInfo = new JLabel(".....");
		lblBenefitiaryNameInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBenefitiaryNameInfo.setBounds(174, 239, 157, 18);
		customerPanel.add(lblBenefitiaryNameInfo);
		
		JLabel lblBeneftiaryNrc = new JLabel("Beneftiary NRC");
		lblBeneftiaryNrc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBeneftiaryNrc.setBounds(40, 277, 97, 20);
		customerPanel.add(lblBeneftiaryNrc);
		
		JLabel lblBenefitiaryNRCInfo = new JLabel(".....");
		lblBenefitiaryNRCInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBenefitiaryNRCInfo.setBounds(174, 278, 157, 18);
		customerPanel.add(lblBenefitiaryNRCInfo);
		
		JLabel lblBeneftiaryPhone = new JLabel("Beneftiary Phone");
		lblBeneftiaryPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBeneftiaryPhone.setBounds(40, 319, 97, 20);
		customerPanel.add(lblBeneftiaryPhone);
		
		JLabel lblBenefitiaryPhoneInfo = new JLabel(".....");
		lblBenefitiaryPhoneInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBenefitiaryPhoneInfo.setBounds(174, 320, 157, 18);
		customerPanel.add(lblBenefitiaryPhoneInfo);
		
		JLabel lblInsuranceCategory = new JLabel("Insurance Category");
		lblInsuranceCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInsuranceCategory.setBounds(341, 67, 111, 20);
		customerPanel.add(lblInsuranceCategory);
		
		JLabel lblInsuranceCategoryInfo = new JLabel(".....");
		lblInsuranceCategoryInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInsuranceCategoryInfo.setBounds(520, 68, 124, 18);
		customerPanel.add(lblInsuranceCategoryInfo);
		
		JLabel lblPolicyPlan = new JLabel("Policy Plan");
		lblPolicyPlan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPolicyPlan.setBounds(341, 111, 97, 20);
		customerPanel.add(lblPolicyPlan);
		
		JLabel lblPolicyPlanInfo = new JLabel(".....");
		lblPolicyPlanInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPolicyPlanInfo.setBounds(520, 112, 124, 18);
		customerPanel.add(lblPolicyPlanInfo);
		
		JLabel lblCause = new JLabel("Cause");
		lblCause.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCause.setBounds(341, 213, 97, 20);
		customerPanel.add(lblCause);
		
		JLabel lblCredential = new JLabel("Credential");
		lblCredential.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCredential.setBounds(341, 261, 97, 20);
		customerPanel.add(lblCredential);
		
		comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-select-"}));
		comboBox.addItem(models.Cause.DISEASE);
		comboBox.addItem(models.Cause.ACCIDENT);
		comboBox.setBounds(486, 201, 133, 34);
		customerPanel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(486, 262, 133, 22);
		customerPanel.add(textField);
		textField.setColumns(10);
		
		JPanel panelInfoDetail = new JPanel();
		panelInfoDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerInfoForm customerInfoForm = new CustomerInfoForm();
				customerInfoForm.customerInfoFrame.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		panelInfoDetail.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInfoDetail.setBounds(341, 152, 184, 34);
		customerPanel.add(panelInfoDetail);
		panelInfoDetail.setLayout(null);
		
		JLabel lblRegisDetail = new JLabel("Check Register Form Detail");
		lblRegisDetail.setBounds(10, 11, 166, 14);
		panelInfoDetail.add(lblRegisDetail);
		
		JButton btnClaim = new JButton("Claim");
		btnClaim.setBounds(219, 359, 89, 34);
		customerPanel.add(btnClaim);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setBounds(352, 359, 89, 34);
		customerPanel.add(btnCancel);
		
		JLabel lblClaimAmount = new JLabel("Claim Amount");
		lblClaimAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClaimAmount.setBounds(341, 306, 97, 20);
		customerPanel.add(lblClaimAmount);
		
		JLabel lblClaimAmountInfo = new JLabel(".....");
		lblClaimAmountInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblClaimAmountInfo.setBounds(486, 310, 124, 18);
		customerPanel.add(lblClaimAmountInfo);
	}

}
