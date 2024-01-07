package apps.app;

import libs.arquivos.Zipper;
import libs.fs.PastaFS;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.Texto;
import libs.luan.fmt;
import libs.xlsx.Documento;


public class Projettum {


    public static void init_geral() {

        // Projettum.init_entidades();
        fmt.print("----------------------------------------");
        Projettum.init();
        Projettum.init_biblioteca("xlsx");

    }


    public static void init() {


        String arquivo_criado_longe_de_casa = "/home/luan/assets/AA_PROJETO_SUPLAV.zip";
        String pasta_local = "/home/luan/dev/Azzal";

        Lista<Documento> documentos = new Lista<Documento>();

        for (Documento documento : Zipper.EXTRAIR_DOCUMENTOS_COM_EXTENSAO(arquivo_criado_longe_de_casa, ".java")) {
            // fmt.print("{} - {}", documento.getNome(), fmt.formatar_tamanho(documento.getConteudo().length()));
            if (documento.getNome().startsWith("AA_PROJETO_SUPLAV/src/libs/")) {
                documentos.adicionar(new Documento(documento.getNome().replace("AA_PROJETO_SUPLAV/src/libs/", ""), documento.getConteudo()));
            }
        }

        Lista<String> arquivos_da_biblioteca = new Lista<String>();

        for (String arquivo : new PastaFS(pasta_local).getArquivosRecursivamente()) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/") && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca.adicionar(arquivo);
            }
        }

        Strings.ORDENAR(arquivos_da_biblioteca);

        for (String arquivo : arquivos_da_biblioteca) {
            String arquivo_local = arquivo.replace("/home/luan/dev/Azzal/src/libs/", "");
            //  fmt.print("{}", arquivo_local);
        }

        Lista<String> arquivos_da_biblioteca_luan = new Lista<String>();
        for (String arquivo : arquivos_da_biblioteca) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/luan/") && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca_luan.adicionar(arquivo);
            }
        }


        exibir_diferencas(arquivos_da_biblioteca, documentos);

    }


    public static void exibir_diferencas(Lista<String> arquivos_local, Lista<Documento> documentos) {

        int diferencas_total = 0;


        for (String arquivo : arquivos_local) {
            String arquivo_local = arquivo.replace("/home/luan/dev/Azzal/src/libs/", "");


            boolean enc = false;
            String enc_arquivo = "";
            String enc_conteudo = "";

            for (Documento documento : documentos) {
                if (documento.getNome().contains(arquivo_local)) {
                    enc_arquivo = documento.getNome();
                    enc_conteudo = documento.getConteudo();

                    enc = true;
                    break;
                }
            }

            if (enc) {

                boolean tem_alteracoes = false;

                // fmt.print("ALTERAÇÕES EM : {}", arquivo_local);
                // fmt.print("\t ++ {}", enc_arquivo);


                //    fmt.print("------ FUNCOES REMOTO");

                Lista<String> funcoes_remoto = new Lista<String>();
                Lista<String> funcoes_local = new Lista<String>();

                Lista<String> funcoes_nova_remoto_local = new Lista<String>();


                for (String linha : Strings.dividir_linhas(enc_conteudo)) {
                    String linha_normalizada = linha.trim();
                    if (linha_normalizada.endsWith(" {")) {
                        linha_normalizada = linha_normalizada.replace(" {", "");
                    }
                    if (linha_normalizada.endsWith("{")) {
                        linha_normalizada = linha_normalizada.replace("{", "");
                    }
                    linha_normalizada = linha_normalizada.toLowerCase();
                    if (linha_normalizada.startsWith("public static") || linha_normalizada.startsWith("public class") || linha_normalizada.startsWith("public")) {
                        //    fmt.print("\t -- " + linha_normalizada);
                        funcoes_remoto.adicionar(linha_normalizada);
                    }
                }

                //  fmt.print("------ FUNCOES LOCAL");

                String local_conteudo = Texto.arquivo_ler(arquivo);

                for (String linha : Strings.dividir_linhas(local_conteudo)) {
                    String linha_normalizada = linha.trim();
                    if (linha_normalizada.endsWith(" {")) {
                        linha_normalizada = linha_normalizada.replace(" {", "");
                    }
                    if (linha_normalizada.endsWith("{")) {
                        linha_normalizada = linha_normalizada.replace("{", "");
                    }
                    linha_normalizada = linha_normalizada.toLowerCase();
                    if (linha_normalizada.startsWith("public static") || linha_normalizada.startsWith("public class") || linha_normalizada.startsWith("public")) {
                        //   fmt.print("\t -- " + linha_normalizada);
                        funcoes_local.adicionar(linha_normalizada);
                    }
                }

                for (String funcao_remota : funcoes_remoto) {
                    boolean existe = false;
                    for (String funcao_local : funcoes_local) {
                        if (funcao_remota.contentEquals(funcao_local)) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        funcoes_nova_remoto_local.adicionar(funcao_remota);
                    }

                }

                if (funcoes_nova_remoto_local.getQuantidade() > 0) {

                    fmt.print("ALTERAÇÕES EM : {}", arquivo_local + " ->> +" + funcoes_nova_remoto_local.getQuantidade());
                    // fmt.print("\t ++ {}", enc_arquivo);

                    //  fmt.print("------ FUNCOES NOVAS");
                    for (String funcao_nova : funcoes_nova_remoto_local) {
                        fmt.print("\t ++ " + funcao_nova);
                    }

                    diferencas_total += funcoes_nova_remoto_local.getQuantidade();

                }


            } else {
                //fmt.print("NOVO ARQUIVO  : {}", arquivo_local);
            }

        }

        for (Documento documento : documentos) {

            boolean enc = false;

            for (String arquivo : arquivos_local) {
                String arquivo_local = arquivo.replace("/home/luan/dev/Azzal/src/libs/", "");
                if (arquivo_local.contentEquals(documento.getNome())) {
                    enc = true;
                    break;
                }
            }

            if (!enc) {
                fmt.print("NOVO ARQUIVO  : {}", documento.getNome());
            }

        }


        fmt.print();
        fmt.print("NOVIDADES : +{}", diferencas_total);


    }

    public static void init_entidades() {


        String arquivo_criado_longe_de_casa = "/home/luan/assets/AA_PROJETO_SUPLAV.zip";
        String pasta_local = "/home/luan/dev/Azzal";

        Lista<Documento> documentos = new Lista<Documento>();

        for (Documento documento : Zipper.EXTRAIR_DOCUMENTOS_COM_EXTENSAO(arquivo_criado_longe_de_casa, ".java")) {
            // fmt.print("{} - {}", documento.getNome(), fmt.formatar_tamanho(documento.getConteudo().length()));
            if (documento.getNome().startsWith("AA_PROJETO_SUPLAV/src/libs/entt")) {
                documentos.adicionar(new Documento(documento.getNome().replace("AA_PROJETO_SUPLAV/src/libs/", ""), documento.getConteudo()));
            }
        }

        Lista<String> arquivos_da_biblioteca = new Lista<String>();

        for (String arquivo : new PastaFS(pasta_local).getArquivosRecursivamente()) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/entt") && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca.adicionar(arquivo);
            }
        }

        Strings.ORDENAR(arquivos_da_biblioteca);

        for (String arquivo : arquivos_da_biblioteca) {
            String arquivo_local = arquivo.replace("/home/luan/dev/Azzal/src/libs/", "");
            //  fmt.print("{}", arquivo_local);
        }

        Lista<String> arquivos_da_biblioteca_luan = new Lista<String>();
        for (String arquivo : arquivos_da_biblioteca) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/luan/") && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca_luan.adicionar(arquivo);
            }
        }


        exibir_diferencas(arquivos_da_biblioteca, documentos);

    }

    public static void init_luan() {


        String arquivo_criado_longe_de_casa = "/home/luan/assets/AA_PROJETO_SUPLAV.zip";
        String pasta_local = "/home/luan/dev/Azzal";

        Lista<Documento> documentos = new Lista<Documento>();

        for (Documento documento : Zipper.EXTRAIR_DOCUMENTOS_COM_EXTENSAO(arquivo_criado_longe_de_casa, ".java")) {
            // fmt.print("{} - {}", documento.getNome(), fmt.formatar_tamanho(documento.getConteudo().length()));
            if (documento.getNome().startsWith("AA_PROJETO_SUPLAV/src/libs/luan")) {
                documentos.adicionar(new Documento(documento.getNome().replace("AA_PROJETO_SUPLAV/src/libs/", ""), documento.getConteudo()));
            }
        }

        Lista<String> arquivos_da_biblioteca = new Lista<String>();

        for (String arquivo : new PastaFS(pasta_local).getArquivosRecursivamente()) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/luan") && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca.adicionar(arquivo);
            }
        }

        Strings.ORDENAR(arquivos_da_biblioteca);

        for (String arquivo : arquivos_da_biblioteca) {
            String arquivo_local = arquivo.replace("/home/luan/dev/Azzal/src/libs/", "");
            //  fmt.print("{}", arquivo_local);
        }

        Lista<String> arquivos_da_biblioteca_luan = new Lista<String>();
        for (String arquivo : arquivos_da_biblioteca) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/luan/") && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca_luan.adicionar(arquivo);
            }
        }


        exibir_diferencas(arquivos_da_biblioteca, documentos);

    }

    public static void init_biblioteca(String biblioteca) {


        fmt.print("--------------------------------------");
        fmt.print("BIBLIOTECA : {}", biblioteca);
        fmt.print();


        String arquivo_criado_longe_de_casa = "/home/luan/assets/AA_PROJETO_SUPLAV.zip";
        String pasta_local = "/home/luan/dev/Azzal";

        Lista<Documento> documentos = new Lista<Documento>();

        for (Documento documento : Zipper.EXTRAIR_DOCUMENTOS_COM_EXTENSAO(arquivo_criado_longe_de_casa, ".java")) {
            // fmt.print("{} - {}", documento.getNome(), fmt.formatar_tamanho(documento.getConteudo().length()));
            if (documento.getNome().startsWith("AA_PROJETO_SUPLAV/src/libs/" + biblioteca)) {
                documentos.adicionar(new Documento(documento.getNome().replace("AA_PROJETO_SUPLAV/src/libs/", ""), documento.getConteudo()));
            }
        }

        Lista<String> arquivos_da_biblioteca = new Lista<String>();

        for (String arquivo : new PastaFS(pasta_local).getArquivosRecursivamente()) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/" + biblioteca) && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca.adicionar(arquivo);
            }
        }

        Strings.ORDENAR(arquivos_da_biblioteca);

        for (String arquivo : arquivos_da_biblioteca) {
            String arquivo_local = arquivo.replace("/home/luan/dev/Azzal/src/libs/", "");
            //  fmt.print("{}", arquivo_local);
        }

        Lista<String> arquivos_da_biblioteca_luan = new Lista<String>();
        for (String arquivo : arquivos_da_biblioteca) {
            if (arquivo.startsWith("/home/luan/dev/Azzal/src/libs/" + biblioteca + "/") && arquivo.endsWith(".java")) {
                arquivos_da_biblioteca_luan.adicionar(arquivo);
            }
        }


        exibir_diferencas(arquivos_da_biblioteca, documentos);

    }

}
