package libs.aqz.volume;

import libs.aqz.AQZ;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;

import java.nio.charset.StandardCharsets;

public class AQZArquivoExternamente {


    private String mArquivo;

    private long mInode;
    private String mNome;
    private long mTamanho;
    private Lista<Long> mInodes;

    public AQZArquivoExternamente(String eArquivo, long eInode) {
        mArquivo = eArquivo;
        mInode = eInode;
        mNome = "";
        mTamanho = 0;
        mInodes = new Lista<Long>();
    }

    public void atualizar() {

        Arquivador mArquivador = new Arquivador(mArquivo);

        mArquivador.setPonteiro(mInode);

        mInodes.limpar();
        mInodes.adicionar(mInode);


        int inode_status = mArquivador.get_u8();
        long inode_proximo = mArquivador.get_u64();

        mArquivador.setPonteiro(mInode + 9L);

        int nome_tamanho = mArquivador.get_u32();

        if (nome_tamanho > 1024) {
            nome_tamanho = 1024;
        }

        byte[] nome_bytes = mArquivador.get_u8_array(nome_tamanho);


        mArquivador.setPonteiro(mInode + 2000);
        long inode_primario_tamanho = (long) mArquivador.get_u32();

        if (inode_primario_tamanho > AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO) {
            inode_primario_tamanho = AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO;
        }

        // e_arquivo.at("INode.Status", inode_status);
        // e_arquivo.at("Nome.Tamanho", nome_tamanho);
        //  e_arquivo.at("Nome", new String(nome_bytes, StandardCharsets.UTF_8));
        //  e_arquivo.at("INodeDadosTamanho", inode_primario_tamanho);
        //  e_arquivo.at("ProximoINode", inode_proximo);

        mNome = new String(nome_bytes, StandardCharsets.UTF_8);
        int blocos_quantidade = 1;
        long arquivo_tamanho = (long) inode_primario_tamanho;


        while (inode_proximo > 0) {

            long inode_aqui = inode_proximo;
            mInodes.adicionar(inode_aqui);

            mArquivador.setPonteiro(inode_proximo);
            inode_status = mArquivador.get_u8();
            inode_proximo = mArquivador.get_u64();

            mArquivador.setPonteiro(inode_aqui + 2000);
            long inode_tamanho = (long) mArquivador.get_u32();

            if (inode_tamanho > AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO) {
                inode_tamanho = AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO;
            }

            arquivo_tamanho += inode_tamanho;

            // fmt.print("Passando em inode {} ->> {}", inode_aqui, inode_tamanho);

            blocos_quantidade += 1;
        }

        mTamanho = arquivo_tamanho;

        mArquivador.encerrar();
    }


    public String getNome() {
        return mNome;
    }

    public long getTamanho() {
        return mTamanho;
    }

    public int getInodesQuantidade() {
        return mInodes.getQuantidade();
    }


    public byte[] getBytes() {

        byte[] bytes = new byte[(int) getTamanho()];

        Arquivador mArquivador = new Arquivador(mArquivo);

        mArquivador.setPonteiro(mInode);

        int i = 0;


        for (Long inode : mInodes) {

            //   fmt.print("Abrindo inode : {}",inode);

            mArquivador.setPonteiro(inode + 2000);
            int bloco_tamanho = mArquivador.get_u32();

            if (bloco_tamanho > AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO) {
                bloco_tamanho = AZVolumeInternamente.VOLUME_INODE_DADOS_TAMANHO;
            }

            mArquivador.setPonteiro(inode + 2020);
            byte[] bloco_dados = mArquivador.get_u8_array(bloco_tamanho);

            long o = (long) i + (long) bloco_tamanho;

            //   fmt.print("\t {} - {} :: {} -> Tamanho {} ",i,o,o-i,bloco_tamanho);

            int tt = i;
            int a = 0;
            for (long u = i; u < o; u++) {
                bytes[i] = bloco_dados[a];
                a += 1;
                i += 1;
            }

            // fmt.print("\t B :: {} - {} - {}", Inteiro.byteToInt(bytes[tt]),Inteiro.byteToInt(bytes[tt+1]),Inteiro.byteToInt(bytes[tt+1]));
            //  fmt.print("\t C :: {} - {} - {}", Inteiro.byteToInt(confere[tt]),Inteiro.byteToInt(confere[tt+1]),Inteiro.byteToInt(confere[tt+1]));


        }

        mArquivador.encerrar();

        return bytes;

    }


    public void remover() {

        atualizar();

        Lista<Entidade> volumes = AQZ.GET_VOLUMES(mArquivo);
        ENTT.EXIBIR_TABELA_COM_NOME(volumes, "@VOLUMES");

        Arquivador mArquivador = new Arquivador(mArquivo);

        mArquivador.setPonteiro(mInode);

        long bloco_tamanho = AZVolumeInternamente.VOLUME_INODE_TAMANHO;

        for (Long inode : mInodes) {

            boolean tem_vid = false;
            int volume_id = 0;

            long enc_volume_mapa_inicio = 0;
            long enc_volume_dados_inicio = 0;

            for (Entidade volume : volumes) {

                long volume_dados_inicio = volume.atLong("DadosInicio");
                long volume_dados_fim = volume.atLong("DadosFim");

                if (inode >= volume_dados_inicio && inode < volume_dados_fim) {
                    tem_vid = true;
                    volume_id = volume.atInt("VID");

                    enc_volume_mapa_inicio = volume.atLong("MapaInicio");
                    enc_volume_dados_inicio = volume_dados_inicio;
                    break;
                }

            }

            if (tem_vid) {
                long bloco_id = (inode - enc_volume_dados_inicio) / bloco_tamanho;
                fmt.print("Removendo INode : VID : {} - Bloco : {} -->> BlocoID : {}", volume_id, inode, bloco_id);

                mArquivador.setPonteiro(enc_volume_mapa_inicio + bloco_id);
                mArquivador.set_u8((byte) 0);

            }

        }


        mArquivador.encerrar();

    }
}
