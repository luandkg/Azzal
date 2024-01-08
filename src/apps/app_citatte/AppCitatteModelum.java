package apps.app_citatte;

import apps.app_citatte.cidade_beta.AnaliseCitatte;
import apps.app_citatte.cidade_beta.Uttoza;
import apps.app_citatte.engenharia.EngenhariaRodoviaria;
import apps.app_citatte.urbanizacao.Urbanizador;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;


public class AppCitatteModelum extends Cena {

    private Cores mCores;
    private Citatte mCitatte;

    private int area_px = 0;
    private int area_py = 0;

    private Fonte mEscritor;

    private int habitavel = 0;
    private AnaliseCitatte mAnaliseCitatte;
    private Lista<Par<Ponto, Integer>> regioes;
    private boolean is_mover = false;

    @Override
    public void iniciar(Windows eWindows) {

        mCores = new Cores();

        mCitatte = new Citatte();

        regioes = new Lista<Par<Ponto, Integer>>();

        //   Akkone eAkkone = new Akkone();
        //  eAkkone.init(mCidade);

        // habitavel = eAkkone.habitavel;

        Uttoza eUttoza = new Uttoza();
        // eUttoza.init(mCidade);

        CitatteConstructor.criar_cidade(mCitatte.get(), mCitatte);
        CitatteConstructor.abrir("/home/luan/assets/cidadela.dkg", mCitatte.get(), mCitatte);

        //CitatteConstructor.abrir( "/home/luan/assets/cidadela_tritton.dkg",mCitatte.get(), mCitatte);
        //  CitatteConstructor.abrir("/home/luan/assets/cidadela_atto.dkg", mCitatte.get(), mCitatte);

        // CitatteConstructor.abrir("/home/luan/assets/cidadela_granttaz/cidadela.dkg", mCitatte.get(), mCitatte);


        Urbanizador.ORGANIZAR_ENDERECOS(mCitatte.get(), mCitatte.getAvenidas(), mCitatte);


        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

            if (area.getNome().startsWith("SUL")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getVerde());
            } else if (area.getNome().startsWith("NORTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getLaranja());
            } else if (area.getNome().startsWith("LESTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getRosa());
            } else if (area.getNome().startsWith("OESTE")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getAzul());
            } else if (area.getNome().startsWith("CENTRO")) {
                mCitatte.get().drawRect(area.getArea(), mCores.getTurquesa());
            } else {
                mCitatte.get().drawRect(area.getArea(), mCores.getBranco());
            }

            if (area.getNome().contains("ESPECIAL")) {

                if (area.getNome().contains("AMARELO")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getAmarelo());
                } else if (area.getNome().contains("AZUL")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getAzul());
                } else if (area.getNome().contains("ROSA")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getRosa());
                } else if (area.getNome().contains("MARROM")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getMarrom());
                } else if (area.getNome().contains("LARANJA")) {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getLaranja());
                } else {
                    mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getCinza());
                }

            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());


        String local_assets = "/home/luan/assets";
        mCitatte.get().exportarSemAlfa(local_assets + "/cidade_epsilon.png");

        area_px = 500;
        area_py = 500;


        // cidade_2();

        mEscritor = new FonteRunTime(mCores.getBranco(), "Arial", 20);
    }

    public void cidade_2() {

        String local_assets = "/home/luan/assets";

        int quad = 5000;

        Renderizador mCidade2 = new Renderizador(Imagem.criarEmBranco(quad, quad));
        mCidade2.drawRect_Pintado(0, 0, quad, quad, mCores.getPreto());
        CitatteConstructor.criar_cidade(mCidade2, new Citatte());
        mCidade2.exportarSemAlfa(local_assets + "/cidade.png");

    }

    public void cidade_3() {

        String local_assets = "/home/luan/assets";

        int quad = 10000;

        Renderizador mCidade2 = new Renderizador(Imagem.criarEmBranco(quad, quad));
        mCidade2.drawRect_Pintado(0, 0, quad, quad, mCores.getPreto());
        CitatteConstructor.criar_cidade(mCidade2, new Citatte());
        mCidade2.exportarSemAlfa(local_assets + "/cidade.png");

    }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        if (is_mover) {
            area_px = px;
            area_py = py;
        }


    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getPreto());

        g.drawImagem(100, 100, mCitatte.get().toImagemSemAlfa());

        g.drawRect(area_px - 50, area_py - 50, 100, 100, mCores.getVermelho());


        int x0 = area_px - 50;
        int y0 = area_py - 50;

        int x1 = x0 + 100;
        int y1 = y0 + 100;

        int indo_y = 0;

        int c_habitavel = 0;


        for (int y = y0; y < y1; y++) {
            int indo_x = 0;
            for (int x = x0; x < x1; x++) {
                Cor pixel = g.getPixel(x, y);
                if (!mCores.getVermelho().igual(pixel)) {
                    g.drawRect_Pintado(1500 + indo_x, 100 + indo_y, 4, 4, pixel);
                }

                if (mCores.getBranco().igual(pixel)) {
                    c_habitavel += 1;
                }


                indo_x += 4;
            }
            indo_y += 4;
        }

        g.drawRect(1500, 100, 400, 400, mCores.getVermelho());

        mEscritor.setRenderizador(g);

        mEscritor.escreva(1500, 530, "Posição : " + area_px + " :: " + area_py);
        mEscritor.escreva(1500, 600, "Habitável : " + c_habitavel);
        // mEscritor.escreva(1500, 700, "Regiões : " + regioes.getQuantidade());

        // Cor a = g.getPixel(area_px - 5, area_py - 5);

        // mEscritor.escreva(1500, 560, "Cor : " + a.toRGBString());


        Opcional<AreaAdministravel> area = mCitatte.procurar(area_px - 100, area_py - 100 - 5);
        if (area.isOK()) {
            mEscritor.escreva(1500, 700, "Area : " + area.get().getNome());
        }

        //  mEscritor.escreva(1500, 800, "Area : " + mCitatte.contagemAreas());

        is_mover = true;
    }


}
