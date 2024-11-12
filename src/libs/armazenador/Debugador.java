package libs.armazenador;


import libs.aqz.colecao.AZInternamenteTX;
import libs.luan.fmt;

public class Debugador {

    public static void debug_colecionador(AZInternamenteTX BancoBS) {
        debug_momentum(BancoBS.getArmazenador());
    }

    public static void debug_colecionador_completo(AZInternamenteTX BancoBS) {

        Debugador.debug_colecionador(BancoBS);
        for (ParticaoMestre particaoEmExtincao : BancoBS.getArmazenador().getParticoes()) {
            Debugador.debug_banco(particaoEmExtincao);
        }

    }

    public static void debug_momentum(Armazenador armazenador) {

        System.out.println("\t - Momentum " + " :: { Local Bancos = " + armazenador.getLocalBancos() + " , Local Cache = " + armazenador.getGlobalCache() + " }");
        System.out.println("\t\t -->> Bancos                 = " + armazenador.getParticoes().getQuantidade());
        System.out.println("\t\t -->> Itens   em Cache       = " + armazenador.contagem_em_cache());

    }

    public static void debug_banco(ParticaoMestre particaoEmExtincao) {

        System.out.println("\t - Banco " + particaoEmExtincao.getID() + " -->> " + particaoEmExtincao.getNome() + " :: { Local Capitulos = " + particaoEmExtincao.getLocalCapitulos() + " , Local Cache = " + particaoEmExtincao.getLocalCache() + " , Local Indice = " + particaoEmExtincao.getLocalIndice() + " , Pagina Corrente = " + particaoEmExtincao.getPaginaCorrente() + " }");

        System.out.println("\t\t -->> Capitulos                  =  " + particaoEmExtincao.getCapitulosContagem());
        System.out.println("\t\t -->> Paginas Referenciadas      =  " + particaoEmExtincao.getPaginasTotalContagem());
        System.out.println("\t\t -->> Paginas Alocadas           =  " + particaoEmExtincao.getPaginasContagem());
        System.out.println("\t\t -->> Itens   Referenciados      =  " + particaoEmExtincao.getItensTotalContagem());
        System.out.println("\t\t -->> Itens   Alocados           =  " + particaoEmExtincao.getItensAlocadosContagem());
        System.out.println("\t\t -->> Itens   Utilizados         =  " + particaoEmExtincao.getItensContagem());
        System.out.println("\t\t -->> Itens   em Cache           =  " + particaoEmExtincao.getItensEmCacheContagem());


        System.out.println("\t\t -->> Usabilidade                =  " + particaoEmExtincao.getUsabilidade() + "%");
        System.out.println("\t\t -->> Disponibilidade            =  " + particaoEmExtincao.getDisponibilidade() + "%");

        System.out.println("\t\t -->> Tamanho Cheio              =  " + fmt.espacar_depois(String.valueOf(particaoEmExtincao.getTamanhoCheio()), 10) + " :: " + fmt.formatar_tamanho(particaoEmExtincao.getTamanhoCheio()));
        System.out.println("\t\t -->> Tamanho Alocado            =  " + fmt.espacar_depois(String.valueOf(particaoEmExtincao.getTamanho()), 10) + " :: " + fmt.formatar_tamanho(particaoEmExtincao.getTamanho()));
        System.out.println("\t\t -->> Tamanho Utilizado          =  " + fmt.espacar_depois(String.valueOf(particaoEmExtincao.getTamanhoUtilizado()), 10) + " :: " + fmt.formatar_tamanho(particaoEmExtincao.getTamanhoUtilizado()));


        System.out.println("\t\t -->> Indice  Paginas            =  " + particaoEmExtincao.getIndicePaginasContagem());
        System.out.println("\t\t -->> Indice  Itens Cheio        =  " + particaoEmExtincao.getIndiceItensTotalContagem());
        System.out.println("\t\t -->> Indice  Itens Utilizado    =  " + particaoEmExtincao.getIndiceItensUtilizadoContagem());

        System.out.println("\t\t -->> Indice Tamanho Cheio       =  " + fmt.espacar_depois(String.valueOf(particaoEmExtincao.getIndiceTamanhoCheio()), 10) + " :: " + fmt.formatar_tamanho(particaoEmExtincao.getIndiceTamanhoCheio()));
        System.out.println("\t\t -->> Indice Tamanho Utilizado   =  " + fmt.espacar_depois(String.valueOf(particaoEmExtincao.getIndiceTamanhoUtilizado()), 10) + " :: " + fmt.formatar_tamanho(particaoEmExtincao.getIndiceTamanhoUtilizado()));

        System.out.println("\t\t -->> Indice Usabilidade         =  " + particaoEmExtincao.getIndiceUsabilidade() + "%");
        System.out.println("\t\t -->> Indice Disponibilidade     =  " + particaoEmExtincao.getIndiceDisponibilidade() + "%");

    }

}
