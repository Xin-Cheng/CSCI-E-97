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
	
	public static void main(String [] args)
	{
		Node cdd = new Node("cdd");
		Predicate predicate =  new Predicate("love");
		Node cxx = new Node("cxx");
		System.out.println(cdd.getIdentifier());
		System.out.println(cdd.getCreateDate());
		System.out.println(predicate.getIdentifier());
		System.out.println(predicate.getCreateDate());
		System.out.println(cxx.getIdentifier());
		System.out.println(cxx.getCreateDate());
		Triple triple = new Triple(cdd, predicate, cxx);
		System.out.println(triple.getIdentifier());
		System.out.println(triple.getCreateDate());
		Importer importer = new Importer();
		try {
			importer.importTripleFile("inputTriples.nt");
		} catch (ImportException ie) {
			System.out.println("Import Exception!");
		}
	}
}
