package servicos;

import libs.fs.PastaFS;

public class ASSETS {

    public static PastaFS GET_PASTA(String eNome){
        return new PastaFS("/home/luan/assets").getPastaFS(eNome);
    }
}
