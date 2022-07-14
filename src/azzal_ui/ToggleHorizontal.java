package azzal_ui;

import azzal.Renderizador;
import azzal.Utils.Cor;
import apps.appLetrum.Fonte;
import azzal_ui.Interface.Acao;

public class ToggleHorizontal {

    private int mx;
    private int my;
    private String mNome;
    private boolean mValor;

    private Cor mPreto = new Cor(0, 0, 0);
    private Cor mVermelho = new Cor(255, 0, 0);
    private Cor mVerde = new Cor(0, 255, 0);

    public ToggleHorizontal(int x, int y, String eNome, boolean eValor) {
        mx = x;
        my = y;
        mNome = eNome;
        mValor = eValor;
    }

    public void foiClicado(int px, int py) {


        if (mValor) {
            if (px >= (mx + 15) && px < (mx + 30) && py >= my && py < (my + 10)) {
                mValor = false;
                clicar();
            }
        } else {
            if (px >= mx && px < (mx + 15) && py >= my && py < (my + 10)) {
                mValor = true;
                clicar();
            }

        }

    }

    public void onRender(Renderizador mRender, Fonte eTexto) {


        mRender.drawRect_Pintado(mx, my, 30, 10, mPreto);

        if (mValor) {
            mRender.drawRect_Pintado(mx, my, 15, 10, mVerde);
        } else {
            mRender.drawRect_Pintado(mx + 15, my, 15, 10, mVermelho);
        }

        eTexto.escreva(mx + 35, my - 5, mNome);


    }

    public void setValor(boolean v) {
        mValor = v;
    }

    public boolean getValor() {
        return mValor;
    }


    private boolean mTemAcao;
    private Acao mAcao;

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
