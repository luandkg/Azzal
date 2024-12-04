package projetos.aoc2024;

import libs.luan.Lista;
import libs.luan.fmt;
import libs.tempo.Calendario;

public class AOC_2024 {

    public static final int PARTE_1 = 1;
    public static final int PARTE_2 = 2;

    public static void PROBLEMA( int eProblema,int eParte) {

        Lista<AOC_2024_DAY> problemas = new Lista<AOC_2024_DAY>();

        problemas.adicionar(new AOC_2024_DAY_01());
        problemas.adicionar(new AOC_2024_DAY_02());
        problemas.adicionar(new AOC_2024_DAY_03());
        problemas.adicionar(new AOC_2024_DAY_04());

        PROBLEMA_EXECUTAR(problemas,eParte,eProblema);
    }

    private static void PROBLEMA_EXECUTAR(Lista<AOC_2024_DAY> problemas,int eParte, int eProblema){

        boolean encontrado = false;

        for (AOC_2024_DAY problema_dia : problemas) {
            if (problema_dia.getProblemaNumero() == eProblema) {
                if(eParte==PARTE_1) {
                    problema_dia.parte_1();
                }else  if(eParte==PARTE_2){
                    problema_dia.parte_2();
                }else{
                    PROBLEMA("AOC 2024 - PARTE " + eParte +" NAO ENCONTRADA NO PROBLEMA -->> " + eProblema);
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            PROBLEMA("AOC 2024 - PROBLEMA NAO ENCONTRADO -->> " + eProblema);
        }

    }


    public static void PROBLEMA(String frase) {

        fmt.print("------------------------ AOC 2024 ----------------------");
        fmt.print("Problema : {}", frase);
        fmt.print("Quando   : {}", Calendario.getTempoCompleto());
        fmt.print("--------------------------------------------------------");

    }


    public static void INFO(int eProblema,int eParte,String frase) {

        fmt.print("------------------------ AOC 2024 ----------------------");
        fmt.print("Problema    : {} -- PARTE {}", eProblema,eParte);
        fmt.print("Quando      : {}", Calendario.getTempoCompleto());
        fmt.print("Resultado   : {}", frase);
        fmt.print("--------------------------------------------------------");

    }

    public static void CABECALHO(int eProblema,int eParte ) {

        fmt.print("------------------------ AOC 2024 ----------------------");
        fmt.print("Problema    : {} -- PARTE {}", eProblema,eParte);
        fmt.print("Quando      : {}", Calendario.getTempoCompleto());
        fmt.print("--------------------------------------------------------");

    }


    public static String GET_ARQUIVO(String nome){
        return "/home/luan/dev/azzal/res/aoc_2024/" +nome;
    }
}
