package apps.app_atzum.utils;

import apps.app_atzum.AtzumCreator;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.tronarko.Tron;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;

public class AtzumCreatorInfo {

    public static void iniciar(String nome) {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("logs/AtzumCreatorInfo.entts");

        Lista<Entidade> logs = ENTT.ABRIR(arquivo);

        Entidade chave = ENTT.GET_SEMPRE(logs, "Item", nome);

        if (chave.at("Primeiro").isEmpty()) {
            chave.at("Primeiro", Tronarko.getTronAgora().getTextoZerado());
        }

        chave.at("Iniciado", Tronarko.getTronAgora().getTextoZerado());
        chave.at("IniciadoContagem", chave.atIntOuPadrao("IniciadoContagem", 0) + 1);

        ENTT.GUARDAR(logs, arquivo);
    }


    public static void terminar(String nome) {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("logs/AtzumCreatorInfo.entts");

        Lista<Entidade> logs = ENTT.ABRIR(arquivo);

        Entidade chave = ENTT.GET_SEMPRE(logs, "Item", nome);

        chave.at("Terminado", Tronarko.getTronAgora().getTextoZerado());
        chave.at("TerminadoContagem", chave.atIntOuPadrao("TerminadoContagem", 0) + 1);

        int interrompido = chave.atIntOuPadrao("IniciadoContagem", 0) - chave.atIntOuPadrao("TerminadoContagem", 0);

        chave.at("Sucesso", chave.atIntOuPadrao("TerminadoContagem", 0));
        chave.at("Interrompido", interrompido);

        ENTT.GUARDAR(logs, arquivo);
    }


    public static void exibir() {

        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("logs/AtzumCreatorInfo.entts");
        Lista<Entidade> logs = ENTT.ABRIR(arquivo);

        ENTT.EXIBIR_TABELA(logs);

    }

    public static void exibir_item(String nome) {
        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("logs/AtzumCreatorInfo.entts");

        Lista<Entidade> logs = ENTT.ABRIR(arquivo);

        Entidade chave = ENTT.GET_SEMPRE(logs, "Item", nome);

        if (!chave.at("Iniciado").isEmpty() && !chave.at("Terminado").isEmpty()) {

            Tron iniciado = StringTronarko.PARSER_TRON(chave.at("Iniciado"));
            Tron terminado = StringTronarko.PARSER_TRON(chave.at("Terminado"));

            if(iniciado.getTozte().isIgual(terminado.getTozte())){

                long uzzons = Tronarko.HAZDE_DIFERENCA(terminado.getHazde(),iniciado.getHazde());
                fmt.print("{} >>> + {} uz",nome,uzzons);

            }

        }


    }

    public static void renomear_item(String nome_antigo,String nome_novo) {
        String arquivo = AtzumCreator.LOCAL_GET_ARQUIVO("logs/AtzumCreatorInfo.entts");

        Lista<Entidade> logs = ENTT.ABRIR(arquivo);

        Opcional<Entidade> chave = ENTT.GET_OPCIONAL(logs, "Item", nome_antigo);
        if(chave.isOK()){
            chave.get().at("Item",nome_novo);
        }

        ENTT.GUARDAR(logs,arquivo);

    }

}
