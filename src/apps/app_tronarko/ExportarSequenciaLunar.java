package apps.app_tronarko;

import apps.app_letrum.Maker.AutoFonte;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.imagem.Imagem;
import libs.tronarko.Satelites.Ceu;
import libs.tronarko.Tozte;

import java.awt.image.BufferedImage;

public class ExportarSequenciaLunar {


    public static void exportar(Tozte eComecar, int eQuantidade, String eArquivo) {

        BufferedImage imagem = Imagem.criarEmBranco(1300, 1500);

        if (eQuantidade > 300) {
            imagem = Imagem.criarEmBranco(1300, 5500);
        }

        Renderizador render = new Renderizador(imagem);


        Satelatizador mSatelatizadorAllux = new Satelatizador("allux");
        Satelatizador mSatelatizadorEttos = new Satelatizador("ettos");
        Satelatizador mSatelatizadorUnnos = new Satelatizador("comum");

        Cores mCores = new Cores();

        AutoFonte escritor = new AutoFonte(render, mCores.getPreto(), 10);


        Ceu mCeu = new Ceu();

        int linha = 100;
        int coluna = 100;

        Tozte vamos = eComecar.getCopia();

        if (!vamos.Superarko_nome().contentEquals("ALFA")) {

            int teve = 0;

            while (!vamos.Superarko_nome().contentEquals("ALFA")) {
                vamos = vamos.adicionar_Superarko(1);
                teve += 1;
            }

            int faltou = 9 - teve;

            coluna += faltou * 120;


        }

        vamos = eComecar.getCopia();


        for (int i = 0; i < eQuantidade; i++) {

            if (vamos.Superarko_nome().contentEquals("ALFA")) {
                coluna = 100;
                linha += 100;
            } else {
                coluna += 120;
            }

            render.drawImagemComAlfa(coluna - 40, linha, mSatelatizadorAllux.get(mCeu.getAllux().getFaseIntTozte(vamos)));
            render.drawImagemComAlfa(coluna, linha, mSatelatizadorEttos.get(mCeu.getEttos().getFaseIntTozte(vamos)));
            render.drawImagemComAlfa(coluna + 40, linha, mSatelatizadorUnnos.get(mCeu.getUnnos().getFaseIntTozte(vamos)));

            escritor.escreva(coluna - 30, linha + 40, vamos.getTexto());

            vamos = vamos.adicionar_Superarko(1);
        }


        Imagem.exportar(imagem, eArquivo);

    }

}
