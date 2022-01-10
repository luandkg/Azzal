package Griff;

import Azzal.Renderizador;
import Azzal.Utils.Cor;

public class EscritorGriph {

    private Cor mTextoCor;

    private Griphattor mGriphattor;
    private Griphizz mGriphizz;
    private Renderizador mRenderizador;

    public EscritorGriph(Cor eTextoCor) {

        mTextoCor = eTextoCor;

        mGriphattor = new Griphattor();
        mGriphizz = new Griphizz();

        mRenderizador = null;

    }

    public void atualizar(Renderizador eRenderizador) {
        mRenderizador = eRenderizador;
    }

    public void escreve(int x, int y, String eTexto) {

        mGriphizz.drawString(mRenderizador, mGriphattor, eTexto, mTextoCor, x, y);


    }
}
