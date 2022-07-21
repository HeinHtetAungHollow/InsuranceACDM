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
import javax.swing.JTextPane;

import models.Benefitiary;
import models.Customer;
import models.PaymentPlan;
import models.PlanDetail;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterConfirmForm {

	public JFrame registerConfirmFrame;
	private JTextField txtCusName;
	private JTextField txtCusNrc;
	private JTextField txtCusAge;
	private JTextField txtCusPhone;
	private JTextField txtCusEmail;
	private JTextField txtCusOccupation;
	private JTextField txtCusIncome;
	private JTextField txtBenName;
	private JTextField txtBenNrc;
	private JTextField txtBenPhone;
	private JTextField txtBenEmail;
	private JTextField txtBenRelation;
	private JTextField txtPolicyTerms;
	private Customer customer;
	private Benefitiary benefitiary;
	private PaymentPlan paymentPlan;
	private PlanDetail planDetail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterConfirmForm window = new RegisterConfirmForm();
					window.registerConfirmFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterConfirmForm() {
		initialize();
	}
	public RegisterConfirmForm(Customer customer,Benefitiary benefitiary,PaymentPlan paymentPlan,long premiumAmount) {

		this.customer=customer;
		this.benefitiary=benefitiary;
		this.paymentPlan=paymentPlan;
		this.planDetail=new PlanDetail();
		this.planDetail.setPremiumAmount(premiumAmount);
		
		System.out.println(this.customer.getCustomer_name()+"  "+this.benefitiary.getBenefitiary_name()
				+"  "+ this.paymentPlan.getPayplan()+"  "+this.paymentPlan.getPolicy().getPlanName()+"  "+
				this.paymentPlan.getPolicy().getCategory().getCategory_name());
		initialize();
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		registerConfirmFrame = new JFrame();
		registerConfirmFrame.setBounds(50, 10, 1300, 720);
		registerConfirmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerConfirmFrame.getContentPane().setLayout(null);
		
		JPanel DefaultPanel = new JPanel();
		DefaultPanel.setLayout(null);
		DefaultPanel.setBackground(new Color(153, 204, 255));
		DefaultPanel.setBounds(0, 0, 1274, 129);
		registerConfirmFrame.getContentPane().add(DefaultPanel);
		
		JLabel lblTitle = new JLabel("ACDM Life Insurance");
		lblTitle.setForeground(new Color(34, 139, 34));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitle.setBounds(547, 12, 243, 70);
		DefaultPanel.add(lblTitle);
		
		JLabel lblLogo = new JLabel("for logo");
		lblLogo.setBounds(379, 25, 63, 56);
		DefaultPanel.add(lblLogo);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(Color.WHITE);
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
		btnLogout.setBounds(1082, 95, 99, 23);
		DefaultPanel.add(btnLogout);
		
		JPanel customerPanel = new JPanel();
		customerPanel.setLayout(null);
		customerPanel.setBackground(SystemColor.controlHighlight);
		customerPanel.setBounds(10, 165, 355, 415);
		registerConfirmFrame.getContentPane().add(customerPanel);
		
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
		txtCusName.setEnabled(false);
		txtCusName.setColumns(10);
		txtCusName.setBounds(175, 44, 170, 20);
		customerPanel.add(txtCusName);
		
		txtCusNrc = new JTextField();
		txtCusNrc.setEnabled(false);
		txtCusNrc.setColumns(10);
		txtCusNrc.setBounds(175, 81, 170, 20);
		customerPanel.add(txtCusNrc);
		
		txtCusAge = new JTextField();
		txtCusAge.setEnabled(false);
		txtCusAge.setColumns(10);
		txtCusAge.setBounds(175, 117, 47, 20);
		customerPanel.add(txtCusAge);
		
		txtCusPhone = new JTextField();
		txtCusPhone.setEnabled(false);
		txtCusPhone.setColumns(10);
		txtCusPhone.setBounds(175, 153, 170, 20);
		customerPanel.add(txtCusPhone);
		
		txtCusEmail = new JTextField();
		txtCusEmail.setEnabled(false);
		txtCusEmail.setColumns(10);
		txtCusEmail.setBounds(175, 255, 170, 20);
		customerPanel.add(txtCusEmail);
		
		txtCusOccupation = new JTextField();
		txtCusOccupation.setEnabled(false);
		txtCusOccupation.setColumns(10);
		txtCusOccupation.setBounds(175, 295, 170, 20);
		customerPanel.add(txtCusOccupation);
		
		txtCusIncome = new JTextField();
		txtCusIncome.setEnabled(false);
		txtCusIncome.setColumns(10);
		txtCusIncome.setBounds(175, 333, 170, 20);
		customerPanel.add(txtCusIncome);
		
		JComboBox<Integer> cboMedicalHistory = new JComboBox<>();
		cboMedicalHistory.setEnabled(false);
		cboMedicalHistory.setBounds(172, 373, 62, 22);
		customerPanel.add(cboMedicalHistory);
		
		JTextPane txtPaneCusAddress = new JTextPane();
		txtPaneCusAddress.setEnabled(false);
		txtPaneCusAddress.setBounds(175, 182, 170, 65);
		customerPanel.add(txtPaneCusAddress);
		
		JPanel BenefitiaryPanel = new JPanel();
		BenefitiaryPanel.setLayout(null);
		BenefitiaryPanel.setBackground(SystemColor.controlHighlight);
		BenefitiaryPanel.setBounds(420, 165, 382, 415);
		registerConfirmFrame.getContentPane().add(BenefitiaryPanel);
		
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
		txtBenName.setEnabled(false);
		txtBenName.setEditable(false);
		txtBenName.setColumns(10);
		txtBenName.setBounds(175, 44, 170, 20);
		BenefitiaryPanel.add(txtBenName);
		
		txtBenNrc = new JTextField();
		txtBenNrc.setEnabled(false);
		txtBenNrc.setEditable(false);
		txtBenNrc.setColumns(10);
		txtBenNrc.setBounds(175, 81, 170, 20);
		BenefitiaryPanel.add(txtBenNrc);
		
		txtBenPhone = new JTextField();
		txtBenPhone.setEnabled(false);
		txtBenPhone.setEditable(false);
		txtBenPhone.setColumns(10);
		txtBenPhone.setBounds(175, 117, 170, 20);
		BenefitiaryPanel.add(txtBenPhone);
		
		txtBenEmail = new JTextField();
		txtBenEmail.setEnabled(false);
		txtBenEmail.setEditable(false);
		txtBenEmail.setColumns(10);
		txtBenEmail.setBounds(175, 255, 170, 20);
		BenefitiaryPanel.add(txtBenEmail);
		
		txtBenRelation = new JTextField();
		txtBenRelation.setEnabled(false);
		txtBenRelation.setEditable(false);
		txtBenRelation.setColumns(10);
		txtBenRelation.setBounds(175, 295, 170, 20);
		BenefitiaryPanel.add(txtBenRelation);
		
		JTextPane txtPaneBenAddress = new JTextPane();
		txtPaneBenAddress.setEnabled(false);
		txtPaneBenAddress.setEditable(false);
		txtPaneBenAddress.setBounds(175, 152, 170, 92);
		BenefitiaryPanel.add(txtPaneBenAddress);
		
		JPanel insurancePanel = new JPanel();
		insurancePanel.setLayout(null);
		insurancePanel.setBackground(SystemColor.controlHighlight);
		insurancePanel.setBounds(857, 165, 382, 415);
		registerConfirmFrame.getContentPane().add(insurancePanel);
		
		JLabel lblInsuranceInformation = new JLabel("Insurance Information");
		lblInsuranceInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInsuranceInformation.setBounds(129, 11, 157, 22);
		insurancePanel.add(lblInsuranceInformation);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(40, 67, 97, 20);
		insurancePanel.add(lblCategory);
		
		JLabel lblPolicy = new JLabel("Policy");
		lblPolicy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPolicy.setBounds(40, 131, 97, 20);
		insurancePanel.add(lblPolicy);
		
		JLabel lblPaymentPlan = new JLabel("Payment Plan");
		lblPaymentPlan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPaymentPlan.setBounds(40, 280, 97, 20);
		insurancePanel.add(lblPaymentPlan);
		
		JLabel lblPremiumAmount = new JLabel("Premium Amount");
		lblPremiumAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPremiumAmount.setBounds(40, 345, 112, 20);
		insurancePanel.add(lblPremiumAmount);
		
		JLabel lblPolicyTerms = new JLabel("Policy Terms");
		lblPolicyTerms.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPolicyTerms.setBounds(40, 207, 97, 20);
		insurancePanel.add(lblPolicyTerms);
		
		txtPolicyTerms = new JTextField();
		txtPolicyTerms.setEnabled(false);
		txtPolicyTerms.setColumns(10);
		txtPolicyTerms.setBounds(174, 208, 47, 20);
		insurancePanel.add(txtPolicyTerms);
		
		JComboBox<String> cboCategory = new JComboBox<>();
		cboCategory.setEnabled(false);
		cboCategory.setBounds(174, 67, 157, 22);
		insurancePanel.add(cboCategory);
		
		JComboBox<String> cboPolicy = new JComboBox<>();
		cboPolicy.setEnabled(false);
		cboPolicy.setBounds(174, 131, 157, 22);
		insurancePanel.add(cboPolicy);
		
		JComboBox<Integer> cboPaymentPlan = new JComboBox<>();
		cboPaymentPlan.setEnabled(false);
		cboPaymentPlan.setBounds(174, 280, 90, 22);
		insurancePanel.add(cboPaymentPlan);
		
		JComboBox<Float> cboPremiumAmount = new JComboBox<>();
		cboPremiumAmount.setEnabled(false);
		cboPremiumAmount.setBounds(174, 345, 157, 22);
		insurancePanel.add(cboPremiumAmount);
		
		// TODO SET DATA
		txtCusName.setText(this.customer.getCustomer_name());
		txtCusNrc.setText(this.customer.getCustomer_nrc());
		txtCusAge.setText(String.valueOf(this.customer.getCustomer_age()));
		txtCusPhone.setText(this.customer.getCustomer_phone());
		txtPaneCusAddress.setText(this.customer.getCustomer_address());
		txtCusOccupation.setText(this.customer.getCustomer_occupation());
		txtCusIncome.setText(String.valueOf(this.customer.getCustomer_income()));
		cboMedicalHistory.addItem(this.customer.getMedical_history());
		txtCusEmail.setText(this.customer.getCustomer_email());
		
		txtBenName.setText(this.benefitiary.getBenefitiary_name());
		txtBenNrc.setText(this.benefitiary.getBenefitiary_nrc());
		txtBenEmail.setText(this.benefitiary.getBenefitiary_email());
		txtBenPhone.setText(this.benefitiary.getBenefitiary_phone());
		txtPaneBenAddress.setText(this.benefitiary.getBenefitiary_address());
		txtBenRelation.setText(this.benefitiary.getRelation());
		
		
		cboCategory.addItem(this.paymentPlan.getPolicy().getCategory().getCategory_name());
		cboPolicy.addItem(this.paymentPlan.getPolicy().getPlanName());
		txtPolicyTerms.setText(String.valueOf(this.paymentPlan.getPolicy().getDuration()));
		cboPaymentPlan.addItem(this.paymentPlan.getPayplan());
		cboPremiumAmount.addItem(this.planDetail.getPremiumAmount());
		
		
		
		
		
		
		
		
		//
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerInfoForm customerInfoForm = new CustomerInfoForm(customer,benefitiary);
				customerInfoForm.customerInfoFrame.setVisible(true);
				registerConfirmFrame.setVisible(false);
			}
			
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(542, 619, 89, 39);
		registerConfirmFrame.getContentPane().add(btnBack);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setBounds(704, 619, 89, 39);
		registerConfirmFrame.getContentPane().add(btnSubmit);
	}
}
