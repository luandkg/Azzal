package apps.app;

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
import libs.tempo.Data;

import java.awt.image.BufferedImage;

public class AppStrava {


    public static void init() {


        PastaFS guardar_em = new PastaFS("/home/luan/assets");
        PastaFS desafios_img = new PastaFS("/home/luan/assets/desafios");


         // Lista<Entidade> lista= ENTT.ABRIR(guardar_em.getArquivo("/gg.entts"));

        // ENTT.GUARDAR(lista,guardar_em.getArquivo("/gg.entts"));


        Lista<Entidade> luan = Strava.PERFIL_GET_DADOS_RECENTE("118956021");
        ENTT.EXIBIR_TABELA(luan);


        Lista<Entidade> gg = Strava.PERFIL_GET_DADOS_RECENTE("137144112");
        ENTT.EXIBIR_TABELA(gg);

        Lista<Entidade> alguem = Strava.PERFIL_GET_DADOS_RECENTE("27312402");
        ENTT.EXIBIR_TABELA(alguem);


        ACOMPANHAR("118956021", guardar_em.getArquivo("luan.entts"));
        ACOMPANHAR("137144112", guardar_em.getArquivo("gg.entts"));
        ACOMPANHAR("27312402", guardar_em.getArquivo("alguem.entts"));


        ACOMPANHAR_DESAFIOS("118956021", guardar_em.getArquivo("luan_desafios.ds"));
        ACOMPANHAR_DESAFIOS("137144112", guardar_em.getArquivo("gg_desafios.ds"));
        ACOMPANHAR_DESAFIOS("27312402", guardar_em.getArquivo("alguem_desafios.ds"));


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


    public static void ACOMPANHAR(String perfil_atleta_id, String arquivo) {

        Lista<Entidade> recentes = Strava.PERFIL_GET_DADOS_RECENTE(perfil_atleta_id);


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

    public static void ACOMPANHAR_DESAFIOS(String perfil_atleta_id, String arquivo_infos) {


        Lista<Entidade> recentes = Strava.PERFIL_GET_DESAFIOS_RECENTE(perfil_atleta_id);
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


    public static void transferencia(String de,String para){

        PastaFS guardar_em = new PastaFS("/home/luan/assets/mudanca");

       Lista<Entidade> entt_de = ENTT.ABRIR(guardar_em.getArquivo(de));
        Lista<Entidade> entt_para = ENTT.ABRIR(guardar_em.getArquivo(para));


        for(Entidade e : entt_de){
            if(e.at("Tipo").contentEquals("")) {
                e.at("Tipo", "RUN");
            }else  if(e.at("Tipo").contentEquals("CORRIDA")){
                e.at("Tipo","RUN");
            }

            String s_data  = Data.toData(e.at("Data")).getTempoInverso();
            e.at("Data",s_data);

            if(e.at("Distancia").isEmpty()){
                e.at("Tipo","EXCLUIR");
            }

            if(ENTT.COLETAR(entt_para,"Data",s_data).getQuantidade()>0){
                e.at("Tipo","EXCLUIR");
            }
        }

        Lista<Entidade> de_validos = ENTT.COLETAR_DIFERENTE_DE(entt_de,"Tipo","EXCLUIR");


        fmt.print("------------- DE :");
        ENTT.EXIBIR_TABELA(entt_de);

        fmt.print("------------- PARA :");
        ENTT.EXIBIR_TABELA(entt_para);

        Unico<String> datas = new Unico<String>(Strings.IGUALAVEL());

        for(Entidade e : entt_de){
            if(!e.at("Tipo").contentEquals("EXCLUIR")){
                datas.item(e.at("Data"));
            }
        }

        for(String data : datas){
            fmt.print("Data : {} ->> {}",data,ENTT.COLETAR(de_validos,"Data",data).getQuantidade());

            for(Entidade valida : ENTT.COLETAR(de_validos,"Data",data)){

                Entidade novo = new Entidade();
                novo.at("Tipo",valida.at("Tipo"));
                novo.at("Data",valida.at("Data"));
                novo.at("Nome",valida.at("Nome"));
                novo.at("Tempo",valida.at("Corrida"));
                novo.at("Altitude",valida.at("Elevacao"));
                novo.at("Distancia",valida.at("Distancia"));
                novo.at("Conquistado",valida.at("DDC"));

                novo.at("Info1",novo.at("Tipo"));
                novo.at("Info2",novo.at("Distancia"));
                novo.at("Info4",novo.at("Altitude"));
                novo.at("Info5",novo.at("Tempo"));

                if(!valida.at("DDA").isEmpty()){
                    novo.at("Editado","SIM");
                    novo.at("EditadoData",valida.at("DDA"));
                    novo.at("Edicoes","1");
                }

                entt_para.adicionar(novo);
            }

        }

        ENTT.ORDENAR_TEXTO(entt_para,"Data");

        fmt.print("------------- PARA :");
        ENTT.EXIBIR_TABELA(entt_para);

        ENTT.GUARDAR(entt_para,guardar_em.getArquivo("gg_novo.entts"));

    }

    public static void transferencia_gg(String de,String para){

        PastaFS guardar_em = new PastaFS("/home/luan/assets/mudanca");

        Lista<Entidade> entt_de = ENTT.ABRIR(guardar_em.getArquivo(de));
        Lista<Entidade> entt_para = ENTT.ABRIR(guardar_em.getArquivo(para));


        for(Entidade e : entt_de){
            if(e.at("Tipo").contentEquals("")) {
                e.at("Tipo", "RUN");
            }else  if(e.at("Tipo").contentEquals("CORRIDA")){
                e.at("Tipo","RUN");
            }

            String s_data  = Data.toData(e.at("Data")).getTempoInverso();
            e.at("Data",s_data);

            if(e.at("Tempo").isEmpty()){
                e.at("Tipo","EXCLUIR");
            }

            if(ENTT.COLETAR(entt_para,"Data",s_data).getQuantidade()>0){
              //  e.at("Tipo","EXCLUIR");
            }
        }

        Lista<Entidade> de_validos = ENTT.COLETAR_DIFERENTE_DE(entt_de,"Tipo","EXCLUIR");


        fmt.print("------------- DE :");
        ENTT.EXIBIR_TABELA(entt_de);

        fmt.print("------------- PARA :");
        ENTT.EXIBIR_TABELA(entt_para);

        Unico<String> datas = new Unico<String>(Strings.IGUALAVEL());

        for(Entidade e : entt_de){
            if(!e.at("Tipo").contentEquals("EXCLUIR")){
                datas.item(e.at("Data"));
            }
        }

        for(String data : datas){
            fmt.print("Data : {} ->> {}",data,ENTT.COLETAR(de_validos,"Data",data).getQuantidade());

            for(Entidade valida : ENTT.COLETAR(de_validos,"Data",data)){

                Entidade novo = new Entidade();
                novo.at("Tipo",valida.at("Tipo"));
                novo.at("Data",valida.at("Data"));
                novo.at("Nome",valida.at("Nome"));
                novo.at("Tempo",valida.at("Tempo"));
                novo.at("Altitude",valida.at("Elevacao"));
                novo.at("Distancia",valida.at("Distancia"));
                novo.at("Conquistado",valida.at("DDC"));

                novo.at("Info1",novo.at("Tipo"));
                novo.at("Info2",novo.at("Distancia"));
                novo.at("Info4",novo.at("Altitude"));
                novo.at("Info5",novo.at("Tempo"));

                if(!valida.at("DDA").isEmpty()){
                    novo.at("Editado","SIM");
                    novo.at("EditadoData",valida.at("DDA"));
                    novo.at("Edicoes","1");
                }

                entt_para.adicionar(novo);
            }

        }

        ENTT.ORDENAR_TEXTO(entt_para,"Data");

        ENTT.SEQUENCIAR(entt_para,"ID",0);

        fmt.print("------------- PARA :");
        ENTT.EXIBIR_TABELA(entt_para);

        ENTT.GUARDAR(entt_para,guardar_em.getArquivo("gg_novo.entts"));

    }

}
