package apps.app_atzum.analisadores;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_atzum.utils.Rasterizador;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;

import java.awt.image.BufferedImage;

public class Publicador {

    public static void PUBLICAR_INFO_CLIMATICO(){

       Lista<Entidade> mapa_sensores= ENTT.ABRIR( AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v9.entts"));

        AtzumTerra mapa_planeta = new AtzumTerra();
        Renderizador render_mapa_climatico = new Renderizador(AtzumCreator.GET_MAPA_PRETO_E_BRANCO());

        Lista<Par<Ponto, String>> sensores_dados_climaticos = new Lista<Par<Ponto, String>>();

        for (Entidade sensor : mapa_sensores) {

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

        Imagem.exportar(render_mapa_climatico.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_modelo_climatico.png"));
        Imagem.exportar(CRIAR_INFO_CLIMATICO(render_mapa_climatico), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_info_modelo_climatico.png"));

    }

    public static void PUBLICAR_INFO_VEGETACAO() {

        // RENDER VEGETACAO

        Lista<Entidade> mapa_sensores= ENTT.ABRIR( AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v9.entts"));


        AtzumTerra mapa_planeta = new AtzumTerra();
        Renderizador render_mapa_vegetacao = new Renderizador(AtzumCreator.GET_MAPA_PRETO_E_BRANCO());

        Lista<Par<Ponto, String>> sensores_dados_vegetacao = new Lista<Par<Ponto, String>>();

        for (Entidade sensor : mapa_sensores) {

            int px = sensor.atInt("X");
            int py = sensor.atInt("Y");

            String vegetacao = sensor.at("Vegetacao");

            sensores_dados_vegetacao.adicionar(new Par<Ponto, String>(new Ponto(px, py), vegetacao));
        }

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    String vegetacao = Espaco2D.GET_TEXTO_DA_DISTANCIA_MAIS_PROXIMA(sensores_dados_vegetacao, x, y);

                    render_mapa_vegetacao.setPixel(x, y, Atzum.GET_MODELO_VEGETACAO_COR(vegetacao));

                }
            }
        }


        Imagem.exportar(render_mapa_vegetacao.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_modelo_vegetacao.png"));

        Imagem.exportar(CRIAR_INFO_VEGETACAO(render_mapa_vegetacao), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_info_modelo_vegetacao.png"));

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


    public static void PUBLICAR_DADOS() {

        AtzumCreatorInfo.iniciar("AnalisadorClimatico.PUBLICAR_DADOS");

        Lista<Entidade> mInformacoesDasCidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades.entts"));

        Lista<Entidade> mapa_sensores = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v9.entts"));
        Lista<Entidade> mapa_sensores_original = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

        fmt.print("Publicar dados resumidamente : Cidades !!! ");

        Lista<Entidade> cidade_dados = ENTT.CRIAR_LISTA();

        for (Entidade cidade : mInformacoesDasCidades) {

            Opcional<Entidade> tem_sensor = ENTT.GET_OPCIONAL(mapa_sensores, "Sensor", cidade.at("Cidade"));
            Opcional<Entidade> tem_sensor_original = ENTT.GET_OPCIONAL(mapa_sensores_original, "Sensor", cidade.at("Cidade"));

            fmt.print("{}", cidade.toTexto());

            double tMin = cidade.atDouble("T1");
            double tMax = cidade.atDouble("T1");

            double uMin = cidade.atDouble("U1");
            double uMax = cidade.atDouble("U1");


            for (int s = 1; s <= 500; s++) {
                //  fmt.print("AQUI :: {}",cidade.at("T"+s));
                double umidade = cidade.atDouble("U" + s);
                double temperatura = cidade.atDouble("T" + s);
                if (temperatura < tMin) {
                    tMin = temperatura;
                }
                if (temperatura > tMax) {
                    tMax = temperatura;
                }

                if (umidade < uMin) {
                    uMin = umidade;
                }
                if (umidade > uMax) {
                    uMax = umidade;
                }
            }

            cidade.atDouble("TMin", tMin);
            cidade.atDouble("TMax", tMax);
            cidade.atDouble("UMin", uMin);
            cidade.atDouble("UMax", uMax);

            Entidade nova_cidade = ENTT.CRIAR_EM(cidade_dados);
            nova_cidade.at("ID", ENTT.CONTAGEM(cidade_dados));
            nova_cidade.atDouble("TMin", tMin);
            nova_cidade.atDouble("TMax", tMax);
            nova_cidade.atDouble("UMin", uMin);
            nova_cidade.atDouble("UMax", uMax);


            if (tem_sensor_original.isOK()) {
                for (int s = 1; s <= 500; s++) {
                    cidade.at("UV" + s, tem_sensor_original.get().at("UV" + s));
                }
            }


            if (tem_sensor.isOK()) {

                cidade.at("Hiperestacao", tem_sensor.get().at("Hiperestacao"));
                cidade.at("Vegetacao", tem_sensor.get().at("Vegetacao"));

                nova_cidade.at("Hiperestacao", tem_sensor.get().at("Hiperestacao"));
                nova_cidade.at("Vegetacao", tem_sensor.get().at("Vegetacao"));

            }


        }


        ENTT.EXIBIR_TABELA(cidade_dados);


        ENTT.GUARDAR(mInformacoesDasCidades, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado.entts"));
        ENTT.GUARDAR(cidade_dados, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades_publicado_simples.entts"));

        AtzumCreatorInfo.terminar("AnalisadorClimatico.PUBLICAR_DADOS");

    }

}
