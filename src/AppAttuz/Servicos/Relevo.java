package AppAttuz.Servicos;

import AppAttuz.Camadas.Massas;
import AppAttuz.EscalasPadroes;
import AppAttuz.Ferramentas.*;
import AppAttuz.IDW.AlgoritmoIDW;
import AppAttuz.IDW.PontoIDW;
import AppAttuz.Mapa.Local;
import AppAttuz.MapaUtilitario;
import AppAttuz.Normalizador;
import Azzal.Formatos.Ponto;
import Imaginador.ImageUtils;
import Servittor.Servico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Relevo extends Servico {

    @Override
    public void onInit() {

        println("Criando mapa....");

        String LOCAL = "/home/luan/Imagens/Arkazz/";
        // String LOCAL = "/home/luan/Imagens/Simples/";

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
            marcar_simples(tectonica, massa, massa.getTerra(), MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf( nivel)),nivel);
        }


        ArrayList<PontoIDW> eixos = AlgoritmoIDW.getEixos(tectonica, tectonica.getTerra(), massa);

        System.out.println("TERRA -->> Eixos :: " + eixos.size());

        AlgoritmoIDW.aplicar(tectonica, tectonica.getTerra(), massa, normalizador, eixos);

        MapaRender.renderiza(mapa, tectonica, tectonica.getTerra(), massa, mRelevo, normalizador, LOCAL + "build/terra.png");


        println("-->> PRONTO !");

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

        for (int nivel = 1; nivel <= 10; nivel++) {
            marcar_simples(tectonica, massa, massa.getTerra(), MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf( nivel)),nivel);
            System.out.println(" -->> " + nivel + ":: " + MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf(mRelevo_aquatico.get(nivel))).size());
        }


        ArrayList<PontoIDW> eixos = AlgoritmoIDW.getEixos(tectonica, tectonica.getAgua(), massa);

        for (PontoIDW eixo : eixos) {
            massa.setValor(eixo.getX(), eixo.getY(), eixo.getValor());
            normalizador.adicionar(eixo.getValor());
        }

        System.out.println("AGUA -->> Eixos :: " + eixos.size());

        AlgoritmoIDW.aplicar(tectonica, tectonica.getAgua(), massa, normalizador, eixos);

        MapaRender.renderiza(mapa, tectonica, tectonica.getAgua(), massa, mRelevo_aquatico, normalizador, LOCAL + "build/agua.png");


        println("-->> PRONTO !");

    }

    private  void unir(String LOCAL) {

        Massas massa = new Massas(LOCAL);

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        BufferedImage terra = ImageUtils.getImagem(LOCAL + "build/terra.png");
        BufferedImage mar = ImageUtils.getImagem(LOCAL +"build/agua.png");

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {
                if (massa.isTerra(x, y)) {
                    mapa.setRGB(x,y,terra.getRGB(x,y));
                } else {
                    mapa.setRGB(x,y,mar.getRGB(x,y));
                }
            }
        }


        ImageUtils.exportar(mapa, LOCAL +"build/relevo.png");

    }


    private  void marcar_simples(Massas tectonica, Massas massa, int VALOR_PADRAO, ArrayList<Ponto> pt_montanhas, int v) {

        AlgoritmosDeMapa am = new AlgoritmosDeMapa();

        ArrayList<Ponto> n2 = am.expandir_em4(pt_montanhas, 10);
        ArrayList<Ponto> n3 = am.expandir_em4(n2, 20);
        ArrayList<Ponto> n4 = am.expandir_em4(n3, 20);
        ArrayList<Ponto> n5 = am.expandir_em4(n4, 20);

        Random eSorte = new Random();

        for (Ponto ePonto : n5) {
            if (tectonica.getValor(ePonto.getX(), ePonto.getY()) == VALOR_PADRAO) {
                massa.setValor(ePonto.getX(), ePonto.getY(), (v * 100) + eSorte.nextInt(100));
            }
        }

    }

}
