package apps.app_atzum.escalas;

import libs.azzal.utilitarios.Cor;

public class Vegetacao {

    private String mNome;
    private Cor mCor;

    public Vegetacao(String eNome, Cor eCor){
        mNome=eNome;
        mCor=eCor;
    }

    public Vegetacao(String eNome, String eCor){
        mNome=eNome;
        mCor=Cor.getHexCor(eCor);
    }

    public String getNome(){return mNome;}
    public Cor getCor(){return mCor;}

}
