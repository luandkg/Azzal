package libs.aqz;

import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.fmt;

import java.nio.charset.StandardCharsets;

public class AQZArquivoInternamente {


    private Arquivador mArquivador;
    private long mInode;
    private String mNome;
    private long mTamanho;
    private Lista<Long> mInodes;

    public AQZArquivoInternamente(Arquivador eArquivador, long eInode) {
        mArquivador = eArquivador;
        mInode = eInode;
        mNome = "";
        mTamanho = 0;
        mInodes = new Lista<Long>();
    }

    public void atualizar() {

        mArquivador.setPonteiro(mInode);

        mInodes.limpar();
        mInodes.adicionar(mInode);


        int inode_status = mArquivador.get_u8();
        long inode_proximo = mArquivador.get_u64();

        mArquivador.setPonteiro(mInode + 9L);

        int nome_tamanho = mArquivador.get_u32();
        byte[] nome_bytes = mArquivador.get_u8_array(nome_tamanho);

        mArquivador.setPonteiro(mInode + 2000);
        long inode_primario_tamanho = (long) mArquivador.get_u32();


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
            arquivo_tamanho += inode_tamanho;

            // fmt.print("Passando em inode {} ->> {}", inode_aqui, inode_tamanho);

            blocos_quantidade += 1;
        }

        mTamanho = arquivo_tamanho;

    }

    public void atualizar_nome(){
        mArquivador.setPonteiro(mInode);

        mInodes.limpar();
        mInodes.adicionar(mInode);


        int inode_status = mArquivador.get_u8();
        long inode_proximo = mArquivador.get_u64();

        mArquivador.setPonteiro(mInode + 9L);

        int nome_tamanho = mArquivador.get_u32();
        byte[] nome_bytes = mArquivador.get_u8_array(nome_tamanho);

        mArquivador.setPonteiro(mInode + 2000);
        long inode_primario_tamanho = (long) mArquivador.get_u32();


        // e_arquivo.at("INode.Status", inode_status);
        // e_arquivo.at("Nome.Tamanho", nome_tamanho);
        //  e_arquivo.at("Nome", new String(nome_bytes, StandardCharsets.UTF_8));
        //  e_arquivo.at("INodeDadosTamanho", inode_primario_tamanho);
        //  e_arquivo.at("ProximoINode", inode_proximo);

        mNome = new String(nome_bytes, StandardCharsets.UTF_8);
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

    public Lista<Long> getINodes(){return mInodes;}


    public byte[] getBytes() {

        byte[] bytes = new byte[(int) getTamanho()];


        mArquivador.setPonteiro(mInode);

        int i = 0;


        for (Long inode : mInodes) {

            //   fmt.print("Abrindo inode : {}",inode);

            mArquivador.setPonteiro(inode + 2000);
            int bloco_tamanho = mArquivador.get_u32();

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


        return bytes;

    }

}
