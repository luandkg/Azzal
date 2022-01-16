package Arquivos;

import Arquivos.Binario.Arquivador;
import Arquivos.Binario.Inteiro;
import Imaginador.Efeitos;
import Imaginador.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AI {

    private static int ALBUM_AI1 = 15;
    private static int ALBUM_AI2 = 30;
    private static int ALBUM_VERSAO_1 = 100;

    public static void criar(ArrayList<String> arquivos, String eArquivo) {

        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }

        try {

            System.out.println("Album AI - Iniciada");

            Arquivador arquivador = new Arquivador(eArquivo);

            arquivador.writeByte((byte) ALBUM_AI1);
            arquivador.writeByte((byte) ALBUM_AI2);

            arquivador.writeByte((byte) ALBUM_VERSAO_1);

            arquivador.writeInt(arquivos.size());


            TX eTX = new TX();

            ArrayList<Long> pt_inicios = new ArrayList<Long>();
            ArrayList<Long> pt_terminos = new ArrayList<Long>();

            ArrayList<Long> inicios = new ArrayList<Long>();
            ArrayList<Long> terminos = new ArrayList<Long>();

            long eCabecalho = arquivador.getPonteiro();

            for (String arquivo : arquivos) {

                String nome = new File(arquivo).getName();

                if (nome.contains(".png")) {
                    nome = nome.replace(".png", ".im");
                }
                if (nome.contains(".jpg")) {
                    nome = nome.replace(".jpg", ".im");
                }
                if (nome.contains(".jpeg")) {
                    nome = nome.replace(".jpeg", ".im");
                }
                if (nome.contains(".bmp")) {
                    nome = nome.replace(".bmp", ".im");
                }

                System.out.println("Cabecalho Arquivo :: " + arquivo);
                System.out.println("Cabecalho Arquivo :: " + nome);

                long pti = arquivador.getPonteiro();

                arquivador.writeByteRepetidos(100,(byte)0);


                long ptf = arquivador.getPonteiro();

                arquivador.setPonteiro(pti);

                ArrayList<Byte> dados = eTX.toListBytes(nome);

                if (dados.size() < 100) {
                    arquivador.writeByteArray(dados);
                } else {
                    throw new IllegalArgumentException("Tamanho de cabecalho invalido !");
                }

                arquivador.setPonteiro(ptf);

                pt_inicios.add(arquivador.getPonteiro());
                arquivador.writeLong((byte) 0);

                pt_terminos.add(arquivador.getPonteiro());
                arquivador.writeLong((byte) 0);

            }

            for (String arquivo : arquivos) {

                System.out.println("Imagem Arquivo :: " + arquivo);

                BufferedImage imagem = ImageUtils.getImagem(arquivo);

                if (imagem.getWidth() >= 2000 && imagem.getHeight() >= 2000) {
                    imagem = Efeitos.reduzir(imagem, imagem.getWidth() / 3, imagem.getHeight() / 3);
                }


                inicios.add(arquivador.getPonteiro());
                IM.salvar_bytes(imagem, arquivador);
                terminos.add(arquivador.getPonteiro());

            }

            for (int a=0;a<arquivos.size();a++) {

                arquivador.setPonteiro(pt_inicios.get(a));
                arquivador.writeLong(inicios.get(a));

                arquivador.setPonteiro(pt_terminos.get(a));
                arquivador.writeLong(terminos.get(a));

                System.out.println(" -->> ITEM " + a + " (" + inicios.get(a) + " :: " + terminos.get(a) + " ) -->> " + pt_inicios.get(a) + " _ " + pt_terminos.get(a));


            }

            arquivador.fechar();

            System.out.println("Album AI - Terminada");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String mArquivo;
    private ArrayList<ImagemDoAlbum> mImagens;

    public AI() {
        mArquivo = "";
        mImagens = new ArrayList<ImagemDoAlbum>();
    }

    public ArrayList<ImagemDoAlbum> getImagens() {
        return mImagens;
    }

    public void abrir(String eArquivo) {

        mArquivo = eArquivo;

        mImagens.clear();

        try {

            System.out.println("Album AI - Iniciada");

            Arquivador arquivador = new Arquivador(eArquivo);

            int b1 = Inteiro.byteToInt(arquivador.readByte());
            int b2 = Inteiro.byteToInt(arquivador.readByte());

            int v = Inteiro.byteToInt(arquivador.readByte());

            int quantidade = arquivador.readInt();

            TX eTX = new TX();

            for (int a = 0; a < quantidade; a++) {

                long pt = arquivador.getPonteiro();

                String item_nome = eTX.lerFluxoLimitado(arquivador,100);


                arquivador.setPonteiro(pt + 100);

                long inicio = arquivador.readLong();
                long fim = arquivador.readLong();

                mImagens.add(new ImagemDoAlbum(mArquivo,item_nome, inicio, fim));

                arquivador.setPonteiro(pt + 100 + 8 + 8);
            }

            arquivador.fechar();

            System.out.println("Album AI - Terminada");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImagem(int indice) {
        return mImagens.get(indice).getImagem();
    }

}
