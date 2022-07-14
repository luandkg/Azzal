package libs.Extenum.Colecionador;

import libs.Extenum.Paginador.Paginador;
import libs.Extenum.Paginador.RefBloco;
import libs.Extenum.Arquivador.Bloco;

import java.util.ArrayList;

public class Colecao {

    private long mBlocoColecaoID;

    private Paginador mPaginador;
    private Bloco mBlocoPrimario;

    private Sumarizador mPrimeiroIndexador;
    private long mChaveador;

    private Bloco mBlocoColecao;

    public Colecao(long eBlocoColecaoID, Paginador ePaginador, Bloco ePrimario) {

        mBlocoColecaoID = eBlocoColecaoID;
        mPaginador = ePaginador;

        //  System.out.println("Indexador -- Bloco ID = " + mPrimario.getBlocoID());

        mBlocoColecao = ePaginador.getArquivador().getBloco(eBlocoColecaoID);

        mBlocoPrimario = ePrimario;
        mPrimeiroIndexador = new Sumarizador(mBlocoPrimario.getArquivador(), 0, mBlocoPrimario);

        // lerCabecalho();

        if (mBlocoPrimario.getUtils().organizarByteInt(mBlocoPrimario.readByte(0)) == 100) {
        } else {
            configurar();
        }

        mChaveador = mBlocoPrimario.getInicio() + 10;

        //    lerCabecalho();

    }

    public String getNome() {
        return mBlocoColecao.lerObjeto(10);
    }

    public long getColecaoID() {
        return mBlocoColecaoID;
    }

    public void lerCabecalho() {

        mPrimeiroIndexador.lerCabecalho();
        System.out.println("\t- Chave         :: " + mBlocoPrimario.readLong(10));
    }


    public long aumentar() {
        mPaginador.getUtils().setPonteiro(mChaveador);
        long c = mPaginador.getUtils().readLong();

        mPaginador.getUtils().setPonteiro(mChaveador);
        mPaginador.getUtils().writeLong(c + 1);

        return c;
    }


    public long getIndiceGeral() {
        mPaginador.getUtils().setPonteiro(mChaveador);
        long c = mPaginador.getUtils().readLong();
        return c;
    }

    public void configurar() {

        //  System.out.println(" -->> Configurar Indexador !");


        mBlocoPrimario.writeByte(0, (byte) 100);
        mBlocoPrimario.writeBoolean(1, false);
        mBlocoPrimario.writeLong(2, 0);
        mBlocoPrimario.writeLong(10, 0);

        mPrimeiroIndexador.zerar();

    }


    public void guarde(String s) {


        boolean eTemLivre = false;
        boolean guardado = false;

        long eChaveGuardada = -1;

        for (Sumarizador ePagina : getPaginas()) {
            if (ePagina.getLivres() > 0) {
                eTemLivre = true;
                break;
            }
        }

        if (!eTemLivre) {
            novaPaginaIndex();
        }

        for (Sumarizador ePagina : getPaginas()) {

            if (ePagina.getLivres() > 0) {

                long eChave = aumentar();

                RefBloco eLivre = mPaginador.getUmBlocoLivre();
                eLivre.travar();

                eChaveGuardada = eChave;

                System.out.println("Guardando em  " + eChave + " :: " + eLivre.getBlocoID() + " = " + s);

                eLivre.getBloco().guardarObjeto(0, s);

                ePagina.guardarComChave(eChave, eLivre.getBlocoID());
                guardado = true;
                break;
            }

        }

        if (!guardado) {
            throw new IllegalArgumentException("Nao foi possivel guardar !");
        }


        Registro eRegistro = getRegistro(eChaveGuardada);
        System.out.println("\t - Valor Guardado = " + eRegistro.getConteudo());

    }

    public Registro getRegistro(long eChave) {

        Registro retRegistro = null;

        for (Registro eRegistro : getRegistros()) {
            if (eRegistro.getChave() == eChave) {
                retRegistro = eRegistro;
                break;
            }
        }

        return retRegistro;
    }

    public long getPaginasContagem() {
        return getPaginas().size();
    }

    public long getIndicesContagem() {
        long eContando = 0;

        for (Sumarizador ePagina : getPaginas()) {
            eContando += ePagina.getTodos();
        }

        return eContando;
    }

    public long getLivresContagem() {
        long eContando = 0;

        for (Sumarizador ePagina : getPaginas()) {
            eContando += ePagina.getLivres();
        }

        return eContando;
    }

    public long getOcupadosContagem() {
        long eContando = 0;

        for (Sumarizador ePagina : getPaginas()) {
            eContando += ePagina.getOcupados();
        }

        return eContando;
    }

    public ArrayList<Sumarizador> getPaginas() {

        ArrayList<Sumarizador> mPaginas = new ArrayList<Sumarizador>();

        Sumarizador mUltimaPagina = mPrimeiroIndexador;

        int mPID = 0;

        mPaginas.add(mUltimaPagina);

        while (mUltimaPagina.temProximo()) {
            mPID += 1;
            mUltimaPagina = new Sumarizador(mBlocoPrimario.getArquivador(), mPID, mBlocoPrimario.getArquivador().getBloco(mUltimaPagina.getProximo()));
            mPaginas.add(mUltimaPagina);

        }

        return mPaginas;

    }


    public void lerIndices() {

        for (Sumarizador ePagina : getPaginas()) {

            System.out.println("Index Pagina :: " + ePagina.getPaginaID());
            System.out.println("");
            System.out.println("\t - Todos    = " + ePagina.getTodos());
            System.out.println("\t - Livres   = " + ePagina.getLivres());
            System.out.println("\t - Ocupados = " + ePagina.getOcupados());
            System.out.println("");

            System.out.println("");

            ePagina.lerIndices();

        }


    }

    public void mostrarPaginas() {

        for (Sumarizador ePagina : getPaginas()) {

            System.out.println("Index Pagina :: " + ePagina.getPaginaID());
            System.out.println("");
            System.out.println("\t - Todos    = " + ePagina.getTodos());
            System.out.println("\t - Livres   = " + ePagina.getLivres());
            System.out.println("\t - Ocupados = " + ePagina.getOcupados());
            System.out.println("");

            System.out.println("");


        }


    }

    public ArrayList<Registro> getRegistros() {

        ArrayList<Registro> mRegistros = new ArrayList<Registro>();

        for (Sumarizador ePagina : getPaginas()) {

            ePagina.colocarConteudosEm(mRegistros);

        }

        return mRegistros;

    }

    public void mostrarSumario() {

        System.out.println("");

        for (Sumarizador ePagina : getPaginas()) {

            System.out.println(" -- Pagina " + ePagina.getPaginaID() + " :: { " + ePagina.getBlocoID() + " } ");

        }
    }

    public void mostrarConteudo() {

        System.out.println("");

        for (Registro eRegistro : getRegistros()) {
            System.out.println(" -- Indice " + eRegistro.getChave() + "  :: { " + eRegistro.getConteudoID() + " } " + eRegistro.getConteudo());
        }

    }


    public void reStart() {
        mPaginador.getUtils().setPonteiro(mChaveador);
        mPaginador.getUtils().writeLong(0);
    }

    public void novaPaginaIndex() {

        int mPID = 0;

        Sumarizador mUltimaPagina = mPrimeiroIndexador;
        //System.out.println("Ultima pagina em  " + mUltimaPagina.getBlocoID());

        while (mUltimaPagina.temProximo()) {
            mPID += 1;
            mUltimaPagina = new Sumarizador(mBlocoPrimario.getArquivador(), mPID, mBlocoPrimario.getArquivador().getBloco(mUltimaPagina.getProximo()));

            //  System.out.println("Trocando ultima para " + mUltimaPagina.getBlocoID());
        }

        mPID += 1;


        RefBloco eLivre = mPaginador.getUmBlocoLivre();
        eLivre.travar();

        mUltimaPagina.marcarDepois(eLivre.getBlocoID());

        Sumarizador mPaginaMestreConstruindo = new Sumarizador(mBlocoPrimario.getArquivador(), mPID, mBlocoPrimario.getArquivador().getBloco(eLivre.getBlocoID()));

        mPaginaMestreConstruindo.anularDepois();


        Bloco eBlocoLimpar = eLivre.getBloco();

        eBlocoLimpar.writeBoolean(0, true);
        eBlocoLimpar.writeBoolean(1, false);
        eBlocoLimpar.writeLong(2, 0);
        eBlocoLimpar.writeLong(10, 0);

        mPaginaMestreConstruindo.zerar();


        if (mPaginador.getDisponiveisContagem() < 200) {
            System.out.println("Vamos expadir pq o indexador precisara de espaco !");
            mPaginador.expanda();
        }

    }


    public void remover(long eIndice) {

        boolean mRemovido = false;
        long mLibertar = 0;

        for (Sumarizador ePagina : getPaginas()) {

            Bloco mBlocoParaRemover = ePagina.remover(eIndice);

            if (mBlocoParaRemover != null) {
                //  System.out.println("preciso remover :: Chave " + eIndice + " -->> " + mBlocoParaRemover.getID());
                mLibertar = mBlocoParaRemover.getID();
                mRemovido = true;
                break;
            }

        }

        if (mRemovido) {
            mPaginador.libertar(mLibertar);
        }

    }

    public void removerTudo() {

        for (Registro eRegistro : getRegistros()) {

            remover(eRegistro.getChave());

        }


    }

    public void atualizar(long eIndice, String eConteudo) {

        boolean mExistente = false;
        long mTrocar = 0;

        for (Sumarizador ePagina : getPaginas()) {

            Bloco mBlocoParaRemover = ePagina.obter(eIndice);

            if (mBlocoParaRemover != null) {
                //  System.out.println("preciso atualizar :: Chave " + eIndice + " -->> " + mBlocoParaRemover.getID());
                mTrocar = mBlocoParaRemover.getID();
                mExistente = true;
                break;
            }

        }

        if (mExistente) {
            mPaginador.atualizar(mTrocar, eConteudo);
        }

    }

    public void dump() {

        System.out.println("");
        System.out.println("\t -->> Indexador Paginas   =   " + this.getPaginasContagem());
        System.out.println("\t -->> Indices Total       =   " + this.getIndicesContagem());
        System.out.println("\t -->> Indices Livres      =   " + this.getLivresContagem());
        System.out.println("\t -->> Indices Ocupados    =   " + this.getOcupadosContagem());
        System.out.println("");

    }

}
