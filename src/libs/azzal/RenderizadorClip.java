package libs.azzal;

import libs.azzal.geometria.Retangulo;
import libs.azzal.utilitarios.Cor;

public class RenderizadorClip {

    private Renderizador mRenderizador;
    private Retangulo mArea;

    public RenderizadorClip(Renderizador eRenderizador, Retangulo eArea) {
        mRenderizador = eRenderizador;
        mArea = eArea;
    }

    public void drawPixelBruto(int eX, int eY, int eCor) {

        if (eX >= mArea.getX() && eX < mArea.getX2() && eY >= mArea.getY() && eY < mArea.getY2()) {

            mRenderizador.drawPixelBruto(eX,eY,eCor);

        }


    }

    public void drawRect_Pintado(Retangulo eRetangulo, Cor eCor) {

        int mX2 = eRetangulo.getX() + eRetangulo.getLargura();

        int iCor = eCor.getValor();

        for (int mX = eRetangulo.getX(); mX < mX2; mX++) {

            int mY2 = eRetangulo.getY() + eRetangulo.getAltura();

            for (int mY = eRetangulo.getY(); mY < mY2; mY++) {
                drawPixelBruto(mX, mY, iCor);
            }


        }


    }

}
