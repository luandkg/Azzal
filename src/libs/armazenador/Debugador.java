package libs.armazenador;


import libs.aqz.BancoBS;
import libs.luan.fmt;

public class Debugador {

    public static void debug_colecionador(BancoBS BancoBS) {
        debug_momentum(BancoBS.getMomentum());
    }

    public static void debug_colecionador_completo(BancoBS BancoBS) {

        Debugador.debug_colecionador(BancoBS);
        for (ParticaoPrimaria particaoPrimaria : BancoBS.getMomentum().getBancos()) {
            Debugador.debug_banco(particaoPrimaria);
        }

    }

    public static void debug_momentum(Armazenador armazenador) {

        System.out.println("\t - Momentum " + " :: { Local Bancos = " + armazenador.getLocalBancos() + " , Local Cache = " + armazenador.getGlobalCache() + " }");
        System.out.println("\t\t -->> Bancos                 = " + armazenador.getBancos().getQuantidade());
        System.out.println("\t\t -->> Itens   em Cache       = " + armazenador.contagem_em_cache());

    }

    public static void debug_banco(ParticaoPrimaria particaoPrimaria) {

        System.out.println("\t - Banco " + particaoPrimaria.getID() + " -->> " + particaoPrimaria.getNome() + " :: { Local Capitulos = " + particaoPrimaria.getLocalCapitulos() + " , Local Cache = " + particaoPrimaria.getLocalCache() + " , Local Indice = " + particaoPrimaria.getLocalIndice() + " , Pagina Corrente = " + particaoPrimaria.getPaginaCorrente() + " }");

        System.out.println("\t\t -->> Capitulos                  =  " + particaoPrimaria.getCapitulosContagem());
        System.out.println("\t\t -->> Paginas Referenciadas      =  " + particaoPrimaria.getPaginasTotalContagem());
        System.out.println("\t\t -->> Paginas Alocadas           =  " + particaoPrimaria.getPaginasContagem());
        System.out.println("\t\t -->> Itens   Referenciados      =  " + particaoPrimaria.getItensTotalContagem());
        System.out.println("\t\t -->> Itens   Alocados           =  " + particaoPrimaria.getItensAlocadosContagem());
        System.out.println("\t\t -->> Itens   Utilizados         =  " + particaoPrimaria.getItensContagem());
        System.out.println("\t\t -->> Itens   em Cache           =  " + particaoPrimaria.getItensEmCacheContagem());


        System.out.println("\t\t -->> Usabilidade                =  " + particaoPrimaria.getUsabilidade() + "%");
        System.out.println("\t\t -->> Disponibilidade            =  " + particaoPrimaria.getDisponibilidade() + "%");

        System.out.println("\t\t -->> Tamanho Cheio              =  " + fmt.espacar_depois(String.valueOf(particaoPrimaria.getTamanhoCheio()), 10) + " :: " + fmt.formatar_tamanho(particaoPrimaria.getTamanhoCheio()));
        System.out.println("\t\t -->> Tamanho Alocado            =  " + fmt.espacar_depois(String.valueOf(particaoPrimaria.getTamanho()), 10) + " :: " + fmt.formatar_tamanho(particaoPrimaria.getTamanho()));
        System.out.println("\t\t -->> Tamanho Utilizado          =  " + fmt.espacar_depois(String.valueOf(particaoPrimaria.getTamanhoUtilizado()), 10) + " :: " + fmt.formatar_tamanho(particaoPrimaria.getTamanhoUtilizado()));


        System.out.println("\t\t -->> Indice  Paginas            =  " + particaoPrimaria.getIndicePaginasContagem());
        System.out.println("\t\t -->> Indice  Itens Cheio        =  " + particaoPrimaria.getIndiceItensTotalContagem());
        System.out.println("\t\t -->> Indice  Itens Utilizado    =  " + particaoPrimaria.getIndiceItensUtilizadoContagem());

        System.out.println("\t\t -->> Indice Tamanho Cheio       =  " + fmt.espacar_depois(String.valueOf(particaoPrimaria.getIndiceTamanhoCheio()), 10) + " :: " + fmt.formatar_tamanho(particaoPrimaria.getIndiceTamanhoCheio()));
        System.out.println("\t\t -->> Indice Tamanho Utilizado   =  " + fmt.espacar_depois(String.valueOf(particaoPrimaria.getIndiceTamanhoUtilizado()), 10) + " :: " + fmt.formatar_tamanho(particaoPrimaria.getIndiceTamanhoUtilizado()));

        System.out.println("\t\t -->> Indice Usabilidade         =  " + particaoPrimaria.getIndiceUsabilidade() + "%");
        System.out.println("\t\t -->> Indice Disponibilidade     =  " + particaoPrimaria.getIndiceDisponibilidade() + "%");

    }

}
