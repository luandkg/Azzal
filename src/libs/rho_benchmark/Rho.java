package libs.rho_benchmark;


import libs.luan.Texto;
import libs.tempo.Tempo;

public class Rho {

    private static String sTexto = "";

    public static void profile(String funcao_nome) {

        String parte = Tempo.getTempoCompleto() + " -->> " + funcao_nome;

        escreva(parte);
        //  print(parte);

    }

    public static void profile_iniciar(String funcao_nome) {
        RhoDuracao.iniciar(funcao_nome);
        Rho.profile(funcao_nome);
    }

    public static void profile_duracao(String funcao_nome) {
        RhoDuracao.terminar(funcao_nome);
        Rho.profile(funcao_nome, RhoDuracao.duracao(funcao_nome));
    }


    public static void profile(String funcao_nome, String execucao) {
        String parte = Tempo.getTempoCompleto() + " -->> " + funcao_nome + " :: " + execucao;

        escreva(parte);
        //print(parte);
    }

    public static void profile(String funcao_nome, int execucao) {
        String parte = Tempo.getTempoCompleto() + " -->> " + funcao_nome + " :: " + execucao;

        escreva(parte);
        //  print(parte);
    }

    public static void profile(String funcao_nome, long execucao) {
        String parte = Tempo.getTempoCompleto() + " -->> " + funcao_nome + " :: " + execucao;

        escreva(parte);
        // print(parte);
    }


    private static void print(String linha) {
        System.out.println(linha);
    }

    private static void escreva(String linha) {
        sTexto += "\n" + linha;
    }

    public static void salvar(String eArquivo) {

        String antes = Texto.arquivo_ler(eArquivo);

        String documento = sTexto;

        if (antes.length() > 0) {
            documento = antes + "\n" + sTexto;
        }

        Texto.arquivo_escrever(eArquivo, documento);

        sTexto = "";
    }

    public static void limpar() {
        sTexto = "";
    }
}