package libs.verkuz;

import libs.luan.*;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

public class VerkuzImplementador {

    // IMPLEMENTADO : Luan Freitas

    // FEATURE 2022.10 - Sistema de organização de implementações
    private Lista<Implementacao> implementacoes;
    private Lista<VerkuzBiblioteca> bibliotecas;

    public VerkuzImplementador() {
        implementacoes = new Lista<Implementacao>();
        bibliotecas = new Lista<VerkuzBiblioteca>();
    }

    public void init(String pasta) {

        File arq = new File(pasta);

        organizar_implementacoes(pasta, implementacoes, bibliotecas);

        for (Implementacao i : implementacoes) {
            i.setArquivo(i.getArquivo().replace(pasta, arq.getName()));
        }

    }

    public void init_bibliotecas(String pasta) {

        organizar_implementacoes(pasta, new Lista<>(), bibliotecas);

        File arq = new File(pasta);

        for (VerkuzBiblioteca i : bibliotecas) {
            i.setArquivo(i.getArquivo().replace(pasta, arq.getName()));
        }

    }

    public void exibir() {

        for (Implementacao i : implementacoes) {
            System.out.println(i.getArquivo() + " :: " + i.getData() + " -->> " + i.getFuncao());
        }

        System.out.println(" ----------------- BIBLIOTECAS ------------------ ");

        Lista<VerkuzBiblioteca> grupos_bibliotecas = new Lista<VerkuzBiblioteca>();

        for (VerkuzBiblioteca i : bibliotecas) {
            String nome = duplo(i.getArquivo());
            //System.out.println("bib :: " + nome);
            if (!nome.contentEquals("/home")) {

                boolean existe = false;

                for (VerkuzBiblioteca vb : grupos_bibliotecas) {
                    if (vb.getArquivo().contentEquals(nome)) {
                        existe = true;
                        i.setTamanho(i.getTamanho() + vb.getTamanho());
                        break;
                    }
                }
                if (!existe) {
                    grupos_bibliotecas.adicionar(new VerkuzBiblioteca(nome, i.getTamanho()));
                }

            }

        }



        for (VerkuzBiblioteca i : ordenar_bibliotecas(grupos_bibliotecas)) {
            System.out.println(i.getArquivo() + " :: " + i.getTamanho());
        }


    }


    public static Lista<VerkuzBiblioteca> ordenar_bibliotecas(Lista<VerkuzBiblioteca> eGrupos) {

        Lista.ORDENAR_CRESCENTE(eGrupos, new Ordenavel<VerkuzBiblioteca>() {
            @Override
            public int emOrdem(VerkuzBiblioteca a, VerkuzBiblioteca b) {
                return Strings.STRING_COMPARAR(a.getArquivo(),b.getArquivo());
            }
        });

        return eGrupos;
    }


    public String duplo(String texto) {


        String ret = "";

        int i = 0;
        int o = texto.length();

        int barras = 0;

        while (i < o) {
            String l = String.valueOf(texto.charAt(i));
            if (l.contentEquals("/")) {
                barras += 1;
                if (barras >= 2) {
                    break;
                }
                ret += l;
            } else {
                ret += l;
            }
            i += 1;
        }

        return ret;
    }

    public void organizar_implementacoes(String pasta, Lista<Implementacao> implementacoes, Lista<VerkuzBiblioteca> ls_bibliotecas) {

        for (File item : new File(pasta).listFiles()) {

            if (item.isFile()) {
                String extensao = organizarExtensao(item.getName());
                if (extensao.contentEquals("java")) {

                    organizar_arquivo_java(item.getAbsolutePath(), implementacoes);

                    //if (implementacoes.size() > antes) {
                    // System.out.println("------------------- EX :: " + item.getName() + " :: " + extensao.toUpperCase());
                    // }
                    long tam = item.length();

                    ls_bibliotecas.adicionar(new VerkuzBiblioteca(item.getAbsolutePath(), tam));

                }
            } else if (item.isDirectory()) {
                organizar_implementacoes(item.getAbsolutePath(), implementacoes, ls_bibliotecas);
            }

        }

    }


    public String organizarExtensao(String arquivo) {

        String extensao = "";

        int i = arquivo.length() - 1;


        while (i > 0) {
            String l = String.valueOf(arquivo.charAt(i));
            if (l.contentEquals(".")) {
                break;
            } else {
                extensao = l + extensao;
            }
            i -= 1;
        }

        return extensao;

    }


    public void organizar_arquivo_java(String arquivo, Lista<Implementacao> implementacoes) {

        String conteudo = Texto.arquivo_ler(arquivo);

        Lista<String> linhas = Strings.DIVIDIR_LINHAS(conteudo);

        for (String linha : linhas) {

            linha = Strings.retirar_espaco_do_comeco(linha);

            if (linha.startsWith("//")) {

                while (linha.contains("  ")) {
                    linha = linha.replace("  ", " ");
                }

                linha = linha.replace("// ", "");
                linha = linha.replace("//", "");

                if (linha.contains("FEATURE")) {
                    implementacoes.adicionar(new Implementacao(arquivo, linha, ""));
                }
                //  System.out.println(linha);
            }

        }


    }


    public void toClasse(String arquivo, String pacote, String classe) {

        TextoDocumento documento = new TextoDocumento();

        documento.adicionarLinha("package " + pacote + ";");
        documento.adicionarLinha();


        documento.adicionarLinha("import java.util.ArrayList;");
        documento.adicionarLinha();

        documento.adicionarLinha("public class " + classe + "{");
        documento.adicionarLinha();

        int impl = 0;

        int maior = 0;
        int menor = 0;

        int mudanca = 50;

        for (Implementacao i : implementacoes) {
            if (i.getFuncao().startsWith("FEATURE")) {
                impl += 1;
                menor += 1;
                if (menor == mudanca) {
                    menor = 0;
                    maior += 1;
                }
            }
        }


        documento.adicionarLinha("\tpublic static final int IMPLEMENTACOES = " + impl + ";");
        documento.adicionarLinha("\tpublic static final String VERSAO = \"" + maior + "." + menor + "\";");


        documento.adicionarLinha("\tpublic void init () {");

        documento.adicionarLinha("\t\tArrayList<String> features = new ArrayList<String>();");

        for (Implementacao i : implementacoes) {
            if (i.getFuncao().startsWith("FEATURE")) {
                documento.adicionarLinha("\t\tfeatures.add(\"" + i.getFuncao() + "\");");
            }
            //  System.out.println(i.getArquivo() + " :: " + i.getData() + " -->> " + i.getFuncao());
        }


        documento.adicionarLinha();
        documento.adicionarLinha("\t}");

        documento.adicionarLinha();
        documento.adicionarLinha("}");

        Texto.arquivo_escrever(arquivo, documento.toDocumento());

    }
}
