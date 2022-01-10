package Extenum;


import Extenum.Arquivador.Bloco;
import Extenum.Colecionador.Colecao;
import Extenum.Colecionador.Registro;
import Extenum.Colecionador.Sumarizador;
import Extenum.Paginador.Pagina;
import Extenum.Paginador.Paginador;
import Extenum.Paginador.RefBloco;
import Luan.TTY;

import java.util.ArrayList;

public class Organizador {

    private String mArquivo;
    private Paginador mPaginador;
    private RefBloco mPrimario;
    private Bloco mBlocoPrimario;
    private Sumarizador mSumarizadorPrimario;
    private long mChaveador;

    private int BPP = 50;
    private int IPS = 50;
    private int MINIMO = 200;

    public Organizador(String eArquivo) {

        mArquivo = eArquivo;
        mPaginador = new Paginador(eArquivo);

        mPrimario = mPaginador.getPaginas().get(0).getGuardado(0);

        mBlocoPrimario = mPrimario.getBloco();

        mSumarizadorPrimario = new Sumarizador(mBlocoPrimario.getArquivador(), 0, mBlocoPrimario);

        // lerCabecalho();

        if (mBlocoPrimario.getUtils().organizarByteInt(mBlocoPrimario.readByte(0)) != 100) {

            System.out.println(" -->> Configurando mestre das colecoes !");

            mPrimario.travar();

            mBlocoPrimario.writeByte(0, (byte) 100);
            mBlocoPrimario.writeBoolean(1, false);
            mBlocoPrimario.writeLong(2, 0);
            mBlocoPrimario.writeLong(10, 0);

            mSumarizadorPrimario.zerar();

        }

        mChaveador = mBlocoPrimario.getInicio() + 10;

        //    lerCabecalho();

    }

    public ArrayList<Sumarizador> getPaginas() {

        ArrayList<Sumarizador> mPaginas = new ArrayList<Sumarizador>();

        int mPID = 0;

        mPaginas.add(mSumarizadorPrimario);

        Sumarizador mPaginaCorrente = mSumarizadorPrimario;

        while (mPaginaCorrente.temProximo()) {
            mPID += 1;
            mPaginaCorrente = new Sumarizador(mBlocoPrimario.getArquivador(), mPID, mBlocoPrimario.getArquivador().getBloco(mPaginaCorrente.getProximo()));
            mPaginas.add(mPaginaCorrente);
        }

        return mPaginas;

    }

    public ArrayList<Registro> getRegistrosColecoes() {

        ArrayList<Registro> mRegistros = new ArrayList<Registro>();

        for (Sumarizador ePagina : getPaginas()) {

            ePagina.colocarConteudosEm(mRegistros);

        }

        return mRegistros;

    }

    public ArrayList<Colecao> getColecoes() {

        ArrayList<Colecao> mColecoes = new ArrayList<Colecao>();

        for (Sumarizador ePagina : getPaginas()) {

            for (Registro eRegistro : ePagina.lerConteudos()) {


                long retBlocoColecao = eRegistro.getConteudoID();
                long retBlocoDadosColecao = eRegistro.getConteudoBloco().readLong(0);

                //  System.out.println("Montando colecao :: " +retBlocoColecao + "<>" +retBlocoDadosColecao);

                mColecoes.add(new Colecao(retBlocoColecao, mPaginador, mPaginador.getArquivador().getBloco(retBlocoDadosColecao)));

            }


        }

        return mColecoes;

    }

    public Colecao getColecao(String s) {

        boolean mExiste = false;

        long retBlocoColecao = 0;
        long retBlocoDadosColecao = 0;

        for (Registro eRegistro : getRegistrosColecoes()) {

            if (eRegistro.getConteudoBloco().lerObjeto(10).contentEquals(s)) {

                retBlocoColecao = eRegistro.getConteudoID();
                retBlocoDadosColecao = eRegistro.getConteudoBloco().readLong(0);

                mExiste = true;
                break;
            }

        }


        if (!mExiste) {

            // Criar Colecao

            boolean eTemLivre = false;
            boolean guardado = false;

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

                    RefBloco eBlocoDadosColecao = mPaginador.getUmBlocoLivre();
                    eBlocoDadosColecao.travar();

                    RefBloco eBlocoColecao = mPaginador.getUmBlocoLivre();
                    eBlocoColecao.travar();

                    eBlocoColecao.getBloco().writeLong(0, eBlocoDadosColecao.getBlocoID());
                    eBlocoColecao.getBloco().guardarObjeto(10, s);


                    ePagina.guardarComChave(eChave, eBlocoColecao.getBlocoID());
                    guardado = true;

                    retBlocoColecao = eBlocoColecao.getBlocoID();
                    retBlocoDadosColecao = eBlocoDadosColecao.getBlocoID();

                    Bloco mBlocoPrimarioColecao = mPaginador.getArquivador().getBloco(retBlocoDadosColecao);

                    mBlocoPrimarioColecao.writeByte(0, (byte) 100);
                    mBlocoPrimarioColecao.writeBoolean(1, false);
                    mBlocoPrimarioColecao.writeLong(2, 0);
                    mBlocoPrimarioColecao.writeLong(10, 0);


                    for (int i = 0; i < BPP; i++) {

                        long pi = IPS + (i * 17);
                        mBlocoPrimarioColecao.writeBoolean(pi, false);
                    }

                    break;
                }

            }

            if (!guardado) {
                throw new IllegalArgumentException("Nao foi possivel criar a nova colecao !");
            }

        }

        // System.out.println(" Obtendo  colecao :: " +retBlocoColecao + "<>" + retBlocoDadosColecao);

        return new Colecao(retBlocoColecao, mPaginador, mPaginador.getArquivador().getBloco(retBlocoDadosColecao));

    }

    public long aumentar() {
        mPaginador.getUtils().setPonteiro(mChaveador);
        long c = mPaginador.getUtils().readLong();
        c += 1;

        mPaginador.getUtils().setPonteiro(mChaveador);
        mPaginador.getUtils().writeLong(c);
        return c;
    }


    public void novaPaginaIndex() {

        int mPID = 0;

        Sumarizador mUltimaPagina = mSumarizadorPrimario;
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


        if (mPaginador.getDisponiveisContagem() < MINIMO) {
            System.out.println("Vamos expadir pq o indexador precisara de espaco !");
            mPaginador.expanda();
        }

    }


    public void dump_paginas() {
        mPaginador.dump_paginas();
    }

    public void dump_refs() {
        mPaginador.dump_refs();
    }

    public void dump_blocos() {
        mPaginador.getArquivador().dump_blocos();
    }

    public void dump_colecoes() {

        TTY eTTY = new TTY();

        System.out.println("");
        System.out.println("-->> Colecoes   =   " + this.getColecoes().size());
        System.out.println("");

        for (Colecao eColecao : getColecoes()) {


            String s0 = " :: Paginas = " + eTTY.LongNum(eColecao.getPaginasContagem(), 5);

            String s1 = " Total = " + eTTY.LongNum(eColecao.getLivresContagem(), 3);
            String s2 = " Ocupado = " + eTTY.LongNum(eColecao.getOcupadosContagem(), 3);
            String s3 = " Indice Geral = " + eTTY.LongNum(eColecao.getIndiceGeral(), 3);

            System.out.println("\t-->> Colecao " + eTTY.LongNum(eColecao.getColecaoID(), 5) + s0 + s1 + s2 + s3);

        }

    }

    public void mostrarBlocosColecao() {

        System.out.println("");

        for (Sumarizador ePagina : getPaginas()) {

            System.out.println(" -- Pagina Global " + ePagina.getPaginaID() + " :: { " + ePagina.getBlocoID() + " } ");

        }


        for (Registro eRegistro : getRegistrosColecoes()) {

            System.out.println(" -- Pagina " + eRegistro.getChave() + " :: { " + eRegistro.getConteudoID() + " } ");

        }

    }


    public void fechar() {
        mPaginador.fechar();
    }
}
