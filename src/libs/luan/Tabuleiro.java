package libs.luan;

public class Tabuleiro<T> {


    public class Letra<T> {

        public int linha = 0;
        public int coluna = 0;
        public T letra = null;
        private boolean descoberto = false;

        public Letra(int eLinha, int eColuna, T eLetra) {
            linha = eLinha;
            coluna = eColuna;
            letra = eLetra;
            descoberto = false;
        }

    }

    public Lista<Lista<Letra<T>>> mLinhas = new Lista<Lista<Letra<T>>>();


    public Letra<T> get(int linha, int coluna) {
        return mLinhas.get(linha).get(coluna);
    }

    public void marque_letra(int linha, int coluna, boolean marcado) {
        mLinhas.get(linha).get(coluna).descoberto = marcado;
    }

    public void criar_linha() {
        mLinhas.adicionar(new Lista<Letra<T>>());
    }

    public void adicionar_letra(T letra) {
        mLinhas.getUltimoValor().adicionar(new Letra<T>(mLinhas.getQuantidade(), mLinhas.getUltimoValor().getQuantidade(), letra));
    }

    public void adicionar_linha(Lista<T> lista){
        criar_linha();

        for (T letra :lista) {
            adicionar_letra(letra);
        }
    }

    public void exibir() {
        for (Lista<Letra<T>> linha : mLinhas) {
            String s_linha = "";
            for (Letra<T> letra : linha) {
                s_linha += letra.letra + " ";
            }
            fmt.print("{}", s_linha);
        }
    }

    public void exibir_descobertos() {
        for (Lista<Letra<T>> linha : mLinhas) {
            String s_linha = "";
            for (Letra<T> letra : linha) {
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


    public Lista<T> getLetrasHorizontalmente(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = coluna; i < coluna + quantidade; i++) {
            ret.adicionar(get(linha, i).letra);
        }

        return ret;
    }

    public Lista<T> getLetrasVerticalmente(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = linha; i < linha + quantidade; i++) {
            ret.adicionar(get(i, coluna).letra);
        }

        return ret;
    }

    public Lista<T> getLetrasDiagonalmenteAbaixoDireita(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = 0; i < quantidade; i++) {
            ret.adicionar(get(linha + i, coluna + i).letra);
        }

        return ret;
    }

    public Lista<T> getLetrasDiagonalmenteAbaixoEsquerda(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = 0; i < quantidade; i++) {
            ret.adicionar(get(linha + i, coluna - i).letra);
        }

        return ret;
    }


    public static <T> String TRANSFORMAR_STRING(Lista<T> lista) {
        String ret = "";
        for (T item : lista) {
            ret += String.valueOf(item);
        }
        return ret;
    }

}