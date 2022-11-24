package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.Demande;

public class AvisDTO {

    private Long id;

    private String avisDRH;

    private String avisSG;

    private String avisSH;

    private String avisDG;

    private Demande demande;

    public AvisDTO() {
    }

    public AvisDTO(Long id, String avisDRH, String avisSG, String avisSH, String avisDG, Demande demande) {
        this.id = id;
        this.avisDRH = avisDRH;
        this.avisSG = avisSG;
        this.avisSH = avisSH;
        this.avisDG = avisDG;
        this.demande = demande;
    }
}
