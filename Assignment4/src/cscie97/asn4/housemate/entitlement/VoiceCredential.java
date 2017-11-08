package cscie97.asn4.housemate.entitlement;

public class VoiceCredential extends Credential{
	private String voiceSignature;
	
	/**
     * Public default constructor
     */
	public VoiceCredential() {
		super();
	}
	
	/**
     * Public method sets voice signature of a user
     * 
     * @param voiceSignature target voiceSignature
     */
	public void setVoiceSignature(String voiceSignature){
		this.voiceSignature = voiceSignature;
	}
}
