package apps.app_atzum.animadores;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.SnapShotter;
import apps.app_atzum.utils.AtzumCreatorInfo;
import libs.arquivos.Quadrum;
import libs.arquivos.video.Empilhador;
import libs.arquivos.video.VideoCodecador;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Efeitos;
import libs.luan.Lista;
import libs.luan.fmt;

public class AnimadorFatoresClimaticos {

    public static void INIT() {


        AtzumCreatorInfo.iniciar("AnimadorFatoresClimaticos.INIT");


        Cores mCores = new Cores();


        AtzumTerra planeta = new AtzumTerra();

        Lista<Entidade> sensores = Atzum.GET_SENSORES();

        String arquivo_sensores_por_sensor_quadrum = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.qa");
        Quadrum sensores_organizando = new Quadrum(arquivo_sensores_por_sensor_quadrum);
        sensores_organizando.abrir();

        fmt.print(">> Iniciando processo...");

        Renderizador render_tronarko_sensores_original = new Renderizador(AtzumCreator.GET_MAPA_PRETO_E_BRANCO());

        int animacao_largura = planeta.getLargura() / 2;
        int animacao_altura = planeta.getAltura() / 2;


        Empilhador video_sensores_observando = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando.vi"), animacao_largura, animacao_altura);
        Empilhador video_sensores_observando_chuva = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_chuva.vi"), animacao_largura, animacao_altura);
        Empilhador video_sensores_observando_seca = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_seca.vi"), animacao_largura, animacao_altura);


        int sensor_tamanho = 30;
        int sensor_tamanho_metade = sensor_tamanho / 2;

        for (int superarko = 1; superarko <= 500; superarko++) {

            fmt.print("\t ++ Superarko : {}", superarko);

            Renderizador render_tronarko_sensores = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());
            Renderizador render_tronarko_sensores_chuva = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());
            Renderizador render_tronarko_sensores_seca = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());

            for (Entidade p_sensor : sensores) {

                Entidade e_sensor = ENTT.PARSER_ENTIDADE(sensores_organizando.get(p_sensor.atInt("SensorID"), superarko));

                if (!e_sensor.isValido("SensorID")) {
                    continue;
                }

                //ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(e_sensor));

                Ponto sensor = new Ponto(e_sensor.atInt("X"), e_sensor.atInt("Y"));

                double temperatura = e_sensor.atDoubleOuPadrao("T" + superarko, 0.0);

                String fator_climatico = e_sensor.at("FC" + superarko);

                boolean isChuva = false;
                boolean isNeve = false;

                boolean isSeca = false;
                boolean isSecaExtrema = false;

                if (fator_climatico.contentEquals("CHUVA")) {
                    isChuva = true;
                } else if (fator_climatico.contentEquals("NEVE")) {
                    isNeve = true;
                } else if (fator_climatico.contentEquals("SECA")) {
                    isSeca = true;
                } else if (fator_climatico.contentEquals("SECA_EXTREMA")) {
                    isSecaExtrema = true;
                }


                if (temperatura >= 30) {
                    AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores, sensor.getX() - sensor_tamanho_metade, sensor.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, mCores.getVermelho());
                } else if (temperatura <= 15) {
                    AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores, sensor.getX() - sensor_tamanho_metade, sensor.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, mCores.getAzul());
                } else {
                    AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores, sensor.getX() - sensor_tamanho_metade, sensor.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, mCores.getVerde());
                }


                if (isChuva) {
                    AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores_chuva, sensor.getX() - sensor_tamanho_metade, sensor.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, mCores.getAzul());
                } else if (isNeve) {
                    AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores_chuva, sensor.getX() - sensor_tamanho_metade, sensor.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, mCores.getCinza());
                }

                if (isSeca) {
                    AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores_seca, sensor.getX() - sensor_tamanho_metade, sensor.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, mCores.getAmarelo());
                } else if (isSecaExtrema) {
                    AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores_seca, sensor.getX() - sensor_tamanho_metade, sensor.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, mCores.getVermelho());
                }


            }


            video_sensores_observando.empurrarQuadro(Efeitos.reduzirMetade(render_tronarko_sensores.toImagemSemAlfa()));
            video_sensores_observando_chuva.empurrarQuadro(Efeitos.reduzirMetade(render_tronarko_sensores_chuva.toImagemSemAlfa()));
            video_sensores_observando_seca.empurrarQuadro(Efeitos.reduzirMetade(render_tronarko_sensores_seca.toImagemSemAlfa()));

        }

        sensores_organizando.fechar();


        video_sensores_observando.fechar();
        video_sensores_observando_chuva.fechar();
        video_sensores_observando_seca.fechar();

        SnapShotter.CRIAR(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando.vi"), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados.png"));
        SnapShotter.CRIAR(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_chuva.vi"), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados_chuva.png"));
        SnapShotter.CRIAR(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_seca.vi"), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados_seca.png"));


        AtzumCreatorInfo.terminar("AnimadorFatoresClimaticos.INIT");
        AtzumCreatorInfo.exibir_item("AnimadorFatoresClimaticos.INIT");


    }

}
