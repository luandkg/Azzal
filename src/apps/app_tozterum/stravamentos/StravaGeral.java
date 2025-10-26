package apps.app_tozterum.stravamentos;

import libs.arquivos.IM;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.ByteChunk;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.entt.ONTT;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.tempo.Calendario;
import servicos.ASSETS;

import java.awt.image.BufferedImage;

public class StravaGeral {

    public static void init() {

        PastaFS guardar_em = ASSETS.GET_PASTA("coisas\\strava");

        Lista<Entidade> luan = StravaQ6.PERFIL_GET_DADOS_RECENTE("118956021");
        ENTT.EXIBIR_TABELA(luan);


        Lista<Entidade> gg = StravaQ6.PERFIL_GET_DADOS_RECENTE("137144112");
        ENTT.EXIBIR_TABELA(gg);

        Lista<Entidade> alguem = StravaQ6.PERFIL_GET_DADOS_RECENTE("27312402");
        ENTT.EXIBIR_TABELA(alguem);


        ACOMPANHAR_EM_ARQUIVO("118956021", guardar_em.getArquivo("luan.entts"));
        ACOMPANHAR_EM_ARQUIVO("137144112", guardar_em.getArquivo("gg.entts"));
        ACOMPANHAR_EM_ARQUIVO("27312402", guardar_em.getArquivo("alguem.entts"));


        fmt.print("---------- LUAN");
        ENTT.EXIBIR_TABELA(ENTT.ABRIR(guardar_em.getArquivo("luan.entts")));
        fmt.print("---------- GG");
        ENTT.EXIBIR_TABELA(ENTT.ABRIR(guardar_em.getArquivo("gg.entts")));
        fmt.print("---------- ALGUEM");
        ENTT.EXIBIR_TABELA(ENTT.ABRIR(guardar_em.getArquivo("alguem.entts")));


        fmt.print("---------- LUAN DESAFIOS");
        Lista<Entidade> dados = ONTT.GET_ENTIDADES(guardar_em.getArquivo("luan_desafios.ds"), ".desafio");
        ENTT.EXIBIR_TABELA(dados);


        //  exportar_desafios();

        fmt.print("------------>> DS");
        ENTT.EXIBIR_TABELA(ONTT.VISUALIZAR(guardar_em.getArquivo("luan_desafios.ds")));

    }

    public static void exportar_desafios(PastaFS guardar_em, PastaFS desafios_img) {

        int d = 1;

        Lista<DSItem> itens = ONTT.FILTRAR(guardar_em.getArquivo("luan_desafios.ds"), ".im");

        for (DSItem item : itens) {

            Arquivador.CONSTRUIR_ARQUIVO(desafios_img.getArquivo("Desafio_" + d + "_v1.png"), item.getBytes());
            BufferedImage img = Imagem.getImagem(desafios_img.getArquivo("Desafio_" + d + "_v1.png"));
            IM.salvar(img, desafios_img.getArquivo("Desafio_" + d + "_v1.im"));

            BufferedImage tmp = IM.abrir(desafios_img.getArquivo("Desafio_" + d + "_v1.im"));
            Imagem.exportar(tmp, desafios_img.getArquivo("Desafio_" + d + "_v2.png"));

            d += 1;
        }
    }


    public static void ACOMPANHAR_EM_ARQUIVO(String perfil_atleta_id, String arquivo) {

        Lista<Entidade> recentes = StravaQ6.PERFIL_GET_DADOS_RECENTE(perfil_atleta_id);


        Lista<Entidade> dados = ENTT.ABRIR(arquivo);

        ENTT.EXIBIR_TABELA(dados);

        int maior_id = ENTT.GET_INTEIRO_MAIOR(dados, "ID");


        for (Entidade novo : recentes) {
            String novo_tipo = novo.at("Tipo");
            String novo_data = novo.at("Data");
            String novo_tempo = novo.at("Tempo");
            String novo_nome = novo.at("Nome");

            boolean existe = false;

            for (Entidade existente : dados) {

                String existente_tipo = existente.at("Tipo");
                String existente_data = existente.at("Data");
                String existente_tempo = existente.at("Tempo");
                String existente_nome = existente.at("Nome");

                if (existente_tipo.contentEquals(novo_tipo) && existente_data.contentEquals(novo_data) && existente_tempo.contentEquals(novo_tempo)) {

                    if (!novo_nome.contentEquals(existente_nome)) {
                        existente.at("Nome", novo_nome);
                        existente.at("Editado", "SIM");
                        existente.at("EditadoData", Calendario.getDataHoje().getTempoInverso());
                        existente.at("Edicoes", existente.atIntOuPadrao("Edicoes", 0) + 1);
                        existente.at("EditadoNomeAnteriormente",  existente.at("Nome"));
                    }


                    existe = true;
                    break;
                }

            }


            if (!existe) {
                maior_id += 1;
                novo.at("ID", maior_id);
                novo.at("Conquistado", Calendario.getTempoCompleto());
                dados.adicionar(novo);
            }

        }

        ENTT.GUARDAR(dados, arquivo);
    }

    public static void ACOMPANHAR_DESAFIOS_EM_ARQUIVO(String perfil_atleta_id, String arquivo_infos) {


        Lista<Entidade> recentes = StravaQ6.PERFIL_GET_DESAFIOS_RECENTE(perfil_atleta_id);
        ENTT.EXIBIR_TABELA(recentes);


        Lista<Entidade> dados = ONTT.GET_ENTIDADES(arquivo_infos, ".desafio");


        ENTT.EXIBIR_TABELA(dados);

        int maior_id = ENTT.GET_INTEIRO_MAIOR(dados, "ID");

        for (Entidade novo : recentes) {
            String novo_chave = novo.at("Chave");

            boolean existe = ENTT.EXISTE(dados, "Chave", novo_chave);

            if (!existe) {
                maior_id += 1;
                novo.at("ID", maior_id);

                ONTT.CRIAR_ENTIDADE(arquivo_infos, novo.at("Chave"), ".desafio", novo);

                Opcional<ByteChunk> imagem = Internet.DOWNLOAD(novo.at("Imagem"));
                if (imagem.isOK()) {
                    fmt.print("Imagem Bytes ->> {}", imagem.get().getLength());

                    //  PastaFS guardar_em = new PastaFS( "/home/luan/assets");
                    //String temporario = guardar_em.getArquivo("strava.cache");

                    // Arquivador.remover(temporario);
                    // Arquivador.CONSTRUIR_ARQUIVO(temporario,imagem.get().getChunk());

                    BufferedImage img = Imagem.GET_IMAGEM(imagem.get().getChunk());
                    // Arquivador.remover(temporario);

                    Lista<Byte> im_bytes = IM.salvar_to_bytes(img);

                    //   byte[] bytes= Arquivador.GET_BYTES(temporario);
                    //   Arquivador.remover(temporario);

                    ONTT.CRIAR_DOCUMENTO(arquivo_infos, novo.at("Chave"), ".im", im_bytes);

                }

            }


        }


    }

}
