package libs.Arquivos.Video;

public class Temporizador {

    private long mInicio;
    private long mFim;

    public Temporizador(){

        mInicio=0;
        mFim=0;

    }

    public void marqueInicio(){
        mInicio=System.currentTimeMillis();
    }

    public void marqueFim(){
        mFim=System.currentTimeMillis();
    }

    public long getInicio(){
        return mInicio;
    }
    public long getFim(){
        return mFim;
    }

    public float getIntervalo() {

        return (getFim() - getInicio()) / 1000F;

    }

}
