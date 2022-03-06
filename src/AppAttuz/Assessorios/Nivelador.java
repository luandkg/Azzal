package AppAttuz.Assessorios;


import UI.Interface.Acao;

public class Nivelador {

    private int mNivel;

    public Nivelador(){mNivel=2;}

    public int getNivel(){return mNivel;}

    public Acao get(int v){
       return new Acao() {
            @Override
            public void onClique() {
                mNivel=v;

                if (v ==0){
                    System.out.println("Quadrante limpar ...");
                }
            }
        };
    }
}
