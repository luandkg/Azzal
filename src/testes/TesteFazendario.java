package testes;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.binario.Arquivador;
import libs.fazendario.Armazem;
import libs.fazendario.Fazendario;
import libs.luan.Opcional;
import libs.luan.fmt;

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

            fmt.print("\t ++ Ponteiro Sumario  : {}", teste.getSumario());
            fmt.print("\t ++ Sumarios          : {}", teste.getSumariosContagem());
            fmt.print("\t ++ Andares           : {}", teste.getAndaresContagem());
            fmt.print("\t ++ Itens Alocados    : {}", teste.getItensAlocadosContagem());

            for(int a=0;a<300;a++){
                teste.item_adicionar("Luan Freitas :: "+a);
            }

            fmt.print("\t ++ Ponteiro Sumario  : {}", teste.getSumario());
            fmt.print("\t ++ Sumarios          : {}", teste.getSumariosContagem());
            fmt.print("\t ++ Andares           : {}", teste.getAndaresContagem());
            fmt.print("\t ++ Itens Alocados    : {}", teste.getItensAlocadosContagem());

        }


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
