package libs.arquivos;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Opcional;
import libs.tronarko.Tronarko;

public class DM {


    public static void ATUALIZAR(String eArquivo, String eNome, String eConteudo) {

        Opcional<DSItem> op_status = DS.buscar_item(eArquivo, "@Status.entt");
        Opcional<DSItem> op_historico = DS.buscar_item(eArquivo, "@Historico.entts");


        if (!Opcional.IS_OK(op_status)) {
            DS.adicionar_pre_alocado(eArquivo, "@Status.entt", Matematica.KB(10));

            Entidade e_status = new Entidade();
            e_status.at("Criado", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Inserido", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Corrente", "");
            e_status.at("Status", "CRIADO");

            DS.alterar_pre_alocado(eArquivo, "@Status.entt", ENTT.TO_DOCUMENTO(e_status));
        }

        if (!Opcional.IS_OK(op_historico)) {
            DS.adicionar_pre_alocado(eArquivo, "@Historico.entts", Matematica.MB(1));
            DS.alterar_pre_alocado(eArquivo, "@Historico.entts", ENTT.TO_DOCUMENTO(ENTT.CRIAR_LISTA()));
        }

        op_status = DS.buscar_item(eArquivo, "@Status.entt");
        op_historico = DS.buscar_item(eArquivo, "@Historico.entts");


        VERIFICADOR.DEVE_SER_VERDADEIRO(op_status.isOK(), "@Status : não encontrado !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(op_historico.isOK(), "@Historico : não encontrado !");


        Entidade e_status = ENTT.PARSER_ENTIDADE(op_status.get().getTextoPreAlocado());
        Lista<Entidade> historico_dados = DSInterno.PARSER_ENTIDADES_PRE_ALOCADO(op_historico.get());

        if (e_status.is("Corrente", eNome)) {

            DS.substituir_ultimo(eArquivo, eNome, eConteudo);

            e_status.at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Status", "ATUALIZADO");

            DS.alterar_pre_alocado(eArquivo, "@Status.entt", ENTT.TO_DOCUMENTO(e_status));

            Entidade e_hist = ENTT.GET_SEMPRE(historico_dados, "Item", eNome);
            e_hist.at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
            e_hist.at("Atualizacoes", e_hist.atIntOuPadrao("Atualizacoes", 0) + 1);

        } else {

            DS.adicionar(eArquivo, eNome, eConteudo);


            e_status.at("Inserido", Tronarko.getTronAgora().getTextoZerado());
            e_status.at("Corrente", eNome);
            e_status.at("Status", "INSERIDO");

            DS.alterar_pre_alocado(eArquivo, "@Status.entt", ENTT.TO_DOCUMENTO(e_status));

            Entidade e_hist = ENTT.GET_SEMPRE(historico_dados, "Item", eNome);
            e_hist.at("Inserido", Tronarko.getTronAgora().getTextoZerado());

        }

        DS.alterar_pre_alocado(eArquivo, "@Historico.entts", ENTT.TO_DOCUMENTO(historico_dados));

    }


    public static Opcional<DSItem> GET_ULTIMO(String eArquivo) {

        Opcional<DSItem> ultimo_item = Opcional.CANCEL();

        for (DSItem item : DS.ler_todos(eArquivo)) {
            if (!item.isNome("@Status.entt") && !item.isNome("@Historico.entts")) {
                ultimo_item.set(item);
            }
        }

        return ultimo_item;
    }

    public static void DUMP(String eArquivo) {

        Lista<DSItem> historico = new Lista<DSItem>();

        for (DSItem item : DS.ler_todos(eArquivo)) {
            if (!item.isNome("@Status.entt") && !item.isNome("@Historico.entts")) {
                historico.adicionar(item);
            }
        }

        DS.DUMP_ITENS(historico);

    }

    public static void DUMP_CONTEUDO(String eArquivo) {

        Lista<DSItem> historico = new Lista<DSItem>();

        for (DSItem item : DS.ler_todos(eArquivo)) {
            if (!item.isNome("@Status.entt") && !item.isNome("@Historico.entts")) {
                historico.adicionar(item);
            }
        }

        DS.DUMP_ITENS_CONTEUDO(historico);

    }


    public static void DUMP_HISTORICO(String eArquivo) {

        Opcional<DSItem> op_historico = DS.buscar_item(eArquivo, "@Historico.entts");

        Lista<Entidade> historico_dados = DSInterno.PARSER_ENTIDADES_PRE_ALOCADO(op_historico.get());


        ENTT.SEQUENCIAR(historico_dados, "ID", 0);

        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(historico_dados, "ID");
        ENTT.ATRIBUTO_DEPOIS_DE(historico_dados, "ID", "Ordem");

        if (ENTT.TEM(historico_dados)) {

            int quantidade = ENTT.CONTAGEM(historico_dados);

            ENTT.ATRIBUTO_TODOS(historico_dados, "Ordem", "Interno");

            if (quantidade == 1) {
                ENTT.GET_PRIMEIRO(historico_dados).at("Ordem", "PrimeiroUltimo");
            } else {
                ENTT.GET_PRIMEIRO(historico_dados).at("Ordem", "Primeiro");
                ENTT.GET_ULTIMO(historico_dados).at("Ordem", "Ultimo");
            }


        }

        ENTT.EXIBIR_TABELA(historico_dados);
    }

    public static void LIMPAR(String eArquivo) {
        DS.limpar(eArquivo);
    }
}
