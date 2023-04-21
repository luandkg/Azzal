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
import apps.app_attuz.Ferramentas.Proximattor;
import libs.arquivos.QTT;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Imagem;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Relevo extends Servico {

    private String LOCAL = "";

    public Relevo(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();

        println("Criando relevo ....");


        onTerra(LOCAL);
        onAgua(LOCAL);

        UnirMapas.unir(LOCAL, "build/terra.png", "build/agua.png", "build/relevo.png");

        marcarFim();
        mostrarTempo();
    }

    private void onTerra(String LOCAL) {

        Massas massa = MassasDados.getTerraAgua(LOCAL);
        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        // println("Tudo :: " + massa.getContagem());
        //println("Agua  :: " + massa.getContagemAgua() + " -->> " + massa.getProporcaoAgua());
        // println("Terra :: " + massa.getContagemTerra() + " -->> " + massa.getProporcaoTerra());

        println("Agua  " + " -->> " + massa.getProporcaoAgua());
        println("Terra " + " -->> " + massa.getProporcaoTerra());

        BufferedImage mapa = Imagem.getImagem(LOCAL + "build/planeta.png");

        Escala mRelevo = EscalasPadroes.getEscalaTerrestre();

        Normalizador normalizador = new Normalizador(mRelevo.getMaximo());

        ArrayList<Local> ls_valores_marcados = MapaUtilitario.toLocalNormalizado(MapaUtilitario.obterLocais(LOCAL + "terra.txt"));

        for (int nivel = 1; nivel <= 10; nivel++) {
            Pontuadores.marcarPontos(tectonica, massa, massa.getTerra(), MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf(nivel)), nivel);
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

        //System.out.println("Maior :: " + Pontuadores.getMaior(tectonica, tectonica.getTerra(), massa));
        //System.out.println("Menor :: " + Pontuadores.getMenor(tectonica, tectonica.getTerra(), massa));


        // MapaRender.renderiza(mapa, tectonica, tectonica.getTerra(), massa, mRelevo, normalizador, LOCAL + "build/terra.png");

        normalizador.equilibrar();
        //normalizador.stock();

        MapaRender.equilibrador(tectonica, tectonica.getTerra(), massa, mRelevo, normalizador);


        System.out.println("Guardar Relevo");

        QTT eRelevoQTT = QTT.criar(tectonica.getLargura(), tectonica.getAltura());


        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {

                if (tectonica.isTerra(x, y)) {
                    int n = massa.getValor(x, y);
                    eRelevoQTT.setValor(x, y, (n * 100));
                } else {
                    eRelevoQTT.setValor(x, y, 0);
                }

            }
        }

        QTT.guardar(LOCAL + "dados/relevo.qtt", eRelevoQTT);

        System.out.println("Guardar Relevo - Concluido !");


        BufferedImage mapa_renderizado = Pintor.colorir(mapa, massa, mRelevo);


        // terra_legenda();


        Imagem.exportar(mapa_renderizado, LOCAL + "build/terra.png");

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

        Imagem.exportar(mapa_entrada, LOCAL + "build/terra.png");

    }

    private void onAgua(String LOCAL) {

        Massas massa = MassasDados.getAguaTerra(LOCAL);
        Massas tectonica = MassasDados.getAguaTerra(LOCAL);

        // println("Tudo :: " + massa.getContagem());

        //   println("Agua  :: " + massa.getContagemAgua() + " -->> " + massa.getProporcaoAgua());
        // println("Terra :: " + massa.getContagemTerra() + " -->> " + massa.getProporcaoTerra());


        BufferedImage mapa = Imagem.getImagem(LOCAL + "build/planeta.png");

        Escala mRelevo_aquatico = EscalasPadroes.getEscalaAquatica();

        Normalizador normalizador = new Normalizador(mRelevo_aquatico.getMaximo());

        ArrayList<Local> ls_valores_marcados = MapaUtilitario.toLocalNormalizado(MapaUtilitario.obterLocais(LOCAL + "agua.txt"));

        //   System.out.println(" -->> TODOS :: " + ls_valores_marcados.size());

        for (int nivel = 1; nivel <= 10; nivel++) {
            Pontuadores.marcarPontos(tectonica, massa, massa.getAgua(), MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf(nivel)), nivel);
            //   System.out.println(" -->> " + nivel + ":: " + MapaUtilitario.getPontosComNome(ls_valores_marcados, String.valueOf(nivel)).size());
        }


        ArrayList<PontoIDW> eixos = AlgoritmoIDW.getEixos(tectonica, tectonica.getAgua(), massa);

        // System.out.println(" -->> OBITIDOS :: " + eixos.size());

        ArrayList<PontoIDW> novos = ExpansorDeLimite.expandir(eixos, tectonica);
        eixos.addAll(novos);
        // System.out.println(" -->> EXPANDIDOS :: " + eixos.size());


        for (PontoIDW eixo : eixos) {
            massa.setValor(eixo.getX(), eixo.getY(), eixo.getValor());
            normalizador.adicionar(eixo.getValor());
        }


        MapaRender.renderizaSoPontos(mapa, tectonica, tectonica.getAgua(), massa, mRelevo_aquatico, normalizador, LOCAL + "build/agua_eixos.png");

        System.out.println("AGUA -->> Eixos :: " + eixos.size());

        //Mapetzo.aplicar(tectonica, tectonica.getAgua(), massa, normalizador, eixos, 2);

        Proximattor.porProximidade(tectonica, tectonica.getAgua(), massa, normalizador, eixos, 10);

        //   System.out.println("Maior :: " + getMaior(tectonica, tectonica.getAgua(), massa));
        // System.out.println("Menor :: " + getMenor(tectonica, tectonica.getAgua(), massa));


        // MapaRender.renderiza(mapa, tectonica, tectonica.getTerra(), massa, mRelevo, normalizador, LOCAL + "build/terra.png");

        normalizador.equilibrar();
        // normalizador.stock();


        //MapaRender.equilibrador(tectonica, tectonica.getAgua(), massa, mRelevo_aquatico, normalizador);


        //agua_legenda(mapa,massa,mRelevo_aquatico);

        MapaRender.renderiza(mapa, tectonica, tectonica.getAgua(), massa, mRelevo_aquatico, normalizador, LOCAL + "build/agua.png");

        QTT eRelevoQTT = QTT.getTudo(LOCAL + "dados/relevo.qtt");

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {

                if (tectonica.isAgua(x, y)) {

                    int n = massa.getValor(x, y);
                    int valor = -(n * 100);

                    eRelevoQTT.setValor(x, y, valor);

                    //QTT.alterar(LOCAL + "dados/relevo.qtt", x,y, valor);

                }

            }
        }

        QTT.guardar(LOCAL + "dados/relevo.qtt", eRelevoQTT);

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

        Imagem.exportar(mapa_renderizado, LOCAL + "build/agua.png");

    }


}
