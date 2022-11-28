package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.enums.EPorteActe;

public class TypeActeDTO{

    private Long id;
    private String reference;
    
    private String libelle;
    
    private String templateUri;
    
    private EPorteActe typeActe;
    

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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getTemplateUri() {
		return templateUri;
	}

	public void setTemplateUri(String templateUri) {
		this.templateUri = templateUri;
	}

	public EPorteActe getTypeActe() {
		return typeActe;
	}

	public void setTypeActe(EPorteActe typeActe) {
		this.typeActe = typeActe;
	}
    
}
