package bf.mfptps.appgestionsconges.service.dto;



public class ValideDemandeDto {
	
	
    private Long id;
 
    private Long demande_id;
   
    private String avisDRH;

  
    private String avisSG;

   
    private String avisSH;

    
    private String avisDG;

    

	public ValideDemandeDto() {
	
	}



	public ValideDemandeDto(Long id, Long demande_id, String avisDRH, String avisSG, String avisSH, String avisDG) {
		super();
		this.id = id;
		this.demande_id = demande_id;
		this.avisDRH = avisDRH;
		this.avisSG = avisSG;
		this.avisSH = avisSH;
		this.avisDG = avisDG;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getDemande_id() {
		return demande_id;
	}



	public void setDemande_id(Long demande_id) {
		this.demande_id = demande_id;
	}



	public String getAvisDRH() {
		return avisDRH;
	}



	public void setAvisDRH(String avisDRH) {
		this.avisDRH = avisDRH;
	}



	public String getAvisSG() {
		return avisSG;
	}



	public void setAvisSG(String avisSG) {
		this.avisSG = avisSG;
	}



	public String getAvisSH() {
		return avisSH;
	}



	public void setAvisSH(String avisSH) {
		this.avisSH = avisSH;
	}



	public String getAvisDG() {
		return avisDG;
	}



	public void setAvisDG(String avisDG) {
		this.avisDG = avisDG;
	}
    
	
	
    
    
    


}
