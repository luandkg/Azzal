package libs.Extenum.Paginador;


import libs.Extenum.Arquivador.Arquivador;
import libs.Extenum.Arquivador.Bloco;

public class Pagina {

    private Bloco mBloco;
    private long mPaginaID;

    public Pagina(Arquivador eArquivador, long ePaginaID, Bloco eBloco) {
        mPaginaID = ePaginaID;
        mBloco = new Bloco(eBloco.getID(), eBloco.getFile(),eArquivador, eBloco.getUtils(), eBloco.getInicio(), eBloco.getFim());
    }

    public long getBlocoID() {
        return mBloco.getID();
    }

    public long getPaginaID() {
        return mPaginaID;
    }

    public long getInicio() {
        return mBloco.getInicio();
    }

    public long getFim() {
        return mBloco.getFim();
    }

    public long getTamanho() {
        return mBloco.getTamanho();
    }

    public void marcarAntes(boolean e) {

        mBloco.writeBoolean(0, e);

    }

    public void escreverAntes(long a) {

        mBloco.writeLong(2, a);

    }

    public void marcarDepois(boolean e) {

        mBloco.writeBoolean(1, e);

    }

    public void escreverDepois(long d) {

        mBloco.writeLong(10, d);

    }

    public boolean temAntes() {
        return mBloco.readBoolean(0);
    }

    public boolean temDepois() {
        return mBloco.readBoolean(1);
    }

    public long getAntes() {
        return mBloco.readLong(2);
    }

    public long getDepois() {
        return mBloco.readLong(10);
    }

    public void guardar(int p, long bloco, boolean status) {

        long mLocal1 = 30 + ((p) * (10));
        long mLocal2 = mLocal1 + 8;

       //   System.out.println("L1 : " + mLocal1);
       //   System.out.println("L2 : " + mLocal2);

       // System.out.println("Tamanho do Bloco : " + getTamanho());

        mBloco.writeLong(mLocal1, bloco);
        mBloco.writeBoolean(mLocal2, status);

    }

    public RefBloco getGuardado(int p) {

        long mLocal1 = mBloco.getInicio() + 30 + ((p) * (10));
        long mLocal2 = mLocal1 + 8;

        //  System.out.println("R1 : " + mLocal1);
        // System.out.println("R2 : " + mLocal2);

        return new RefBloco(mBloco.getArquivador(), mBloco.getUtils(), mLocal1, mLocal2);
    }




}
