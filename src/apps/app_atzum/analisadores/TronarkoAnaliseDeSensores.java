package apps.app_atzum.analisadores;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import libs.arquivos.Quadrum;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class TronarkoAnaliseDeSensores {

    public static void init(){

        fmt.print(">> Obtendo dados dos sensores...");

        Lista<Entidade> sensores = Atzum.GET_SENSORES();


        String arquivo_sensores_por_sensor_quadrum = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.qa");
        Quadrum sensores_organizando = new Quadrum(arquivo_sensores_por_sensor_quadrum);
        sensores_organizando.abrir();

        fmt.print("Sensores : {}",ENTT.CONTAGEM(sensores));

      //  ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_dados),"SENSORES - AMOSTRA");

        Unico<String> strings_table = new Unico<String>(Strings.IGUALAVEL());

        for(Entidade p_sensor : sensores){
            for(int superarko=1;superarko<=500;superarko++){

                Entidade e_sensor = ENTT.PARSER_ENTIDADE(sensores_organizando.get(p_sensor.atInt("SensorID"), superarko));


                String v1 = e_sensor.at("FC"+superarko);
                String v2 = e_sensor.at("M"+superarko);

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



        for(Entidade p_sensor : sensores){
            for(int superarko=1;superarko<=500;superarko++){

                Entidade e_sensor = ENTT.PARSER_ENTIDADE(sensores_organizando.get(p_sensor.atInt("SensorID"), superarko));

                String v1 = e_sensor.at("FC"+superarko);
                String v2 = e_sensor.at("M"+superarko);

                if(!v1.isEmpty()) {
                    Opcional<Entidade> item_v1 = ENTT.GET_OPCIONAL(e_strings_table,"Item",v1);
                    if(item_v1.isOK()){
                        e_sensor.at("FC"+superarko,"#"+item_v1.get().at("ID"));
                    }
                }

                if(!v2.isEmpty()) {
                    Opcional<Entidade> item_v2 = ENTT.GET_OPCIONAL(e_strings_table,"Item",v2);
                    if(item_v2.isOK()){
                        e_sensor.at("M"+superarko,"#"+item_v2.get().at("ID"));
                    }
                }



            }
        }



      //   ENTT.GUARDAR(sensores_dados,AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));

    }
}
