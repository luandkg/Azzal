package apps.app_atzum.utils;

import apps.app_atzum.AtzumCreator;
import libs.arquivos.DSInterno;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSIndexador;
import libs.arquivos.ds.DSItem;
import libs.arquivos.dsvideo.DSVideo;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;

import java.awt.image.BufferedImage;

public class ArquivoAtzumTronarko {

    private Lista<DSItem> mIndice;
    private Lista<Entidade> mCidadesIndexadas;

    private boolean mIndiceCidadeCarregado = false;

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

    public Lista<Entidade> getCidadesDadosPublicadosIndicePorCidade() {
        if (!mIndiceCidadeCarregado) {
            mCidadesIndexadas = DSInterno.PARSER_ENTIDADES(DSIndexador.GET_ITEM(mIndice, "@dados/tronarko_cidades_dados_publicados_indice_por_cidade.entts").get());
            mIndiceCidadeCarregado = true;
        }
        return mCidadesIndexadas;
    }

    public Entidade GET_CIDADE_DADOS(String cidade_pos) {

        Entidade idx_cidade = ENTT.GET_SEMPRE(getCidadesDadosPublicadosIndicePorCidade(), "CidadePos", cidade_pos);
        DSItem dados_todas_cidades = DSIndexador.GET_ITEM(mIndice, "@dados/tronarko_cidades_dados_publicados.entts").get();

        return DSInterno.GET_ENTIDADE_INDEXADA_ULTRA_RAPIDO(dados_todas_cidades, idx_cidade);
    }

    public BufferedImage GET_MODELO_CLIMATICO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/modelo_climatico.im").get());
        return mapa;
    }

    public BufferedImage GET_MODELO_VEGETACAO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/modelo_vegetacao.im").get());
        return mapa;
    }

    public BufferedImage GET_MAPA_MODELO(String modelo, String modelo_id) {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/" + modelo + "/modelo_" + modelo_id + ".im").get());
        return mapa;
    }


    public DSVideo GET_VIDEO(String nome) {

        Opcional<DSItem> ret= DSIndexador.GET_ITEM(mIndice, nome);


        if(ret.isOK()){
            DSItem ret_item = ret.get();

            fmt.print(">> {}",ret_item.getNome());


            long ptr_inicio = ret_item.getInicio();
            long ptr_fim = ret_item.getFim();


            return new DSVideo(ret_item);

        }

        return null;
    }
}
