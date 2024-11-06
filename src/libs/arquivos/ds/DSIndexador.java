package libs.arquivos.ds;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;

import java.nio.charset.StandardCharsets;

public class DSIndexador {


    public static void INDEX(String arquivo_ds, String item_ds_nome) {

        Opcional<DSItem> op_init = DS.buscar_item(arquivo_ds, item_ds_nome);

        if (Opcional.IS_OK(op_init)) {

            Lista<Entidade> indice = ENTT.CRIAR_LISTA();

            for (DSItem item : DS.ler_todos(arquivo_ds)) {
                Entidade e_item = ENTT.CRIAR_EM(indice);
                e_item.at("Index", item.getIndex());
                e_item.at("Status", item.getStatus());
                e_item.at("Nome", item.getNome());
                e_item.at("Inicio", item.getInicio());
                e_item.at("Fim", item.getFim());
                e_item.at("Tamanho", item.getTamanho());
            }

            byte[] bytes = ENTT.TO_DOCUMENTO(indice).getBytes(StandardCharsets.UTF_8);


            if (bytes.length < (op_init.get().getTamanho() - 8)) {

                DS.alterar_pre_alocado(arquivo_ds, item_ds_nome, bytes);

            } else {
                throw new RuntimeException("DS - Tamanho do item insuficiente : " + op_init.get().getTamanho() + " bytes, mas precisa de " + bytes.length + " bytes");
            }


        } else {
            throw new RuntimeException("DS - Item não encontrado : " + item_ds_nome);
        }


    }

    public static Lista<DSItem> GET_INDEX(String arquivo_ds, String item_ds_nome) {

        Lista<DSItem> itens = new Lista<DSItem> ();
        Lista<Entidade> indice = ENTT.PARSER(DS.buscar_item(arquivo_ds, item_ds_nome).get().getTextoPreAlocado());

        for (Entidade e : indice) {
            e.at("Arquivo", arquivo_ds);
            itens.adicionar(new DSItem(arquivo_ds,e.atInt("Index"),e.atInt("Status"),e.at("Nome"),e.atLong("Tamanho"),e.atLong("Inicio")));
        }

        return itens;
    }


    public static Opcional<DSItem> GET_ITEM(Lista<DSItem> indice, String item_nome) {

        Opcional<DSItem> ret = Opcional.CANCEL();

        for (DSItem e : indice) {
            if(e.isNome(item_nome)){
                ret.set(e);
                break;
            }
        }

        return ret;
    }


    public static void VER_TAMANHO( Lista<Entidade> indice ){

        for(Entidade item : indice){
            item.at("Tamanho", fmt.formatar_tamanho_precisao_dupla(item.atLong("Tamanho")));
        }
    }


}
