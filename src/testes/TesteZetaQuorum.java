package testes;

import libs.entt.Entidade;
import libs.luan.fmt;
import libs.tronarko.Tronarko;
import libs.zettaquorum.ZettaColecao;
import libs.zettaquorum.ZettaQuorum;

public class TesteZetaQuorum {

    public static void init() {

        fmt.print("----------------- ZETA QUORUM ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaQuorum zeta = new ZettaQuorum(arquivo_zeta);

        ZettaColecao tronarkum = zeta.getColecaoSempre("@Tronarkum");


        Entidade item = new Entidade();
        item.at("Tron", Tronarko.getTronAgora().getTextoZerado());

        tronarkum.adicionar(item);


        tronarkum.exibir_colecao();
        tronarkum.exibir_indice();


        //zeta.dump();

        zeta.fechar();
    }
}
