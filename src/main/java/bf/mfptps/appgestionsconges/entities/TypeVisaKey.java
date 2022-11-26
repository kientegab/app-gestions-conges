package bf.mfptps.appgestionsconges.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TypeVisaKey implements Serializable {
    @Column(name = "visa_id")
    Long visaId;

    @Column(name = "type_demande_id")
    Long typeDemandeId;
}
