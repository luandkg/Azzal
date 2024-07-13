package algoritmos;

import libs.arquivos.QTT;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.tempo.Calendario;

public class Teste {

    public static void init(){


        PastaFS pasta = new PastaFS("/home/luan/assets");


        String arquivo = pasta.getArquivo("oi.mqtt");

        DS.limpar(arquivo);

        DS.adicionar_pre_alocado(arquivo,"cabecalho.entt",100*1024);


        DS.adicionar(arquivo,"QA.qtt",QTT.toBytes(QTT.criar(100,100)));
        DS.adicionar(arquivo,"QB.qtt",QTT.toBytes(QTT.criar(100,100)));
        DS.adicionar(arquivo,"QC.qtt",QTT.toBytes(QTT.criar(100,100)));


        Lista<Entidade> cabecalho = new Lista<Entidade>();
        Entidade info_ddc = new Entidade();
        info_ddc.at("Tipo","Info");
        info_ddc.at("DDC", Calendario.getTempoCompleto());
        info_ddc.at("QTTs", 0);

        cabecalho.adicionar(info_ddc);

        fmt.print("-- {} : {}  {}",fmt.espacar_depois("ITEM",30),fmt.espacar_depois("TAMANHO",10),"INÍCIO DADOS");
        fmt.print("");

        for(DSItem item : DS.ler_todos(arquivo)){
            fmt.print("++ {} : {}  {}",fmt.espacar_depois(item.getNome(),30),fmt.espacar_depois(item.getTamanho(),10),item.getInicio());

            if(item.getNome().endsWith(".qtt")){
                Entidade ent_qtt = new Entidade();
                ent_qtt.at("Tipo","QTT");
                ent_qtt.at("Inicio",item.getInicio());
                cabecalho.adicionar(ent_qtt);

                info_ddc.at("QTTs",  info_ddc.atInt("QTTs")+1);

            }

        }

        Opcional<DSItem> item_cabecalho = DS.buscar_item(arquivo,"cabecalho.entt");

        if(item_cabecalho.isOK()){

            Lista<Byte> bytes = new Lista<>();
            for(Byte b : ENTT.GUARDAR_DOCUMENTO(cabecalho).getBytes()){
                bytes.adicionar(b);
            }

            DS.atualizar_pre_alocado(item_cabecalho.get(),bytes);

        }

        Opcional<DSItem> item_cabecalho_v2 = DS.buscar_item(arquivo,"cabecalho.entt");
        if(item_cabecalho_v2.isOK()) {
            fmt.print("Valor : {}",item_cabecalho_v2.get().getTextoPreAlocado());
        }


    }

}
