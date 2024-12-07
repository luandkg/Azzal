package projetos.aoc2024;

import libs.luan.*;

public class AOC_2024_DAY_05 extends AOC_2024_DAY {

    public AOC_2024_DAY_05() {
        super(5, "Print Queue");
    }


    @Override
    public void parte_1() {

        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_05.txt"));
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");

        Lista<Regra> regras = new Lista<Regra>();
        Lista<Lista<Integer>> updates = new Lista<Lista<Integer>>();


        for (String linha : Strings.DIVIDIR_LINHAS(texto_entrada)) {
            //fmt.print("++ {}",linha);
            if (linha.contains("|")) {
                regras.adicionar(new Regra(Integer.parseInt(Strings.GET_ATE(linha, "|")), Integer.parseInt(Strings.GET_DEPOIS(linha, "|"))));
            } else if (linha.contains(",")) {
                Lista<Integer> update = new Lista<Integer>();
                for (String numero : Strings.DIVIDIR_POR(linha, ",")) {
                    update.adicionar(Integer.parseInt(numero));
                }
                updates.adicionar(update);
            }
        }

        for (Regra regra : regras) {
            fmt.print("Regra -->> ( {} : {} )", regra.parte_1, regra.parte_2);
        }


        fmt.print("Regras     :: {}",regras.getQuantidade());
        fmt.print("Sequencias :: {}",updates.getQuantidade());


        Lista<Lista<Integer>> updates_corretos = new Lista<Lista<Integer>>();

        for (Lista<Integer> update : updates) {
            fmt.print("++ Update :: {}", Strings.LISTA_TO_TEXTO_LINHA_INTEIRO(update));

            int numero_depois = 0;
            int numero_contagem=0;
            int update_correto=0;

            for (Integer numero : update) {
                fmt.print("\t ++ {}",numero);

                int segundo_numero=0;
                int segundo_quantidade=0;
                int segundo_numero_ok=0;

                for (Integer numero2 : update) {
                    if(segundo_numero>numero_contagem){

                        boolean tem = false;
                        for(Regra regra : regras){
                            if(regra.parte_1==numero && regra.parte_2==numero2){
                                tem=true;
                                break;
                            }
                        }

                        fmt.print("\t ++ {}::{} -->> {}",numero,numero2,Portugues.VALIDAR(tem,"OK","PROBLEMA"));
                        segundo_quantidade+=1;
                        if(tem){
                            segundo_numero_ok+=1;
                        }
                    }
                    segundo_numero += 1;
                }

                if(segundo_quantidade==segundo_numero_ok){
                    update_correto+=1;
                }

                numero_contagem += 1;
            }

            if (update_correto == update.getQuantidade()) {
                updates_corretos.adicionar(update);
            }

        }

        int somatorio_numero_do_meio = 0;

        for (Lista<Integer> update : updates_corretos) {

            fmt.print("++ Update Correto :: {}", Strings.LISTA_TO_TEXTO_LINHA_INTEIRO(update));


            int numero_do_meio = 0;

            if (update.getQuantidade() % 2 == 1) {
                numero_do_meio = update.get(update.getQuantidade() / 2);
            } else if (update.getQuantidade() % 2 == 0) {
                throw new RuntimeException("Problema para encontrar numero central !");
            }

            somatorio_numero_do_meio += numero_do_meio;

            fmt.print("\t -- Centro :: {}", numero_do_meio);
        }

        fmt.print("Somatório Número do Meio :: {}", somatorio_numero_do_meio);

    }

    @Override
    public void parte_2() {


    }

    class Regra {

        public int parte_1 = 0;
        public int parte_2 = 0;

        public Regra(int eParte1, int eParte2) {
            parte_1 = eParte1;
            parte_2 = eParte2;
        }
    }

}
