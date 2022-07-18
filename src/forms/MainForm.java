package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.Category;
import services.CategoryService;

public class MainForm {

	public JFrame mainFrame;
	private JTextField txtSearch;
	private JTable tblRegister;
	private DefaultTableModel dtm = new DefaultTableModel();
	private List<Category> categoryList = new ArrayList<>();
	private CategoryService categoryService;
	private JComboBox<String> cboInsurance;
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
	}
	
	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Phone");
		dtm.addColumn("Email");
		dtm.addColumn("Address");
		dtm.addColumn("Username");
		dtm.addColumn("Status");
		this.tblRegister.setModel(dtm);
	}
	
	private void initializeDependencies() {
		this.categoryService=new CategoryService();
	}
	
	private void loadCategoryForCbo() {
		this.cboInsurance.addItem("Select Category");
		this.categoryList=this.categoryService.findAllCategorys();
		this.categoryList.forEach(cl-> cboInsurance.addItem(cl.getCategoryName()));
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
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.setForeground(new Color(34, 139, 34));
		btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEmployee.setBounds(480, 95, 99, 23);
		DefaultPanel.add(btnEmployee);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(new Color(34, 139, 34));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(923, 95, 99, 23);
		DefaultPanel.add(btnLogout);
		
		cboInsurance = new JComboBox<>();
		cboInsurance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//cboInsurance.setModel(new DefaultComboBoxModel(new String[] {"Insurance"}));
		cboInsurance.setBounds(45, 139, 155, 27);
		mainFrame.getContentPane().add(cboInsurance);
		
		JComboBox cboPlan = new JComboBox();
		cboPlan.setModel(new DefaultComboBoxModel(new String[] {"Plan"}));
		cboPlan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboPlan.setBounds(234, 139, 155, 27);
		mainFrame.getContentPane().add(cboPlan);
		
		txtSearch = new JTextField();
		txtSearch.setToolTipText("Seach By Register id,Customer Name,");
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearch.setBounds(605, 139, 246, 27);
		mainFrame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(877, 139, 145, 27);
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
		scrollPane.setBounds(45, 183, 977, 415);
		mainFrame.getContentPane().add(scrollPane);

		tblRegister = new JTable();
		tblRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblRegister);

		
		txtSearch.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				char c=e.getKeyChar();
				String searchValue=txtSearch.getText()+c;
				
			}
		});
	}
}
