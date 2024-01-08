package libs.luan;

public class LinhaHash<T1, T2> {

    // LUAN FREITAS
    // 2023 11 27

    private int mIndice;
    private boolean mExiste;
    private T1 mChave;
    private T2 mValor;

    private Igualdade<T1> mIgualdade;
    private Lista<LinhaHashColisao<T1, T2>> mColisoes;

    public LinhaHash(Igualdade<T1> eIgualdade, int eChave) {
        mIndice = eChave;
        mExiste = false;
        mChave = null;
        mValor = null;
        mIgualdade = eIgualdade;
        mColisoes = new Lista<LinhaHashColisao<T1, T2>>();
    }

    public void set(T1 eChave, T2 eValor) {

        if (mExiste) {

            boolean atualizado = false;

            if (mIgualdade.isIgual(mChave, eChave)) {
                setValor(eValor);
                atualizado = true;
            } else {
                for (LinhaHashColisao<T1, T2> colisao : getColisoes()) {
                    if (mIgualdade.isIgual(colisao.getChave(), eChave)) {
                        colisao.setValor(eValor);
                        atualizado = true;
                        break;
                    }
                }
            }

            if (!atualizado) {
                mColisoes.adicionar(new LinhaHashColisao<T1, T2>(eChave, eValor));
            }

            // fmt.print("TabelaHash COLISAO {} -->> {}", mIndice, mChave);

        } else {
            mExiste = true;
            mChave = eChave;
            mValor = eValor;

            // fmt.print("TabelaHash {} -->> {}", mIndice, mChave);
        }

    }


    public boolean existe(T1 eChave) {

        boolean ret = false;

        if (mExiste) {
            if (mIgualdade.isIgual(mChave, eChave)) {
                ret = true;
            } else {
                for (LinhaHashColisao<T1, T2> o : mColisoes) {
                    if (mIgualdade.isIgual(o.getChave(), eChave)) {
                        ret = true;
                        break;
                    }
                }
            }
        }

        return ret;
    }

    public T2 get(T1 eChave) {

        T2 ret = null;
        boolean enc = false;

        if (mExiste) {
            if (mIgualdade.isIgual(mChave, eChave)) {
                ret = mValor;
                enc = true;
            }
            if (!enc) {
                for (LinhaHashColisao<T1, T2> o : mColisoes) {
                    if (mIgualdade.isIgual(o.getChave(), eChave)) {
                        ret = o.getValor();
                        enc = true;
                        break;
                    }
                }
            }

        }

        return ret;
    }


    public boolean remover(T1 eChave) {

        boolean removido = false;

        if (mExiste) {
            if (mIgualdade.isIgual(mChave, eChave)) {

                mExiste = false;
                mChave = null;
                mValor = null;

                if (mColisoes.getQuantidade() > 0) {

                    LinhaHashColisao<T1, T2> primeiro = getColisoes().getValor(0);
                    mExiste = true;
                    mChave = primeiro.getChave();
                    mValor = primeiro.getValor();

                    getColisoes().remover(primeiro);

                }

                removido = true;

            } else {

                for (LinhaHashColisao<T1, T2> colisao : getColisoes()) {
                    if (mIgualdade.isIgual(colisao.getChave(), eChave)) {
                        mColisoes.remover(colisao);
                        removido = true;
                        break;
                    }
                }

            }


        }

        return removido;
    }

    public T1 getChave() {
        return mChave;
    }

    public T2 getValor() {
        return mValor;
    }

    public boolean estaOcupada() {
        return mExiste;
    }


    public Lista<LinhaHashColisao<T1, T2>> getColisoes() {
        return mColisoes;
    }

    public int getQuantidade() {
        if (mExiste) {
            return getColisoes().getQuantidade() + 1;
        } else {
            return 0;
        }
    }

    public int getIndice() {
        return mIndice;
    }

    public void setValor(T2 eValor) {
        mValor = eValor;
    }
}