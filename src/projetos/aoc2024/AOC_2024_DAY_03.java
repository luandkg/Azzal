package projetos.aoc2024;

import libs.luan.Strings;
import libs.luan.Texto;
import libs.luan.fmt;

public class AOC_2024_DAY_03 extends AOC_2024_DAY {

    public AOC_2024_DAY_03() {
        super(3);
    }

    @Override
    public void parte_1() {
        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_03.txt"));
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        String l1 = "";
        String l2 = "";
        String l3 = "";
        String p1 = "";
        String numero_1 = "";
        String virgula = "";
        String numero_2 = "";
        String p2 = "";

        int somatorio_produto = 0;

        int fase = 0;

        int i = 0;
        int o = texto_entrada.length();
        while (i < o) {
            String letra = String.valueOf(texto_entrada.charAt(i));

            if (fase == 0 && letra.contentEquals("m")) {
                fase = 1;
                l1 = letra;
            } else if (fase == 1 && letra.contentEquals("u")) {
                fase = 2;
                l2 = letra;
            } else if (fase == 2 && letra.contentEquals("l")) {
                fase = 3;
                l3 = letra;
            } else if (fase == 3 && letra.contentEquals("(")) {
                fase = 4;
                p1 = letra;
            } else if (fase == 4 && Strings.isDigito(letra)) {
                fase = 5;
                numero_1 = "";
                while (i < o) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    if (Strings.isDigito(d)) {
                        numero_1+=d;
                    }else{
                        i-=1;
                        break;
                    }
                    i += 1;
                }

            } else if (fase == 5 && letra.contentEquals(",")) {
                fase = 6;
                virgula = letra;
            } else if (fase == 6 && Strings.isDigito(letra)) {
                fase = 7;
                numero_2 = "";
                while (i < o) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    if (Strings.isDigito(d)) {
                        numero_2+=d;
                    }else{
                        i-=1;
                        break;
                    }
                    i += 1;
                }
            } else if (fase == 7 && letra.contentEquals(")")) {
                fase = 0;
                p2 = letra;

                int valor_1 = Integer.parseInt(numero_1);
                int valor_2 = Integer.parseInt(numero_2);

                int produto = valor_1*valor_2;
                somatorio_produto+=produto;

                fmt.print(">> {}{}{} :: {} * {} = {}", l1, l2, l3,numero_1,numero_2,produto);

            } else {
                fase = 0;
               // fmt.print("-- ZERANDO !");
                l1 = "";
                l2 = "";
                numero_1="";
            }


            i += 1;
        }


        fmt.print("Somatorio do Produto = {}",somatorio_produto);

    }

    @Override
    public void parte_2() {
        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_2);


    }
}
