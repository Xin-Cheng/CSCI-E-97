package cscie97.asn1.knowledge.engine;

import java.time.Instant;

class Node {
	private String identifier;
	private long createDate;
	
	public Node(String identifier) {
		this.identifier = identifier;
		createDate = Instant.now().getEpochSecond();
	}
	
	public String getIdentifier(){
		return identifier;
	}
	
	public long getCreateDate(){
		return createDate;
	}
}