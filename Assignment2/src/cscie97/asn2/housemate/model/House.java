package cscie97.asn2.housemate.model;

public class House {

	private String name;
	private String address;
	
    /**
     * Public constructor
     * @param name unique house identifier
     * @param address house address
     */
	public House(String name, String address) {
		this.name = name;
		this.address = address;
	}
}
