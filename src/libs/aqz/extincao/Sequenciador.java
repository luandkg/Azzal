package libs.aqz.extincao;

import libs.armazenador.ParticaoEmExtincao;
import libs.dkg.DKG;
import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.Opcional;

public class Sequenciador {



    public static void criar_sequencial(ParticaoEmExtincao eParticaoEmExtincao, String eNome, int eInicio, int ePasso) {

        DKGObjeto objeto = new DKGObjeto("Sequencia");
        objeto.identifique("Nome").setValor(eNome);
        objeto.identifique("Sequencia").setInteiro(eInicio);
        objeto.identifique("Passo").setInteiro(ePasso);
        eParticaoEmExtincao.adicionar(objeto.toString());

    }

    public static Opcional<ItemDoBancoEmExtincao> procurar_sequencial(ParticaoEmExtincao eParticaoEmExtincao, String eNome) {

        Opcional<ItemDoBancoEmExtincao> ret = new Opcional<ItemDoBancoEmExtincao>();

        for (ItemDoBancoEmExtincao item : eParticaoEmExtincao.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Nome").isValor(eNome)) {
                ret.set(item);
                break;
            }
            //  System.out.println(objeto.toString());
        }

        return ret;
    }

    public static int aumentar_sequencial(ParticaoEmExtincao eParticaoEmExtincao, String eNome) {

        int chave = 0;

        for (ItemDoBancoEmExtincao item : eParticaoEmExtincao.getItens()) {
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

    public static void zerar_sequencial(ParticaoEmExtincao eParticaoEmExtincao, String eNome) {


        for (ItemDoBancoEmExtincao item : eParticaoEmExtincao.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (objeto.identifique("Nome").isValor(eNome)) {
                objeto.identifique("Sequencia").setInteiro(0);
                item.atualizar(objeto.toDocumento());
                break;
            }
        }

    }

    public static int sequencia(ItemDoBancoEmExtincao item) {
        DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
        return objeto.identifique("Sequencia").getInteiro(0);
    }


    public static void organizar_sequencial(ParticaoEmExtincao eParticaoEmExtincao, String eNome) {

        Opcional<ItemDoBancoEmExtincao> item_objeto_contador = procurar_sequencial(eParticaoEmExtincao, eNome);

        if (item_objeto_contador.temValor()) {
            sequencia(item_objeto_contador.get());
        } else {
            criar_sequencial(eParticaoEmExtincao, eNome, 0, 1);
        }


    }

    public static int get_sequencial_contador(ParticaoEmExtincao eParticaoEmExtincao, String eNome) {

        Opcional<ItemDoBancoEmExtincao> item_objeto_contador = procurar_sequencial(eParticaoEmExtincao, eNome);

        int ret = 0;

        if (item_objeto_contador.temValor()) {
            ret = sequencia(item_objeto_contador.get());
        } else {
            criar_sequencial(eParticaoEmExtincao, eNome, 0, 1);
            ret = 0;
        }

        return ret;
    }

    public static void DUMP(ParticaoEmExtincao eParticaoEmExtincao, String eNome) {

        Lista<DKGObjeto> objs = new Lista<DKGObjeto>();

        for (ItemDoBancoEmExtincao item : eParticaoEmExtincao.getItens()) {
            DKGObjeto objeto = DKG.PARSER_TO_OBJETO(item.lerTexto());
            objs.adicionar(objeto);
        }

        DKGFeatures.EXIBIR_TABELA(objs);

    }

}
