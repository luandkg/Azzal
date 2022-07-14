package apps.appFisica;


import azzal.Renderizador;
import azzal.Utils.Cor;
import apps.appLetrum.Fonte;

public class Trena {

    private int mIntervalo;
    private int mOrigem;
    private Fonte mLetramentoPreto;

    public Trena(int eOrigem, int eIntervalo, Fonte eLetramento) {
        mOrigem = eOrigem;
        mIntervalo = eIntervalo;
        mLetramentoPreto=eLetramento;
    }

    public void render(Renderizador mRenderizador, int eInicio, int eFim, int eY) {


        int cx = eInicio;
        int eAtual = mOrigem;

        while (cx < eFim) {

            mRenderizador.drawLinhaVertical(cx,eY-20, 20,new Cor(0,0,0));

            mLetramentoPreto.escreva( cx+5, eY,eAtual + " ");
            cx += mIntervalo;
            eAtual += mIntervalo;
        }


    }

}
