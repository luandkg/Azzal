package projetos.aoc2024;

import libs.azzal.geometria.Ponto;
import libs.luan.*;

public class AOC_2024_DAY_06 extends AOC_2024_DAY {

    public AOC_2024_DAY_06() {
        super(6, "Guard Gallivant");
    }

    @Override
    public void parte_1() {

        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(getArquivoEntrada());
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        Tabuleiro<String> tabuleiro = new Tabuleiro<String>();
        Tabuleiro<String> tabuleiro_passou = new Tabuleiro<String>();

        Lista<Lista<String>> inicial_conteudo = new Lista<Lista<String>>();

        for (String linha : Strings.DIVIDIR_LINHAS(texto_entrada)) {
            inicial_conteudo.adicionar(Strings.GET_LETRAS(linha));
        }

        tabuleiro.setTabuleiro(inicial_conteudo);
        tabuleiro_passou.setTabuleiro(inicial_conteudo);

        for (int y = 0; y < tabuleiro.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro.getLetrasQuantidadeDaLinha(y); x++) {
                tabuleiro_passou.setValorCartesiano(x, y, ".");
            }
        }

        tabuleiro.exibir();

        int pos_x = 0;
        int pos_y = 0;

        for (int y = 0; y < tabuleiro.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro.getLetrasQuantidadeDaLinha(y); x++) {

                String valor = tabuleiro.getValorCartesiano(x, y);

                if (Strings.isIgual(valor, "^") || Strings.isIgual(valor, ">") || Strings.isIgual(valor, "<") || Strings.isIgual(valor, "v")) {
                    pos_x = x;
                    pos_y = y;
                    tabuleiro_passou.setValorCartesiano(pos_x, pos_y, "X");
                    break;
                }
            }
        }


        fmt.print("Guarda -->> {} :: {}", pos_x, pos_y);

        int mov_x = 0;
        int mov_y = -1;


        int a = 0;

        while (tabuleiro.posicao_valida_cartesiana(pos_x, pos_y)) {

            fmt.print("--------- MOVIMENTAR ----------");

            tabuleiro.exibir();

            //  fmt.print("Guarda passou ...");
            // tabuleiro_passou.exibir();


            int nova_px = pos_x + mov_x;
            int nova_py = pos_y + mov_y;

            String guarda = tabuleiro.getValorCartesiano(pos_x, pos_y);

            if (!tabuleiro.posicao_valida_cartesiana(nova_px, nova_py)) {
                break;
            }

            String frente = tabuleiro.getValorCartesiano(nova_px, nova_py);


            if (Strings.isIgual(frente, ".")) {

                tabuleiro.setValorCartesiano(pos_x, pos_y, ".");
                tabuleiro.setValorCartesiano(nova_px, nova_py, guarda);

                pos_x += mov_x;
                pos_y += mov_y;

            } else if (Strings.isIgual(frente, "#")) {

                if (Strings.isIgual(guarda, "^")) {
                    guarda = ">";
                    mov_x = 1;
                    mov_y = 0;
                } else if (Strings.isIgual(guarda, ">")) {
                    guarda = "v";
                    mov_x = 0;
                    mov_y = 1;
                } else if (Strings.isIgual(guarda, "v")) {
                    guarda = "<";
                    mov_x = -1;
                    mov_y = 0;
                } else if (Strings.isIgual(guarda, "<")) {
                    guarda = "^";
                    mov_x = 0;
                    mov_y = -1;
                }

                tabuleiro.setValorCartesiano(pos_x, pos_y, guarda);

            }

            tabuleiro_passou.setValorCartesiano(pos_x, pos_y, "X");


            fmt.print("Guarda -->> {} :: {}", pos_x, pos_y);


        }

        int passou = 0;

        for (int y = 0; y < tabuleiro_passou.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro_passou.getLetrasQuantidadeDaLinha(y); x++) {

                String valor = tabuleiro_passou.getValorCartesiano(x, y);

                if (Strings.isIgual(valor, "X")) {
                    passou += 1;
                }
            }
        }


        info(AOC_2024.PARTE_1, passou);

    }


    @Override
    public void parte_2() {

        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_2);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(getArquivoEntrada());
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        Tabuleiro<String> tabuleiro = new Tabuleiro<String>();
        Tabuleiro<String> tabuleiro_passou = new Tabuleiro<String>();
        Tabuleiro<String> tabuleiro_contagem = new Tabuleiro<String>();

        Lista<Lista<String>> conteudo = new Lista<Lista<String>>();

        for (String linha : Strings.DIVIDIR_LINHAS(texto_entrada)) {

            conteudo.adicionar(Strings.GET_LETRAS(linha));

        }

        tabuleiro.setTabuleiro(conteudo);
        tabuleiro_passou.setTabuleiro(conteudo);
        tabuleiro_contagem.setTabuleiro(conteudo);

        tabuleiro.exibir();

        Tabuleiro<String> tabuleiro_inicial = tabuleiro.getCopia();


        tabuleiro.exibir();

        int pos_x = 0;
        int pos_y = 0;

        for (int y = 0; y < tabuleiro.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro.getLetrasQuantidadeDaLinha(y); x++) {

                String valor = tabuleiro.getValorCartesiano(x, y);

                if (Strings.isIgual(valor, "^") || Strings.isIgual(valor, ">") || Strings.isIgual(valor, "<") || Strings.isIgual(valor, "v")) {
                    pos_x = x;
                    pos_y = y;
                    tabuleiro_passou.setValorCartesiano(pos_x, pos_y, "X");
                    break;
                }
            }
        }


        fmt.print("Guarda -->> {} :: {}", pos_x, pos_y);

        int mov_x = 0;
        int mov_y = -1;

        tabuleiro_contagem.aplicar_todos("0");


        while (tabuleiro.posicao_valida_cartesiana(pos_x, pos_y)) {

            fmt.print("--------- MOVIMENTAR ----------");

            tabuleiro.exibir();

            fmt.print("Guarda passou ...");
            tabuleiro_passou.exibir();

            fmt.print("Contagem ...");
            tabuleiro_contagem.exibir();


            int nova_px = pos_x + mov_x;
            int nova_py = pos_y + mov_y;

            String guarda = tabuleiro.getValorCartesiano(pos_x, pos_y);

            if (!tabuleiro.posicao_valida_cartesiana(nova_px, nova_py)) {
                break;
            }

            String frente = tabuleiro.getValorCartesiano(nova_px, nova_py);

            String guarda_caminhante_valor = guarda;

            if (Strings.isIgual(frente, ".")) {

                tabuleiro.setValorCartesiano(pos_x, pos_y, ".");
                tabuleiro.setValorCartesiano(nova_px, nova_py, guarda);

                pos_x += mov_x;
                pos_y += mov_y;

            } else if (Strings.isIgual(frente, "#")) {

                if (Strings.isIgual(guarda, "^")) {
                    guarda = ">";
                    mov_x = 1;
                    mov_y = 0;
                } else if (Strings.isIgual(guarda, ">")) {
                    guarda = "v";
                    mov_x = 0;
                    mov_y = 1;
                } else if (Strings.isIgual(guarda, "v")) {
                    guarda = "<";
                    mov_x = -1;
                    mov_y = 0;
                } else if (Strings.isIgual(guarda, "<")) {
                    guarda = "^";
                    mov_x = 0;
                    mov_y = -1;
                }

                guarda_caminhante_valor = "+";

                tabuleiro.setValorCartesiano(pos_x, pos_y, guarda);

            }

            if (Strings.isIgual(tabuleiro_passou.getValorCartesiano(pos_x, pos_y), ".")) {
                tabuleiro_passou.setValorCartesiano(pos_x, pos_y, guarda_caminhante(guarda_caminhante_valor));
            } else {
                String novo = guarda_caminhante(guarda_caminhante_valor);
                if (Strings.isIgual(novo, "|") && Strings.isIgual(tabuleiro_passou.getValorCartesiano(pos_x, pos_y), "|")) {
                } else if (Strings.isIgual(novo, "-") && Strings.isIgual(tabuleiro_passou.getValorCartesiano(pos_x, pos_y), "-")) {

                } else {
                    tabuleiro_passou.setValorCartesiano(pos_x, pos_y, "+");
                }
            }

            tabuleiro_contagem.setValorCartesiano(pos_x, pos_y, String.valueOf(Integer.parseInt(tabuleiro_contagem.getValorCartesiano(pos_x, pos_y)) + 1));


            fmt.print("Guarda -->> {} :: {}", pos_x, pos_y);


        }



        fmt.print("Contagem ...");
        tabuleiro_contagem.exibir();

        Lista<Ponto> lugares_visitados = new Lista<Ponto>();
        int mais_passou_contagem = 0;

        for (int y = 0; y < tabuleiro_contagem.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro_contagem.getLetrasQuantidadeDaLinha(y); x++) {
                int passou = Integer.parseInt(tabuleiro_contagem.getValorCartesiano(x, y));
                if (passou > mais_passou_contagem) {
                    mais_passou_contagem = passou;
                }
                if (passou > 0) {
                    lugares_visitados.adicionar(new Ponto(x, y));
                }
            }
        }

        fmt.print("Passou várias vezes : {}", mais_passou_contagem);
        fmt.print("Lugares visitados   : {}", lugares_visitados.getQuantidade());

        int total_em_loop = 0;

        for (Indexado<Ponto> i_visitado : Indexamento.indexe(lugares_visitados)) {
            Ponto visitado = i_visitado.get();
            if (Strings.isIgual(tabuleiro_inicial.getValorCartesiano(visitado.getX(), visitado.getY()), ".")) {

                boolean em_loop = esta_em_loop(tabuleiro_inicial, visitado.getX(), visitado.getY());
                if (em_loop) {
                    total_em_loop += 1;
                }

                fmt.print("{} -- ( {} de {} ) -- Posicionar Obstáculo :: ( {}:{} ) ->> {}     Em Loop = {}    ----   {}", fmt.f2Porcentagem(i_visitado.index()+1,lugares_visitados.getQuantidade()),i_visitado.index() + 1, lugares_visitados.getQuantidade(), visitado.getX(), visitado.getY(), tabuleiro_inicial.getValorCartesiano(visitado.getX(), visitado.getY()), Portugues.VALIDAR(em_loop, "SIM", " - "),total_em_loop);

            }
        }


        info(AOC_2024.PARTE_2, total_em_loop);


    }

    public static String guarda_caminhante(String guarda) {
        if (Strings.isIgual(guarda, "^") || Strings.isIgual(guarda, "v")) {
            guarda = "|";
        } else if (Strings.isIgual(guarda, ">") || Strings.isIgual(guarda, "<")) {
            guarda = "-";
        }
        return guarda;
    }


    public static boolean esta_em_loop(Tabuleiro<String> original, int novo_obstaculo_x, int novo_obstaculo_y) {

        Tabuleiro<String> tabuleiro = original.getCopia();
        Tabuleiro<String> tabuleiro_passou = original.getCopia();
        Tabuleiro<String> tabuleiro_contagem = original.getCopia();

        tabuleiro.setValorCartesiano(novo_obstaculo_x, novo_obstaculo_y, "#");

        boolean em_loop = false;

        int pos_x = 0;
        int pos_y = 0;

        for (int y = 0; y < tabuleiro.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro.getLetrasQuantidadeDaLinha(y); x++) {

                String valor = tabuleiro.getValorCartesiano(x, y);

                if (Strings.isIgual(valor, "^") || Strings.isIgual(valor, ">") || Strings.isIgual(valor, "<") || Strings.isIgual(valor, "v")) {
                    pos_x = x;
                    pos_y = y;
                    tabuleiro_passou.setValorCartesiano(pos_x, pos_y, "X");
                    break;
                }
            }
        }


        //  fmt.print("Guarda -->> {} :: {}", pos_x, pos_y);

        int mov_x = 0;
        int mov_y = -1;


        for (int y = 0; y < tabuleiro_contagem.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro_contagem.getLetrasQuantidadeDaLinha(y); x++) {
                tabuleiro_contagem.setValorCartesiano(x, y, "0");
            }
        }

        while (tabuleiro.posicao_valida_cartesiana(pos_x, pos_y)) {

            // fmt.print("--------- MOVIMENTAR ----------");

            // tabuleiro.exibir();

            // fmt.print("Guarda passou ...");
            // tabuleiro_passou.exibir();

            // fmt.print("Contagem ...");
            //  tabuleiro_contagem.exibir();


            int nova_px = pos_x + mov_x;
            int nova_py = pos_y + mov_y;

            String guarda = tabuleiro.getValorCartesiano(pos_x, pos_y);

            if (!tabuleiro.posicao_valida_cartesiana(nova_px, nova_py)) {
                break;
            }

            String frente = tabuleiro.getValorCartesiano(nova_px, nova_py);

            String guarda_caminhante_valor = guarda;

            if (Strings.isIgual(frente, ".")) {

                tabuleiro.setValorCartesiano(pos_x, pos_y, ".");
                tabuleiro.setValorCartesiano(nova_px, nova_py, guarda);

                pos_x += mov_x;
                pos_y += mov_y;

            } else if (Strings.isIgual(frente, "#")) {

                if (Strings.isIgual(guarda, "^")) {
                    guarda = ">";
                    mov_x = 1;
                    mov_y = 0;
                } else if (Strings.isIgual(guarda, ">")) {
                    guarda = "v";
                    mov_x = 0;
                    mov_y = 1;
                } else if (Strings.isIgual(guarda, "v")) {
                    guarda = "<";
                    mov_x = -1;
                    mov_y = 0;
                } else if (Strings.isIgual(guarda, "<")) {
                    guarda = "^";
                    mov_x = 0;
                    mov_y = -1;
                }

                guarda_caminhante_valor = "+";

                tabuleiro.setValorCartesiano(pos_x, pos_y, guarda);

            }

            if (Strings.isIgual(tabuleiro_passou.getValorCartesiano(pos_x, pos_y), ".")) {
                tabuleiro_passou.setValorCartesiano(pos_x, pos_y, guarda_caminhante(guarda_caminhante_valor));
            } else {
                String novo = guarda_caminhante(guarda_caminhante_valor);

                if (Strings.isIgual(novo, "|") && Strings.isIgual(tabuleiro_passou.getValorCartesiano(pos_x, pos_y), "|")) {
                } else if (Strings.isIgual(novo, "-") && Strings.isIgual(tabuleiro_passou.getValorCartesiano(pos_x, pos_y), "-")) {
                } else {
                    tabuleiro_passou.setValorCartesiano(pos_x, pos_y, "+");
                }
            }

            tabuleiro_contagem.setValorCartesiano(pos_x, pos_y, String.valueOf(Integer.parseInt(tabuleiro_contagem.getValorCartesiano(pos_x, pos_y)) + 1));

            int passou_quantidade = Integer.parseInt(tabuleiro_contagem.getValorCartesiano(pos_x, pos_y));
            if (passou_quantidade >= 10) {
                em_loop = true;
                break;
            }

            //  fmt.print("Guarda -->> {} :: {}", pos_x, pos_y);

        }

        // fmt.print("Contagem ...");
        //  tabuleiro_contagem.exibir();

        return em_loop;
    }

}
