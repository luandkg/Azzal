package apps.app_momentum;

import libs.aqz.AQZTX;
import libs.aqz.colecao.AZInternamenteTX;
import libs.aqz.colecao.ColecaoTX;
import libs.armazenador.Debugador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.fmt;

public class HarrempluzCreator {

    public static void criar() {


        String arquivo_banco = "/home/luan/assets/tron.az";

        AQZTX.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);

        AZInternamenteTX aqz = new AZInternamenteTX(arquivo_banco);


        int t1 = 6900;
        int t2 = 7200;

        for (int t = t1; t < t2; t++) {

            ColecaoTX colecao_tronarko = aqz.colecao_orgarnizar_e_obter("Tronarko" + t);
            colecao_tronarko.zerarSequencial();
            colecao_tronarko.limpar();

            for (int m = 1; m <= 50; m++) {

                for (int s = 1; s <= 10; s++) {
                    Entidade item = Harrumpluz.construir("S" + s);
                    item.at("Tronarko", t);
                    item.at("Megarko", m);
                    colecao_tronarko.adicionar(item);

                    System.out.println(" @@ -->> Adicionando " + fmt.espacar_antes(m, 3) + " / " + fmt.espacar_antes(t, 3) + " :: " + ENTT.TO_DOCUMENTO(item).length());

                }


            }

            Debugador.debug_colecionador_completo(aqz);

        }


        Debugador.debug_colecionador_completo(aqz);


        aqz.fechar();

    }


}
