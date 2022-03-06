package AppAttuz.Servicos;


import AppAttuz.Arkazz;
import AppAttuz.Assessorios.Progressante;
import AppAttuz.Camadas.CadaPonto;
import AppAttuz.Camadas.DadosQTT;
import AppAttuz.Camadas.MapaFolha;
import AppAttuz.Camadas.Massas;
import AppAttuz.Camadas.MassasDados;
import AppAttuz.Regiao;
import Arquivos.Audio.RefInt;
import Azzal.Formatos.Ponto;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Imaginador.ImageUtils;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import Luan.STTY;
import Servittor.Servico;

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

        BufferedImage politico = MapaFolha.getMapaPolitico(LOCAL);

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        DadosQTT dadosQTT = new DadosQTT(LOCAL);

        Progressante progresso = new Progressante(tectonica.getAltura() * tectonica.getLargura());

        RefInt pontos = new RefInt(0);
        RefInt terra = new RefInt(0);

        BufferedImage mp = new BufferedImage(tectonica.getLargura(), tectonica.getAltura(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {

                mp.setRGB(x, y, Color.WHITE.getRGB());

            }
        }

        Renderizador rr = new Renderizador(mp);

        int PRETO = Color.BLACK.getRGB();
        int BRANCO = Color.WHITE.getRGB();

        ArrayList<String> regioes = new ArrayList<String>();
        ArrayList<Ponto> reg_pontos = new ArrayList<Ponto>();

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.isTerra(x, y)) {

                    int eCor = politico.getRGB(x, y);

                    terra.mais(1);

                    if (eCor == PRETO || eCor == BRANCO) {
                        pontos.mais(1);

                        int eCorProxima = getCorProxima(politico, x, y);

                        //  rr.drawPixel(x, y, new Cor(0, 0, 0));

                        rr.drawPixelBruto(x, y, eCorProxima);

                    } else {
                        if (!regioes.contains(String.valueOf(eCor))) {
                            regioes.add(String.valueOf(eCor));
                            reg_pontos.add(new Ponto(x, y));
                        }

                        rr.drawPixelBruto(x, y, eCor);
                    }


                }


            }
        });

        rr.exportarSemAlfa(LOCAL + "build/politicamente.png");


        System.out.println("Terra             :: " + terra.get());
        // System.out.println("Divisao Politica  :: " + pontos.get());

        int i = 0;

        int mais_y = 200;

        Fonte escrever = new FonteRunTime(new Cor(0, 0, 0), 22);

        escrever.setRenderizador(rr);

        double soma = 0;

        BufferedImage novo_politico = rr.toImagemSemAlfa();

        Arkazz eArkazz = new Arkazz();

        for (Ponto p : reg_pontos) {

            // rr.drawRect_Pintado(p.getX(), p.getY(), 20, 20, Cor.getInt(Integer.parseInt(regioes.get(i))));

            rr.drawRect_Pintado(100, mais_y, 20, 20, Cor.getInt(Integer.parseInt(regioes.get(i))));

            int tamanho = getArea(tectonica, novo_politico, Integer.parseInt(regioes.get(i)));
            double prop = ((double) tamanho / (double) terra.get()) * 100.0;

            Cor qCor = Cor.getInt(Integer.parseInt(regioes.get(i)));
            String sCor = "[" + qCor.getRed() + ":" + qCor.getGreen() + ":" + qCor.getBlue() + "]";

            Regiao regiao = eArkazz.getRegiaoDaCor(qCor);

            System.out.println("Regiao " + regiao.getNome() + " -->> " + sCor + " :: " + tamanho + " :: " + STTY.doubleNumC2(prop));

            soma += Double.parseDouble(STTY.doubleNumC2(prop));

            escrever.escreva(130, mais_y, regiao.getNome() + " :: " + STTY.doubleNumC2(prop));

            mais_y += 50;

            i += 1;
        }

        System.out.println("Soma :: " + soma);

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
