package apps.app_metropoles;

import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;

public class Controlador {

    private Ponto mLocalizacao;

    private Cano mC1;
    private Cano mC2;

    private boolean mStatus;

    public Controlador(int eX, int eY, Cano eC1, Cano eC2) {
        mLocalizacao = new Ponto(eX, eY);
        mC1 = eC1;
        mC2 = eC2;
        mStatus = false;
    }

    public boolean isAberto() {
        return mStatus == true;
    }

    public boolean isFechado() {
        return mStatus == false;
    }

    public void abrir() {
        mStatus = true;
    }

    public void fechar() {
        mStatus = false;
    }

    public void passar() {

        if (isAberto()) {

            EixoCano antes = mC1.getEixos().get(mC1.getEixos().size() - 1);
            EixoCano depois = mC2.getEixos().get(0);

            if (antes.isCheio() && depois.isVazio()) {
                depois.encher(antes.getQuantidade());
                antes.esvaziar();
            }

        }

    }

    public void render(Renderizador mRenderizador) {

        mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX(), mLocalizacao.getY(), 30, 30), new Cor(255, 100, 50));

        if (isAberto()) {
            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 10, mLocalizacao.getY() + 10, 10, 10), new Cor(255, 255, 255));
        } else {
            mRenderizador.drawRect_Pintado(new Retangulo(mLocalizacao.getX() + 10, mLocalizacao.getY() + 10, 10, 10), new Cor(0, 0, 0));
        }


    }
}
