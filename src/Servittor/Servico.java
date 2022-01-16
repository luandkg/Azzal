package Servittor;

public class Servico {

    private String mNome = "";

    public void onNomear(String eNome) {
        mNome = eNome;
    }

    public void onInit( ) {

    }

    public void println(String a) {
        System.out.println(mNome + " :: " + a);
    }
}
