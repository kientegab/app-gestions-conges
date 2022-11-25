package bf.mfptps.appgestionsconges.service.dto;

public class TypeVisaDTO  {

    private Long id;

    private VisaDTO visa;

    private TypeDemandeDTO typeDemande;
    private Long numeroOrdre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VisaDTO getVisa() {
        return visa;
    }

    public void setVisa(VisaDTO visa) {
        this.visa = visa;
    }

    public TypeDemandeDTO getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemandeDTO typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Long getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Long numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }


}
