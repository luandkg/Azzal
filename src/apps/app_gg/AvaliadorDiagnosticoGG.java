package apps.app_gg;

import libs.dkg.DKGObjeto;
import libs.luan.RefString;
import libs.luan.Strings;
import libs.luan.TextoDocumento;
import libs.tempo.Calendario;

public class AvaliadorDiagnosticoGG {


    public static void finalizar(DKGObjeto turma_objeto) {


        System.out.println(Strings.exibir_justificado(bimestre(turma_objeto, "B1"), 100));
        System.out.println(Strings.exibir_justificado(bimestre(turma_objeto, "B2"), 100));
        System.out.println(Strings.exibir_justificado(bimestre(turma_objeto, "B3"), 100));

        System.out.println(Strings.exibir_justificado(comparar_3(turma_objeto, "B1", "B2", "B3"), 100));

        System.out.println("By @luandkg - http://www.scholarium.com.br/adf");
        System.out.println("ATUALIZADO EM " + Calendario.getDataHoje().getTempoLegivel() + " " + Calendario.getHoraCompleta());

    }

    public static String bimestre(DKGObjeto turma_objeto, String bimestre_chave) {

        TextoDocumento texto = new TextoDocumento();


        String turma = turma_objeto.identifique("Nome").getValor();
        int c_atividades = turma_objeto.unicoObjeto(bimestre_chave).identifique("Atividades").getInteiro();
        int dias_estudando = turma_objeto.unicoObjeto(bimestre_chave).identifique("ESTUDO_EFETIVO").getInteiro();
        int dias_estudando_muito = turma_objeto.unicoObjeto(bimestre_chave).identifique("ESTUDO_QUALITATIVO").getInteiro();

        int primeiros_dias = turma_objeto.unicoObjeto(bimestre_chave).identifique("AP_TEMPO").getInteiro();
        int primeiros_aprovados = turma_objeto.unicoObjeto(bimestre_chave).identifique("AP_QUANTIDADE").getInteiro();

        int aprovados = turma_objeto.unicoObjeto(bimestre_chave).identifique("Aprovados").getInteiro();


        RefString bimestre_nome = new RefString();
        RefString bimestre_inicio_frase = new RefString();

        se_entao(bimestre_nome, bimestre_chave, "B1", "primeiro");
        se_entao(bimestre_nome, bimestre_chave, "B2", "segundo");
        se_entao(bimestre_nome, bimestre_chave, "B3", "terceiro");
        se_entao(bimestre_nome, bimestre_chave, "B4", "quarto");

        se_entao(bimestre_inicio_frase, bimestre_chave, "B1", "Durante o");
        se_entao(bimestre_inicio_frase, bimestre_chave, "B2", "Já no");
        se_entao(bimestre_inicio_frase, bimestre_chave, "B3", "Após o recesso, no");
        se_entao(bimestre_inicio_frase, bimestre_chave, "B4", "Ao final do ano, no");


        String frase_final = ".";

        if (dias_estudando_muito > 0) {
            frase_final = ", " + se_falso(dias_estudando_muito < dias_estudando / 2, "dos quais, em", "mas, em apenas") + " " + dias_estudando_muito + " dias a turma teve uma participação " + se_falso(dias_estudando_muito < dias_estudando / 2, "incrível", "considerável") + ".";
        }

        texto.adicionar(bimestre_inicio_frase.get() + " " + bimestre_nome.get() + " bimestre a turma " + turma + " realizou " + c_atividades + " atividades distribuidas em " + dias_estudando + " dias de realização de atividades avaliativas" + frase_final);

        int BIMESTRE_DIAS = turma_objeto.unicoObjeto(bimestre_chave).identifique("BIMESTRE_DIAS").getInteiro();

        String frase_dias = "em " + primeiros_dias + " dias, após o início do bimestre, ";

        if (primeiros_dias > ((BIMESTRE_DIAS / 2) + (BIMESTRE_DIAS / 4))) {
            frase_dias = "faltando " + (BIMESTRE_DIAS - primeiros_dias) + " dias para acabar o bimestre ";
        } else if (primeiros_dias > BIMESTRE_DIAS / 2) {
            frase_dias = "no meio bimestre ";
        }

        String alunos = singular_plural(primeiros_aprovados, "aluno conseguiu", "alunos conseguiram");


        if (BIMESTRE_DIAS - primeiros_dias > 0) {
            texto.adicionar("Foi observado que " + frase_dias + primeiros_aprovados + " " + alunos + " antigir média suficiente para aprovação no bimestre e que ao final do bimestre " + aprovados + " alunos foram aprovados.");
        } else {
            texto.adicionar("Foi observado que somente ao final do bimestre houve aprovação de " + aprovados + " alunos.");
        }

        return texto.toDocumento();
    }


    public static String comparar_3(DKGObjeto turma_objeto, String b1, String b2, String b3) {

        TextoDocumento texto = new TextoDocumento();


        String turma = turma_objeto.identifique("Nome").getValor();

        int b1_aprovados = turma_objeto.unicoObjeto(b1).identifique("Aprovados").getInteiro();
        int b2_aprovados = turma_objeto.unicoObjeto(b2).identifique("Aprovados").getInteiro();
        int b3_aprovados = turma_objeto.unicoObjeto(b3).identifique("Aprovados").getInteiro();

        if (b2_aprovados > b1_aprovados) {
            if (b3_aprovados > b2_aprovados) {
                texto.adicionar("Foi possível perceber que a turma apresenta evolução, já que a quantidade de alunos aprovados aumenta a cada bimestre.");
            }
        }

        return texto.toDocumento();
    }


    public static String singular_plural(int v, String singular, String plural) {
        String ret = singular;
        if (v > 1) {
            ret = plural;
        }
        return ret;
    }


    public static void se_entao(RefString frase, String condicao, String condicao_verdadeira, String alterar) {
        if (condicao.contentEquals(condicao_verdadeira)) {
            frase.set(alterar);
        }
    }


    public static String se_falso(boolean condicao, String falso, String verdade) {
        String ret = falso;

        if (condicao) {
            ret = verdade;
        }

        return ret;
    }
}
