package libs.aqz.volume;

import libs.armazenador.Armazenador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;

public class AQZVolumes {

    public static Lista<Entidade> GET_VOLUMES(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Lista<Entidade> e_volumes = aqz.volume_listar();
        aqz.fechar();

        return e_volumes;
    }

    public static void CRIAR_VOLUME(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        aqz.volume_criar();
        aqz.fechar();

    }

    public static Lista<Entidade> GET_VOLUMES_DADOS(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Lista<Entidade> e_volumes = aqz.volume_listar_dados();
        aqz.fechar();

        return e_volumes;
    }

    public static void VOLUMES_ZERAR(String arquivo_banco) {

        Armazenador mArmazenador = new Armazenador(arquivo_banco);
        AZVolumeInternamente aqz = new AZVolumeInternamente(mArmazenador);
        aqz.volumes_zerar();


        mArmazenador.fechar();

    }

    public static boolean TEM_BLOCO_DISPONIVEL(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        boolean tem = aqz.volume_tem_bloco_disponivel();
        aqz.fechar();

        return tem;
    }

    public static Opcional<Long> ARQUIVO_ALOCAR(String arquivo_banco, String eArquivoNome, String eConteudo) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<Long> tem = aqz.arquivo_alocar(eArquivoNome, eConteudo);
        aqz.fechar();

        return tem;
    }

    public static Opcional<Long> ARQUIVO_ALOCAR(String arquivo_banco, String eArquivoNome, byte[] eConteudo) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<Long> tem = aqz.arquivo_alocar(eArquivoNome, eConteudo);
        aqz.fechar();

        return tem;
    }

    public static void ARQUIVO_DUMP(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        aqz.arquivos_dump();
        aqz.fechar();

    }

    public static Opcional<Entidade> ARQUIVO_PROCURAR(String arquivo_banco, String proc_arquivo_nome) {
        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<Entidade> op = aqz.procurar_arquivo(proc_arquivo_nome);
        aqz.fechar();

        return op;
    }

    public static Opcional<AQZArquivoExternamente> ARQUIVO_PROCURAR_EXTERNAMENTE(String arquivo_banco, String proc_arquivo_nome) {
        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<AQZArquivoExternamente> op = aqz.procurar_arquivo_externamente(proc_arquivo_nome);
        aqz.fechar();

        return op;
    }

    public static void EXIBIR_VOLUMES(String arquivo_banco) {
        Lista<Entidade> volumes_dados = GET_VOLUMES(arquivo_banco);
        ENTT.EXIBIR_TABELA_COM_NOME(volumes_dados, "@VOLUMES");
    }

    public static void EXIBIR_VOLUMES_DADOS(String arquivo_banco) {
        Lista<Entidade> volumes_dados = GET_VOLUMES_DADOS(arquivo_banco);
        ENTT.EXIBIR_TABELA_COM_NOME(volumes_dados, "@VOLUMES - DADOS");
    }

    public static void VOLUMES_DUMP(String arquivo_banco) {
        Lista<Entidade> volumes_dados = GET_VOLUMES_DADOS(arquivo_banco);
        ENTT.EXIBIR_TABELA_COM_NOME(volumes_dados, "@VOLUMES");
    }


    public static long VOLUME_BLOCOS_LIVRES(String arquivo_banco) {
        Lista<Entidade> volumes_dados = GET_VOLUMES_DADOS(arquivo_banco);
        return ENTT.ATRIBUTO_LONG_SOMAR(volumes_dados, "Objetos.Livre");
    }

    public static void VOLUME_ANALISAR_INTEGRIDADE(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        aqz.analisar_integridade();
        aqz.fechar();

    }

}
