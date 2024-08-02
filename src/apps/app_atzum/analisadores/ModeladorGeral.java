package apps.app_atzum.analisadores;

import apps.app_atzum.utils.AtzumCreatorInfo;

public class ModeladorGeral {


    public static void CLIMA_E_VEGETACAO() {


        AtzumCreatorInfo.iniciar("AnalisadorClimatico.ANALISE_TEMPORAL_TRONARKO");

        AnalisadorClimatico.INIT();
        AnalisadorVegetacao.INIT();


        AtzumCreatorInfo.terminar("AnalisadorClimatico.ANALISE_TEMPORAL_TRONARKO");
        AtzumCreatorInfo.exibir_item("AnalisadorClimatico.ANALISE_TEMPORAL_TRONARKO");


    }


}
