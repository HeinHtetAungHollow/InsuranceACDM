package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.Category;
import models.Policy;
import models.Register;
import models.UserRole;
import services.CategoryService;

import services.PolicyService;
import services.RegisterServices;
import shared.utils.CurrentUserHolder;

import com.toedter.calendar.JDateChooser;

public class MainForm {

	public JFrame mainFrame;
	private JTextField txtSearch;
	private JTable tblRegister;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Category> categoryList = new ArrayList<>();
	private CategoryService categoryService;
	private JComboBox<String> cboInsurance;
	private JComboBox<String> cboPlan;
	private Optional<Category> selectedCategory;
	private Optional<Policy> selectedPolicy;
	private List<Policy> policyList = new ArrayList<>();

	private PolicyService policyService;
	private RegisterServices registerServices;

	private List<Register> registerList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
		setTableDesign();
		initializeDependencies();
		loadCategoryForCbo();
		loadAllRegister(Optional.empty());
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Customer Name");
		dtm.addColumn("Phone");
		dtm.addColumn("Email");
		dtm.addColumn("Insurance");
		dtm.addColumn("Plan");
		dtm.addColumn("Payment Plan");
		dtm.addColumn("StartDate");
		dtm.addColumn("End Date");
		this.tblRegister.setModel(dtm);
	}

	private void initializeDependencies() {
		this.categoryService = new CategoryService();
		this.policyService = new PolicyService();
		this.registerServices = new RegisterServices();
	}

	private void loadCategoryForCbo() {
		this.cboInsurance.addItem("Select Category");
		this.categoryList = this.categoryService.findAllCategorys();
		this.categoryList.forEach(cl -> cboInsurance.addItem(cl.getCategory_name()));
	}

	private void loadAllRegister(Optional<List<Register>> optionalList) {
		this.dtm = (DefaultTableModel) this.tblRegister.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.registerList = this.registerServices.loadAllActiveRegisters();

		List<Register> filteredList = optionalList.orElseGet(() -> this.registerList).stream()
				.collect(Collectors.toList());

		filteredList.forEach(fl -> {
			Object[] dataRow = new Object[9];
			dataRow[0] = fl.getRegister_id();
			dataRow[1] = fl.getCustomer().getCustomer_name();
			dataRow[2] = fl.getCustomer().getCustomer_phone();
			dataRow[3] = fl.getCustomer().getCustomer_email();
			dataRow[4] = fl.getPlanDetail().getPaymentPlan().getPolicy().getCategory().getCategory_name();
			dataRow[5] = fl.getPlanDetail().getPaymentPlan().getPolicy().getPlanName();
			dataRow[6] = fl.getPlanDetail().getPaymentPlan().getPayplan();
			dataRow[7] = fl.getPlanDetail().getStartDate();
			dataRow[8] = fl.getPlanDetail().getEndDate();
			dtm.addRow(dataRow);
		});
		this.tblRegister.setModel(dtm);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBounds(150, 15, 1080, 720);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		JPanel DefaultPanel = new JPanel();
		DefaultPanel.setLayout(null);
		DefaultPanel.setBackground(new Color(153, 204, 255));
		DefaultPanel.setBounds(0, 0, 1064, 129);
		mainFrame.getContentPane().add(DefaultPanel);

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

		JButton btnClaimList = new JButton("Claim List");
		btnClaimList.setForeground(new Color(34, 139, 34));
		btnClaimList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClaimList.setBounds(244, 97, 112, 23);
		DefaultPanel.add(btnClaimList);

		JButton btnInsurance = new JButton("Insurance");
		btnInsurance.setForeground(new Color(34, 139, 34));
		btnInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInsurance.setBounds(368, 97, 99, 23);
		DefaultPanel.add(btnInsurance);
		btnInsurance.setVisible(CurrentUserHolder.getCurrentUser().getRole() != UserRole.STAFF);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.setForeground(new Color(34, 139, 34));
		btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEmployee.setBounds(480, 95, 99, 23);
		DefaultPanel.add(btnEmployee);
		btnEmployee.setVisible(CurrentUserHolder.getCurrentUser().getRole() != UserRole.STAFF);
		System.out.println(CurrentUserHolder.getCurrentUser().getRole());
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(34, 139, 34));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(923, 95, 99, 23);
		DefaultPanel.add(btnLogout);

		cboInsurance = new JComboBox<>();
		cboInsurance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// cboInsurance.setModel(new DefaultComboBoxModel(new String[] {"Insurance"}));
		cboInsurance.setBounds(45, 167, 145, 27);
		mainFrame.getContentPane().add(cboInsurance);

		cboPlan = new JComboBox<>();
		// cboPlan.setModel(new DefaultComboBoxModel(new String[] {"Plan"}));
		cboPlan.addItem("Plan");
		cboPlan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboPlan.setBounds(205, 167, 155, 27);
		mainFrame.getContentPane().add(cboPlan);

		txtSearch = new JTextField();
		txtSearch.setToolTipText("Seach By Register id,Customer Name,");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearch.setBounds(677, 167, 174, 27);
		mainFrame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(877, 167, 145, 27);
		mainFrame.getContentPane().add(btnSearch);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(45, 608, 145, 27);
		mainFrame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(234, 608, 145, 27);
		mainFrame.getContentPane().add(btnDelete);

		JButton btnClaim = new JButton("Claim");
		btnClaim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClaim.setBounds(877, 608, 145, 27);
		mainFrame.getContentPane().add(btnClaim);

		JButton btnPayPremium = new JButton("Pay Premium");
		btnPayPremium.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPayPremium.setBounds(706, 608, 145, 27);
		mainFrame.getContentPane().add(btnPayPremium);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 205, 977, 393);
		mainFrame.getContentPane().add(scrollPane);

		tblRegister = new JTable();
		tblRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblRegister);

		JDateChooser dateChooserStart = new JDateChooser();
		dateChooserStart.setBounds(372, 167, 145, 27);
		mainFrame.getContentPane().add(dateChooserStart);

		JDateChooser dateChooserEnd = new JDateChooser();
		dateChooserEnd.setBounds(529, 167, 136, 27);
		mainFrame.getContentPane().add(dateChooserEnd);

		JLabel lblInsurance = new JLabel("Insurance");
		lblInsurance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInsurance.setBounds(46, 139, 144, 18);
		mainFrame.getContentPane().add(lblInsurance);

		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPlan.setBounds(205, 139, 144, 18);
		mainFrame.getContentPane().add(lblPlan);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStartDate.setBounds(372, 139, 144, 18);
		mainFrame.getContentPane().add(lblStartDate);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEndDate.setBounds(529, 139, 144, 18);
		mainFrame.getContentPane().add(lblEndDate);

		JLabel lblRegisterIdName = new JLabel("Register Id, Name ..");
		lblRegisterIdName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRegisterIdName.setBounds(677, 139, 144, 18);
		mainFrame.getContentPane().add(lblRegisterIdName);

		

		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CustomerInfoForm customerInfoForm = new CustomerInfoForm();
				customerInfoForm.customerInfoFrame.setVisible(true);
				mainFrame.setVisible(false);
			}
		});

		btnClaimList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ClaimListForm claimListForm = new ClaimListForm();
				claimListForm.claimListFrame.setVisible(true);
				mainFrame.setVisible(false);
			}
		});

		btnInsurance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnEmployee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EmployeeForm employeeForm = new EmployeeForm();
				employeeForm.frame.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i=JOptionPane.showConfirmDialog(null, "Are u sure logout?");
				System.out.println(i);
				if (i==0) {
					CredentialInfoForm credentialInfoForm=new CredentialInfoForm();
					credentialInfoForm.frame.setVisible(true);
					mainFrame.setVisible(false);
					CurrentUserHolder.setLoggedInUser(null);
				}
				
			}
		});

		cboInsurance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboInsurance.getSelectedIndex() != 0 && cboInsurance.getSelectedIndex() != -1) {
					cboPlan.removeAllItems();
					cboPlan.addItem("Plan");
					selectedCategory = categoryList.stream()
							.filter(cl -> cl.getCategory_name().equals(cboInsurance.getSelectedItem())).findFirst();
					int id = selectedCategory.map(sc -> sc.getId()).get();
					policyList = policyService.findPolicyListByCategoryId(String.valueOf(id));
					policyList.forEach(pl -> cboPlan.addItem(pl.getPlanName()));

					loadAllRegister(
							Optional.of(
									registerList
											.stream().filter(register -> register.getPlanDetail().getPaymentPlan()
													.getPolicy().getCategory().getId() == id)
											.collect(Collectors.toList())));
				} else {
					cboPlan.removeAllItems();
					cboPlan.addItem("Plan");
				}
			}
		});

		cboPlan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cboPlan.getSelectedIndex() != 0 && cboPlan.getSelectedIndex() != -1) {
					selectedPolicy = policyList.stream()
							.filter(pl -> pl.getPlanName().equals(cboPlan.getSelectedItem())).findFirst();
					int policy_id = selectedPolicy.map(sp -> sp.getId()).get();

					loadAllRegister(Optional.of(registerList.stream().filter(
							register -> register.getPlanDetail().getPaymentPlan().getPolicy().getId() == policy_id

					).collect(Collectors.toList())));
				}
			}
		});
//		txtSearch.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				char c=e.getKeyChar();
//				String searchValue=txtSearch.getText()+c;
//				
//			}
//		});
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String keyword = txtSearch.getText();

				loadAllRegister(Optional.of(registerList.stream()
						.filter(register -> register.getCustomer().getCustomer_name().toLowerCase(Locale.ROOT)
								.startsWith(keyword.toLowerCase(Locale.ROOT))
								|| String.valueOf(register.getRegister_id()).toLowerCase(Locale.ROOT)
										.startsWith(keyword.toLowerCase(Locale.ROOT)))
						.collect(Collectors.toList())));
			}
		});

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null, "Are You Sure? Want To delete?");
			}
		});
	}

}
