package libs.luan;

public class Tabuleiro<T> {

    public class Peca<T> {

        public int linha = 0;
        public int coluna = 0;
        public T mConteudo = null;
        private boolean descoberto = false;

        public Peca(int eLinha, int eColuna, T eLetra) {
            linha = eLinha;
            coluna = eColuna;
            mConteudo = eLetra;
            descoberto = false;
        }

    }

    public Lista<Lista<Peca<T>>> mLinhas = new Lista<Lista<Peca<T>>>();


    public Peca<T> get(int linha, int coluna) {
        return mLinhas.get(linha).get(coluna);
    }

    public T getValor(int linha,int coluna){
        return get(linha,coluna).mConteudo;
    }

    public void marque(int linha, int coluna, boolean marcado) {
        mLinhas.get(linha).get(coluna).descoberto = marcado;
    }

    public void criar_linha() {
        mLinhas.adicionar(new Lista<Peca<T>>());
    }

    public void adicionar_letra(T letra) {
        mLinhas.getUltimoValor().adicionar(new Peca<T>(mLinhas.getQuantidade(), mLinhas.getUltimoValor().getQuantidade(), letra));
    }

    public void adicionar_linha(Lista<T> lista){
        criar_linha();

        for (T letra :lista) {
            adicionar_letra(letra);
        }
    }

    public void exibir() {
        for (Lista<Peca<T>> linha : mLinhas) {
            String s_linha = "";
            for (Peca<T> letra : linha) {
                s_linha += letra.mConteudo + " ";
            }
            fmt.print("{}", s_linha);
        }
    }

    public void exibir_descobertos() {
        for (Lista<Peca<T>> linha : mLinhas) {
            String s_linha = "";
            for (Peca<T> letra : linha) {
                if (letra.descoberto) {
                    s_linha += letra.mConteudo + " ";
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

    public void marcar_horizontalmente(int linha, int coluna, int quantidade) {

        for (int i = coluna; i < coluna + quantidade; i++) {
            marque(linha, i, true);
        }

    }

    public void marcar_verticalmente(int linha, int coluna, int quantidade) {


        for (int i = linha; i < linha + quantidade; i++) {
            marque(i, coluna, true);

        }

    }


    public void marque_diagonalmente_abaixo_direita(int linha, int coluna, int quantidade) {

        for (int i = 0; i < quantidade; i++) {
            marque(linha + i, coluna + i, true);

        }

    }

    public void marque_diagonalmente_abaixo_esquerda(int linha, int coluna, int quantidade) {

        for (int i = 0; i < quantidade; i++) {
            marque(linha + i, coluna - i, true);
        }

    }


    public Lista<T> getHorizontalmente(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = coluna; i < coluna + quantidade; i++) {
            ret.adicionar(get(linha, i).mConteudo);
        }

        return ret;
    }

    public Lista<T> getVerticalmente(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = linha; i < linha + quantidade; i++) {
            ret.adicionar(get(i, coluna).mConteudo);
        }

        return ret;
    }

    public Lista<T> getDiagonalmenteAbaixoDireita(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = 0; i < quantidade; i++) {
            ret.adicionar(get(linha + i, coluna + i).mConteudo);
        }

        return ret;
    }

    public Lista<T> getDiagonalmenteAbaixoEsquerda(int linha, int coluna, int quantidade) {

        Lista<T> ret = new Lista<T>();

        for (int i = 0; i < quantidade; i++) {
            ret.adicionar(get(linha + i, coluna - i).mConteudo);
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