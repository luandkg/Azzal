package apps.app_azzal;

import libs.Arquivos.Audio.AreaBinaria;
import libs.Arquivos.Audio.RepeticaoBinaria;
import libs.Arquivos.Audio.RepetidorBinario;
import libs.Arquivos.Binario.Arquivador;
import libs.Arquivos.Binario.Inteiro;

import java.util.ArrayList;

public class VerificarAU {

    public static void verificar(String eArquivo) {

        AreaBinaria ab = new AreaBinaria();


        RepetidorBinario rb = new RepetidorBinario();

        Arquivador arquivador = new Arquivador(eArquivo, "r");
        long eTamanho = arquivador.getLength();

        arquivador.encerrar();


        System.out.println("Tamanho Total      :: " + eTamanho);

        long comecar = 0;
        long terminar = eTamanho;
        int i = 0;

        long s_total = 0;
        long s_repeticoes = 0;
        int rep_contagem = 0;

        int mostrar = 0;

        Arquivador au_arquivador = new Arquivador(eArquivo + "5", "rw");

        int TAMANHO_BUFFER = 256;

        while (comecar < terminar) {

            ArrayList<Integer> z_Bloco = ab.getAreaGrande(eArquivo, comecar, TAMANHO_BUFFER);

            ArrayList<RepeticaoBinaria> z_repeticoes = rb.getRepeticoes(z_Bloco);


            rep_contagem = 0;
            int chaves = 0;

            for (RepeticaoBinaria rep : z_repeticoes) {
                if (rep.getQuantidade() > 1) {
                    // System.out.println("REP :: " + rep.getValor() + " -->> " + rep.getQuantidade());
                    rep_contagem += rep.getQuantidade();
                    chaves += 1;
                }
            }

            if (rep_contagem == 0) {

                // au_arquivador.writeByte((byte) 100);
                au_arquivador.writeByte((byte) 0);

                for (RepeticaoBinaria rep : z_repeticoes) {
                    au_arquivador.writeByte((byte) rep.getB1());
                    au_arquivador.writeByte((byte) rep.getB2());
                }

            } else {

                // au_arquivador.writeByte((byte) 200);

                au_arquivador.writeByte((byte) chaves);

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() > 1) {
                        au_arquivador.writeByte((byte) rep.getB1());
                        au_arquivador.writeByte((byte) rep.getB2());

                        au_arquivador.writeByte((byte) rep.getPosicoes().size());

                        for (Integer ePosicao : rep.getPosicoes()) {
                            au_arquivador.writeByte((byte) ((int) ePosicao));
                        }

                    }
                }

                int originais = 0;

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() == 1) {
                        au_arquivador.writeByte((byte) rep.getB1());
                        au_arquivador.writeByte((byte) rep.getB2());
                        originais += 1;
                    }
                }


                debug(z_repeticoes, rep_contagem, originais);
                 ab.mostrar(z_Bloco);

                // System.out.println("-------");
                break;
            }


            long eFalta = eTamanho - ((comecar + z_Bloco.size()));

            if (mostrar >= 100 || ((comecar + (TAMANHO_BUFFER)) >= terminar)) {

                int dobro = rep_contagem + rep_contagem;

                System.out.println("Bloco  " + i);
                System.out.println("\t- Tamanho    :: " + z_Bloco.size() + " -->> " + comecar + " ate " + (comecar + z_Bloco.size()) + " :: " + eFalta);
                System.out.println("\t- Repeticoes :: " + dobro);
                System.out.println("\t- Final      :: " + (z_Bloco.size() - dobro));

                mostrar = 0;
            }
            mostrar += 1;


            s_total += z_Bloco.size();
            s_repeticoes += rep_contagem;

            comecar += (TAMANHO_BUFFER);
            i += 1;
        }

        au_arquivador.encerrar();


        System.out.println("");
        System.out.println("FINALMENTE");
        System.out.println("");
        System.out.println("\t- Total      :: " + s_total);
        System.out.println("\t- Repeticoes :: " + s_repeticoes);
        System.out.println("\t- Final      :: " + (s_total - s_repeticoes));

    }

    public static void debug(ArrayList<RepeticaoBinaria> z_repeticoes, int rep_contagem, int originais) {

        int completo = (2 * rep_contagem) + (2 * originais);

        System.out.println("Repetidos :: " + rep_contagem);
        System.out.println("Originais :: " + originais);
        System.out.println("Completo :: " + completo);


        for (RepeticaoBinaria rep : z_repeticoes) {
            if (rep.getQuantidade() > 1) {

                System.out.println("Rep :: " + Inteiro.byteToInt(rep.getB1()) + " :: " + Inteiro.byteToInt(rep.getB2()));

                String srt = "";

                for (Integer ePosicao : rep.getPosicoes()) {
                    srt += ePosicao + " ";
                }

                System.out.println("Quantidade :: " + rep.getPosicoes().size() + " -->> " + srt);

            }
        }

    }


    public static void converter(String eArquivoData,String eArquivoAU) {

        AreaBinaria ab = new AreaBinaria();


        RepetidorBinario rb = new RepetidorBinario();

        Arquivador arquivador = new Arquivador(eArquivoData, "r");
        long eTamanho = arquivador.getLength();

        arquivador.encerrar();


        System.out.println("Tamanho Total      :: " + eTamanho);

        long comecar = 0;
        long terminar = eTamanho;
        int i = 0;

        long s_total = 0;
        long s_repeticoes = 0;
        int rep_contagem = 0;

        int mostrar = 0;

        Arquivador au_arquivador = new Arquivador(eArquivoAU, "rw");

        int TAMANHO_BUFFER = 256;

        while (comecar < terminar) {

            ArrayList<Integer> z_Bloco = ab.getAreaGrande(eArquivoData, comecar, TAMANHO_BUFFER);

            ArrayList<RepeticaoBinaria> z_repeticoes = rb.getRepeticoes(z_Bloco);


            rep_contagem = 0;
            int chaves = 0;

            for (RepeticaoBinaria rep : z_repeticoes) {
                if (rep.getQuantidade() > 1) {
                    // System.out.println("REP :: " + rep.getValor() + " -->> " + rep.getQuantidade());
                    rep_contagem += rep.getQuantidade();
                    chaves += 1;
                }
            }

            if (rep_contagem == 0) {

                // au_arquivador.writeByte((byte) 100);
                au_arquivador.writeByte((byte) 0);

                for (RepeticaoBinaria rep : z_repeticoes) {
                    au_arquivador.writeByte((byte) rep.getB1());
                    au_arquivador.writeByte((byte) rep.getB2());
                }

            } else {

                // au_arquivador.writeByte((byte) 200);

                au_arquivador.writeByte((byte) chaves);

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() > 1) {
                        au_arquivador.writeByte((byte) rep.getB1());
                        au_arquivador.writeByte((byte) rep.getB2());

                        au_arquivador.writeByte((byte) rep.getPosicoes().size());

                        for (Integer ePosicao : rep.getPosicoes()) {
                            au_arquivador.writeByte((byte) ((int) ePosicao));
                        }

                    }
                }

                int originais = 0;

                for (RepeticaoBinaria rep : z_repeticoes) {
                    if (rep.getQuantidade() == 1) {
                        au_arquivador.writeByte((byte) rep.getB1());
                        au_arquivador.writeByte((byte) rep.getB2());
                        originais += 1;
                    }
                }


               // debug(z_repeticoes, rep_contagem, originais);
                //ab.mostrar(z_Bloco);

                // System.out.println("-------");
                //break;
            }


            long eFalta = eTamanho - ((comecar + z_Bloco.size()));

            if (mostrar >= 100 || ((comecar + (TAMANHO_BUFFER)) >= terminar)) {

                int dobro = rep_contagem + rep_contagem;

                System.out.println("Bloco  " + i);
                System.out.println("\t- Tamanho    :: " + z_Bloco.size() + " -->> " + comecar + " ate " + (comecar + z_Bloco.size()) + " :: " + eFalta);
                System.out.println("\t- Repeticoes :: " + dobro);
                System.out.println("\t- Final      :: " + (z_Bloco.size() - dobro));

                mostrar = 0;
            }
            mostrar += 1;


            s_total += z_Bloco.size();
            s_repeticoes += rep_contagem;

            comecar += (TAMANHO_BUFFER);
            i += 1;
        }

        au_arquivador.encerrar();


        System.out.println("");
        System.out.println("FINALMENTE");
        System.out.println("");
        System.out.println("\t- Total      :: " + s_total);
        System.out.println("\t- Repeticoes :: " + s_repeticoes);
        System.out.println("\t- Final      :: " + (s_total - s_repeticoes));

    }

}
