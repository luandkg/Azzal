package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.Fazendario;
import libs.fazendario.ItemAlocado;
import libs.luan.*;
import libs.tronarko.Tronarko;
import libs.zettaquorum.ArmazemPrimario;
import libs.zettaquorum.ZettaArquivo;

public class TesteFazendario {

    public static void init() {

        fmt.print("----------------- FAZENDARIO ------------------");

        String arquivo_fazenda = "/home/luan/assets/teste_fazendas/fazenda_alfa.az";

        Arquivador.remover(arquivo_fazenda);

        Fazendario fazenda = new Fazendario(arquivo_fazenda);

        fazenda.dump_armazens();

        fmt.print("---------------------------- AÇÃO 1 ----------------------");
        teste_alocar(fazenda, "@Teste");

        fmt.print("---------------------------- AÇÃO 2 ----------------------");
        teste_alocar(fazenda, "@Fundamento");

        fmt.print("---------------------------- AÇÃO 3 ----------------------");
        teste_alocar(fazenda, "@Capital");

        fmt.print("---------------------------- AÇÃO 4 ----------------------");
        teste_desocupar(fazenda, "@Capital");

        fmt.print("---------------------------- AÇÃO 5 ----------------------");
        teste_alocar(fazenda, "@Areas");

        fmt.print("---------------------------- AÇÃO 6 ----------------------");
        teste_alocar(fazenda, "@Montanhas");

        fmt.print("---------------------------- AÇÃO 7 ----------------------");
        teste_alocar(fazenda, "@Rios");

        fmt.print("---------------------------- AÇÃO 8 ----------------------");
        teste_desocupar(fazenda, "@Fundamento");

        fazenda.dump_armazens_existentes();

        fmt.print("---------------------------- AÇÃO 9 ----------------------");

        Opcional<Armazem> armazem_teste = fazenda.procurar_armazem("@Teste");
        if (armazem_teste.isOK()) {

            Armazem teste = armazem_teste.get();

            fmt.print("\t ++ Ponteiro Sumario  : {}", teste.getPortao());
            fmt.print("\t ++ Portoes           : {}", teste.getPortoesContagem());
            fmt.print("\t ++ Andares           : {}", teste.getAndaresContagem());
            fmt.print("\t ++ Itens Alocados    : {}", teste.getItensAlocadosContagem());

            for (int a = 0; a < 20; a++) {
                teste.item_adicionar("Luan Freitas :: " + a + " -- " + Tronarko.getTronAgora().getTextoZerado() + " :: " + Tronarko.getTozte().getHiperarko_Status().toString());
            }

            fmt.print("\t ++ Ponteiro Sumario  : {}", teste.getPortao());
            fmt.print("\t ++ Portoes           : {}", teste.getPortoesContagem());
            fmt.print("\t ++ Andares           : {}", teste.getAndaresContagem());
            fmt.print("\t ++ Itens Alocados    : {}", teste.getItensAlocadosContagem());

            for (int a = 0; a < 30; a++) {
                teste.item_adicionar("Indo sequencialmente :: " + a + " -- " + Tronarko.getTronAgora().getTextoZerado() + " :: " + Tronarko.getTozte().getHiperarko_Status().toString());
            }

            fmt.print("\t ++ Ponteiro Sumario  : {}", teste.getPortao());
            fmt.print("\t ++ Portoes           : {}", teste.getPortoesContagem());
            fmt.print("\t ++ Andares           : {}", teste.getAndaresContagem());
            fmt.print("\t ++ Itens Alocados    : {}", teste.getItensAlocadosContagem());

            int ii = 0;
            int aa = 0;
            int removidos = 0;

            for (ItemAlocado alocado : teste.getItensAlocados()) {
                fmt.print("\t ++ Item Alocado :: <{}>  {} = {} -- {} = {}", aa, alocado.getPonteiroStatus(), alocado.getStatus(), alocado.getPonteiroDados(), alocado.lerTextoUTF8());
                if (ii == 5) {
                    ii = 0;
                    fmt.print("-- Remover item :: {}", alocado.getPonteiroStatus());
                    alocado.remover();
                    removidos += 1;
                }
                ii += 1;
                aa += 1;
            }

            fmt.print("Removidos = {}", removidos);

            fmt.print("----- Segunda Listagem ---------");
            aa = 0;
            for (ItemAlocado alocado : teste.getItensAlocados()) {
                fmt.print("\t ++ Item Alocado :: <{}>  {} = {} -- {} = {}", aa, alocado.getPonteiroStatus(), alocado.getStatus(), alocado.getPonteiroDados(), alocado.lerTextoUTF8());
                aa += 1;
            }


            for (int i = 0; i < 10; i++) {
                teste.item_adicionar("Vamos além");
            }

            fmt.print("----- Terceira Listagem ---------");
            aa = 0;
            for (ItemAlocado alocado : teste.getItensAlocados()) {
                fmt.print("\t ++ Item Alocado :: <{}>  {} = {} -- {} = {}", aa, alocado.getPonteiroStatus(), alocado.getStatus(), alocado.getPonteiroDados(), alocado.lerTextoUTF8());
                aa += 1;
            }
        }


        fazenda.dump_armazens_existentes();
        fazenda.dump_armazens_existentes_completo();

        fazenda.fechar();

    }

    public static void teste_alta_pressao() {

        int repetir = Aleatorio.aleatorio_entre(10, 20);

        for (int a = 0; a < repetir; a++) {
            teste_pressao();
        }

/*
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
|                                                            FAZENDARIO - ARMAZENS : OCUPADOS E DISPONIVEIS                                                            |
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
|ID      |Status       |Portao        |Nome              |Portoes      |Andares      |Espacos      |Alocados      |NaoAlocados      |Reciclaveis      |Utilizados      |
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
|0       |Ocupado      |265732        |@Pressao::12      |3            |21           |600          |408           |12               |377              |31              |
|1       |Ocupado      |1548597       |@Pressao::11      |3            |28           |600          |541           |19               |115              |426             |
|2       |Ocupado      |1559326       |@Pressao::18      |3            |23           |600          |452           |8                |345              |107             |
|3       |Ocupado      |2739395       |@Pressao::16      |2            |13           |400          |248           |12               |81               |167             |
|4       |Ocupado      |3817064       |@Pressao::15      |2            |16           |400          |312           |8                |0                |312             |
|5       |Ocupado      |4669150       |@Pressao::19      |5            |47           |1000         |928           |12               |123              |805             |
|6       |Ocupado      |4679879       |@Pressao::17      |3            |27           |600          |536           |4                |0                |536             |
|7       |Ocupado      |8783985       |@Pressao::10      |3            |22           |600          |434           |6                |0                |434             |
|8       |Ocupado      |11934094      |@Pressao::14      |6            |55           |1200         |1091          |9                |0                |1091            |
|9       |Ocupado      |14283503      |@Pressao::13      |4            |34           |800          |671           |9                |0                |671             |
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

                */

    }

    public static void teste_pressao() {

        fmt.print("----------------- FAZENDARIO ------------------");

        String arquivo_fazenda = "/home/luan/assets/teste_fazendas/fazenda_pressao.az";

        //  Arquivador.remover(arquivo_fazenda);

        boolean deve_adicionar = true;
        boolean deve_atualizar = true;

        boolean deve_remover = true;
        boolean deve_reciclar_tudo = true;


        Fazendario pressao = new Fazendario(arquivo_fazenda);


        String colecao_nome = "@Pressao::" + Aleatorio.aleatorio_entre(10, 20);


        if (!pressao.existe_armazem(colecao_nome)) {
            pressao.alocar_armazem(colecao_nome);
        }

        Armazem armazem_pressao = Opcional.SOME(pressao.procurar_armazem(colecao_nome));

        if (deve_adicionar) {

            if (Aleatorio.aleatorio(100) > 80) {
                armazem_pressao.item_adicionar(ENTT.TO_DOCUMENTO(ENTT.CRIAR("Texto", PessoaNomeadorDeAkkax.get(), "TDN", Tronarko.getTronAgora().getTextoZerado())));
            } else {
                for (int a = 0; a < Aleatorio.aleatorio_entre(100, 300); a++) {
                    armazem_pressao.item_adicionar(ENTT.CRIAR("Texto", PessoaNomeadorDeAkkax.get(), "TDN", Tronarko.getTronAgora().getTextoZerado()).toTexto());
                }
            }

        }


        long indice_de_exclusao = armazem_pressao.getItensAlocadosContagem() / 10;
        long indice_de_exclusao_metade = indice_de_exclusao / 2;

        if (indice_de_exclusao_metade > 0 && deve_remover) {

            int exclusao_inicio = (int) indice_de_exclusao_metade + Aleatorio.aleatorio_entre(10, 50);
            int exclusao_fim = exclusao_inicio + (int) (indice_de_exclusao_metade / 4) + Aleatorio.aleatorio_entre(10, 50);


            for (int a = 0; a < Aleatorio.aleatorio_entre(exclusao_inicio, exclusao_fim); a++) {

                int aa = Aleatorio.aleatorio_entre(30, 50);
                int ii = 0;

                for (ItemAlocado item : armazem_pressao.getItensAlocados()) {
                    if (ii >= aa) {
                        item.remover();
                        break;
                    }
                    ii += 1;
                }
            }

        }

        if (Aleatorio.aleatorio(100) > 80 && deve_remover) {

            int ii = 0;
            int aa = Aleatorio.aleatorio_entre(100, 200);

            for (ItemAlocado item : armazem_pressao.getItensAlocados()) {
                if (ii < aa) {
                    item.remover();
                }
                ii += 1;
            }

        }

        if (Aleatorio.aleatorio(100) > 50 && deve_atualizar) {

            int ii = 0;
            int aa = Aleatorio.aleatorio_entre(100, 200);

            for (ItemAlocado item : armazem_pressao.getItensAlocados()) {
                if (ii < aa) {

                    Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
                    e.at("ADS", Tronarko.getTronAgora().getTextoZerado());
                    e.at("Valor", e.atIntOuPadrao("Valor", 0) + 1);

                    item.atualizarUTF8(ENTT.TO_DOCUMENTO(e));

                }
                ii += 1;
            }

        }


        if (Aleatorio.aleatorio(100) > 80) {
            //   fmt.print("!! Zerando : {}", colecao_nome);
            // armazem_pressao.zerar();
        }

        if (Aleatorio.aleatorio(100) > 90 && deve_reciclar_tudo) {
            for (ItemAlocado item : armazem_pressao.getItensAlocados()) {
                item.remover();
            }
        }


        fmt.print("!! Modificado : {}", colecao_nome);

        pressao.dump_armazens_existentes_completo();


        pressao.fechar();
    }


    public static void teste_alocar(Fazendario fazenda, String nome) {

        if (!fazenda.existe_armazem(nome)) {
            fazenda.alocar_armazem(nome);
        }

        Opcional<Armazem> armazem_teste = fazenda.procurar_armazem(nome);

        if (armazem_teste.isOK()) {

        } else {
            VERIFICADOR.IGUALDADE(armazem_teste.isOK(), true);
        }

        fazenda.dump_armazens();

    }

    public static void teste_desocupar(Fazendario fazenda, String nome) {

        if (fazenda.existe_armazem(nome)) {
            fazenda.desocupar_armazem(nome);
        }


        fazenda.dump_armazens();

    }


    public static void teste_2() {

        String arquivo_fazenda = "/home/luan/assets/teste_fazendas/fazenda_alfa.az";

        //Arquivador.remover(arquivo_fazenda);

        Fazendario fazenda = new Fazendario(arquivo_fazenda);

        ArmazemPrimario valores = new ArmazemPrimario(fazenda, "@Valores");

        // valores.zerar();


        valores.adicionar(ENTT.CRIAR("Tron", Tronarko.getTronAgora().getTextoZerado()));

        valores.dump();

        fazenda.fechar();
    }

    public static void teste_objetos_grandes() {

        String arquivo_fazenda = "/home/luan/assets/teste_fazendas/zeta.az";

        // Arquivador.remover(arquivo_fazenda);

        Fazendario fazenda = new Fazendario(arquivo_fazenda);

        ArmazemPrimario valores = new ArmazemPrimario(fazenda, "@Valores");

        //  valores.zerar();


        Entidade grandemente = new Entidade();

        int max_at = Aleatorio.aleatorio_entre(500, 800);


        if (Aleatorio.aleatorio(100) > 80) {
            max_at = 3;
        }


        grandemente.at("Max", max_at);
        grandemente.at("Status", "Alfa");

        for (int a = 0; a <= max_at; a++) {
            grandemente.at("Valor" + a, "Entao deu : " + (a * 5000));
            grandemente.at("Tempo" + a, Tronarko.getTronAgora().getTextoZerado());
        }

        String conteudo = ENTT.TO_DOCUMENTO(grandemente);

        fmt.print("Tamanho :: {}", fmt.formatar_tamanho(Strings.GET_STRING_VIEW_BYTES(conteudo).length));

        valores.adicionar(grandemente);


        for (ItemAlocado item : valores.getItensAlocados()) {
            Entidade aa = new Entidade();
            aa.at("Oiee", Tronarko.getTronAgora().getTextoZerado());


            max_at = Aleatorio.aleatorio_entre(500, 800);

            aa.at("Max", max_at);

            for (int a = 0; a <= max_at; a++) {
                aa.at("Valor" + a, "Entao deu : " + (a * 5000));
                aa.at("Tempo" + a, Tronarko.getTronAgora().getTextoZerado());
            }


            item.atualizarUTF8(ENTT.TO_DOCUMENTO(aa));
        }


        valores.dump();

        valores.dump_plantacoes();

        fazenda.fechar();
    }


}
