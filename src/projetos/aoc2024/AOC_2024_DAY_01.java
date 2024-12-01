package projetos.aoc2024;

import libs.arquivos.binario.Inteiro;
import libs.luan.*;

public class AOC_2024_DAY_01 extends  AOC_2024_DAY {

    public AOC_2024_DAY_01( ){
        super(1);
    }

    @Override
    public void parte_1() {
        AOC_2024.CABECALHO(getProblemaNumero(),1);

        fmt.print("------------- ENTRADA ----------------");

        String texto_dica = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_01.txt"));
        fmt.print("{}",texto_dica);

        Lista<Integer> esquerda = new Lista<Integer>();
        Lista<Integer> direita = new Lista<Integer>();

        int i =0;
        for(String linha : Strings.DIVIDIR_LINHAS(texto_dica)){

            int n1 = Integer.parseInt(Strings.GET_ATE(linha," "));
            int n2 = Integer.parseInt(Strings.GET_REVERSO_ATE(linha," "));

            fmt.print("\t ++ esquerda({}) = {} --- direita({}) = {}",i,n1,i,n2);

            esquerda.adicionar(n1);
            direita.adicionar(n2);

            i+=1;
        }

        fmt.print(">> Ordenando....");
        Ordenador.ordenar_lista_crescente(esquerda, Inteiro.GET_ORDENAVEL());
        Ordenador.ordenar_lista_crescente(direita, Inteiro.GET_ORDENAVEL());

        fmt.print(">> Exibindo");

        int distancia_total = 0;

        for(int a=0;a<esquerda.getQuantidade();a++){

            int esquerda_valor = esquerda.get(a);
            int direita_valor = direita.get(a);

            int distancia = Matematica.modulo(esquerda_valor-direita_valor);

            fmt.print("\t ++ esquerda({}) = {} --- direita({}) = {} :: {}",a,esquerda_valor,i,direita_valor,distancia);

            distancia_total+=distancia;
        }

        fmt.print(">> Distancia total = {}",distancia_total);
    }

    @Override
    public void parte_2() {

        AOC_2024.CABECALHO(getProblemaNumero(),2);

        fmt.print("------------- ENTRADA ----------------");

        String texto_dica = Texto.arquivo_ler(AOC_2024.GET_ARQUIVO("DAY_01.txt"));
        fmt.print("{}",texto_dica);

        Lista<Integer> esquerda = new Lista<Integer>();
        Lista<Integer> direita = new Lista<Integer>();

        int i =0;
        for(String linha : Strings.DIVIDIR_LINHAS(texto_dica)){

            int n1 = Integer.parseInt(Strings.GET_ATE(linha," "));
            int n2 = Integer.parseInt(Strings.GET_REVERSO_ATE(linha," "));

            fmt.print("\t ++ esquerda({}) = {} --- direita({}) = {}",i,n1,i,n2);

            esquerda.adicionar(n1);
            direita.adicionar(n2);

            i+=1;
        }


        fmt.print(">> Exibindo");

        int similaridade_total = 0;

        for(int a=0;a<esquerda.getQuantidade();a++){

            int esquerda_valor = esquerda.get(a);
            int contagem_valor= direita.contar(Inteiro.IGUALAVEL(),esquerda_valor);

            int similaridade = esquerda_valor*contagem_valor;

            fmt.print("\t ++ esquerda({}) = {} -->> {} :: {}",a,esquerda_valor,contagem_valor,similaridade);

            similaridade_total+=similaridade;
        }

        fmt.print(">> Similaridade = {}",similaridade_total);

    }
}
