package apps.app_attuz.Widgets;

import apps.app_attuz.Ferramentas.Local;
import apps.app_letrum.Fonte;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.luan.Lista;

import java.util.ArrayList;

public class ListaDeCidades {

    private Fonte pequeno;
    private Cores mCores;

    private ArrayList<String> mVisitadas;

    public ListaDeCidades(Fonte eLetramento, Cores eCores) {
        pequeno = eLetramento;
        mCores = eCores;
        mVisitadas = new ArrayList<String>();
    }

    public void visitar(String eCidade) {
        if (!mVisitadas.contains(eCidade)) {
            mVisitadas.add(eCidade);
        }
    }

    public void onDraw(Renderizador g, Lista<Local> mLocais) {

        int ex = 40;
        int ey = 40;

        int l = 0;

        for (Local ePonto : mLocais) {

            if (mVisitadas.contains(ePonto.getX() + "::" + ePonto.getY())) {
                g.drawRect_Pintado(ex - 30, ey + 5, 10, 10, mCores.getLaranja());
            } else {
                g.drawRect_Pintado(ex - 30, ey + 5, 10, 10, mCores.getPreto());
            }

            pequeno.escreva(ex, ey, l + " - " + ePonto.getNome());

            ey += 22;
            if (ey > 980) {
                ey = 40;
                ex = 2500;
            }

            l += 1;


        }

    }


}
