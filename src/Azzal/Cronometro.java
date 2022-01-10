package Azzal;

public class Cronometro {

    private long mIniciado;
    private long mAtualizado;
    private long mFinalizar;

    private long mEsperar;

    public Cronometro(long eMilissegundos) {
        mIniciado = System.currentTimeMillis();
        mAtualizado = mIniciado;
        mFinalizar = mIniciado + eMilissegundos;
        mEsperar = eMilissegundos;
    }

    public void atualizar() {
        mAtualizado = System.currentTimeMillis();
    }

    public boolean foiEsperado(){return isFinalizado(); }

    public long get(){
        return mAtualizado;
    }
    public boolean esperado() {
        boolean ret = isFinalizado();
        if(ret){
            ReIniciar();
        }
        return ret;
    }

    public boolean isFinalizado() {
        return mAtualizado >= mFinalizar;
    }

    public long getFim(){return mFinalizar;}

    public void ReIniciar() {
        mIniciado = System.currentTimeMillis();
        mAtualizado = mIniciado;
        mFinalizar = mIniciado + mEsperar;
    }

    public void zerar() {
        mIniciado = System.currentTimeMillis();
        mAtualizado = mIniciado;
        mFinalizar = mIniciado + mEsperar;
    }
}
