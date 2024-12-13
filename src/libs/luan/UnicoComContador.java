package libs.luan;


public class UnicoComContador<T> {

    private Unico<Par<T,Integer>> mValores;

    public UnicoComContador(Igualavel<T> eIgualavel){

        mValores =new Unico<Par<T,Integer>>(new Igualavel<Par<T, Integer>>() {
            @Override
            public boolean is(Par<T, Integer> a, Par<T, Integer> b) {
                return eIgualavel.is(a.getChave(),b.getChave());
            }
        });

    }


    public void conte(T eValor){
        Par<T, Integer> marcado = mValores.item_get(new Par<T, Integer>(eValor, 0));
        marcado.set(marcado.getChave(), marcado.getValor() + 1);
    }

    public Lista<Par<T,Integer>> toLista(){
        return mValores.toLista();
    }

    public int getQuantidade(){
        return mValores.getQuantidade();
    }
}
