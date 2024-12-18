package apps.app_atzum.utils;

import apps.app_atzum.AtzumCreator;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;

public class AtzumUtilitario {


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


    public static String OBTER_TEMPO(Entidade item) {
        return item.at("Tempo");
    }


    public static void VER_CIDADES() {

        Lista<Entidade> cidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("parametros/CIDADES_NOMES.entts"));
        ENTT.EXIBIR_TABELA(cidades);

    }


}
