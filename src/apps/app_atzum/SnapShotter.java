package apps.app_atzum;

import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.arquivos.video.Video;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SnapShotter {

    public static void CRIAR(String arquivo_video, String arquivo_imagem) {

        fmt.print("++ SnapShotter : {}", Strings.GET_REVERSO_ATE(arquivo_imagem,"/"));


        Video mVideo = new Video();
        mVideo.abrir(arquivo_video);

        Lista<BufferedImage> quadros = new Lista<BufferedImage>();

        int total = mVideo.getQuadrosTotal();
        int taxa = total / 10;
        int indice = 0;

        int guardar = 0;

        while(indice<=total){

            mVideo.proximo();

            if(indice==guardar){
                fmt.print("\t - Organizando snaps = {esq5} de {esq3}",indice,total);
                BufferedImage reduzido = Efeitos.reduzir(mVideo.getImagemCorrente(), mVideo.getImagemCorrente().getWidth() / 2, mVideo.getImagemCorrente().getHeight() / 2);
                quadros.adicionar(reduzido);
                guardar+=taxa;
            }

            indice+=1;
        }




        Cores mCores = new Cores();
        Renderizador render = Renderizador.construir(1500, 2300, mCores.getPreto());

         Fonte escritor= new FonteRunTime(mCores.getBranco(), 30);
        escritor.setRenderizador(render);

        int py = 0;
        int px = 0;

        int hiperarko = 1;
        for (BufferedImage imagem : quadros) {
            render.drawImagem(100 + px, 100 + py, imagem);
            escritor.escreva(100+px,100 + py,"Hiperarko "+hiperarko);
            py += 400;

            if (py > 1600) {
                py = 0;
                px += 750;
            }
            hiperarko+=1;
        }

        Imagem.exportar(render.toImagemSemAlfa(), arquivo_imagem);

    }

    public static void CRIAR_DUPLO(String arquivo_video1,String arquivo_video2, String arquivo_imagem) {

        fmt.print("++ SnapShotter Duplo: {}", Strings.GET_REVERSO_ATE(arquivo_imagem,"/"));

        Video video1 = new Video();
        video1.abrir(arquivo_video1);

        Video video2 = new Video();
        video2.abrir(arquivo_video2);

        Lista<BufferedImage> quadros1 = new Lista<BufferedImage>();
        Lista<BufferedImage> quadros2 = new Lista<BufferedImage>();

        int total = video1.getQuadrosTotal();
        int taxa = 50;
        int indice = 0;

        int guardar = 0;

        while(indice<=total){

            video1.proximo();
            video2.proximo();

            if(indice==guardar && quadros1.getQuantidade()<10){
                fmt.print("\t - Organizando snaps = {esq5} de {esq3}",indice,total);
                BufferedImage reduzido = Efeitos.reduzir(video1.getImagemCorrente(), video1.getImagemCorrente().getWidth() / 2, video1.getImagemCorrente().getHeight() / 2);
                quadros1.adicionar(reduzido);

                BufferedImage reduzido2 = Efeitos.reduzir(video2.getImagemCorrente(), video2.getImagemCorrente().getWidth() / 2, video2.getImagemCorrente().getHeight() / 2);
                quadros2.adicionar(reduzido2);

                guardar+=taxa;
            }

            indice+=1;
        }




        Cores mCores = new Cores();
        Renderizador render = Renderizador.construir(2500, 2300, mCores.getPreto());


        int py = 0;
        int px = 0;

        int quadro_indice = 0;

        for (BufferedImage imagem : quadros1) {

            render.drawImagem(100 + px, 100 + py, imagem);
            render.drawImagem(700 + px, 100 + py, quadros2.get(quadro_indice));

            py += 400;

            if (py > 1600) {
                py = 0;
                px += 1200;
            }

            quadro_indice+=1;
        }

        Imagem.exportar(render.toImagemSemAlfa(), arquivo_imagem);

    }

}
