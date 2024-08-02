package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.AtzumCreatorInfo;
import libs.arquivos.QTT;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;

public class AtzumCentralDados {

    public static void PROXIMIDADE_COM_OCENAO() {

        AtzumCreatorInfo.iniciar("AtzumCentralDados.PROXIMIDADE_COM_OCENAO");


        Lista<Entidade> mInformacoesDasCidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado.entts"));


        Atzum atzum = new Atzum();
        AtzumTerra atzum_terra = new AtzumTerra();


        QTT dados_oceano = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("oceanos.qtt"));


        int largura = atzum_terra.getLargura();
        int altura = atzum_terra.getAltura();


        for (Entidade cidade : mInformacoesDasCidades) {

            int cidade_x = cidade.atInt("X");
            int cidade_y = cidade.atInt("Y");


            int distancia_proximidade = Integer.MAX_VALUE;
            int mais_proximo = 0;

            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    int valor = dados_oceano.getValor(x, y);
                    if (valor > 0) {
                        if (Espaco2D.distancia_entre_pontos(cidade_x, cidade_y, x, y) < distancia_proximidade) {
                            distancia_proximidade = Espaco2D.distancia_entre_pontos(cidade_x, cidade_y, x, y);
                            mais_proximo = valor;
                        }
                    }
                }
            }

            fmt.print("Cidade {} - {} -->> {} dist = {} :: {}", cidade_x, cidade_y, mais_proximo, distancia_proximidade, atzum.GET_OCEANO(mais_proximo));

            cidade.at("Oceano_Nome", atzum.GET_OCEANO(mais_proximo));
            cidade.at("Oceano_Distancia", distancia_proximidade);

        }


        ENTT.GUARDAR(mInformacoesDasCidades, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado.entts"));


        AtzumCreatorInfo.terminar("AtzumCentralDados.PROXIMIDADE_COM_OCENAO");

        fmt.print("OK !");
    }


    public static void VER_AMOSTRAS() {

        //Lista<Entidade> dados_publicados = ENTT.ABRIR_COM_LIMITE(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado.entts"),30);

        // ENTT.EXIBIR_TABELA(dados_publicados);


        Lista<Entidade> mapa_sensores_v9 = ENTT.ABRIR_COM_LIMITE(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"), 10);
        ENTT.EXIBIR_TABELA(mapa_sensores_v9);

    }


    public static void NOMEAR_CIDADES() {

        fmt.print(">> Nomear cidades...");

        Lista<Entidade> cidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado.entts"));

        NOMEAR(cidades,706,955,"Ampz");
        NOMEAR(cidades,692,963,"Eczos");
        NOMEAR(cidades,690,949,"Talaque");

        NOMEAR(cidades,1140,755,"Aaz");

        ENTT.GUARDAR(cidades, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado.entts"));

        fmt.print(">> Tudo Ok !");
    }

    public static void NOMEAR(Lista<Entidade> cidades,int px,int py,String nome){

        Entidade e_cidade = ENTT.GET_SEMPRE(cidades,"Cidade",px+"::"+py);
        e_cidade.at("Nome",nome);

    }
}
