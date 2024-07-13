package apps.app_atzum.utils;

import libs.azzal.utilitarios.Cor;
import libs.luan.Aleatorio;

public class FaixaDeTemperatura {

    private int mMinimo;
    private int mMaximo;
    private Cor mCor;

    public FaixaDeTemperatura(int eMinimo, int eMaximo, Cor eCor){
        mMinimo=eMinimo;
        mMaximo=eMaximo;
        mCor=eCor;
    }

    public int getMinimo(){return mMinimo;}
    public int getMaximo(){return mMaximo;}

    public Cor getCor(){return mCor;}

    public int getTemperaturaAleatoria(){return Aleatorio.alatorio_entre(mMinimo,mMaximo);}
}
