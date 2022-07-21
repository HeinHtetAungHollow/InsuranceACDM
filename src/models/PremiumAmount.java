package models;

import java.util.ArrayList;
import java.util.List;

public class PremiumAmount {
	List<Long> premiumList=new ArrayList<>();
	
	public PremiumAmount() {
		// TODO Auto-generated constructor stub
		premiumList.add(5000000L);
		premiumList.add(10000000L);
		premiumList.add(15000000L);
		
	}
	
	public void setPremiumList(List<Long> premiumList) {
		this.premiumList=premiumList;
	}
	
	public List<Long> getPremiumList(int lvl) {
		
//		calculate(lvl);
//		System.out.println(this.premiumList.get(1));
		return calculate(lvl);
	}
	
	public List<Long> calculate(int medicalHistoryLevel) {
		List<Long> actList=new ArrayList<>();
		if (medicalHistoryLevel==3) {
			this.premiumList.forEach(pl->{
				long add=(long) (pl*0.2);
				pl+=add;
				actList.add(pl);
			});
		}else if (medicalHistoryLevel>3) {
			this.premiumList.forEach(pl->{
				long add=(long) (pl*0.5);
				pl+=add;
				actList.add(pl);
			});
		}
		
		return actList;
	}
}
