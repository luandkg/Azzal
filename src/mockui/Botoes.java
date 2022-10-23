package mockui;

import java.util.ArrayList;

public class Botoes {

    private ArrayList<Botao> mBotaos;

    private boolean mPegou;
    private String mBotaoClicado;

    public Botoes() {


        mBotaos = new ArrayList<>();
        mPegou = false;
        mBotaoClicado = "";

    }

    public boolean pegouAlgum(int eX, int eY) {

        mPegou = false;
        mBotaoClicado = "";

        for (Botao eBotao : mBotaos) {

            if (eBotao.isDentro(eX, eY)) {
                mPegou = true;
                mBotaoClicado = eBotao.getNome();
                break;
            }


        }

        return mPegou;

    }


    public boolean isPegou() {
        return mPegou;
    }

    public String getBotaoClicado() {
        return mBotaoClicado;
    }

    public void soltar() {
        mPegou = false;
        mBotaoClicado = "";
    }

    public void limpar() {
        mBotaos.clear();
    }

    public void adicionarBotao(Botao eBotao) {
        mBotaos.add(eBotao);
    }

}
