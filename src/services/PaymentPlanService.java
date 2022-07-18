package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.PaymentPlan;
import repositories.PaymentPlanRepo;
import config.DBConfig;
import shared.exception.AppException;

import javax.swing.*;

public class PaymentPlanService  {

    private final DBConfig dbConfig = new DBConfig();

    public void savePaymentPlan(PaymentPlan paymentPlan) 
    {
//    	JOptionPane.showMessageDialog(null, paymentPlan.getPayplan());
        try {

            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("INSERT INTO payment_plan (payplan)  VALUES (?);");

            ps.setInt(1, paymentPlan.getPayplan());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            if (e instanceof SQLException) {
                JOptionPane.showMessageDialog(null, "Already Exists");
            }
        }
    }

    public void updatePaymentPlan(String id, PaymentPlan paymentPlan) {
        try {

            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("UPDATE payment_plan SET payplan = ? WHERE id = ?");

            ps.setInt(1, paymentPlan.getPayplan());
            ps.setString(2, id);
            ps.execute();

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PaymentPlan> findAllPaymentPlans() {

        List<PaymentPlan> paymentPlanList = new ArrayList<>();
        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM payment_plan";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
            	PaymentPlan p = new PaymentPlan();
                p.setId((rs.getInt("id")));
                p.setPayplan(rs.getInt("payplan"));
                paymentPlanList.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paymentPlanList;
    }

    public PaymentPlan findById(String id) {
    	PaymentPlan paymentPlan = new PaymentPlan();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM payment_plan WHERE id = " + id + ";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
            	paymentPlan.setId(rs.getInt("id"));
            	paymentPlan.setPayplan(rs.getInt("payplan"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paymentPlan;
    }

    public void deletePaymentPlan(String id) throws SQLException {
      
            String query = "DELETE FROM payment_plan WHERE id= ?;";

            PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(query);

            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();

        
    }

}
