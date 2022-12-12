package bf.mfptps.appgestionsconges.service.dto;

import java.util.Date;

import bf.mfptps.appgestionsconges.enums.EStatusDemande;

public class DemandeDTO {
    private Long id;
    private String numeroDemande;
    private String lieuJouissanceBF;
    private String lieuJouissanceEtrang;
    private String refLastDecision;
    private String situationSND;
    private Long dureeAbsence;

    private Date periodeDebut;

    private Date periodeFin;
    private MotifAbsenceDTO motifAbsence;

    private TypeDemandeDTO typeDemande;

    private AgentDTO agent ;
    
    private EStatusDemande statusDemande;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public String getLieuJouissanceBF() {
        return lieuJouissanceBF;
    }

    public void setLieuJouissanceBF(String lieuJouissanceBF) {
        this.lieuJouissanceBF = lieuJouissanceBF;
    }

    public String getLieuJouissanceEtrang() {
        return lieuJouissanceEtrang;
    }

    public void setLieuJouissanceEtrang(String lieuJouissanceEtrang) {
        this.lieuJouissanceEtrang = lieuJouissanceEtrang;
    }

    public String getRefLastDecision() {
        return refLastDecision;
    }

    public void setRefLastDecision(String refLastDecision) {
        this.refLastDecision = refLastDecision;
    }

    public String getSituationSND() {
        return situationSND;
    }

    public void setSituationSND(String situationSND) {
        this.situationSND = situationSND;
    }

    public Long getDureeAbsence() {
        return dureeAbsence;
    }

    public void setDureeAbsence(Long dureeAbsence) {
        this.dureeAbsence = dureeAbsence;
    }

    public Date getPeriodeDebut() {
        return periodeDebut;
    }

    public void setPeriodeDebut(Date periodeDebut) {
        this.periodeDebut = periodeDebut;
    }

    public Date getPeriodeFin() {
        return periodeFin;
    }

    public void setPeriodeFin(Date periodeFin) {
        this.periodeFin = periodeFin;
    }

    public MotifAbsenceDTO getMotifAbsence() {
        return motifAbsence;
    }

    public void setMotifAbsence(MotifAbsenceDTO motifAbsence) {
        this.motifAbsence = motifAbsence;
    }

    public TypeDemandeDTO getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemandeDTO typeDemande) {
        this.typeDemande = typeDemande;
    }

    public AgentDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentDTO agent) {
        this.agent = agent;
    }

	public EStatusDemande getStatusDemande() {
		return statusDemande;
	}

	public void setStatusDemande(EStatusDemande statusDemande) {
		this.statusDemande = statusDemande;
	}
}
