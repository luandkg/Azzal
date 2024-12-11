package projetos.aoc2024;

import apps.app_attuz.Ferramentas.Espaco2D;
import libs.azzal.geometria.Ponto;
import libs.luan.*;

public class AOC_2024_DAY_08 extends AOC_2024_DAY {

    public AOC_2024_DAY_08() {
        super(8, "");
    }

    @Override
    public void parte_1() {

        setParte(AOC_2024.PARTE_1);

        AOC_2024.CABECALHO(getProblemaNumero(), getParte());

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(getArquivoEntrada());
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        Tabuleiro<String> tabuleiro_antenas = new Tabuleiro<String>();

        tabuleiro_antenas.parser(texto_entrada);

        tabuleiro_antenas.exibir();

        Unico<Par<String, Lista<Ponto>>> antenas = new Unico<Par<String, Lista<Ponto>>>(new Igualavel<Par<String, Lista<Ponto>>>() {
            @Override
            public boolean is(Par<String, Lista<Ponto>> a, Par<String, Lista<Ponto>> b) {
                return Strings.isIgual(a.getChave(), b.getChave());
            }
        });

        for (int y = 0; y < tabuleiro_antenas.getLinhasQuantidade(); y++) {
            for (int x = 0; x < tabuleiro_antenas.getLetrasQuantidadeDaLinha(y); x++) {

                String valor = tabuleiro_antenas.getValorCartesiano(x, y);

                if (Strings.isDiferente(".", valor) && Strings.isDiferente("#", valor)) {
                    Par<String, Lista<Ponto>> antena = antenas.item_get(new Par<String, Lista<Ponto>>(valor, new Lista<Ponto>()));
                    antena.getValor().adicionar(new Ponto(x, y));
                }

            }
        }

        fmt.print("--------------- Antenas -----------------");

        int a = 0;

        Tabuleiro<String> tabuleiro_sinal_original = tabuleiro_antenas.getCopia();

        Unico<Par<Ponto,Integer>> pontos_marcados =new Unico<Par<Ponto,Integer>>(new Igualavel<Par<Ponto, Integer>>() {
            @Override
            public boolean is(Par<Ponto, Integer> a, Par<Ponto, Integer> b) {
                return Ponto.IGUAL().is(a.getChave(),b.getChave());
            }
        });



        int sinal_total_contagem = 0;

        for (Par<String, Lista<Ponto>> antena : antenas) {
            fmt.print("\t ++ Antenas Com Sinal : {}", antena.getChave());

            Tabuleiro<String> tabuleiro_sinal = tabuleiro_sinal_original.getCopia();

            for (Ponto pos : antena.getValor()) {
                fmt.print("\t\t >> {}:{}", pos.getX(), pos.getY());
            }

            fmt.print("\t\t ----------- Ressonancia");

            Unico<Par<Ponto, Ponto>> ressonancia = new Unico<Par<Ponto, Ponto>>(new Igualavel<Par<Ponto, Ponto>>() {
                @Override
                public boolean is(Par<Ponto, Ponto> a, Par<Ponto, Ponto> b) {
                    return a.getChave().isIgual(b.getValor()) && a.getValor().isIgual(b.getChave());
                }
            });

            for (Ponto p1 : antena.getValor()) {
                for (Ponto p2 : antena.getValor()) {
                    if (p1.isDiferente(p2)) {
                        ressonancia.item(new Par<Ponto, Ponto>(p1, p2));
                    }
                }
            }

            for (Par<Ponto, Ponto> par : ressonancia) {


                Ponto p1 = par.getChave();
                Ponto p2 = par.getValor();


                int diff_x = (p2.getX() - p1.getX());
                int diff_y = (p2.getY() - p1.getY());

                fmt.print("\t\t  ++ Diff(x) = {}", diff_x);
                fmt.print("\t\t  ++ Diff(y) = {}", diff_y);


                int distancia = Matematica.POSITIVO(Espaco2D.distancia_entre_pontos(p2, p1));
                fmt.print("\t\t >> {}:{} com {}:{} -->> {}", par.getChave().getX(), par.getChave().getY(), par.getValor().getX(), par.getValor().getY(), distancia);

                fmt.print("\t\t ++ ORDEM {}:{} com {}:{} -->> {}", p1.getX(), p1.getY(), p2.getX(), p2.getY(), distancia);

                double aa = ((double) diff_y / (double) diff_x);

                diff_x = Matematica.POSITIVO(diff_x);
                diff_y = Matematica.POSITIVO(diff_y);

                fmt.print("\t\t  ++ A(p1,p2) = {}", aa);

                tabuleiro_antenas.setValorCartesiano( p1.getX() , p1.getY(), "!");
                tabuleiro_antenas.setValorCartesiano( p2.getX() , p2.getY(), "!");

                if (aa >= 0) {

                    if (p1.getY() >= p2.getY()) {
                        se_diferente(tabuleiro_sinal, p1.getX() + diff_x, p1.getY() + diff_y, "#");
                        se_diferente(tabuleiro_sinal, p2.getX() - diff_x, p2.getY() - diff_y, "#");
                    } else {
                        se_diferente(tabuleiro_sinal, p1.getX() - diff_x, p1.getY() - diff_y, "#");
                        se_diferente(tabuleiro_sinal, p2.getX() + diff_x, p2.getY() + diff_y, "#");
                    }

                } else {

                    if (p1.getY() >= p2.getY()) {
                        se_diferente(tabuleiro_sinal, p1.getX() - diff_x, p1.getY() + diff_y, "#");
                        se_diferente(tabuleiro_sinal, p2.getX() + diff_x, p2.getY() - diff_y, "#");
                    } else {
                        se_diferente(tabuleiro_sinal, p1.getX() + diff_x, p1.getY() - diff_y, "#");
                        se_diferente(tabuleiro_sinal, p2.getX() - diff_x, p2.getY() + diff_y, "#");
                    }

                }


            }


            fmt.print("--------------  Sinal");
            tabuleiro_sinal.exibir();

            int contagem = 0;

            for (int y = 0; y < tabuleiro_sinal.getLinhasQuantidade(); y++) {
                for (int x = 0; x < tabuleiro_sinal.getLetrasQuantidadeDaLinha(y); x++) {

                    String valor = tabuleiro_sinal.getValorCartesiano(x, y);

                    if (Strings.isIgual("#", valor)) {

                        se_diferente(tabuleiro_antenas, x, y, "#");
                        Par<Ponto,Integer> marcado=  pontos_marcados.item_get(new Par<Ponto,Integer>(new Ponto(x,y),0));

                        marcado.set(marcado.getChave(),marcado.getValor()+1);

                        contagem += 1;
                    }

                }
            }

            sinal_total_contagem += contagem;

            fmt.print("\t\t  ++ Sinal = {}", contagem);


        }

        tabuleiro_antenas.exibir();

        int total = 0;

        for(Indexado<Par<Ponto,Integer>> p : Indexamento.indexe(pontos_marcados.toLista())){
          //  fmt.print("\t ++ {} -->> {} :: {}",p.index()+1,p.get().getChave().toString(),p.get().getValor());
            total+=p.get().getValor();
        }


        info(getParte(), pontos_marcados.getQuantidade());


    }


    public void se_diferente(Tabuleiro<String> tabuleiro_antenas, int pos_x, int pos_y, String valor) {

        if (tabuleiro_antenas.posicao_valida_cartesiana(pos_x, pos_y)) {
           // if (Strings.isIgual(tabuleiro_antenas.getValorCartesiano(pos_x, pos_y), ".")) {
                tabuleiro_antenas.setValorCartesiano(pos_x, pos_y, valor);
          //  }
        }

    }

    @Override
    public void parte_2() {

    }
}
