package libs.asset;

import libs.arquivos.StringView;
import libs.arquivos.binario.Arquivador;

import java.io.File;
import java.util.ArrayList;

public class AssetContainer {


    private ArrayList<Pasta> mPastas;
    private ArrayList<Arquivo> mArquivos;

    private String mCabecalho;
    private String mVersao;
    private String mCriado;
    private String mFinalizado;
    private byte mExtrumExiste;
    private long mExtrumPonteiro;

    private String mArquivo;
    private boolean mAberto;
    private Anexado mAnexado;

    private Referencia mExtrumReferencia;
    private Referencia mDataReferencia;

    public AssetContainer() {

        mPastas = new ArrayList<Pasta>();
        mArquivos = new ArrayList<Arquivo>();

        mCabecalho = "";
        mVersao = "";
        mCriado = "";
        mFinalizado = "";
        mExtrumExiste = (byte) 0;
        mExtrumPonteiro = 0;

        mAberto = false;

        mAnexado = new Anexado(this);


    }

    public String getCabecalho() {
        return mCabecalho;
    }

    public String getVersao() {
        return mVersao;
    }

    public String getCriado() {
        return mCriado;
    }

    public String getFinalizado() {
        return mFinalizado;
    }

    public String getArquivo() {
        return mArquivo;
    }

    public boolean isAberto() {
        return mAberto;
    }

    public long getTamanho() {
        File mFile = new File(mArquivo);
        return mFile.length();
    }

    public Referencia getDataReferencia() {
        return mDataReferencia;
    }

    public Referencia getExtrumReferencia() {
        return mExtrumReferencia;
    }


    public void abrir(String eArquivo) {

        mPastas = new ArrayList<Pasta>();
        mCabecalho = "";
        mArquivo = eArquivo;


        Arquivador fu = new Arquivador(eArquivo);
        fu.inicio();

        mCabecalho = StringView.deArquivador(fu, 100);
        mVersao = StringView.deArquivador(fu, 100);

        long rd1 = fu.getPonteiro();
        mCriado = StringView.deArquivador(fu, 100);

        long rd2 = fu.getPonteiro();
        mFinalizado = StringView.deArquivador(fu, 100);

        mDataReferencia = new Referencia(rd1, rd2);

        long re1 = fu.getPonteiro();

        mExtrumExiste = fu.get();
        long re2 = fu.getPonteiro();

        mExtrumPonteiro = fu.get_u64();

        mExtrumReferencia = new Referencia(re1, re2);

        int v = 0;

        while (v != 13) {
            v = (int) fu.get();

            if (v == 11) {

                String s1 = StringView.deArquivador(fu, 100);

                long r1 = fu.getPonteiro();
                long l2 = fu.get_u64();

                long r2 = fu.getPonteiro();
                long l3 = fu.get_u64();

                mPastas.add(new Pasta(this, new Referencia(r1, r2), new AssetRef(s1, 11, l2, l3)));

            } else if (v == 12) {

                String s1 = StringView.deArquivador(fu, 100);

                long r1 = fu.getPonteiro();
                long l2 = fu.get_u64();

                long r2 = fu.getPonteiro();
                long l3 = fu.get_u64();

                mArquivos.add(new Arquivo(this, new Referencia(r1, r2), new AssetRef(s1, 12, l2, l3)));
            } else {
                //   System.out.println("ERRO -- " + v);
                // break;
            }
        }


        fu.encerrar();


        mAberto = true;
    }

    public void listarDebug(String elocalExportar) {

        System.out.println("Cabecalho : " + mCabecalho);


        listarDebugCom(0, elocalExportar, this.getArquivos(), this.getPastas());
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
            i += mPasta.getPastasContagemInterno(mPasta);
        }

        return i;
    }


    public int getContagem() {
        int i = 0;

        i += getArquivos().size();
        i += this.getPastas().size();

        for (Pasta mPasta : this.getPastas()) {
            i += mPasta.getContagem();
        }

        return i;
    }


    public void listarDebugCom(int t, String eAnterior, ArrayList<Arquivo> mDebugArquivos, ArrayList<Pasta> mDebugPastas) {

        String mTab = "";
        if (t > 0) {
            for (int i = 0; i < t; i++) {
                mTab += "\t";
            }
        }

        for (Pasta mPasta : mDebugPastas) {

            System.out.println(mTab + "DIR = " + mPasta.getNome());
            System.out.println(mTab + "\tI = " + mPasta.getInicio());
            System.out.println(mTab + "\tF = " + mPasta.getFim());

            listarDebugCom(t + 1, eAnterior + mPasta.getNome() + ".", mPasta.getArquivos(), mPasta.getPastas());

        }

        for (Arquivo subArquivo : mDebugArquivos) {

            System.out.println(mTab + "tARQ = " + subArquivo.getNome());
            System.out.println(mTab + "\tI = " + subArquivo.getInicio());
            System.out.println(mTab + "\tF = " + subArquivo.getFim());
            System.out.println(mTab + "\tTamanho = " + subArquivo.getTamanho());


            subArquivo.exportar(eAnterior + subArquivo.getNome());

        }

    }

    public void listarTabelaDeArquivos() {

        for (Arquivo eArquivo : getTabelaDeArquivos()) {
            System.out.println(" ARQUIVO -->> " + " [ " + longMapeado(eArquivo.getInicio(), 7) + " " + longMapeado(eArquivo.getFim(), 7) + " :: " + longMapeado(eArquivo.getFim() - eArquivo.getInicio(), 7) + " ] :: " + eArquivo.getNomeCompleto());
        }

        //  listarTabelaDeArquivosInterno("", this.getArquivos(), this.getPastas());
    }

    public void listarTabelaDeArquivosInterno(String eAntes, ArrayList<Arquivo> mDebugArquivos, ArrayList<Pasta> mDebugPastas) {


        for (Pasta mPasta : mDebugPastas) {


            listarTabelaDeArquivosInterno(eAntes + mPasta.getNome() + "/", mPasta.getArquivos(), mPasta.getPastas());

        }

        for (Arquivo subArquivo : mDebugArquivos) {


            System.out.println(" ARQUIVO -->> " + " [ " + longMapeado(subArquivo.getInicio(), 7) + " " + longMapeado(subArquivo.getFim(), 7) + " :: " + longMapeado(subArquivo.getFim() - subArquivo.getInicio(), 7) + " ] :: " + eAntes + subArquivo.getNome());

        }

    }


    public String longMapeado(long e, int casas) {

        String ret = String.valueOf(e);
        while (ret.length() < casas) {
            ret = "0" + ret;
        }

        return ret;
    }

    public void listarTabelaDePastas() {


        //listarTabelaDePastasInterno("", this.getArquivos(), this.getPastas());

        for (Pasta mPasta : getTabelaDePastas()) {

            System.out.println(" PASTA -->> " + " [ " + longMapeado(mPasta.getInicio(), 7) + " " + longMapeado(mPasta.getFim(), 7) + " :: " + longMapeado(mPasta.getFim() - mPasta.getInicio(), 7) + " ] :: " + mPasta.getNome());


        }

    }

    public void listarTabelaDePastasInterno(String eAntes, ArrayList<Arquivo> mDebugArquivos, ArrayList<Pasta> mDebugPastas) {


        for (Pasta mPasta : mDebugPastas) {


            listarTabelaDePastasInterno(eAntes + mPasta.getNome() + "/", mPasta.getArquivos(), mPasta.getPastas());

        }


    }

    public ArrayList<Pasta> getTabelaDePastas() {

        ArrayList<Pasta> mTabelaDePastas = new ArrayList<Pasta>();

        getTabelaDePastas("", mTabelaDePastas, this.getPastas());

        return mTabelaDePastas;
    }

    public void getTabelaDePastas(String eAntes, ArrayList<Pasta> mTabelaDePastas, ArrayList<Pasta> mDebugPastas) {


        for (Pasta mPasta : mDebugPastas) {


            if (eAntes.length() == 0) {

                AssetRef p = new AssetRef(mPasta.getNome(), 11, mPasta.getInicio(), mPasta.getFim());
                mTabelaDePastas.add(new Pasta(this, mPasta.getReferencia(), p));
                getTabelaDePastas(mPasta.getNome(), mTabelaDePastas, mPasta.getPastas());

            } else {

                AssetRef p = new AssetRef(eAntes + "/" + mPasta.getNome(), 11, mPasta.getInicio(), mPasta.getFim());
                mTabelaDePastas.add(new Pasta(this, mPasta.getReferencia(), p));
                getTabelaDePastas(eAntes + "/" + mPasta.getNome(), mTabelaDePastas, mPasta.getPastas());

            }

        }


    }

    public ArrayList<Arquivo> getTabelaDeArquivos() {

        ArrayList<Arquivo> mTabelaDeArquivos = new ArrayList<Arquivo>();
        mTabelaDeArquivos.addAll(this.getArquivos());

        for (Pasta mPasta : this.getPastas()) {
            getTabelaDeArquivo("", mTabelaDeArquivos, mPasta);
        }

        return mTabelaDeArquivos;
    }

    public void getTabelaDeArquivo(String eAntes, ArrayList<Arquivo> mTabelaDeArquivos, Pasta mDebugPastas) {


        for (Arquivo mArquivo : mDebugPastas.getArquivos()) {
            if (eAntes.length() == 0) {
                mArquivo.setNomeCompleto(mDebugPastas.getNome() + "/" + mArquivo.getNome());
                mTabelaDeArquivos.add(mArquivo);
            } else {
                mArquivo.setNomeCompleto(eAntes + "/" + mArquivo.getNome());
                mTabelaDeArquivos.add(mArquivo);
            }

        }
        for (Pasta mPasta : mDebugPastas.getPastas()) {

            if (eAntes.length() == 0) {
                getTabelaDeArquivo(mDebugPastas.getNome() + "/" + mPasta.getNome(), mTabelaDeArquivos, mPasta);
            } else {
                getTabelaDeArquivo(eAntes + "/" + mPasta.getNome(), mTabelaDeArquivos, mPasta);
            }

        }


    }

    public Pasta getPastaCaminho(String eLocal) {

        for (Pasta mPasta : getTabelaDePastas()) {
            if (mPasta.getNome().contentEquals(eLocal)) {
                return mPasta;
            }
        }

        eLocal = eLocal.replace("\\", "/");

        for (Pasta mPasta : getTabelaDePastas()) {
            if (mPasta.getNome().contentEquals(eLocal)) {
                return mPasta;
            }
        }

        return null;
    }

    public Arquivo getArquivoCaminho(String eLocal) {

        ArrayList<Arquivo> mTodosArquivos = getTabelaDeArquivos();

        for (Arquivo mArquivo : mTodosArquivos) {
            if (mArquivo.getNomeCompleto().contentEquals(eLocal)) {
                return mArquivo;
            }
        }

        eLocal = eLocal.replace("\\", "/");

        for (Arquivo mArquivo : mTodosArquivos) {
            if (mArquivo.getNomeCompleto().contentEquals(eLocal)) {
                return mArquivo;
            }
        }

        return null;
    }


    public boolean existePastaCaminho(String eLocal) {

        for (Pasta mPasta : getTabelaDePastas()) {
            if (mPasta.getNome().contentEquals(eLocal)) {
                return true;
            }
        }

        eLocal = eLocal.replace("\\", "/");

        for (Pasta mPasta : getTabelaDePastas()) {
            if (mPasta.getNome().contentEquals(eLocal)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Arquivo> getArquivos() {
        return mArquivos;
    }

    public ArrayList<Pasta> getPastas() {
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


    public ArquivoTexto getArquivoTexto(String eNome) {
        return new ArquivoTexto(getArquivo(eNome));
    }

    public boolean temApendice() {

        if (mExtrumExiste == (byte) 1) {
            return true;
        } else {
            return false;
        }

    }

    public long getExtrumPonteiro() {
        return mExtrumPonteiro;
    }

    public Listador criarListador(String eNome) {
        return mAnexado.criarListador(eNome);
    }

    public ArquivoLink criarLinkArquivo(String eNome, Arquivo eLocal) {
        return mAnexado.criarLinkArquivo(eNome, eLocal);
    }


    public PastaLink criarLinkPasta(String eNome, Pasta eLocal) {
        return mAnexado.criarLinkPasta(eNome, eLocal);
    }

    public Biblioteca criarBiblioteca(String eNome) {
        return mAnexado.criarBiblioteca(eNome);
    }

    public ArrayList<ArquivoLink> getArquivosLink() {
        return mAnexado.getArquivosLink();
    }

    public ArrayList<PastaLink> getPastasLink() {
        return mAnexado.getPastasLink();
    }

    public ArrayList<Listador> getListadores() {
        return mAnexado.getListadores();
    }

    public ArrayList<Biblioteca> getBibliotecas() {
        return mAnexado.getBibliotecas();
    }

    public void salvarExtrum() {
        mAnexado.salvar();
    }

    public void limparExtrum() {
        mAnexado.limpar();
    }

    public void listarTabelaDeListadores() {


        for (Listador mListador : getListadores()) {

            System.out.println(" -->> " + mListador.getNome() + " :: " + mListador.getArquivos().size());
            for (Pasta eLocal : mListador.getLocais()) {
                System.out.println("\t - LOCAL : " + eLocal.getNome());
            }

        }

    }

    public void listarTabelaDeArquivosLink() {


        for (ArquivoLink mListador : getArquivosLink()) {

            System.out.println(" -->> " + mListador.getNome() + " :: " + mListador.getArquivo().getInicio() + " - " + mListador.getArquivo().getFim());


        }

    }

    public void listarTabelaDeBibliotecas() {


        for (Biblioteca mListador : getBibliotecas()) {

            System.out.println(" -->> " + mListador.getNome() + " :: " + mListador.getArquivos().size());
            for (String eLocal : mListador.getLocais()) {
                System.out.println("\t - LOCAL : " + eLocal);
            }
            for (String eExtensao : mListador.getExtensoes()) {
                System.out.println("\t - EXTENSAO : " + eExtensao);
            }
        }

    }

}
