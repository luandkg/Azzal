package apps.app_atzum.utils;

import apps.app_atzum.AtzumCreator;
import libs.arquivos.DSInterno;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSIndexador;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;

import java.awt.image.BufferedImage;

public class ArquivoAtzumGeral {

    private Lista<DSItem> mIndice;
    private Opcional<DSItem> op_planeta_relevo = Opcional.CANCEL();

    public ArquivoAtzumGeral() {
        String arquivo_atzum = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/atzum.ds");
        Opcional<DSItem> op_init = DS.buscar_item(arquivo_atzum, "@Atzum.index");

        if (Opcional.IS_OK(op_init)) {
            mIndice = DSIndexador.GET_INDEX(arquivo_atzum, "@Atzum.index");

            op_planeta_relevo = DSIndexador.GET_ITEM(mIndice, "@dados/relevo.qtt");

            if(op_planeta_relevo.isOK()){
                fmt.print("++ Tem Relevo !");
            }else{
                fmt.print("++ Relevo nao encontrado.");
            }


            DS.DUMP_ITENS(mIndice);
        }
    }

    public BufferedImage GET_MAPA_DE_RELEVO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_relevo.im").get());
        return mapa;
    }

    public BufferedImage GET_MAPA_DE_CONTORNO() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_regioes_contornos.im").get());
        return mapa;
    }
    public  BufferedImage GET_MAPA_DE_REGIOES() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_regioes.im").get());
        return mapa;
    }

    public  BufferedImage GET_MAPA_DE_OCEANOS() {
        BufferedImage mapa = DSInterno.IM_VISUALIZAR(DSIndexador.GET_ITEM(mIndice, "@imagem/atzum_oceanos.im").get());
        return mapa;
    }

    public int GET_RELEVO_ALTITUDE(int px,int py) {
        if(op_planeta_relevo.isOK()){
            return DSInterno.QTT_GET(op_planeta_relevo.get(), px, py);
        }else{
            return 0;
        }

    }
}
