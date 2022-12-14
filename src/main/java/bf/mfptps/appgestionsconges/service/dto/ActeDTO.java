package bf.mfptps.appgestionsconges.service.dto;

import java.util.HashSet;
import java.util.Set;

import bf.mfptps.appgestionsconges.enums.EStatusActe;


public class ActeDTO{

    private Long id;
    
    private String reference;
    
    private String enteteMinistere;
    
    private EStatusActe status;
    
    private String ampliation;
    
    private TypeActeDTO typeActe;
    
    private String annee;
    
    private Set<DemandeDTO> demandes = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEnteteMinistere() {
		return enteteMinistere;
	}

	public void setEnteteMinistere(String enteteMinistere) {
		this.enteteMinistere = enteteMinistere;
	}

	public EStatusActe getStatus() {
		return status;
	}

	public void setStatus(EStatusActe status) {
		this.status = status;
	}

	public String getAmpliation() {
		return ampliation;
	}

	public void setAmpliation(String ampliation) {
		this.ampliation = ampliation;
	}

	public TypeActeDTO getTypeActe() {
		return typeActe;
	}

	public void setTypeActe(TypeActeDTO typeActe) {
		this.typeActe = typeActe;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public Set<DemandeDTO> getDemandes() {
		return demandes;
	}

	public void setDemandes(Set<DemandeDTO> demandes) {
		this.demandes = demandes;
	}
        
}
