package libs.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.zetta.ZettaPasta;

public class Bloco {

    private Arquivador mArquivador;
    private Fazendario mFazendario;

    private ZettaPasta mPasta;
    private long mPonteiro;

    public Bloco(Arquivador eArquivador,Fazendario eFazendario, ZettaPasta ePasta, long ePonteiro) {
        mArquivador = eArquivador;
        mFazendario=eFazendario;
        mPasta = ePasta;
        mPonteiro = ePonteiro;
    }

    public long getPonteiroDados() {
        Inode inode = mFazendario.getInode(mPonteiro);
        return inode.ponteiro_dados_aqui;
    }

    public Lista<Long> getInodes() {

        Inode inode = mFazendario.getInode(mPonteiro);

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

        Inode inode = mFazendario.getInode(mPonteiro);

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);

        int quantidade_inodes = mArquivador.get_u32();

        return quantidade_inodes;
    }

    public void adicionar_inode(long novo) {

        Inode inode = mFazendario.getInode(mPonteiro);

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);

        int quantidade_inodes = mArquivador.get_u32();

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui + 4L + (8L * quantidade_inodes));
        mArquivador.set_u64(novo);

        quantidade_inodes += 1;

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);
        mArquivador.set_u32(quantidade_inodes);


    }


    public void remover() {

        Inode inode = mFazendario.getInode(mPonteiro);

        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);

        int quantidade_inodes = mArquivador.get_u32();

        Lista<Long> inodes = getInodes();

        for (long inode_local : inodes) {
            Inode inode_corrente = mFazendario.getInode(inode_local);

            mArquivador.setPonteiro(inode_corrente.ponteiro_eu_mesmo);
            mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);
        }


        mArquivador.setPonteiro(inode.ponteiro_dados_aqui);
        mArquivador.set_u32(0);

        mArquivador.setPonteiro(inode.ponteiro_eu_mesmo);
        mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);

    }


}
