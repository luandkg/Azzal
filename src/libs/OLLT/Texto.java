package libs.OLLT;

import java.io.*;
import java.util.ArrayList;

public class Texto {

    public static void Escrever(String eArquivo, String eConteudo) {

        BufferedWriter writer = null;
        try {
            File logFile = new File(eArquivo);

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(eConteudo);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }

    public static String Ler(String eArquivo) {

        String ret = "";

        try {
            FileReader arq = new FileReader(eArquivo);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            ret += linha;

            while (linha != null) {

                linha = lerArq.readLine();
                if (linha != null) {
                    ret += "\n" + linha;
                }

            }

            arq.close();
        } catch (IOException e) {

        }

        return ret;
    }

    public static String Anexar(String eConteudo, String eLinha) {

        if (eConteudo.contentEquals("")) {
            return eLinha;

        } else {
            return eConteudo + "\n" + eLinha;

        }
    }

    public static void transformar_em_linhas(String eConteudo, ArrayList<String> linhas) {
        linhas.clear();

        int i = 0;
        int o = eConteudo.length();
        String linha = "";

        while (i < o) {
            String letra = String.valueOf(eConteudo.charAt(i));
            if (letra.contentEquals("\n")){
                linhas.add(linha);
                linha="";
            }else{
                linha+=letra;
            }
            i += 1;
        }

        if (linha.length()>0){
            linhas.add(linha);
        }

    }
}
