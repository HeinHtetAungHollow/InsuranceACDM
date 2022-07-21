package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import models.Benefitiary;
import models.Category;
import models.Customer;
import models.PaymentPlan;
import models.Policy;
import models.PremiumAmount;
import services.CategoryService;
import services.PaymentPlanService;
import services.PolicyService;


import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class RegistrationForm {

	public JFrame registerFrame;
	private JTextField textField_2;
	private JComboBox<String> cboCategory;
	private JComboBox<String> cboPremiumAmount;
	
	private Customer customer;
	private Benefitiary benefitiary;

	private List<Category> categoryList = new ArrayList<>();
	private CategoryService categoryService;
	private Optional<Category> selectedCategory;
	private List<Policy> policyList = new ArrayList<>();
	private PolicyService policyService;

	private Optional<Policy> selectedPolicy;
	private List<PaymentPlan> paymentPlanList = new ArrayList<>();
	private PaymentPlanService paymentPlanService;
	
	private Optional<PaymentPlan> selectedPayment;
	private PremiumAmount premiumAmount;
	
	private JComboBox<String> cboPolicy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationForm window = new RegistrationForm();
					window.registerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrationForm() {
		initialize();
		initializeDependencies();
		loadCategoryForCbo();
		//loadPremiumAmount();
	}

	public RegistrationForm(Customer customer, Benefitiary benefitiary) {
		initialize();
		initializeDependencies();
		this.customer = customer;
		this.benefitiary = benefitiary;
		loadCategoryForCbo();
		loadPremiumAmount();
		
	}

	private void initializeDependencies() {
		this.categoryService = new CategoryService();
		this.policyService = new PolicyService();
		this.paymentPlanService = new PaymentPlanService();
	}

	private void loadCategoryForCbo() {
		this.cboCategory.addItem("Select Category");
		this.categoryList = this.categoryService.findAllCategorys();
		this.categoryList.forEach(cl -> cboCategory.addItem(cl.getCategory_name()));
	}
	private void loadPremiumAmount() {
		this.premiumAmount=new PremiumAmount();
		this.cboPremiumAmount.addItem("Select Amount");
		List<Long> actualList=premiumAmount.getPremiumList(customer.getMedical_history());		
		actualList.forEach(al->this.cboPremiumAmount.addItem(String.valueOf(al)));
	}
	
	
	
	private void loadPolicy(Optional<List<Policy>> optionalList) {
		List<Policy> filteredList=optionalList.orElseGet(()->this.policyList).stream().collect(Collectors.toList());
		filteredList.forEach(p -> cboPolicy.addItem(p.getPlanName()));
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		registerFrame = new JFrame();
		registerFrame.setBounds(150, 15, 1080, 720);
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.getContentPane().setLayout(null);

		JPanel DefaultPanel = new JPanel();
		DefaultPanel.setLayout(null);
		DefaultPanel.setBackground(new Color(153, 204, 255));
		DefaultPanel.setBounds(0, 0, 1064, 129);
		registerFrame.getContentPane().add(DefaultPanel);

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
		panel.setLayout(null);
		panel.setBounds(0, 130, 1064, 551);
		registerFrame.getContentPane().add(panel);

		JLabel lblRegister = new JLabel("Registration Form");
		lblRegister.setForeground(new Color(25, 25, 112));
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegister.setBounds(460, 11, 155, 38);
		panel.add(lblRegister);

		JPanel customerPanel = new JPanel();
		customerPanel.setLayout(null);
		customerPanel.setBackground(SystemColor.controlHighlight);
		customerPanel.setBounds(339, 47, 382, 415);
		panel.add(customerPanel);

		JLabel lblInsuranceInformation = new JLabel("Insurance Information");
		lblInsuranceInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInsuranceInformation.setBounds(129, 11, 157, 22);
		customerPanel.add(lblInsuranceInformation);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCategory.setBounds(40, 67, 97, 20);
		customerPanel.add(lblCategory);

		JLabel lblPolicy = new JLabel("Policy");
		lblPolicy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPolicy.setBounds(40, 131, 97, 20);
		customerPanel.add(lblPolicy);

		JLabel lblPaymentPlan = new JLabel("Payment Plan");
		lblPaymentPlan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPaymentPlan.setBounds(40, 280, 97, 20);
		customerPanel.add(lblPaymentPlan);

		JLabel lblPremiumAmount = new JLabel("Premium Amount");
		lblPremiumAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPremiumAmount.setBounds(40, 345, 112, 20);
		customerPanel.add(lblPremiumAmount);

		JLabel lblPolicyTerms = new JLabel("Policy Terms");
		lblPolicyTerms.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPolicyTerms.setBounds(40, 207, 97, 20);
		customerPanel.add(lblPolicyTerms);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(174, 208, 47, 20);
		customerPanel.add(textField_2);

		cboCategory = new JComboBox<>();
		// cboCategory.setModel(new DefaultComboBoxModel(new String[] {"Category"}));
		cboCategory.setBounds(174, 67, 157, 22);
		customerPanel.add(cboCategory);

		cboPolicy = new JComboBox<>();
		// cboPolicy.setModel(new DefaultComboBoxModel(new String[] { "Policy" }));
		cboPolicy.setBounds(174, 131, 157, 22);
		customerPanel.add(cboPolicy);

		JComboBox<String> cboPaymentPlan = new JComboBox<>();
		// cboPaymentPlan.setModel(new DefaultComboBoxModel(new String[] { "Month" }));
		cboPaymentPlan.setBounds(174, 280, 90, 22);
		customerPanel.add(cboPaymentPlan);

		cboPremiumAmount = new JComboBox<>();
		// cboPremiumAmount.setModel(new DefaultComboBoxModel(new String[] { "Premium
		// Amount" }));
		cboPremiumAmount.setBounds(174, 345, 157, 22);
		customerPanel.add(cboPremiumAmount);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setForeground(new Color(25, 25, 112));
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConfirm.setBounds(339, 494, 155, 29);
		panel.add(btnConfirm);

		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(25, 25, 112));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(566, 497, 155, 29);
		panel.add(btnBack);

		cboCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboCategory.getSelectedIndex() != 0 && cboCategory.getSelectedIndex() != -1) {
					cboPolicy.removeAllItems();
					cboPolicy.addItem("Policy");
					selectedCategory = categoryList.stream()
							.filter(c -> c.getCategory_name().equals(cboCategory.getSelectedItem())).findFirst();
					int s = selectedCategory.map(c -> c.getId()).get();
					policyList = policyService.findPolicyListByCategoryId(String.valueOf(s));
					loadPolicy(Optional.of(policyList.stream().filter(
							pl->pl.getDuration()+customer.getCustomer_age()<66
							).collect(Collectors.toList())));
					
				} else {
					cboPolicy.removeAllItems();
					cboPolicy.addItem("Policy");
				}
			}
		});

		cboPolicy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboPolicy.getSelectedIndex() != 0 && cboPolicy.getSelectedIndex() != -1) {
					cboPaymentPlan.removeAllItems();
					cboPaymentPlan.addItem("Payment Plan");
					selectedPolicy=policyList.stream()
							.filter(p->p.getPlanName().equals(cboPolicy.getSelectedItem())).findFirst();
					int id=selectedPolicy.map(sp->sp.getId()).get();
					paymentPlanList=paymentPlanService.findPaymentPlanListByPolicyId(String.valueOf(id));
					paymentPlanList.forEach(ppl->cboPaymentPlan.addItem(String.valueOf(ppl.getPayplan())));
				} else {
					cboPaymentPlan.removeAllItems();
					cboPaymentPlan.addItem("Payment Plan");
				}
			}
		});
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPayment=paymentPlanList.stream()
						.filter(pp->pp.getPayplan()==(Integer.parseInt((String) cboPaymentPlan.getSelectedItem()))).findFirst();
				int paymentplan_id=selectedPayment.map(spp->spp.getId()).get();
				PaymentPlan paymentPlan=new PaymentPlan();
				paymentPlan=paymentPlanService.findById(String.valueOf(paymentplan_id));
				
				if(paymentPlan!=null && cboPremiumAmount.getSelectedIndex()!=0 && cboPremiumAmount.getSelectedIndex()!=-1) {
					RegisterConfirmForm registerConfirmForm = new RegisterConfirmForm(
							customer,benefitiary,paymentPlan,Long.parseLong((String) cboPremiumAmount.getSelectedItem()));
					registerConfirmForm.registerConfirmFrame.setVisible(true);
					registerFrame.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Select All Fields!");
				}			
			}
		});

		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CustomerInfoForm customerInfoForm = new CustomerInfoForm(customer,benefitiary);
				customerInfoForm.customerInfoFrame.setVisible(true);
				registerFrame.setVisible(false);
			}
		});
	}

}
