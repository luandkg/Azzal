package apps.AppAttuz.Politicamente;

import apps.AppAttuz.Mapa.Local;
import azzal.Cores;
import azzal.Renderizador;
import apps.appLetrum.Fonte;

import java.util.ArrayList;

public class ListaDeCidades {

    private Fonte pequeno;
    private Cores mCores;

    private ArrayList<String> mVisitadas;

    public ListaDeCidades(Fonte eLetramento,Cores eCores) {
        pequeno = eLetramento;
        mCores=eCores;
        mVisitadas = new ArrayList<String>();
    }

    public void visitar(String eCidade) {
        if (!mVisitadas.contains(eCidade)) {
            mVisitadas.add(eCidade);
        }
    }

    public void onDraw(Renderizador g, ArrayList<Local> mLocais) {

        int ex = 60;
        int ey = 60;

        int l = 0;

        for (Local ePonto : mLocais) {

            if (mVisitadas.contains(ePonto.getX() + "::" + ePonto.getY() )){
                g.drawRect_Pintado(ex - 30, ey+5 , 10, 10, mCores.getLaranja());
            }else{
                g.drawRect_Pintado(ex - 30, ey +5, 10, 10, mCores.getPreto());
            }


         //   pequeno.EscreveNegrito(g, ePonto.getX() + "::" + ePonto.getY() + " - " + ePonto.getNome(), ex, ey);
            pequeno.escreva( ex, ey, l + " - " + ePonto.getNome());

            ey += 22;
            if (ey > 980) {
                ey = 60;
                ex = 1900;
            }

            l+=1;


        }

    }


}
