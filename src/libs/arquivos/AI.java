package libs.arquivos;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.binario.Inteiro;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.Lista;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AI {

    private static int ALBUM_AI1 = 15;
    private static int ALBUM_AI2 = 30;
    private static int ALBUM_VERSAO_1 = 100;

    public static void criar(Lista<String> arquivos, String eArquivo) {

        File Arq = new File(eArquivo);
        if (Arq.exists()) {
            Arq.delete();
        }

        try {

            System.out.println("Album AI - Iniciada");

            Arquivador arquivador = new Arquivador(eArquivo);

            arquivador.set_u8((byte) ALBUM_AI1);
            arquivador.set_u8((byte) ALBUM_AI2);

            arquivador.set_u8((byte) ALBUM_VERSAO_1);

            arquivador.set_u32(arquivos.getQuantidade());


            TX eTX = new TX();

            Lista<Long> pt_inicios = new Lista<Long>();
            Lista<Long> pt_terminos = new Lista<Long>();

            Lista<Long> inicios = new Lista<Long>();
            Lista<Long> terminos = new Lista<Long>();

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

                System.out.println("\t - Indice :: " + arquivo + " -->> " + nome);
                //System.out.println("Cabecalho Arquivo :: " + nome);

                long pti = arquivador.getPonteiro();

                arquivador.set_u8_em_bloco(100, (byte) 0);


                long ptf = arquivador.getPonteiro();

                arquivador.setPonteiro(pti);

                Lista<Byte> dados = eTX.toListaBytes(nome);

                if (dados.getQuantidade() < 100) {
                    arquivador.set_u8_lista(dados);
                } else {
                    throw new IllegalArgumentException("Tamanho de cabecalho invalido !");
                }

                arquivador.setPonteiro(ptf);

                pt_inicios.adicionar(arquivador.getPonteiro());
                arquivador.set_u64((byte) 0);

                pt_terminos.adicionar(arquivador.getPonteiro());
                arquivador.set_u64((byte) 0);

            }

            for (String arquivo : arquivos) {

                System.out.println("\t - Imagem :: " + arquivo);

                BufferedImage imagem = Imagem.getImagem(arquivo);

                if (imagem.getWidth() >= 2000 && imagem.getHeight() >= 2000) {
                    imagem = Efeitos.reduzir(imagem, imagem.getWidth() / 3, imagem.getHeight() / 3);
                }


                inicios.adicionar(arquivador.getPonteiro());
                IM.salvar_bytes(imagem, arquivador);
                terminos.adicionar(arquivador.getPonteiro());

            }

            for (int a = 0; a < arquivos.getQuantidade(); a++) {

                arquivador.setPonteiro(pt_inicios.get(a));
                arquivador.set_u64(inicios.get(a));

                arquivador.setPonteiro(pt_terminos.get(a));
                arquivador.set_u64(terminos.get(a));

                System.out.println(" -->> ITEM " + a + " (" + inicios.get(a) + " :: " + terminos.get(a) + " ) -->> " + pt_inicios.get(a) + " _ " + pt_terminos.get(a));


            }

            arquivador.fechar();

            System.out.println("Album AI - Terminada");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String mArquivo;
    private Lista<ImagemDoAlbum> mImagens;

    public AI() {
        mArquivo = "";
        mImagens = new Lista<ImagemDoAlbum>();
    }

    public Lista<ImagemDoAlbum> getImagens() {
        return mImagens;
    }

    public void abrir(String eArquivo) {

        mArquivo = eArquivo;

        mImagens.limpar();

        try {

            System.out.println("Album AI - Iniciada");

            Arquivador arquivador = new Arquivador(eArquivo);

            int b1 = Inteiro.byteToInt(arquivador.get());
            int b2 = Inteiro.byteToInt(arquivador.get());

            int v = Inteiro.byteToInt(arquivador.get());

            int quantidade = arquivador.get_u32();

            TX eTX = new TX();

            for (int a = 0; a < quantidade; a++) {

                long pt = arquivador.getPonteiro();

                String item_nome = eTX.lerFluxoLimitado(arquivador, 100);


                arquivador.setPonteiro(pt + 100);

                long inicio = arquivador.get_u64();
                long fim = arquivador.get_u64();

                mImagens.adicionar(new ImagemDoAlbum(mArquivo, item_nome, inicio, fim));

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
