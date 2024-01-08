package libs.mockui.Interface;


import libs.azzal.geometria.Retangulo;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;


public class BotaoCor {

    private Retangulo mRetangulo;
    private Cor mCor = new Cor(255, 255, 255);
    private boolean mTemAcao;
    private Acao mAcao;

    private boolean mTemVariacao;
    private Cor mCorNormal;
    private Cor mCorPressionada;

    public BotaoCor(int eX, int eY, int eLargura, int eAltura) {

        mRetangulo = new Retangulo(eX, eY, eLargura, eAltura);
        mTemAcao = false;
        mAcao = null;

        mTemVariacao = false;
    }


    public BotaoCor(int eX, int eY, int eLargura, int eAltura, Cor eCor) {

        mRetangulo = new Retangulo(eX, eY, eLargura, eAltura);
        mCor = eCor;
        mTemAcao = false;
        mAcao = null;

    }

    public void setVariacao(Cor eCorNormal, Cor eCorPressionada) {
        mTemVariacao = true;
        mCorNormal = eCorNormal;
        mCorPressionada = eCorPressionada;
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

    public int getX() {
        return mRetangulo.getX();
    }

    public int getY() {
        return mRetangulo.getY();
    }

    public int getLargura() {
        return mRetangulo.getLargura();
    }

    public int getAltura() {
        return mRetangulo.getAltura();
    }

    public int getX2() {
        return mRetangulo.getX2();
    }

    public int getY2() {
        return mRetangulo.getY2();
    }

    public void setX(int eX) {
        mRetangulo.setX(eX);
    }

    public void setY(int eY) {
        mRetangulo.setY(eY);
    }

    public Cor getCor() {
        return mCor;
    }

    public void setCor(Cor eCor) {
        mCor = eCor;
    }

    public void draw(Renderizador g) {

        g.drawRect_Pintado(mRetangulo.getX(), mRetangulo.getY(), mRetangulo.getLargura(), mRetangulo.getAltura(), mCor);


    }

    public boolean getClicado(int px, int py) {
        boolean ret = false;

        if (px >= this.getX() && px <= this.getX2()) {
            if (py >= this.getY() && py <= this.getY2()) {
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
