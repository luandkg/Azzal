package projetos.aoc2024;

import libs.luan.Strings;
import libs.luan.Texto;
import libs.luan.fmt;

public class AOC_2024_DAY_03 extends AOC_2024_DAY {

    public AOC_2024_DAY_03() {
        super(3,"");
    }

    @Override
    public void parte_1() {
        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("TIP_03.txt"));
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        String comando_inicial = "";

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

            //     fmt.print("-->> {} {}",letra,fase);

            if (fase == 0 && letra.contentEquals("m")) {
                fase = 1;

                int i_antes = i;
                int a = 0;

                String comando = "";

                while (i < o && a < 3) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    comando += d;
                    a += 1;
                    i += 1;
                }

                if (Strings.isIgual(comando, "mul")) {
                    comando_inicial = comando;
                    i -= 1;
                    //   fmt.print("++ MUL");
                } else {
                    i = i_antes;
                }

            } else if (fase == 1 && letra.contentEquals("(")) {
                fase = 2;
                p1 = letra;
            } else if (fase == 2 && Strings.isDigito(letra)) {
                fase = 3;
                numero_1 = "";
                while (i < o) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    if (Strings.isDigito(d)) {
                        numero_1 += d;
                    } else {
                        i -= 1;
                        break;
                    }
                    i += 1;
                }

            } else if (fase == 3 && letra.contentEquals(",")) {
                fase = 4;
                virgula = letra;
            } else if (fase == 4 && Strings.isDigito(letra)) {
                fase = 5;
                numero_2 = "";
                while (i < o) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    if (Strings.isDigito(d)) {
                        numero_2 += d;
                    } else {
                        i -= 1;
                        break;
                    }
                    i += 1;
                }
            } else if (fase == 5 && letra.contentEquals(")")) {
                fase = 0;
                p2 = letra;

                int valor_1 = Integer.parseInt(numero_1);
                int valor_2 = Integer.parseInt(numero_2);

                int produto = valor_1 * valor_2;
                somatorio_produto += produto;

                //   fmt.print(">> {} :: {} * {} = {}", comando_inicial, numero_1, numero_2, produto);

            } else {
                fase = 0;
                //  fmt.print("-- ZERANDO !");
                comando_inicial = "";
                numero_1 = "";
                numero_2 = "";
            }


            i += 1;
        }


        fmt.print("Somatorio do Produto = {}", somatorio_produto);

    }

    @Override
    public void parte_2() {
        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_2);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_03.txt"));
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        String comando_inicial = "";

        String p1 = "";
        String numero_1 = "";
        String virgula = "";
        String numero_2 = "";
        String p2 = "";



        int somatorio_produto = 0;
        boolean somar = true;

        int fase = 0;

        int i = 0;
        int o = texto_entrada.length();
        while (i < o) {
            String letra = String.valueOf(texto_entrada.charAt(i));

            //     fmt.print("-->> {} {}",letra,fase);

            if (fase == 0 && letra.contentEquals("m")) {
                fase = 1;

                int i_antes = i;
                int a = 0;

                String comando = "";

                while (i < o && a < 3) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    comando += d;
                    a += 1;
                    i += 1;
                }

                if (Strings.isIgual(comando, "mul")) {
                    comando_inicial = comando;
                    i -= 1;
                    //   fmt.print("++ MUL");
                } else {
                    i = i_antes;
                }
            } else if (fase == 0 && letra.contentEquals("d")) {


                int i_antes = i;
                int i_do =0;
                int a = 0;

               // fmt.print(">> Comecar D");

                String comando_dont = "";
                String comando_do = "";

                while (i < o && a < 7) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    if (a < 7) {
                        comando_dont += d;
                    }
                    if (a < 4) {
                        comando_do += d;
                        i_do=i;
                    }
                    a += 1;
                    i += 1;
                }

               // fmt.print("DO -->> {} : {}",comando_dont,comando_do);

                if (Strings.isIgual(comando_dont, "don't()")) {
                    i -= 1;
                    fmt.print("++ DON'T");
                    fase = 0;
                    comando_inicial = "";

                    somar=false;
                } else {

                    if (Strings.isIgual(comando_do, "do()")) {
                        i = i_do-1;
                        fmt.print("++ DO");
                        fase = 0;
                        comando_inicial = "";

                        somar=true;
                    }else{
                        i = i_antes;
                    }

                }


            } else if (fase == 1 && letra.contentEquals("(")) {
                fase = 2;
                p1 = letra;
            } else if (fase == 2 && Strings.isDigito(letra)) {
                fase = 3;
                numero_1 = "";
                while (i < o) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    if (Strings.isDigito(d)) {
                        numero_1 += d;
                    } else {
                        i -= 1;
                        break;
                    }
                    i += 1;
                }

            } else if (fase == 3 && letra.contentEquals(",")) {
                fase = 4;
                virgula = letra;
            } else if (fase == 4 && Strings.isDigito(letra)) {
                fase = 5;
                numero_2 = "";
                while (i < o) {
                    String d = String.valueOf(texto_entrada.charAt(i));
                    if (Strings.isDigito(d)) {
                        numero_2 += d;
                    } else {
                        i -= 1;
                        break;
                    }
                    i += 1;
                }
            } else if (fase == 5 && letra.contentEquals(")")) {
                fase = 0;
                p2 = letra;

                int valor_1 = Integer.parseInt(numero_1);
                int valor_2 = Integer.parseInt(numero_2);


                int produto = valor_1 * valor_2;

                if(somar && comando_inicial.contentEquals("mul")){
                    somatorio_produto += produto;
                    fmt.print(">> {} :: {} * {} = {}", comando_inicial, numero_1, numero_2, produto);
                }else{
                    fmt.print(">> PULRAR {} :: {} * {} = {}", comando_inicial, numero_1, numero_2, produto);
                }


                comando_inicial = "";

            } else {
                fase = 0;
                //  fmt.print("-- ZERANDO !");
                comando_inicial = "";
                numero_1 = "";
                numero_2 = "";
            }


            i += 1;
        }


        fmt.print("Somatorio do Produto = {}", somatorio_produto);

    }
}
