package libs.armazenador;


import libs.az.AZColecionador;
import libs.luan.fmt;

public class Debugador {

    public static void debug_colecionador(AZColecionador AZColecionador) {
        debug_momentum(AZColecionador.getMomentum());
    }

    public static void debug_colecionador_completo(AZColecionador AZColecionador) {

        Debugador.debug_colecionador(AZColecionador);
        for (Banco banco : AZColecionador.getMomentum().getBancos()) {
            Debugador.debug_banco(banco);
        }

    }

    public static void debug_momentum(Armazenador armazenador) {

        System.out.println("\t - Momentum " + " :: { Local Bancos = " + armazenador.getLocalBancos() + " , Local Cache = " + armazenador.getGlobalCache() + " }");
        System.out.println("\t\t -->> Bancos                 = " + armazenador.getBancos().getQuantidade());
        System.out.println("\t\t -->> Itens   em Cache       = " + armazenador.contagem_em_cache());

    }

    public static void debug_banco(Banco banco) {

        System.out.println("\t - Banco " + banco.getID() + " -->> " + banco.getNome() + " :: { Local Capitulos = " + banco.getLocalCapitulos() + " , Local Cache = " + banco.getLocalCache() + " , Local Indice = " + banco.getLocalIndice() + " , Pagina Corrente = " + banco.getPaginaCorrente() + " }");

        System.out.println("\t\t -->> Capitulos                  =  " + banco.getCapitulosContagem());
        System.out.println("\t\t -->> Paginas Referenciadas      =  " + banco.getPaginasTotalContagem());
        System.out.println("\t\t -->> Paginas Alocadas           =  " + banco.getPaginasContagem());
        System.out.println("\t\t -->> Itens   Referenciados      =  " + banco.getItensTotalContagem());
        System.out.println("\t\t -->> Itens   Alocados           =  " + banco.getItensAlocadosContagem());
        System.out.println("\t\t -->> Itens   Utilizados         =  " + banco.getItensContagem());
        System.out.println("\t\t -->> Itens   em Cache           =  " + banco.getItensEmCacheContagem());


        System.out.println("\t\t -->> Usabilidade                =  " + banco.getUsabilidade() + "%");
        System.out.println("\t\t -->> Disponibilidade            =  " + banco.getDisponibilidade() + "%");

        System.out.println("\t\t -->> Tamanho Cheio              =  " + fmt.espacar_depois(String.valueOf(banco.getTamanhoCheio()), 10) + " :: " + fmt.formatar_tamanho(banco.getTamanhoCheio()));
        System.out.println("\t\t -->> Tamanho Alocado            =  " + fmt.espacar_depois(String.valueOf(banco.getTamanho()), 10) + " :: " + fmt.formatar_tamanho(banco.getTamanho()));
        System.out.println("\t\t -->> Tamanho Utilizado          =  " + fmt.espacar_depois(String.valueOf(banco.getTamanhoUtilizado()), 10) + " :: " + fmt.formatar_tamanho(banco.getTamanhoUtilizado()));


        System.out.println("\t\t -->> Indice  Paginas            =  " + banco.getIndicePaginasContagem());
        System.out.println("\t\t -->> Indice  Itens Cheio        =  " + banco.getIndiceItensTotalContagem());
        System.out.println("\t\t -->> Indice  Itens Utilizado    =  " + banco.getIndiceItensUtilizadoContagem());

        System.out.println("\t\t -->> Indice Tamanho Cheio       =  " + fmt.espacar_depois(String.valueOf(banco.getIndiceTamanhoCheio()), 10) + " :: " + fmt.formatar_tamanho(banco.getIndiceTamanhoCheio()));
        System.out.println("\t\t -->> Indice Tamanho Utilizado   =  " + fmt.espacar_depois(String.valueOf(banco.getIndiceTamanhoUtilizado()), 10) + " :: " + fmt.formatar_tamanho(banco.getIndiceTamanhoUtilizado()));

        System.out.println("\t\t -->> Indice Usabilidade         =  " + banco.getIndiceUsabilidade() + "%");
        System.out.println("\t\t -->> Indice Disponibilidade     =  " + banco.getIndiceDisponibilidade() + "%");

    }

}
