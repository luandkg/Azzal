package libs.aqz.utils;

import libs.armazenador.ParticaoPrimaria;
import libs.dkg.DKG;
import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.Opcional;

public class Sequenciador {



    public static void criar_sequencial(ParticaoPrimaria eParticaoPrimaria, String eNome, int eInicio, int ePasso) {

        DKGObjeto objeto = new DKGObjeto("Sequencia");
        objeto.identifique("Nome").setValor(eNome);
        objeto.identifique("Sequencia").setInteiro(eInicio);
        objeto.identifique("Passo").setInteiro(ePasso);
        eParticaoPrimaria.adicionar(objeto.toString());

    }

    public static Opcional<ItemDoBanco> procurar_sequencial(ParticaoPrimaria eParticaoPrimaria, String eNome) {

        Opcional<ItemDoBanco> ret = new Opcional<ItemDoBanco>();

        for (ItemDoBanco item : eParticaoPrimaria.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Nome").isValor(eNome)) {
                ret.set(item);
                break;
            }
            //  System.out.println(objeto.toString());
        }

        return ret;
    }

    public static int aumentar_sequencial(ParticaoPrimaria eParticaoPrimaria, String eNome) {

        int chave = 0;

        for (ItemDoBanco item : eParticaoPrimaria.getItens()) {
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

    public static void zerar_sequencial(ParticaoPrimaria eParticaoPrimaria, String eNome) {


        for (ItemDoBanco item : eParticaoPrimaria.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Nome").isValor(eNome)) {
                objeto.identifique("Sequencia").setInteiro(0);
                item.atualizar(objeto.toDocumento());
                break;
            }
        }

    }

    public static int sequencia(ItemDoBanco item) {
        DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
        return objeto.identifique("Sequencia").getInteiro(0);
    }


    public static void organizar_sequencial(ParticaoPrimaria eParticaoPrimaria, String eNome) {

        Opcional<ItemDoBanco> item_objeto_contador = procurar_sequencial(eParticaoPrimaria, eNome);

        if (item_objeto_contador.temValor()) {
            Sequenciador.sequencia(item_objeto_contador.get());
        } else {
            Sequenciador.criar_sequencial(eParticaoPrimaria, eNome, 0, 1);
        }


    }

    public static int get_sequencial_contador(ParticaoPrimaria eParticaoPrimaria, String eNome) {

        Opcional<ItemDoBanco> item_objeto_contador = procurar_sequencial(eParticaoPrimaria, eNome);

        int ret = 0;

        if (item_objeto_contador.temValor()) {
            ret = Sequenciador.sequencia(item_objeto_contador.get());
        } else {
            Sequenciador.criar_sequencial(eParticaoPrimaria, eNome, 0, 1);
            ret = 0;
        }

        return ret;
    }

    public static void DUMP(ParticaoPrimaria eParticaoPrimaria, String eNome) {

        Lista<DKGObjeto> objs = new Lista<DKGObjeto>();

        for (ItemDoBanco item : eParticaoPrimaria.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            objs.adicionar(objeto);
        }

        DKGFeatures.EXIBIR_TABELA(objs);

    }

}
