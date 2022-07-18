package models;

public class Benefitiary {
	private int id;
	private String benefitiary_name;
	private String benefitiary_nrc;
	private String benefitiary_phone;
	private String benefitiary_email;
	private String benefitiary_address;
	private String relation;

	public Benefitiary() {

	}

	public Benefitiary(int id, String benefitiary_name, String benefitiary_nrc, String benefitiary_phone,
			String benefitiary_email, String benefitiary_address, String relation) {
		super();
		this.id = id;
		this.benefitiary_name = benefitiary_name;
		this.benefitiary_nrc = benefitiary_nrc;
		this.benefitiary_phone = benefitiary_phone;
		this.benefitiary_email = benefitiary_email;
		this.benefitiary_address = benefitiary_address;
		this.relation = relation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBenefitiary_name() {
		return benefitiary_name;
	}

	public void setBenefitiary_name(String benefitiary_name) {
		this.benefitiary_name = benefitiary_name;
	}

	public String getBenefitiary_nrc() {
		return benefitiary_nrc;
	}

	public void setBenefitiary_nrc(String benefitiary_nrc) {
		this.benefitiary_nrc = benefitiary_nrc;
	}

	public String getBenefitiary_phone() {
		return benefitiary_phone;
	}

	public void setBenefitiary_phone(String benefitiary_phone) {
		this.benefitiary_phone = benefitiary_phone;
	}

	public String getBenefitiary_email() {
		return benefitiary_email;
	}

	public void setBenefitiary_email(String benefitiary_email) {
		this.benefitiary_email = benefitiary_email;
	}

	public String getBenefitiary_address() {
		return benefitiary_address;
	}

	public void setBenefitiary_address(String benefitiary_address) {
		this.benefitiary_address = benefitiary_address;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
