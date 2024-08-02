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


        Cores mCores = new Cores();

        AtzumTerra mapa_planeta = new AtzumTerra();
        Renderizador render_mapa_climatico = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(render_mapa_climatico, mCores.getAmarelo(), mCores.getBranco());

        Lista<Par<Ponto, String>> sensores_dados_climaticos = new Lista<Par<Ponto, String>>();

        for (Entidade sensor : mapa_sensores) {

            sensor.at("X", Strings.GET_ATE(sensor.at("Sensor"), ":"));
            sensor.at("Y", Strings.GET_REVERSO_ATE(sensor.at("Sensor"), ":"));

            int px = sensor.atInt("X");
            int py = sensor.atInt("Y");

            String hiperestacao = sensor.at("Hiperestacao");

            sensores_dados_climaticos.adicionar(new Par<Ponto, String>(new Ponto(px, py), hiperestacao));
        }

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    String cor_climatica = Espaco2D.GET_TEXTO_DA_DISTANCIA_MAIS_PROXIMA(sensores_dados_climaticos, x, y);

                    render_mapa_climatico.setPixel(x, y, Atzum.GET_MODELO_CLIMATICO_COR(cor_climatica));

                }
            }
        }


        Imagem.exportar(render_mapa_climatico.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_modelo_climatico.png"));


        // RENDER VEGETACAO

        Renderizador render_vegetacao = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(render_vegetacao, mCores.getAmarelo(), mCores.getBranco());

        Lista<Par<Ponto, String>> sensores_dados_vegetacao = new Lista<Par<Ponto, String>>();

        for (Entidade sensor : mapa_sensores) {

            sensor.at("X", Strings.GET_ATE(sensor.at("Sensor"), ":"));
            sensor.at("Y", Strings.GET_REVERSO_ATE(sensor.at("Sensor"), ":"));

            int px = sensor.atInt("X");
            int py = sensor.atInt("Y");

            String vegetacao = sensor.at("Vegetacao");

            sensores_dados_vegetacao.adicionar(new Par<Ponto, String>(new Ponto(px, py), vegetacao));
        }

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    String vegetacao = Espaco2D.GET_TEXTO_DA_DISTANCIA_MAIS_PROXIMA(sensores_dados_vegetacao, x, y);

                    render_vegetacao.setPixel(x, y, Atzum.GET_MODELO_VEGETACAO_COR(vegetacao));

                }
            }
        }


        Imagem.exportar(render_mapa_climatico.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_modelo_climatico.png"));
        Imagem.exportar(render_vegetacao.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_modelo_vegetacao.png"));


        Imagem.exportar(CRIAR_INFO_CLIMATICO(render_mapa_climatico), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_info_modelo_climatico.png"));
        Imagem.exportar(CRIAR_INFO_VEGETACAO(render_vegetacao), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_info_modelo_vegetacao.png"));


        AtzumCreatorInfo.terminar("AnalisadorVegetacao.INIT");

    }

    public static BufferedImage CRIAR_INFO_CLIMATICO(Renderizador render_climatico) {
        Cores mCores = new Cores();
        Renderizador info_vegetacao = Renderizador.CONSTRUIR(render_climatico.getLargura() + 1000, render_climatico.getAltura(), mCores.getPreto());
        info_vegetacao.drawImagem(0, 0, render_climatico.toImagemSemAlfa());

        Fonte escritor = new FonteRunTime(mCores.getBranco(), 50);
        escritor.setRenderizador(info_vegetacao);

        int py = 100;
        for (String modelo_vegetacao : Atzum.GET_MODELO_CLIMATICO()) {

            info_vegetacao.drawRect_Pintado(render_climatico.getLargura() + 0, py, 50, 50, Atzum.GET_MODELO_CLIMATICO_COR(modelo_vegetacao));
            escritor.escreva(render_climatico.getLargura() + 0 + 100, py, modelo_vegetacao);

            py += 100;
        }
        return info_vegetacao.toImagemSemAlfa();
    }

    public static BufferedImage CRIAR_INFO_VEGETACAO(Renderizador render_vegetacao) {
        Cores mCores = new Cores();
        Renderizador info_vegetacao = Renderizador.CONSTRUIR(render_vegetacao.getLargura() + 1000, render_vegetacao.getAltura(), mCores.getPreto());
        info_vegetacao.drawImagem(0, 0, render_vegetacao.toImagemSemAlfa());

        Fonte escritor = new FonteRunTime(mCores.getBranco(), 50);
        escritor.setRenderizador(info_vegetacao);

        int py = 100;
        for (String modelo_vegetacao : Atzum.GET_MODELO_VEGETACAO()) {

            info_vegetacao.drawRect_Pintado(render_vegetacao.getLargura() + 0, py, 50, 50, Atzum.GET_MODELO_VEGETACAO_COR(modelo_vegetacao));
            escritor.escreva(render_vegetacao.getLargura() + 0 + 100, py, modelo_vegetacao);

            py += 100;
        }
        return info_vegetacao.toImagemSemAlfa();
    }


}
