package apps.app_atzum;

import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

public class AtzumTerra {

    private Renderizador MAPA_PLANETA;
    private Cor COR_TERRA ;

    public AtzumTerra(){
        MAPA_PLANETA = new Renderizador(AtzumCreator.GET_MAPA_TERRA());
        COR_TERRA = new Cores().getAmarelo();
    }

    public int getLargura(){return MAPA_PLANETA.getLargura();}
    public int getAltura(){return MAPA_PLANETA.getAltura();}

    public boolean isTerra(int x,int y){
        return MAPA_PLANETA.getPixel(x,y).igual(COR_TERRA);
    }
    public boolean isOceano(int x,int y){
        return MAPA_PLANETA.getPixel(x,y).isDiferente(COR_TERRA);
    }
}
