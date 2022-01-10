package LuanDKG;

import java.util.ArrayList;

public class Matriz {

    private String mNome;
    private ArrayList<ArrayList<String>> mValores;

    public Matriz(String eNome) {

        mNome = eNome;
        mValores = new ArrayList<ArrayList<String>>();

    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public String getNome() {
        return mNome;
    }

    public void limpar(){
        mValores.clear();
    }

    public ArrayList<ArrayList<String>> getValores(){
        return mValores;
    }

    public void adicionar(ArrayList<String> eValor){
        mValores.add(eValor);
    }

    public void adicionar(String eValorA,String eValorB){

        ArrayList<String> eNovoVetor = new ArrayList<String>();

        eNovoVetor.add(eValorA);
        eNovoVetor.add(eValorB);

        mValores.add(eNovoVetor);


    }

    public void adicionar(String eValorA,String eValorB,String ValorC){

        ArrayList<String> eNovoVetor = new ArrayList<String>();

        eNovoVetor.add(eValorA);
        eNovoVetor.add(eValorB);
        eNovoVetor.add(ValorC);

        mValores.add(eNovoVetor);


    }

}
