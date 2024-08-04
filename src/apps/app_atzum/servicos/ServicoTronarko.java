package apps.app_atzum.servicos;

import apps.app_arquivos.AppVideo;
import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.*;
import apps.app_atzum.apps.AtzumSnapShots;
import apps.app_atzum.escalas.EscalaAQ4;
import apps.app_atzum.escalas.EscalaRT3;
import apps.app_atzum.escalas.EscalaVT2;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_atzum.utils.FaixaDeTemperatura;
import apps.app_atzum.utils.Rasterizador;
import libs.arquivos.QTT;
import libs.arquivos.binario.Inteiro;
import libs.arquivos.video.Empilhador;
import libs.arquivos.video.VideoCodecador;
import libs.azzal.AzzalUnico;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.utilitarios.Cor;
import libs.azzal.utilitarios.HSV;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.*;


public class ServicoTronarko {

    public static void CONSTRUIR_TRONARKO() {
        AtzumCreatorInfo.iniciar("ServicoTronarko.CONSTRUIR_TRONARKO");

        CONSTRUIR_TRONARKO_MODELO_V2(1);
        CONSTRUIR_TRONARKO_MODELO_V2(2);

        AtzumCreatorInfo.terminar("ServicoTronarko.CONSTRUIR_TRONARKO");
        AtzumCreatorInfo.exibir_item("ServicoTronarko.CONSTRUIR_TRONARKO");

    }

    public static void CONSTRUIR_TRONARKO_MODELO_V2(int modelo_valor) {


        String modelagem = String.valueOf(modelo_valor);

        fmt.print("Lendo temperaturas :: Modelo - T" + modelo_valor);

        AtzumTerra mapa_planeta = new AtzumTerra();

        Cores mCores = new Cores();

        Extremos<Integer> ex_temperatura = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());
        Extremos<Integer> ex_temperatura_validados = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());

        QTT temperatura_dados = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("temperatura_t" + modelagem + ".qtt"));

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int valor = temperatura_dados.getValor(x, y);
                    ex_temperatura.set(valor);

                }
            }
        }


        fmt.print("\t++ Maior Temperatura : {}", ex_temperatura.getMaior());
        fmt.print("\t++ Menor Temperatura : {}", ex_temperatura.getMenor());


        String arquivo_processando_temperatura = "build/tronarko/temperatura_t" + modelagem + ".qtt";
        QTT.alocar(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura), mapa_planeta.getLargura(), mapa_planeta.getAltura());
        QTT.alterar_todos(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura), mapa_planeta.getLargura(), mapa_planeta.getAltura(), -1);


        fmt.print(">> Processando");
        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura_corrente = temperatura_dados.getValor(x, y);

                    if (Aleatorio.aleatorio(100) > 50) {
                        temperatura_corrente += 1;
                    } else {
                        temperatura_corrente -= 1;
                    }

                    QTT.alterar(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura), x, y, temperatura_corrente);

                    ex_temperatura_validados.set(temperatura_corrente);
                }
            }
        }


        fmt.print("\t++ Maior Temperatura : {}", ex_temperatura_validados.getMaior());
        fmt.print("\t++ Menor Temperatura : {}", ex_temperatura_validados.getMenor());


        fmt.print(">> Renderizando");

        temperatura_dados = QTT.getTudo(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura));

        Renderizador render_distancia = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

        double temp_escopo = (double) (ex_temperatura_validados.getMaior() - ex_temperatura_validados.getMenor());

        double temp_taxa = 100.0 / temp_escopo;

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura = temperatura_dados.getValor(x, y);

                    render_distancia.setPixel(x, y, new HSV(350, HSV.MAXIMO, HSV.INVERSO((int) (temperatura * temp_taxa))));

                }
            }
        }


        Imagem.exportar(render_distancia.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/temperatura_t" + modelagem + ".png"));

        fmt.print(">> Tudo OK !");

    }

    public static void CONSTRUIR_TRONARKO_MODELO(int modelo_valor) {


        String modelagem = String.valueOf(modelo_valor);

        fmt.print("Lendo temperaturas...");
        AtzumTerra mapa_planeta = new AtzumTerra();

        Cores mCores = new Cores();

        Extremos<Integer> ex_temperatura = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());
        Extremos<Integer> ex_temperatura_validados = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());


        Unico<Integer> tipos = new Unico<Integer>(Inteiro.IGUALAVEL());

        Lista<Ponto> pontos_terra = new Lista<Ponto>();

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int valor = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("temperatura_t" + modelagem + ".qtt"), x, y);
                    ex_temperatura.set(valor);
                    tipos.item(valor);

                    pontos_terra.adicionar(new Ponto(x, y));
                }
            }
        }


        fmt.print("Maior Temperatura : {}", ex_temperatura.getMaior());
        fmt.print("Menor Temperatura : {}", ex_temperatura.getMenor());

        fmt.print("------------- TEMPERATURA MATRIZ ------------------");

        Vetor<Extremos<Integer>> zonas_de_temperatura = new Atzum().GET_ZONAS_DE_TEMPERATURAS();


        for (Extremos<Integer> tipo : zonas_de_temperatura) {
            fmt.print("\t + {esq5}      -- {esq5}", tipo.getMenor(), tipo.getMaior());
        }

        fmt.print("------------- TEMPERATURA TIPOS ------------------");
        for (int tipo : tipos) {
            fmt.print("\t + {}", tipo);
        }

        // Embaralhar.emabaralhe(pontos_terra);

        int criar_pontos = (mapa_planeta.getLargura() / 100) * (mapa_planeta.getAltura() / 100);

        Lista<Par<Ponto, Integer>> pontos_escolhidos = new Lista<Par<Ponto, Integer>>();

        int processando = 0;

        while (processando < criar_pontos) {

            int escolhido = Aleatorio.aleatorio(pontos_terra.getQuantidade());
            Ponto ponto_escolhido = pontos_terra.get(escolhido);

            int tipo = QTT.pegar(AtzumCreator.DADOS_GET_ARQUIVO("temperatura_t" + modelagem + ".qtt"), ponto_escolhido.getX(), ponto_escolhido.getY());

            int temperatura_corrente = Aleatorio.alatorio_entre(zonas_de_temperatura.get(tipo).getMenor(), zonas_de_temperatura.get(tipo).getMaior());
            ex_temperatura_validados.set(temperatura_corrente);

            pontos_escolhidos.adicionar(new Par<Ponto, Integer>(ponto_escolhido, temperatura_corrente));

            processando += 1;
        }

        fmt.print("Terra pontos    : {}", pontos_terra.getQuantidade());
        fmt.print("Criar pontos    : {}", criar_pontos);
        fmt.print("Pontos criados  : {}", pontos_escolhidos.getQuantidade());

        String arquivo_processando_temperatura = "build/tronarko/temperatura_t" + modelagem + ".qtt";

        QTT.alocar(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura), mapa_planeta.getLargura(), mapa_planeta.getAltura());
        QTT.alterar_todos(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura), mapa_planeta.getLargura(), mapa_planeta.getAltura(), -1);


        fmt.print(">> Processando");
        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int valor_proximo = Espaco2D.GET_VALOR_DA_DISTANCIA_MAIS_PROXIMA(pontos_escolhidos, x, y);

                    QTT.alterar(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura), x, y, valor_proximo);

                }
            }
        }


        fmt.print(">> Renderizando");

        Renderizador render_distancia = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getBranco());

        double temp_escopo = (double) (ex_temperatura_validados.getMaior() - ex_temperatura_validados.getMenor());

        double temp_taxa = 100.0 / temp_escopo;

        int SUBIR = 60;
        int DESCER = 80;

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int valor = QTT.pegar(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura), x, y);
                    int temperatura = valor;

                    valor = valor + 100;

                    HSV hsv = new HSV(20, HSV.MEDIANO, HSV.INVERSO((int) (temperatura * temp_taxa)));

                    // render_distancia.setPixel(x, y, new Cor(valor, valor, valor));

                    if (temperatura <= 15) {
                        render_distancia.setPixel(x, y, new HSV(240, HSV.MEDIANO, HSV.INVERSO((int) ((temperatura) * temp_taxa))));
                    } else if (temperatura > 25) {
                        render_distancia.setPixel(x, y, new HSV(10, HSV.MEDIANO, HSV.INVERSO((int) ((temperatura) * temp_taxa))));
                    } else {
                        render_distancia.setPixel(x, y, new HSV(150, HSV.MEDIANO, HSV.INVERSO((int) (temperatura * temp_taxa))));
                    }


                    render_distancia.setPixel(x, y, new HSV(350, HSV.MAXIMO, HSV.INVERSO((int) (temperatura * temp_taxa))));

                    //render_distancia.setPixel(x, y, hsv);


                }
            }
        }


        Imagem.exportar(render_distancia.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/temperatura_t" + modelagem + ".png"));

        fmt.print("Tudo OK !");

    }


    public static void CALCULAR_TRONARKO_TRANSICAO() {

        AtzumCreatorInfo.iniciar("ServicoTronarko.CALCULAR_TRONARKO_TRANSICAO");

        boolean ANALISAR_VARIACAO = true;


        String arquivo_processando_temperatura_v1 = "build/tronarko/temperatura_t1.qtt";
        String arquivo_processando_temperatura_v2 = "build/tronarko/temperatura_t2.qtt";

        Cores mCores = new Cores();

        AtzumTerra mapa_planeta = new AtzumTerra();

        Extremos<Integer> ex_temperatura_validados = new Extremos<Integer>(Inteiro.GET_ORDENAVEL());

        double[] tronarko_temperatura = new double[mapa_planeta.getAltura() * mapa_planeta.getLargura()];
        double[] tronarko_temperatura_transicao = new double[mapa_planeta.getAltura() * mapa_planeta.getLargura()];

        double[] tronarko_umidade_variacao = new double[mapa_planeta.getAltura() * mapa_planeta.getLargura()];

        Atzum atzum = new Atzum();

        // MASSAS DE AR
        Cor AR_FRIO = atzum.getMassaDeArFria();
        Cor AR_QUENTE = atzum.getMassaDeArQuente();


        boolean[] tronarko_quente = new boolean[mapa_planeta.getAltura() * mapa_planeta.getLargura()];
        boolean[] tronarko_frio = new boolean[mapa_planeta.getAltura() * mapa_planeta.getLargura()];
        boolean[] tronarko_vazio = new boolean[mapa_planeta.getAltura() * mapa_planeta.getLargura()];

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    tronarko_umidade_variacao[(y * mapa_planeta.getLargura()) + x] = 0;

                    tronarko_quente[(y * mapa_planeta.getLargura()) + x] = true;
                    tronarko_frio[(y * mapa_planeta.getLargura()) + x] = true;
                    tronarko_vazio[(y * mapa_planeta.getLargura()) + x] = true;
                } else {
                    tronarko_quente[(y * mapa_planeta.getLargura()) + x] = false;
                    tronarko_frio[(y * mapa_planeta.getLargura()) + x] = false;
                    tronarko_vazio[(y * mapa_planeta.getLargura()) + x] = false;
                }
            }
        }


        fmt.print(">> Carregando massas de ar...");
        Lista<MassaDeAr> mMassasDeAr = new Lista<MassaDeAr>();

        mMassasDeAr.adicionar(new MassaDeAr("MIZ_A", "miz", "FRIO", 100, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MOP_A", "mop", "QUENTE", 300, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MUT_A", "mut", "FRIO", 100, 1));
        mMassasDeAr.adicionar(new MassaDeAr("MOX_A", "mox", "FRIO", 100, 1));

        mMassasDeAr.adicionar(new MassaDeAr("RAF_A", "raf", "QUENTE", 50, 1));
        mMassasDeAr.adicionar(new MassaDeAr("REZ_A", "rez", "QUENTE", 50, 1));
        mMassasDeAr.adicionar(new MassaDeAr("RUC_A", "ruc", "QUENTE", 50, 1));

        mMassasDeAr.adicionar(new MassaDeAr("REC_B", "rez", "QUENTE", 300, -1));
        //   mMassasDeAr.adicionar(new MassaDeAr("MUT_B", "mut", "FRIO", 400-300, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RAF_B", "raf", "FRIO", 500, 1));


        mMassasDeAr.adicionar(new MassaDeAr("MUT_C", "mut", "FRIO", 250 + 300, -1));
        mMassasDeAr.adicionar(new MassaDeAr("REZ_C", "rez", "QUENTE", 400, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RAF_C", "raf", "QUENTE", 400, -1));
        mMassasDeAr.adicionar(new MassaDeAr("RUC_C", "ruc", "QUENTE", 450 + 130, 1));


        fmt.print(">> Massas de Ar : Inicializando...");
        for (MassaDeAr massa : mMassasDeAr) {
            ServicoMassasDeAr.MASSA_DE_AR_INICIAR(massa.getMassa());
        }

        fmt.print(">> Processando Temperatura...");
        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int valor1 = QTT.pegar(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura_v1), x, y);
                    int valor2 = QTT.pegar(AtzumCreator.LOCAL_GET_ARQUIVO(arquivo_processando_temperatura_v2), x, y);

                    tronarko_temperatura_transicao[(y * mapa_planeta.getLargura()) + x] = ((double) valor2 - (double) valor1) / 250.0;

                    tronarko_temperatura[(y * mapa_planeta.getLargura()) + x] = valor1;

                    ex_temperatura_validados.set(valor1, valor2);
                }
            }
        }


        double temp_escopo = (double) (ex_temperatura_validados.getMaior() - ex_temperatura_validados.getMenor());

        double temp_taxa = 100.0 / temp_escopo;


        fmt.print(">> Organizando arquivos de saida !");


        Atzum mAtzum = new Atzum();
        Lista<Entidade> sensores = mAtzum.GET_SENSORES();

        Lista<Entidade> sensores_dados = new Lista<Entidade>();

        Empilhador video_temperatura = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/temperatura.vi"), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2);
        Empilhador video_temperatura_umidade = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/tu.vi"), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2);
        Empilhador video_temperatura_e_massas_de_ar = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/tronarko_temperatura_e_massas_de_ar.vi"), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2);
        Empilhador video_preciptacao_valor = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/preciptacao_valor.vi"), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2);
        Empilhador video_preciptacao_tronarko = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/preciptacao_tronarko.vi"), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2);

        Empilhador video_fatores_climaticos = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/fatores_climaticos.vi"), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2);

        Empilhador video_temperatura_zonas = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/temperatura_zonas.vi"), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2);


        Extremos<Double> d_indo = new Extremos<Double>(Matematica.DOUBLE_COMPARADOR());
        Extremos<Double> d_voltando = new Extremos<Double>(Matematica.DOUBLE_COMPARADOR());


        QTT umidade_dados = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"));

        Renderizador render_tronarko_preciptacao = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

        Renderizador render_mapa_pronto = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(render_mapa_pronto, mCores.getAmarelo(), mCores.getBranco());

        QTT variacao_inferior = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("variacao_inferior.qtt"));
        QTT variacao_superior = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("variacao_superior.qtt"));


        fmt.print(">> Processando Tronarko...");
        for (int superarko = 1; superarko <= 500; superarko++) {


            boolean temperatura_aumetando = true;

            if (superarko > 250) {
                temperatura_aumetando = false;
            }


            for (int y = 0; y < mapa_planeta.getAltura(); y++) {
                for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                    if (mapa_planeta.isTerra(x, y)) {

                        if (temperatura_aumetando) {
                            tronarko_temperatura[(y * mapa_planeta.getLargura()) + x] = tronarko_temperatura[(y * mapa_planeta.getLargura()) + x] + tronarko_temperatura_transicao[(y * mapa_planeta.getLargura()) + x];
                            d_indo.set(tronarko_temperatura[(y * mapa_planeta.getLargura()) + x]);
                        } else {
                            tronarko_temperatura[(y * mapa_planeta.getLargura()) + x] = tronarko_temperatura[(y * mapa_planeta.getLargura()) + x] - tronarko_temperatura_transicao[(y * mapa_planeta.getLargura()) + x];
                            d_voltando.set(tronarko_temperatura[(y * mapa_planeta.getLargura()) + x]);
                        }


                    }
                }
            }


            // RENDERIZAR PARTES

            fmt.print("Superarko :: {}", superarko);

            Renderizador render_massas_de_ar = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());
            Renderizador render_massas_de_ar_pura = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());
            Renderizador render_preciptacao_valor = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

            Renderizador render_tp = renderizar_variacao_de_temperatura(mapa_planeta, tronarko_temperatura, temp_taxa);

            for (MassaDeAr massa : mMassasDeAr) {

                if (massa.isFrio()) {
                    ServicoMassasDeAr.MASSA_DE_AR_MOVIMENTANDO_FIXO(render_massas_de_ar_pura, AR_FRIO, massa);
                    ServicoMassasDeAr.MASSA_DE_AR_MOVIMENTANDO_JUNTAS_FIXO(render_massas_de_ar, AR_FRIO, massa);
                } else if (massa.isQuente()) {
                    ServicoMassasDeAr.MASSA_DE_AR_MOVIMENTANDO_FIXO(render_massas_de_ar_pura, AR_QUENTE, massa);
                    ServicoMassasDeAr.MASSA_DE_AR_MOVIMENTANDO_JUNTAS_FIXO(render_massas_de_ar, AR_QUENTE, massa);
                }


                massa.proximo();
            }


            Renderizador PROCESSO_TU = renderizar_temperatura_umidade(mapa_planeta, render_mapa_pronto, tronarko_temperatura, umidade_dados);

            ServicoMassasDeAr.TRANSPOR_MASSA_DE_AR(render_massas_de_ar, render_tp);
            TRANSPOR_MASSA_DE_AR(mapa_planeta, render_massas_de_ar, PROCESSO_TU, render_preciptacao_valor, render_tronarko_preciptacao);
            Renderizador render_fatores_climaticos = PROCESSAR_FATORES_CLIMATICOS(mapa_planeta, tronarko_temperatura, umidade_dados, render_massas_de_ar);

            CALCULAR_UMIDADE(ANALISAR_VARIACAO, mapa_planeta, tronarko_umidade_variacao, render_fatores_climaticos, variacao_inferior, variacao_superior);


            video_temperatura.empurrarQuadro(Efeitos.reduzir(renderizar_variacao_de_temperatura(mapa_planeta, tronarko_temperatura, temp_taxa).toImagemSemAlfa(), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2));
            video_temperatura_zonas.empurrarQuadro(Efeitos.reduzir(renderizar_variacao_de_temperatura_zona(mapa_planeta, tronarko_temperatura, temp_taxa).toImagemSemAlfa(), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2));


            video_temperatura_umidade.empurrarQuadro(Efeitos.reduzir(PROCESSO_TU.toImagemSemAlfa(), mapa_planeta.getLargura() / 2, mapa_planeta.getAltura() / 2));

            video_temperatura_e_massas_de_ar.empurrarQuadro(Efeitos.reduzirMetade(render_tp.toImagemSemAlfa()));
            video_preciptacao_valor.empurrarQuadro(Efeitos.reduzirMetade(render_preciptacao_valor.toImagemSemAlfa()));

            video_preciptacao_tronarko.empurrarQuadro(Efeitos.reduzirMetade(render_tronarko_preciptacao.toImagemSemAlfa()));
            video_fatores_climaticos.empurrarQuadro(Efeitos.reduzirMetade(render_fatores_climaticos.toImagemSemAlfa()));

            for (Entidade sensor : sensores) {

                int sensor_px = 0;
                int sensor_py = 0;


                int padrao_sensor_px = sensor.atInt("X");
                int padrao_sensor_py = sensor.atInt("Y");

                if (sensor.is("Tipo", "Comum")) {
                    sensor_px = sensor.atInt("X");
                    sensor_py = sensor.atInt("Y");
                } else if (sensor.is("Tipo", "Cidade")) {
                    sensor_px = sensor.atInt("X");
                    sensor_py = sensor.atInt("Y");
                } else if (sensor.is("Tipo", "Referenciado")) {
                    sensor_px = sensor.atInt("RefX");
                    sensor_py = sensor.atInt("RefY");
                }


                String fator_climatico_corrente = atzum.getFatorClimatico(render_fatores_climaticos.getPixel(sensor_px, sensor_py));
                double temperatura_corrente = tronarko_temperatura[(sensor_py * mapa_planeta.getLargura()) + sensor_px];
                double umidade_corrente = umidade_dados.getValor(sensor_px, sensor_py);

                double umidade_variacao = tronarko_umidade_variacao[(sensor_py * mapa_planeta.getLargura()) + sensor_px];

                umidade_corrente += umidade_variacao;

                umidade_corrente = Matematica.NORMALIZAR(umidade_corrente, 0, 100);

                String tem_interferencia = "NAO";

                if (Strings.isIgual(fator_climatico_corrente, "ONDA_DE_CALOR")) {
                    temperatura_corrente += 7;
                    tem_interferencia = "SIM";
                } else if (Strings.isIgual(fator_climatico_corrente, "SECA")) {
                    temperatura_corrente += 5;
                    tem_interferencia = "SIM";
                } else if (Strings.isIgual(fator_climatico_corrente, "SECA_EXTREMA")) {
                    temperatura_corrente += 10;
                    tem_interferencia = "SIM";
                } else if (Strings.isIgual(fator_climatico_corrente, "CHUVA")) {
                    temperatura_corrente -= 10;
                    tem_interferencia = "SIM";
                } else if (Strings.isIgual(fator_climatico_corrente, "TEMPESTADE_CHUVA")) {
                    temperatura_corrente -= 12;
                    tem_interferencia = "SIM";
                } else if (Strings.isIgual(fator_climatico_corrente, "NEVE")) {
                    temperatura_corrente -= 15;
                    tem_interferencia = "SIM";
                } else if (Strings.isIgual(fator_climatico_corrente, "TEMPESTADE")) {
                    temperatura_corrente -= 10;
                    tem_interferencia = "SIM";
                }

                umidade_corrente = Matematica.NORMALIZAR(umidade_corrente, 0, 100);

                Entidade sensor_param = ENTT.GET_SEMPRE(sensores_dados, "Sensor", padrao_sensor_px + "::" + padrao_sensor_py);
                sensor_param.at("X", padrao_sensor_px);
                sensor_param.at("Y", padrao_sensor_py);
                sensor_param.at("T" + superarko, fmt.f2(temperatura_corrente));
                sensor_param.at("U" + superarko, fmt.f2(umidade_corrente));
                sensor_param.at("M" + superarko, atzum.getMassaDeArTipo(render_massas_de_ar.getPixel(sensor_px, sensor_py)));
                sensor_param.at("FC" + superarko, fator_climatico_corrente);
                sensor_param.at("IC" + superarko, tem_interferencia);
                sensor_param.at("UV" + superarko, fmt.f2(umidade_variacao));

            }


            for (int y = 0; y < mapa_planeta.getAltura(); y++) {
                for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                    if (mapa_planeta.isTerra(x, y)) {

                        if (tronarko_quente[(y * mapa_planeta.getLargura()) + x]) {
                            String fator_climatico = atzum.getFatorClimatico(render_fatores_climaticos.getPixel(x, y));

                            if (fator_climatico.contentEquals("") || fator_climatico.contentEquals("SECA") || fator_climatico.contentEquals("SECA_EXTREMA") || fator_climatico.contentEquals("TEMPESTADE_VENTO")) {

                            } else {
                                tronarko_quente[(y * mapa_planeta.getLargura()) + x] = false;
                            }
                        }

                        if (tronarko_frio[(y * mapa_planeta.getLargura()) + x]) {
                            String fator_climatico = atzum.getFatorClimatico(render_fatores_climaticos.getPixel(x, y));

                            if (fator_climatico.contentEquals("") || fator_climatico.contentEquals("CHUVA") || fator_climatico.contentEquals("NEVE") || fator_climatico.contentEquals("TEMPESTADE_CHUVA") || fator_climatico.contentEquals("TEMPESTADE_NEVE")) {

                            } else {
                                tronarko_frio[(y * mapa_planeta.getLargura()) + x] = false;
                            }
                        }

                        if (tronarko_vazio[(y * mapa_planeta.getLargura()) + x]) {
                            String fator_climatico = atzum.getFatorClimatico(render_fatores_climaticos.getPixel(x, y));

                            if (!fator_climatico.isEmpty()) {
                                tronarko_vazio[(y * mapa_planeta.getLargura()) + x] = false;
                            }
                        }

                    }
                }
            }

        }


        video_temperatura.fechar();
        video_temperatura_zonas.fechar();
        video_temperatura_umidade.fechar();
        video_temperatura_e_massas_de_ar.fechar();
        video_preciptacao_valor.fechar();
        video_preciptacao_tronarko.fechar();
        video_fatores_climaticos.fechar();

        Imagem.exportar(render_tronarko_preciptacao.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_preciptacao_temporal.png"));

        fmt.print("\t + {esq5}      -- {esq5}", fmt.f2(d_indo.getMenor()), fmt.f2(d_indo.getMaior()));
        fmt.print("\t + {esq5}      -- {esq5}", fmt.f2(d_voltando.getMenor()), fmt.f2(d_voltando.getMaior()));


        Renderizador render_tronarko_quente = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());
        Renderizador render_tronarko_frio = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());
        Renderizador render_tronarko_vazio = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());
        Renderizador render_tronarko_completo = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    if (tronarko_quente[(y * mapa_planeta.getLargura()) + x]) {
                        render_tronarko_quente.setPixel(x, y, mCores.getVermelho());
                        render_tronarko_completo.setPixel(x, y, mCores.getVermelho());
                    }

                    if (tronarko_frio[(y * mapa_planeta.getLargura()) + x]) {
                        render_tronarko_frio.setPixel(x, y, mCores.getAzul());
                        render_tronarko_completo.setPixel(x, y, mCores.getAzul());
                    }

                    if (tronarko_vazio[(y * mapa_planeta.getLargura()) + x]) {
                        render_tronarko_vazio.setPixel(x, y, mCores.getVerde());
                        render_tronarko_completo.setPixel(x, y, mCores.getVerde());
                    }

                }
            }
        }

        Imagem.exportar(render_tronarko_quente.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_quente.png"));
        Imagem.exportar(render_tronarko_frio.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_frio.png"));
        Imagem.exportar(render_tronarko_vazio.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_vazio.png"));
        Imagem.exportar(render_tronarko_completo.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_completo.png"));


        fmt.print(">> GUARDAR DADOS DOS SENSORES");

        ENTT.GUARDAR(sensores_dados, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

        AtzumCreatorInfo.terminar("ServicoTronarko.CALCULAR_TRONARKO_TRANSICAO");

        fmt.print(">> Tudo OK !!!");

    }


    public static Renderizador renderizar_variacao_de_temperatura(AtzumTerra mapa_planeta, double[] processando, double temp_taxa) {

        Cores mCores = new Cores();
        Renderizador render_distancia = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());


        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura = (int) processando[(y * mapa_planeta.getLargura()) + x];

                    render_distancia.setPixel(x, y, new HSV(350, HSV.MAXIMO, HSV.INVERSO((int) (temperatura * temp_taxa))));

                }
            }
        }

        return render_distancia;
    }


    public static Renderizador renderizar_variacao_de_temperatura_zona(AtzumTerra mapa_planeta, double[] processando, double temp_taxa) {

        Cores mCores = new Cores();
        Renderizador render_temperatura = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

        Cor COR_MUITO_QUENTE = Cor.getHexCor("#BF360C");
        Cor COR_QUENTE = Cor.getHexCor("#FB8C00");
        Cor COR_FRIO = Cor.getHexCor("#1976D2");
        Cor COR_MUITO_FRIO = Cor.getHexCor("#0D47A1");
        Cor COR_NORMAL = Cor.getHexCor("#8BC34A");

        Lista<FaixaDeTemperatura> FAIXAS_DE_TEMPERATURA = new Lista<FaixaDeTemperatura>();

        FAIXAS_DE_TEMPERATURA.adicionar(new FaixaDeTemperatura(36, 45, COR_MUITO_QUENTE));
        FAIXAS_DE_TEMPERATURA.adicionar(new FaixaDeTemperatura(30, 35, COR_QUENTE));
        FAIXAS_DE_TEMPERATURA.adicionar(new FaixaDeTemperatura(10, 30, COR_NORMAL));
        FAIXAS_DE_TEMPERATURA.adicionar(new FaixaDeTemperatura(-15, 10, COR_FRIO));
        FAIXAS_DE_TEMPERATURA.adicionar(new FaixaDeTemperatura(-30, -15, COR_MUITO_FRIO));


        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura = (int) processando[(y * mapa_planeta.getLargura()) + x];


                    Cor cor_temp = mCores.getBranco();

                    for (FaixaDeTemperatura temp_zona : FAIXAS_DE_TEMPERATURA) {
                        if (temperatura >= temp_zona.getMinimo() && temperatura <= temp_zona.getMaximo()) {
                            cor_temp = temp_zona.getCor();
                            break;
                        }
                    }

                    if (temperatura > FAIXAS_DE_TEMPERATURA.get(0).getMaximo()) {
                        cor_temp = FAIXAS_DE_TEMPERATURA.get(0).getCor();
                    }

                    if (temperatura < FAIXAS_DE_TEMPERATURA.get(FAIXAS_DE_TEMPERATURA.getQuantidade() - 1).getMinimo()) {
                        cor_temp = FAIXAS_DE_TEMPERATURA.get(FAIXAS_DE_TEMPERATURA.getQuantidade() - 1).getCor();
                    }

                    render_temperatura.setPixel(x, y, cor_temp);

                }
            }
        }

        return render_temperatura;
    }


    public static Renderizador renderizar_temperatura_umidade(AtzumTerra mapa_planeta, Renderizador render_mapa_pronto, double[] tronarko_temperatura, QTT umidade_dados) {


        int TEMPERATURA_NORMAL_SUPERIOR = 25;
        int TEMPERATURA_NORMAL_INFERIOR = 20;


        Renderizador render_preciptacao = render_mapa_pronto.getCopia();


        for (int y = 0; y < mapa_planeta.getAltura(); y++) {
            for (int x = 0; x < mapa_planeta.getLargura(); x++) {
                if (mapa_planeta.isTerra(x, y)) {

                    int temperatura = (int) tronarko_temperatura[(y * mapa_planeta.getLargura()) + x];
                    int umidade = umidade_dados.getValor(x, y);


                    // CHUVA FRIA
                    if (umidade >= 25 && (temperatura <= TEMPERATURA_NORMAL_INFERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaAQ4.T5);
                    }
                    if (umidade >= 50 && temperatura <= TEMPERATURA_NORMAL_INFERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaAQ4.T10);
                    }
                    if (umidade >= 75 && temperatura <= TEMPERATURA_NORMAL_INFERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaAQ4.T12);
                    }

                    // CHUVA NORMAL
                    if (umidade >= 25 && (temperatura > TEMPERATURA_NORMAL_INFERIOR) && (temperatura < TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaVT2.T5);
                    }
                    if (umidade >= 50 && temperatura > TEMPERATURA_NORMAL_INFERIOR && (temperatura < TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaVT2.T10);
                    }
                    if (umidade >= 75 && temperatura > TEMPERATURA_NORMAL_INFERIOR && (temperatura < TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaVT2.T12);
                    }

                    // CHUVA QUENTE
                    if (umidade >= 25 && (temperatura >= TEMPERATURA_NORMAL_SUPERIOR)) {
                        render_preciptacao.setPixel(x, y, EscalaRT3.T5);
                    }
                    if (umidade >= 50 && temperatura >= TEMPERATURA_NORMAL_SUPERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaRT3.T10);
                    }
                    if (umidade >= 75 && temperatura >= TEMPERATURA_NORMAL_SUPERIOR) {
                        render_preciptacao.setPixel(x, y, EscalaRT3.T12);
                    }


                }
            }
        }

        return render_preciptacao;
    }


    public static void TRANSPOR_MASSA_DE_AR(AtzumTerra planeta, Renderizador render_massas_de_ar, Renderizador PROCESSO_TU, Renderizador render_preciptacao_valor, Renderizador render_chuva_tronarko) {

        Cores mCores = new Cores();


        for (int y = 0; y < render_massas_de_ar.getAltura(); y++) {
            for (int x = 0; x < render_massas_de_ar.getLargura(); x++) {

                if (render_massas_de_ar.getPixel(x, y).isDiferente(mCores.getPreto())) {
                    render_preciptacao_valor.setPixel(x, y, PROCESSO_TU.getPixel(x, y));

                    if (planeta.isTerra(x, y)) {
                        render_chuva_tronarko.setPixel(x, y, mCores.getAzul());
                    }
                }

            }
        }


    }

    public static Renderizador PROCESSAR_FATORES_CLIMATICOS(AtzumTerra planeta, double[] tronarko_temperatura, QTT umidade_dados, Renderizador render_massas_de_ar) {

        Cores mCores = new Cores();

        Atzum atzum = new Atzum();


        Renderizador render_chuva = Renderizador.construir(planeta.getLargura(), planeta.getAltura(), mCores.getPreto());

        int UMIDADE_MINIMA = 25;

        int TEMPERATURA_BAIXA = 5;
        int TEMPERATURA_ALTA = 36;

        int TEMPERATURA_ALTA_DE_VENTANIA = 35;

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {

                if (planeta.isTerra(x, y)) {

                    int temperatura = (int) tronarko_temperatura[(y * planeta.getLargura()) + x];
                    int umidade = umidade_dados.getValor(x, y);


                    if (render_massas_de_ar.getPixel(x, y).isDiferente(mCores.getPreto())) {

                        String massa_de_ar = atzum.getMassaDeArTipo(render_massas_de_ar.getPixel(x, y));


                        if (umidade >= UMIDADE_MINIMA) {

                            if (massa_de_ar.contains("FRIO") && temperatura <= TEMPERATURA_BAIXA) {
                                render_chuva.setPixel(x, y, Atzum.COR_NEVE);
                            } else if (massa_de_ar.contains("FRIO") && temperatura > TEMPERATURA_BAIXA && temperatura < TEMPERATURA_ALTA) {
                                render_chuva.setPixel(x, y, Atzum.COR_CHUVA);
                            } else if (massa_de_ar.contains("FRIO") && temperatura >= TEMPERATURA_ALTA_DE_VENTANIA) {
                                render_chuva.setPixel(x, y, Atzum.COR_VENTANIA);
                            } else if (massa_de_ar.contains("QUENTE") && temperatura > TEMPERATURA_BAIXA && temperatura < TEMPERATURA_ALTA) {
                                render_chuva.setPixel(x, y, Atzum.COR_CHUVA);
                            } else if (massa_de_ar.contains("QUENTE") && temperatura >= TEMPERATURA_ALTA) {
                                render_chuva.setPixel(x, y, Atzum.COR_ONDA_DE_CALOR);
                            } else if (massa_de_ar.contains("TEMPESTADE") && temperatura > TEMPERATURA_BAIXA && temperatura < TEMPERATURA_ALTA) {
                                render_chuva.setPixel(x, y, Atzum.COR_TEMPESTADE_CHUVA);
                            } else if (massa_de_ar.contains("TEMPESTADE") && temperatura <= TEMPERATURA_BAIXA) {
                                render_chuva.setPixel(x, y, Atzum.COR_TEMPESTADE_NEVE);
                            }


                            if (massa_de_ar.contains("SUPERFRIO") && temperatura > TEMPERATURA_BAIXA && temperatura < TEMPERATURA_ALTA) {
                                render_chuva.setPixel(x, y, Atzum.COR_NEVE);
                            } else if (massa_de_ar.contains("SUPERQUENTE") && temperatura > TEMPERATURA_BAIXA && temperatura < TEMPERATURA_ALTA) {
                                render_chuva.setPixel(x, y, Atzum.COR_TEMPESTADE_CHUVA);
                            }

                        } else {

                            if (massa_de_ar.contains("FRIO") && temperatura <= TEMPERATURA_BAIXA) {
                                render_chuva.setPixel(x, y, Atzum.COR_SECA_EXTREMA);
                            } else if (massa_de_ar.contains("QUENTE") && temperatura >= TEMPERATURA_ALTA) {
                                render_chuva.setPixel(x, y, Atzum.COR_SECA_EXTREMA);
                            } else if ((massa_de_ar.contains("FRIO") || massa_de_ar.contains("QUENTE")) && (temperatura > TEMPERATURA_BAIXA && temperatura < TEMPERATURA_ALTA)) {
                                render_chuva.setPixel(x, y, Atzum.COR_SECA);
                            } else if (massa_de_ar.contains("TEMPESTADE")) {
                                render_chuva.setPixel(x, y, Atzum.COR_TEMPESTADE_VENTO);
                            }

                        }
                    }


                }


            }
        }

        return render_chuva;
    }

    public static void CALCULAR_UMIDADE(boolean ANALISAR_VARIACAO, AtzumTerra planeta, double[] tronarko_umidade, Renderizador render_massas_de_ar, QTT dados_inferior, QTT dados_superior) {

        Atzum atzum = new Atzum();


        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {

                if (planeta.isTerra(x, y)) {

                    double umidade = tronarko_umidade[(y * planeta.getLargura()) + x];

                    if (ANALISAR_VARIACAO) {

                        String fator_climatico = atzum.getFatorClimatico(render_massas_de_ar.getPixel(x, y));


                        if (Strings.isIgual(fator_climatico, "CHUVA")) {
                            umidade += 1;
                        } else if (Strings.isIgual(fator_climatico, "NEVE")) {
                            umidade += 2;
                        } else if (Strings.isIgual(fator_climatico, "TEMPESTADE_CHUVA")) {
                            umidade += 3;
                        } else if (Strings.isIgual(fator_climatico, "TEMPESTADE")) {
                            umidade += 4;

                        } else if (Strings.isIgual(fator_climatico, "SECA")) {
                            umidade -= 1;
                        } else if (Strings.isIgual(fator_climatico, "SECA_EXTREMA")) {
                            umidade -= 2;
                        } else if (Strings.isIgual(fator_climatico, "ONDA_DE_CALOR")) {
                            umidade -= 3;

                        } else {

                            if (umidade > 0) {
                                umidade -= 0.5;
                            } else if (umidade < 0) {
                                umidade += 0.5;
                            }

                        }


                        int inferior = dados_inferior.getValor(x, y);
                        int superior = dados_superior.getValor(x, y);

                        int max_umidade = 10;
                        int min_umidade = 10;

                        if (superior >= 50) {
                            max_umidade = 40;
                        }

                        if (superior >= 80) {
                            max_umidade = 50;
                        }

                        if (inferior >= 50) {
                            min_umidade = 40;
                        }

                        if (inferior >= 80) {
                            min_umidade = 50;
                        }

                        min_umidade = min_umidade * (-1);


                        // APLICAR

                        if (umidade >= max_umidade) {
                            umidade = max_umidade;
                        }

                        if (umidade < min_umidade) {
                            umidade = min_umidade;
                        }

                    }


                    tronarko_umidade[(y * planeta.getLargura()) + x] = umidade;

                }


            }
        }

    }


    public static void EXIBIR_TRONARKO() {

        Lista<Entidade> dados_brutos = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko.entts"));

        // ENTT.EXIBIR_TABELA(dados_temp);
        // ENTT.EXIBIR_TABELA(ENTT.GET_FATIA_INICIO_PEQUENO(dados_brutos));

        Lista<Entidade> dados_resumidos = new Lista<Entidade>();

        Lista<Entidade> fatores_climaticos = new Lista<Entidade>();
        Lista<Entidade> estacoes_termicas = new Lista<Entidade>();


        int TEMPERATURA_QUENTE = 30;
        int TEMPERATURA_FRIA = 15;


        Unico<String> tipos_climaticos = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade cidade : dados_brutos) {

            Entidade e_cidade = ENTT.GET_SEMPRE(dados_resumidos, "Cidade", cidade.at("Cidade"));
            e_cidade.at("X", cidade.at("X"));
            e_cidade.at("Y", cidade.at("Y"));

            e_cidade.at("TMenor", cidade.atDouble("T1"));
            e_cidade.at("TMaior", cidade.atDouble("T1"));

            for (int superarko = 1; superarko <= 500; superarko++) {
                e_cidade.atSeMenor("TMenor", cidade.atDouble("T" + superarko));
                e_cidade.atSeMaior("TMaior", cidade.atDouble("T" + superarko));
            }

            e_cidade.at("UMenor", cidade.atDouble("U1"));
            e_cidade.at("UMaior", cidade.atDouble("U1"));

            for (int superarko = 1; superarko <= 500; superarko++) {
                e_cidade.atSeMenor("UMenor", cidade.atDouble("U" + superarko));
                e_cidade.atSeMaior("UMaior", cidade.atDouble("U" + superarko));
            }

            e_cidade.at("EstacoesTermicas", "");
            e_cidade.at("FatoresClimaticos", "");

            String massa_anterior = cidade.at("FC1");
            int massa_duracao = 0;
            String massa_inicio = "1";
            String massa_fim = "1";

            for (int s = 1; s <= 500; s++) {
                String massa_corrente = cidade.at("FC" + s);
                // fmt.print("Atual : {} - {}",cidade.at("Cidade"),massa_atual);

                if (!massa_corrente.isEmpty()) {
                    e_cidade.at(massa_corrente, "SIM");
                    tipos_climaticos.item(massa_corrente);
                }


                if (massa_anterior.contentEquals(massa_corrente)) {
                    massa_duracao += 1;
                    massa_fim = String.valueOf(s);
                } else {
                    Entidade fator_climatico = new Entidade();
                    fator_climatico.at("Cidade", cidade.at("Cidade"));
                    fator_climatico.at("Fator", massa_anterior);
                    fator_climatico.at("Inicio", massa_inicio);
                    fator_climatico.at("Fim", massa_fim);
                    fator_climatico.at("Tempo", massa_duracao);

                    fatores_climaticos.adicionar(fator_climatico);


                    massa_anterior = massa_corrente;
                    massa_duracao = 1;
                    massa_inicio = String.valueOf(s);
                    massa_fim = String.valueOf(s);

                }

            }

            Entidade fator_climatico = new Entidade();
            fator_climatico.at("Cidade", cidade.at("Cidade"));
            fator_climatico.at("Fator", massa_anterior);
            fator_climatico.at("Inicio", massa_inicio);
            fator_climatico.at("Fim", massa_fim);
            fator_climatico.at("Tempo", massa_duracao);

            fatores_climaticos.adicionar(fator_climatico);

            String s_fatores = "{ ";
            for (String fator : tipos_climaticos) {
                if (e_cidade.at(fator).contentEquals("SIM")) {
                    s_fatores += fator + " ";
                }
            }
            s_fatores = s_fatores + "}";

            e_cidade.at("FatoresClimaticos", s_fatores);


            String temperatura_anterior = TEMPERATURA_CLASSIFICAR(cidade.atDouble("T1"));
            int estacao_duracao = 0;
            String estacao_inicio = "1";
            String estacao_fim = "1";

            for (int s = 1; s <= 500; s++) {
                String temperatura_corrente = TEMPERATURA_CLASSIFICAR(cidade.atDouble("T" + s));

                if (temperatura_anterior.contentEquals(temperatura_corrente)) {
                    estacao_duracao += 1;
                    estacao_fim = String.valueOf(s);
                } else {
                    Entidade estacao_termica = new Entidade();
                    estacao_termica.at("Cidade", cidade.at("Cidade"));
                    estacao_termica.at("Estacao", temperatura_anterior);
                    estacao_termica.at("Inicio", estacao_inicio);
                    estacao_termica.at("Fim", estacao_fim);
                    estacao_termica.at("Tempo", estacao_duracao);
                    estacoes_termicas.adicionar(estacao_termica);

                    estacao_duracao = 1;
                    estacao_inicio = String.valueOf(s);
                    estacao_fim = String.valueOf(s);
                }

                if (s == 500) {
                    Entidade estacao_termica = new Entidade();
                    estacao_termica.at("Cidade", cidade.at("Cidade"));
                    estacao_termica.at("Estacao", temperatura_anterior);
                    estacao_termica.at("Inicio", estacao_inicio);
                    estacao_termica.at("Fim", estacao_fim);
                    estacao_termica.at("Tempo", estacao_duracao);
                    estacoes_termicas.adicionar(estacao_termica);
                }

                temperatura_anterior = temperatura_corrente;
            }

            String s_estacoes = "{ ";
            for (String estacao : ENTT.FILTRAR_UNICOS(ENTT.COLETAR(estacoes_termicas, "Cidade", cidade.at("Cidade")), "Estacao")) {
                s_estacoes += estacao + " ";
            }
            s_estacoes = s_estacoes + "}";
            e_cidade.at("EstacoesTermicas", s_estacoes);

        }

        ENTT.EXIBIR_TABELA(dados_brutos);

        //ENTT.EXIBIR_TABELA(dados_resumidos);

        /// ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados_brutos));
        //ENTT.EXIBIR_TABELA(fatores_climaticos);

        //  ENTT.EXIBIR_TABELA(ENTT.GET_FATIA_INICIO_PEQUENO(fatores_climaticos));
        // ENTT.EXIBIR_TABELA(estacoes_termicas);

        ENTT.GUARDAR(dados_brutos, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_7000/tronarko_7000.entts"));
        ENTT.GUARDAR(fatores_climaticos, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_7000/tronarko_fatores_climaticos.entts"));
        ENTT.GUARDAR(dados_resumidos, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_7000/tronarko_resumo.entts"));
        ENTT.GUARDAR(estacoes_termicas, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_7000/tronarko_estacoes.entts"));

    }

    public static String TEMPERATURA_CLASSIFICAR(double temperatura) {
        if (temperatura <= 15) {
            return "FRIO";
        } else if (temperatura >= 30) {
            return "QUENTE";
        } else {
            return "AMBIENTE";
        }
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

        Atzum mAtzum = new Atzum();
        for (Ponto sensor : mAtzum.GET_CIDADES()) {


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

        Lista<Entidade> dados_brutos = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

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

    public static void OBSERVAR_SENSORES() {

        //  ServicoTronarko.OBSERVAR_SENSORES >>> + 29 uz

        AtzumCreatorInfo.iniciar("ServicoTronarko.OBSERVAR_SENSORES");

        Lista<Entidade> dados_brutos = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));


        Cores mCores = new Cores();


        AtzumTerra planeta = new AtzumTerra();

        Vetor<Ponto> sensores = new Vetor<Ponto>(dados_brutos.getQuantidade());

        int sensor_id = 0;
        for (Entidade sensor : dados_brutos) {

            int px = sensor.atInt("X");
            int py = sensor.atInt("Y");

            sensores.set(sensor_id, new Ponto(px, py));
            sensor_id += 1;
        }


        fmt.print(">> Obtendo proximidade dos sensores");

        QTT proximidade = QTT.getTudo(AtzumCreator.LOCAL_GET_ARQUIVO("dados/sensor_proximidade.qtt"));


        fmt.print(">> Iniciando processo...");

        Empilhador video_sensores_observando = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando.vi"), planeta.getLargura() / 2, planeta.getAltura() / 2);

        Renderizador render_tronarko_sensores_original = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(render_tronarko_sensores_original, mCores.getAmarelo(), mCores.getBranco());

        for (int superarko = 1; superarko <= 500; superarko++) {

            fmt.print("Superarko : {}", superarko);

            Renderizador render_tronarko_sensores = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());


            for (int y = 0; y < planeta.getAltura(); y++) {
                for (int x = 0; x < planeta.getLargura(); x++) {

                    if (planeta.isTerra(x, y)) {

                        int sensor_proximo = proximidade.getValor(x, y);

                        Ponto proximo = sensores.get(sensor_proximo);

                        Entidade sensor = ENTT.GET_SEMPRE(dados_brutos, "Cidade", proximo.getX() + "::" + proximo.getY());

                        double temperatura = sensor.atDoubleOuPadrao("T" + superarko, 0.0);

                        if (temperatura >= 30) {
                            render_tronarko_sensores.setPixel(x, y, mCores.getVermelho());
                        } else if (temperatura <= 15) {
                            render_tronarko_sensores.setPixel(x, y, mCores.getAzul());
                        } else {
                            render_tronarko_sensores.setPixel(x, y, mCores.getVerde());
                        }


                    }
                }
            }


            video_sensores_observando.empurrarQuadro(Efeitos.reduzirMetade(render_tronarko_sensores.toImagemSemAlfa()));

        }


        video_sensores_observando.fechar();

        SnapShotter.CRIAR(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando.vi"), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados.png"));


        AtzumCreatorInfo.terminar("ServicoTronarko.OBSERVAR_SENSORES");
        AtzumCreatorInfo.exibir_item("ServicoTronarko.OBSERVAR_SENSORES");

        //  ENTT.EXIBIR_TABELA(dados_brutos);


    }

    public static void OBSERVAR_SENSORES_v2() {

        //  ServicoTronarko.OBSERVAR_SENSORES >>> + 29 uz

        AtzumCreatorInfo.iniciar("ServicoTronarko.OBSERVAR_SENSORES");

        fmt.print(">> Obtendo dados dos sensores...");

        Lista<Entidade> dados_brutos = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));


        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados_brutos));

        Cores mCores = new Cores();


        AtzumTerra planeta = new AtzumTerra();

        Vetor<Ponto> sensores = new Vetor<Ponto>(dados_brutos.getQuantidade());

        int sensor_id = 0;
        for (Entidade sensor : dados_brutos) {

            int px = sensor.atInt("X");
            int py = sensor.atInt("Y");

            sensores.set(sensor_id, new Ponto(px, py));
            sensor_id += 1;
        }


        fmt.print(">> Iniciando processo...");


        Renderizador render_tronarko_sensores_original = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(render_tronarko_sensores_original, mCores.getAmarelo(), mCores.getBranco());


        Empilhador video_sensores_observando = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando.vi"), planeta.getLargura() / 2, planeta.getAltura() / 2);
        Empilhador video_sensores_observando_chuva = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_chuva.vi"), planeta.getLargura() / 2, planeta.getAltura() / 2);
        Empilhador video_sensores_observando_seca = new VideoCodecador().criar(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_seca.vi"), planeta.getLargura() / 2, planeta.getAltura() / 2);


        int sensor_tamanho = 30;
        int sensor_tamanho_metade = sensor_tamanho / 2;

        for (int superarko = 1; superarko <= 500; superarko++) {

            fmt.print("Superarko : {}", superarko);

            Renderizador render_tronarko_sensores = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());
            Renderizador render_tronarko_sensores_chuva = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());
            Renderizador render_tronarko_sensores_seca = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());

            for (Ponto sensor : sensores) {

                Entidade e_sensor = ENTT.GET_SEMPRE(dados_brutos, "Sensor", sensor.getX() + "::" + sensor.getY());
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


        // Imagem.exportar(render_tronarko_sensores.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados.png"));

        video_sensores_observando.fechar();
        video_sensores_observando_chuva.fechar();
        video_sensores_observando_seca.fechar();

        SnapShotter.CRIAR(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando.vi"), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados.png"));
        SnapShotter.CRIAR(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_chuva.vi"), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados_chuva.png"));
        SnapShotter.CRIAR(AtzumCreator.LOCAL_GET_ARQUIVO("videos/sensores_observando_seca.vi"), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_observados_seca.png"));


        AtzumCreatorInfo.terminar("ServicoTronarko.OBSERVAR_SENSORES");
        AtzumCreatorInfo.exibir_item("ServicoTronarko.OBSERVAR_SENSORES");

        //  ENTT.EXIBIR_TABELA(dados_brutos);


    }

    public static void VIDEO_PLAYER() {
        AzzalUnico.unico("AppVideo", 2000, 1100, new AppVideo());
    }

    public static void SNAP_SHOTS() {
        AzzalUnico.unico("AtzumSnapShots", 2000, 1100, new AtzumSnapShots());
    }


    public static void MINIATURAS() {

        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/fatores_climaticos.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_fatores_climaticos.png");
        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/temperatura_zonas.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_temperatura_zonas.png");


    }


    public static void OBSERAR_VARIADORES() {


        fmt.print(">> Obtendo dados dos sensores...");

        Lista<Entidade> dados_brutos = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));


        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados_brutos));


        Lista<Entidade> dados_resumo = ENTT.CRIAR_LISTA();


        for (Entidade sensor : dados_brutos) {

            Entidade resumo = ENTT.GET_SEMPRE(dados_resumo, "Sensor", sensor.at("Sensor"));

            resumo.at("X", sensor.at("X"));
            resumo.at("Y", sensor.at("Y"));

            for (int s = 1; s <= 500; s++) {
                String fator_climatico = sensor.at("FC" + s);
                if (Strings.isValida(fator_climatico)) {
                    resumo.at(fator_climatico, resumo.atIntOuPadrao(fator_climatico, 0) + 1);
                }
            }

            int fc_t = resumo.atIntOuPadrao("TEMPESTADE", 0);


            int fc_seca = resumo.atIntOuPadrao("SECA", 0);
            int fc_se = resumo.atIntOuPadrao("SECA_EXTREMA", 0);
            int fc_oc = resumo.atIntOuPadrao("ONDA_DE_CALOR", 0);
            int fc_ventania = resumo.atIntOuPadrao("VENTANIA", 0);


            int fc_chuva = resumo.atIntOuPadrao("CHUVA", 0);
            int fc_neve = resumo.atIntOuPadrao("NEVE", 0);
            int fc_tc = resumo.atIntOuPadrao("TEMPESTADE_CHUVA", 0);
            int fc_tn = resumo.atIntOuPadrao("TEMPESTADE_NEVE", 0);

            int inferior = fc_chuva + fc_neve + fc_tc + fc_tn + fc_t;


            int superior = fc_seca + fc_se + fc_oc + fc_ventania + fc_t;

            resumo.at("INFERIOR", inferior);
            resumo.at("SUPERIOR", superior);

        }

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados_resumo));


        int maximo_superior = ENTT.GET_INTEIRO_MAIOR(dados_resumo, "SUPERIOR");
        int maximo_inferior = ENTT.GET_INTEIRO_MAIOR(dados_resumo, "INFERIOR");

        fmt.print("\t + SUPERIOR {}", maximo_superior);
        fmt.print("\t + INFERIOR {}", maximo_inferior);


        AtzumCentralDados.INFORME("Variacao.Superior", maximo_superior);
        AtzumCentralDados.INFORME("Variacao.Inferior", maximo_inferior);

        Cores mCores = new Cores();


        AtzumTerra planeta = new AtzumTerra();

        int sensor_tamanho = 30;
        int sensor_tamanho_metade = sensor_tamanho / 2;

        Renderizador render_tronarko_sensores_original = new Renderizador(AtzumCreator.GET_MAPA());
        Rasterizador.trocar_cores(render_tronarko_sensores_original, mCores.getAmarelo(), mCores.getBranco());
        Renderizador render_tronarko_sensores_inferior = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());
        Renderizador render_tronarko_sensores_superior = new Renderizador(render_tronarko_sensores_original.toImagemSemAlfa());


        QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("variacao_inferior.qtt"), planeta.getLargura(), planeta.getAltura());
        QTT.alocar(AtzumCreator.DADOS_GET_ARQUIVO("variacao_superior.qtt"), planeta.getLargura(), planeta.getAltura());

        QTT.alterar_todos(AtzumCreator.DADOS_GET_ARQUIVO("variacao_inferior.qtt"), planeta.getLargura(), planeta.getAltura(), -1);
        QTT.alterar_todos(AtzumCreator.DADOS_GET_ARQUIVO("variacao_superior.qtt"), planeta.getLargura(), planeta.getAltura(), -1);


        for (Entidade sensor : dados_resumo) {

            int x = sensor.atInt("X");
            int y = sensor.atInt("Y");


            int i_inferior = Matematica.ESCALA_NORMAL(sensor.atInt("INFERIOR"), maximo_inferior);
            int i_superior = Matematica.ESCALA_NORMAL(sensor.atInt("SUPERIOR"), maximo_superior);

            QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("variacao_inferior.qtt"), x, y, i_inferior);
            QTT.alterar(AtzumCreator.DADOS_GET_ARQUIVO("variacao_superior.qtt"), x, y, i_superior);


            AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores_inferior, x - sensor_tamanho_metade, y - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, new HSV(40, 100, HSV.NORMAL(i_inferior)).toRGB());
            AtzumCreator.PREENCHER_TERRA(planeta, render_tronarko_sensores_superior, x - sensor_tamanho_metade, y - sensor_tamanho_metade, sensor_tamanho, sensor_tamanho, new HSV(40, 100, HSV.NORMAL(i_superior)).toRGB());


        }


        render_tronarko_sensores_inferior.exportarSemAlfa(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_limites_inferior.png"));
        render_tronarko_sensores_superior.exportarSemAlfa(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_limites_superior.png"));

    }
}
