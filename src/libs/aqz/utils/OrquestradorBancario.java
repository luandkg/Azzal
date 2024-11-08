package libs.aqz.utils;

import libs.armazenador.Armazenador;
import libs.armazenador.Banco;

public class OrquestradorBancario {

    public static void verificar_banco(Armazenador m, String eBancoNome) {
        if (!m.banco_existe(eBancoNome)) {
            m.banco_criar(eBancoNome);
        }
    }

    public static Banco organizar_banco(Armazenador m, String eBancoNome) {
        if (!m.banco_existe(eBancoNome)) {
            m.banco_criar(eBancoNome);
        }

        return m.getBanco(eBancoNome);
    }

}
