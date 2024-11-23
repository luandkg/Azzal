package apps.app_atzum.utils;

import apps.app_atzum.AtzumCreator;
import libs.arquivos.DSInterno;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSIndexador;
import libs.arquivos.ds.DSItem;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;

import java.awt.image.BufferedImage;

public class ArquivoAtzumTronarko {

    private Lista<DSItem> mIndice;

    public ArquivoAtzumTronarko(String eTronarko) {
        String arquivo_atzum = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/atzum_tronarko_" + eTronarko + ".ds");

        //  DS.DUMP_TABELA(arquivo_atzum);

        Opcional<DSItem> op_init = DS.buscar_item(arquivo_atzum, "@AtzumTronarko.index");

        if (Opcional.IS_OK(op_init)) {
            mIndice = DSIndexador.GET_INDEX(arquivo_atzum, "@AtzumTronarko.index");



            DS.DUMP_ITENS(mIndice);
        }


    }

    public Lista<Entidade> getCidadesDadosPublicados() {
        Lista<Entidade> mCidadesDadosPublicados = DSInterno.ENTT_ABRIR(DSIndexador.GET_ITEM(mIndice, "@dados/tronarko_cidades_dados_publicados.entts").get());
        return mCidadesDadosPublicados;
    }

    public BufferedImage GET_MODELO_CLIMATICO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/modelo_climatico.im").get());
        return mapa;
    }

    public BufferedImage GET_MODELO_VEGETACAO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/modelo_vegetacao.im").get());
        return mapa;
    }

}
