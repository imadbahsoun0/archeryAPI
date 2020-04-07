/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author toshiba
 */
public class ArcherySec {
    private final String Username = "admin"; //username of archery sec
    private final String Password = "admin"; //password of archery sec
    public static String URLBASE = "http://10.4.21.22:8000/";
   
    
    
    public String loginToken() throws IOException {
        URL obj = new URL(ArcherySec.URLBASE+"api-token-auth/");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        String urlParameters = "{\n"
                + "    \"username\": \""+Username+"\",\n"
                + "    \"password\": \""+Password+"\"\n"
                + "}";

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer res = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            res.append(inputLine);
        }
        String htmlform = res.toString();
        String token = htmlform.split("&quot;")[3];

        in.close();
        return token;
    }
    
        public  ArrayList<ProjectArcherySec> getAllProject(String token) throws IOException {
        URL obj = new URL(ArcherySec.URLBASE+"api/project/?format=json");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization","JWT "+token);


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer res = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            res.append(inputLine);
        }
        String htmlform = res.toString();
        System.out.println(htmlform);

        in.close();
        return null;
    }

}
