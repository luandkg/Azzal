package libs.ez;



import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.RefLong;

import java.util.ArrayList;

public class Paginador {

    public static Opcional<Long> alocar_nova_pagina(Arquivador mArquivador, long capitulo_ponteiro) {

        Opcional<Long> retornar_pagina = new Opcional<Long>();

        mArquivador.setPonteiro(capitulo_ponteiro);
        int pagina_status = mArquivador.get_u8();

        for (int pagina = 0; pagina < Armazenador.MAX_PAGINAS; pagina++) {

            long ponteiro_antes = mArquivador.getPonteiro();

            long pag_local = mArquivador.get_u64();

            if (pag_local == 0) {
                if (libs.armazenador.Armazenador.IS_DEBUG) {
                    System.out.println("!INFO - ALOCAR PAGINA{" + pagina + "}");
                }


                mArquivador.setPonteiro(mArquivador.getLength());


                // CRIAR NOVA PAGINA
                long ponteiro_nova_pagina = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) Armazenador.MARCADOR_PAGINA);
                for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {
                    mArquivador.set_u8((byte) 0);
                    mArquivador.set_u64(0);
                }

                mArquivador.setPonteiro(ponteiro_antes);
                mArquivador.set_u64(ponteiro_nova_pagina);

                retornar_pagina.set(ponteiro_nova_pagina);

                if (libs.armazenador.Armazenador.IS_DEBUG) {
                    System.out.println("!INFO - NOVA PAGINA{" + pagina + "} ALOCADA EM - " + ponteiro_nova_pagina);
                }

                break;
            }

        }

        return retornar_pagina;
    }

    public static Lista<Long> getPaginasUtilizadasDoCapitulo(Arquivador mArquivador, long capitulo_ponteiro) {

        mArquivador.setPonteiro(capitulo_ponteiro);

        int pagina_status = mArquivador.get_u8();

        Lista<Long> paginas = new Lista<Long>();

        for (int pagina = 0; pagina < libs.armazenador.Armazenador.MAX_PAGINAS; pagina++) {
            long pag_local = mArquivador.get_u64();

            if (pag_local != 0) {
                paginas.adicionar(new Long(pag_local));
            }

        }

        return paginas;
    }


    public static void trocar_de_pagina(Arquivador mArquivador, Banco mBanco, RefLong mLocalBanco, RefLong mLocalCapitulos, RefLong mPaginaCorrente) {

        if (libs.armazenador.Armazenador.IS_DEBUG) {
            System.out.println("!INFO - TROCAR DE PAGINA");
        }

        mArquivador.setPonteiro(mLocalCapitulos.get());

        Sumario sumario = new Sumario(mArquivador, mLocalCapitulos.get());

        for (Long capitulo_ponteiro : sumario.getCapitulosUtilizados()) {


            boolean encontrado = false;

            for (Long pagina_ponteiro : getPaginasUtilizadasDoCapitulo(mArquivador, capitulo_ponteiro)) {
                if (libs.armazenador.Armazenador.IS_DEBUG) {
                    System.out.println("!INFO - TENTAR REUTILIZAR ALGUMA PAGINA DO CAP{" + capitulo_ponteiro + "}");
                }

               Pagina pg = new Pagina(mArquivador, mBanco, pagina_ponteiro);

                if (pg.getPonteiro() != 0) {

                    if (libs.armazenador.Armazenador.IS_DEBUG) {
                        System.out.println("!INFO - PAGINA{" + pagina_ponteiro + "} -->> " + pg.contagemUsados() + " : " + pg.contagemTodos());
                    }


                    if (pg.temDisponivel()) {
                        mPaginaCorrente.set(pg.getPonteiro());
                        if (libs.armazenador.Armazenador.IS_DEBUG) {
                            System.out.println("!INFO - REUTILIZAR PAGINA{" + pagina_ponteiro + "} : " + pg.getPonteiro());
                        }

                        encontrado = true;
                        break;
                    }
                }
            }

            if (!encontrado) {


                Opcional<Long> nova_pagina = alocar_nova_pagina(mArquivador, capitulo_ponteiro);

                if (nova_pagina.temValor()) {

                    mPaginaCorrente.set(nova_pagina.get());

                    mArquivador.setPonteiro(mLocalBanco.get() + 1 + 1024 + 8 + 8);
                    mArquivador.set_u64(nova_pagina.get());

                    encontrado = true;
                }


                if (!encontrado) {
                    if (libs.armazenador.Armazenador.IS_DEBUG) {
                        System.out.println("!INFO - SEM ESPACO NO CAPITULO");
                    }


                    Opcional<Long> novo_capitulo_primeira_pagina = alocar_novo_capitulo_com_uma_pagina(mArquivador, mLocalCapitulos.get());

                    if (novo_capitulo_primeira_pagina.temValor()) {

                        mPaginaCorrente.set(novo_capitulo_primeira_pagina.get());

                        mArquivador.setPonteiro(mLocalBanco.get() + 1 + 1024 + 8 + 8);
                        mArquivador.set_u64(mPaginaCorrente.get());


                    }


                }

            }

        }


    }

    public static Opcional<Long> alocar_novo_capitulo_com_uma_pagina(Arquivador mArquivador, long capitulos_ponteiro) {

        Opcional<Long> ret = new Opcional<Long>();

        mArquivador.setPonteiro(capitulos_ponteiro);

        int capitulo_status = mArquivador.get_u8();

        // System.out.println("Contar capitulos guardados em : " + mLocalCapitulos);
        // System.out.println("\t - Status = " + st);


        for (int capitulo = 0; capitulo < libs.armazenador.Armazenador.MAX_CAPITULOS; capitulo++) {

            long ponteiro_local_capitulo = mArquivador.getPonteiro();

            if (libs.armazenador.Armazenador.IS_DEBUG) {
                System.out.println("!INFO - PASSANDO POR CAP{" + capitulo + "}");
            }

            long cap_local = mArquivador.get_u64();
            if (cap_local == 0) {

                if (libs.armazenador.Armazenador.IS_DEBUG) {

                    System.out.println("!INFO - ALOCAR NOVO CAPITULO " + capitulo);
                }

                mArquivador.setPonteiro(mArquivador.getLength());
                long ponteiro_novo_capitulo = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) 160);
                for (int item = 0; item < libs.armazenador.Armazenador.MAX_PAGINAS; item++) {
                    mArquivador.set_u64(0);
                }


                long primeira_pagina_novo_capitulo = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) 180);
                for (int item = 0; item < libs.armazenador.Armazenador.MAX_ITENS_POR_PAGINA; item++) {
                    mArquivador.set_u8((byte) 0);
                    mArquivador.set_u64(0);
                }

                mArquivador.setPonteiro(ponteiro_local_capitulo);
                mArquivador.set_u64(ponteiro_novo_capitulo);

                mArquivador.setPonteiro(ponteiro_novo_capitulo + 1);
                mArquivador.set_u64(primeira_pagina_novo_capitulo);

                ret.set(primeira_pagina_novo_capitulo);


                if (libs.armazenador.Armazenador.IS_DEBUG) {
                    System.out.println("!INFO - NOVO CAPITULO{" + capitulo + "} ALOCADO EM " + ponteiro_local_capitulo);
                }

                break;
            }

        }

        return ret;

    }

    public static long getPaginasUtilizadasContagem(Arquivador mArquivador, long pagina_ponteiro) {

        mArquivador.setPonteiro(pagina_ponteiro);

        long contando = 0;

        int capitulo_status = mArquivador.get_u8();

        //   System.out.println("\t Capitulo " + capitulando + " - Status = " + capitulo_status);

        for (int pagina = 0; pagina < Armazenador.MAX_PAGINAS; pagina++) {
            long pag_local = mArquivador.get_u64();

            //   System.out.println("\t Pagina " + pagina + " - Local = " + pag_local);

            if (pag_local != 0) {
                contando += 1;
            }

        }

        return contando;

    }
}
