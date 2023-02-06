/**
 *
 */
package bf.mfptps.appgestionsconges.utils;

import bf.mfptps.appgestionsconges.service.dto.ModalitePaieDTO;
import bf.mfptps.appgestionsconges.service.dto.PayData;
import bf.mfptps.appgestionsconges.service.dto.PayReturnData;
import bf.mfptps.appgestionsconges.web.exceptions.AppTechnicalException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaymentUtil {

    /*
	 * function to validate a specific payment
     */
    public static PayReturnData validatePayment(String appURL, String lastCode, String payNumber, String otpNumber, double amount, Long remoteProviderID, Long remotePRMID, Long remoteProcessID) throws AppTechnicalException {
        try {
            PayData data = new PayData();

            log.error("==============++========> HERE FOR PAYMENT");

            Integer remoteProvider = (remoteProviderID != null && remoteProviderID > 0) ? Integer.parseInt(remoteProviderID.toString()) : -1;
            Integer remoteParam = (remotePRMID != null && remotePRMID > 0) ? Integer.parseInt(remotePRMID.toString()) : -1;
            Integer remoteProcess = (remoteProcessID != null && remoteProcessID > 0) ? Integer.parseInt(remoteProcessID.toString()) : -1;
            Integer lastCde = (lastCode != null && !lastCode.isEmpty()) ? Integer.parseInt(lastCode.toString()) : -1;

            data.setCodeU(remoteProvider);
            data.setCodeD(remoteParam);
            data.setCodeT(remoteProcess);
            data.setCodeZ(lastCde);

            data.setCodeOTP(otpNumber);

            data.setLogin("");
            data.setPassword("");
            data.setMontant(amount);
            data.setTelephone(payNumber);

            String url = appURL + AppDataProvider.transxPath + "verify";

            log.error("==============++========> HERE FOR PAYMENT PATH = " + url);

            JsonObject jsonObject = AppDataProvider.makePOST(data, url);

            log.error("==============++========> HERE FOR PAYMENT OBJECT = " + jsonObject);

            if (jsonObject != null && jsonObject.getString("status").equalsIgnoreCase("OK")) {
                PayReturnData retData = AppJsonConverter.decodeItfromJson(jsonObject.get("payload").toString(), PayReturnData.class);
                return retData;
            }

            return null;
            //throw new AppTechnicalException("Impossible de procéder au paiement");

        } catch (Exception e) {
            log.error("Error when validating payment", e);
            return null;
            //throw new AppTechnicalException("Erreur lors de la validation du paiement", e);
        }
    }

    /*
	 * function to initialize a specific payment process
     */
    public static PayReturnData initPayment(String appURL, String payNumber, double amount, Long remoteProviderID, Long remotePRMID, Long remoteProcessID) throws AppTechnicalException {
        try {
            PayData data = new PayData();

            Integer remoteProvider = (remoteProviderID != null && remoteProviderID > 0) ? Integer.parseInt(remoteProviderID.toString()) : -1;
            Integer remoteParam = (remotePRMID != null && remotePRMID > 0) ? Integer.parseInt(remotePRMID.toString()) : -1;
            Integer remoteProcess = (remoteProcessID != null && remoteProcessID > 0) ? Integer.parseInt(remoteProcessID.toString()) : -1;
            Integer lastCde = -1;

            data.setCodeU(remoteProvider);
            data.setCodeD(remoteParam);
            data.setCodeT(remoteProcess);
            data.setCodeZ(lastCde);

            data.setCodeOTP("");
            data.setLogin("");
            data.setPassword("");

            data.setMontant(amount);
            data.setTelephone(payNumber);

            String url = appURL + AppDataProvider.transxPath + "init/";

            JsonObject jsonObject = AppDataProvider.makePOST(data, url);

            if (jsonObject != null && jsonObject.getString("status").equalsIgnoreCase("OK")) {

                PayReturnData retMoMoData = AppJsonConverter.decodeItfromJson(jsonObject.get("payload").toString(), PayReturnData.class);

                return retMoMoData;
            }

            return null;
        } catch (Exception e) {
            log.error("Error when initing payment", e);
            return null;
            //throw new AppTechnicalException("Erreur lors de l initialisation du paiement", e);
        }
    }

    public static List<ModalitePaieDTO> findAllProviderData(String appURL, Long remoteID) throws AppTechnicalException {
        log.error("======== Entry s " + remoteID);
        try {
            List<ModalitePaieDTO> data = new ArrayList<ModalitePaieDTO>();

            String url = appURL + AppDataProvider.fnsPath + "findAllDetail/" + remoteID;

            log.error("======== url " + url);

            JsonObject jsonObject = AppDataProvider.makeGET(null, url);

            log.error("======== JSON OBJECT " + jsonObject);

            JsonArray jsonArray = jsonObject.getJsonArray("payload");

            log.error("======== JSON OBJECT PAYLOAD" + jsonArray);

            if (jsonObject != null && jsonObject.getString("status").equalsIgnoreCase("OK")) {

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jos = jsonArray.getJsonObject(i);
                    data.add(AppJsonConverter.decodeItfromJson(jos.toString(), ModalitePaieDTO.class));
                }

                return data;
            } else {
                throw new AppTechnicalException("Aucun lors du chargement des systèmes de paiement");
            }
        } catch (Exception e) {
            log.error("Error when checking all payment providers", e);
            throw new AppTechnicalException("Erreur lors du chargement des systèmes de paiement", e);
        }
    }
}
