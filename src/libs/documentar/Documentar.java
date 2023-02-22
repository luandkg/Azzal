package libs.documentar;

import libs.luan.ArquivoTexto;


import java.io.*;
import java.util.ArrayList;

public class Documentar {

    public static void organizar(String arquivo_txt, String arquivo_pdf) {

        String arquivo_png = arquivo_pdf.replace(".pdf", ".png");

        TextoToPartes eTextoToPartes = new TextoToPartes();
        ToFolha eToFolha = new ToFolha();


        if (new File(arquivo_txt).exists()) {

            ArrayList<ParteTextual> objetos = eTextoToPartes.parser(ArquivoTexto.arquivo_ler(arquivo_txt));

            //mostrarObjetos(objetos);

            eToFolha.render(1100, 1500, objetos, arquivo_png);

            pngToPDF(arquivo_png, arquivo_pdf);

            removerArquivo(arquivo_png);

        }


    }



    private static void pngToPDF(String eFonte, String eDestino) {

        String eComando = "img2pdf " + " '" + eFonte + "' " + " -o " +   "'" + eDestino + "'";

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

    private static void mostrarObjetos(ArrayList<ParteTextual> objetos) {

        System.out.println("---------------------------------------------");
        for (ParteTextual objeto : objetos) {

            System.out.println(" -->> " + objeto.getTipo() + " :: " + objeto.getConteudo());

            if (objeto.getAtributos().size() > 0) {
                for (String at : objeto.getAtributos()) {
                    System.out.println("\t :: " + at);
                }
            }

        }
        System.out.println("---------------------------------------------");

    }

    private static void removerArquivo(String eArquivo){
        if (new File(eArquivo).exists()) {
            File remover = new File(eArquivo);
            remover.delete();
        }
    }
}
