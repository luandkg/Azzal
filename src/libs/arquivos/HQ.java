package libs.arquivos;

import libs.luan.Lista;
import libs.luan.Ordenador;
import libs.luan.Strings;

import java.io.File;
import java.util.ArrayList;

public class HQ {

    public static void criarHQ(String eArquivo, String eImagens) {

        File dir = new File(eImagens);

        Lista<String> arquivos = new Lista<String>();

        for (File eArquivoImagem : dir.listFiles()) {
            if (eArquivoImagem.getPath().endsWith(".im") || eArquivoImagem.getPath().endsWith(".png") || eArquivoImagem.getPath().endsWith(".jpg") || eArquivoImagem.getPath().endsWith(".jpeg") || eArquivoImagem.getPath().endsWith(".bmp")) {
                arquivos.adicionar(eArquivoImagem.getPath());
            }
        }


        Ordenador.ORDENAR_CRESCENTE_LISTA(arquivos, Ordenador.ORDENAR_STRING_NAO_SENSITIVA());

        AI.criar(arquivos, eArquivo);

    }

}
