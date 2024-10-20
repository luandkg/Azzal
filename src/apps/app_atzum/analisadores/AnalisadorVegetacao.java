package apps.app_atzum.analisadores;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_atzum.utils.Rasterizador;
import apps.app_campeonatum.VERIFICADOR;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Par;
import libs.luan.Strings;
import libs.luan.fmt;

import java.awt.image.BufferedImage;

public class AnalisadorVegetacao {

    public static void INIT() {

        AtzumCreatorInfo.iniciar("AnalisadorVegetacao.INIT");

        Lista<Entidade> mapa_sensores = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v8.entts"));


        for (Entidade sensor : mapa_sensores) {

            String hiperestacao = sensor.at("Hiperestacao");

            int nevasca = sensor.atInt("Nevasca");
            int chuva = sensor.atInt("Chuva");
            int secura = sensor.atInt("Secura");

            if (hiperestacao.contains("FRIO") || hiperestacao.contains("ESFRIANDO")) {

                // sensor.at("Vegetacao", "DESERTO_DE_GELO");

                if (nevasca >= 25 && nevasca > (chuva + secura)) {
                    sensor.at("Vegetacao", "TUNDRA");
                } else if (chuva >= 25 && chuva > (nevasca + secura)) {

                    if (nevasca > secura) {
                        sensor.at("Vegetacao", "FLORESTA_TEMPERADA");
                    } else {
                        sensor.at("Vegetacao", "TAIGA");
                    }


                } else {
                    sensor.at("Vegetacao", "DESERTO_DE_GELO");
                }


            } else if (hiperestacao.contains("QUENTE") || hiperestacao.contains("ESQUENTANDO")) {

                if (nevasca >= 25 && nevasca > (chuva + secura)) {
                    sensor.at("Vegetacao", "FLORESTA_TEMPERADA");
                } else if (chuva >= 25 && chuva > (nevasca + secura)) {
                    sensor.at("Vegetacao", "FLORESTA_TROPICAL");
                } else {

                    if (nevasca > 25 || chuva > 25) {
                        sensor.at("Vegetacao", "SAVANA");
                    } else {
                        sensor.at("Vegetacao", "DESERTO");
                    }

                }


            } else if (hiperestacao.contains("SAZONAL") || hiperestacao.contains("AMBIENTE")) {

                if (nevasca >= 25 && (nevasca >= (chuva + secura)) || (nevasca > (chuva - secura))) {
                    sensor.at("Vegetacao", "FLORESTA_TEMPERADA");
                } else if (chuva >= 25 && chuva > (nevasca + secura)) {
                    sensor.at("Vegetacao", "FLORESTA_TROPICAL");
                } else {
                    sensor.at("Vegetacao", "SAVANA");
                }


            }

            if (sensor.isVazio("Vegetacao")) {
                fmt.print("{}", sensor.toTexto());
            }


        }


        ENTT.EXIBIR_TABELA_COM_TITULO(mapa_sensores, "VEGETACAO");
        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.DISPERSAO(mapa_sensores, "Vegetacao"), "VEGETACAO");


        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.COLETAR_VAZIO(mapa_sensores, "Vegetacao"), "VEGETACAO - #PROBLEMA");


        VERIFICADOR.IGUALDADE(0, ENTT.COLETAR_VAZIO(mapa_sensores, "Vegetacao").getQuantidade());


        if (ENTT.CONTAGEM(mapa_sensores) > 0) {
            //   return;
        }

        ENTT.GUARDAR(mapa_sensores, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v9.entts"));


        ENTT.EXIBIR_TABELA(ENTT.COLETAR(mapa_sensores, "Vegetacao", "TAIGA"));






        AtzumCreatorInfo.terminar("AnalisadorVegetacao.INIT");

    }



}
