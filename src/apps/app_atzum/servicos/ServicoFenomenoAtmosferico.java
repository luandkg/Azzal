package apps.app_atzum.servicos;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.Aleatorio;
import libs.luan.Lista;
import libs.luan.fmt;

import java.awt.image.BufferedImage;


public class ServicoFenomenoAtmosferico {


    public static Renderizador MAPA_TEMPESTADE_INICIAR() {

        Cores mCores = new Cores();

        AtzumTerra mapa_planeta = new AtzumTerra();

        Renderizador render = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

        return render;
    }

    public static void PROCESSAR_FURACAO(AtzumTerra planeta, Atzum atzum, Renderizador mapa, Renderizador render_massas_de_ar) {

        Cor COR_AZUL = Atzum.FENOMENO_COR_FURACAO;

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isOceano(x, y)) {

                    String massa_de_ar = atzum.getMassaDeArTipo(render_massas_de_ar.getPixel(x, y));

                    if (massa_de_ar.contains("TEMPESTADE")) {
                        mapa.drawPixel(x, y, COR_AZUL);
                    }

                }
            }
        }

    }

    public static void PROCESSAR_TORNADO(AtzumTerra planeta, Atzum atzum, Renderizador mapa, Renderizador render_massas_de_ar) {

        Cor COR_LARANJA = Atzum.FENOMENO_COR_TORNADO;

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isTerra(x, y)) {

                    String massa_de_ar = atzum.getMassaDeArTipo(render_massas_de_ar.getPixel(x, y));

                    if (massa_de_ar.contains("TEMPESTADE")) {
                        mapa.drawPixel(x, y, COR_LARANJA);
                    }

                }
            }
        }

    }


    public static String ARQUIVO_FENOMENOS_FURACOES() {
        return AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/fenomenos_furacoes.entts");
    }

    public static String ARQUIVO_FENOMENOS_TORNADOS() {
        return AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/fenomenos_tornados.entts");
    }

    public static String ARQUIVO_FENOMENOS_ATMOSFERICOS() {
        return AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/fenomenos_atmosfericos.entts");
    }


    public static void ZERAR() {

        Lista<Entidade> dados_furacoes = new Lista<Entidade>();


        for (int as = 0; as < 15; as++) {
            Entidade a = ENTT.CRIAR_EM_SEQUENCIALMENTE(dados_furacoes, "ID");
            a.at("Maximo", Aleatorio.aleatorio_entre(1000, 5000));
            a.at("Valor", Aleatorio.aleatorio(a.atInt("Maximo")));
        }

        ENTT.GUARDAR(dados_furacoes, ARQUIVO_FENOMENOS_FURACOES());

        Lista<Entidade> dados_tornados = new Lista<Entidade>();

        for (int as = 0; as < 15; as++) {
            Entidade a = ENTT.CRIAR_EM_SEQUENCIALMENTE(dados_tornados, "ID");
            a.at("Maximo", Aleatorio.aleatorio_entre(1000, 5000));
            a.at("Valor", Aleatorio.aleatorio(a.atInt("Maximo")));
        }

        ENTT.GUARDAR(dados_tornados, ARQUIVO_FENOMENOS_TORNADOS());

    }

    public static void PROCESSAR_TRONARKO(int tronarko) {

        BufferedImage zona_de_furacoes = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/tronarko_zona_de_furacoes.png"));
        BufferedImage zona_de_tornados = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/tronarko_zona_de_tornados.png"));


        Cores mCores = new Cores();
        Cor cor_furacao = Atzum.FENOMENO_COR_FURACAO;
        Cor cor_tornado = Atzum.FENOMENO_COR_TORNADO;


        Lista<Entidade> tronarko_atividade_atmosfericos = new Lista<Entidade>();

        Lista<Entidade> dados_furacoes = ENTT.ABRIR(ARQUIVO_FENOMENOS_FURACOES());
        Lista<Entidade> dados_tornados = ENTT.ABRIR(ARQUIVO_FENOMENOS_TORNADOS());

        ENTT.EXIBIR_TABELA_COM_TITULO(dados_furacoes, "@FURACOES");
        ENTT.EXIBIR_TABELA_COM_TITULO(dados_tornados, "@TORNADOS");


        for (int superarko = 1; superarko <= 500; superarko++) {

            tronarko_atividade_atmosfericos.adicionar_varios(processar_furacao(dados_furacoes, cor_furacao, zona_de_furacoes, tronarko, superarko));
            tronarko_atividade_atmosfericos.adicionar_varios(processar_tornado(dados_tornados, cor_tornado, zona_de_tornados, tronarko, superarko));

        }


        ENTT.GUARDAR(dados_furacoes, ARQUIVO_FENOMENOS_FURACOES());
        ENTT.GUARDAR(dados_tornados, ARQUIVO_FENOMENOS_TORNADOS());


        RENDER(tronarko);

    }

    public static Lista<Entidade> processar_furacao(Lista<Entidade> dados_furacoes, Cor cor_furacao, BufferedImage mapa_zona_de_furacoes, int tronarko, int superarko) {

        Lista<Entidade> retornar_fenonemos = new Lista<Entidade>();

        for (Entidade a : dados_furacoes) {

            a.at("Evento", "NAO");

            a.at("Valor", a.atInt("Valor") + Aleatorio.aleatorio(5));


            if (a.atInt("Valor") >= a.atInt("Maximo")) {
                a.at("Valor", a.atInt("Valor") - a.atInt("Maximo"));
                a.at("Maximo", Aleatorio.aleatorio_entre(1000, 5000));

                if (Aleatorio.aleatorio(100) > 70) {
                    a.at("Evento", "SIM");
                    a.at("Escala", Aleatorio.aleatorio_entre(1, 10));
                }

            }

            if (a.is("Evento", "SIM")) {

                long tamanho_zona_de_furacoes = 0;
                for (int py = 0; py < mapa_zona_de_furacoes.getHeight(); py++) {
                    for (int px = 0; px < mapa_zona_de_furacoes.getWidth(); px++) {
                        Cor cor = Cor.getInt(mapa_zona_de_furacoes.getRGB(px, py));
                        if (cor.igual(cor_furacao)) {
                            tamanho_zona_de_furacoes += 1;
                        }
                    }
                }

                fmt.print("Total                    :: {}", (mapa_zona_de_furacoes.getWidth() * mapa_zona_de_furacoes.getHeight()));
                fmt.print("Tamanho Zona de Furacoes :: {}", tamanho_zona_de_furacoes);

                fmt.print("Fatores Atividade de Furacoes :: {}", dados_furacoes.getQuantidade());

                long furacoes_taxa = tamanho_zona_de_furacoes / dados_furacoes.getQuantidade();

                long contagem_furacoes_inicio = 0;
                long contagem_furacoes_final = furacoes_taxa;

                for (Entidade b : dados_furacoes) {

                    b.at("Inicio", contagem_furacoes_inicio);
                    b.at("Fim", contagem_furacoes_final);

                    contagem_furacoes_inicio += furacoes_taxa;
                    contagem_furacoes_final += furacoes_taxa;

                }

                boolean adicionar_evento = false;

                int sorteado = Aleatorio.aleatorio_entre(a.atInt("Inicio"), a.atInt("Fim"));

                long contagem = 0;
                for (int py = 0; py < mapa_zona_de_furacoes.getHeight(); py++) {
                    for (int px = 0; px < mapa_zona_de_furacoes.getWidth(); px++) {
                        Cor cor = Cor.getInt(mapa_zona_de_furacoes.getRGB(px, py));
                        if (cor.igual(cor_furacao)) {
                            if (contagem == sorteado) {
                                a.at("X", px);
                                a.at("Y", py);

                                //fmt.print("\t ++ Trocar {} para :: {} -- {} :: {}", a.at("ID"), area_sismica, px, py);
                                adicionar_evento = true;
                                break;
                            }
                            contagem += 1;
                        }
                    }
                    if (contagem == sorteado) {
                        break;
                    }
                }


                fmt.print("Superarko {} -- {} >> Furacao !!!", superarko, a.at("ID"));
                ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(a));

                a.at("Superarko", superarko);
                a.at("Tozte", SUPERARKOS_DO_TRONARKO_PARA_TOZTE(superarko, tronarko));

                if (adicionar_evento) {
                    a.at("Tipo", "FURACAO");
                    retornar_fenonemos.adicionar(a.getCopia());
                }
            }

        }

        return retornar_fenonemos;
    }

    public static Lista<Entidade> processar_tornado(Lista<Entidade> dados_tornados, Cor cor_tornado, BufferedImage mapa_zona_de_tornados, int tronarko, int superarko) {

        Lista<Entidade> retornar_fenonemos = new Lista<Entidade>();

        for (Entidade a : dados_tornados) {

            a.at("Evento", "NAO");

            a.at("Valor", a.atInt("Valor") + Aleatorio.aleatorio(5));


            if (a.atInt("Valor") >= a.atInt("Maximo")) {
                a.at("Valor", a.atInt("Valor") - a.atInt("Maximo"));
                a.at("Maximo", Aleatorio.aleatorio_entre(1000, 5000));

                if (Aleatorio.aleatorio(100) > 70) {
                    a.at("Evento", "SIM");
                    a.at("Escala", Aleatorio.aleatorio_entre(1, 10));
                }

            }

            if (a.is("Evento", "SIM")) {

                long tamanho_zona_de_tornados = 0;
                for (int py = 0; py < mapa_zona_de_tornados.getHeight(); py++) {
                    for (int px = 0; px < mapa_zona_de_tornados.getWidth(); px++) {
                        Cor cor = Cor.getInt(mapa_zona_de_tornados.getRGB(px, py));
                        if (cor.igual(cor_tornado)) {
                            tamanho_zona_de_tornados += 1;
                        }
                    }
                }

                fmt.print("Total                    :: {}", (mapa_zona_de_tornados.getWidth() * mapa_zona_de_tornados.getHeight()));
                fmt.print("Tamanho Zona de Tornados :: {}", tamanho_zona_de_tornados);

                fmt.print("Fatores Atividade de Tornados :: {}", dados_tornados.getQuantidade());

                long taxa_de_tornados = tamanho_zona_de_tornados / dados_tornados.getQuantidade();

                long contagem_tornados_inicio = 0;
                long contagem_tornados_final = taxa_de_tornados;

                for (Entidade b : dados_tornados) {

                    b.at("Inicio", contagem_tornados_inicio);
                    b.at("Fim", contagem_tornados_final);

                    contagem_tornados_inicio += taxa_de_tornados;
                    contagem_tornados_final += taxa_de_tornados;

                }

                boolean adicionar_evento = false;

                int sorteado = Aleatorio.aleatorio_entre(a.atInt("Inicio"), a.atInt("Fim"));

                long contagem = 0;
                for (int py = 0; py < mapa_zona_de_tornados.getHeight(); py++) {
                    for (int px = 0; px < mapa_zona_de_tornados.getWidth(); px++) {
                        Cor cor = Cor.getInt(mapa_zona_de_tornados.getRGB(px, py));
                        if (cor.igual(cor_tornado)) {
                            if (contagem == sorteado) {
                                a.at("X", px);
                                a.at("Y", py);

                                //fmt.print("\t ++ Trocar {} para :: {} -- {} :: {}", a.at("ID"), area_sismica, px, py);
                                adicionar_evento = true;
                                break;
                            }
                            contagem += 1;
                        }
                    }
                    if (contagem == sorteado) {
                        break;
                    }
                }


                fmt.print("Superarko {} -- {} >> Furacao !!!", superarko, a.at("ID"));
                ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(a));

                a.at("Superarko", superarko);
                a.at("Tozte", SUPERARKOS_DO_TRONARKO_PARA_TOZTE(superarko, tronarko));

                if (adicionar_evento) {
                    a.at("Tipo", "TORNADO");
                    retornar_fenonemos.adicionar(a.getCopia());
                }
            }

        }

        return retornar_fenonemos;
    }


    public static String SUPERARKOS_DO_TRONARKO_PARA_TOZTE(int superarko, int eTronarko) {

        int hiperarko = 1;
        while (superarko > 50) {
            hiperarko += 1;
            superarko -= 50;
        }

        return fmt.numero_zerado_c2(superarko) + "/" + fmt.numero_zerado_c2(hiperarko) + "/" + eTronarko;
    }


    public static void RENDER(int tronarko){

       Lista<Entidade> tronarko_atividade_atmosfericos= ENTT.ABRIR( ARQUIVO_FENOMENOS_ATMOSFERICOS());

       Cores mCores = new Cores();

        ENTT.EXIBIR_TABELA_COM_TITULO(tronarko_atividade_atmosfericos, "FENOMENOS ATMOSFERICOS");

        BufferedImage mapa_atividade_atmosferico = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_regioes_contornos.png"));

        Renderizador render = new Renderizador(mapa_atividade_atmosferico);

        Fonte ESCRITOR_NORMAL_BRANCO = new FonteRunTime(mCores.getBranco(), 20);
        ESCRITOR_NORMAL_BRANCO.setRenderizador(render);

        for (Entidade fenomeno : tronarko_atividade_atmosfericos) {

            if (fenomeno.is("Tipo", "FURACAO")) {
                Entidade furacao = fenomeno;
                render.drawCirculoCentralizado_Pintado(furacao.atInt("X"), furacao.atInt("Y"), 15, Cores.hexToCor("#B3E5FC"));
                render.drawCirculoCentralizado_Pintado(furacao.atInt("X"), furacao.atInt("Y"), 5, Cores.hexToCor("#43A047"));

                ESCRITOR_NORMAL_BRANCO.escreva(furacao.atInt("X"), furacao.atInt("Y") + 20, "Furacão : " + furacao.at("Escala"));
                ESCRITOR_NORMAL_BRANCO.escreva(furacao.atInt("X"), furacao.atInt("Y") + 60, "Superarko :: " + SUPERARKOS_DO_TRONARKO_PARA_TOZTE(furacao.atInt("Superarko"), tronarko));
            }else   if (fenomeno.is("Tipo", "TORNADO")) {
                Entidade tornado = fenomeno;
                render.drawCirculoCentralizado_Pintado(tornado.atInt("X"), tornado.atInt("Y"), 15, Cores.hexToCor("#FFEE58"));
                render.drawCirculoCentralizado_Pintado(tornado.atInt("X"), tornado.atInt("Y"), 5, Cores.hexToCor("#FFA000"));

                ESCRITOR_NORMAL_BRANCO.escreva(tornado.atInt("X"), tornado.atInt("Y") + 20, "Tornado : " + tornado.at("Escala"));
                ESCRITOR_NORMAL_BRANCO.escreva(tornado.atInt("X"), tornado.atInt("Y") + 60, "Superarko :: " + SUPERARKOS_DO_TRONARKO_PARA_TOZTE(tornado.atInt("Superarko"), tronarko));
            }

        }



        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/atzum_fenomenos_atmosfericos.png"));
        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/atzum_fenomenos_atmosfericos_tronarko_" + tronarko + ".png"));



    }
}
