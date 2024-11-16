package libs.fazendario;

import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class Fazendario {

    public final static int ARMAZEM_NAO_INICIADO = 0;
    public final static int ARMAZEM_JA_INICIADO_E_DISPONIVEL = 1;
    public final static int ARMAZEM_JA_INICIADO_E_OCUPADO = 2;


    public final static int ARMAZEM_TIPO_ARMAZEM = 53;
    public final static int ARMAZEM_TIPO_ANDAR = 55;
    public final static int ARMAZEM_TIPO_ZONA_DE_RECICLAGEM = 56;

    public final static int NAO_TEM = 0;
    public final static int ARMAZEM_FIM = 255;
    public final static int ANDAR_FIM = 255;

    public final static int ESTA_VAZIO = 0;
    public final static int TEM = 255;


    public final static int ESPACO_VAZIO_E_NAO_ALOCADO = 0;
    public final static int ESPACO_VAZIO_E_JA_ALOCADO = 1;
    public final static int ESPACO_OCUPADO = 2;


    public final static long QUANTIDADE_DE_ANDARES = 10;//Matematica.KB(64);
    public final static long QUANTIDADE_DE_ESPACOS = 20;//Matematica.KB(64);

    public final static long TAMANHO_SETOR_ITEM = Matematica.KB(10);


    private Arquivador mArquivador;

    public Fazendario(String eArquivo) {
        mArquivador = new Arquivador(eArquivo);

        long tamanho = mArquivador.getLength();

        if (tamanho == 0) {

            mArquivador.setPonteiro(0);
            mArquivador.set_u8((byte) TX.INDICE_DE_CARACTERE("A"));
            mArquivador.set_u8((byte) TX.INDICE_DE_CARACTERE("Z"));
            mArquivador.set_u8((byte) 0);
            mArquivador.set_u8((byte) 1);

            for (int i = 0; i < 256; i++) {
                mArquivador.set_u64((long) i);
                mArquivador.set_u8((byte) ARMAZEM_NAO_INICIADO);
                mArquivador.set_u64((byte) 0);
                mArquivador.set_u32((byte) 0);
                mArquivador.set_u8_em_bloco(1024, (byte) 0);
            }

        }

        mArquivador.setPonteiro(0);

        int formato_alfa = mArquivador.get_u8();
        int formato_beta = mArquivador.get_u8();
        int formato_v1 = mArquivador.get_u8();
        int formato_v2 = mArquivador.get_u8();

        String s_formato = TX.OBTER_CARACTERE(formato_alfa) + TX.OBTER_CARACTERE(formato_beta);
        String s_versao = formato_v1 + "." + formato_v2;

        VERIFICADOR.IGUALDADE(s_formato, "AZ");
        VERIFICADOR.IGUALDADE(s_versao, "0.1");

    }

    public void fechar() {
        mArquivador.encerrar();
    }


    public Lista<Armazem> getArmazens() {

        Lista<Armazem> armazens = new Lista<Armazem>();

        mArquivador.setPonteiro(4L);

        for (int i = 0; i < 256; i++) {
            mArquivador.setPonteiro(4L + (i * (8 + 1 + 8 + 4 + 1024)));
            armazens.adicionar(new Armazem(Armazem.ARMAZEM_TIPO_PRIMARIO, this, mArquivador, i));
        }

        return armazens;
    }


    public Opcional<Armazem> alocar_armazem(String eNome) {

        for (Armazem armazem : getArmazens()) {
            if (armazem.isDisponivel()) {

                long ponteiro = armazem.getPortao();

                //  armazem.zerar();

                armazem.setStatus(ARMAZEM_JA_INICIADO_E_OCUPADO);
                armazem.setNome(eNome);
                armazem.setPortao(ponteiro);


                break;
            } else if (armazem.isNaoIniciado()) {

                long ponteiro_portao = CRIAR_PORTAO(armazem.getIndice());

                armazem.setStatus(ARMAZEM_JA_INICIADO_E_OCUPADO);
                armazem.setNome(eNome);
                armazem.setPortao(ponteiro_portao);

                break;
            }
        }

        return Opcional.CANCEL();
    }

    public void desocupar_armazem(String eNome) {

        for (Armazem armazem : getArmazens()) {
            if (armazem.isOcupado()) {

                if (armazem.isNome(eNome)) {

                    armazem.zerar();

                    armazem.setStatus(ARMAZEM_JA_INICIADO_E_DISPONIVEL);
                    armazem.setNome("");

                    break;

                }

            }
        }


    }


    public boolean existe_armazem(String eNome) {

        for (Armazem armazem : getArmazens()) {
            if (armazem.isOcupado()) {
                if (armazem.isNome(eNome)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Opcional<Armazem> procurar_armazem(String eNome) {

        for (Armazem armazem : getArmazens()) {
            if (armazem.isOcupado()) {
                if (armazem.isNome(eNome)) {
                    return Opcional.OK(armazem);
                }
            }
        }

        return Opcional.CANCEL();
    }


    public void dump_armazens_existentes() {

        Lista<Entidade> armazens = ENTT.CRIAR_LISTA();

        for (Armazem armazem : getArmazens()) {
            if (armazem.isOcupado()) {
                Entidade e_armazem = ENTT.CRIAR_EM(armazens, "ID", armazem.getIndice());
                e_armazem.at("Status", "Ocupado");
                e_armazem.at("Portao", armazem.getPortao());
                e_armazem.at("Nome", armazem.getNome());
            } else if (armazem.isDisponivel()) {
                Entidade e_armazem = ENTT.CRIAR_EM(armazens, "ID", armazem.getIndice());
                e_armazem.at("Status", "Disponivel");
                e_armazem.at("Portao", armazem.getPortao());
                e_armazem.at("Nome", "");
            }
        }

        ENTT.EXIBIR_TABELA_COM_TITULO(armazens, "FAZENDARIO - ARMAZENS : OCUPADOS E DISPONIVEIS");
    }

    public void dump_armazens_existentes_completo() {

        Lista<Entidade> armazens = ENTT.CRIAR_LISTA();

        for (Armazem armazem : getArmazens()) {
            if (armazem.isOcupado()) {
                Entidade e_armazem = ENTT.CRIAR_EM(armazens, "ID", armazem.getIndice());
                e_armazem.at("Status", "Ocupado");
                e_armazem.at("Portao", armazem.getPortao());
                e_armazem.at("Nome", armazem.getNome());

                e_armazem.at("Portoes", armazem.getPortoesContagem());
                e_armazem.at("Andares", armazem.getAndaresContagem());
                e_armazem.at("Espacos", armazem.getEspacosContagem());

                e_armazem.at("Alocados", armazem.getItensAlocadosContagem());
                e_armazem.at("NaoAlocados", armazem.getItensNaoAlocadosContagem());

                e_armazem.at("Reciclaveis", armazem.getItensReciclaveisContagem());
                e_armazem.at("Utilizados", armazem.getItensUtilizadosContagem());

            } else if (armazem.isDisponivel()) {
                Entidade e_armazem = ENTT.CRIAR_EM(armazens, "ID", armazem.getIndice());
                e_armazem.at("Status", "Disponivel");
                e_armazem.at("Portao", armazem.getPortao());
                e_armazem.at("Nome", "");

                e_armazem.at("Portoes", armazem.getPortoesContagem());
                e_armazem.at("Andares", armazem.getAndaresContagem());
                e_armazem.at("Espacos", armazem.getEspacosContagem());

                e_armazem.at("Alocados", armazem.getItensAlocadosContagem());
                e_armazem.at("NaoAlocados", armazem.getItensNaoAlocadosContagem());

                e_armazem.at("Reciclaveis", armazem.getItensReciclaveisContagem());
                e_armazem.at("Utilizados", armazem.getItensUtilizadosContagem());

            }
        }

        ENTT.EXIBIR_TABELA_COM_TITULO(armazens, "FAZENDARIO - ARMAZENS : OCUPADOS E DISPONIVEIS");
    }

    public void dump_armazens() {

        mArquivador.setPonteiro(0);

        int formato_alfa = mArquivador.get_u8();
        int formato_beta = mArquivador.get_u8();
        int formato_v1 = mArquivador.get_u8();
        int formato_v2 = mArquivador.get_u8();

        fmt.print("Formato : {}", TX.OBTER_CARACTERE(formato_alfa) + TX.OBTER_CARACTERE(formato_beta));
        fmt.print("Versão  : {}", formato_v1 + "." + formato_v2);

        for (int i = 0; i < 256; i++) {

            mArquivador.setPonteiro(4L + (i * (1024 + 4 + 8 + 1 + 8)));

            long bloco_indice = mArquivador.get_u64();
            int bloco_status = mArquivador.get_u8();
            long bloco_local = mArquivador.get_u64();
            int bloco_nome_tamanho = mArquivador.get_u32();
            if (bloco_nome_tamanho > 1024) {
                bloco_nome_tamanho = 1024;
            }
            byte[] nome_bytes = mArquivador.get_u8_array(bloco_nome_tamanho);

            fmt.print("\t ++ Bloco : {} :: {} - {} : {}", bloco_indice, bloco_status, bloco_local, Strings.GET_STRING_VIEW(nome_bytes));

        }

    }


    public long CRIAR_ARMAZEM_LOCATARIO() {

        mArquivador.ir_para_o_fim();

        long ponteiro_armazem_locatario = mArquivador.getPonteiro();

        mArquivador.set_u64((long) ponteiro_armazem_locatario);
        mArquivador.set_u8((byte) ARMAZEM_NAO_INICIADO);
        mArquivador.set_u64((byte) 0);
        mArquivador.set_u32((byte) 0);
        mArquivador.set_u8_em_bloco(1024, (byte) 0);

        long ponteiro_portao = CRIAR_PORTAO(ponteiro_armazem_locatario);

        Armazem armazem = new Armazem(Armazem.ARMAZEM_TIPO_LOCATARIO, this, mArquivador, ponteiro_armazem_locatario);

        armazem.setStatus(ARMAZEM_JA_INICIADO_E_OCUPADO);
        armazem.setNome(String.valueOf(ponteiro_armazem_locatario));
        armazem.setPortao(ponteiro_portao);

        return ponteiro_armazem_locatario;
    }


    public long CRIAR_PORTAO(long indice) {

        mArquivador.ir_para_o_fim();

        long ponteiro = mArquivador.getPonteiro();

        mArquivador.set_u64(indice);     // Indice
        mArquivador.set_u8((byte) ARMAZEM_TIPO_ARMAZEM);    // Tipo para Armazem - Armazem Sumario
        mArquivador.set_u8((byte) NAO_TEM);                 // Tem outra página de Armazem Sumario
        mArquivador.set_u64((long) 0);                      // Ponteiro da proxima página Armazem Sumario
        mArquivador.set_u8((byte) NAO_TEM);


        for (int i = 0; i < QUANTIDADE_DE_ANDARES; i++) {
            mArquivador.set_u64((long) NAO_TEM);
        }

        mArquivador.set_u8((byte) ARMAZEM_FIM);

        return ponteiro;
    }

    public long CRIAR_ANDAR(long indice) {

        mArquivador.ir_para_o_fim();

        long ponteiro = mArquivador.getPonteiro();

        mArquivador.set_u64(indice);                  // Indice
        mArquivador.set_u8((byte) ARMAZEM_TIPO_ANDAR);     // Tipo para Armazem - Armazem Sumario
        mArquivador.set_u64((long) 0);                      // Espacos Existentes
        mArquivador.set_u64((long) 0);                      // Espacos Ocupados
        mArquivador.set_u64((long) 0);                      // Proximo espaco vazio
        mArquivador.set_u64((long) 0);                      // Area de Reciclagem de Espacos
        mArquivador.set_u8((byte) NAO_TEM);


        for (int i = 0; i < QUANTIDADE_DE_ESPACOS; i++) {
            mArquivador.set_u8((byte) NAO_TEM);
            mArquivador.set_u64((long) NAO_TEM);
        }

        mArquivador.set_u8((byte) ANDAR_FIM);

        return ponteiro;
    }

    public long CRIAR_ZONA_DE_RECICLAGEM(long indice) {

        mArquivador.ir_para_o_fim();

        long ponteiro = mArquivador.getPonteiro();

        mArquivador.set_u64(indice);                                // Indice
        mArquivador.set_u8((byte) ARMAZEM_TIPO_ZONA_DE_RECICLAGEM); // Tipo para Armazem - Armazem Sumario
        mArquivador.set_u64((long) 0);                              // Espacos Existentes
        mArquivador.set_u64((long) 0);                              // Espacos Ocupados
        mArquivador.set_u8((byte) NAO_TEM);


        for (int i = 0; i < QUANTIDADE_DE_ESPACOS; i++) {
            mArquivador.set_u64((long) NAO_TEM);
        }

        mArquivador.set_u8((byte) ANDAR_FIM);

        return ponteiro;
    }


    public Armazem OBTER_ARMAZEM_LOCATORIO(long ponteiro){
        return new Armazem(Armazem.ARMAZEM_TIPO_LOCATARIO, this, mArquivador,ponteiro );
    }
}
