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
    public final static int NAO_TEM = 0;
    public final static int ARMAZEM_FIM = 255;


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
                mArquivador.set_u8((byte) i);
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
            mArquivador.setPonteiro(4L + (i * (1024 + 4 + 8 + 2)));
            armazens.adicionar(new Armazem(this, mArquivador, i));
        }

        return armazens;
    }


    public Opcional<Armazem> alocar_armazem(String eNome) {

        for (Armazem armazem : getArmazens()) {
            if (armazem.isDisponivel()) {

                long ponteiro = armazem.getPonteiro();

                //  armazem.zerar();

                armazem.setStatus(ARMAZEM_JA_INICIADO_E_OCUPADO);
                armazem.setNome(eNome);
                armazem.setPonteiro(ponteiro);


                break;
            } else if (armazem.isNaoIniciado()) {

                mArquivador.ir_para_o_fim();

                long ponteiro = mArquivador.getPonteiro();

                mArquivador.set_u8((byte) armazem.getIndice());
                mArquivador.set_u8((byte) ARMAZEM_TIPO_ARMAZEM);
                mArquivador.set_u8((byte) NAO_TEM);
                mArquivador.set_u64((long) 0);
                mArquivador.set_u8((byte) NAO_TEM);


                for (int i = 0; i < Matematica.KB(64); i++) {
                    mArquivador.set_u64((long) NAO_TEM);
                }

                mArquivador.set_u8((byte) ARMAZEM_FIM);

                armazem.setStatus(ARMAZEM_JA_INICIADO_E_OCUPADO);
                armazem.setNome(eNome);
                armazem.setPonteiro(ponteiro);

                break;
            }
        }

        return Opcional.CANCEL();
    }

    public void desocupar_armazem(String eNome) {

        for (Armazem armazem : getArmazens()) {
            if (armazem.isOcupado()) {

                if (armazem.isNome(eNome)) {

                    //  armazem.zerar();

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
                e_armazem.at("Ponteiro", armazem.getPonteiro());
                e_armazem.at("Nome", armazem.getNome());
            } else if (armazem.isDisponivel()) {
                Entidade e_armazem = ENTT.CRIAR_EM(armazens, "ID", armazem.getIndice());
                e_armazem.at("Status", "Disponivel");
                e_armazem.at("Ponteiro", armazem.getPonteiro());
                e_armazem.at("Nome", "");
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

            mArquivador.setPonteiro(4L + (i * (1024 + 4 + 8 + 2)));

            int bloco_indice = mArquivador.get_u8();
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

}
