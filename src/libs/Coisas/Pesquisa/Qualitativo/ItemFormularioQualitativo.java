package libs.Coisas.Pesquisa.Qualitativo;

public class ItemFormularioQualitativo {

    private boolean mRespondido;
    private String mResposta;

    public ItemFormularioQualitativo() {
        mRespondido = false;
        mResposta = "";
    }


    public boolean foiRespondido() {
        return mRespondido ;
    }

    public void responder(String eResposta) {
        mResposta = eResposta;
        mRespondido = true;
    }

    public String getResposta(){return mResposta;}
}
