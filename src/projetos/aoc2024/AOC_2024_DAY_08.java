package projetos.aoc2024;

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
        String texto_entrada = Texto.arquivo_ler(getArquivoDica());
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

        UnicoComContador<Ponto> pontos_marcados = new UnicoComContador<Ponto>(Ponto.IGUAL());

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

                Ponto ponto_diff = Ponto.DIFERENCA(p1, p2);


                fmt.print("");
                fmt.print("\t\t  ++ Diff(x) = {}", ponto_diff.getX());
                fmt.print("\t\t  ++ Diff(y) = {}", ponto_diff.getY());


                int distancia = Matematica.POSITIVO(Ponto.DISTANCIA(p2, p1));
                fmt.print("\t\t >> {}:{} com {}:{} -->> {}", p1.getX(), p1.getY(), p2.getX(), p2.getY(), distancia);


                int diff_x = Matematica.POSITIVO(ponto_diff.getX());
                int diff_y = Matematica.POSITIVO(ponto_diff.getY());

                double inclinacao = Ponto.INCLINACAO(p1, p2);

                fmt.print("\t\t ++ A(p1,p2) = {}", inclinacao);

                tabuleiro_antenas.setValorCartesiano(p1.getX(), p1.getY(), "!");
                tabuleiro_antenas.setValorCartesiano(p2.getX(), p2.getY(), "!");

                if (inclinacao >= 0) {

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

            Lista<Ponto> pontos = tabuleiro_sinal.getPontosCartesianosComValor(Strings.IGUALAVEL(), "#");

            for (Ponto ponto : pontos) {
                se_diferente(tabuleiro_antenas, ponto.getX(), ponto.getY(), "#");
                pontos_marcados.conte(ponto);
                contagem += 1;
            }


            fmt.print("\t\t  ++ Sinal = {}", contagem);


        }

        tabuleiro_antenas.exibir();


        for (Indexado<Par<Ponto, Integer>> p : Indexamento.indexe(pontos_marcados.toLista())) {
            //  fmt.print("\t ++ {} -->> {} :: {}",p.index()+1,p.get().getChave().toString(),p.get().getValor());
        }


        info(getParte(), pontos_marcados.getQuantidade());


    }


    public void se_diferente(Tabuleiro<String> tabuleiro_antenas, int pos_x, int pos_y, String valor) {

        if (tabuleiro_antenas.posicao_valida_cartesiana(pos_x, pos_y)) {
            tabuleiro_antenas.setValorCartesiano(pos_x, pos_y, valor);
        }

    }


    public boolean propagar_antinodes(Tabuleiro<String> tabuleiro_antenas, int pos_x, int pos_y, int mais_x, int mais_y, String valor) {

        int novo_px = pos_x + mais_x;
        int novo_py = pos_y + mais_y;

        if (tabuleiro_antenas.posicao_valida_cartesiana(novo_px, novo_py)) {

            if (Strings.isIgual(tabuleiro_antenas.getValorCartesiano(novo_px, novo_py), ".")) {
                tabuleiro_antenas.setValorCartesiano(novo_px, novo_py, valor);
            }

            propagar_antinodes(tabuleiro_antenas, novo_px, novo_py, mais_x, mais_y, valor);

            return true;
        }

        return false;
    }


    @Override
    public void parte_2() {

        setParte(AOC_2024.PARTE_2);

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

        UnicoComContador<Ponto> pontos_marcados = new UnicoComContador<Ponto>(Ponto.IGUAL());

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

                Ponto ponto_diff = Ponto.DIFERENCA(p1, p2);


                fmt.print("");
                fmt.print("\t\t  ++ Diff(x) = {}", ponto_diff.getX());
                fmt.print("\t\t  ++ Diff(y) = {}", ponto_diff.getY());


                int distancia = Matematica.POSITIVO(Ponto.DISTANCIA(p2, p1));
                fmt.print("\t\t >> {}:{} com {}:{} -->> {}", p1.getX(), p1.getY(), p2.getX(), p2.getY(), distancia);


                int diff_x = Matematica.POSITIVO(ponto_diff.getX());
                int diff_y = Matematica.POSITIVO(ponto_diff.getY());

                double inclinacao = Ponto.INCLINACAO(p1, p2);

                fmt.print("\t\t ++ A(p1,p2) = {}", inclinacao);

                tabuleiro_antenas.setValorCartesiano(p1.getX(), p1.getY(), "!");
                tabuleiro_antenas.setValorCartesiano(p2.getX(), p2.getY(), "!");

                if (inclinacao >= 0) {

                    if (p1.getY() >= p2.getY()) {
                        propagar_antinodes(tabuleiro_sinal, p1.getX(), p1.getY(), +diff_x, +diff_y, "#");
                        propagar_antinodes(tabuleiro_sinal, p2.getX(), p2.getY(), -diff_x, -diff_y, "#");
                    } else {
                        propagar_antinodes(tabuleiro_sinal, p1.getX(), p1.getY(), -diff_x, -diff_y, "#");
                        propagar_antinodes(tabuleiro_sinal, p2.getX(), p2.getY(), diff_x, diff_y, "#");
                    }

                } else {

                    if (p1.getY() >= p2.getY()) {
                        propagar_antinodes(tabuleiro_sinal, p1.getX(), p1.getY(), -diff_x, +diff_y, "#");
                        propagar_antinodes(tabuleiro_sinal, p2.getX(), p2.getY(), +diff_x, -diff_y, "#");
                    } else {
                        propagar_antinodes(tabuleiro_sinal, p1.getX(), p1.getY(), +diff_x, -diff_y, "#");
                        propagar_antinodes(tabuleiro_sinal, p2.getX(), p2.getY(), -diff_x, +diff_y, "#");
                    }

                }


            }


            fmt.print("--------------  Sinal");
            tabuleiro_sinal.exibir();

            int contagem = 0;

            Lista<Ponto> pontos = tabuleiro_sinal.getPontosCartesianosComValor(Strings.IGUALAVEL(), "#");

            for (Ponto ponto : pontos) {
                se_diferente(tabuleiro_antenas, ponto.getX(), ponto.getY(), "#");
                pontos_marcados.conte(ponto);
                contagem += 1;
            }


            fmt.print("\t\t  ++ Sinal = {}", contagem);


        }

        tabuleiro_antenas.exibir();


        for (Indexado<Par<Ponto, Integer>> p : Indexamento.indexe(pontos_marcados.toLista())) {
        //    fmt.print("\t ++ {} -->> {} :: {}", p.index() + 1, p.get().getChave().toString(), p.get().getValor());
        }

        int antenas_originais = 0;
        for (Par<String, Lista<Ponto>> antena : antenas) {
            antenas_originais += antena.getValor().getQuantidade();
        }


        info(getParte(), pontos_marcados.getQuantidade() + antenas_originais);


    }

}
