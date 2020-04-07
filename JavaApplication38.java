
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author toshiba
 */
public class JavaApplication38 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<String> scanid=new ArrayList<>();
        String projectID=args[1];
        if (args[0].equals("web-scan")) {
            File file = new File("inputWebAppUrl.txt");
            Scanner scan = new Scanner(file);
            ArcherySec ar = new ArcherySec();
            ProjectArcherySec pro = new ProjectArcherySec(projectID, "", "", "", "", "");
            String token = ar.loginToken();
            while (scan.hasNext()) {
                String input = scan.nextLine();
                scanid.add(pro.WebScan(input, "zap_scan",token));       
            }
            sleep(600000);
            token = ar.loginToken();
            String result="";
            for (int i = 0; i < scanid.size(); i++) {
                result+=pro.ScanWebResult(token, scanid.get(i));
                }
            System.out.println(result);
        } else if (args[0].equals("infra-scan")) {
            File file = new File("inputInfra-IP.txt");
            Scanner scan = new Scanner(file);
            ArcherySec ar = new ArcherySec();
            ProjectArcherySec pro = new ProjectArcherySec(projectID, "", "", "", "", "");
            String token = ar.loginToken();
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String input = scan.nextLine();
                InfrastructureScan(input,token);
                System.out.println(input);
            }
        }
//       ArcherySec ar=new ArcherySec();
//       ProjectArcherySec pro=new ProjectArcherySec("2db1b00b-fd27-4e65-981a-c5b1ae35be89", "", "", "", "", "");
//       String token=ar.loginToken();
//       String scanid = pro.WebScan("http://info-bitcode.com", "zap_scan",token );
//       pro.ScanWebResult(token, "35c4d85f-2eb1-4520-bd51-b65be8eb9421");

    }

    public static void getAllProject(String token) throws IOException {
        URL obj = new URL(ArcherySec.URLBASE + "api/project/?format=json");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "JWT " + token);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer res = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            res.append(inputLine);
        }
        String htmlform = res.toString();
        System.out.println(htmlform);

        in.close();
    }

}
