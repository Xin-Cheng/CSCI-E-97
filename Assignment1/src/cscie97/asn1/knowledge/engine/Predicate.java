package cscie97.asn1.knowledge.engine;

import java.time.Instant;

public class Predicate {
	private String identifier;
	private long createDate;
	
	public Predicate(String identifier) {
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
