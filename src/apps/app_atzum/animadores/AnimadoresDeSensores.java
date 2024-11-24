package apps.app_atzum.animadores;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.VideoRasterizar;
import libs.arquivos.ds.DS;
import libs.arquivos.video.Empilhador;
import libs.arquivos.video.VideoCodecador;
import libs.arquivos.video.VideoConstrutor;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.fmt;

public class AnimadoresDeSensores {

    public static final String PASTA_TRONARKO_VALORES = AtzumCreator.LOCAL_GET_ARQUIVO("videos_sensores/");

    public static void TRONARKO_VER_SENSORES_FATORES_CLIMATICOS() {

        fmt.print(">> Ver dados sensores !");


        String arquivo_sensores_por_superarko = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_superarko.ds");

        DS.DUMP_TABELA(arquivo_sensores_por_superarko);

        fmt.print("Superarkos : {}", DS.contar(arquivo_sensores_por_superarko));

        AtzumTerra planeta = new AtzumTerra();

        int sensor_tamanho = 30;
        int sensor_tamanho_metade = sensor_tamanho / 2;


        Lista<Entidade> sensores = Atzum.GET_SENSORES();

        String arquivo_video = PASTA_TRONARKO_VALORES + "fatores_climaticos.vi";

        VideoConstrutor mVideo = new VideoConstrutor(arquivo_video, planeta.getLargura() / 2, planeta.getAltura() / 2);

        for (int superarko = 1; superarko <= 500; superarko++) {


            fmt.print("\t ++ Superarko {}", superarko, superarko);


            Lista<Entidade> dados = ENTT.PARSER(DS.buscar_item(arquivo_sensores_por_superarko, superarko + ".entts").get().getTexto());

           // ENTT.EXIBIR_TABELA(ENTT.SLICE_PRIMEIROS(dados, 5));

            Renderizador render = AtzumCreator.GET_RENDER_FUNDO_PRETO();


            for (Entidade sensor : dados) {

                String fator_climatico = sensor.at("FC" + superarko);

                // Cor cor = new HSV(350, HSV.MAXIMO, HSV.INVERSO((int) (temperatura * temp_taxa))).toRGB();

                Ponto sensor_normalizado = Atzum.SENSOR_NORMALIZAR(sensores, sensor);

                //render.drawRect_Pintado(sensor_px, sensor_py, 5, 5, cor);

                if (!fator_climatico.isEmpty()) {

                    Cor cor = Atzum.GET_FATOR_CLIMATICO_COR(fator_climatico);

                    AtzumCreator.PREENCHER_TERRA(planeta, render, sensor_normalizado.getX() - sensor_tamanho_metade, sensor_normalizado.getY() - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, cor);

                }

            }

            mVideo.adicionarQuadro(Efeitos.reduzirMetade(render.toImagemSemAlfa()));

        }

        mVideo.fechar();
    }

}
