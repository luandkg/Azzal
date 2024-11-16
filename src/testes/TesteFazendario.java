package testes;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.fazendario.Armazem;
import libs.fazendario.Fazendario;
import libs.fazendario.ItemAlocado;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.tronarko.Tronarko;

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
                fmt.print("\t ++ Item Alocado :: <{}>  {} = {} -- {} = {}", aa, alocado.getPonteiroStatus(), alocado.getStatus(), alocado.getPonteiroDados(), alocado.getTextoUTF8());
                if (ii == 5) {
                    ii = 0;
                    fmt.print("-- Remover item :: {}", alocado.getPonteiroStatus());
                    alocado.remover();
                    removidos+=1;
                }
                ii += 1;
                aa += 1;
            }

            fmt.print("Removidos = {}", removidos);

            fmt.print("----- Segunda Listagem ---------");
            aa = 0;
            for (ItemAlocado alocado : teste.getItensAlocados()) {
                fmt.print("\t ++ Item Alocado :: <{}>  {} = {} -- {} = {}", aa, alocado.getPonteiroStatus(), alocado.getStatus(), alocado.getPonteiroDados(), alocado.getTextoUTF8());
                aa += 1;
            }


            for (int i = 0; i < 10; i++) {
                teste.item_adicionar("Vamos além");
            }

            fmt.print("----- Terceira Listagem ---------");
            aa = 0;
            for (ItemAlocado alocado : teste.getItensAlocados()) {
                fmt.print("\t ++ Item Alocado :: <{}>  {} = {} -- {} = {}", aa, alocado.getPonteiroStatus(), alocado.getStatus(), alocado.getPonteiroDados(), alocado.getTextoUTF8());
                aa += 1;
            }
        }


        fazenda.dump_armazens_existentes();
        fazenda.dump_armazens_existentes_completo();

        fazenda.fechar();

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

}
