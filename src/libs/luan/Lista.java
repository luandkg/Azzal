package libs.luan;

public class Lista<T> {

    private class Item {

        private T mValor;
        private Item mProximo;

        public Item(T eValor) {
            mValor = eValor;
            mProximo = null;
        }

        public void setValor(T eValor) {
            this.mValor = eValor;
        }

        public T getValor() {
            return this.mValor;
        }

        public void setProximo(Item eProximo) {
            this.mProximo = eProximo;
        }

        public Item getProximo() {
            return this.mProximo;
        }

    }

    private Item mPrimeiro;
    private Item mUltimo;
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

    public T getValor(int indice) {

        if (indice >= 0) {

            int indicecontagem = 0;
            Item mCorrente = mPrimeiro;
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

    public Iterador<T> getIterador() {
        Iterador<T> eIterador = new Iterador<T>(this);
        return eIterador;
    }

    public void paraCada(EmCada emCada) {

        Iterador<T> passador = getIterador();

        for (passador.iniciar(); passador.continuar(); passador.proximo()) {

            emCada.fazer(passador.getValor());

        }

    }

    public void opereCada(T resultado,Operacao emCada) {

        Iterador<T> passador = getIterador();

        for (passador.iniciar(); passador.continuar(); passador.proximo()) {

            emCada.fazer(passador.getValor(),resultado);

        }

    }

}
