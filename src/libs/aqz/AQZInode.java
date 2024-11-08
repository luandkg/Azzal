package libs.aqz;

import libs.armazenador.Armazenador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Unico;
import libs.luan.fmt;

public class AQZInode {

    public static final boolean DEBUG = false;

    public static Lista<Long> INODE_GET_INODES(AZVolumeInternamente mAZVolumeInternamente, Armazenador mArmazenador, long mInode) {

        Unico<Long> mInodes = new Unico<Long>(Matematica.LONG_IGUALAVEL());


        mArmazenador.getArquivador().setPonteiro(mInode);

        mInodes.item(mInode);

        if (DEBUG) {
            fmt.print("Buscando iNode : {}", mInode);
        }

        int inode_status = mArmazenador.getArquivador().get_u8();
        long inode_proximo = mArmazenador.getArquivador().get_u64();

        mArmazenador.getArquivador().setPonteiro(mInode + 9L);

        int nome_tamanho = mArmazenador.getArquivador().get_u32();

        if (nome_tamanho >= 1024) {
            nome_tamanho = 1024;
        }

        //  byte[] nome_bytes = mArmazenador.getArquivador().get_u8_array(nome_tamanho);

        mArmazenador.getArquivador().setPonteiro(mInode + 2000);
        long inode_primario_tamanho = (long) mArmazenador.getArquivador().get_u32();


        // e_arquivo.at("INode.Status", inode_status);
        // e_arquivo.at("Nome.Tamanho", nome_tamanho);
        //  e_arquivo.at("Nome", new String(nome_bytes, StandardCharsets.UTF_8));
        //  e_arquivo.at("INodeDadosTamanho", inode_primario_tamanho);
        //  e_arquivo.at("ProximoINode", inode_proximo);

        // mNome = new String(nome_bytes, StandardCharsets.UTF_8);
        int blocos_quantidade = 1;
        long arquivo_tamanho = (long) inode_primario_tamanho;

        long ultima_posicao_valida = mArmazenador.getArquivador().getLength();


        while (inode_proximo > 0) {

            long inode_aqui = inode_proximo;

            if (DEBUG) {
                fmt.print("Buscando iNode : {}", inode_aqui);
            }

            if (inode_aqui >= ultima_posicao_valida) {
                break;
            }

            mInodes.item(inode_aqui);

            //   fmt.print("Novo inode :: {}",inode_aqui);

            mArmazenador.getArquivador().setPonteiro(inode_proximo);
            inode_status = mArmazenador.getArquivador().get_u8();
            inode_proximo = mArmazenador.getArquivador().get_u64();

            mArmazenador.getArquivador().setPonteiro(inode_aqui + 2000);
            long inode_tamanho = (long) mArmazenador.getArquivador().get_u32();
            arquivo_tamanho += inode_tamanho;

            // fmt.print("Passando em inode {} ->> {}", inode_aqui, inode_tamanho);

            blocos_quantidade += 1;
        }

        //   mTamanho = arquivo_tamanho;

        return mInodes.toLista();
    }


    public static void INODE_REMOVER(AZVolumeInternamente mAZVolumeInternamente, Armazenador mArmazenador, long mInode) {


        Lista<Entidade> volumes = mAZVolumeInternamente.volume_listar();

        if (DEBUG) {
            ENTT.EXIBIR_TABELA_COM_NOME(volumes, "@VOLUMES");
        }

        Lista<Long> mInodes = INODE_GET_INODES(mAZVolumeInternamente, mArmazenador, mInode);

        if (DEBUG) {
            fmt.print("Remover inodes : {}", mInodes.getQuantidade());
        }

        mArmazenador.getArquivador().setPonteiro(mInode);

        long bloco_tamanho = Matematica.KB(64);

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

                if (DEBUG) {
                    fmt.print("Removendo INode : VID : {} - Bloco : {} -->> BlocoID : {}", volume_id, inode, bloco_id);
                }

                mArmazenador.getArquivador().setPonteiro(enc_volume_mapa_inicio + bloco_id);
                mArmazenador.getArquivador().set_u8((byte) 0);

                if (DEBUG) {
                    mArmazenador.getArquivador().setPonteiro(enc_volume_dados_inicio + ((long) bloco_id * Matematica.KB(64)));
                    int status_bloco = mArmazenador.getArquivador().get_u8();
                    fmt.print("Status do bloco :: {}", status_bloco);
                }

                mArmazenador.getArquivador().setPonteiro(enc_volume_dados_inicio + ((long) bloco_id * Matematica.KB(64)));
                mArmazenador.getArquivador().set_u8((byte) 1);
                mArmazenador.getArquivador().set_u64(0);

            }

        }


    }


}
