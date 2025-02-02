package libs.bibliotecas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PDF {

    public static void pngToPDF(String eFonte, String eDestino) {

        String eComando = "img2pdf " + " '" + eFonte + "' " + " -o " + " '" + eDestino + " '";

        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("/bin/bash");
        commands.add("-c");
        commands.add(eComando);

        BufferedReader br = null;
        String saida = "";

        try {
            final ProcessBuilder p = new ProcessBuilder(commands);
            final Process process;
            process = p.start();
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                saida += "Retorno do comando = [" + line + "]\n";
            }
        } catch (IOException ioe) {

        } finally {

        }

    }


}
