package projetos.aoc2024;

import libs.luan.*;

public class AOC_2024_DAY_07 extends AOC_2024_DAY {


    public AOC_2024_DAY_07() {
        super(7, "Bridge Repair");
    }

    @Override
    public void parte_1() {

        AOC_2024.CABECALHO(getProblemaNumero(), AOC_2024.PARTE_1);

        fmt.print("------------- ENTRADA ----------------");
        String texto_entrada = Texto.arquivo_ler(getArquivoEntrada());
        fmt.print("{}", texto_entrada);

        fmt.print(">> Processando");


        long somatorio_correto = 0;
        int equacoes_contagem = 0;
        int equacoes_solucionadas = 0;


        for (String equacao : Strings.DIVIDIR_LINHAS(texto_entrada)) {

            fmt.print("++ EQUAÇÃO :: [{}]", equacao);

            long resultado = Long.parseLong(Strings.GET_ATE(equacao, ":"));
            Lista<Long> entradas = new Lista<Long>();
            for (String s_numero : Strings.DIVIDIR_POR(Strings.GET_DEPOIS(equacao, ":"), " ")) {
                entradas.adicionar(Long.parseLong(s_numero.trim()));
            }


            int operadores_desconhecidos = -1;

            for (Long numero : entradas) {
                fmt.print("\t - Entrada      : {}", numero);
                operadores_desconhecidos += 1;
            }

            final String OPERADOR_TIPO_SOMA = "+";
            final String OPERADOR_TIPO_PRODUTO = "*";

            Lista<String> operadores_permitidas = new Lista<String>();
            operadores_permitidas.adicionar(OPERADOR_TIPO_SOMA);
            operadores_permitidas.adicionar(OPERADOR_TIPO_PRODUTO);

            int operacoes = Matematica.PERMUTACAO_QUANTIDADE(operadores_desconhecidos, operadores_permitidas.getQuantidade());

            fmt.print("\t - Resultado    : {}", resultado);
            fmt.print("\t - Operadores   : {}", operadores_desconhecidos);
            fmt.print("\t - Operações    : {}", operacoes);

            fmt.print("");


            Lista<String> operadores_permutacoes = Matematica.PERMUTACOES(operadores_desconhecidos, operadores_permitidas, Strings.CONCATENADOR());

            boolean tem_alguma_ok = false;

            int operacao_id = 1;
            for (String operadores_permutacao : operadores_permutacoes) {

                int arg = 0;

                long operacao_resultado = entradas.get(arg);
                String construindo_operacao = String.valueOf(entradas.get(arg));

                for (String operador_corrente : Strings.GET_LETRAS(operadores_permutacao)) {

                    arg += 1;
                    long numero_corrente = entradas.get(arg);

                    if (Strings.isIgual(operador_corrente, OPERADOR_TIPO_SOMA)) {
                        operacao_resultado += numero_corrente;
                    } else if (Strings.isIgual(operador_corrente, OPERADOR_TIPO_PRODUTO)) {
                        operacao_resultado *= numero_corrente;
                    }

                    construindo_operacao += " " + operador_corrente + " " + numero_corrente;

                }

                boolean is_ok = resultado == operacao_resultado;

                if (is_ok) {
                    tem_alguma_ok = true;
                }

                fmt.print("\t >> OPERAÇÃO :: {} = {} -->> {} = {} :: {}", operacao_id, operadores_permutacao, construindo_operacao, fmt.espacar_depois(operacao_resultado, 15), Portugues.VALIDAR(is_ok, "CORRETO", "."));

                operacao_id += 1;
            }

            fmt.print("");
            fmt.print("\t - TEM CORRETO : {}", Portugues.VALIDAR(tem_alguma_ok, "SIM", "-"));


            if (tem_alguma_ok) {
                somatorio_correto += resultado;
                equacoes_solucionadas += 1;
            }

            equacoes_contagem += 1;
        }

        fmt.print("");
        fmt.print(">> Equações {} de {}", equacoes_solucionadas, equacoes_contagem);

        info(AOC_2024.PARTE_1, somatorio_correto);


    }

    @Override
    public void parte_2() {

    }
}
