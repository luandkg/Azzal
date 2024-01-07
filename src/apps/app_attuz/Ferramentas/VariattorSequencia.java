package apps.app_attuz.Ferramentas;

import apps.app_attuz.Assessorios.DadosQTT;
import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class VariattorSequencia {

    public void variacao(String LOCAL, int quantidade, MassaComNormal inicio, MassaComNormal fim, Escala mEscala, String eArquivoPrefixo) {


        int futuro_real = quantidade;


        DadosQTT dadosQTT = new DadosQTT(LOCAL);

        BufferedImage mapa = Imagem.getImagem(LOCAL + "build/planeta.png");

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);


        Variattor eVariattor = new Variattor();
        eVariattor.init(tectonica, quantidade, inicio.getDados(), fim.getDados());


        Massas verao_normalizado = renderJa(LOCAL, tectonica, inicio.getDados(), inicio.getNormalizado(), mEscala);

        //  ImageUtils.exportar(Pintor.colorir(mapa, verao_normalizado, mEscala), LOCAL + "build/var_temperatura/0v.png");


        Massas inverno_normalizado = renderJa(LOCAL, tectonica, fim.getDados(), fim.getNormalizado(), mEscala);

        // ImageUtils.exportar(Pintor.colorir(mapa, inverno_normalizado, mEscala), LOCAL + "build/var_temperatura/oi.png");

        ArrayList<String> eArquivos = new ArrayList<String>();

        for (int i = 0; i < (quantidade); i++) {

            Normalizador cGeral = new Normalizador(mEscala.getMaximo());

            for (int y = 0; y < tectonica.getAltura(); y++) {
                for (int x = 0; x < tectonica.getLargura(); x++) {

                    if (tectonica.isTerra(x, y)) {


                        int inicio_valor = fim.getDados().getValor(x, y);

                        if (eVariattor.temVariacao(x, y)) {

                            double variacao = eVariattor.getVariacao(x, y);

                            if (i > 0) {
                                inicio_valor += (int) variacao;
                            }


                            fim.getDados().setValor(x, y, inicio_valor);


                        }

                        cGeral.adicionar(inicio_valor);

                    }


                }
            }

            cGeral.equilibrar();

            Massas corrente_normalizado = renderJa(LOCAL, tectonica, fim.getDados(), cGeral, mEscala);


            System.out.println("Variacao :: " + i);

            int passado = (quantidade - i - 1);
            int futuro = (quantidade + i);

            Imagem.exportar(Pintor.colorir(mapa, corrente_normalizado, mEscala), LOCAL + eArquivoPrefixo + passado + ".png");

            eArquivos.add(LOCAL + eArquivoPrefixo + passado + ".png");

            if (futuro != quantidade && futuro != ((2 * quantidade) - 1)) {

                Imagem.exportar(Pintor.colorir(mapa, corrente_normalizado, mEscala), LOCAL + eArquivoPrefixo + futuro_real + ".png");

                eArquivos.add(LOCAL + eArquivoPrefixo + futuro_real + ".png");


                futuro_real += 1;
            }

        }

        //VideoCodecador.criar(LOCAL + "build/temperatura.vi", eArquivos);
        //  for (String eArquivo : eArquivos) {
        //new File(eArquivo).delete();
        // }


    }

    public Massas renderJa(String LOCAL, Massas tectonica, Massas dados, Normalizador vGeral, Escala mEscala) {

        Massas renderAqui = MassasDados.getTerraAgua(LOCAL);

        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {
                if (tectonica.getValor(x, y) == tectonica.getTerra()) {
                    int real = vGeral.intervalo(mEscala.getMinimo(), mEscala.getMaximo(), vGeral.get(dados.getValor(x, y)));
                    renderAqui.setValor(x, y, real);
                }
            }
        }

        return renderAqui;
    }


}
