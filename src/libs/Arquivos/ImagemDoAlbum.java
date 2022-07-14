package libs.Arquivos;

import libs.Arquivos.Binario.Arquivador;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagemDoAlbum {

    private String mArquivo;
    private String mNome;
    private long mInicio;
    private long mFim;

    public ImagemDoAlbum(String eArquivo,String eNome,long eInicio,long eFim) {
        mArquivo=eArquivo;
        mNome=eNome;
        mInicio=eInicio;
        mFim=eFim;
    }

    public String getNome(){return mNome;}
    public long getInicio(){return mInicio;}
    public long getFim(){return mFim;}

    public BufferedImage getImagem( ) {

        BufferedImage eImagem = null;

        try {

            System.out.println("Album AI - Iniciada");

            Arquivador arquivador = new Arquivador(mArquivo);

            arquivador.setPonteiro(getInicio());

            eImagem = IM.lerDoFluxo(arquivador);


            arquivador.fechar();

            System.out.println("Album AI - Terminada");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return eImagem;
    }

}
