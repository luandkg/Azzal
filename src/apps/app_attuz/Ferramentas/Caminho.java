package apps.app_attuz.Ferramentas;


import libs.azzal.geometria.Ponto;

public class Caminho {

    private Ponto mInicio;
    private Ponto mFim;

    public Caminho(Ponto eInicio,Ponto eFim){
        mInicio=eInicio;
        mFim=eFim;
    }

    public Ponto getInicio(){return mInicio;}
    public Ponto getFim(){return mFim;}

}
