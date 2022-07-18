package forms;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;

import models.Employee;
import models.Policy;
import services.PolicyService;
import shared.utils.CurrentUserHolder;

import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class PolicyForm {

	private JFrame frmPolicy;
	private JTextField txtPolicy;
	private JTextField txtDuration;
	private JTable tblPolicy;
	private JTextField txtSearch;
	private PolicyService policyService;
	private DefaultTableModel dtm = new DefaultTableModel();
	private Policy policy;
	private List<Policy> origianlPolicyList = new ArrayList<>();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PolicyForm window = new PolicyForm();
					window.frmPolicy.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PolicyForm() {
		initialize();
		initializeDependency();
		this.setTableDesign();
		this.loadAllPolicys(Optional.empty());
	}

	private void loadAllPolicys(Optional<List<Policy>> optionalPolicys) {
		this.dtm = (DefaultTableModel) this.tblPolicy.getModel();
		this.dtm.getDataVector().removeAllElements();
		this.dtm.fireTableDataChanged();

		this.origianlPolicyList = this.policyService.findAllPolicys();
		List<Policy> policyList = optionalPolicys.orElseGet(() -> origianlPolicyList);

		policyList.forEach(c -> {
			Object[] row = new Object[4];
			row[0] = c.getId();
			row[1] = c.getPlanName();
			row[2] = c.getDuration();
			dtm.addRow(row);
		});

		this.tblPolicy.setModel(dtm);
	}

	private void setTableDesign() {
		dtm.addColumn("ID");
		dtm.addColumn("Policy");
		dtm.addColumn("Duration");
		this.tblPolicy.setModel(dtm);
	}

	private void initializeDependency() {
		this.policyService = new PolicyService();
	}

	private void resetFormData() {
		txtPolicy.setText("");
		txtDuration.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPolicy = new JFrame();
		frmPolicy.setTitle("Policy");
		frmPolicy.setBounds(100, 100, 1000, 500);
		frmPolicy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPolicy.getContentPane().setLayout(null);

		JLabel lblPolicy = new JLabel("Policy");
		lblPolicy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPolicy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPolicy.setBounds(-23, 138, 85, 29);
		frmPolicy.getContentPane().add(lblPolicy);

		txtPolicy = new JTextField();
		txtPolicy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPolicy.setBounds(89, 136, 290, 29);
		frmPolicy.getContentPane().add(txtPolicy);
		txtPolicy.setColumns(10);

		JLabel lblNewLabel = new JLabel("test");
		lblNewLabel.setBounds(318, 37, 129, 42);
		frmPolicy.getContentPane().add(lblNewLabel);

		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDuration.setBounds(-15, 216, 85, 29);
		frmPolicy.getContentPane().add(lblDuration);

		txtDuration = new JTextField();
		txtDuration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDuration.setColumns(10);
		txtDuration.setBounds(87, 215, 290, 29);
		frmPolicy.getContentPane().add(txtDuration);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (null != policy && policy.getId() != 0) {

					policy.setPlanName(txtPolicy.getText());
					policy.setDuration(Integer.parseInt(txtDuration.getText()));

					if (!policy.getPlanName().isBlank() && policy.getDuration() != 0) {

						policyService.updatePolicy(String.valueOf(policy.getId()), policy);
						resetFormData();
						loadAllPolicys(Optional.empty());
						policy = null;

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				} else {
					Policy policy = new Policy();
					policy.setPlanName(txtPolicy.getText());
					policy.setDuration(Integer.parseInt(txtDuration.getText()));

					if (!policy.getPlanName().isBlank() && policy.getDuration() != 0) {

						policyService.savePolicy(policy);
						resetFormData();
						loadAllPolicys(Optional.empty());

					} else {
						JOptionPane.showMessageDialog(null, "Enter Required Field");
					}
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(47, 324, 85, 29);
		frmPolicy.getContentPane().add(btnSave);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != policy) {
					try {
						policyService.deletePolicy(policy.getId() + "");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					resetFormData();
					loadAllPolicys(Optional.empty());
					policy = null;
				} else {
					JOptionPane.showMessageDialog(null, "Choose Policy");
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDelete.setBounds(181, 324, 85, 29);
		frmPolicy.getContentPane().add(btnDelete);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();

				loadAllPolicys(Optional.of(origianlPolicyList.stream().filter(
						c -> c.getPlanName().toLowerCase(Locale.ROOT).startsWith(keyword.toLowerCase(Locale.ROOT)))
						.collect(Collectors.toList())));
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSearch.setBounds(873, 50, 85, 29);
		frmPolicy.getContentPane().add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 133, 574, 291);
		frmPolicy.getContentPane().add(scrollPane);

		tblPolicy = new JTable();
		tblPolicy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(tblPolicy);
		this.tblPolicy.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
			if (!tblPolicy.getSelectionModel().isSelectionEmpty()) {

				String id = tblPolicy.getValueAt(tblPolicy.getSelectedRow(), 0).toString();

				policy = policyService.findById(id);

				txtPolicy.setText(policy.getPlanName());
				txtDuration.setText(String.valueOf(policy.getDuration()));
				lblNewLabel.setText(policy.getCategory().getCategoryName());
			}
		});

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSearch.setColumns(10);
		txtSearch.setBounds(682, 50, 181, 29);
		frmPolicy.getContentPane().add(txtSearch);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategory.setBounds(10, 59, 72, 20);
		frmPolicy.getContentPane().add(lblCategory);

		JComboBox cboCategory = new JComboBox();
		cboCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboCategory.setModel(new DefaultComboBoxModel(new String[] { "Category" }));
		cboCategory.setBounds(89, 48, 145, 35);
		frmPolicy.getContentPane().add(cboCategory);

	}
}
