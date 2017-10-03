package cscie97.asn1.knowledge.engine;

import java.time.Instant;

public class Node {
	private String identifier;
	private long createDate;
	
	public Node(String identifier) {
		this.identifier = identifier;
		createDate = Instant.now().getEpochSecond();
	}
	
	public String getIdentifier(){
		return identifier;
	}
	
	public void setIdentifier(String identifier){
		this.identifier = identifier;
	}
	
	public void refresh(){
		createDate = Instant.now().getEpochSecond();
	}
	
	public long getCreateDate(){
		return createDate;
	}
}