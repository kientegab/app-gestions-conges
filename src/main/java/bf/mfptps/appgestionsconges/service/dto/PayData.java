/**
 *
 */
package bf.mfptps.appgestionsconges.service.dto;

import java.io.Serializable;

/**
 *
 * @author HEBIE
 */
public class PayData implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7348194160129165396L;

    //PRC
    private Integer codeT;

    //FNS
    private Integer codeU;

    //PRM
    private Integer codeD;

    //TRX
    private Integer codeZ;

    private String telephone;

    private Double montant;

    private String codeOTP;

    private String login;

    private String password;

    public Integer getCodeT() {
        return codeT;
    }

    public void setCodeT(Integer codeT) {
        this.codeT = codeT;
    }

    public Integer getCodeU() {
        return codeU;
    }

    public void setCodeU(Integer codeU) {
        this.codeU = codeU;
    }

    public Integer getCodeD() {
        return codeD;
    }

    public void setCodeD(Integer codeD) {
        this.codeD = codeD;
    }

    public Integer getCodeZ() {
        return codeZ;
    }

    public void setCodeZ(Integer codeZ) {
        this.codeZ = codeZ;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getCodeOTP() {
        return codeOTP;
    }

    public void setCodeOTP(String codeOTP) {
        this.codeOTP = codeOTP;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PayData() {
    }

    public PayData(Integer codeT, Integer codeU, Integer codeD, Integer codeZ, String telephone, Double montant, String codeOTP, String login, String password) {
        this.codeT = codeT;
        this.codeU = codeU;
        this.codeD = codeD;
        this.codeZ = codeZ;
        this.telephone = telephone;
        this.montant = montant;
        this.codeOTP = codeOTP;
        this.login = login;
        this.password = password;
    }

}
