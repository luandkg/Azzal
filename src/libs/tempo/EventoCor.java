package libs.tempo;

import libs.azzal.utilitarios.Cor;

public class EventoCor {

    private String mEvento;
    private Cor mCor;

    public EventoCor(String evento, Cor eCor) {
        mEvento = evento;
        mCor = eCor;
    }

    public String getEvento() {
        return mEvento;
    }

    public Cor getCor() {
        return mCor;
    }
}