package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.entt.Entidade;
import libs.luan.Aleatorio;
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


        for(int a=0;a<30;a++){

            Entidade item = new Entidade();
            item.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            item.at("Valor", PessoaNomeadorDeAkkax.get());
            item.at("TTS", Aleatorio.aleatorio_entre(1,50)+"/"+Aleatorio.aleatorio_entre(1,10) +"/"+Aleatorio.aleatorio_entre(6900,7100));

            tronarkum.adicionar(item);

        }



        tronarkum.exibir_colecao();
        tronarkum.exibir_indice();


        zeta.dump();

        zeta.fechar();
    }
}
