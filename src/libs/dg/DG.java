package libs.dg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import libs.arquivos.IO;
import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.Luan.Opcional;
import libs.Luan.fmt;

public class DG {

    // CONSTANTES DE IO

    public static final int BLOCO = 4 * 1024;

    public static final int COLECOES = 255;

    public static final int BLOCO_COLECOES = COLECOES * (BLOCO);

    public static final int TAMANHO_PAGINA = 100 * (BLOCO);
    public static final int TAMANHO_PAGINA_MESTRE = 100 * (BLOCO);

    public static final int ITENS_POR_PAGINA = TAMANHO_PAGINA / BLOCO;

    public static final int PAGINAS = TAMANHO_PAGINA / 8;

    public static final int MAX_ITENS = (PAGINAS * ITENS_POR_PAGINA);

    // FUNCOES ESTATICAS

    public static boolean existe(String eArquivo) {
        return new File(eArquivo).exists();
    }

    public static void criar(String eArquivo) {

        try {
            File a = new File(eArquivo);
            a.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Arquivador aa = new Arquivador(eArquivo);

        aa.writeByte((byte) 44);
        aa.writeByte((byte) 47);

        aa.writeByteRepetidos(BLOCO_COLECOES, (byte) 0);

        aa.encerrar();

    }

    // FUNCOES DE CLASSE

    private String mLocal_Arquivo;
    private Arquivador mArquivador;

    public Arquivador getArquivador() {
        return mArquivador;
    }

    private ArrayList<DGColecao> mCACHE_COLECOES;
    private boolean mCACHE_TODAS = false;

    public final static int CHAVE_UNICA = 1;

    public DG(String eArquivo, boolean eAutoMontagem) {

        if (eAutoMontagem) {
            if (!DG.existe(eArquivo)) {
                DG.criar(eArquivo);
            }
        }

        mLocal_Arquivo = eArquivo;
        mArquivador = new Arquivador(eArquivo);

        mCACHE_COLECOES = new ArrayList<DGColecao>();
    }

    public void verificarAssinatura() {
        mArquivador.setPonteiro(2);

        int a = mArquivador.organizar_to_int(mArquivador.readByte());
        int b = mArquivador.organizar_to_int(mArquivador.readByte());

        if (a == 44 && b == 47) {

        }

    }

    public DGColecao colecao(String eNome) {
        boolean existe = false;

        for (DGColecao c : mCACHE_COLECOES) {
            if (c.getNome().contentEquals(eNome)) {
                // System.out.println("Obtida do Cache :: COLECOES !");
                return c;
            }
        }

        Opcional<DGColecao> ret = getColecaoDireta(eNome);

        // System.out.println("Colecao " + eNome + " -->> " + existe);

        if (ret.isVazio()) {
            ret.set(colecao_criar(eNome));
            mCACHE_TODAS = false;
        }

        mCACHE_COLECOES.add(ret.get());

        return ret.get();
    }

    public void expandir(long comecar, long tamanho, int valor) {

        mArquivador.setPonteiro(comecar);
        mArquivador.writeByteRepetidos(tamanho, (byte) valor);

    }

    private DGColecao colecao_criar(String eNome) {

        boolean criado = false;
        DGColecao ret = null;

        mArquivador.setPonteiro(2);

        TX eTX = new TX();

        for (int proc_colecao = 0; proc_colecao < COLECOES; proc_colecao++) {

            int pos_proc_col = 2 + (proc_colecao * 255);

            mArquivador.setPonteiro(pos_proc_col);

            int proc_a = mArquivador.organizar_to_int(mArquivador.readByte());

            if (proc_a == 0) {

                long p1 = pos_proc_col;

                mArquivador.setPonteiro(pos_proc_col);
                mArquivador.writeByte((byte) 1);

                eTX.escreverFluxo(eNome, mArquivador);

                long p2 = mArquivador.getLength();

                expandir(p2, DG.TAMANHO_PAGINA_MESTRE, 0);

                ret = new DGColecao(this, p1, p2);

                mArquivador.setPonteiro(pos_proc_col);
                mArquivador.setPonteiro(pos_proc_col + 102);

                mArquivador.writeLong(p1);
                mArquivador.writeLong(p2);

                criado = true;
                break;
            }
        }

        if (!criado) {
            System.out.println("DG [ ERRO ] -->> Nao foi possivel criar a colecao : " + eNome);
        }

        return ret;
    }

    public ArrayList<DGColecao> getColecoes() {

        if (mCACHE_TODAS) {
            // System.out.println("Do cache completo !");
            return mCACHE_COLECOES;
        }

        ArrayList<DGColecao> ls = new ArrayList<DGColecao>();

        mArquivador.setPonteiro(2);

        TX eTX = new TX();

        for (int proc_colecao = 0; proc_colecao < COLECOES; proc_colecao++) {

            int pos_proc_col = 2 + (proc_colecao * 255);

            mArquivador.setPonteiro(pos_proc_col);

            int proc_a = mArquivador.organizar_to_int(mArquivador.readByte());
            if (proc_a == 1) {

                String eNome = eTX.lerFluxoLimitado(mArquivador, 100);

                mArquivador.setPonteiro(pos_proc_col + 102);

                long p1 = mArquivador.readLong();
                long p2 = mArquivador.readLong();

                // System.out.println("proc col :: " + proc_colecao + " -->> " + proc_a + " :: "
                // + eNome);
                // System.out.println("\t - P1 :: " + p1);
                // System.out.println("\t - P2 :: " + p2);

                ls.add(new DGColecao(this, p1, p2));

            } else {
                // System.out.println("proc col :: " + proc_colecao + " -->> " + proc_a);
            }

        }

        mCACHE_TODAS = true;
        mCACHE_COLECOES = ls;

        return ls;
    }

    public Opcional<DGColecao> getColecaoDireta(String proc_colecao_nome) {

        Opcional<DGColecao> ret = new Opcional<DGColecao>();

        mArquivador.setPonteiro(2);

        TX eTX = new TX();

        for (int proc_colecao = 0; proc_colecao < COLECOES; proc_colecao++) {

            int pos_proc_col = 2 + (proc_colecao * 255);

            mArquivador.setPonteiro(pos_proc_col);

            int proc_a = mArquivador.organizar_to_int(mArquivador.readByte());
            if (proc_a == 1) {

                String eNome = eTX.lerFluxoLimitado(mArquivador, 100);

                if (eNome.contentEquals(proc_colecao_nome)) {

                    mArquivador.setPonteiro(pos_proc_col + 102);

                    long p1 = mArquivador.readLong();
                    long p2 = mArquivador.readLong();

                    // System.out.println("proc col :: " + proc_colecao + " -->> " + proc_a + " :: "
                    // + eNome);
                    // System.out.println("\t - P1 :: " + p1);
                    // System.out.println("\t - P2 :: " + p2);

                    ret.set(new DGColecao(this, p1, p2));
                }

            } else {
                // System.out.println("proc col :: " + proc_colecao + " -->> " + proc_a);
            }

        }

        return ret;
    }

    public void mostrar_informacoes() {

        System.out.println("COLECIONADOR DG 1.0");
        System.out.println("");
        System.out.println("\t - Coleções : " + getColecoes().size());
        System.out.println("\t - Tamanho  : " + IO.formatar_tamanho(mArquivador.getLength()));

    }

    public void mostrar_colecoes() {

        for (DGColecao colecao : getColecoes()) {

            String p1 = fmt.format("{esq20}", colecao.getNome());
            String p2 = fmt.format("{dir5}", colecao.getPaginasContagem());
            String p3 = fmt.format("{dir5}", colecao.getItensContagem());
            String p4 = fmt.format("{dir5}", colecao.getVaziosContagem());

            System.out.println(
                    "\t -- Colecao " + p1 + "  -->>     PAGINAS = " + p2 + " ITENS = " + p3 + " VAZIOS = " + p4);
        }

    }

    public void fechar() {
        mArquivador.encerrar();
    }

    public int indicePara(String eNome) {
        return Features.INDEX(this, eNome);
    }

    public int ultimoIndice(String eNome) {
        return Features.INDEX_ANTERIOR(this, eNome);
    }
}
