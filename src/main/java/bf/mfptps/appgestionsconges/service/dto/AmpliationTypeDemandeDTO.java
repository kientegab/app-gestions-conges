package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.Ampliation;
import bf.mfptps.appgestionsconges.entities.Article;
import bf.mfptps.appgestionsconges.entities.TypeDemande;

public class AmpliationTypeDemandeDTO {

    private Long id;

    private Ampliation ampliation;

    private TypeDemande typeDemande;

    public AmpliationTypeDemandeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ampliation getAmpliation() {
        return ampliation;
    }

    public void setAmpliation(Ampliation ampliation) {
        this.ampliation = ampliation;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }
}
