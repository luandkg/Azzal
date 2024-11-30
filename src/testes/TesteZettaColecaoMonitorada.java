package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.TronarkoAleatorium;
import libs.zetta.ItemColecionavel;
import libs.zetta.OpcionalZ;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;
import libs.zetta.fazendario.IndiceLocalizado;
import libs.zetta_monitorum.ItemColecionavelMonitorum;
import libs.zetta_monitorum.ZettaColecaoMonitorum;
import libs.zetta_monitorum.ZettaQuorumMonitorum;

public class TesteZettaColecaoMonitorada {

    public static void init_grande_estresse() {

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        long contagem = 0;


        while (contagem < 10) {

            init();

            ZettaQuorumMonitorum zeta = new ZettaQuorumMonitorum(arquivo_zeta);
            ZettaColecaoMonitorum tronarkum = zeta.getColecaoSempre("@Tronarkum");

            contagem = tronarkum.contagem();

            fmt.print(">> Logs");
            zeta.logs_ver();

            zeta.fechar();

            fmt.print("++ Contagem : {}", contagem);


        }


    }

    public static void init() {

        fmt.print("----------------- ZETA QUORUM ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaQuorumMonitorum zeta = new ZettaQuorumMonitorum(arquivo_zeta);

        ZettaColecaoMonitorum tronarkum = zeta.getColecaoSempre("@Tronarkum");

        //tronarkum.zerar();

        TronarkoAleatorium ta = new TronarkoAleatorium(Tronarko.TOZTE_PRIMEIRO(6900), Tronarko.TOZTE_ULTIMO(7100));

        int adicionar_quantidade = Aleatorio.aleatorio_entre(5, 100);
        int remover_quantidade = Aleatorio.aleatorio_entre(10, 30);
        int atualizar_quantidade = Aleatorio.aleatorio_entre(10, 30);

        for (int a = 0; a < adicionar_quantidade; a++) {

            fmt.print(">> Inserindo : {}", a);

            Entidade item = new Entidade();
            item.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            item.at("Valor", PessoaNomeadorDeAkkax.get());
            item.at("TTS", ta.getTozte().getTextoZerado());

            tronarkum.adicionar(item);

        }


        int passando = Aleatorio.aleatorio_entre(10, 50);

        for (ItemColecionavelMonitorum item : tronarkum.getItensEditaveis()) {

            if (passando == 0) {
                fmt.print("-- Removendo item : {}", item.get().at("@ID"));

                passando = Aleatorio.aleatorio_entre(10, 50);
                remover_quantidade -= 1;
                item.remover();
            }

            if (remover_quantidade < 0) {
                break;
            }
            passando -= 1;
        }


        int atualizar_passando = Aleatorio.aleatorio_entre(10, 50);

        for (ItemColecionavelMonitorum item : tronarkum.getItensEditaveis()) {

            if (atualizar_passando == 0) {
                fmt.print("-- Atualizando item : {}", item.get().at("@ID"));

                atualizar_passando = Aleatorio.aleatorio_entre(10, 50);
                atualizar_quantidade -= 1;

                item.get().at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
                item.get().at("Atualizacoes", item.get().atIntOuPadrao("Atualizacoes", 0) + 1);


                Lista<Entidade> palavras = ENTT.CRIAR_LISTA();

                Lista<String> palavras_texto = new Lista<String>();
                for (String palavra : Strings.DIVIDIR_POR(item.get().at("Texto"), " ")) {
                    palavra = palavra.trim();
                    if (!palavra.isEmpty()) {
                        palavras_texto.adicionar(palavra);
                    }
                }

                for (String palavra : palavras_texto) {
                    Entidade e_palavra = ENTT.GET_SEMPRE(palavras, "Palavra", palavra);
                    e_palavra.at("Ranking", Strings.CONTAGEM_EM_LISTA(palavras_texto, palavra));
                }

                item.get().at("FTS", Strings.LINEARIZAR(ENTT.TO_DOCUMENTO(palavras)));
                item.atualizar();
            }

            if (atualizar_quantidade < 0) {
                break;
            }
            atualizar_passando -= 1;
        }


        tronarkum.exibir_colecao();
        // tronarkum.exibir_indice();

        fmt.print("Contagem     : {}", tronarkum.contagem());


        Opcional<IndiceLocalizado> op_maior = tronarkum.getIndiceMaior();

        fmt.print("Maior Indice : {}", OpcionalZ.OBTER_OU_ERRO(op_maior, "NÃO FOI POSSÍVEL ENCONTRAR O MAIOR INDICE !"));

        Opcional<IndiceLocalizado> op_indice = tronarkum.procurar_indice(5);

        fmt.print("Indice(5)    : {}", OpcionalZ.OBTER_PONTEIRO_OU_ERRO(op_indice, "NÃO FOI POSSÍVEL ENCONTRAR O PONTEIRO !"));

        Opcional<Entidade> op_indice_valor = tronarkum.procurar_item_por_indice(5);

        OpcionalZ.EXIBIR_ITEM_OU_ERRO(op_indice_valor, "Indice(5) -->> NÃO FOI POSSÍVEL ENCONTRAR O ITEM !");

        if (op_maior.isOK()) {

            Opcional<IndiceLocalizado> op_indice_ponteiro = tronarkum.procurar_indice(op_maior.get().getIndice());
            Opcional<Entidade> op_ultimo = tronarkum.procurar_item_por_indice(op_maior.get().getIndice());


            fmt.print("Indice(" + op_maior.get().getIndice() + ")    : {}", OpcionalZ.OBTER_PONTEIRO_OU_ERRO(op_indice_ponteiro, "NÃO FOI POSSÍVEL ENCONTRAR O PONTEIRO !"));
            OpcionalZ.EXIBIR_ITEM_OU_ERRO(op_ultimo, "Indice(" + op_maior.get().getIndice() + ") -->> NÃO FOI POSSÍVEL ENCONTRAR O ITEM !");

        }


        zeta.dump();

        fmt.print(">> Logs");
        zeta.logs_ver();

        zeta.fechar();
    }

}
