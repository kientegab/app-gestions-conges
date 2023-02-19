/**
 *
 */
package bf.mfptps.appgestionsconges.service.dto;

import java.io.Serializable;

/**
 *
 * @author HEBIE
 */
public class PayReturnData implements Serializable {

    private static final long serialVersionUID = -757867576687681881L;

    private String status;

    private String telephone;

    private String message;

    private String lastCode;

    private String trxCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastCode() {
        return lastCode;
    }

    public void setLastCode(String lastCode) {
        this.lastCode = lastCode;
    }

    public String getTrxCode() {
        return trxCode;
    }

    public void setTrxCode(String trxCode) {
        this.trxCode = trxCode;
    }

    public PayReturnData() {
    }

    public PayReturnData(String status, String telephone, String message, String lastCode, String trxCode) {
        this.status = status;
        this.telephone = telephone;
        this.message = message;
        this.lastCode = lastCode;
        this.trxCode = trxCode;
    }

    @Override
    public String toString() {
        return "PayReturnData{" + "status=" + status + ", telephone=" + telephone + ", message=" + message + ", lastCode=" + lastCode + ", trxCode=" + trxCode + '}';
    }

}
