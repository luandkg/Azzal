package Arquivos.Audio;

import java.util.ArrayList;

public class RepeticaoBinaria {

    private String mValor;
    private int mQuantidade;
    private ArrayList<Integer> mPosicoes;

private byte b1;
private byte b2;

    public RepeticaoBinaria(byte e1,byte e2,String eValor) {
        b1=e1;
        b2=e2;
        mValor = eValor;
        mQuantidade = 1;
        mPosicoes = new ArrayList<Integer>();
    }

    public byte getB1() {
        return b1;
    }
    public byte getB2() {
        return b2;
    }

    public void aumentar() {
        mQuantidade += 1;
    }

    public String getValor() {
        return mValor;
    }

    public int getQuantidade() {
        return mQuantidade;
    }

    public void guardar(int pos) {
        mPosicoes.add(pos);
    }


    public  ArrayList<Integer> getPosicoes(){return mPosicoes;}
}
