package libs.arquivos.video;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.fmt;

public class Arena {

    private Arquivador mArquivo;
    private long mLocal;

    public Arena(Arquivador eArquivo, long eLocal) {

        mArquivo = eArquivo;
        mLocal = eLocal;

        fmt.print("PTR Arena :: {}",eLocal);

    }

    public long getPonteiro() {
        return mLocal;
    }

    public long getAnterior() {
        mArquivo.setPonteiro(mLocal + 1);
        return mArquivo.get_u64();
    }

    public long getProximo() {
        mArquivo.setPonteiro(mLocal + 9);
        return mArquivo.get_u64();
    }

    public Lista<Quadro> getQuadros() {

        Lista<Quadro> mQuadros = new Lista<Quadro>();

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18L);

        for (int i = 0; i < 100; i++) {

            fmt.print("Q :: {}",i);

            long eAntes = mArquivo.getPonteiro();
            mQuadros.adicionar(new Quadro(mArquivo, i, eAntes));
            mArquivo.setPonteiro(eAntes);

            long eFramePonteiro = mArquivo.get_u64();
            eContador += 1;

        }

        fmt.print("Quadros :: {}",mQuadros.getQuantidade());

        return mQuadros;

    }

    public int getFramesContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.get_u64();
            eContador += 1;


        }

        return eContador;


    }

    public int getFramesUsadosContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.get_u64();
            if (eFramePonteiro != 0) {
                eContador += 1;
            }

        }

        return eContador;


    }

    public int getFramesLivreContagem() {

        int eContador = 0;

        mArquivo.setPonteiro(mLocal + 18);

        for (int i = 0; i < 100; i++) {

            long eFramePonteiro = mArquivo.get_u64();
            if (eFramePonteiro == 0) {
                eContador += 1;
            }

        }

        return eContador;


    }

    public void guardar(long e) {

        mArquivo.setPonteiro(mLocal + 18);

        boolean guardou = false;

        for (int i = 0; i < 100; i++) {

            long ePonteiroAntes = mArquivo.getPonteiro();


            long eFramePonteiro = mArquivo.get_u64();

            if (eFramePonteiro == 0) {


                mArquivo.setPonteiro(ePonteiroAntes);
                mArquivo.set_u64(e);
                guardou = true;


                break;
            } else {
                //  System.out.println(i + " de 100 ocupado com " + eFramePonteiro);
            }

            mArquivo.setPonteiro(ePonteiroAntes + 8);


        }

        if (!guardou) {
            System.out.println("Falta de pagina no quadro detectada !");
        }


    }


}
