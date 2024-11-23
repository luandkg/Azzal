package apps.app_atzum.utils;

import libs.azzal.utilitarios.Cor;
import libs.luan.Aleatorio;
import libs.luan.Lista;

public class IntervaloDeValorColorido {

    private int mMinimo;
    private int mMaximo;
    private Cor mCor;

    public IntervaloDeValorColorido(int eMinimo, int eMaximo, Cor eCor){
        mMinimo=eMinimo;
        mMaximo=eMaximo;
        mCor=eCor;
    }

    public int getMinimo(){return mMinimo;}
    public int getMaximo(){return mMaximo;}

    public Cor getCor(){return mCor;}

    public int getTemperaturaAleatoria(){return Aleatorio.aleatorio_entre(mMinimo,mMaximo);}



    public static Cor GET_COR(Lista<IntervaloDeValorColorido> FAIXAS_DE_TEMPERATURA,int valor,Cor cor_padrao){

        Cor cor =cor_padrao;

        for (IntervaloDeValorColorido faixa : FAIXAS_DE_TEMPERATURA) {
            if (valor >= faixa.getMinimo() && valor < faixa.getMaximo()) {
                //   fmt.print("Temp : {} ->> {} :: {}",temperatura,faixa.getMinimo(),faixa.getMaximo());
                cor = faixa.getCor();
                break;
            }
        }

        if (valor <= FAIXAS_DE_TEMPERATURA.getPrimeiroValor().getMinimo()) {
            cor = FAIXAS_DE_TEMPERATURA.getPrimeiroValor().getCor();
        }

        if (valor >= FAIXAS_DE_TEMPERATURA.getUltimoValor().getMaximo()) {
            cor = FAIXAS_DE_TEMPERATURA.getUltimoValor().getCor();
        }

        return cor;
    }
}
