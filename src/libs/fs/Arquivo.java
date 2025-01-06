package libs.fs;

import libs.arquivos.binario.Arquivador;

import java.io.File;

public class Arquivo {

    private String mLocal;

    public Arquivo(String eLocal){
        mLocal=eLocal;
    }

    public String getLocal(){return mLocal;}

    public String getNome(){
        return new File(mLocal).getName();
    }

    public boolean existe(){
        return new File(mLocal).exists();
    }

    public long getTamanho(){
       return Arquivador.GET_TAMANHO(mLocal);
    }
}
