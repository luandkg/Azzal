package libs.ez;

import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Opcional;


public class AQSequenciador {

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

    public static void criar_sequencial(Banco eBanco, String eNome, int eInicio, int ePasso) {

        DKGObjeto objeto = new DKGObjeto("Sequencia");
        objeto.identifique("Nome").setValor(eNome);
        objeto.identifique("Sequencia").setInteiro(eInicio);
        objeto.identifique("Passo").setInteiro(ePasso);
        eBanco.adicionar(objeto.toString());

    }

    public static Opcional<ItemDoBanco> procurar_sequencial(Banco eBanco, String eNome) {

        Opcional<ItemDoBanco> ret = new Opcional<ItemDoBanco>();

        for (ItemDoBanco item : eBanco.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Nome").isValor(eNome)) {
                ret.set(item);
                break;
            }
            //  System.out.println(objeto.toString());
        }

        return ret;
    }

    public static int aumentar_sequencial(Banco eBanco, String eNome) {

        int chave = 0;

        for (ItemDoBanco item : eBanco.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Nome").isValor(eNome)) {

                chave = objeto.identifique("Sequencia").getInteiro(0);

                int passo = objeto.identifique("Passo").getInteiro(0);
                objeto.identifique("Sequencia").setInteiro(chave + passo);
                item.atualizar(objeto.toDocumento());

                break;
            }
            // System.out.println(objeto.toDocumento());
        }

        return chave;
    }

    public static int sequencia(ItemDoBanco item) {
        DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
        return objeto.identifique("Sequencia").getInteiro(0);
    }

    public static int zerar_sequencial(Banco eBanco, String eNome) {

        int chave = 0;

        for (ItemDoBanco item : eBanco.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Nome").isValor(eNome)) {
                objeto.identifique("Sequencia").setInteiro(0);
                item.atualizar(objeto.toDocumento());
                break;
            }
            // System.out.println(objeto.toDocumento());
        }

        return chave;
    }


    public static void organizar_sequencial(Banco eBanco, String eNome) {

        Opcional<ItemDoBanco> item_objeto_contador = procurar_sequencial(eBanco, eNome);

        if (item_objeto_contador.temValor()) {
            AQSequenciador.sequencia(item_objeto_contador.get());
        } else {
            AQSequenciador.criar_sequencial(eBanco, eNome, 0, 1);
        }


    }

    public static int get_sequencial_contador(Banco eBanco, String eNome) {

        Opcional<ItemDoBanco> item_objeto_contador = procurar_sequencial(eBanco, eNome);

        int ret = 0;

        if (item_objeto_contador.temValor()) {
            ret = AQSequenciador.sequencia(item_objeto_contador.get());
        } else {
            AQSequenciador.criar_sequencial(eBanco, eNome, 0, 1);
            ret = 0;
        }

        return ret;
    }

}
