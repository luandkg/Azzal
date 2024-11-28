package libs.zetta;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.zetta.fazendario.IndiceLocalizado;
import libs.luan.Opcional;
import libs.luan.fmt;

public class OpcionalZ {

    public static String OBTER_OU_ERRO(Opcional<IndiceLocalizado> op_indice, String erro) {

        if (op_indice.isOK()) {
            return String.valueOf(op_indice.get().getIndice());
        } else {
            return erro;
        }

    }

    public static String OBTER_PONTEIRO_OU_ERRO(Opcional<IndiceLocalizado> op_indice, String erro) {

        if (op_indice.isOK()) {
            return String.valueOf(op_indice.get().getPonteiro());
        } else {
            return erro;
        }

    }

    public static void EXIBIR_ITEM_OU_ERRO(Opcional<Entidade> op_valor, String erro) {

        if (op_valor.isOK()) {
            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(op_valor.get()));
        } else {
            fmt.print("ERRO :: {}", erro);
        }

    }

}
