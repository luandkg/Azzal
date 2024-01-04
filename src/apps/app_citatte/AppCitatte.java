package apps.app_citatte;

import apps.app_citatte.cidade_beta.CidadeItem;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.mockui.Interface.Acao;
import libs.mockui.Interface.BotaoCor;
import libs.mockui.Interface.Clicavel;


public class AppCitatte extends Cena {

    private Cores mCores;

    private Clicavel mClicavel;

    private Lista<CidadeItem> mItens;

    public static final int FERRAMENTA_LOTE = 1;
    public static final int FERRAMENTA_ESTRADA = 2;

    private int mFerramenta = 0;

    @Override
    public void iniciar(Windows eWindows) {

        mCores = new Cores();
        mItens = new Lista<CidadeItem>();

        mClicavel = new Clicavel();


        BotaoCor BTN_LOTE = mClicavel.criarBotaoCor(new BotaoCor(100, 100, 50, 50, new Cor(200, 0, 0)));

        BTN_LOTE.setAcao(new Acao() {
            @Override
            public void onClique() {
                mFerramenta = FERRAMENTA_LOTE;
            }
        });

        BotaoCor BTN_ESTRADA = mClicavel.criarBotaoCor(new BotaoCor(100, 200, 50, 50, new Cor(150, 100, 0)));

        BTN_ESTRADA.setAcao(new Acao() {
            @Override
            public void onClique() {
                mFerramenta = FERRAMENTA_ESTRADA;
            }
        });

        BotaoCor BTN_GUARDAR = mClicavel.criarBotaoCor(new BotaoCor(100, 300, 50, 50, new Cor(150, 100, 0)));

        BTN_GUARDAR.setAcao(new Acao() {
            @Override
            public void onClique() {

                DKG documento = new DKG();
                DKGObjeto raiz = documento.unicoObjeto("Citatte");

                for (CidadeItem pt : mItens) {

                    if (pt.isLote()) {

                        DKGObjeto item = raiz.criarObjeto("Item");
                        item.identifique("Tipo", "Lote");
                        item.identifique("X", pt.getX());
                        item.identifique("Y", pt.getY());

                    } else if (pt.isEstrada()) {
                        DKGObjeto item = raiz.criarObjeto("Item");
                        item.identifique("Tipo", "Estrada");
                        item.identifique("X", pt.getX());
                        item.identifique("Y", pt.getY());
                    }

                }

                String local_assets = "/home/luan/assets";
                documento.salvar(local_assets + "/citatte.dkg");


            }
        });


        BotaoCor BTN_LIMPAR = mClicavel.criarBotaoCor(new BotaoCor(100, 800, 100, 50, new Cor(26, 188, 156)));

        BTN_LIMPAR.setAcao(new Acao() {
            @Override
            public void onClique() {
                mItens.limpar();
            }
        });


        String local_assets = "/home/luan/assets";

        DKG documento = DKG.ABRIR_DO_ARQUIVO(local_assets + "/citatte.dkg");
        DKGObjeto raiz = documento.unicoObjeto("Citatte");
        for (DKGObjeto item : raiz.getObjetos()) {
            if (item.identifique("Tipo").isValor("Lote")) {
                mItens.adicionar(new CidadeItem(CidadeItem.LOTE, item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)));
            } else if (item.identifique("Tipo").isValor("Estrada")) {
                mItens.adicionar(new CidadeItem(CidadeItem.ESTRADA, item.identifique("X").getInteiro(0), item.identifique("Y").getInteiro(0)));
            }
        }


    }

    @Override
    public void update(double dt) {

        int px = getWindows().getMouse().getX();
        int py = getWindows().getMouse().getY();

        if (getWindows().getMouse().isClicked()) {

            if (mFerramenta == FERRAMENTA_LOTE) {

                if (px > 300) {
                    mItens.adicionar(new CidadeItem(CidadeItem.LOTE, px - 5, py - 10));
                }
            } else if (mFerramenta == FERRAMENTA_ESTRADA) {

                if (px > 300) {
                    mItens.adicionar(new CidadeItem(CidadeItem.ESTRADA, px - 5, py - 10));
                }

            }


            mClicavel.update(dt, px, py, getWindows().getMouse().isClicked());


            getWindows().getMouse().liberar();
        }


    }

    @Override
    public void draw(Renderizador g) {

        g.limpar(mCores.getBranco());

        for (CidadeItem pt : mItens) {

            if (pt.isLote()) {
                g.drawRect(pt.getX(), pt.getY(), 10, 10, new Cor(255, 0, 0));
            } else if (pt.isEstrada()) {
                g.drawRect_Pintado(pt.getX(), pt.getY(), 10, 10, new Cor(200, 150, 0));
            }

        }

        mClicavel.onDraw(g);

    }
}
