package DKG;

public abstract class CadaObjeto {

    private boolean mDeveContinuar = true;

    public abstract void dentro(DKGObjeto objeto);

    public void cancelar(){
        mDeveContinuar=false;
    }

    public boolean deveContinuar(){return mDeveContinuar;}

    public boolean foiCancelado(){return !mDeveContinuar;}
}
