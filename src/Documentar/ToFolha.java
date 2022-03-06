package Documentar;

import Azzal.Cores;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ToFolha {

    public void render(int largura,int altura, ArrayList<ParteTextual> objetos,String arquivo_png){

        Cor COR_FORTE = Cor.getHexCor("#e53935");
        Cor COR_FRACA = Cor.getHexCor("#ff6f60");


        BufferedImage imagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);


        Renderizador render = new Renderizador(imagem);
        render.drawRect_Pintado(0, 0, largura, altura, new Cor(255, 255, 255));

        Cores mCores = new Cores();

        Fonte mTituloPreto = new FonteRunTime(mCores.getPreto(), 22);
        mTituloPreto.setRenderizador(render);

        Fonte mPreto = new FonteRunTime(mCores.getPreto(), 12);
        mPreto.setRenderizador(render);


        int y = 0;

        for (ParteTextual objeto : objetos) {

            if (objeto.eDoTipo("CORES")) {

                if (objeto.getAtributos().size() == 2) {

                    String forte = objeto.getAtributos().get(0);
                    String fraca = objeto.getAtributos().get(1);

                    COR_FORTE = Cor.getHexCor(forte);
                    COR_FRACA = Cor.getHexCor(fraca);

                }

            } else if (objeto.eDoTipo("TITULO")) {

                render.drawRect_Pintado(0, y, largura, 100, COR_FORTE);

                if (objeto.getAtributos().size() > 0) {

                    int larg = mTituloPreto.getLarguraDe(objeto.getAtributos().get(0));
                    int met = larg / 2;

                    mTituloPreto.escreva((largura / 2) - met, y + 20, objeto.getAtributos().get(0));
                }

                if (objeto.getAtributos().size() > 1) {

                    int larg = mTituloPreto.getLarguraDe(objeto.getAtributos().get(1));
                    int met = larg / 2;

                    mTituloPreto.escreva((largura / 2) - met, y + 48, objeto.getAtributos().get(1));
                }

                y += 100;

            } else if (objeto.eDoTipo("SECAO")) {

                y += 20;
                render.drawRect_Pintado(80, y, largura - 160, 50, COR_FORTE);

                if (objeto.getAtributos().size() > 0) {

                    int larg = mTituloPreto.getLarguraDe(objeto.getAtributos().get(0));
                    int met = larg / 2;

                    mTituloPreto.escreva((largura / 2) - met, y + 10, objeto.getAtributos().get(0));
                }

                y += 50;
            } else if (objeto.eDoTipo("BLOCO")) {

                y += 20;

                render.drawRect_Pintado(0, y, 600, 40, COR_FRACA);
                render.drawRect_Pintado(0, y + 45, 600, 10, COR_FRACA);

                if (objeto.getAtributos().size() > 0) {
                    mTituloPreto.escreva(50, y + 5, objeto.getAtributos().get(0));
                }

                y += 60;

            } else if (objeto.eDoTipo("TEXTO")) {

                y += 20;
                mPreto.escreva(100, y, objeto.getConteudo());

            } else if (objeto.eDoTipo("ITEM")) {

                y += 20;
                if (objeto.getAtributos().size() > 0) {

                    render.drawRect_Pintado(130, y + 5, 10, 10, COR_FORTE);
                    render.drawRect_Pintado(130 + 3, y + 5 + 3, 4, 4, Cor.getHexCor("#ffffff"));

                    mPreto.escreva(150, y, objeto.getAtributos().get(0));
                }
            } else if (objeto.eDoTipo("ITEM_PRIMEIRO")) {

                y += 30;
                if (objeto.getAtributos().size() > 0) {

                    render.drawRect_Pintado(130, y + 5, 10, 10, COR_FORTE);
                    render.drawRect_Pintado(130 + 3, y + 5 + 3, 4, 4, Cor.getHexCor("#ffffff"));

                    mPreto.escreva(150, y, objeto.getAtributos().get(0));
                }

            } else if (objeto.eDoTipo("ITEM_ULTIMO")) {

                y += 20;
                if (objeto.getAtributos().size() > 0) {

                    render.drawRect_Pintado(130, y + 5, 10, 10, COR_FORTE);
                    render.drawRect_Pintado(130 + 3, y + 5 + 3, 4, 4, Cor.getHexCor("#ffffff"));

                    mPreto.escreva(150, y, objeto.getAtributos().get(0));
                }

                y += 20;

            } else if (objeto.eDoTipo("PULAR_PEQUENO")) {
                y += 30;
            }

        }


        render.exportarSemAlfa(arquivo_png);

    }
}
