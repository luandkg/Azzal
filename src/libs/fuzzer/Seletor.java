package libs.fuzzer;

import azzal.utilitarios.Cor;
import mockui.Interface.Acao;

public class Seletor {

    private Botao mBotao;

    private int mValor;

    private int mValorInicial;
    private int mValorProximo;
    private Cor mEixo;

    public Seletor(Botao eBotao, int eValorInicial, int eValorProximo) {

        mBotao = eBotao;
        mValor = eValorInicial;
        mValorInicial = eValorInicial;
        mValorProximo = eValorProximo;

        if (mValor == mValorInicial) {
            mValor = mValorProximo;
            mEixo = new Cor(0,255,0);
        } else {
            mValor = eValorInicial;
            mEixo = new Cor(50,130,50);
        }


        mBotao.setAcao(new Acao() {
            @Override
            public void onClique() {
                if (mValor == mValorInicial) {
                    mValor = mValorProximo;
                    mEixo = new Cor(0,255,0);
                } else {
                    mValor = eValorInicial;
                    mEixo = new Cor(50,130,50);
                }
            }
        });
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
