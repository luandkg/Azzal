package Arquivos;

import java.io.File;

public class IO {

    public static void remova_se_existir(String eArquivo){

        if (new File(eArquivo).exists()) {
            new File(eArquivo).delete();
        }

    }
}
