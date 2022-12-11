package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.enums.EStatusActe;


public class ActeDTO{

    private Long id;
    private String reference;
    private EStatusActe status;
    private String ampliation;
    
    private TypeActeDTO typeActe;
    

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
    
    
}
