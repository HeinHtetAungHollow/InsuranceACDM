package forms;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;

import models.PaymentPlan;
import services.PaymentPlanService;
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

public class PaymentPlanForm {

    private JFrame frmPaymentPlan;
    private JTextField txtPaymentPlan;
    private JTable tblPaymentPlan;
    private JTextField txtSearch;
    private PaymentPlanService paymentPlanService;
    private DefaultTableModel dtm = new DefaultTableModel();
    private PaymentPlan paymentPlan;
    private List<PaymentPlan> origianlPaymentPlanList = new ArrayList<>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	PaymentPlanForm window = new PaymentPlanForm();
                    window.frmPaymentPlan.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public PaymentPlanForm() {
        initialize();
        initializeDependency();
        this.setTableDesign();
        this.loadAllPaymentPlans(Optional.empty());
    }

    private void loadAllPaymentPlans(Optional<List<PaymentPlan>> optionalPaymentPlans) {
        this.dtm = (DefaultTableModel) this.tblPaymentPlan.getModel();
        this.dtm.getDataVector().removeAllElements();
        this.dtm.fireTableDataChanged();

        this.origianlPaymentPlanList = this.paymentPlanService.findAllPaymentPlans();
        List<PaymentPlan> paymentPlanList = optionalPaymentPlans.orElseGet(() -> origianlPaymentPlanList);

        paymentPlanList.forEach(c -> {
            Object[] row = new Object[2];
            row[0] = c.getId();
            row[1] = c.getPayplan();
            dtm.addRow(row);
        });

        this.tblPaymentPlan.setModel(dtm);
    }

    private void setTableDesign() {
        dtm.addColumn("ID");
        dtm.addColumn("Payment_plan Name");
        this.tblPaymentPlan.setModel(dtm);
    }

    private void initializeDependency() {
        this.paymentPlanService = new PaymentPlanService();
    }

    private void resetFormData() {
        txtPaymentPlan.setText("");
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmPaymentPlan = new JFrame();
        frmPaymentPlan.setTitle("PaymentPlan Form");
        frmPaymentPlan.setBounds(100, 100, 1000, 500);
        frmPaymentPlan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmPaymentPlan.getContentPane().setLayout(null);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (null != paymentPlan && paymentPlan.getId() != 0) 
                {
                	
                	paymentPlan.setPayplan(Integer.parseInt(txtPaymentPlan.getText()));

                    if (paymentPlan.getPayplan() !=0) 
                    {
                    	paymentPlanService.updatePaymentPlan(String.valueOf(paymentPlan.getId()), paymentPlan);
                        resetFormData();
                        loadAllPaymentPlans(Optional.empty());
                        paymentPlan = null;
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Enter required field");
                    }
                } 
                else 
                {
//                	JOptionPane.showMessageDialog(null, "here");
                	PaymentPlan paymentPlan = new PaymentPlan();
                	paymentPlan.setPayplan(Integer.parseInt(txtPaymentPlan.getText()));

                    if (paymentPlan.getPayplan() !=0 ) {

                    	paymentPlanService.savePaymentPlan(paymentPlan);
                        resetFormData();
                        loadAllPaymentPlans(Optional.empty());
                    } 
                    else 
                    {
                        JOptionPane.showMessageDialog(null, "Enter Required Field!");
                    }
                }

            }
        });
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSave.setBounds(403, 65, 85, 29);
        frmPaymentPlan.getContentPane().add(btnSave);

        txtPaymentPlan = new JTextField();
        txtPaymentPlan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtPaymentPlan.setBounds(95, 65, 290, 29);
        frmPaymentPlan.getContentPane().add(txtPaymentPlan);
        txtPaymentPlan.setColumns(10);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (null != paymentPlan) {
                	try {
						paymentPlanService.deletePaymentPlan(paymentPlan.getId() + "");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    resetFormData();
                    loadAllPaymentPlans(Optional.empty());
                    paymentPlan = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Choose Paymentplan");
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnDelete.setBounds(498, 65, 85, 29);
        frmPaymentPlan.getContentPane().add(btnDelete);

        JLabel lblNewLabel = new JLabel("PaymentPlan");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(0, 61, 85, 29);
        frmPaymentPlan.getContentPane().add(lblNewLabel);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = txtSearch.getText();

                loadAllPaymentPlans(Optional.of(origianlPaymentPlanList.stream().filter(c -> String.valueOf(c.getPayplan()).toLowerCase(Locale.ROOT)
                        .startsWith(keyword.toLowerCase(Locale.ROOT))).collect(Collectors.toList())));
            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSearch.setBounds(879, 65, 85, 29);
        frmPaymentPlan.getContentPane().add(btnSearch);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(24, 134, 940, 290);
        frmPaymentPlan.getContentPane().add(scrollPane);

        tblPaymentPlan = new JTable();
        tblPaymentPlan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        scrollPane.setViewportView(tblPaymentPlan);
        this.tblPaymentPlan.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!tblPaymentPlan.getSelectionModel().isSelectionEmpty()) {

                String id = tblPaymentPlan.getValueAt(tblPaymentPlan.getSelectedRow(), 0).toString();

                paymentPlan = paymentPlanService.findById(id);

                txtPaymentPlan.setText(String.valueOf(paymentPlan.getPayplan()));

            }
        });

        txtSearch = new JTextField();
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtSearch.setColumns(10);
        txtSearch.setBounds(687, 65, 181, 29);
        frmPaymentPlan.getContentPane().add(txtSearch);
    }
}
