package cscie97.asn2.housemate.model;

public class HouseMateModelService {
	private static HouseMateModelService instance;
	
	public static HouseMateModelService getInstance(){
		if(instance == null)
			instance = new HouseMateModelService();
		return instance;
	}
	
	public void executeCommand(String command){
		System.out.println(command);
	}
	public void addOccupant(String houseName){
		
	}
	
	public void define(String command) {
		
	}
	
	public void setStatus(String command){
		
	}
	
	public void show(String command) {
		
	}
}
