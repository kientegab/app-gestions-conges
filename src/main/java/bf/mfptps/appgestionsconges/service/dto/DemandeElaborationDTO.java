/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.MotifAbsence;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.enums.EPositionDemande;
import bf.mfptps.appgestionsconges.enums.EStatusDemande;
import bf.mfptps.appgestionsconges.enums.ETrancheDemande;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class DemandeElaborationDTO {

    private Long id;

    private String numeroDemande;

    private String lieuJouissanceBF;

    private String lieuJouissanceEtrang;

    private String refLastDecision;

    private String situationSND;

    private Long dureeAbsence;

    private Date periodeDebut;

    private Date periodeFin;

    private Date debutPeriodeOuvrant; //prevu pour etre renseigné par l'élaborateur de decision de congé annuel

    private Date finPeriodeOuvrant; //prevu pour etre renseigné par l'élaborateur de decision de congé annuel

    private Date debutPeriodeJouissance; //prevu pour etre calculé automatiquement pour decision de congé annuel

    private Date finPeriodeJouissance; //prevu pour etre calculé automatiquement pour decision de congé annuel

    private Long numStructure; //Ne pas en faire une relation. Ajouter pour faciliter les requete

    private Boolean elabore; // Oui si demande elaboré et acte validé

    private MotifAbsence motifAbsence;

    @JsonIgnoreProperties(value = {"demandes", "typeVisas", "articleTypeDemandes", "ampliation"})
    private TypeDemande typeDemande;

    @JsonIgnoreProperties(value = {"structure"})
    private Agent agent;

    private EStatusDemande statusDemande;

    private EPositionDemande positionDemande;

    private ETrancheDemande trancheDemande;

    private String motifRejet;

    @JsonIgnoreProperties(value = {"demandes"}, allowSetters = true)
    private Acte acte;

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

    public Date getDebutPeriodeOuvrant() {
        return debutPeriodeOuvrant;
    }

    public void setDebutPeriodeOuvrant(Date debutPeriodeOuvrant) {
        this.debutPeriodeOuvrant = debutPeriodeOuvrant;
    }

    public Date getFinPeriodeOuvrant() {
        return finPeriodeOuvrant;
    }

    public void setFinPeriodeOuvrant(Date finPeriodeOuvrant) {
        this.finPeriodeOuvrant = finPeriodeOuvrant;
    }

    public Date getDebutPeriodeJouissance() {
        return debutPeriodeJouissance;
    }

    public void setDebutPeriodeJouissance(Date debutPeriodeJouissance) {
        this.debutPeriodeJouissance = debutPeriodeJouissance;
    }

    public Date getFinPeriodeJouissance() {
        return finPeriodeJouissance;
    }

    public void setFinPeriodeJouissance(Date finPeriodeJouissance) {
        this.finPeriodeJouissance = finPeriodeJouissance;
    }

    public Long getNumStructure() {
        return numStructure;
    }

    public void setNumStructure(Long numStructure) {
        this.numStructure = numStructure;
    }

    public Boolean getElabore() {
        return elabore;
    }

    public void setElabore(Boolean elabore) {
        this.elabore = elabore;
    }

    public MotifAbsence getMotifAbsence() {
        return motifAbsence;
    }

    public void setMotifAbsence(MotifAbsence motifAbsence) {
        this.motifAbsence = motifAbsence;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public EStatusDemande getStatusDemande() {
        return statusDemande;
    }

    public void setStatusDemande(EStatusDemande statusDemande) {
        this.statusDemande = statusDemande;
    }

    public EPositionDemande getPositionDemande() {
        return positionDemande;
    }

    public void setPositionDemande(EPositionDemande positionDemande) {
        this.positionDemande = positionDemande;
    }

    public ETrancheDemande getTrancheDemande() {
        return trancheDemande;
    }

    public void setTrancheDemande(ETrancheDemande trancheDemande) {
        this.trancheDemande = trancheDemande;
    }

    public String getMotifRejet() {
        return motifRejet;
    }

    public void setMotifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
    }

    public Acte getActe() {
        return acte;
    }

    public void setActe(Acte acte) {
        this.acte = acte;
    }

}
