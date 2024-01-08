package apps.app;

import libs.luan.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class AppFit {

    public static void init() {

        System.out.println("FIT --- CONECTANDO");

        String APP_TOKEN = "UQVBQEJyQktGXip6SltGImp2ej48BAAEAAAAA__PeXBuebZX-xTpt61q_llrfrtKLaFgQPLTUu7_zL5eFSK55DMCH5GZBFQlaPsScx_GHlOwGOcWvCIlUQCBrOuLdAroShEYrD4PRqXamOvBqz840rCvJI08dvVJ3lM0giPbxTN0Fpezv1CL-IMdLVq-kfwLnt2DAUtq0Rip05YCNM-_nckyfS0trDgSNfhfN";
        String SOURCE = "run.mifit.huami.com";


        String url = "https://api-mifit.huami.com/v1/sport/run/history.json?apptoken=" + APP_TOKEN + "&source=" + SOURCE;
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            System.out.println("ERRO = " + e.getMessage());
        }

        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            System.out.println("ERRO = " + e.getMessage());
        }
        //  conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        try {
            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            }
        } catch (IOException e) {
            System.out.println("ERRO = " + e.getMessage());
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        } catch (IOException e) {
            System.out.println("ERRO = " + e.getMessage());
        }

        String output = "";
        String line;
        if (br != null) {

            while (true) {
                try {
                    if (!((line = br.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                output += line;
            }

        }


        System.out.println("DADOS = ");

        for (String parte : Strings.dividir_em_partes(output, 100)) {
            System.out.println(parte);
        }

        conn.disconnect();

        System.out.println("FIT --- TUDO OK");

    }
}
