package apps.app_attuz.Servicos;


import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Assessorios.Progressante;
import apps.app_attuz.Assessorios.CadaPonto;
import apps.app_attuz.Assessorios.DadosQTT;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Regiao.Regiao;
import libs.arquivos.audio.RefInt;
import azzal.geometria.Ponto;
import azzal.Renderizador;
import azzal.utilitarios.Cor;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import libs.Imaginador.ImageUtils;
import libs.Luan.fmt;
import libs.Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DivisaoPolitica extends Servico {

    private String LOCAL;

    public DivisaoPolitica(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();


        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        DadosQTT dadosQTT = new DadosQTT(LOCAL);
        Progressante progresso = new Progressante(tectonica.getAltura() * tectonica.getLargura());


        Renderizador rr = new Renderizador(ImageUtils.criarEmBranco(tectonica.getLargura(), tectonica.getAltura()));

        ArrayList<String> regioes = new ArrayList<String>();
        ArrayList<Ponto> reg_pontos = new ArrayList<Ponto>();


        int mais_y = 200;

        Fonte escrever = new FonteRunTime(new Cor(0, 0, 0), 22);

        escrever.setRenderizador(rr);

        double soma = 0;

        BufferedImage novo_politico = ImageUtils.getImagem(LOCAL + "build/politicamente.png");

        Arkazz eArkazz = new Arkazz();

        Massas planeta = MassasDados.getTerraAgua(LOCAL);

        rr.drawImagem(0, 0, novo_politico);


        for (Regiao regiao_corrente : eArkazz.getRegioes()) {

            // rr.drawRect_Pintado(p.getX(), p.getY(), 20, 20, Cor.getInt(Integer.parseInt(regioes.get(i))));

            rr.drawRect_Pintado(100, mais_y, 20, 20, regiao_corrente.getCor());

            int tamanho = getArea(tectonica, novo_politico, regiao_corrente.getCor().getValor());
            double prop = ((double) tamanho / (double) planeta.getContagemTerra()) * 100.0;


            Cor qCor = regiao_corrente.getCor();

            String sCor = "[" + qCor.getRed() + ":" + qCor.getGreen() + ":" + qCor.getBlue() + "]";

            Regiao regiao = eArkazz.getRegiaoDaCor(qCor);

            System.out.println("Regiao " + regiao.getNome() + " -->> " + sCor + " :: " + tamanho + " :: " + fmt.doubleNumC2(prop));

            soma += Double.parseDouble(fmt.doubleNumC2(prop));

            escrever.escreva(130, mais_y, regiao.getNome() + " :: " + fmt.doubleNumC2(prop));

            mais_y += 50;

        }

        //  System.out.println("Soma :: " + soma);
        System.out.println("Soma :: 100.00 %");

        mais_y += 50;
        escrever.escreva(100, mais_y, "TOTAL : " + soma);


        rr.exportarSemAlfa(LOCAL + "build/regioes.png");


        System.out.println("Regioes Politicas :: " + regioes.size());


        marcarFim();
        mostrarTempo();
    }

    public static int getArea(Massas tectonica, BufferedImage politico, int pCor) {


        RefInt terra = new RefInt(0);

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.isTerra(x, y)) {

                    int eCor = politico.getRGB(x, y);

                    if (eCor == pCor) {
                        terra.mais(1);
                    }

                }


            }
        });


        return terra.get();
    }

    public static int getCorProxima(BufferedImage politico, int x, int y) {

        int valor = 0;
        int dist = 0;

        int PRETO = Color.BLACK.getRGB();
        int BRANCO = Color.WHITE.getRGB();

        int OBSERVAR = 100;

        for (int xi = 0; xi < OBSERVAR; xi++) {
            int vcor = politico.getRGB(x + xi, y);
            if (vcor != PRETO && vcor != BRANCO) {
                valor = vcor;
                dist = xi;
                break;
            }
        }

        int guardar_valor = valor;
        int guardar_dist = dist;

        for (int xi = 0; xi < OBSERVAR; xi++) {
            int vcor = politico.getRGB(x - xi, y);
            if (vcor != PRETO && vcor != BRANCO) {
                valor = vcor;
                dist = xi;
                break;
            }
        }

        if (guardar_dist < dist && guardar_valor != 0) {
            valor = guardar_valor;
            dist = guardar_dist;
        }

        guardar_valor = valor;
        guardar_dist = dist;

        for (int yi = 0; yi < OBSERVAR; yi++) {

            int px = x;
            int py = y + yi;

            if (px >= 0 && px < politico.getWidth() && py >= 0 && py < politico.getHeight()) {

                int vcor = politico.getRGB(x, y + yi);
                if (vcor != PRETO && vcor != BRANCO) {
                    valor = vcor;
                    dist = yi;
                    break;
                }

            }


        }

        if (guardar_dist < dist && guardar_valor != 0) {
            valor = guardar_valor;
            dist = guardar_dist;
        }

        guardar_valor = valor;
        guardar_dist = dist;


        for (int yi = 0; yi < OBSERVAR; yi++) {
            int px = x;
            int py = y - yi;

            if (px >= 0 && px < politico.getWidth() && py >= 0 && py < politico.getHeight()) {

                int vcor = politico.getRGB(x, y - yi);
                if (vcor != PRETO && vcor != BRANCO) {
                    valor = vcor;
                    dist = yi;
                    break;
                }

            }

        }

        if (guardar_dist < dist && guardar_valor != 0) {
            valor = guardar_valor;
            dist = guardar_dist;
        }

        return valor;

    }
}
