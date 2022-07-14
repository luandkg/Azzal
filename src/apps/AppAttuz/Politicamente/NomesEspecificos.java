package apps.AppAttuz.Politicamente;


import apps.AppAttuz.Mapa.Local;
import azzal.Cores;
import azzal.Renderizador;
import apps.appLetrum.Fonte;

public class NomesEspecificos {

    private Cores mCores;

    public NomesEspecificos(Cores eCores){
        mCores=eCores;
    }

    public void nomear(Renderizador g, Fonte micro, int l, Local ePonto, int X0, int Y0) {
        nomearDireto(g,micro,l,ePonto.getNome(),ePonto.getX(),ePonto.getY(),X0,Y0 );
    }

    public void nomearDireto(Renderizador g, Fonte micro, int l, String sNome, int rx, int ry, int X0, int Y0) {

        String eNome = sNome;

        int px =rx + X0;
        int py = ry + Y0 + 15;

        micro.setRenderizador(g);

        if (eNome.contentEquals("Castelo das Águas")) {

            micro.escreva(px - 20,py - 60,l + " - " + eNome );
            g.drawLinha(px, py - 15, px - 20, py - 60, mCores.getPreto());

        } else if (eNome.contentEquals("Muralha de Gelo")) {

            micro.escreva(px - 20, py - 40,l + " - " + eNome );
            g.drawLinha(px, py - 15, px - 20, py - 40, mCores.getPreto());

        } else if (eNome.contentEquals("Ilha de Vocz")) {

            micro.escreva(px +40, py-20 ,l + " - " + eNome);
            g.drawLinha(px, py - 15, px +35, py -20, mCores.getPreto());

        } else if (eNome.contentEquals("Grande Mercatto")) {

            micro.escreva( px - 20, py + 40,l + " - " + eNome);
            g.drawLinha(px, py - 15, px - 20, py + 40, mCores.getPreto());

        } else if (eNome.contentEquals("Ruela de Penhores")) {

            micro.escreva(  px - 20+10, py + 40,l + " - " + eNome);
            g.drawLinha(px, py - 15, px - 20 + 10, py + 40, mCores.getPreto());

        } else if (eNome.contentEquals("Labirinto do Touro")) {

            micro.escreva( px +40, py-10 ,l + " - " + eNome);
            g.drawLinha(px, py - 15, px +35, py -10, mCores.getPreto());

        } else if (eNome.contentEquals("Metrópolis de Cal")) {

            micro.escreva(  px - 20, py + 40,l + " - " + eNome);
            g.drawLinha(px, py - 15, px - 20, py + 40, mCores.getPreto());

        } else if (eNome.contentEquals("Castelo do Duque Valgrá")) {

            micro.escreva( px - 20, py - 40,l + " - " + eNome);
            g.drawLinha(px, py - 15, px - 20, py - 40, mCores.getPreto());

        } else if (eNome.contentEquals("Deserto de Halcabraz")) {

            micro.escreva( px - 20, py - 20,l + " - " + eNome);
            g.drawLinha(px, py - 15, px - 20, py - 20, mCores.getPreto());

        } else {
            micro.escreva( rx+ X0 - 20, ry + Y0 -5,l + " - " + eNome);
        }


    }

}
