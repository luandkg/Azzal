package libs.fuzzer;

import azzal.utilitarios.Cor;
import mockui.Interface.Acao;

import java.util.ArrayList;

public class Menu {

    private Botao mBotao;

    private boolean mValor;

    private Cor mEixo;

    public Menu(Botao eBotao, ArrayList<Menu> menu_principal) {

        mBotao = eBotao;
        mValor = false;

        if (mValor) {
            mEixo = new Cor(0, 255, 0);
        } else {
            mEixo = new Cor(50, 130, 50);
        }

        mBotao.setAcao(new Acao() {
            @Override
            public void onClique() {

                for (Menu eMenu : menu_principal) {
                    eMenu.setValor(false);
                }

                mValor = true;

                if (mValor) {
                    mEixo = new Cor(0, 255, 0);
                } else {
                    mEixo = new Cor(50, 130, 50);
                }

            }
        });
    }

    public void setValor(boolean e){
        mValor=e;
        if (mValor) {
            mEixo = new Cor(0, 255, 0);
        } else {
            mEixo = new Cor(50, 130, 50);
        }
    }


    public int getX() {
        return mBotao.getX();
    }

    public int getY() {
        return mBotao.getY();
    }

    public int getL() {
        return mBotao.getL();
    }

    public int getA() {
        return mBotao.getA();
    }

    public String getTexto() {
        return mBotao.getTexto();
    }

    public Cor getCor() {
        return mBotao.getCor();
    }

    public Cor getEixoCor() {
        return mEixo;
    }
}
