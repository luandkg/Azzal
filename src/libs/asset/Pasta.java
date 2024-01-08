package libs.asset;

import libs.arquivos.StringView;
import libs.arquivos.binario.Arquivador;

import java.util.ArrayList;

public class Pasta {

    private AssetRef mAssetRef;
    private AssetContainer mAssetContainer;
    private boolean mAberto;
    private ArrayList<Arquivo> mArquivos;
    private ArrayList<Pasta> mPastas;


    private boolean mAbertoTodos;
    private ArrayList<Arquivo> mArquivosTodos;
    private ArrayList<Pasta> mPastasTodos;

    private Referencia mReferencia;

    public Pasta(AssetContainer eAssetContainer, Referencia eReferencia, AssetRef eAssetRef) {

        mAssetContainer = eAssetContainer;
        mReferencia = eReferencia;
        mAssetRef = eAssetRef;

        mAberto = false;
        mAbertoTodos = false;

        mArquivos = new ArrayList<Arquivo>();
        mPastas = new ArrayList<Pasta>();

        mArquivosTodos = new ArrayList<Arquivo>();
        mPastasTodos = new ArrayList<Pasta>();

    }

    public String getNome() {
        return mAssetRef.getNome();
    }

    public long getTipo() {
        return mAssetRef.getTipo();
    }

    public long getInicio() {
        return mAssetRef.getInicio();
    }

    public long getFim() {
        return mAssetRef.getFim();
    }

    public Referencia getReferencia() {
        return mReferencia;
    }


    private void abrir() {

        mPastas.clear();
        mArquivos.clear();


        if (getInicio() != getFim()) {


            Arquivador fu = new Arquivador(mAssetContainer.getArquivo());

            fu.setPonteiro(getInicio());
            int v = 0;

            while (v != 13) {
                v = (int) fu.get_u8();

                if (v == 11) {

                    String s1 = StringView.deArquivador(fu, 100);

                    long r1 = fu.getPonteiro();

                    long l2 = fu.get_u64();

                    long r2 = fu.getPonteiro();

                    long l3 = fu.get_u64();


                    mPastas.add(new Pasta(mAssetContainer, new Referencia(r1, r2), new AssetRef(s1, 11, l2, l3)));

                } else if (v == 12) {

                    String s1 = StringView.deArquivador(fu, 100);

                    long r1 = fu.getPonteiro();

                    long l2 = fu.get_u64();

                    long r2 = fu.getPonteiro();

                    long l3 = fu.get_u64();

                    mArquivos.add(new Arquivo(mAssetContainer, new Referencia(r1, r2), new AssetRef(s1, 12, l2, l3)));

                }
            }

            fu.encerrar();

        }


    }

    public ArrayList<Arquivo> getArquivos() {

        if (!mAberto) {
            abrir();
        }

        return mArquivos;

    }

    public ArrayList<Pasta> getPastas() {

        if (!mAberto) {
            abrir();
        }

        return mPastas;

    }


    public boolean existePasta(String eNome) {

        boolean ret = false;

        for (Pasta mPasta : getPastas()) {
            if (mPasta.getNome().contentEquals(eNome)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public boolean existeArquivo(String eNome) {

        boolean ret = false;

        for (Arquivo mArquivo : getArquivos()) {
            if (mArquivo.getNome().contentEquals(eNome)) {
                ret = true;
                break;
            }
        }
        return ret;
    }


    public Pasta getPasta(String eNome) {

        Pasta ret = null;

        for (Pasta mPasta : getPastas()) {
            if (mPasta.getNome().contentEquals(eNome)) {
                ret = mPasta;
                break;
            }
        }
        return ret;
    }

    public Arquivo getArquivo(String eNome) {

        Arquivo ret = null;

        for (Arquivo mArquivo : getArquivos()) {
            if (mArquivo.getNome().contentEquals(eNome)) {
                ret = mArquivo;
                break;
            }
        }
        return ret;
    }

    public ArquivoImagem getArquivoImagem(String eNome) {
        return new ArquivoImagem(getArquivo(eNome));
    }


    public int getArquivosContagem() {
        int i = 0;

        i += this.getArquivos().size();

        for (Pasta mPasta : this.getPastas()) {
            i += getArquivosContagemInterno(mPasta);
        }

        return i;
    }


    public int getArquivosContagemInterno(Pasta ePasta) {
        int i = 0;

        i += ePasta.getArquivos().size();

        for (Pasta mPasta : ePasta.getPastas()) {
            i += getArquivosContagemInterno(mPasta);
        }

        return i;
    }


    public int getPastasContagem() {
        int i = 0;

        i += this.getPastas().size();

        for (Pasta mPasta : this.getPastas()) {
            i += getPastasContagemInterno(mPasta);
        }

        return i;
    }

    public int getPastasContagemInterno(Pasta ePasta) {
        int i = 0;

        i += ePasta.getPastas().size();

        for (Pasta mPasta : ePasta.getPastas()) {
            i += getPastasContagemInterno(mPasta);
        }

        return i;
    }


    public int getContagem() {
        int i = 0;

        i += getArquivos().size();
        i += this.getPastas().size();

        for (Pasta mPasta : this.getPastas()) {
            i += getContagemInterno(mPasta);
        }

        return i;
    }

    public int getContagemInterno(Pasta ePasta) {
        int i = 0;

        i += ePasta.getArquivos().size();
        i += ePasta.getPastas().size();

        for (Pasta mPasta : ePasta.getPastas()) {
            i += getContagemInterno(mPasta);
        }

        return i;
    }


    public ArrayList<Arquivo> getArquivosTodos() {

        if (!mAbertoTodos) {
            abrirTodos();
        }

        return mArquivosTodos;

    }

    public ArrayList<Pasta> getPastasTodos() {

        if (!mAbertoTodos) {
            abrirTodos();
        }

        return mPastasTodos;

    }

    private void abrirTodos() {

        mArquivosTodos.clear();
        mPastasTodos.clear();

        mAbertoTodos = true;

        for (Arquivo ma : getArquivos()) {
            mArquivosTodos.add(ma);
        }

        for (Pasta ma : getPastas()) {
            mPastasTodos.add(ma);
            abrirTodosInterno(ma);
        }

    }

    private void abrirTodosInterno(Pasta ePasta) {

        for (Arquivo ma : ePasta.getArquivos()) {
            mArquivosTodos.add(ma);
        }

        for (Pasta ma : ePasta.getPastas()) {
            mPastasTodos.add(ma);
            abrirTodosInterno(ma);
        }

    }


}
