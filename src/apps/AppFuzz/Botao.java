package apps.AppFuzz;

import azzal.Utils.Cor;
import azzal_ui.Interface.Acao;

public class Botao {

    private int mx;
    private int my;
    private int ml;
    private int ma;
    private String mTexto;

    private boolean mTemAcao;
    private Acao mAcao;

    private boolean mTemVariacao;

    private Cor mCorNormal;
    private Cor mCorPressionada;

    private Cor mCor;

    public Botao(int x, int y, int l, int a, String texto) {
        mx = x;
        my = y;
        ml = l;
        ma = a;
        mTexto = texto;

        mTemAcao = false;
        mAcao = null;

        mTemVariacao = false;
    }

    public int getX() {
        return mx;
    }

    public int getY() {
        return my;
    }

    public int getL() {
        return ml;
    }

    public int getA() {
        return ma;
    }

    public String getTexto() {
        return mTexto;
    }

    public Cor getCor() {
        return mCor;
    }

    public void setCor(Cor eCor) {
        mCor = eCor;
    }

    public void setVariacao(Cor eCorNormal, Cor eCorPressionada) {
        mTemVariacao = true;
        mCorNormal = eCorNormal;
        mCorPressionada = eCorPressionada;
        mCor = eCorNormal;
    }

    public boolean temVariacao() {
        return mTemVariacao;
    }

    public Cor getCorPressionado() {
        return mCorPressionada;
    }

    public Cor getCorNormal() {
        return mCorNormal;
    }


    public boolean getClicado(int px, int py) {
        boolean ret = false;

        if (px >= this.getX() && px <= (this.getX() + this.getL())) {
            if (py >= this.getY() && py <= (this.getY() + this.getA())) {
                ret = true;
            }
        }

        return ret;
    }

    public boolean temAcao() {
        return mTemAcao;
    }

    public void setAcao(Acao eAcao) {
        mAcao = eAcao;
        mTemAcao = true;
    }

    public void clicar() {
        mAcao.onClique();
    }
}
