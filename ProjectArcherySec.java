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
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author toshiba
 */
public class ProjectArcherySec {
    private String Project_ID;
    private String Project_Name;
    private String Project_Disk;
    private String Project_Start;
    private String Project_End;
    private String Project_Owner;

    public String getProject_ID() {
        return Project_ID;
    }

    public void setProject_ID(String Project_ID) {
        this.Project_ID = Project_ID;
    }

    public String getProject_Name() {
        return Project_Name;
    }

    public void setProject_Name(String Project_Name) {
        this.Project_Name = Project_Name;
    }

    public String getProject_Disk() {
        return Project_Disk;
    }

    public void setProject_Disk(String Project_Disk) {
        this.Project_Disk = Project_Disk;
    }

    public String getProject_Start() {
        return Project_Start;
    }

    public void setProject_Start(String Project_Start) {
        this.Project_Start = Project_Start;
    }

    public String getProject_End() {
        return Project_End;
    }

    public void setProject_End(String Project_End) {
        this.Project_End = Project_End;
    }

    public String getProject_Owner() {
        return Project_Owner;
    }

    public void setProject_Owner(String Project_Owner) {
        this.Project_Owner = Project_Owner;
    }

    public ProjectArcherySec(String Project_ID, String Project_Name, String Project_Disk, String Project_Start, String Project_End, String Project_Owner) {
        this.Project_ID = Project_ID;
        this.Project_Name = Project_Name;
        this.Project_Disk = Project_Disk;
        this.Project_Start = Project_Start;
        this.Project_End = Project_End;
        this.Project_Owner = Project_Owner;
    }
    
    public String WebScan(String scanurl,String scanner,String token) throws MalformedURLException, IOException{
        URL obj = new URL(ArcherySec.URLBASE+"api/webscan/");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization","JWT "+token);
        String urlParameters = "{\n"
                + "    \"scan_url\": \""+scanurl+"\",\n"
                + "    \"project_id\": \""+Project_ID+"\",\n"
                + "    \"scanner\": \""+scanner+"\"\n"
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
       

        in.close();
        return htmlform.split("&quot;")[7];

    
    }
    
    public String ScanWebResult(String token,String scanid) throws MalformedURLException, IOException{
        URL obj = new URL(ArcherySec.URLBASE+"api/webscanresult/?format=json");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization","JWT "+token);
        String urlParameters = "{\n"
                + "    \"scan_id\": \""+scanid+"\"\n"
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
        System.out.println(htmlform);

        in.close();
        return null;
    }
    
    
     public String InfrastructureScan(String scanip,String scanner,String token) throws MalformedURLException, IOException{
        URL obj = new URL(ArcherySec.URLBASE+"api/networkscan/");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization","JWT "+token);
        String urlParameters = "{\n"
                + "    \"scan_url\": \""+scanip+"\",\n"
                + "    \"project_id\": \""+Project_ID+"\"\n"
                
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
        

        in.close();
        return htmlform.split("&quot;")[7];
    
    }
     
    public String InfrastructureScan_Result(String token) throws MalformedURLException, IOException{
        URL obj = new URL(ArcherySec.URLBASE+"api/networkscan/?format=json");
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
        return htmlform;
    }
     
}
