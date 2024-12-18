package apps.app_testes;

import apps.app_citatte.Stringum;
import libs.arquivos.video.VideoCodecador;
import libs.luan.*;

public class app_testes {


    public static void init() {

        test_vetores();

        test_listas();
    }


    public static void test_vetores() {


        fmt.print("------------------------------ TESTE : VETORES -----------------------------");


        Vetor<String> valores = new Vetor<String>(5);
        valores.set(0, "Luca");
        valores.set(1, "Fernando");
        valores.set(2, "Alfredo");
        valores.set(3, "Matheus");
        valores.set(4, "Hugo");


        fmt.print("Exibir ");
        for (String item : valores) {
            fmt.print("\t {}", item);
        }

        fmt.print("Exibir Indexado");
        for (Indexado<String> item : Indexamento.indexe(valores)) {
            fmt.print("\t {} - {}", item.index(), item.get());
        }

        fmt.print("Exibir Embaralhado e Indexado v1");
        for (Indexado<String> item : Embaralhar.indexe(valores)) {
            fmt.print("\t {} - {}", item.index(), item.get());
        }

        fmt.print("Exibir Embaralhado e Indexado v2");
        for (Indexado<String> item : Embaralhar.indexe(valores)) {
            fmt.print("\t {} - {}", item.index(), item.get());
        }

        fmt.print("Exibir Embaralhado e Indexado v3");
        for (Indexado<String> item : Embaralhar.indexe(valores)) {
            fmt.print("\t {} - {}", item.index(), item.get());
        }


        Ordenador.ordenar_vetor_crescente(valores, Ordenador.ORDENAR_STRING_NAO_SENSITIVA());

        fmt.print("Exibir em ordem - CRESCENTE");
        for (String item : valores) {
            fmt.print("\t {}", item);
        }

        Ordenador.ordenar_vetor_decrescente(valores, Ordenador.ORDENAR_STRING_NAO_SENSITIVA());

        fmt.print("Exibir em ordem - DECRESCENTE");
        for (String item : valores) {
            fmt.print("\t {}", item);
        }

        fmt.print("Obter Aleatorio - Com Repeticao");
        fmt.print("\t Pegue 1 -> {}", Aleatorio.escolha_um(valores));
        fmt.print("\t Pegue 2 -> {}", Aleatorio.escolha_um(valores));
        fmt.print("\t Pegue 3 -> {}", Aleatorio.escolha_um(valores));


        fmt.print("Obter Aleatorio - Sem Repeticao");
        Lista<String> candidatos = Aleatorio.criarCaixaDeEscolhaUnica(valores);
        fmt.print("\t Pegue 1 -> {}", Aleatorio.escolha_um_sem_repetir(candidatos));
        fmt.print("\t Pegue 2 -> {}", Aleatorio.escolha_um_sem_repetir(candidatos));
        fmt.print("\t Pegue 3 -> {}", Aleatorio.escolha_um_sem_repetir(candidatos));
        fmt.print("\t Pegue 4 -> {}", Aleatorio.escolha_um_sem_repetir(candidatos));
        fmt.print("\t Pegue 5 -> {}", Aleatorio.escolha_um_sem_repetir(candidatos));


    }

    public static void test_listas() {


        fmt.print("------------------------------ TESTE : LISTAS -----------------------------");


        Lista<Integer> valores = new Lista<Integer>();
        valores.adicionar(52);
        valores.adicionar(15);
        valores.adicionar(86);
        valores.adicionar(43);
        valores.adicionar(23);

        fmt.print("{}", Stringum.EXIBIR_EM_LISTA_TIPADA(valores));


        Ordenador.ordenar_lista_crescente(valores, Matematica.INTEIRO_ORDENAVEL());

        fmt.print("{}", Stringum.EXIBIR_EM_LISTA_TIPADA(valores));


        VideoCodecador.init("/home/luan/Vídeos/vi/ecossistema_v2.vi");

    }
}
