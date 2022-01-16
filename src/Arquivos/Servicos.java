package Arquivos;

import Arquivos.Binario.Arquivador;
import Arquivos.Binario.Int8;
import Imaginador.ImageUtils;
import Servittor.Servico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Servicos extends Servico {

    @Override
    public void onInit() {

        String LOCAL = "/home/luan/Imagens/Simples/";

        Int8 int8 = new Int8(250);

        println("BITS   :: " + int8.get());
        println("2 BITS :: " + int8.getBitsInt(0, 2));


        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "build/relevo.png");

        IM.salvar(mapa, LOCAL + "build/relevo.im");

        //  memoriaDump(LOCAL + "build/relevo.im");

        IM.toPNG(LOCAL + "build/relevo.im", LOCAL + "build/relevo_im.png");

        IM.salvar(ImageUtils.getImagem("/home/luan/Imagens/diii.png"), "/home/luan/Imagens/eu.im");
        IM.toPNG("/home/luan/Imagens/eu.im", "/home/luan/Imagens/eu_im.png");

        TX.escreverTX("Oiiieee Luan \n Qual é o seu nome ?", "/home/luan/Documentos/luan.tx");

        TX eTX = new TX();

        TX.escreverTX(eTX.getCaracteres(), "/home/luan/Documentos/luan.tx");

        println("");
        println("ARQUIVO TX 1.0");

        System.out.println(TX.lerTX("/home/luan/Documentos/luan.tx"));

        // memoriaDump("/home/luan/Documentos/luan.tx",300);

        ArrayList<String> arquivos = new ArrayList<String>();
        arquivos.add("/home/luan/Imagens/Circulo-Cromatico.jpg");
        arquivos.add("/home/luan/Imagens/Festa.png");
        arquivos.add("/home/luan/Imagens/diii.png");
        arquivos.add("/home/luan/Imagens/sketches-mountain-brushes.jpg");
        arquivos.add("/home/luan/Imagens/2021/2021 09/2021-08-30 16.18.34.jpg");

        AI.criar(arquivos, "/home/luan/Imagens/luan.ai");

        AI minha = new AI();
        minha.abrir("/home/luan/Imagens/luan.ai");

        for (ImagemDoAlbum imagem : minha.getImagens()) {
            System.out.println("Item " + imagem.getNome() + " -->> " + imagem.getInicio() + "::" + imagem.getFim());
        }

    }


}

