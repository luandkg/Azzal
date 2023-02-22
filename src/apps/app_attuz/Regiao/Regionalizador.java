package apps.app_attuz.Regiao;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Ferramentas.Local;
import azzal.utilitarios.Cor;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Regionalizador {
    private BufferedImage novo_politico = null;
    private Arkazz eArkazz;

    public Regionalizador(String LOCAL) {

        eArkazz = new Arkazz();
        novo_politico = Imagem.getImagem(LOCAL + "build/politicamente.png");


    }

    public ArrayList<Regiao> getRegioes() {
        return eArkazz.getRegioes();
    }

    public String getRegiao(int px, int py) {

        Cor qCor = Cor.getInt(novo_politico.getRGB(px * 2, py * 2));
        String sCor = "[" + qCor.getRed() + ":" + qCor.getGreen() + ":" + qCor.getBlue() + "]";

        String regiao = "";

        if (!sCor.contentEquals("[255:255:255]")) {
            regiao = eArkazz.getRegiaoNomeDaCor(qCor);
        }

        return regiao;
    }

    public String getRegiaoDaCidade(String cidade) {

        int px = 0;
        int py = 0;

        for (Local c : eArkazz.getCidades()) {
            if (c.getNome().contentEquals(cidade)) {
                px = c.getX();
                py = c.getY();
                break;
            }
        }


        return getRegiao(px, py);

    }
}
