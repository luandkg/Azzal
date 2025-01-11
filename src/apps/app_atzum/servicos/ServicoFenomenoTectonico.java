package apps.app_atzum.servicos;

import apps.app_attuz.Sociedade.NomeadorBurgo;
import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.QTT;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.meta_functional.FuncaoGama;
import libs.tronarko.utils.StringTronarko;

import java.awt.image.BufferedImage;

public class ServicoFenomenoTectonico {



    public static String ARQUIVO_VULCANISMO() {
        return AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/vulcanismo.entts");
    }

    public static String ARQUIVO_SISMICIDADE() {
        return AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/sismicidade.entts");
    }

    public static String ARQUIVO_FENOMENOS_TECTONICOS() {
        return AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/fenomenos_tectonicos.entts");
    }

    public static void ZERAR() {

        Lista<Entidade> dados_vulcanismo = ENTT.ABRIR(AtzumCreator.DADOS_GET_ARQUIVO("vulcanismo.entts"));
        ENTT.GUARDAR(dados_vulcanismo, ARQUIVO_VULCANISMO());


        Lista<Entidade> dados_sismicidade = ENTT.CRIAR_LISTA();
        ENTT.GUARDAR(dados_sismicidade, ARQUIVO_SISMICIDADE());


        Embaralhar.emabaralhe(dados_vulcanismo);

        for (Entidade vulcao : dados_vulcanismo) {

            int dormencia = Aleatorio.aleatorio_entre(30000, 70000);
            vulcao.at("Dormencia", dormencia);
            vulcao.at("Sismicidade", dormencia - Aleatorio.aleatorio_entre(10, 15));


            vulcao.at("Valor", Aleatorio.aleatorio(vulcao.atInt("Dormencia")));
            if (vulcao.atInt("Escala") == 0) {
                vulcao.at("Escala", 1);
            }
        }


        Embaralhar.emabaralhe(dados_vulcanismo);

        int mais = 0;

        for (Entidade vulcao : dados_vulcanismo) {

            if (mais >= 20) {

                int dormencia = vulcao.atInt("Dormencia") + Aleatorio.aleatorio_entre(10000, 30000);
                vulcao.at("Dormencia", dormencia);
                vulcao.at("Sismicidade", dormencia - Aleatorio.aleatorio_entre(10, 15));


                vulcao.at("Valor", Aleatorio.aleatorio(vulcao.atInt("Dormencia")));

                mais = 0;
            }

            mais += Aleatorio.aleatorio(5);
        }

        Embaralhar.emabaralhe(dados_vulcanismo);

        mais = 0;

        for (Entidade vulcao : dados_vulcanismo) {

            if (mais >= 30) {

                int dormencia = vulcao.atInt("Dormencia") + Aleatorio.aleatorio_entre(10000, 30000);
                vulcao.at("Dormencia", dormencia);
                vulcao.at("Sismicidade", dormencia - Aleatorio.aleatorio_entre(10, 15));


                vulcao.at("Valor", Aleatorio.aleatorio(vulcao.atInt("Dormencia")));

                mais = 0;
            }

            mais += Aleatorio.aleatorio(5);
        }


        ENTT.ORDENAR_INTEIRO(dados_vulcanismo, "VID");


        for (int as = 0; as < 15; as++) {
            Entidade a = ENTT.CRIAR_EM_SEQUENCIALMENTE(dados_sismicidade, "ID");
            a.at("Maximo", Aleatorio.aleatorio_entre(1000, 5000));
            a.at("Valor", Aleatorio.aleatorio(a.atInt("Maximo")));
            a.at("Trocar", Aleatorio.aleatorio(100));
        }

        ENTT.GUARDAR(dados_vulcanismo, ARQUIVO_VULCANISMO());
        ENTT.GUARDAR(dados_sismicidade, ARQUIVO_SISMICIDADE());

    }

    public static void INIT(int tronarko) {

        fmt.print(">> Simulação de fenomenos iniciada !");

      //  ZERAR();

        Lista<Entidade> dados_vulcanismo = ENTT.ABRIR(ARQUIVO_VULCANISMO());
        Lista<Entidade> dados_atividade_sismica = ENTT.ABRIR(ARQUIVO_SISMICIDADE());


        ENTT.EXIBIR_TABELA_COM_TITULO(dados_vulcanismo, "VULCANISMO");
        ENTT.EXIBIR_TABELA_COM_TITULO(dados_atividade_sismica, "ATIVIDADE SISMICA");

        simular_tudo(tronarko, dados_atividade_sismica, dados_vulcanismo);

        ENTT.GUARDAR(dados_vulcanismo, ARQUIVO_VULCANISMO());
        ENTT.GUARDAR(dados_atividade_sismica, ARQUIVO_SISMICIDADE());

        fmt.print(">> Simulação de fenomenos finalizada !");

    }

    public static void VER_DADOS(){

        Lista<Entidade> dados_vulcanismo = ENTT.ABRIR(ARQUIVO_VULCANISMO());
        Lista<Entidade> dados_atividade_sismica = ENTT.ABRIR(ARQUIVO_SISMICIDADE());

        ENTT.EXIBIR_TABELA_COM_TITULO(dados_vulcanismo,"@VULCANISMO");
        ENTT.EXIBIR_TABELA_COM_TITULO(dados_atividade_sismica,"@SISMOS");


    }

    public static void atividade_sismica_processar(AtzumTerra atzum_terra, Entidade a, BufferedImage atividade_sismica, Cor amarelo, Cor azul) {

        a.at("Evento", "NAO");

        a.at("Valor", a.atInt("Valor") + Aleatorio.aleatorio(5));
        a.at("Trocar", a.atInt("Trocar") + Aleatorio.aleatorio(5));


        if (!a.atributo_existe("X") && !a.atributo_existe("Y")) {

            int sorteado = Aleatorio.aleatorio_entre(a.atInt("Inicio"), a.atInt("Fim"));

            long area_sismica = 0;
            for (int py = 0; py < atividade_sismica.getHeight(); py++) {
                for (int px = 0; px < atividade_sismica.getWidth(); px++) {
                    Cor cor = Cor.getInt(atividade_sismica.getRGB(px, py));
                    if (cor.igual(amarelo) || cor.igual(azul)) {
                        if (area_sismica == sorteado) {
                            a.at("X", px);
                            a.at("Y", py);

                            if (atzum_terra.isOceano(px, py)) {
                                a.at("Tipo", "Aquatico");
                            } else {
                                a.at("Tipo", "Terrestre");
                            }


                            //fmt.print("\t ++ Trocar {} para :: {} -- {} :: {}", a.at("ID"), area_sismica, px, py);

                            break;
                        }
                        area_sismica += 1;
                    }
                }
                if (area_sismica == sorteado) {
                    break;
                }
            }
        }


        if (a.atInt("Trocar") >= 100) {
            a.at("Trocar", 0);


            int sorteado = Aleatorio.aleatorio_entre(a.atInt("Inicio"), a.atInt("Fim"));
            //  fmt.print("Trocar pos :: {}",sorteado);

            long area_sismica = 0;
            for (int py = 0; py < atividade_sismica.getHeight(); py++) {
                for (int px = 0; px < atividade_sismica.getWidth(); px++) {
                    Cor cor = Cor.getInt(atividade_sismica.getRGB(px, py));
                    if (cor.igual(amarelo) || cor.igual(azul)) {
                        if (area_sismica == sorteado) {
                            a.at("X", px);
                            a.at("Y", py);

                            if (atzum_terra.isOceano(px, py)) {
                                a.at("Tipo", "Aquatico");
                            } else {
                                a.at("Tipo", "Terrestre");
                            }
                            //fmt.print("\t ++ Trocar {} para :: {} -- {} :: {}", a.at("ID"), area_sismica, px, py);

                            break;
                        }
                        area_sismica += 1;
                    }
                }
                if (area_sismica == sorteado) {
                    break;
                }
            }

        }

        if (a.atInt("Valor") >= a.atInt("Maximo")) {
            a.at("Valor", a.atInt("Valor") - a.atInt("Maximo"));
            a.at("Maximo", Aleatorio.aleatorio_entre(1000, 5000));

            if (Aleatorio.aleatorio(100) > 70) {
                a.at("Evento", "SIM");
                a.at("Escala", Aleatorio.aleatorio_entre(1, 12));
            }

        }

    }

    public static void vulcanismo_processar(Entidade e_vulcao) {

        e_vulcao.at("Evento", "NAO");
        e_vulcao.at("Valor", e_vulcao.atInt("Valor") + Aleatorio.aleatorio(5));

        if (e_vulcao.atInt("Valor") >= e_vulcao.atInt("Sismicidade")) {
            e_vulcao.at("Evento", "SISMICO");
            e_vulcao.at("AtividadeSismica", "NAO");

            if (Aleatorio.aleatorio(100) > 50) {
                e_vulcao.at("AtividadeSismica", "SIM");
                if (e_vulcao.atInt("Escala") == 1) {
                    e_vulcao.at("Escala", 1);
                } else {
                    e_vulcao.at("Escala", Aleatorio.aleatorio_entre(1, e_vulcao.atInt("Escala")));
                }
            }

        }

        if (e_vulcao.atInt("Valor") >= e_vulcao.atInt("Dormencia")) {
            e_vulcao.at("Evento", "VULCANISMO");
            e_vulcao.at("Valor", 0);
        }

    }

    public static void simular_tudo(int tronarko, Lista<Entidade> dados_atividade_sismica, Lista<Entidade> dados_vulcanismo) {


        BufferedImage atividade_sismica = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("build/tectonico/atzum_tectonismo_atividade_sismica.png"));

        fmt.print("++ Largura : {}", atividade_sismica.getWidth());
        fmt.print("++ Altura  : {}", atividade_sismica.getHeight());

        Cores mCores = new Cores();
        Cor amarelo = mCores.getAmarelo();
        Cor azul = mCores.getAzul();


        long area_sismica = 0;
        for (int py = 0; py < atividade_sismica.getHeight(); py++) {
            for (int px = 0; px < atividade_sismica.getWidth(); px++) {
                Cor cor = Cor.getInt(atividade_sismica.getRGB(px, py));
                if (cor.igual(amarelo) || cor.igual(azul)) {
                    area_sismica += 1;
                }
            }
        }

        fmt.print("Total   :: {}", (atividade_sismica.getWidth() * atividade_sismica.getHeight()));
        fmt.print("Sismica :: {}", area_sismica);

        fmt.print("Fatores Atividade Sismica :: {}", dados_atividade_sismica.getQuantidade());

        long area_sismica_taxa = area_sismica / dados_atividade_sismica.getQuantidade();

        long sismica_inicial = 0;
        long sismica_final = area_sismica_taxa;

        for (Entidade a : dados_atividade_sismica) {

            a.at("Inicio", sismica_inicial);
            a.at("Fim", sismica_final);

            sismica_inicial += area_sismica_taxa;
            sismica_final += area_sismica_taxa;

        }


        Lista<Entidade> tronarko_vulcanismo = new Lista<Entidade>();
        Lista<Entidade> tronarko_vulcanismo_sismica = new Lista<Entidade>();
        Lista<Entidade> tronarko_atividade_sismica = new Lista<Entidade>();


        AtzumTerra atzum_terra = new AtzumTerra();

        for (int superarko = 1; superarko <= 500; superarko++) {

            for (Entidade a : dados_atividade_sismica) {

                atividade_sismica_processar(atzum_terra, a, atividade_sismica, amarelo, azul);

                if (a.is("Evento", "SIM")) {
                    fmt.print("Superarko {} -- {} >> Terremoto !!!", superarko, a.at("ID"));
                    ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(a));

                    a.at("Superarko", superarko);

                    tronarko_atividade_sismica.adicionar(a.getCopia());
                }
            }


            for (Entidade e_vulcao : dados_vulcanismo) {
                vulcanismo_processar(e_vulcao);
            }

            Lista<Entidade> vulcoes_sismicidade = ENTT.COLETAR_E_COLETAR(dados_vulcanismo, "Evento", "SISMICO", "AtividadeSismica", "SIM");
            Lista<Entidade> vulcoes_vulcanismo = ENTT.COLETAR(dados_vulcanismo, "Evento", "VULCANISMO");

            if (vulcoes_sismicidade.possuiObjetos()) {
                ENTT.EXIBIR_TABELA_COM_TITULO(vulcoes_sismicidade, "ATIVIDADE VULCÕES :: SISMICIDADE :: " + superarko);

                ENTT.ATRIBUTO_TODOS(vulcoes_sismicidade, "Superarko", superarko);

                tronarko_vulcanismo_sismica.adicionar_varios(ENTT.COPIAR(vulcoes_sismicidade));
            }

            if (vulcoes_vulcanismo.possuiObjetos()) {
                ENTT.ATRIBUTO_REMOVER(vulcoes_vulcanismo, "AtividadeSismica");
                ENTT.ATRIBUTO_REMOVER(vulcoes_vulcanismo, "SismicaEscala");
                ENTT.EXIBIR_TABELA_COM_TITULO(vulcoes_vulcanismo, "ATIVIDADE VULCÕES :: VULCANISMO :: " + superarko);

                ENTT.ATRIBUTO_TODOS(vulcoes_vulcanismo, "Superarko", superarko);

                tronarko_vulcanismo.adicionar_varios(ENTT.COPIAR(vulcoes_vulcanismo));
            }


        }


        ENTT.ATRIBUTO_TODOS(tronarko_atividade_sismica, "AtividadeTerrestre", "TERREMOTO");
        ENTT.ATRIBUTO_TODOS(tronarko_vulcanismo_sismica, "AtividadeTerrestre", "TERREMOTO");
        ENTT.ATRIBUTO_TODOS(tronarko_vulcanismo, "AtividadeTerrestre", "VULCANISMO");

        fmt.print("Processar Tronarko :: " + tronarko);

        ENTT.EXIBIR_TABELA(tronarko_atividade_sismica);
        ENTT.EXIBIR_TABELA(tronarko_vulcanismo_sismica);
        ENTT.EXIBIR_TABELA(tronarko_vulcanismo);


        fmt.print("");
        fmt.print("------------ ATIVIDADE TERRESTRE :: LOGS -----------------");
        fmt.print("");

        Lista<Entidade> atividade_terrestre = new Lista<Entidade>();

        atividade_terrestre.adicionar_varios(tronarko_atividade_sismica);
        atividade_terrestre.adicionar_varios(tronarko_vulcanismo_sismica);
        atividade_terrestre.adicionar_varios(tronarko_vulcanismo);

        for (Entidade e : atividade_terrestre) {
            e.at("Tozte",  StringTronarko.SUPERARKOS_DO_TRONARKO_PARA_TOZTE(e.atInt("Superarko"), tronarko));
        }

        ENTT.ORDENAR_INTEIRO(atividade_terrestre, "Superarko");
        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(atividade_terrestre, "Tozte");
        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(atividade_terrestre, "AtividadeTerrestre");

        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "VID");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Sismicidade");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Dormencia");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Valor");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Evento");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "ID");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Maximo");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Trocar");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Inicio");
        ENTT.ATRIBUTO_REMOVER(atividade_terrestre, "Fim");

        ENTT.AT_ALTERAR_NOME(atividade_terrestre, "AtividadeTerrestre", "Evento");

        ENTT.EXIBIR_TABELA(atividade_terrestre);


        BufferedImage mapa_atividade_terrestre = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("atzum_regioes_contornos.png"));

        Renderizador render = new Renderizador(mapa_atividade_terrestre);

        Fonte ESCRITOR_NORMAL_BRANCO = new FonteRunTime(mCores.getBranco(), 20);
        ESCRITOR_NORMAL_BRANCO.setRenderizador(render);

        for (Entidade sismica : tronarko_atividade_sismica) {

            //  ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(sismica));

            sismica.at("TemVulcanismo", "NAO");

            render.drawCirculoCentralizado_Pintado(sismica.atInt("X"), sismica.atInt("Y"), 15, Cores.hexToCor("#B3E5FC"));
            render.drawCirculoCentralizado_Pintado(sismica.atInt("X"), sismica.atInt("Y"), 5, Cores.hexToCor("#43A047"));

            ESCRITOR_NORMAL_BRANCO.escreva(sismica.atInt("X"), sismica.atInt("Y") + 20, "Terremoto : " + sismica.at("Escala"));
            ESCRITOR_NORMAL_BRANCO.escreva(sismica.atInt("X"), sismica.atInt("Y") + 60, "Superarko :: " +  StringTronarko.SUPERARKOS_DO_TRONARKO_PARA_TOZTE(sismica.atInt("Superarko"), tronarko));

        }

        for (Entidade sismica : tronarko_vulcanismo_sismica) {

            //   ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(sismica));

            boolean deve_renderizar = true;

            for (Entidade vulcao : tronarko_vulcanismo) {
                if (sismica.atInt("X") == vulcao.atInt("X") && sismica.atInt("Y") == vulcao.atInt("Y")) {
                    deve_renderizar = false;
                    sismica.at("TemVulcanismo", "NAO");
                    break;
                }
            }

            if (deve_renderizar) {
                render.drawCirculoCentralizado_Pintado(sismica.atInt("X"), sismica.atInt("Y"), 15, Cores.hexToCor("#FFCDD2"));
                render.drawCirculoCentralizado_Pintado(sismica.atInt("X"), sismica.atInt("Y"), 5, Cores.hexToCor("#1565C0"));

                ESCRITOR_NORMAL_BRANCO.escreva(sismica.atInt("X"), sismica.atInt("Y") + 20, "Terremoto : " + sismica.at("Escala"));
                ESCRITOR_NORMAL_BRANCO.escreva(sismica.atInt("X"), sismica.atInt("Y") + 60, "Superarko :: " +  StringTronarko.SUPERARKOS_DO_TRONARKO_PARA_TOZTE(sismica.atInt("Superarko"), tronarko));
            }

        }


        for (Entidade vulcao : tronarko_vulcanismo) {

            render.drawCirculoCentralizado_Pintado(vulcao.atInt("X"), vulcao.atInt("Y"), 15, Cores.hexToCor("#FFF9C4"));
            render.drawCirculoCentralizado_Pintado(vulcao.atInt("X"), vulcao.atInt("Y"), 5, Cores.hexToCor("#FF5722"));

            ESCRITOR_NORMAL_BRANCO.escreva(vulcao.atInt("X"), vulcao.atInt("Y") + 20, "Erupção : " + vulcao.at("Escala"));
            ESCRITOR_NORMAL_BRANCO.escreva(vulcao.atInt("X"), vulcao.atInt("Y") + 60, "Superarko :: " + StringTronarko. SUPERARKOS_DO_TRONARKO_PARA_TOZTE(vulcao.atInt("Superarko"), tronarko));


            int descer_py = vulcao.atInt("Y") + 100;

            for (Entidade e_atividade_sismica : tronarko_vulcanismo_sismica) {
                if (vulcao.atInt("X") == e_atividade_sismica.atInt("X") && vulcao.atInt("Y") == e_atividade_sismica.atInt("Y")) {

                    e_atividade_sismica.at("TemVulcanismo", "SIM");
                    vulcao.getEntidades().adicionar(e_atividade_sismica);

                    ESCRITOR_NORMAL_BRANCO.escreva(vulcao.atInt("X"), descer_py, "Terremoto = " + e_atividade_sismica.at("Escala") + " :: " + "Superarko :: " +  StringTronarko.SUPERARKOS_DO_TRONARKO_PARA_TOZTE(e_atividade_sismica.atInt("Superarko"), tronarko));

                    //  ESCRITOR_NORMAL_BRANCO.escreva(vulcao.atInt("X"), descer_py, "aaaaa");

                    descer_py += 40;
                }
            }


        }


        Lista<Entidade> fenomenos_tectonicos = new Lista<Entidade>();
        fenomenos_tectonicos.adicionar_varios(ENTT.COPIAR(tronarko_vulcanismo));
        fenomenos_tectonicos.adicionar_varios(ENTT.COPIAR(tronarko_vulcanismo_sismica));
        fenomenos_tectonicos.adicionar_varios(ENTT.COPIAR(tronarko_atividade_sismica));

        ENTT.ATRIBUTO_REMOVER(fenomenos_tectonicos,"Nivel");
        ENTT.ATRIBUTO_REMOVER(fenomenos_tectonicos,"AtividadeSismica");
        ENTT.ATRIBUTO_REMOVER(fenomenos_tectonicos,"TemVulcanismo");

        ENTT.ATRIBUTO_TODOS(tronarko_vulcanismo,"Fenomeno","VULCANISMO");
        ENTT.ATRIBUTO_TODOS(tronarko_vulcanismo_sismica,"Fenomeno","TERREMOTO");
        ENTT.ATRIBUTO_TODOS(tronarko_atividade_sismica,"Fenomeno","TERREMOTO");

        ENTT.EXIBIR_TABELA_COM_TITULO(fenomenos_tectonicos,"FENOMENOS TECTONICOS");

        ENTT.GUARDAR(fenomenos_tectonicos, ARQUIVO_FENOMENOS_TECTONICOS());

        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/atzum_fenomenos_tectonicos.png"));
        Imagem.exportar(render.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/fenomenos/atzum_fenomenos_tectonicos_tronarko_" + tronarko + ".png"));

        tronarko_vulcanismo.limpar();
        tronarko_vulcanismo_sismica.limpar();
        tronarko_atividade_sismica.limpar();


    }



}
