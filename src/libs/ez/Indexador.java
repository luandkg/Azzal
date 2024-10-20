package libs.ez;


import libs.armazenador.Armazenador;
import libs.arquivos.binario.Arquivador;
import libs.luan.Opcional;
import libs.luan.RefLong;

public class Indexador {

    private Arquivador mArquivador;
    private long mLocalIndice;

    public Indexador(Arquivador eArquivador, long eLocalIndice) {
        mArquivador = eArquivador;
        mLocalIndice = eLocalIndice;
    }


    public void set(long chave, long endereco) {

        while (chave >= getIndiceItensTotalContagem()) {
            aumentar();
        }


        mArquivador.setPonteiro(mLocalIndice);

        int indice_status = mArquivador.get_u8();

        long indice_anterior = mArquivador.get_u64();
        long indice_posterior = mArquivador.get_u64();


        long escritor = 0;

        boolean foi_lido = false;

        long limite_de_chaves = 0;
        long chaves_acumuladas = 0;

        if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

            if (chave < libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE) {
                long pos = chave * 8L;
                mArquivador.setPonteiro(mLocalIndice + (1L + 16L) + pos);
                mArquivador.set_u64(endereco);
                foi_lido = true;
            } else {
                long pos = libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE * 8L;
                mArquivador.setPonteiro(mLocalIndice + (1L + 16L) + pos);
                escritor += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;
            }

            limite_de_chaves += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;
            chaves_acumuladas += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;

            if (!foi_lido) {

                while (indice_posterior > 0 && !foi_lido) {

                    long indice_corrente = indice_posterior;

                    mArquivador.setPonteiro(indice_posterior);

                    indice_status = mArquivador.get_u8();


                    if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

                        indice_anterior = mArquivador.get_u64();
                        indice_posterior = mArquivador.get_u64();

                        limite_de_chaves += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;

                        if (chave < limite_de_chaves) {
                            long pos = (chave - chaves_acumuladas) * 8L;
                            mArquivador.setPonteiro(indice_corrente + (1L + 16L) + pos);
                            mArquivador.set_u64(endereco);
                            foi_lido = true;
                            break;
                        } else {
                            escritor += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;
                        }

                        chaves_acumuladas += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;

                    } else {
                        break;
                    }

                }

            }


        }


    }

    public Opcional<RefLong> get(long chave) {

        Opcional<RefLong> ret = new Opcional<RefLong>();

        while (chave >= getIndiceItensTotalContagem()) {
            aumentar();
        }


        mArquivador.setPonteiro(mLocalIndice);

        int indice_status = mArquivador.get_u8();

        long indice_anterior = mArquivador.get_u64();
        long indice_posterior = mArquivador.get_u64();


        long escritor = 0;

        boolean foi_lido = false;

        long limite_de_chaves = 0;
        long chaves_acumuladas = 0;

        if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

            if (chave < libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE) {
                long pos = chave * 8L;
                mArquivador.setPonteiro(mLocalIndice + (1L + 16L) + pos);
                ret.set(new RefLong(mArquivador.get_u64()));
                foi_lido = true;
            } else {
                long pos = libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE * 8L;
                mArquivador.setPonteiro(mLocalIndice + (1L + 16L) + pos);
                escritor += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;
            }

            limite_de_chaves += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;
            chaves_acumuladas += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;

            if (!foi_lido) {

                while (indice_posterior > 0 && !foi_lido) {

                    long indice_corrente = indice_posterior;

                    mArquivador.setPonteiro(indice_posterior);

                    indice_status = mArquivador.get_u8();


                    if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

                        indice_anterior = mArquivador.get_u64();
                        indice_posterior = mArquivador.get_u64();

                        limite_de_chaves += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;

                        if (chave < limite_de_chaves) {
                            long pos = (chave - chaves_acumuladas) * 8L;
                            mArquivador.setPonteiro(indice_corrente + (1L + 16L) + pos);
                            ret.set(new RefLong(mArquivador.get_u64()));
                            foi_lido = true;
                            break;
                        } else {
                            escritor += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;
                        }

                        chaves_acumuladas += libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;

                    } else {
                        break;
                    }

                }

            }


        }

        return ret;
    }


    public long getIndicePaginasContagem() {

        mArquivador.setPonteiro(mLocalIndice);

        int indice_status = mArquivador.get_u8();

        long indice_anterior = mArquivador.get_u64();
        long indice_posterior = mArquivador.get_u64();


        long contando = 0;

        if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {
            contando += 1;

            while (indice_posterior > 0) {
                mArquivador.setPonteiro(indice_posterior);

                indice_status = mArquivador.get_u8();

                if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

                    contando += 1;

                    indice_anterior = mArquivador.get_u64();
                    indice_posterior = mArquivador.get_u64();

                } else {
                    break;
                }


            }

        }


        return contando;
    }

    public long getIndiceItensTotalContagem() {
        return getIndicePaginasContagem() * (long) libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE;
    }

    public long getIndiceItensUtilizadoContagem() {


        mArquivador.setPonteiro(mLocalIndice);

        int indice_status = mArquivador.get_u8();

        long indice_anterior = mArquivador.get_u64();
        long indice_posterior = mArquivador.get_u64();


        long contando = 0;

        if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

            for (int p = 0; p < libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE; p++) {
                long indice_chave = mArquivador.get_u64();
                if (indice_chave != 0) {
                    contando += 1;
                }
            }


            while (indice_posterior > 0) {
                mArquivador.setPonteiro(indice_posterior);

                indice_status = mArquivador.get_u8();

                if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

                    indice_anterior = mArquivador.get_u64();
                    indice_posterior = mArquivador.get_u64();

                    for (int p = 0; p < libs.armazenador.Armazenador.MAX_ITENS_DO_INDICE; p++) {
                        long indice_chave = mArquivador.get_u64();
                        if (indice_chave != 0) {
                            contando += 1;
                        }
                    }

                } else {
                    break;
                }


            }

        }


        return contando;
    }

    public void aumentar() {

        mArquivador.setPonteiro(mLocalIndice);

        int indice_status = mArquivador.get_u8();

        long indice_corrente = mLocalIndice;

        long indice_anterior = mArquivador.get_u64();
        long indice_posterior = mArquivador.get_u64();


        long contando = 0;

        if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {
            contando += 1;

            while (indice_posterior > 0) {

                indice_corrente = indice_posterior;

                mArquivador.setPonteiro(indice_posterior);

                indice_status = mArquivador.get_u8();

                if (indice_status == libs.armazenador.Armazenador.MARCADOR_INDICE) {

                    contando += 1;

                    indice_anterior = mArquivador.get_u64();
                    indice_posterior = mArquivador.get_u64();

                } else {
                    break;
                }


            }

        }

        mArquivador.setPonteiro(mArquivador.getLength());

        long nova_pagina = mArquivador.getPonteiro();

        mArquivador.set_u8((byte) libs.armazenador.Armazenador.MARCADOR_INDICE);
        mArquivador.set_u64(indice_corrente);
        mArquivador.set_u64(0);


        for (int p = 0; p < Armazenador.MAX_ITENS_DO_INDICE; p++) {
            mArquivador.set_u64(0);
        }

        mArquivador.setPonteiro(indice_corrente);

        int pagina_anterior_status = mArquivador.get_u8();

        long pagina_anterior_anterior = mArquivador.get_u64();

        mArquivador.set_u64(nova_pagina);
        // long pagina_anterior_posterior = mArquivador.readLong();


    }


}
