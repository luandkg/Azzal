package libs.Extenum.Paginador;

import libs.Extenum.Arquivador.Arquivador;
import libs.Extenum.Arquivador.Bloco;
import libs.Extenum.Arquivador.Utils;
import libs.Luan.fmt;

import java.util.ArrayList;

public class Paginador {

    private String mArquivo;
    private Arquivador mArquivador;
    private final int BPP = 50;

    public Paginador(String eArquivo) {

        mArquivo = eArquivo;
        mArquivador = new Arquivador(mArquivo);

        if (!mArquivador.temConstrutor()) {

            mArquivador.expanda();

            Pagina mPaginaMestreConstruindo = new Pagina(mArquivador, 0, mArquivador.getBloco(0));

            mPaginaMestreConstruindo.marcarAntes(false);
            mPaginaMestreConstruindo.marcarDepois(false);
            mPaginaMestreConstruindo.escreverAntes(0);
            mPaginaMestreConstruindo.escreverDepois(0);

            for (int p = 0; p < BPP; p++) {

                mArquivador.expanda();

                long indice = mArquivador.getBlocosContagem();

                // System.out.println("Pos " + p + " ->> " + indice);

                mPaginaMestreConstruindo.guardar(p, indice, true);

            }

        }


    }

    public Arquivador getArquivador() {
        return mArquivador;
    }

    public void mostrarPagina(Pagina mPaginaMestre) {

        System.out.println("      - Inicio      = " + mPaginaMestre.getInicio());
        System.out.println("      - Fim         = " + mPaginaMestre.getFim());
        System.out.println("      - Tamanho     = " + mPaginaMestre.getTamanho());
        System.out.println("      - Tem Antes   = " + mPaginaMestre.temAntes());
        System.out.println("      - Tem Depois  = " + mPaginaMestre.temDepois());

        if (mPaginaMestre.temAntes()) {
            System.out.println("      - Antes       = " + mPaginaMestre.getAntes());
        } else {
            System.out.println("      - Antes       = --- ");
        }

        if (mPaginaMestre.temDepois()) {
            System.out.println("      - Depois      = " + mPaginaMestre.getDepois());
        } else {
            System.out.println("      - Depois      = --- ");
        }


        System.out.println("");

        for (int r = 0; r < BPP; r++) {
            System.out.println("\t - Ref " + r + " :: " + mPaginaMestre.getGuardado(r).getBlocoID() + " :: " + mPaginaMestre.getGuardado(r).getStatus());
        }

    }

    public void mostrarBlocos() {


        long b = mArquivador.getBlocosContagem();

        for (long i = 0; i < b; i++) {

            Bloco mBlocoCorrente = mArquivador.getBloco(i);

            String s1 = " Inicio = " + fmt.longNum(mBlocoCorrente.getInicio(), 10);
            String s2 = " Fim = " + fmt.longNum(mBlocoCorrente.getFim(), 10);
            String s3 = " Tamanho = " + fmt.longNum(mBlocoCorrente.getTamanho(), 5);

            System.out.println(" -->> Bloco " + fmt.longNum(i, 5) + s1 + s2 + s3);

        }


    }

    public ArrayList<Pagina> getPaginas() {

        ArrayList<Pagina> mPaginas = new ArrayList<Pagina>();

        int mPID = 0;

        Pagina mUltimaPagina = new Pagina(mArquivador, mPID, mArquivador.getBloco(0));
        mPaginas.add(mUltimaPagina);

        while (mUltimaPagina.temDepois()) {
            mPID += 1;
            mUltimaPagina = new Pagina(mArquivador, mPID, mArquivador.getBloco(mUltimaPagina.getDepois()));
            mPaginas.add(mUltimaPagina);

        }

        return mPaginas;

    }

    public boolean temConstrutor() {
        return mArquivador.temConstrutor();
    }


    public long getConstrutor() {
        return mArquivador.getConstrutor();
    }


    public void formatar() {
        mArquivador.formatar();
    }

    public void expanda() {

        int mPID = 0;

        Pagina mUltimaPagina = new Pagina(mArquivador, mPID, mArquivador.getBloco(0));
        //System.out.println("Ultima pagina em  " + mUltimaPagina.getBlocoID());

        while (mUltimaPagina.temDepois()) {
            mPID += 1;
            mUltimaPagina = new Pagina(mArquivador, mPID, mArquivador.getBloco(mUltimaPagina.getDepois()));

            //  System.out.println("Trocando ultima para " + mUltimaPagina.getBlocoID());

        }

        mPID += 1;

        mArquivador.expanda();


        mUltimaPagina.marcarDepois(true);
        mUltimaPagina.escreverDepois(mArquivador.getBlocosContagem() - 1);


        Pagina mPaginaMestreConstruindo = new Pagina(mArquivador, mPID, mArquivador.getBloco(mArquivador.getBlocosContagem() - 1));

        mPaginaMestreConstruindo.marcarAntes(true);
        mPaginaMestreConstruindo.marcarDepois(false);
        mPaginaMestreConstruindo.escreverAntes(mUltimaPagina.getBlocoID());
        mPaginaMestreConstruindo.escreverDepois(0);

        for (int p = 0; p < BPP; p++) {

            mArquivador.expanda();

            long indice = mArquivador.getBlocosContagem();

            //System.out.println("Pos " + p + " ->> " + indice);

            mPaginaMestreConstruindo.guardar(p, indice, true);

        }


    }

    public void fechar() {
        mArquivador.fechar();
    }

    public long getBlocosContagem() {
        return mArquivador.getBlocosContagem();
    }

    public long getPaginasContagem() {
        return getPaginas().size();
    }

    public long getDisponiveisContagem() {

        long eContagem = 0;

        for (Pagina ePagina : getPaginas()) {

            for (int r = 0; r < BPP; r++) {

                RefBloco eRefBloco = ePagina.getGuardado(r);

                if (eRefBloco.getStatus()) {
                    eContagem += 1;
                }
            }

        }

        return eContagem;
    }

    public long getOcupadosContagem() {
        long eContagem = 0;

        for (Pagina ePagina : getPaginas()) {

            eContagem += 1;

            for (int r = 0; r < BPP; r++) {

                RefBloco eRefBloco = ePagina.getGuardado(r);
                if (!eRefBloco.getStatus()) {
                    eContagem += 1;
                }
            }

        }

        return eContagem;
    }

    public void mostrarPaginas() {
        for (Pagina ePagina : getPaginas()) {

            System.out.println(" -->> Pagina " + ePagina.getPaginaID());
            mostrarPagina(ePagina);


        }
    }

    public Utils getUtils() {
        return mArquivador.getUtils();
    }

    public RefBloco getUmBlocoLivre() {

        boolean enc = false;
        RefBloco mRef = null;

        if (this.getDisponiveisContagem() < 100) {
            System.out.println("Vamos expandir pq ta ficando pequeno isso aqui !");
            this.expanda();
        }

        for (Pagina ePagina : getPaginas()) {

            for (int r = 0; r < BPP; r++) {
                RefBloco eRef = ePagina.getGuardado(r);
                if (eRef.getStatus()) {

                    enc = true;
                    mRef = eRef;
                    break;
                }
            }

            if (enc) {
                break;
            }

        }


        if (enc) {
            return mRef;
        } else {
            throw new IllegalArgumentException("Houve um grande problema !");
        }


    }

    public void libertar(long eIndice) {

        boolean mLibertar = false;

        for (Pagina ePagina : getPaginas()) {

            //    System.out.println("preciso libertar :: " + eIndice);

            for (int r = 0; r < BPP; r++) {
                RefBloco eRef = ePagina.getGuardado(r);
                if (!eRef.getStatus()) {

                    if (eRef.getBlocoID() == eIndice) {
                        //    System.out.println("Libertando :: " + eIndice);
                        eRef.destravar();
                        mLibertar = true;
                        break;
                    }

                }
            }

            if (mLibertar) {
                break;
            }

        }


    }

    public void atualizar(long eIndice, String eConteudo) {

        boolean mAtualizado = false;

        for (Pagina ePagina : getPaginas()) {

            //   System.out.println("preciso atualizar :: " + eIndice);

            for (int r = 0; r < BPP; r++) {
                RefBloco eRef = ePagina.getGuardado(r);
                if (!eRef.getStatus()) {

                    if (eRef.getBlocoID() == eIndice) {
                        //        System.out.println("Atualizando :: " + eIndice);

                        eRef.getBloco().guardarObjeto(0, eConteudo);

                        mAtualizado = true;
                        break;
                    }

                }
            }

            if (mAtualizado) {
                break;
            }

        }


    }

    public void dump_cabecalho() {

        if (mArquivador.temConstrutor()) {
            System.out.println("\t -->> Construtor  =   " + mArquivador.getConstrutor());
        } else {
            System.out.println("\t -->> Construtor  =   -");
        }


    }

    public void dump_blocos() {

        System.out.println("");
        System.out.println("\t -->> Blocos    =   " + this.getBlocosContagem());
        System.out.println("\t -->> Blocos L  =   " + this.getDisponiveisContagem());
        System.out.println("\t -->> Blocos O  =   " + this.getOcupadosContagem());
        System.out.println("");

    }

    public void dump_paginas() {


        System.out.println("");
        System.out.println("-->> Paginas   =   " + this.getPaginasContagem());
        System.out.println("");

        for (Pagina ePagina : getPaginas()) {

            String s0 = " :: Bloco = " + fmt.longNum(ePagina.getBlocoID(), 5);

            String s1 = " Inicio = " + fmt.longNum(ePagina.getInicio(), 10);
            String s2 = " Fim = " + fmt.longNum(ePagina.getFim(), 10);
            String s3 = " Tamanho = " + fmt.longNum(ePagina.getTamanho(), 5);

            System.out.println("\t-->> Pagina " + fmt.longNum(ePagina.getPaginaID(), 5) + s0 + s1 + s2 + s3);

        }

    }


    public void dump_refs() {


        System.out.println("");
        System.out.println("-->> Paginas   =   " + this.getPaginasContagem());
        System.out.println("");

        for (Pagina ePagina : getPaginas()) {

            long mPTotal = 0;
            long mPOcupado = 0;
            long mPLivre = 0;

            for (int r = 0; r < BPP; r++) {
                RefBloco eRef = ePagina.getGuardado(r);

                mPTotal += 1;

                if (!eRef.getStatus()) {
                    mPOcupado += 1;
                } else {
                    mPLivre += 1;
                }
            }


            String s0 = " :: Bloco = " + fmt.longNum(ePagina.getBlocoID(), 5);

            String s1 = " Total = " + fmt.longNum(mPTotal, 3);
            String s2 = " Ocupado = " + fmt.longNum(mPOcupado, 3);
            String s3 = " Livre = " + fmt.longNum(mPLivre, 3);

            System.out.println("\t-->> Pagina " + fmt.longNum(ePagina.getPaginaID(), 5) + s0 + s1 + s2 + s3);

        }

    }
}
