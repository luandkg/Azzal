package Fisica;


import Azzal.Renderizador;
import Azzal.Utils.Cor;
import LetrumArkaica.Letramento;

public class Trena {

    private int mIntervalo;
    private int mOrigem;
    private Letramento mLetramentoPreto;

    public Trena(int eOrigem, int eIntervalo,Letramento eLetramento) {
        mOrigem = eOrigem;
        mIntervalo = eIntervalo;
        mLetramentoPreto=eLetramento;
    }

    public void render(Renderizador mRenderizador, int eInicio, int eFim, int eY) {


        int cx = eInicio;
        int eAtual = mOrigem;

        while (cx < eFim) {

            mRenderizador.drawLinhaVertical(cx,eY-20, 20,new Cor(0,0,0));

            mLetramentoPreto.escreve( cx+5, eY,eAtual + " ");
            cx += mIntervalo;
            eAtual += mIntervalo;
        }


    }

}
