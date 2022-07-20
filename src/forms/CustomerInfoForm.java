package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

import models.Benefitiary;
import models.Customer;
import models.CustomerDetail;
import services.CustomerServices;

public class CustomerInfoForm {

	public JFrame customerInfoFrame;
	private JTextField txtCusName;
	private JTextField txtCusNrc;
	private JTextField txtCusAge;
	private JTextField txtCusPhone;
	private JTextField txtCusEmail;
	private JTextField txtOccupation;
	private JTextField txtIncome;
	private JTextField txtBenName;
	private JTextField txtBenNrc;
	private JTextField txtBenPhone;
	private JTextField txtBenEmail;
	private JTextField txtBenRelation;
	private JTextPane txtPaneCusAddress;
	private JTextPane txtPaneBenAddress;
	private JComboBox<Integer> cboMedicalHistory;
	private Customer customer;
	private Benefitiary benefitiary;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInfoForm window = new CustomerInfoForm();
					window.customerInfoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerInfoForm() {
		initialize();
		//System.out.println(new java.sql.Date(new Date().getTime()));
		loadMedicalHistoryLevelCbo();
	}
	private void loadMedicalHistoryLevelCbo() {
		List<Integer> medLvl= new ArrayList<>();
		medLvl.add(1);
		medLvl.add(2);
		medLvl.add(3);
		medLvl.add(4);
		
		medLvl.forEach(ml->this.cboMedicalHistory.addItem(ml));
	}
	private void resetForm() {
		txtCusName.setText("");
		txtCusPhone.setText("");
		txtCusNrc.setText("");
		txtPaneCusAddress.setText("");
		txtCusEmail.setText("");
		txtCusAge.setText("");
		txtOccupation.setText("");
		txtIncome.setText("");
		cboMedicalHistory.setSelectedIndex(0);
		
		txtBenName.setText("");
		txtBenNrc.setText("");
		txtBenPhone.setText("");
		txtBenEmail.setText("");
		txtPaneBenAddress.setText("");
		txtBenRelation.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		customerInfoFrame = new JFrame();
		customerInfoFrame.setBounds(150, 15, 1080, 720);
		customerInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerInfoFrame.getContentPane().setLayout(null);

		JPanel DefaultPanel = new JPanel();
		DefaultPanel.setBackground(new Color(153, 204, 255));
		DefaultPanel.setBounds(0, 0, 1064, 129);
		customerInfoFrame.getContentPane().add(DefaultPanel);
		DefaultPanel.setLayout(null);

		JLabel lblTitle = new JLabel("ACDM Life Insurance");
		lblTitle.setForeground(new Color(34, 139, 34));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setBounds(416, 12, 243, 70);
		DefaultPanel.add(lblTitle);

		JLabel lblLogo = new JLabel("for logo");
		lblLogo.setBounds(319, 25, 63, 56);
		DefaultPanel.add(lblLogo);

		JButton btnHome = new JButton("Home");
		btnHome.setForeground(new Color(34, 139, 34));
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnHome.setBounds(46, 95, 89, 23);
		DefaultPanel.add(btnHome);

		JButton btnRegister = new JButton("Register");
		btnRegister.setForeground(new Color(34, 139, 34));
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(145, 97, 89, 23);
		DefaultPanel.add(btnRegister);

		JButton btnClaim = new JButton("Claim");
		btnClaim.setForeground(new Color(34, 139, 34));
		btnClaim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClaim.setBounds(244, 97, 89, 23);
		DefaultPanel.add(btnClaim);

		JButton btnInsurance = new JButton("Insurance");
		btnInsurance.setForeground(new Color(34, 139, 34));
		btnInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsurance.setBounds(343, 97, 99, 23);
		DefaultPanel.add(btnInsurance);

		JButton btnEmployee = new JButton("Employee");
		btnEmployee.setForeground(new Color(34, 139, 34));
		btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEmployee.setBounds(452, 97, 99, 23);
		DefaultPanel.add(btnEmployee);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(34, 139, 34));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(955, 97, 99, 23);
		DefaultPanel.add(btnLogout);

		JPanel panel = new JPanel();
		panel.setBounds(0, 130, 1064, 551);
		customerInfoFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblRegister = new JLabel("Registration Form");
		lblRegister.setForeground(new Color(25, 25, 112));
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegister.setBounds(460, 11, 155, 38);
		panel.add(lblRegister);

		JPanel customerPanel = new JPanel();
		customerPanel.setBackground(SystemColor.controlHighlight);
		customerPanel.setBounds(46, 46, 382, 415);
		panel.add(customerPanel);
		customerPanel.setLayout(null);

		JLabel lblCusInfo = new JLabel("Customer Information");
		lblCusInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCusInfo.setBounds(129, 11, 157, 22);
		customerPanel.add(lblCusInfo);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(40, 44, 97, 20);
		customerPanel.add(lblName);

		JLabel lblNrc = new JLabel("NRC");
		lblNrc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNrc.setBounds(40, 80, 97, 20);
		customerPanel.add(lblNrc);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhone.setBounds(40, 152, 97, 20);
		customerPanel.add(lblPhone);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(40, 183, 97, 20);
		customerPanel.add(lblAddress);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(40, 254, 97, 20);
		customerPanel.add(lblEmail);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAge.setBounds(40, 116, 97, 20);
		customerPanel.add(lblAge);

		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOccupation.setBounds(40, 294, 97, 20);
		customerPanel.add(lblOccupation);

		JLabel lblMonthlyIncome = new JLabel("Monthly Income");
		lblMonthlyIncome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMonthlyIncome.setBounds(40, 332, 97, 20);
		customerPanel.add(lblMonthlyIncome);

		JLabel lblMedicalHistory = new JLabel("Medical History");
		lblMedicalHistory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMedicalHistory.setBounds(40, 373, 97, 20);
		customerPanel.add(lblMedicalHistory);

		txtCusName = new JTextField();
		txtCusName.setBounds(175, 44, 170, 20);
		customerPanel.add(txtCusName);
		txtCusName.setColumns(10);

		txtCusNrc = new JTextField();
		txtCusNrc.setColumns(10);
		txtCusNrc.setBounds(175, 81, 170, 20);
		customerPanel.add(txtCusNrc);

		txtCusAge = new JTextField();
		txtCusAge.setColumns(10);
		txtCusAge.setBounds(175, 117, 47, 20);
		customerPanel.add(txtCusAge);

		txtCusPhone = new JTextField();
		txtCusPhone.setColumns(10);
		txtCusPhone.setBounds(175, 153, 170, 20);
		customerPanel.add(txtCusPhone);

		txtCusEmail = new JTextField();
		txtCusEmail.setColumns(10);
		txtCusEmail.setBounds(175, 255, 170, 20);
		customerPanel.add(txtCusEmail);

		txtOccupation = new JTextField();
		txtOccupation.setColumns(10);
		txtOccupation.setBounds(175, 295, 170, 20);
		customerPanel.add(txtOccupation);

		txtIncome = new JTextField();
		txtIncome.setColumns(10);
		txtIncome.setBounds(175, 333, 170, 20);
		customerPanel.add(txtIncome);

		cboMedicalHistory = new JComboBox<>();
		cboMedicalHistory.setModel(new DefaultComboBoxModel(new String[] { "Level" }));
		cboMedicalHistory.setBounds(172, 373, 62, 22);
		customerPanel.add(cboMedicalHistory);

		txtPaneCusAddress = new JTextPane();
		txtPaneCusAddress.setBounds(175, 182, 170, 65);
		customerPanel.add(txtPaneCusAddress);

		JPanel BenefitiaryPanel = new JPanel();
		BenefitiaryPanel.setLayout(null);
		BenefitiaryPanel.setBackground(SystemColor.controlHighlight);
		BenefitiaryPanel.setBounds(623, 46, 382, 415);
		panel.add(BenefitiaryPanel);

		JLabel lblBenefitiaryInformation = new JLabel("Benefitiary Information");
		lblBenefitiaryInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBenefitiaryInformation.setBounds(129, 11, 157, 22);
		BenefitiaryPanel.add(lblBenefitiaryInformation);

		JLabel lblBenName = new JLabel("Name");
		lblBenName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBenName.setBounds(40, 44, 97, 20);
		BenefitiaryPanel.add(lblBenName);

		JLabel lblBenNrc = new JLabel("NRC");
		lblBenNrc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBenNrc.setBounds(40, 80, 97, 20);
		BenefitiaryPanel.add(lblBenNrc);

		JLabel lblPhone_1 = new JLabel("Phone");
		lblPhone_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPhone_1.setBounds(40, 116, 97, 20);
		BenefitiaryPanel.add(lblPhone_1);

		JLabel lblAddress_1 = new JLabel("Address");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress_1.setBounds(40, 152, 97, 20);
		BenefitiaryPanel.add(lblAddress_1);

		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail_1.setBounds(40, 254, 97, 20);
		BenefitiaryPanel.add(lblEmail_1);

		JLabel lblRelation = new JLabel("Relation");
		lblRelation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRelation.setBounds(40, 294, 97, 20);
		BenefitiaryPanel.add(lblRelation);

		txtBenName = new JTextField();
		txtBenName.setColumns(10);
		txtBenName.setBounds(175, 44, 170, 20);
		BenefitiaryPanel.add(txtBenName);

		txtBenNrc = new JTextField();
		txtBenNrc.setColumns(10);
		txtBenNrc.setBounds(175, 81, 170, 20);
		BenefitiaryPanel.add(txtBenNrc);

		txtBenPhone = new JTextField();
		txtBenPhone.setColumns(10);
		txtBenPhone.setBounds(175, 117, 170, 20);
		BenefitiaryPanel.add(txtBenPhone);

		txtBenEmail = new JTextField();
		txtBenEmail.setColumns(10);
		txtBenEmail.setBounds(175, 255, 170, 20);
		BenefitiaryPanel.add(txtBenEmail);

		txtBenRelation = new JTextField();
		txtBenRelation.setColumns(10);
		txtBenRelation.setBounds(175, 295, 170, 20);
		BenefitiaryPanel.add(txtBenRelation);

		txtPaneBenAddress = new JTextPane();
		txtPaneBenAddress.setBounds(175, 152, 170, 92);
		BenefitiaryPanel.add(txtPaneBenAddress);

		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setForeground(new Color(25, 25, 112));
		btnNext.setBounds(339, 494, 155, 29);
		panel.add(btnNext);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(25, 25, 112));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(566, 497, 155, 29);
		panel.add(btnCancel);

		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (saveCusInfo() != null && saveBenInfo() !=null) {

						RegistrationForm registrationForm = new RegistrationForm(customer,benefitiary);
						registrationForm.registerFrame.setVisible(true);
						customerInfoFrame.setVisible(false);
						customer = null;
					} else {
						JOptionPane.showMessageDialog(null, "Fill All Fields");
						resetForm();
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Input Only Digit for age and income");
					resetForm();
				}
				

			}
		});

	}

	private Customer saveCusInfo() throws NumberFormatException {
		customer = new Customer();
		
			if (txtCusName.getText().isBlank() || txtCusName.getText().isEmpty() || txtCusNrc.getText().isBlank()
					|| txtCusNrc.getText().isEmpty() || txtCusPhone.getText().isBlank()
					|| txtCusPhone.getText().isEmpty() || txtCusAge.getText().isBlank() || txtCusAge.getText().isEmpty()
					|| txtPaneCusAddress.getText().isBlank() || txtPaneCusAddress.getText().isEmpty()
					|| txtCusEmail.getText().isBlank() || txtCusEmail.getText().isEmpty()
					|| txtOccupation.getText().isBlank() || txtOccupation.getText().isEmpty()
					|| txtIncome.getText().isBlank() || txtIncome.getText().isEmpty()
					|| cboMedicalHistory.getSelectedIndex() == 0) {
				customer = null;
				return customer;
			} else {
				customer.setCustomer_name(txtCusName.getText());
				customer.setCustomer_nrc(txtCusNrc.getText());
				customer.setCustomer_phone(txtCusPhone.getText());
				customer.setCustomer_address(txtPaneCusAddress.getText());
				customer.setCustomer_age(Integer.parseInt(txtCusAge.getText()));
				customer.setCustomer_email(txtCusEmail.getText());
				customer.setCustomer_occupation(txtOccupation.getText());
				customer.setCustomer_income(Long.parseLong(txtIncome.getText()));
				customer.setMedical_history((int) cboMedicalHistory.getSelectedItem());
			}
		

		return customer;

	}

	private Benefitiary saveBenInfo() {
		benefitiary = new Benefitiary();
		
			if (txtBenName.getText().isBlank() || txtBenName.getText().isEmpty() || txtBenNrc.getText().isBlank()
					|| txtBenNrc.getText().isEmpty() || txtBenPhone.getText().isBlank()
					|| txtBenPhone.getText().isEmpty() || txtPaneBenAddress.getText().isBlank()
					|| txtPaneBenAddress.getText().isEmpty() || txtBenEmail.getText().isBlank()
					|| txtBenEmail.getText().isEmpty() || txtBenRelation.getText().isBlank()
					|| txtBenRelation.getText().isEmpty()) {

				
				benefitiary = null;
				return benefitiary;
			} else {
				benefitiary.setBenefitiary_name(txtBenName.getText());
				benefitiary.setBenefitiary_nrc(txtBenNrc.getText());
				benefitiary.setBenefitiary_phone(txtBenPhone.getText());
				benefitiary.setBenefitiary_address(txtPaneBenAddress.getText());
				benefitiary.setBenefitiary_email(txtBenEmail.getText());
				benefitiary.setRelation(txtBenRelation.getText());
			}
		

		return benefitiary;
	}
}
