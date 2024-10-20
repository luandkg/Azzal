package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_atzum.utils.Rasterizador;
import libs.arquivos.QTT;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Vetor;
import libs.luan.fmt;

public class ServicoSensores {

    public static void INIT() {

        AtzumCreatorInfo.iniciar("ServicoSensores.INIT");

        MAPEAR_SENSORES();
        ORGANIZAR_SENSORES();

        AtzumCreatorInfo.terminar("ServicoSensores.INIT");
        AtzumCreatorInfo.exibir_item("ServicoSensores.INIT");

    }

    public static void MAPEAR_SENSORES() {
        AtzumCreatorInfo.iniciar("ServicoTronarko.MAPEAR_SENSORES");

        Cores mCores = new Cores();

        Renderizador render_tronarko_sensores = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(render_tronarko_sensores, mCores.getAmarelo(), mCores.getBranco());

        AtzumTerra planeta = new AtzumTerra();
        Lista<Entidade> sensores = new Lista<Entidade>();


        int sensor_tamanho = 15;
        int sensor_y = 0;

        while (sensor_y < planeta.getAltura()) {
            int sensor_x = 0;

            while (sensor_x < planeta.getLargura()) {

                int sensor_posicao_x = sensor_x - (sensor_tamanho / 2);
                int sensor_posicao_y = sensor_y - (sensor_tamanho / 2);


                if (planeta.isTerra(sensor_posicao_x, sensor_posicao_y)) {

                    render_tronarko_sensores.drawCirculoCentralizado_Pintado(sensor_posicao_x, sensor_posicao_y, 5, mCores.getVermelho());

                    Entidade e_sensor = new Entidade();
                    e_sensor.at("Tipo", "Comum");
                    e_sensor.at("X", sensor_posicao_x);
                    e_sensor.at("Y", sensor_posicao_y);

                    sensores.adicionar(e_sensor);

                } else if (planeta.isOceano(sensor_posicao_x, sensor_posicao_y)) {

                    if (planeta.isTerra(sensor_posicao_x + (sensor_tamanho / 2), sensor_posicao_y - (sensor_tamanho / 2))) {

                        render_tronarko_sensores.drawCirculoCentralizado_Pintado(sensor_posicao_x, sensor_posicao_y, 5, mCores.getAzul());

                        Entidade e_sensor = new Entidade();
                        e_sensor.at("Tipo", "Referenciado");
                        e_sensor.at("X", sensor_posicao_x);
                        e_sensor.at("Y", sensor_posicao_y);

                        e_sensor.at("RefX", sensor_posicao_x + (sensor_tamanho / 2));
                        e_sensor.at("RefY", sensor_posicao_y - (sensor_tamanho / 2));

                        sensores.adicionar(e_sensor);

                    } else if (planeta.isTerra(sensor_posicao_x + (sensor_tamanho / 2), sensor_posicao_y + (sensor_tamanho / 2))) {

                        render_tronarko_sensores.drawCirculoCentralizado_Pintado(sensor_posicao_x, sensor_posicao_y, 5, mCores.getAzul());

                        Entidade e_sensor = new Entidade();
                        e_sensor.at("Tipo", "Referenciado");
                        e_sensor.at("X", sensor_posicao_x);
                        e_sensor.at("Y", sensor_posicao_y);

                        e_sensor.at("RefX", sensor_posicao_x + (sensor_tamanho / 2));
                        e_sensor.at("RefY", sensor_posicao_y + (sensor_tamanho / 2));

                        sensores.adicionar(e_sensor);
                    } else if (planeta.isTerra(sensor_posicao_x - (sensor_tamanho / 2), sensor_posicao_y - (sensor_tamanho / 2))) {

                        render_tronarko_sensores.drawCirculoCentralizado_Pintado(sensor_posicao_x, sensor_posicao_y, 5, mCores.getAzul());

                        Entidade e_sensor = new Entidade();
                        e_sensor.at("Tipo", "Referenciado");
                        e_sensor.at("X", sensor_posicao_x);
                        e_sensor.at("Y", sensor_posicao_y);

                        e_sensor.at("RefX", sensor_posicao_x - (sensor_tamanho / 2));
                        e_sensor.at("RefY", sensor_posicao_y - (sensor_tamanho / 2));

                        sensores.adicionar(e_sensor);
                    } else if (planeta.isTerra(sensor_posicao_x - (sensor_tamanho / 2), sensor_posicao_y + (sensor_tamanho / 2))) {

                        render_tronarko_sensores.drawCirculoCentralizado_Pintado(sensor_posicao_x, sensor_posicao_y, 5, mCores.getAzul());

                        Entidade e_sensor = new Entidade();
                        e_sensor.at("Tipo", "Referenciado");
                        e_sensor.at("X", sensor_posicao_x);
                        e_sensor.at("Y", sensor_posicao_y);

                        e_sensor.at("RefX", sensor_posicao_x - (sensor_tamanho / 2));
                        e_sensor.at("RefY", sensor_posicao_y + (sensor_tamanho / 2));

                        sensores.adicionar(e_sensor);
                    }


                }

                sensor_x += sensor_tamanho;
            }

            sensor_y += sensor_tamanho;
        }

        for (Ponto sensor : Atzum.GET_CIDADES()) {


            render_tronarko_sensores.drawCirculoCentralizado_Pintado(sensor.getX(), sensor.getY(), 5, mCores.getAmarelo());

            Entidade e_sensor = new Entidade();
            e_sensor.at("Tipo", "Cidade");
            e_sensor.at("X", sensor.getX());
            e_sensor.at("Y", sensor.getY());

            e_sensor.at("RefX", sensor.getX() - (sensor_tamanho / 2));
            e_sensor.at("RefY", sensor.getY() - (sensor_tamanho / 2));

            sensores.adicionar(e_sensor);

        }


        fmt.print("Sensores : {}", sensores.getQuantidade());

        Imagem.exportar(render_tronarko_sensores.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.png"));


        ENTT.GUARDAR(sensores, AtzumCreator.LOCAL_GET_ARQUIVO("parametros/SENSORES.entts"));


        AtzumCreatorInfo.terminar("ServicoTronarko.MAPEAR_SENSORES");
        AtzumCreatorInfo.exibir_item("ServicoTronarko.MAPEAR_SENSORES");

    }

    public static void ORGANIZAR_SENSORES() {

        AtzumCreatorInfo.iniciar("ServicoTronarko.ORGANIZAR_SENSORES");

        AtzumTerra planeta = new AtzumTerra();

        Lista<Entidade> dados_brutos = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/SENSORES.entts"));

        Vetor<Ponto> sensores = new Vetor<Ponto>(dados_brutos.getQuantidade());

        int sensor_id = 0;
        for (Entidade sensor : dados_brutos) {

            int px = sensor.atInt("X");
            int py = sensor.atInt("Y");

            sensores.set(sensor_id, new Ponto(px, py));
            sensor_id += 1;
        }


        Lista<Ponto> sensores_lista = sensores.toLista();

        fmt.print(">> Calculando proximidade dos sensores !");

        QTT.alocar(AtzumCreator.LOCAL_GET_ARQUIVO("dados/sensor_proximidade.qtt"), planeta.getLargura(), planeta.getAltura());
        QTT.alterar_todos(AtzumCreator.LOCAL_GET_ARQUIVO("dados/sensor_proximidade.qtt"), planeta.getLargura(), planeta.getAltura(), -1);

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isTerra(x, y)) {
                    Opcional<Integer> proximo = Espaco2D.GET_MAIS_PROXIMO_ORDEM(new Ponto(x, y), sensores_lista);
                    if (proximo.isOK()) {
                        QTT.alterar(AtzumCreator.LOCAL_GET_ARQUIVO("dados/sensor_proximidade.qtt"), x, y, proximo.get());
                    }
                }
            }
        }


        AtzumCreatorInfo.terminar("ServicoTronarko.ORGANIZAR_SENSORES");
        AtzumCreatorInfo.exibir_item("ServicoTronarko.ORGANIZAR_SENSORES");
    }

}
