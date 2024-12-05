package projetos.aoc2024;

import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.Texto;
import libs.luan.fmt;

public class AOC_2024_DAY_04 extends AOC_2024_DAY {

    public AOC_2024_DAY_04() {
        super(4, "Ceres Search");
    }

    class Letra {

        public int linha = 0;
        public int coluna = 0;
        public String letra = "";
        private boolean descoberto = false;

        public Letra(int eLinha, int eColuna, String eLetra) {
            linha = eLinha;
            coluna = eColuna;
            letra = eLetra;
            descoberto = false;
        }

    }

    class Tabuleiro {

        public Lista<Lista<Letra>> mLinhas = new Lista<Lista<Letra>>();


        public String getLetra(int linha, int coluna) {
            return mLinhas.get(linha).get(coluna).letra;
        }

        public void marque_letra(int linha, int coluna, boolean marcado) {
            mLinhas.get(linha).get(coluna).descoberto = marcado;
        }

        public void criar_linha() {
            mLinhas.adicionar(new Lista<Letra>());
        }

        public void adicionar_letra(String letra) {
            mLinhas.getUltimoValor().adicionar(new Letra(mLinhas.getQuantidade(), mLinhas.getUltimoValor().getQuantidade(), letra));
        }

        public void exibir() {
            for (Lista<Letra> linha : mLinhas) {
                String s_linha = "";
                for (Letra letra : linha) {
                    s_linha += letra.letra + " ";
                }
                fmt.print("{}", s_linha);
            }
        }

        public void exibir_descobertos() {
            for (Lista<Letra> linha : mLinhas) {
                String s_linha = "";
                for (Letra letra : linha) {
                    if (letra.descoberto) {
                        s_linha += letra.letra + " ";
                    } else {
                        s_linha += "." + " ";
                    }
                }
                fmt.print("{}", s_linha);
            }
        }


        public int getLinhasQuantidade() {
            return mLinhas.getQuantidade();
        }

        public int getLetrasQuantidadeDaLinha(int linha) {
            return mLinhas.get(linha).getQuantidade();
        }

        public boolean posicao_valida(int linha, int coluna) {
            if (linha >= 0 && linha < mLinhas.getQuantidade()) {
                if (coluna >= 0 && coluna < mLinhas.get(linha).getQuantidade()) {
                    return true;
                }
            }
            return false;
        }

        public void marcar_letras_horizontalmente(int linha, int coluna, int quantidade) {

            for (int i = coluna; i < coluna + quantidade; i++) {
                marque_letra(linha, i, true);
            }

        }

        public void marcar_letras_verticalmente(int linha, int coluna, int quantidade) {


            for (int i = linha; i < linha + quantidade; i++) {
                marque_letra(i, coluna, true);

            }

        }


        public void marque_letras_diagonalmente_abaixo_direita(int linha, int coluna, int quantidade) {

            for (int i = 0; i < quantidade; i++) {
                marque_letra(linha + i, coluna + i, true);

            }

        }

        public void marque_letras_diagonalmente_abaixo_esquerda(int linha, int coluna, int quantidade) {

            for (int i = 0; i < quantidade; i++) {
                marque_letra(linha + i, coluna - i, true);
            }

        }


        public String getLetrasHorizontalmente(int linha, int coluna, int quantidade) {

            String ret = "";

            for (int i = coluna; i < coluna + quantidade; i++) {
                ret += getLetra(linha, i);
            }

            return ret;
        }

        public String getLetrasVerticalmente(int linha, int coluna, int quantidade) {

            String ret = "";

            for (int i = linha; i < linha + quantidade; i++) {
                ret += getLetra(i, coluna);
            }

            return ret;
        }

        public String getLetrasDiagonalmenteAbaixoDireita(int linha, int coluna, int quantidade) {

            String ret = "";

            for (int i = 0; i < quantidade; i++) {
                ret += getLetra(linha + i, coluna + i);
            }

            return ret;
        }

        public String getLetrasDiagonalmenteAbaixoEsquerda(int linha, int coluna, int quantidade) {

            String ret = "";

            for (int i = 0; i < quantidade; i++) {
                ret += getLetra(linha + i, coluna - i);
            }

            return ret;
        }

    }


    @Override
    public void parte_1() {

        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_04.txt"));
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        Tabuleiro tabuleiro = new Tabuleiro();

        for (String linha : Strings.DIVIDIR_LINHAS(texto_entrada)) {

            tabuleiro.criar_linha();

            for (String letra : Strings.GET_LETRAS(linha)) {
                tabuleiro.adicionar_letra(letra);
            }

        }

        tabuleiro.exibir();

        int xmas_contagem = 0;

        for (int linha = 0; linha < tabuleiro.getLinhasQuantidade(); linha++) {
            for (int coluna = 0; coluna < tabuleiro.getLetrasQuantidadeDaLinha(linha); coluna++) {
                String letra = tabuleiro.getLetra(linha, coluna);

                if (Strings.isIgual(letra, "X")) {

                    if (tabuleiro.posicao_valida(linha, coluna + 3)) {

                        String palavra = tabuleiro.getLetrasHorizontalmente(linha, coluna, 4);
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS HORIZONTAL DIRETO  ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_letras_horizontalmente(linha, coluna, 4);
                        }

                    }
                    if (tabuleiro.posicao_valida(linha + 3, coluna)) {

                        String palavra = tabuleiro.getLetrasVerticalmente(linha, coluna, 4);
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS VERTICAL DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_letras_verticalmente(linha, coluna, 4);
                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna + 3)) {

                        String palavra = tabuleiro.getLetrasDiagonalmenteAbaixoDireita(linha, coluna, 4);
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO DIREITA DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marque_letras_diagonalmente_abaixo_direita(linha, coluna, 4);
                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna - 3)) {

                        String palavra = tabuleiro.getLetrasDiagonalmenteAbaixoEsquerda(linha, coluna, 4);
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO ESQUERDA DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                        }

                    }


                } else if (Strings.isIgual(letra, "S")) {

                    if (tabuleiro.posicao_valida(linha, coluna + 3)) {

                        String palavra = Strings.REVERSE(tabuleiro.getLetrasHorizontalmente(linha, coluna, 4));

                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS HORIZONTAL REVERSO ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_letras_horizontalmente(linha, coluna, 4);

                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna)) {

                        String palavra = Strings.REVERSE(tabuleiro.getLetrasVerticalmente(linha, coluna, 4));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS VERTICAL REVERSO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marcar_letras_verticalmente(linha, coluna, 4);
                        }
                    }


                    if (tabuleiro.posicao_valida(linha + 3, coluna + 3)) {

                        String palavra = Strings.REVERSE(tabuleiro.getLetrasDiagonalmenteAbaixoDireita(linha, coluna, 4));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO DIREITA REVERSO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marque_letras_diagonalmente_abaixo_direita(linha, coluna, 4);
                        }

                    }

                    if (tabuleiro.posicao_valida(linha + 3, coluna - 3)) {

                        String palavra = Strings.REVERSE(tabuleiro.getLetrasDiagonalmenteAbaixoEsquerda(linha, coluna, 4));
                        if (Strings.isIgual(palavra, "XMAS")) {
                            fmt.print("XMAS DIAGONAL ABAIXO ESQUERDA DIRETO    ->> {}:{}", linha, coluna);
                            xmas_contagem += 1;
                            tabuleiro.marque_letras_diagonalmente_abaixo_esquerda(linha, coluna, 4);
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

        Tabuleiro tabuleiro = new Tabuleiro();

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
                String letra = tabuleiro.getLetra(linha, coluna);

                if (Strings.isIgual(letra, "A") ) {


                    int d_linha = linha-1;
                    int d_coluna=coluna-1;

                    int b_linha = linha-1;
                    int b_coluna=coluna+1;

                    boolean lado_a = false;
                    boolean lado_b=false;



                    fmt.print("AA : {}::{}",linha,coluna);

                    if (tabuleiro.posicao_valida(d_linha, d_coluna) && tabuleiro.posicao_valida(d_linha+2, d_coluna+2)) {

                        String palavra = tabuleiro.getLetrasDiagonalmenteAbaixoDireita(d_linha, d_coluna, 3);
                        //fmt.print("{}:{} -- Enc :: {}",linha,coluna,palavra);

                        fmt.print("Aqui D : {}::{}",b_linha,b_coluna);

                        if (Strings.isIgual(letra, "A") && (Strings.isIgual(palavra, PALAVRA_CHAVE)||Strings.isIgual(palavra, PALAVRA_CHAVE_REVERSA) ) ) {
                            fmt.print("MAS DIAGONAL ABAIXO DIREITA     ->> {}:{}", d_linha, d_coluna);
                            lado_a = true;
                        }

                    }

                    if (tabuleiro.posicao_valida(b_linha, b_coluna) && tabuleiro.posicao_valida(b_linha+2, b_coluna-2)) {

                        fmt.print("Aqui E : {}::{}",b_linha,b_coluna);
                        String palavra = tabuleiro.getLetrasDiagonalmenteAbaixoEsquerda(b_linha, b_coluna, 3);
                        fmt.print("{}:{} -- Enc ED :: {}",linha,coluna,palavra);


                        if (Strings.isIgual(letra, "A") && (Strings.isIgual(palavra, PALAVRA_CHAVE)||Strings.isIgual(palavra, PALAVRA_CHAVE_REVERSA) ) ) {
                            fmt.print("MAS DIAGONAL ABAIXO ESQUERDA    ->> {}:{}", b_linha, b_coluna);
                            lado_b = true;
                        }

                    }

                    fmt.print("A ->> {}::{} com {}:{}",linha,coluna,lado_a,lado_b);

                    if(lado_a && lado_b ) {
                        tabuleiro.marque_letras_diagonalmente_abaixo_direita(d_linha, d_coluna, 3);
                        tabuleiro.marque_letras_diagonalmente_abaixo_esquerda(b_linha, b_coluna, 3);
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
