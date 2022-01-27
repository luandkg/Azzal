package AppAttuz.Servicos;

import AppAttuz.*;
import AppAttuz.Camadas.Massas;
import AppAttuz.Ferramentas.*;
import AppAttuz.IDW.AlgoritmoIDW;
import AppAttuz.IDW.PontoIDW;
import AppAttuz.Mapa.Local;
import AppAttuz.Mapa.Proximattor;
import Azzal.Formatos.Ponto;
import Azzal.Renderizador;
import Azzal.Utils.Cor;
import Imaginador.ImageUtils;
import Letrum.Fonte;
import Letrum.Maker.FonteRunTime;
import Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Relevo extends Servico {

    private String LOCAL = "";

    public Relevo(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        println("Criando relevo ....");


        onTerra(LOCAL);
        onAgua(LOCAL);

        unir(LOCAL);

    }

    private void onTerra(String LOCAL) {

        Massas massa = new Massas(LOCAL);
        Massas tectonica = new Massas(LOCAL);

        println("Tudo :: " + massa.getContagem());

        println("Agua  :: " + massa.getContagemAgua() + " -->> " + massa.getProporcaoAgua());
        println("Terra :: " + massa.getContagemTerra() + " -->> " + massa.getProporcaoTerra());


        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Escala mRelevo = EscalasPadroes.getEscalaTerrestre();

        Normalizador normalizador = new Normalizador(mRelevo.getMaximo());

        ArrayList<Local> ls_valores_marcados = MapaUtilitario.toLocalNormalizado(MapaUtilitario.obterLocais(LOCAL + "terra.txt"));

        for (int nivel = 1; nivel <= 10; nivel++) {
            marcar_simples(tectonica, massa, massa.getTerra(), MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf(nivel)), nivel);
        }


        ArrayList<PontoIDW> eixos = AlgoritmoIDW.getEixos(tectonica, tectonica.getTerra(), massa);

        for (PontoIDW eixo : eixos) {
            massa.setValor(eixo.getX(), eixo.getY(), eixo.getValor());
            normalizador.adicionar(eixo.getValor());
        }

        MapaRender.renderizaSoPontos(mapa, tectonica, tectonica.getTerra(), massa, mRelevo, normalizador, LOCAL + "build/terra_eixos.png");


        System.out.println("TERRA -->> Eixos :: " + eixos.size());

        // AlgoritmoIDW.aplicar(tectonica, tectonica.getTerra(), massa, normalizador, eixos);

        Proximattor.porProximidade(tectonica, tectonica.getTerra(), massa, normalizador, eixos, 10);

        System.out.println("Maior :: " + getMaior(tectonica, tectonica.getTerra(), massa));
        System.out.println("Menor :: " + getMenor(tectonica, tectonica.getTerra(), massa));


        // MapaRender.renderiza(mapa, tectonica, tectonica.getTerra(), massa, mRelevo, normalizador, LOCAL + "build/terra.png");

        normalizador.equilibrar();
        //normalizador.stock();

        MapaRender.equilibrador(tectonica, tectonica.getTerra(), massa, mRelevo, normalizador);

        BufferedImage mapa_renderizado = Pintor.colorir(mapa, massa, mRelevo);


        // terra_legenda();


        ImageUtils.exportar(mapa_renderizado, LOCAL + "build/terra.png");

        println("-->> PRONTO !");

    }

    private void terra_legenda(BufferedImage mapa_entrada, Massas eMassa, Escala eEscala) {

        Renderizador render = new Renderizador(mapa_entrada);

        Fonte escrever = new FonteRunTime(Cor.getRGB(Color.BLACK), 15);
        escrever.setRenderizador(render);


        int altitude_min = 100;
        int delta = 100;
        int altitude_max = altitude_min + delta;

        for (int t = 1; t < 11; t++) {
            render.drawRect_Pintado(30, (t * 50) + 50, 20, 20, Cor.getInt(eEscala.get(t)));
            escrever.escreva(50, (t * 50) + 50, altitude_min + " m -- " + altitude_max + " m");

            altitude_min = altitude_max;
            delta = 100;
            altitude_max = altitude_min + delta;
        }

        ImageUtils.exportar(mapa_entrada, LOCAL + "build/terra.png");

    }

    private void onAgua(String LOCAL) {

        Massas massa = new Massas(LOCAL, true);
        Massas tectonica = new Massas(LOCAL, true);

        println("Tudo :: " + massa.getContagem());

        println("Agua  :: " + massa.getContagemAgua() + " -->> " + massa.getProporcaoAgua());
        println("Terra :: " + massa.getContagemTerra() + " -->> " + massa.getProporcaoTerra());


        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Escala mRelevo_aquatico = EscalasPadroes.getEscalaAquatica();

        Normalizador normalizador = new Normalizador(mRelevo_aquatico.getMaximo());

        ArrayList<Local> ls_valores_marcados = MapaUtilitario.toLocalNormalizado(MapaUtilitario.obterLocais(LOCAL + "agua.txt"));

        System.out.println(" -->> TODOS :: " + ls_valores_marcados.size());

        for (int nivel = 1; nivel <= 10; nivel++) {
            marcar_simples(tectonica, massa, massa.getAgua(), MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf(nivel)), nivel);
            System.out.println(" -->> " + nivel + ":: " + MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf(nivel)).size());
        }


        ArrayList<PontoIDW> eixos = AlgoritmoIDW.getEixos(tectonica, tectonica.getAgua(), massa);

        System.out.println(" -->> OBITIDOS :: " + eixos.size());

        ArrayList<PontoIDW> novos = ExpansorDeLimite.expandir(eixos, tectonica);
        eixos.addAll(novos);
        System.out.println(" -->> EXPANDIDOS :: " + eixos.size());


        for (PontoIDW eixo : eixos) {
            massa.setValor(eixo.getX(), eixo.getY(), eixo.getValor());
            normalizador.adicionar(eixo.getValor());
        }


        MapaRender.renderizaSoPontos(mapa, tectonica, tectonica.getAgua(), massa, mRelevo_aquatico, normalizador, LOCAL + "build/agua_eixos.png");

        System.out.println("AGUA -->> Eixos :: " + eixos.size());

        //Mapetzo.aplicar(tectonica, tectonica.getAgua(), massa, normalizador, eixos, 2);

        Proximattor.porProximidade(tectonica, tectonica.getAgua(), massa, normalizador, eixos, 10);

        System.out.println("Maior :: " + getMaior(tectonica, tectonica.getAgua(), massa));
        System.out.println("Menor :: " + getMenor(tectonica, tectonica.getAgua(), massa));


        // MapaRender.renderiza(mapa, tectonica, tectonica.getTerra(), massa, mRelevo, normalizador, LOCAL + "build/terra.png");

        normalizador.equilibrar();
        normalizador.stock();

        //MapaRender.equilibrador(tectonica, tectonica.getAgua(), massa, mRelevo_aquatico, normalizador);


        //agua_legenda(mapa,massa,mRelevo_aquatico);

         MapaRender.renderiza(mapa, tectonica, tectonica.getAgua(), massa, mRelevo_aquatico, normalizador, LOCAL + "build/agua.png");

       // ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo_aquatico), LOCAL + "build/agua.png");

        println("-->> PRONTO !");

    }

    private void agua_legenda(BufferedImage mapa_entrada, Massas eMassa, Escala eEscala) {

        BufferedImage mapa_renderizado = Pintor.colorir(mapa_entrada, eMassa, eEscala);

        Renderizador render = new Renderizador(mapa_renderizado);

        Fonte escrever = new FonteRunTime(Cor.getRGB(Color.BLACK), 15);
        escrever.setRenderizador(render);


        int altitude = 100;

        for (int t = 1; t < 11; t++) {
            render.drawRect_Pintado(50, (t * 50) + 50, 30, 30, Cor.getInt((eEscala.get(t))));
            escrever.escreva(90, (t * 50) + 50, altitude + "m");
            altitude += 100;
        }

        ImageUtils.exportar(mapa_renderizado, LOCAL + "build/agua.png");

    }


    private void unir(String LOCAL) {

        Massas massa = new Massas(LOCAL);

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        BufferedImage terra = ImageUtils.getImagem(LOCAL + "build/terra.png");
        BufferedImage mar = ImageUtils.getImagem(LOCAL + "build/agua.png");

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {
                if (massa.isTerra(x, y)) {
                    mapa.setRGB(x, y, terra.getRGB(x, y));
                } else {
                    mapa.setRGB(x, y, mar.getRGB(x, y));
                }
            }
        }


        ImageUtils.exportar(mapa, LOCAL + "build/relevo.png");

    }


    private void marcar_simples(Massas tectonica, Massas massa, int VALOR_PADRAO, ArrayList<Ponto> pt_montanhas, int v) {

        System.out.println(" -->> " + v + " ENTRADA " + pt_montanhas.size());

        AlgoritmosDeMapa am = new AlgoritmosDeMapa();

        ArrayList<Ponto> n2 = am.expandir_em4(pt_montanhas, 10);
        ArrayList<Ponto> n3 = am.expandir_em4(n2, 20);
        ArrayList<Ponto> n4 = am.expandir_em4(n3, 20);
        ArrayList<Ponto> n5 = am.expandir_em4(n4, 20);

        Random eSorte = new Random();

        for (Ponto ePonto : pt_montanhas) {
            if (tectonica.getValor(ePonto.getX(), ePonto.getY()) == VALOR_PADRAO) {
                massa.setValor(ePonto.getX(), ePonto.getY(), (v * 100) + eSorte.nextInt(100));
            }
        }

    }

    public static int getMaior(Massas tectonica, int TECTONICA_VALOR, Massas massa) {

        final int[] maior = {0};

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {
                    int corrente = massa.getValor(x, y);
                    if (corrente > maior[0]) {
                        maior[0] = corrente;
                    }
                }
            }
        });

        return maior[0];
    }

    public static int getMenor(Massas tectonica, int TECTONICA_VALOR, Massas massa) {


        final int[] menor = {Integer.MAX_VALUE};

        tectonica.paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {
                    int corrente = massa.getValor(x, y);
                    if (corrente < menor[0]) {
                        menor[0] = corrente;
                    }
                }
            }
        });

        return menor[0];
    }

}
