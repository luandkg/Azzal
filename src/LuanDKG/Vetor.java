package LuanDKG;

import java.util.ArrayList;

public class Vetor {

    private String mNome;
    private ArrayList<String> mValores;

    public Vetor(String eNome) {

        mNome = eNome;
        mValores = new ArrayList<String>();

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

    public ArrayList<String> getValores(){
        return mValores;
    }

    public void adicionar(String eValor){
        mValores.add(eValor);
    }

    public void adicionar(String eValorA,String eValorB){
        mValores.add(eValorA);
        mValores.add(eValorB);

    }

}
