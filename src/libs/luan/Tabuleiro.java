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

    public Vetor<Vetor<Peca<T>>> mLinhas = new Vetor<Vetor<Peca<T>>>(1);


    public Peca<T> get(int linha, int coluna) {
        return mLinhas.get(linha).get(coluna);
    }

    public T getValor(int linha, int coluna) {
        return get(linha, coluna).mConteudo;
    }

    public void setValor(int linha, int coluna,T valor) {
         get(linha, coluna).mConteudo=valor;
    }

    public T getValorCartesiano(int coluna, int linha) {
        return get(linha, coluna).mConteudo;
    }

    public void setValorCartesiano(int coluna, int linha, T valor) {
        get(linha, coluna).mConteudo = valor;
    }

    public void marque(int linha, int coluna, boolean marcado) {
        mLinhas.get(linha).get(coluna).descoberto = marcado;
    }


    public void setTabuleiro(Lista<Lista<T>> conteudo){

        mLinhas = new Vetor<Vetor<Peca<T>>>(conteudo.getQuantidade());

        int i = 0;
        for(Lista<T> linha : conteudo){
            aplicar_linha(i,linha);
            i+=1;
        }

    }

    public void aplicar_linha(int linha_indice,Lista<T> lista) {

        int linha_tamanho = lista.getQuantidade();
        Vetor<Peca<T>> linha_vec_pecas = new Vetor<Peca<T>>(linha_tamanho);

        int i =0;
        for (T letra : lista) {
            Peca<T> peca =new Peca<T>(linha_indice,i,letra);
            linha_vec_pecas.set(i,peca);
            i+=1;
        }

        mLinhas.set(linha_indice,linha_vec_pecas);
    }



    public void exibir() {
        for (Vetor<Peca<T>> linha : mLinhas) {
            String s_linha = "";
            for (Peca<T> letra : linha) {
                s_linha += letra.mConteudo + " ";
            }
            fmt.print("{}", s_linha);
        }
    }

    public void exibir_descobertos() {
        for (Vetor<Peca<T>> linha : mLinhas) {
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
        return mLinhas.getCapacidade();
    }

    public int getLetrasQuantidadeDaLinha(int linha) {
        return mLinhas.get(linha).getCapacidade();
    }

    public boolean posicao_valida(int linha, int coluna) {
        if (linha >= 0 && linha < mLinhas.getCapacidade()) {
            if (coluna >= 0 && coluna < mLinhas.get(linha).getCapacidade()) {
                return true;
            }
        }
        return false;
    }


    public boolean posicao_valida_cartesiana(int coluna, int linha) {
        if (linha >= 0 && linha < mLinhas.getCapacidade()) {
            if (coluna >= 0 && coluna < mLinhas.get(linha).getCapacidade()) {
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


    public Tabuleiro<T> getCopia(){

        Tabuleiro<T> copia = new Tabuleiro<T>();

        copia.mLinhas=new Vetor<Vetor<Peca<T>>>(getLinhasQuantidade());

        int indice_linha=0;

        for(Vetor<Peca<T>> linha : mLinhas){

            Vetor<Peca<T>> nova_linha = new Vetor<Peca<T>>(linha.getCapacidade());

            int i = 0;
            for(Peca<T> peca : linha){
                Peca<T> peca_nova = new Peca<T>(peca.linha,peca.coluna,peca.mConteudo);
                nova_linha.set(i,peca_nova);
                i+=1;
            }

            copia.mLinhas.set(indice_linha,nova_linha);
            indice_linha+=1;
        }

        return copia;
    }


    public void aplicar_todos(T valor){
        for (int y = 0; y < getLinhasQuantidade(); y++) {
            for (int x = 0; x < getLetrasQuantidadeDaLinha(y); x++) {
                setValorCartesiano(x, y, valor);
            }
        }
    }

}