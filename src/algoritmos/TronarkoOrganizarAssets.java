package algoritmos;

import libs.arquivos.IM;
import libs.arquivos.binario.Arquivador;
import libs.arquivos.ds.DS;
import libs.fs.PastaFS;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Par;

import java.awt.image.BufferedImage;

public class TronarkoOrganizarAssets {


    public static void signos(){

        PastaFS pasta_signos = new PastaFS( "/home/luan/assets/signos");

        Lista<Par<String,BufferedImage>> imagens = new Lista<Par<String,BufferedImage>>();


        IMAGEM_ADICIONAR(imagens,"carpa",Imagem.getImagem("res/signos/carpa.png"));
        IMAGEM_ADICIONAR(imagens,"gato",Imagem.getImagem("res/signos/gato.png"));
        IMAGEM_ADICIONAR(imagens,"gaviao",Imagem.getImagem("res/signos/gaviao.png"));
        IMAGEM_ADICIONAR(imagens,"leao",Imagem.getImagem("res/signos/leao.png"));
        IMAGEM_ADICIONAR(imagens,"leopardo",Imagem.getImagem("res/signos/leopardo.png"));

        IMAGEM_ADICIONAR(imagens,"lobo",Imagem.getImagem("res/signos/lobo.png"));
        IMAGEM_ADICIONAR(imagens,"raposa",Imagem.getImagem("res/signos/raposa.png"));
        IMAGEM_ADICIONAR(imagens,"serpente",Imagem.getImagem("res/signos/serpente.png"));
        IMAGEM_ADICIONAR(imagens,"tigre",Imagem.getImagem("res/signos/tigre.png"));
        IMAGEM_ADICIONAR(imagens,"touro",Imagem.getImagem("res/signos/touro.png"));

        DS.limpar(pasta_signos.getArquivo("tronarko_signos.ds"));

        for(Par<String,BufferedImage> imagem : imagens){

            Imagem.exportar(imagem.getValor(),pasta_signos.getArquivo(imagem.getChave()+"_o.png"));

            IM.salvar(imagem.getValor(),pasta_signos.getArquivo(imagem.getChave()+".im"));

            BufferedImage img_aberta = IM.abrir(pasta_signos.getArquivo(imagem.getChave()+".im"));
            Imagem.exportar(img_aberta,pasta_signos.getArquivo(imagem.getChave()+"_v.png"));

            DS.adicionar(pasta_signos.getArquivo("tronarko_signos.ds"),imagem.getChave(), Arquivador.GET_BYTES(pasta_signos.getArquivo(imagem.getChave()+".im")));

        }

    }

    private static void IMAGEM_ADICIONAR(Lista<Par<String,BufferedImage>> imagens,String nome,BufferedImage imagem){
        imagens.adicionar(new Par<String,BufferedImage>(nome,imagem));
    }

}
