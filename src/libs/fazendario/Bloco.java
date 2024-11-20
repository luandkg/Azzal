package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.zettaquorum.Silos;

public class Bloco {

    private Arquivador mArquivador;
    private Silos mSilos;
    private long mPonteiro;

    public Bloco(Arquivador eArquivador, Silos eSilos, long ePonteiro) {
        mArquivador = eArquivador;
        mSilos = eSilos;
        mPonteiro = ePonteiro;
    }

    public long getPonteiroDados() {
        Inode inode = mSilos.getInode(mPonteiro);
        return inode.ponteiro_dados_aqui;
    }

    public Lista<Long> getInodes() {

        Inode inode = mSilos.getInode(mPonteiro);

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);

        int quantidade_inodes = mArquivador.get_u32();
        Lista<Long> inodes = new Lista<Long>();

        if (quantidade_inodes > 0) {

            for (int i = 0; i < quantidade_inodes; i++) {
                long inode_ponteiro = mArquivador.get_u64();
                inodes.adicionar(inode_ponteiro);
            }

        }

        return inodes;
    }

    public int getInodesContagem() {

        Inode inode = mSilos.getInode(mPonteiro);

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);

        int quantidade_inodes = mArquivador.get_u32();

        return quantidade_inodes;
    }

    public void adicionar_inode(long novo) {

        Inode inode = mSilos.getInode(mPonteiro);

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);

        int quantidade_inodes = mArquivador.get_u32();

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui + 4L+(8L * quantidade_inodes));
        mArquivador.set_u64(novo);

        quantidade_inodes += 1;

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);
        mArquivador.set_u32(quantidade_inodes);


    }


}
