package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.CommonEntity;
import bf.mfptps.appgestionsconges.entities.TypeDemande;
import bf.mfptps.appgestionsconges.entities.TypeVisaKey;
import bf.mfptps.appgestionsconges.entities.Visa;

import javax.persistence.EmbeddedId;
import java.util.Objects;

public class TypeVisaDTO {
//    TypeVisaKey id;
    private Long id;

    private Visa visa;

    private TypeDemande typeDemande;

    private Long numeroOrdre;

    public TypeVisaDTO() {
    }

//    public TypeVisaDTO(TypeVisaKey id, Visa visa, TypeDemande typeDemande, Long numeroOrdre) {
//        this.id = id;
//        this.visa = visa;
//        this.typeDemande = typeDemande;
//        this.numeroOrdre = numeroOrdre;
//    }


    public TypeVisaDTO(Long id, Visa visa, TypeDemande typeDemande, Long numeroOrdre) {
        this.id = id;
        this.visa = visa;
        this.typeDemande = typeDemande;
        this.numeroOrdre = numeroOrdre;
    }

//    public TypeVisaKey getId() {
//        return id;
//    }
//
//    public void setId(TypeVisaKey id) {
//        this.id = id;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visa getVisa() {
        return visa;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Long getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Long numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TypeVisaDTO that = (TypeVisaDTO) o;
//        return id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @Override
//    public String toString() {
//        return "TypeVisaDTO{" +
//                "id=" + id +
//                ", visa=" + visa +
//                ", typeDemande=" + typeDemande +
//                ", numeroOrdre=" + numeroOrdre +
//                '}';
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeVisaDTO that = (TypeVisaDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeVisaDTO{" +
                "id=" + id +
                ", visa=" + visa +
                ", typeDemande=" + typeDemande +
                ", numeroOrdre=" + numeroOrdre +
                '}';
    }
}
