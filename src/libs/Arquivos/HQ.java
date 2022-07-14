package libs.Arquivos;

import libs.Luan.Strings;

import java.io.File;
import java.util.ArrayList;

public class HQ {

    public static void criarHQ(String eArquivo, String eImagens) {

        File dir = new File(eImagens);

        ArrayList<String> arquivos = new ArrayList<String>();

        for (File eArquivoImagem : dir.listFiles()) {
            if (eArquivoImagem.getPath().endsWith(".im") || eArquivoImagem.getPath().endsWith(".png") || eArquivoImagem.getPath().endsWith(".jpg") || eArquivoImagem.getPath().endsWith(".jpeg") || eArquivoImagem.getPath().endsWith(".bmp")) {
                arquivos.add(eArquivoImagem.getPath());
            }
        }

        Strings.ordenar(arquivos);

        AI.criar(arquivos, eArquivo);

    }

}
