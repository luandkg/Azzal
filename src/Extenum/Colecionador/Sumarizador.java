package Extenum.Colecionador;


import Extenum.Arquivador.Arquivador;
import Extenum.Arquivador.Bloco;

import java.util.ArrayList;

public class Sumarizador {


    private Bloco mBloco;
    private long mPaginaID;
    private int BPP = 50;
    private int IPS = 50;

    public Sumarizador(Arquivador eArquivador, long ePaginaID, Bloco eBloco) {
        mPaginaID = ePaginaID;
        mBloco = new Bloco(eBloco.getID(), eBloco.getFile(), eArquivador, eBloco.getUtils(), eBloco.getInicio(), eBloco.getFim());
    }


    public boolean getStarter() {
        return mBloco.readBoolean(0);
    }

    public boolean temProximo() {
        return mBloco.readBoolean(1);
    }

    public long getProximo() {
        return mBloco.readLong(2);
    }

    public void marcarDepois(long e) {
        mBloco.writeBoolean(1, true);
        mBloco.writeLong(2, e);
    }

    public void anularDepois() {
        mBloco.writeBoolean(1, false);
        mBloco.writeLong(2, 0);
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


    public void lerCabecalho() {

        System.out.println("\t- Index Starter :: " + mBloco.getUtils().organizarByteInt(mBloco.readByte(0)));
        System.out.println("\t- Index Proximo :: " + mBloco.readBoolean(1));
        System.out.println("\t- Proximo       :: " + mBloco.readLong(2));

    }

    public void lerIndices() {

        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (mBloco.readBoolean(p0)) {
                long c1 = mBloco.readLong(p1);
                long c2 = mBloco.readLong(p2);

                //  System.out.println(" -- Indice " + i + " Existe { " + p0 + " : " + p1 + " <> " + p2 + " } ");
                System.out.println(" -- Indice " + i + " Dados  { " + c1 + " ::--:: " + c2 + " } ");

                Bloco b2 = mBloco.getArquivador().getBloco(c2);

                System.out.println("\t - Conteudo : " + b2.lerObjeto(0));
            } else {
                System.out.println(" -- Indice " + i + " -->> VAZIO ");
            }

        }

    }

    public ArrayList<Registro> lerConteudos() {

        ArrayList<Registro> mRegistros = new ArrayList<Registro>();

        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (mBloco.readBoolean(p0)) {

                long c1 = mBloco.readLong(p1);
                long c2 = mBloco.readLong(p2);

                mRegistros.add(new Registro(mBloco, c1, c2));
            }

        }

        return mRegistros;
    }

    public void colocarConteudosEm(ArrayList<Registro> mRegistros) {

        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (mBloco.readBoolean(p0)) {

                long c1 = mBloco.readLong(p1);
                long c2 = mBloco.readLong(p2);

                mRegistros.add(new Registro(mBloco, c1, c2));
            }

        }

    }


    public Bloco remover(long eIndice) {

        Bloco mBlocoParaRemover = null;


        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (mBloco.readBoolean(p0)) {

                long c1 = mBloco.readLong(p1);
                long c2 = mBloco.readLong(p2);

                if (c1 == eIndice) {

                    Bloco b2 = mBloco.getArquivador().getBloco(c2);
                    mBlocoParaRemover = b2;

                    mBloco.writeBoolean(p0, false);

                    break;
                }


            }

        }

        return mBlocoParaRemover;

    }

    public Bloco obter(long eIndice) {

        Bloco mBlocoParaRemover = null;


        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (mBloco.readBoolean(p0)) {

                long c1 = mBloco.readLong(p1);
                long c2 = mBloco.readLong(p2);

                if (c1 == eIndice) {

                    Bloco b2 = mBloco.getArquivador().getBloco(c2);
                    mBlocoParaRemover = b2;


                    break;
                }


            }

        }

        return mBlocoParaRemover;

    }


    public int getLivres() {

        int eContagem = 0;


        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (!mBloco.readBoolean(p0)) {
                eContagem += 1;
            }

        }

        return eContagem;

    }

    public int getOcupados() {

        int eContagem = 0;


        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (mBloco.readBoolean(p0)) {
                eContagem += 1;
            }

        }

        return eContagem;

    }

    public int getTodos() {

        int eContagem = 0;


        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            eContagem += 1;

        }

        return eContagem;

    }


    public void zerar() {

        for (int i = 0; i < BPP; i++) {
            long pi = IPS + (i * 17);
            mBloco.writeBoolean(pi, false);
        }

    }


    public void guardarComChave(long eChave, long eAlocadoID) {


        for (int i = 0; i < BPP; i++) {

            long pi = IPS + (i * 17);

            long p0 = pi;
            long p1 = pi + 1;
            long p2 = pi + 9;

            if (!mBloco.readBoolean(p0)) {

                mBloco.writeBoolean(p0, true);
                mBloco.writeLong(p1, eChave);
                mBloco.writeLong(p2, eAlocadoID);

                break;
            }

        }

    }
}
