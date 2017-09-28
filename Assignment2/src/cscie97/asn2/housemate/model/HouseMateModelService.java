package cscie97.asn2.housemate.model;

public class HouseMateModelService {
	private static HouseMateModelService instance;
	
	public static HouseMateModelService getInstance(){
		if(instance == null)
			instance = new HouseMateModelService();
		return instance;
	}
}
