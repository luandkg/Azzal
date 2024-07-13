package apps.app_atzum;

import apps.app_atzum.escalas.Vegetacao;
import libs.luan.Lista;

public class AtzumVegetacoes {

    private Atzum mAtzum;

    private Vegetacao mSavana;
    private Vegetacao mSazonal;
    private Vegetacao mTaiga;
    private Vegetacao mDeserto;
    private Vegetacao mEstepe;
    private Vegetacao mMata;
    private Vegetacao mTundra;
    private Vegetacao mFloresta;

    public AtzumVegetacoes(){

        mAtzum = new Atzum();

        mSavana = getVegetacao("Savana");
        mSazonal = getVegetacao("Sazonal");
        mTaiga = getVegetacao("Taiga");
        mDeserto = getVegetacao("Deserto");
        mEstepe = getVegetacao("Estepe");
        mMata = getVegetacao("Mata");
        mTundra = getVegetacao("Tundra");
        mFloresta = getVegetacao("Floresta");

    }


    public Vegetacao SAVANA(){return mSavana;}
    public Vegetacao SAZONAL(){return mSazonal;}
    public Vegetacao TAIGA(){return mTaiga;}
    public Vegetacao DESERTO(){return mDeserto;}
    public Vegetacao ESTEPE(){return mEstepe;}
    public Vegetacao MATA(){return mMata;}
    public Vegetacao TUNDRA(){return mTundra;}
    public Vegetacao FLORESTA(){return mFloresta;}

    public Lista<Vegetacao> listar(){return mAtzum.GET_VEGETACOES();}

    public Vegetacao getVegetacao(String eNome) {
        return mAtzum.GET_VEGETACAO(eNome);
    }



}
