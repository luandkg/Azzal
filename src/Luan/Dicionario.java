package Luan;


public class Dicionario<T> {

    private Lista<Par<String, T>> mLista;
    private Iterador<Par<String, T>> mIterador;

    public Dicionario() {

        mLista = new Lista<Par<String, T>>();
        mIterador = new Iterador<Par<String, T>>(mLista);

    }

    public boolean existeChave(String eChave) {
        boolean ret = false;

        for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

            if (mIterador.getValor().getChave().contentEquals(eChave)) {
                ret = true;
                break;
            }


        }

        return ret;
    }

    public void adicionar(String eNome, T eCor) {
        if (!existeChave(eNome)) {

            mLista.adicionar(new Par<String, T>(eNome, eCor));

        } else {

            throw new IllegalArgumentException("Ja existe um item com esse nome : " + eNome);
        }

    }


    public T obter(String eNome) {
        T eCor = null;

        boolean enc = false;

        for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

            if (mIterador.getValor().getChave().contentEquals(eNome)) {
                eCor = mIterador.getValor().getValor();
                enc = true;
                break;
            }


        }

        if (!enc) {
            throw new IllegalArgumentException("Item nao encontrado : " + eNome);
        }
        return eCor;
    }

    public void remover(String eChave){

        boolean enc = false;

        for (mIterador.iniciar(); mIterador.continuar(); mIterador.proximo()) {

            if (mIterador.getValor().getChave().contentEquals(eChave)) {
                mLista.remover( mIterador.getValor());
                enc = true;
                break;
            }


        }

        if (!enc) {
            throw new IllegalArgumentException("Item nao encontrado : " + eChave);
        }

    }

    public int getQuantidade() {
        return mLista.getQuantidade();
    }

}
