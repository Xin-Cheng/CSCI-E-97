package cscie97.asn1.knowledge.engine;

public class Test {
	public static void main(String [] args)
	{
//		Node cdd = new Node("cdd");
//		Predicate predicate =  new Predicate("love");
//		Node cxx = new Node("cxx");
//		System.out.println(cdd.getIdentifier());
//		System.out.println(cdd.getCreateDate());
//		System.out.println(predicate.getIdentifier());
//		System.out.println(predicate.getCreateDate());
//		System.out.println(cxx.getIdentifier());
//		System.out.println(cxx.getCreateDate());
//		Triple triple = new Triple(cdd, predicate, cxx);
//		System.out.println(triple.getIdentifier());
//		System.out.println(triple.getCreateDate());
		Importer importer = new Importer();
		try {
			importer.importTripleFile("inputTriples.nt");
		} catch (ImportException ie) {
			System.out.println("Import Exception: " + ie.getMessage());
		}
	}
}
