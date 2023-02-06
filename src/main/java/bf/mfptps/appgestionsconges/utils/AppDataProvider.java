package bf.mfptps.appgestionsconges.utils;

import bf.mfptps.appgestionsconges.entities.CommonEntity;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDataProvider {

    private static final Logger log = LoggerFactory.getLogger(AppDataProvider.class.getName());

    //protected static final String SERVICE_BASE_URI = "http://localhost:8087/payapi/";
    public static final String transxPath = "transx/";
    public static final String fnsPath = "fnsws/fournisseur/"; //findAllDetail

    public static JsonObject makePOST(Object vo, String path) {
        String ipApiQuery = path; //SERVICE_BASE_URI + path;
        HttpURLConnection connection = null;//using url connection to avoid (JavaEE 6) JAX-RS client api conflicts
        BufferedReader rd = null;
        try {
            URL url = new URL(ipApiQuery);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Accept", "application/json");

            if (vo != null) {
                connection.setDoOutput(true);

                try ( OutputStream os = connection.getOutputStream()) {
                    String jsonInputString = new com.google.gson.Gson().toJson(vo);
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            InputStream is = connection.getInputStream();
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                json.append(line);
            }

            InputStream jsonStream = new ByteArrayInputStream(json.toString().getBytes("UTF-8"));
            JsonObject jsonObject = Json.createReader(jsonStream).readObject();

            return jsonObject;

        } catch (Exception e) {
            log.error("Could not get additional info from IP API request:" + ipApiQuery, e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    log.error("Problem closing buffered reader", e);
                }
            }
        }
    }

    public static JsonObject makeGET(CommonEntity vo, String path) {
        String ipApiQuery = path; //SERVICE_BASE_URI + path;
        HttpURLConnection connection = null;//using url connection to avoid (JavaEE 6) JAX-RS client api conflicts
        BufferedReader rd = null;
        try {
            URL url = new URL(ipApiQuery);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Accept-Charset", "UTF-8");

            if (vo != null) {
                connection.setDoOutput(true);

                try ( OutputStream os = connection.getOutputStream()) {
                    String jsonInputString = new com.google.gson.Gson().toJson(vo);
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            InputStream is = connection.getInputStream();
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                json.append(line);
            }

            InputStream jsonStream = new ByteArrayInputStream(json.toString().getBytes("UTF-8"));
            JsonObject jsonObject = Json.createReader(jsonStream).readObject();

            return jsonObject;

        } catch (Exception e) {
            log.error("Could not get additional info from IP API request:" + ipApiQuery, e);
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException e) {
                    log.error("Problem closing buffered reader", e);
                }
            }
        }
    }
}
