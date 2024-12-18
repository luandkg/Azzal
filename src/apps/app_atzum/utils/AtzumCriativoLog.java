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

public class AtzumCriativoLog {

    public static String GET_ARQUIVO(){
        return AtzumCreator.LOCAL_GET_ARQUIVO("logs/atzum_logs.entts");
    }

    public static void iniciar(String nome) {


        Lista<Entidade> logs = ENTT.ABRIR(GET_ARQUIVO());

        Entidade chave = ENTT.GET_SEMPRE(logs, "Item", nome);

        if (chave.at("Primeiro").isEmpty()) {
            chave.at("Primeiro", Tronarko.getTronAgora().getTextoZerado());
        }

        chave.at("Iniciado", Tronarko.getTronAgora().getTextoZerado());
        chave.at("IniciadoContagem", chave.atIntOuPadrao("IniciadoContagem", 0) + 1);

        ENTT.GUARDAR(logs, GET_ARQUIVO());
    }


    public static void terminar(String nome) {


        Lista<Entidade> logs = ENTT.ABRIR(GET_ARQUIVO());

        Entidade chave = ENTT.GET_SEMPRE(logs, "Item", nome);

        chave.at("Terminado", Tronarko.getTronAgora().getTextoZerado());
        chave.at("TerminadoContagem", chave.atIntOuPadrao("TerminadoContagem", 0) + 1);

        int interrompido = chave.atIntOuPadrao("IniciadoContagem", 0) - chave.atIntOuPadrao("TerminadoContagem", 0);

        chave.at("Sucesso", chave.atIntOuPadrao("TerminadoContagem", 0));
        chave.at("Interrompido", interrompido);

        ENTT.GUARDAR(logs, GET_ARQUIVO());
    }


    public static void exibir() {

        Lista<Entidade> logs = ENTT.ABRIR(GET_ARQUIVO());

        ENTT.EXIBIR_TABELA(logs);

    }

    public static void exibir_item(String nome) {

        Lista<Entidade> logs = ENTT.ABRIR(GET_ARQUIVO());

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

        Lista<Entidade> logs = ENTT.ABRIR(GET_ARQUIVO());

        Opcional<Entidade> chave = ENTT.GET_OPCIONAL(logs, "Item", nome_antigo);
        if(chave.isOK()){
            chave.get().at("Item",nome_novo);
        }

        ENTT.GUARDAR(logs,GET_ARQUIVO());

    }

}
