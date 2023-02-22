package apps.app_gamafs;

import libs.gama_fs.GamaFS;

public class AppGamaFS {

    public static void init() {

        boolean isGama = false;
        if (isGama) {
            String eArquivo = "/home/luan/Documentos/fs/gama.fs";

            // libs.GamaFS.criar(eArquivo, 10 * 1024 * 1024);
            // libs.GamaFS.zerar(eArquivo);
            GamaFS.formatar(eArquivo);

            GamaFS eGama = new GamaFS(eArquivo);

            eGama.encerrar();
        }



    }
}
