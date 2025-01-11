package apps.app_atzum.utils;

import apps.app_atzum.AtzumCreator;
import libs.arquivos.DSInterno;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSIndexador;
import libs.arquivos.ds.DSItem;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;

import java.awt.image.BufferedImage;

public class ArquivoAtzumGeral {

    private Lista<DSItem> mIndice;

    private Opcional<DSItem> op_planeta = Opcional.CANCEL();
    private Opcional<DSItem> op_planeta_relevo = Opcional.CANCEL();
    private Opcional<DSItem> op_planeta_oceanos = Opcional.CANCEL();
    private Opcional<DSItem> op_planeta_regioes = Opcional.CANCEL();
    private Opcional<DSItem> op_planeta_subregioes = Opcional.CANCEL();
    private Opcional<DSItem> op_planeta_placas_tectonicas = Opcional.CANCEL();

    public ArquivoAtzumGeral() {
        String arquivo_atzum = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/atzum.ds");
        Opcional<DSItem> op_init = DS.buscar_item(arquivo_atzum, "@Atzum.index");

        if (Opcional.IS_OK(op_init)) {

            mIndice = DSIndexador.GET_INDEX(arquivo_atzum, "@Atzum.index");

            op_planeta = DSIndexador.GET_ITEM(mIndice, "@dados/planeta.qtt");

            op_planeta_relevo = DSIndexador.GET_ITEM(mIndice, "@dados/relevo.qtt");
            op_planeta_oceanos = DSIndexador.GET_ITEM(mIndice, "@dados/oceanos.qtt");
            op_planeta_regioes = DSIndexador.GET_ITEM(mIndice, "@dados/regioes.qtt");
            op_planeta_subregioes = DSIndexador.GET_ITEM(mIndice, "@dados/subregioes.qtt");
            op_planeta_placas_tectonicas = DSIndexador.GET_ITEM(mIndice, "@dados/placas_tectonicas.qtt");


            DS.DUMP_ITENS(mIndice);
        }
    }

    public Lista<Entidade> GET_CIDADES(){
        return DSInterno.ENTT_ABRIR(DSIndexador.GET_ITEM(mIndice, "@parametros/cidades.entts").get());
    }

    public BufferedImage GET_MAPA_DE_PLACAS_TECTONICAS() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_tectonismo_placas.im").get());
        return mapa;
    }

    public BufferedImage GET_MAPA_DE_PLACAS_TECTONICAS_LIMITES() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_tectonismo_limites.im").get());
        return mapa;
    }

    public BufferedImage GET_MAPA_DE_RELEVO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_relevo.im").get());
        return mapa;
    }

    public BufferedImage GET_MAPA_DE_CONTORNO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_regioes_contornos.im").get());
        return mapa;
    }

    public BufferedImage GET_MAPA_DE_REGIOES() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_regioes.im").get());
        return mapa;
    }

    public BufferedImage GET_MAPA_DE_OCEANOS() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_oceanos.im").get());
        return mapa;
    }

    public int GET_PLANETA(int px, int py) {
        if (op_planeta.isOK()) {
            return DSInterno.QTT_GET(op_planeta.get(), px, py);
        } else {
            return 0;
        }
    }

    public int GET_RELEVO_ALTITUDE(int px, int py) {
        if (op_planeta_relevo.isOK()) {
            return DSInterno.QTT_GET(op_planeta_relevo.get(), px, py);
        } else {
            return 0;
        }
    }

    public int GET_OCEANO(int px, int py) {
        if (op_planeta_oceanos.isOK()) {
            return DSInterno.QTT_GET(op_planeta_oceanos.get(), px, py);
        } else {
            return 0;
        }
    }

    public int GET_REGIAO(int px, int py) {
        if (op_planeta_regioes.isOK()) {
            return DSInterno.QTT_GET(op_planeta_regioes.get(), px, py);
        } else {
            return 0;
        }
    }

    public int GET_SUBREGIAO(int px, int py) {
        if (op_planeta_subregioes.isOK()) {
            return DSInterno.QTT_GET(op_planeta_subregioes.get(), px, py);
        } else {
            return 0;
        }
    }



    public int GET_PLACA_TECTONICA(int px, int py) {
        if (op_planeta_relevo.isOK()) {
            return DSInterno.QTT_GET(op_planeta_placas_tectonicas.get(), px, py);
        } else {
            return 0;
        }
    }

}
