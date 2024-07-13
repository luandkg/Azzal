package apps.app_atzum;

import algoritmos.IMEmpacotador;
import apps.app_atzum.servicos.ServicoTronarko;
import apps.app_atzum.utils.AtzumCreatorInfo;
import libs.arquivos.IM;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.ds.DS;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.fs.Arquivo;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.Strings;
import libs.luan.fmt;

import java.awt.image.BufferedImage;

public class AtzumProcessoCriativo {

    public static void init() {


        AtzumCreatorInfo.exibir();
        // apps.app_atzum.Apps.INIT(Apps.MASSAS_DE_AR);

        fmt.print("");
        fmt.print("------------------------------------------");
        fmt.print("");
        fmt.print("");

        // ------------------- WORLD - BUILDING -----------------------

        // ServicoInicial.INIT();
        // ServicoRegioes.INIT();
        // ServicoRelevo.INIT();
        // ServicoUmidade.INIT();
        // ServicoTemperatura.INIT();
        // ServicoCorrelacionar.INIT();
        // ServicoMassasDeAr.INIT();
        //  ServicoTronarko.MAPEAR_SENSORES();
        //   ServicoTronarko.ORGANIZAR_SENSORES();


        // ------------------- TRONARKO BUILDING -----------------------

        // ServicoTronarko.CONSTRUIR_TRONARKO();
        //  ServicoMassasDeAr.PROCESSAR_TRONARKO();
        //  ServicoTronarko.CALCULAR_TRONARKO_TRANSICAO();


        // ------------------- VISUALIZADORES -----------------------

        // ServicoTronarko.OBSERVAR_SENSORES();
        //   ServicoTronarko.EXIBIR_TRONARKO();

      //  ServicoTronarko.OBSERVAR_SENSORES_v2();
        TronarkoAnaliseDeSensores.init();

        //ServicoTronarko.VIDEO_PLAYER();

        // TAKE_SHOT();

        //  for(Arquivo arquivo : new PastaFS("/home/luan/Imagens/atzum/parametros/massas_de_ar").getArquivos()){
        // ORGANIZAR_PARAMETROS_PONTOS(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/CIDADES.dkg"),AtzumCreator.LOCAL_GET_ARQUIVO("parametros/CIDADES.dkg"));
        //}

        //  AtzumCreatorInfo.renomear_item( "ServicoRegioes.PROXIMIDAE_COM_OCEANO","ServicoRegioes.PROXIMIDADE_COM_OCEANO");
        //   AtzumCreatorInfo.renomear_item( "ServicoRegioes.PROXIMIDAE_COM_TERRA","ServicoRegioes.PROXIMIDADE_COM_TERRA");



    }

    public static void ANTERIORMENTE() {

        //   ServicoRelevo.INIT();
        //ServicoTemperatura.INIT();


        //  ServicoRegioes.ORGANIZAR_REGIOES();

        //    ServicoRegioes.EXPANDIR_REGIOES_ATE_A_MARGEM();
        //   ServicoRegioes.ORGANIZAR_DADOS_REGIOES();
        //  ServicoRegioes.EXTRAIR_CONTORNOS();
        // ServicoRegioes.ORGANIZAR_DADOS_REGIOES_v2();
        //ServicoRegioes.EXTRAIR_MARGEM_OCEANICA();
        // ServicoRegioes.EXTRAIR_DISTANCIA_OCEANICA();
        // ServicoRegioes.PROXIMIDAE_COM_OCEANO();

        //  ServicoTemperatura.PROCESSAR();
        //  ServicoTemperatura.INIT_T2();
        //  ServicoTemperatura.RENDERIZAR_FAIXAS_DE_TEMPERATURA_T2();

        //   ServicoTronarko.CONSTRUIR_TRONARKO();

        //ServicoUmidade.INIT();
        // ServicoClima.INIT();
        // ServicoMassasDeAr.ORGANIZAR_MASSAS();
        //  ServicoMassasDeAr.INIT();


        //  ServicoTronarko.CALCULAR_TRONARKO_TRANSICAO();
        // ServicoTronarko.EXIBIR_TRONARKO();

        // TAKE_SHOT();

        //ServicoTronarko.VIDEO_PLAYER();
        //  ServicoComplexo.SNAP_SHOTS();


        // AtzumCreator.ORGANIZAR_OCEANOS();
        //  AtzumCreator.EMPACOTAR_ATZUM();


    }

    public static void TAKE_SHOT() {

        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/temperatura.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_temperatura.png");
        //  SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/preciptacao.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_preciptacao.png");
        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/preciptacao_valor.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_preciptacao_valor.png");

        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/tu.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_tu.png");

        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/tronarko_temperatura_e_massas_de_ar.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_temperatura_e_massas_de_ar.png");
        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/preciptacao_tronarko.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_500_preciptacao.png");
        SnapShotter.CRIAR("/home/luan/Imagens/atzum/videos/fatores_climaticos.vi", "/home/luan/Imagens/atzum/build/tronarko/tronarko_fatores_climaticos.png");

        SnapShotter.CRIAR_DUPLO("/home/luan/Imagens/atzum/videos/tronarko_temperatura_e_massas_de_ar.vi", "/home/luan/Imagens/atzum/videos/preciptacao_valor.vi", "/home/luan/Imagens/atzum/build/tronarko/massas_de_ar_movimentando.png");
    }


    public static void ORGANIZAR_PARAMETROS_PONTOS_E_VALOR(String arquivo, String arquivo_para) {

        DKG saida = new DKG();
        DKGObjeto saida_raiz = saida.unicoObjeto("Pontos");

        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);

        fmt.print("{}", documento.toDocumento());

        for (DKGObjeto item : documento.unicoObjeto("Cidades").getObjetos()) {
            fmt.print(">> {} : {}", item.identifique("X").getValor(), item.identifique("Y").getValor());

            DKGObjeto ponto = saida_raiz.criarObjeto("Ponto");
            ponto.identifique("X").setValor(item.identifique("X").getValor());
            ponto.identifique("Y").setValor(item.identifique("Y").getValor());
            ponto.identifique("Valor").setValor(item.identifique("Valor").getValor());

        }

        saida.salvar(arquivo_para);
    }

    public static void ORGANIZAR_PARAMETROS_PONTOS(String arquivo, String arquivo_para) {

        DKG saida = new DKG();
        DKGObjeto saida_raiz = saida.unicoObjeto("Pontos");

        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);

        fmt.print("{}", documento.toDocumento());

        for (DKGObjeto item : documento.unicoObjeto("Cidades").getObjetos()) {
            fmt.print(">> {} : {}", item.identifique("X").getValor(), item.identifique("Y").getValor());

            DKGObjeto ponto = saida_raiz.criarObjeto("Ponto");
            ponto.identifique("X").setValor(item.identifique("X").getValor());
            ponto.identifique("Y").setValor(item.identifique("Y").getValor());

        }

        saida.salvar(arquivo_para);
    }


}
