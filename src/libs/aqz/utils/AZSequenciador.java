package libs.aqz.utils;

import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;

public class AZSequenciador {

    public static void criar_sequencial(ParticaoMestre eParticaoPrimaria, String eNome, int eInicio, int ePasso) {

        Entidade objeto = new Entidade();
        objeto.at("Nome", eNome);
        objeto.at("Sequencia", eInicio);
        objeto.at("Passo", ePasso);
        eParticaoPrimaria.adicionarTX(objeto);

    }

    public static Opcional<ItemDoBancoTX> procurar_sequencial(ParticaoMestre eParticaoPrimaria, String eNome) {

        Opcional<ItemDoBancoTX> ret = new Opcional<ItemDoBancoTX>();

        for (ItemDoBancoTX item : eParticaoPrimaria.getItensTX()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (objeto.is("Nome", eNome)) {
                ret.set(item);
                break;
            }
            //  System.out.println(objeto.toString());
        }

        return ret;
    }

    public static int aumentar_sequencial(ParticaoMestre eParticaoPrimaria, String eNome) {

        int chave = 0;


        for (ItemDoBancoTX item : eParticaoPrimaria.getItensTX()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (objeto.is("Nome", eNome)) {

                chave = objeto.atIntOuPadrao("Sequencia", 0);

                int passo = objeto.atIntOuPadrao("Passo", 1);
                objeto.at("Sequencia", chave + passo);
                item.atualizarTX(objeto);

                break;
            }
            // System.out.println(objeto.toDocumento());
        }


        return chave;
    }

    public static void zerar_sequencial(ParticaoMestre eParticaoPrimaria, String eNome) {


        for (ItemDoBancoTX item : eParticaoPrimaria.getItensTX()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (objeto.is("Nome", eNome)) {
                objeto.at("Sequencia", 0);
                item.atualizarTX(objeto);
                break;
            }
        }

    }

    public static int sequencia(ItemDoBancoTX item) {
        Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
        return objeto.atIntOuPadrao("Sequencia", 0);
    }


    public static void organizar_sequencial(ParticaoMestre eParticaoPrimaria, String eNome) {

        Opcional<ItemDoBancoTX> item_objeto_contador = procurar_sequencial(eParticaoPrimaria, eNome);

        if (item_objeto_contador.temValor()) {
            sequencia(item_objeto_contador.get());
        } else {
            criar_sequencial(eParticaoPrimaria, eNome, 0, 1);
        }


    }

    public static int get_sequencial_contador(ParticaoMestre eParticaoPrimaria, String eNome) {

        Opcional<ItemDoBancoTX> item_objeto_contador = procurar_sequencial(eParticaoPrimaria, eNome);

        int ret = 0;

        if (item_objeto_contador.temValor()) {
            ret = sequencia(item_objeto_contador.get());
        } else {
            criar_sequencial(eParticaoPrimaria, eNome, 0, 1);
            ret = 0;
        }

        return ret;
    }

    public static void DUMP(ParticaoMestre eParticaoPrimaria, String eNome) {

        Lista<Entidade> objs = new Lista<Entidade>();

        for (ItemDoBancoTX item : eParticaoPrimaria.getItensTX()) {
            Entidade objeto = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            objs.adicionar(objeto);
        }

        ENTT.EXIBIR_TABELA(objs);

    }
}
