package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Strings;
import libs.luan.fmt;

public class ArmazemAndar {

    private Arquivador mArquivador;
    private long mAndarPonteiro;

    public ArmazemAndar(Arquivador eArquivador, long eAndarPonteiro) {
        mArquivador = eArquivador;
        mAndarPonteiro = eAndarPonteiro;
        atualizar();
    }


    public void atualizar() {

        mArquivador.setPonteiro(mAndarPonteiro);

        int armazem_indice = mArquivador.get_u8();
        int armazem_tipo = mArquivador.get_u8();
        long andar_espacos_existentes = mArquivador.get_u64();
        long andar_espacos_ocupados = mArquivador.get_u64();
        long andar_proximo_espaco_vazio = mArquivador.get_u64();

        int armazem_vazio = mArquivador.get_u8();


    }

    public long getItensAlocadosContagem() {
        long ret = 0;

        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 1L);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_SETORES; i++) {

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_OCUPADO || tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                ret += 1;
            }
        }


        return ret;
    }

    public boolean temEspaco() {
        boolean ret = false;

        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 1L);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_SETORES; i++) {

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO || tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {
                ret = true;
                break;
            }
        }


        return ret;
    }

    public void item_adicionar(String texto) {


        mArquivador.setPonteiro(mAndarPonteiro + 1L + 1L + 8L + 8L + 8L + 1L);


        fmt.print("\t ++ Adicionar Item : Andar {} ", mAndarPonteiro);

        for (int i = 0; i < Fazendario.QUANTIDADE_DE_SETORES; i++) {

            long ponteiro_local = mArquivador.getPonteiro();

            long tem_ponteiro_espaco = mArquivador.get_u8();
            long ponteiro_espaco = mArquivador.get_u64();

            if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_NAO_ALOCADO) {

                mArquivador.ir_para_o_fim();
                long ponteiro_dados = mArquivador.getPonteiro();

                mArquivador.set_u8_em_bloco(Fazendario.TAMANHO_SETOR_ITEM, (byte) 0);

                mArquivador.setPonteiro(ponteiro_local);
                mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);
                mArquivador.set_u64(ponteiro_dados);

                mArquivador.setPonteiro(ponteiro_dados);
                mArquivador.set_u8_vector(Strings.GET_STRING_VIEW_BYTES(texto));

                fmt.print("\t ++ Alocando novo espaco : {}", ponteiro_dados);

                break;
            } else if (tem_ponteiro_espaco == Fazendario.ESPACO_VAZIO_E_JA_ALOCADO) {

                mArquivador.setPonteiro(ponteiro_local);
                mArquivador.set_u8((byte) Fazendario.ESPACO_OCUPADO);

                mArquivador.setPonteiro(ponteiro_espaco);
                mArquivador.set_u8_vector(Strings.GET_STRING_VIEW_BYTES(texto));

                fmt.print("\t ++ Utilizando espaco existente : {}", ponteiro_espaco);

                break;
            }
        }

    }

}
