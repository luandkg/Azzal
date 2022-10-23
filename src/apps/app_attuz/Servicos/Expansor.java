package apps.app_attuz.Servicos;

import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.MapaUtilitario;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Ferramentas.*;
import apps.app_attuz.IDW.AlgoritmoIDW;
import apps.app_attuz.IDW.PontoIDW;
import apps.app_attuz.Ferramentas.Local;
import azzal.geometria.Ponto;
import azzal.Renderizador;
import azzal.utilitarios.Cor;
import libs.Imaginador.ImageUtils;
import libs.Servittor.Servico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Expansor extends Servico {

    private String LOCAL = "";

    public Expansor(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        println("Criando area de expansao ....");


        Massas massa = MassasDados.getAguaTerra(LOCAL);
        Massas tectonica = MassasDados.getAguaTerra(LOCAL);

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

        for (PontoIDW eixo : eixos) {
            massa.setValor(eixo.getX(), eixo.getY(), eixo.getValor());
            normalizador.adicionar(eixo.getValor());
        }

        System.out.println(" -->> OBITIDOS :: " + eixos.size());

        // FAZER MAIOR
        BufferedImage maior = new BufferedImage(tectonica.getLargura() + 1500, tectonica.getAltura() + 1500, BufferedImage.TYPE_INT_RGB);

        Renderizador render = new Renderizador(maior);
        render.drawRect_Pintado(0, 0, render.getLargura(), render.getAltura(), new Cor(255, 255, 255));
        render.drawImagem(500, 500, ImageUtils.getCopia(mapa));

        int deslocar_x = 500;
        int deslocar_y = 500;

        for (PontoIDW eixo : eixos) {
            render.drawRect_Pintado(deslocar_x + eixo.getX(), deslocar_y + eixo.getY(), 10, 10, new Cor(255, 0, 0));
        }


        ArrayList<PontoIDW> novos = ExpansorDeLimite.expandir(eixos, tectonica);

        System.out.println("-->> ABAIXO :: " + novos.size());

        for (PontoIDW eixo : novos) {
            render.drawRect_Pintado(deslocar_x + eixo.getX(), deslocar_y + eixo.getY(), 10, 10, new Cor(0, 255, 0));
        }


        render.exportar(LOCAL + "build/area_expansao.png");




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

}
