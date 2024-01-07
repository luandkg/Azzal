package libs.luan;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> {

    public static class Item<T1> {

        private T1 mValor;
        private Item<T1> mProximo;

        public Item(T1 eValor) {
            mValor = eValor;
            mProximo = null;
        }

        public void setValor(T1 eValor) {
            this.mValor = eValor;
        }

        public T1 getValor() {
            return this.mValor;
        }

        public void setProximo(Item<T1> eProximo) {
            this.mProximo = eProximo;
        }

        public Item<T1> getProximo() {
            return this.mProximo;
        }

    }

    private Item<T> mPrimeiro;
    private Item<T> mUltimo;
    private int mQuantidade;

    private boolean mLimitada;
    private int mLimite;

    public Lista() {

        mPrimeiro = null;
        mUltimo = null;
        mQuantidade = 0;
        mLimitada = false;
        mLimite = 0;

        //System.out.println("Construindo Lista");
    }


    public Item _INTERNO_PRIMEIRO() {
        return mPrimeiro;
    }

    public void adicionar(T eValor) {

        boolean adicionando = false;

        if (mLimitada) {

            if (mQuantidade < mLimite) {
                adicionando = true;
            } else {
                throw new IllegalArgumentException("A lista esta cheia !");
            }

        } else {
            adicionando = true;
        }

        if (adicionando) {

            Item mAdicionar = new Item(eValor);

            if (mPrimeiro == null) {

                mPrimeiro = mAdicionar;

            } else {

                mUltimo.setProximo(mAdicionar);
            }

            mUltimo = mAdicionar;

            //System.out.println("Adicionando " + eValor);
            mQuantidade += 1;

        }


    }

    public int getQuantidade() {
        return mQuantidade;
    }

    public void remover(T eValor) {

        if (mPrimeiro != null) {

            Item mAnterior = null;
            Item mCorrente = mPrimeiro;

            while (mCorrente != null) {

                if (mCorrente.getValor() == eValor) {

                    mQuantidade -= 1;
                    //	System.out.println("Removendo " + eValor);

                    if (mAnterior == null) {

                        if (mPrimeiro.getProximo() != null) {
                            mPrimeiro = mPrimeiro.getProximo();
                        } else {
                            mPrimeiro = null;
                            mUltimo = null;
                        }

                    } else {

                        if (mCorrente.getProximo() == null) {
                            mUltimo = mAnterior;
                            mAnterior.setProximo(null);
                            mCorrente = null;
                        } else {

                            mAnterior.setProximo(mCorrente.getProximo());
                            mCorrente = null;
                        }

                    }

                    break;
                }

                mAnterior = mCorrente;
                mCorrente = mCorrente.getProximo();
            }

        }

    }

    public void remover_indice(int indice_para_remover) {

        if (mPrimeiro != null) {

            Item mAnterior = null;
            Item mCorrente = mPrimeiro;
            int index_corrente = 0;

            while (mCorrente != null) {

                if (index_corrente == indice_para_remover) {

                    mQuantidade -= 1;
                    //	System.out.println("Removendo " + eValor);

                    if (mAnterior == null) {

                        if (mPrimeiro.getProximo() != null) {
                            mPrimeiro = mPrimeiro.getProximo();
                        } else {
                            mPrimeiro = null;
                            mUltimo = null;
                        }

                    } else {

                        if (mCorrente.getProximo() == null) {
                            mUltimo = mAnterior;
                            mAnterior.setProximo(null);
                            mCorrente = null;
                        } else {

                            mAnterior.setProximo(mCorrente.getProximo());
                            mCorrente = null;
                        }

                    }

                    break;
                }

                mAnterior = mCorrente;
                mCorrente = mCorrente.getProximo();
                index_corrente += 1;
            }

        }

    }

    public void listarSemIndice() {

        Item mCorrente = mPrimeiro;

        while (mCorrente != null) {
            System.out.println(" ->> " + mCorrente.getValor());
            mCorrente = mCorrente.getProximo();
        }

    }

    public void listarComIndice() {

        int indice = 0;

        Item mCorrente = mPrimeiro;
        while (mCorrente != null) {

            System.out.println(" " + indice + " ->> " + mCorrente.getValor());

            mCorrente = mCorrente.getProximo();
            indice += 1;
        }
    }

    public T get(int index) {

        if (index >= 0) {

            int indicecontagem = 0;
            Item<T> mCorrente = mPrimeiro;
            while (mCorrente != null) {

                if (index == indicecontagem) {
                    return mCorrente.getValor();
                }

                indicecontagem += 1;
                mCorrente = mCorrente.getProximo();
            }

            throw new IllegalArgumentException("Indice invalido : " + index);

        } else {

            throw new IllegalArgumentException("Indice invalido : " + index);
        }

    }

    public T getValor(int indice) {

        if (indice >= 0) {

            int indicecontagem = 0;
            Item<T> mCorrente = mPrimeiro;
            while (mCorrente != null) {

                if (indice == indicecontagem) {
                    return mCorrente.getValor();
                }

                indicecontagem += 1;
                mCorrente = mCorrente.getProximo();
            }

            throw new IllegalArgumentException("Indice invalido : " + indice);

        } else {

            throw new IllegalArgumentException("Indice invalido : " + indice);
        }

    }

    public void setValor(int indice, T eValor) {

        if (indice >= 0) {

            int indicecontagem = 0;
            Item mCorrente = mPrimeiro;
            boolean trocou = false;

            while (mCorrente != null) {

                if (indice == indicecontagem) {
                    mCorrente.setValor(eValor);
                    trocou = true;
                    break;
                }

                indicecontagem += 1;
                mCorrente = mCorrente.getProximo();

            }

            if (!trocou) {
                throw new IllegalArgumentException("Indice invalido : " + indice);
            }

        } else {

            throw new IllegalArgumentException("Indice invalido : " + indice);

        }

    }

    public void set(int index, T eValor) {

        if (index >= 0) {

            int indicecontagem = 0;
            Item mCorrente = mPrimeiro;
            boolean trocou = false;

            while (mCorrente != null) {

                if (index == indicecontagem) {
                    mCorrente.setValor(eValor);
                    trocou = true;
                    break;
                }

                indicecontagem += 1;
                mCorrente = mCorrente.getProximo();

            }

            if (!trocou) {
                throw new IllegalArgumentException("Indice invalido : " + index);
            }

        } else {

            throw new IllegalArgumentException("Indice invalido : " + index);

        }

    }


    public boolean estaVazia() {

        return mQuantidade == 0;

    }

    public boolean possuiObjetos() {

        return mQuantidade > 0;
    }

    public void limpar() {
        mQuantidade = 0;
        mPrimeiro = null;
        mUltimo = null;
    }


    public int getIndexOf(T eValor) {


        boolean encontrou = false;

        Item mCorrente = mPrimeiro;

        int mIndice = 0;

        while (mCorrente != null) {

            if (mCorrente.getValor() == eValor) {
                encontrou = true;
                break;
            }


            mIndice += 1;
            mCorrente = mCorrente.getProximo();
        }

        if (encontrou == false) {
            throw new IllegalArgumentException("Valor nao encontrado : " + eValor);
        }

        return mIndice;
    }

    public boolean existe(T eValor) {

        boolean retorno = false;

        Item mCorrente = mPrimeiro;
        while (mCorrente != null) {
            if (mCorrente.getValor() == eValor) {
                retorno = true;
                break;
            }
            mCorrente = mCorrente.getProximo();
        }

        return retorno;

    }

    public boolean existe(Igualavel<T> eIgualador,T procurado){

        for(T valor : this){
            if(eIgualador.is(procurado,valor)){
                return true;
            }
        }

        return false;
    }

    public int contar(T eValor) {

        int retorno = 0;


        Item mCorrente = mPrimeiro;

        while (mCorrente != null) {
            if (mCorrente.getValor() == eValor) {
                retorno += 1;
            }
            mCorrente = mCorrente.getProximo();
        }

        return retorno;
    }


    public void limitar(int eLimite) {

        if (mQuantidade <= eLimite) {
            mLimitada = true;
            mLimite = eLimite;
        } else {
            throw new IllegalArgumentException("A quantidade de objetos e maior que o limite !");
        }

    }

    public void desLimitar() {
        mLimitada = false;
        mLimite = 0;
    }


    public boolean estaLimitada() {
        return mLimitada;
    }

    public int getLimite() {
        return mLimite;
    }

    public boolean estaCheia() {
        if (mLimitada) {
            return mQuantidade >= mLimite;
        } else {
            return false;
        }
    }

    public void aumentarLimite(int eAumento) {


    }

    public int completar() {
        if (mLimitada) {

            return mLimite - mQuantidade;

        } else {
            return 0;
        }
    }

    // SUPER FUNCOES



    public Iterator<T> iterator() {
        return new IteradorDaLista(this);
    }

    public static class IteradorDaLista<T> implements Iterator<T> {

        int index = 0;
        int quantidade = 0;
        Item<T> i_corrente;

        public IteradorDaLista(Lista<T> eLista) {
            index = 0;
            quantidade = eLista.getQuantidade();
            i_corrente = eLista.mPrimeiro;
        }

        @Override
        public boolean hasNext() {
            return index < quantidade;
        }

        @Override
        public T next() {
            T eValor = i_corrente.getValor();

            index += 1;
            i_corrente = i_corrente.getProximo();

            return eValor;
        }

        public void remove() {
            throw new UnsupportedOperationException("not supported yet");

        }
    }


    public void paraCada(EmCada<T> emCada) {


        for (T item : this) {
            emCada.fazer(item);
        }

    }

    public void opereCada(T resultado, Operacao<T> emCada) {


        for (T item : this) {

            emCada.fazer(item, resultado);

        }

    }


    public void adicionar_varios(Lista<T> varios) {

        for (T item : varios) {
            adicionar(item);
        }
    }


    public static <T1> Lista<T1> TIRAR_COPIA(Lista<T1> original) {
        Lista<T1> copia = new Lista<T1>();

        for (T1 valor : original) {
            copia.adicionar(valor);
        }

        return copia;
    }

    public static <T> void ORDENAR_CRESCENTE(Lista<T> lista, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = lista.getQuantidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(lista.get(j - 1), lista.get(j));

                if (ordem == Ordenavel.MAIOR) {
                    temp = lista.get(j - 1);
                    lista.set(j - 1, lista.get(j));
                    lista.set(j, temp);
                }

            }
        }

    }

    public static <T> void ORDENAR_DECRESCENTE(Lista<T> lista, Ordenavel<T> algoritmo_de_ordenacao) {

        int n = lista.getQuantidade();
        T temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                int ordem = algoritmo_de_ordenacao.emOrdem(lista.get(j - 1), lista.get(j));

                if (ordem == Ordenavel.MENOR) {
                    temp = lista.get(j - 1);
                    lista.set(j - 1, lista.get(j));
                    lista.set(j, temp);
                }

            }
        }

    }


    public static <T> Lista<T> EMBARALHAR(Lista<T> lista) {


        for (int i = 0; i < lista.getQuantidade(); i++) {

            int p1 = Aleatorio.aleatorio(lista.getQuantidade());
            int p2 = Aleatorio.aleatorio(lista.getQuantidade());

            T c1 = lista.getValor(p1);
            T c2 = lista.getValor(p2);

            lista.setValor(p1, c2);
            lista.setValor(p2, c1);

        }


        return lista;
    }



    public void adicionar_como_primeiro(T eObjeto){

        Item novo_primeiro = new Item(eObjeto);
        novo_primeiro.mProximo=mPrimeiro;

        mPrimeiro=novo_primeiro;
        mQuantidade+=1;
    }



    public Opcional<T> procurar(T procurado, Igualdade<T> igualdade) {

        for (T item : this) {
            if (igualdade.isIgual(item, procurado)) {
                return Opcional.OK(item);
            }
        }

        return Opcional.CANCEL();
    }

    public void removerIndex(int remover_index) {

        int index = 0;

        if (mPrimeiro != null) {

            Item mAnterior = null;
            Item mCorrente = mPrimeiro;

            while (mCorrente != null) {

                if (index == remover_index) {

                    mQuantidade -= 1;
                    //	System.out.println("Removendo " + eValor);

                    if (mAnterior == null) {

                        if (mPrimeiro.getProximo() != null) {
                            mPrimeiro = mPrimeiro.getProximo();
                        } else {
                            mPrimeiro = null;
                            mUltimo = null;
                        }

                    } else {

                        if (mCorrente.getProximo() == null) {
                            mUltimo = mAnterior;
                            mAnterior.setProximo(null);
                            mCorrente = null;
                        } else {

                            mAnterior.setProximo(mCorrente.getProximo());
                            mCorrente = null;
                        }

                    }

                    break;
                }

                mAnterior = mCorrente;
                mCorrente = mCorrente.getProximo();
                index += 1;
            }

        }

    }

}
