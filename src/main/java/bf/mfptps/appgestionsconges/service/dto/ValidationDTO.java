package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.enums.EPositionDemande;
import bf.mfptps.appgestionsconges.enums.ETypeValidation;

public class ValidationDTO {
	private String numeroDemande;
	private String matriculeValidateur;
	private ETypeValidation enumValidation;
	private String avis;
	private EPositionDemande typeValidator;
	
	public String getNumeroDemande() {
		return numeroDemande;
	}
	public void setNumeroDemande(String numeroDemande) {
		this.numeroDemande = numeroDemande;
	}
	public String getMatriculeValidateur() {
		return matriculeValidateur;
	}
	public void setMatriculeValidateur(String matriculeValidateur) {
		this.matriculeValidateur = matriculeValidateur;
	}
	public ETypeValidation getEnumValidation() {
		return enumValidation;
	}
	public void setEnumValidation(ETypeValidation enumValidation) {
		this.enumValidation = enumValidation;
	}
	public String getAvis() {
		return avis;
	}
	public void setAvis(String avis) {
		this.avis = avis;
	}
	public EPositionDemande getTypeValidator() {
		return typeValidator;
	}
	public void setTypeValidator(EPositionDemande typeValidator) {
		this.typeValidator = typeValidator;
	}
		
}
