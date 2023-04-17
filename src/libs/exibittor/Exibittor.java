package libs.exibittor;

import libs.luan.fmt;

import java.util.ArrayList;

public class Exibittor {

    private int mTamanho;
    private ArrayList<String> mCabecalhos;
    private ArrayList<String> mDados;

    public Exibittor(int eTamanho) {
        mTamanho = eTamanho;
        mCabecalhos = new ArrayList<String>();
        mDados = new ArrayList<String>();
    }

    public void adicionar_cabecalho(String a) {
        mCabecalhos.add(a);
    }

    public void exibir() {

        String cabecalho_completo = fmt.espacar_depois(" ", 2 * mTamanho);

        for (String a : mCabecalhos) {
            cabecalho_completo += fmt.espacar_depois(a, mTamanho);
        }

        System.out.println(cabecalho_completo);
        System.out.println("");

    }

    public void adicionar_dados(String a) {
        mDados.add(a);
    }

    public void adicionar_dados(int a) {
        mDados.add(String.valueOf(a));
    }

    public void exibir_dados(String item) {

        item = "       " + item+ "       ---->>      ";

        String cabecalho_completo = fmt.espacar_depois(item, 2 * mTamanho);

        for (String a : mDados) {
            cabecalho_completo += fmt.espacar_depois(a, mTamanho);
        }

        System.out.println(cabecalho_completo);
        mDados.clear();
    }

    public static void nova_secao(String titulo){
        System.out.println("");
        System.out.println(titulo);
        System.out.println("");
    }

}
