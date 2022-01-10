package Extenum.Paginador;


import Extenum.Arquivador.Arquivador;
import Extenum.Arquivador.Bloco;
import Extenum.Arquivador.Utils;

public class RefBloco {

    private Arquivador mArquivador;
    private Utils mUtils;

    private long mBlocoValue;
    private long mStatusValue;

    public RefBloco(Arquivador eArquivador, Utils eUtils, long eBlocoValue, long eStatusValue){

        mArquivador=eArquivador;
        mUtils=eUtils;

        mBlocoValue=eBlocoValue;
        mStatusValue=eStatusValue;

    }

    public Bloco getBloco(){
        return mArquivador.getBloco(getBlocoID());
    }

    public long getBlocoID(){
        mUtils.setPonteiro(mBlocoValue);
        return mUtils.readLong();
    }

    public boolean getStatus(){
        mUtils.setPonteiro(mStatusValue);
        return mUtils.readBoolean();
    }


    public void travar(){
        mUtils.setPonteiro(mStatusValue);
        mUtils.writeBoolean(false);
    }
    public void destravar(){
        mUtils.setPonteiro(mStatusValue);
        mUtils.writeBoolean(true);
    }


}
