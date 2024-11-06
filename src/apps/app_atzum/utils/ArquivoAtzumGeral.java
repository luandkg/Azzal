package apps.app_atzum.utils;

import apps.app_atzum.AtzumCreator;
import libs.arquivos.DSInterno;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSIndexador;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;

import java.awt.image.BufferedImage;

public class ArquivoAtzumGeral {

    private Lista<DSItem> mIndice;

    public ArquivoAtzumGeral() {
        String arquivo_atzum = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/atzum.ds");
        Opcional<DSItem> op_init = DS.buscar_item(arquivo_atzum, "@Atzum.index");

        if (Opcional.IS_OK(op_init)) {
            mIndice = DSIndexador.GET_INDEX(arquivo_atzum, "@Atzum.index");
            DS.DUMP_ITENS(mIndice);
        }
    }

    public BufferedImage GET_MAPA_DE_RELEVO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_relevo.im").get());
        return mapa;
    }
}
