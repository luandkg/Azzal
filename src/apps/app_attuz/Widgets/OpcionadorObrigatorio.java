package apps.app_attuz.Widgets;

import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;

import java.awt.*;
import java.util.ArrayList;

public class OpcionadorObrigatorio {

    private int mX;
    private int mY;
    private String mCabacalho = "";

    private ArrayList<ItemOpcionador> mItens;
    private Fonte pequeno;

    private String mSelecionado = "";
    private boolean mIsAlterado = false;

    public OpcionadorObrigatorio(int eX, int eY) {
        mX = eX;
        mY = eY;
        mItens = new ArrayList<ItemOpcionador>();

        pequeno = new FonteRunTime(Cor.getRGB(Color.BLACK), 11);

    }

    public String getSelecionado() {
        return mSelecionado;
    }

    public void setSelecionado(String e) {
        mSelecionado = e;
    }

    public void setCabecalho(String eCabecalho) {
        mCabacalho = eCabecalho;
    }


    public void adicionar(String eNome, Cor eCorNormal, Cor eCorSelecionado) {
        mItens.add(new ItemOpcionador(eNome, eCorNormal, eCorSelecionado));
    }

    public void update(double dt, int px, int py, boolean ePrecionado) {

        mIsAlterado = false;

        if (ePrecionado) {

            int y = mY + 30;

            for (ItemOpcionador item : mItens) {

                int qx = mX + 30;
                int qy = y + 5;

                if (isDentro(px, py, qx, qy, qx + 20, qy + 20)) {

                    String novo_selecionado = item.getNome();

                    if (!novo_selecionado.contentEquals(mSelecionado)) {

                        mIsAlterado = true;
                        mSelecionado = novo_selecionado;

                    }


                    break;
                }

                y += 20;

            }
        }

    }

    public boolean isAlterado() {
        return mIsAlterado;
    }

    public boolean isDentro(int px, int py, int qx, int qy, int qx2, int qy2) {
        boolean ret = false;

        if (px >= qx && px <= qx2) {
            if (py >= qy && py <= qy2) {
                ret = true;
            }
        }

        return ret;
    }

    public void draw(Renderizador g) {

        pequeno.setRenderizador(g);
        pequeno.escreva(mX, mY, mCabacalho + mSelecionado);

        int y = mY + 30;

        for (ItemOpcionador item : mItens) {

            int qx = mX + 30;
            int qy = y + 5;

            if (item.getNome().contentEquals(mSelecionado)) {
                g.drawRect_Pintado(qx, qy, 10, 10, item.getCorSelecionado());
            } else {
                g.drawRect_Pintado(qx, qy, 10, 10, item.getCorNormal());
            }

            pequeno.escreva(mX + 50, y, item.getNome());

            y += 20;
        }
    }


}
