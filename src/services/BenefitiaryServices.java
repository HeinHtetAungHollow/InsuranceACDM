package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.Benefitiary;
import repositories.BenefitiaryRepo;
import shared.mapper.BenefitiaryMapper;

public class BenefitiaryServices implements BenefitiaryRepo {
	private DBConfig dbConfig;
	private BenefitiaryMapper benefitiaryMapper;

	public BenefitiaryServices() {
		this.dbConfig = new DBConfig();
		this.benefitiaryMapper = new BenefitiaryMapper();
	}

	@Override
	public void createBenefitiary(Benefitiary benefitiary) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.dbConfig.getConnection().prepareStatement(
					"INSERT INTO recepient (rep_name,rep_nrc,rep_phone,rep_email,rep_address) VALUES (?,?,?,?,?)");
			pst.setString(1, benefitiary.getBenefitiary_name());
			pst.setString(2, benefitiary.getBenefitiary_nrc());
			pst.setString(3, benefitiary.getBenefitiary_phone());
			pst.setString(4, benefitiary.getBenefitiary_email());
			pst.setString(5, benefitiary.getBenefitiary_address());

			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Already Exists!");
			} else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Benefitiary> loadAllBenefitiary() {
		// TODO Auto-generated method stub
		List<Benefitiary> benefitiaryList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "SELECT * FROM recepient ";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Benefitiary benefitiary = new Benefitiary();
				benefitiaryList.add(this.benefitiaryMapper.mapToBenefitiary(benefitiary, rs));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return benefitiaryList;
	}

	@Override
	public Benefitiary findById(String id) {
		// TODO Auto-generated method stub
		Benefitiary benefitiary = new Benefitiary();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "SELECT * FROM recepient WHERE rep_id='" + id + "'";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				benefitiary = this.benefitiaryMapper.mapToBenefitiary(benefitiary, rs);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return benefitiary;
	}

	@Override
	public void updateBenefitiary(String id, Benefitiary benefitiary) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.dbConfig.getConnection().prepareStatement(
					"UPDATE recepient SET rep_name=?,rep_nrc=?,rep_phone=?,rep_email=?,rep_address=? WHERE rep_id='"
							+ id + "'");
			pst.setString(1, benefitiary.getBenefitiary_name());
			pst.setString(2, benefitiary.getBenefitiary_nrc());
			pst.setString(3, benefitiary.getBenefitiary_phone());
			pst.setString(4, benefitiary.getBenefitiary_email());
			pst.setString(5, benefitiary.getBenefitiary_address());
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			if (e instanceof SQLIntegrityConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "Already Exists!");
			} else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteBenefitiary(String id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst = this.dbConfig.getConnection()
					.prepareStatement("DELETE FROM recepient WHERE rep_id='" + id + "'");
			pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
