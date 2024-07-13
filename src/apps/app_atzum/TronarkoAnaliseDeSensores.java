package apps.app_atzum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class TronarkoAnaliseDeSensores {

    public static void init(){

        fmt.print(">> Obtendo dados dos sensores...");

        Lista<Entidade> sensores_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

        fmt.print("Sensores : {}",ENTT.CONTAGEM(sensores_dados));

        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_dados),"SENSORES - AMOSTRA");

        Unico<String> strings_table = new Unico<String>(Strings.IGUALAVEL());

        for(Entidade sensor : sensores_dados){
            for(int superarko=1;superarko<=500;superarko++){

                String v1 = sensor.at("FC"+superarko);
                String v2 = sensor.at("M"+superarko);

                if(!v1.isEmpty()){
                    strings_table.item(v1);
                }
                if(!v2.isEmpty()){
                    strings_table.item(v2);
                }

            }
        }

        Lista<Entidade> e_strings_table = new Lista<Entidade>();


        for(String item : strings_table){
            e_strings_table.adicionar(ENTT.CRIAR("ID",e_strings_table.getQuantidade(),"Item",item));
        }

        ENTT.EXIBIR_TABELA_COM_TITULO(e_strings_table,"STRING - TABLE");



        for(Entidade sensor : sensores_dados){
            for(int superarko=1;superarko<=500;superarko++){

                String v1 = sensor.at("FC"+superarko);
                String v2 = sensor.at("M"+superarko);

                if(!v1.isEmpty()) {
                    Opcional<Entidade> item_v1 = ENTT.GET_OPCIONAL(e_strings_table,"Item",v1);
                    if(item_v1.isOK()){
                        sensor.at("FC"+superarko,"#"+item_v1.get().at("ID"));
                    }
                }

                if(!v2.isEmpty()) {
                    Opcional<Entidade> item_v2 = ENTT.GET_OPCIONAL(e_strings_table,"Item",v2);
                    if(item_v2.isOK()){
                        sensor.at("M"+superarko,"#"+item_v2.get().at("ID"));
                    }
                }



            }
        }



         ENTT.GUARDAR(sensores_dados,AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));

    }
}
