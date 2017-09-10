package cscie97.asn1.knowledge.engine;

import java.time.Instant;

public class Triple {
	/* Properties */
	private String identifier;
	private long createDate;
	
	/* Associations */
	private Node subject;
	private Predicate predicate;
	private Node object;
	
	public Triple(Node subject, Predicate predicate, Node object) {
		identifier = subject.getIdentifier() + " " + predicate.getIdentifier() + " " + object.getIdentifier();
		createDate = Instant.now().getEpochSecond();
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
	}
	
	public String getIdentifier(){
		return identifier;
	}
	
	public long getCreateDate(){
		return createDate;
	}
}
