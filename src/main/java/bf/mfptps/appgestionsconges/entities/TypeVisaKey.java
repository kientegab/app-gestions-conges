package bf.mfptps.appgestionsconges.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TypeVisaKey implements Serializable {
    Long visaId;

    Long typeDemandeId;

    public TypeVisaKey() {
    }

    public Long getVisaId() {
        return visaId;
    }

    public void setVisaId(Long visaId) {
        this.visaId = visaId;
    }

    public Long getTypeDemandeId() {
        return typeDemandeId;
    }

    public void setTypeDemandeId(Long typeDemandeId) {
        this.typeDemandeId = typeDemandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeVisaKey that = (TypeVisaKey) o;
        return visaId.equals(that.visaId) && typeDemandeId.equals(that.typeDemandeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visaId, typeDemandeId);
    }
}
