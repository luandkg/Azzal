package apps.app_tronarko;

import libs.arquivos.IM;
import libs.arquivos.binario.ByteChunk;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.imagem.Efeitos;
import libs.luan.Lista;
import libs.tronarko.Signos;

import java.awt.image.BufferedImage;

public class TronarkoImagemSignos {

    public BufferedImage SIGNO_CARPA;
    public BufferedImage SIGNO_GATO;
    public BufferedImage SIGNO_GAVIAO;
    public BufferedImage SIGNO_LEAO;
    public BufferedImage SIGNO_LEOPARDO;

    public BufferedImage SIGNO_LOBO;
    public BufferedImage SIGNO_RAPOSA;
    public BufferedImage SIGNO_SERPENTE;
    public BufferedImage SIGNO_TIGRE;
    public BufferedImage SIGNO_TOURO;

    public TronarkoImagemSignos() {

        Lista<DSItem> itens = DS.ler_todos("res/tronarko_signos.ds");

        SIGNO_CARPA = GET_IMAGEM(itens, "carpa");
        SIGNO_GATO = GET_IMAGEM(itens, "gato");
        SIGNO_GAVIAO = GET_IMAGEM(itens, "gaviao");
        SIGNO_LEAO = GET_IMAGEM(itens, "leao");
        SIGNO_LEOPARDO = GET_IMAGEM(itens, "leopardo");

        SIGNO_LOBO = GET_IMAGEM(itens, "lobo");
        SIGNO_RAPOSA = GET_IMAGEM(itens, "raposa");
        SIGNO_SERPENTE = GET_IMAGEM(itens, "serpente");
        SIGNO_TIGRE = GET_IMAGEM(itens, "tigre");
        SIGNO_TOURO = GET_IMAGEM(itens, "touro");


    }

    private BufferedImage GET_IMAGEM(Lista<DSItem> itens, String nome) {
        BufferedImage ret = null;

        for (DSItem item : itens) {
            if (item.getNome().contentEquals(nome)) {
                ByteChunk bytes = new ByteChunk(item.getBytes(), item.getBytes().length);
                ret = Efeitos.reduzirComAlfa(IM.ler_bytes(bytes), 64, 64);
                break;
            }

        }

        return ret;
    }


    public BufferedImage getSigno(Signos eSigno) {


        BufferedImage imagem = null;


        switch (eSigno) {
            case CARPA: {
                imagem = SIGNO_CARPA;
                break;
            }
            case GATO: {
                imagem = SIGNO_GATO;
                break;
            }
            case GAVIAO: {
                imagem = SIGNO_GAVIAO;
                break;
            }
            case LEAO: {
                imagem = SIGNO_LEAO;
                break;
            }
            case LEOPARDO: {
                imagem = SIGNO_LEOPARDO;
                break;
            }
            case LOBO: {
                imagem = SIGNO_LOBO;
                break;
            }
            case RAPOSA: {
                imagem = SIGNO_RAPOSA;
                break;
            }
            case SERPENTE: {
                imagem = SIGNO_SERPENTE;
                break;
            }
            case TIGRE: {
                imagem = SIGNO_TIGRE;
                break;
            }
            case TOURO: {
                imagem = SIGNO_TOURO;
                break;
            }
        }

        return imagem;

    }


}
