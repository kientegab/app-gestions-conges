package bf.mfptps.appgestionsconges.service.dto;

/**
 *
 * @author HEBIE
 */
public class PayInitData {

    private String payNumber;

    private int montant;

    private ModalitePaieDTO provider;

    public PayInitData() {
    }

    public PayInitData(String payNumber, int montant, ModalitePaieDTO provider) {
        this.payNumber = payNumber;
        this.montant = montant;
        this.provider = provider;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public ModalitePaieDTO getProvider() {
        return provider;
    }

    public void setProvider(ModalitePaieDTO provider) {
        this.provider = provider;
    }

}
