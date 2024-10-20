package apps.app_azka;

import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.ByteChunk;
import libs.arquivos.binario.Inteiro;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.tronarko.Tronarko;
import libs.utils.DS_ENTT;

import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;


public class JujutsuKaizenDownloder {

    public static void DOWNLOAD() {

        String PASTA_DOWNLOAD = "/home/luan/Imagens/jujutsu_kaizen/";
        String jujutsu = "https://mangaonline.biz/capitulo/jujutsu-kaisen-capitulo-1/";

        Opcional<String> cap0 = Internet.GET_PAGINA_HTML_TIMEOUT(jujutsu);

        if (cap0.isOK()) {

            String manga_volume_nome = Strings.GET_REVERSO_ATE(jujutsu, "-").replace("/", "");

            Lista<String> linhas = Strings.DIVIDIR_LINHAS(cap0.get());

            Lista<Entidade> manga = ENTT.CRIAR_LISTA();

            boolean validado = false;

            String VALIDAR_COMECA = "lazy";
            String VALIDAR_TERMINA = "comments";
            String VALIDO_LINK = "https://mangaonline.biz/wp-content/uploads/";


            for (String linha : linhas) {

                for (String item : Strings.GET_ENTRE_ASPAS_VARIOS(linha)) {

                    validado = Strings.isTernario(validado, VALIDAR_COMECA, VALIDAR_TERMINA, item);

                    if (validado) {
                        if (item.startsWith(VALIDO_LINK)) {
                            Entidade e_item = new Entidade();
                            e_item.at("ID", ENTT.CONTAGEM(manga) + 1);
                            e_item.at("Link", item);
                            e_item.at("Volume", manga_volume_nome);
                            e_item.at("Pagina", ENTT.CONTAGEM(manga) + 1);
                            manga.adicionar(e_item);
                        }
                    }
                }

            }

            ENTT.EXIBIR_TABELA_COM_NOME(manga, "MANGÁ");

            String manga_volume_arquivo = PASTA_DOWNLOAD + manga_volume_nome + ".hq";

            for (Entidade e : manga) {

                e.at("Tempo", Tronarko.getTronAgora().getTextoZerado());
                e.at("Status", "FALHOU");

                Opcional<ByteChunk> dados = Internet.DOWNLOAD(e.at("Link"));
                if (dados.isOK()) {

                    BufferedImage imagem = Imagem.GET_IMAGEM(dados.get().getChunk());

                    DS.adicionar(manga_volume_arquivo, e.at("Pagina") + ".im", Imagem.IMAGEM_TO_BYTES(imagem));

                    // Arquivador.GUARDAR_BYTES(PASTA_DOWNLOAD + e.at("ID") + ".png", dados.get()  );
                    e.at("Status", "OK");
                }
            }

            ENTT.EXIBIR_TABELA_COM_NOME(manga, "MANGÁ");

            DS.adicionar(manga_volume_arquivo, "download.tx", ENTT.TO_DOCUMENTO(manga));

        }

    }


    public static void VER_INTERNO() {

        String PASTA_DOWNLOAD = "/home/luan/Imagens/jujutsu_kaizen/";

        String ARQUIVO = "1.hq";

        Lista<Entidade> manga = ENTT.CRIAR_LISTA();

        for (DSItem item : DS.ler_todos(PASTA_DOWNLOAD + ARQUIVO)) {

            Entidade e_pagina = new Entidade();
            e_pagina.at("Nome", item.getNome());
            e_pagina.at("Tamanho", fmt.formatar_tamanho_precisao_dupla(item.getTamanho()));
            manga.adicionar(e_pagina);
        }

        ENTT.EXIBIR_TABELA_COM_NOME(manga, "MANGÁ");

    }


    public static void BAIXAR_CAPITULOS() {

        String jujutsu = "https://mangaonline.biz/manga/jujutsu-kaisen/";

        Opcional<String> cap0 = Internet.GET_PAGINA_HTML_TIMEOUT(jujutsu);

        if (cap0.isOK()) {

            String manga_volume_nome = Strings.GET_REVERSO_ATE(jujutsu, "-").replace("/", "");

            Lista<String> linhas = Strings.DIVIDIR_LINHAS(cap0.get());

            Lista<Entidade> manga = ENTT.CRIAR_LISTA();

            for (String linha : linhas) {
                Lista<String> itens = Strings.DIVIDIR_POR(linha, "<");
                for (Indexado<String> item : Indexamento.indexe(itens)) {

                    if (item.get().contains("episodiotitle")) {
                        Entidade capitulo = ENTT.CRIAR_EM(manga);
                        capitulo.at("ID", Strings.GET_DEPOIS(Strings.GET_REVERSO_ATE(itens.get(item.index() + 1), ">"), " ").trim());
                        capitulo.at("Nome", Strings.GET_REVERSO_ATE(itens.get(item.index() + 1), ">"));
                        capitulo.at("Data", Strings.GET_REVERSO_ATE(itens.get(item.index() + 2), ">"));
                        capitulo.at("Link", Strings.GET_ENTRE_ASPAS(itens.get(item.index() + 1), 1));
                    }
                }
            }


            ENTT.ORDENAR_DOUBLE(manga, "ID");

            ENTT.EXIBIR_TABELA_COM_NOME(manga, "JUJUTSU KAIZEN - CAPITULOS");


            int estou = 1;
            boolean baixar_so = true;

            for (Indexado<Entidade> capitulo : Indexamento.indexe(manga)) {

                String PASTA_DOWNLOAD = "/home/luan/Imagens/jujutsu_kaizen/";

                if(baixar_so){
                    if (capitulo.index() == estou) {
                        DOWNLOAD_CAPITULO(capitulo.get().at("Link"), PASTA_DOWNLOAD);
                    }
                }else{
                    if (capitulo.index() >= estou) {
                        DOWNLOAD_CAPITULO(capitulo.get().at("Link"), PASTA_DOWNLOAD);
                    }

                }

                capitulo.get().at("Status", "Downloaded");

                ENTT.EXIBIR_TABELA_COM_NOME(ENTT.SLICE(manga, capitulo.index() - 5, capitulo.index() + 5), "JUJUTSU KAIZEN - CAPITULOS");
            }

        }
    }

    public static void DOWNLOAD_CAPITULO(String link, String PASTA_DOWNLOAD) {


        Opcional<String> cap0 = Internet.GET_PAGINA_HTML_TIMEOUT(link);

        if (cap0.isOK()) {

            Lista<Entidade> infos = ENTT.CRIAR_LISTA();

            String manga_volume_sequencial = Strings.GET_REVERSO_ATE(link, "-").replace("/", "");
            String manga_titulo = Strings.GET_REVERSO_ATE( link.substring(0,link.length()-1),"/");

            manga_titulo=manga_titulo.replace("-"," ");
            manga_titulo=Strings.GET_REVERSO_DEPOIS_DE(manga_titulo," ");
            manga_titulo=Strings.GET_REVERSO_DEPOIS_DE(manga_titulo," ");
            manga_titulo = Strings.CAPTALIZAR_FRASE(manga_titulo);

            ENTT.CRIAR_EM(infos, "Nome", "Nome").at("Valor", manga_titulo);
            ENTT.CRIAR_EM(infos, "Nome", "Tipo").at("Valor", "Manga");
            ENTT.CRIAR_EM(infos, "Nome", "Capitulo").at("Valor", manga_volume_sequencial);
            ENTT.CRIAR_EM(infos, "Nome", "DownloadURL").at("Valor",link);

            Lista<String> linhas = Strings.DIVIDIR_LINHAS(cap0.get());

            Lista<Entidade> manga = ENTT.CRIAR_LISTA();

            boolean validado = false;

            String VALIDAR_COMECA = "lazy";
            String VALIDAR_TERMINA = "comments";
            String VALIDO_LINK = "https://mangaonline.biz/wp-content/uploads/";


            for (String linha : linhas) {

                for (String item : Strings.GET_ENTRE_ASPAS_VARIOS(linha)) {

                    validado = Strings.isTernario(validado, VALIDAR_COMECA, VALIDAR_TERMINA, item);

                    if (validado) {
                        if (item.startsWith(VALIDO_LINK)) {
                            Entidade e_item = new Entidade();
                            e_item.at("ID", ENTT.CONTAGEM(manga) + 1);
                            e_item.at("Link", item);
                            e_item.at("Volume", manga_volume_sequencial);
                            e_item.at("Pagina", ENTT.CONTAGEM(manga) + 1);
                            manga.adicionar(e_item);
                        }
                    }
                }

            }

            ENTT.EXIBIR_TABELA_COM_NOME(manga, "MANGÁ");

            String manga_volume_arquivo = PASTA_DOWNLOAD + manga_volume_sequencial + ".hq";
            DS.limpar(manga_volume_arquivo);
            DS.adicionar_pre_alocado(manga_volume_arquivo, "info.entts", 500 * 1024);

            String item_capa = "";
            boolean tem_capa = false;

            ENTT.CRIAR_EM(infos, "Nome", "DownloadInicio").at("Valor", Tronarko.getTronAgora().getTextoZerado());

            for (Indexado<Entidade> pagina : Indexamento.indexe(manga)) {

                pagina.get().at("Tempo", Tronarko.getTronAgora().getTextoZerado());
                pagina.get().at("Status", "FALHOU");

                Opcional<ByteChunk> dados = Internet.DOWNLOAD(pagina.get().at("Link"));
                if (dados.isOK()) {

                    BufferedImage imagem = Imagem.GET_IMAGEM(dados.get().getChunk());

                    if (!tem_capa) {
                        tem_capa = true;
                        item_capa = pagina.get().at("Pagina") + ".im";
                    }

                    DS.adicionar(manga_volume_arquivo, pagina.get().at("Pagina") + ".im", Imagem.IMAGEM_TO_BYTES(imagem));
                    // AS.adicionar(manga_volume_arquivo, e.at("Pagina") + ".im", IM.salvar_to_bytes(imagem));

                    // Arquivador.GUARDAR_BYTES(PASTA_DOWNLOAD + e.at("ID") + ".png", dados.get()  );
                    pagina.get().at("Status", "OK");
                }

                ENTT.EXIBIR_TABELA_COM_NOME(ENTT.SLICE(manga, pagina.index() - 5, pagina.index() + 5), "JUJUTSU KAIZEN - PAGINAS");

            }


            Opcional<DSItem> op_infos = DS.buscar_item(manga_volume_arquivo, "info.entts");
            fmt.print("INFO DADOS");
            if (op_infos.isOK()) {


                if (tem_capa) {
                    ENTT.CRIAR_EM(infos, "Nome", "Capa").at("Valor", item_capa);
                }

                ENTT.CRIAR_EM(infos, "Nome", "Paginas").at("Valor", ENTT.CONTAGEM(manga));
                ENTT.CRIAR_EM(infos, "Nome", "Lendo").at("Valor", "Nao");
                ENTT.CRIAR_EM(infos, "Nome", "DownloadFim").at("Valor", Tronarko.getTronAgora().getTextoZerado());


                fmt.print("INFO DADOS - ATUALIZAR ");
                DS.alterar_pre_alocado(manga_volume_arquivo, "info.entts", ENTT.TO_DOCUMENTO(infos));

            }


            ENTT.EXIBIR_TABELA_COM_NOME(manga, "MANGÁ");

            DS.adicionar(manga_volume_arquivo, "download.entts", ENTT.TO_DOCUMENTO(manga));

        }

    }


    public static void VER_UM() {

        String PASTA_DOWNLOAD = "/home/luan/Imagens/jujutsu_kaizen/";


        String arquivo = FS.GET_ARQUIVO(PASTA_DOWNLOAD, "1.hq");


        DS_ENTT.DUMP(arquivo);

        Opcional<DSItem> op_info = DS.buscar_item(arquivo, "info.entts");

        if (op_info.isOK()) {

            String dados = DS.obter_pre_alocado_texto(arquivo, "info.entts");

            ENTT.EXIBIR_TABELA(ENTT.PARSER(dados));

            // DS.alterar_pre_alocado(arquivo_novo,"info.entts");

        }

    }


}
