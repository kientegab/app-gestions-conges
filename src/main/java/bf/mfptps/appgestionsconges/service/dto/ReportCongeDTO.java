package bf.mfptps.appgestionsconges.service.dto;

import java.io.Serializable;

public class ReportCongeDTO implements Serializable{
	private String nom;
	private String prenom;
	private String typeconge;
	private String matricule;
	private String qualite;
	private String structureliblle;
	private String siglestructure;
	private String dateDebut;
	private String dateFin;
	
	
	
	

	public ReportCongeDTO(String nOM, String prenom, String typeconge, String matricule, String qualite,
			String structureliblle, String siglestructure, String dateDebut, String dateFin) {
		super();
		nom = nOM;
		prenom = prenom;
		this.typeconge = typeconge;
		this.matricule = matricule;
		this.qualite = qualite;
		this.structureliblle = structureliblle;
		this.siglestructure = siglestructure;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public ReportCongeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNOM() {
		return nom;
	}

	public void setNOM(String nOM) {
		nom = nOM;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		prenom = prenom;
	}

	public String getTypeconge() {
		return typeconge;
	}

	public void setTypeconge(String typeconge) {
		this.typeconge = typeconge;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public String getStructureliblle() {
		return structureliblle;
	}

	public void setStructureliblle(String structureliblle) {
		this.structureliblle = structureliblle;
	}

	public String getSiglestructure() {
		return siglestructure;
	}

	public void setSiglestructure(String siglestructure) {
		this.siglestructure = siglestructure;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
	
	

}
