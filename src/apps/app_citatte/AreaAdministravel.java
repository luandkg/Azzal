package apps.app_citatte;

import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;

public class AreaAdministravel {


    private Retangulo mArea;
    private String mNome;

    public AreaAdministravel(Retangulo eArea, String eNome) {
        mArea = eArea;
        mNome =eNome;
    }


    public boolean isDentro(int px, int py) {
        return mArea.isDentro(px, py);
    }

    public String getNome(){return mNome;}

    public void setNome(String eNome){mNome=eNome;}


    public Ponto getLocalizacao(){return new Ponto(mArea.getX(),mArea.getY());}
    public Ponto getCentroLocalizacao(){return new Ponto(mArea.getX()+mArea.getLargura()/2,mArea.getY()+mArea.getAltura()/2);}

    public Retangulo getArea(){return mArea;}
}
