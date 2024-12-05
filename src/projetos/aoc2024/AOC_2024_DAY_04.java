package projetos.aoc2024;

import libs.luan.Strings;
import libs.luan.Tabuleiro;
import libs.luan.Texto;
import libs.luan.fmt;

public class AOC_2024_DAY_04 extends AOC_2024_DAY {

    public AOC_2024_DAY_04() {
        super(4, "Ceres Search");
    }


    @Override
    public void parte_1() {

        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_04.txt"));
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        Tabuleiro<String> tabuleiro = new Tabuleiro<String>();

        for (String linha : Strings.DIVIDIR_LINHAS(texto_entrada)) {

            tabuleiro.adicionar_linha(Strings.GET_LETRAS(linha));

        }

        tabuleiro.exibir();

        int xmas_contagem = 0;

        for (int linha = 0; linha < tabuleiro.getLinhasQuantidade(); linha++) {
            for (int coluna = 0; coluna < tabuleiro.getLetrasQuantidadeDaLinha(linha); coluna++) {
                String letra = tabuleiro.getValor(linha, coluna);

                if (Strings.isIgual(letra, "X")) {

                    if (tabuleiro.posicao_valida(linha, coluna + 3)) {

                        String palavra = Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getHorizontalmente(linha, coluna, 4));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS HORIZONTAL DIRETO  ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_horizontalmente(linha, coluna, 4);
                        }

                    }
                    if (tabuleiro.posicao_valida(linha + 3, coluna)) {

                        String palavra = Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getVerticalmente(linha, coluna, 4));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS VERTICAL DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_verticalmente(linha, coluna, 4);
                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna + 3)) {

                        String palavra = Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getDiagonalmenteAbaixoDireita(linha, coluna, 4));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO DIREITA DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marque_diagonalmente_abaixo_direita(linha, coluna, 4);
                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna - 3)) {

                        String palavra = Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getDiagonalmenteAbaixoEsquerda(linha, coluna, 4));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO ESQUERDA DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marque_diagonalmente_abaixo_esquerda(linha, coluna, 4);
                        }

                    }


                } else if (Strings.isIgual(letra, "S")) {

                    if (tabuleiro.posicao_valida(linha, coluna + 3)) {

                        String palavra = Strings.REVERSE(Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getHorizontalmente(linha, coluna, 4)));

                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS HORIZONTAL REVERSO ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_horizontalmente(linha, coluna, 4);

                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna)) {

                        String palavra = Strings.REVERSE(Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getVerticalmente(linha, coluna, 4)));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS VERTICAL REVERSO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_verticalmente(linha, coluna, 4);
                        }
                    }


                    if (tabuleiro.posicao_valida(linha + 3, coluna + 3)) {

                        String palavra = Strings.REVERSE(Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getDiagonalmenteAbaixoDireita(linha, coluna, 4)));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO DIREITA REVERSO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marque_diagonalmente_abaixo_direita(linha, coluna, 4);
                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna - 3)) {

                        String palavra = Strings.REVERSE(Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getDiagonalmenteAbaixoEsquerda(linha, coluna, 4)));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO ESQUERDA DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marque_diagonalmente_abaixo_esquerda(linha, coluna, 4);
                        }

                    }


                }
            }
        }

        tabuleiro.exibir_descobertos();

        fmt.print("");
        fmt.print("XMAS total = {}", xmas_contagem);


    }

    @Override
    public void parte_2() {

        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_04.txt"));
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        Tabuleiro<String> tabuleiro = new Tabuleiro<String>();

        for (String linha : Strings.DIVIDIR_LINHAS(texto_entrada)) {

            tabuleiro.criar_linha();

            for (String letra : Strings.GET_LETRAS(linha)) {
                tabuleiro.adicionar_letra(letra);
            }

        }

        tabuleiro.exibir();

        int xmas_contagem = 0;

        String PALAVRA_CHAVE = "MAS";
        String PALAVRA_CHAVE_REVERSA = Strings.REVERSE(PALAVRA_CHAVE);

        for (int linha = 0; linha < tabuleiro.getLinhasQuantidade(); linha++) {

            for (int coluna = 0; coluna < tabuleiro.getLetrasQuantidadeDaLinha(linha); coluna++) {
                String letra = tabuleiro.getValor(linha, coluna);

                if (Strings.isIgual(letra, "A")) {


                    int d_linha = linha - 1;
                    int d_coluna = coluna - 1;

                    int b_linha = linha - 1;
                    int b_coluna = coluna + 1;

                    boolean lado_a = false;
                    boolean lado_b = false;


                    fmt.print("AA : {}::{}", linha, coluna);

                    if (tabuleiro.posicao_valida(d_linha, d_coluna) && tabuleiro.posicao_valida(d_linha + 2, d_coluna + 2)) {

                        String palavra = Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getDiagonalmenteAbaixoDireita(d_linha, d_coluna, 3));
                        //fmt.print("{}:{} -- Enc :: {}",linha,coluna,palavra);

                        fmt.print("Aqui D : {}::{}", b_linha, b_coluna);

                        if (Strings.isIgual(letra, "A") && (Strings.isIgual(palavra, PALAVRA_CHAVE) || Strings.isIgual(palavra, PALAVRA_CHAVE_REVERSA))) {
                            fmt.print("MAS DIAGONAL ABAIXO DIREITA     ->> {}:{}", d_linha, d_coluna);
                            lado_a = true;
                        }

                    }

                    if (tabuleiro.posicao_valida(b_linha, b_coluna) && tabuleiro.posicao_valida(b_linha + 2, b_coluna - 2)) {

                        fmt.print("Aqui E : {}::{}", b_linha, b_coluna);
                        String palavra = Tabuleiro.TRANSFORMAR_STRING(tabuleiro.getDiagonalmenteAbaixoEsquerda(b_linha, b_coluna, 3));
                        fmt.print("{}:{} -- Enc ED :: {}", linha, coluna, palavra);


                        if (Strings.isIgual(letra, "A") && (Strings.isIgual(palavra, PALAVRA_CHAVE) || Strings.isIgual(palavra, PALAVRA_CHAVE_REVERSA))) {
                            fmt.print("MAS DIAGONAL ABAIXO ESQUERDA    ->> {}:{}", b_linha, b_coluna);
                            lado_b = true;
                        }

                    }

                    fmt.print("A ->> {}::{} com {}:{}", linha, coluna, lado_a, lado_b);

                    if (lado_a && lado_b) {
                        tabuleiro.marque_diagonalmente_abaixo_direita(d_linha, d_coluna, 3);
                        tabuleiro.marque_diagonalmente_abaixo_esquerda(b_linha, b_coluna, 3);
                        xmas_contagem += 1;
                    }


                }

            }
        }

        tabuleiro.exibir_descobertos();

        fmt.print("");
        fmt.print("X-MAS total = {}", xmas_contagem);

    }
}
