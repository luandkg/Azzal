package libs.Coisas.Pesquisa.Quantitativo;

public class ItemFormularioQuantitativo {

    private boolean mRespondido;
    private int mResposta;

    public ItemFormularioQuantitativo() {
        mRespondido = false;
        mResposta =0;
    }


    public boolean foiRespondido() {
        return mRespondido ;
    }

    public void responder(int eResposta) {
        mResposta = eResposta;
        mRespondido = true;
    }

    public int getResposta(){return mResposta;}

}
