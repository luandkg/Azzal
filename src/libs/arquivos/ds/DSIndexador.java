package libs.arquivos.ds;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;

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

    public static Lista<Entidade> GET_INDEX(String arquivo_ds, String item_ds_nome) {
        Lista<Entidade> indice = ENTT.PARSER(DS.buscar_item(arquivo_ds, item_ds_nome).get().getTextoPreAlocado());

        for (Entidade e : indice) {
            e.at("Arquivo", arquivo_ds);
        }

        return indice;
    }


    public static Opcional<DSItem> GET_ITEM(Lista<Entidade> indice, String item_nome) {

        Opcional<DSItem> ret = Opcional.CANCEL();

        for (Entidade e : indice) {
            if(e.is("Nome",item_nome)){

                ret.set(new DSItem(e.at("Arquivo"),e.atInt("Index"),e.atInt("Status"),item_nome,e.atInt("Tamanho"),e.atInt("Inicio")));

                break;
            }
        }

        return ret;
    }
}
