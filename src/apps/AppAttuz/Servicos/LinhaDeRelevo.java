package apps.AppAttuz.Servicos;

import apps.AppAttuz.Camadas.Massas;
import apps.AppAttuz.Camadas.MassasDados;
import apps.AppAttuz.Camadas.DadosQTT;
import apps.AppAttuz.Ferramentas.Espaco2D;
import apps.AppAttuz.Mapa.Local;
import apps.AppAttuz.Assessorios.MapaUtilitario;
import azzal.Cores;
import azzal.Formatos.Ponto;
import azzal.Renderizador;
import libs.Imaginador.ImageUtils;
import libs.Servittor.Servico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LinhaDeRelevo extends Servico {

    private String LOCAL = "";

    public LinhaDeRelevo(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();
        println("Criando linha de relevo ....");


        Massas massa = MassasDados.getTerraAgua(LOCAL);
        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        ArrayList<Local> pontos = MapaUtilitario.toLocalNormalizado(MapaUtilitario.obterLocais(LOCAL + "dados.txt"));

        for (Local eixo : pontos) {
            massa.setValor(eixo.getX(), eixo.getY(), 10);
        }

        if (pontos.size() < 2) {
            return;
        }

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "build/terra.png");
        BufferedImage copia = ImageUtils.getCopia(mapa);

        Renderizador render = new Renderizador(copia);

        Cores mCores = new Cores();

        ArrayList<Ponto> todos_pontos = Espaco2D.getPontosDeLinha(pontos.get(0).getX(), pontos.get(0).getY(), pontos.get(1).getX(), pontos.get(1).getY());

        render.drawLinha(pontos.get(0).getX(), pontos.get(0).getY(), pontos.get(1).getX(), pontos.get(1).getY(), mCores.getVermelho());


        ImageUtils.exportar(copia, LOCAL + "build/linha_de_relevo.png");

        DadosQTT dadosQTT = new DadosQTT(LOCAL);

        int largura = Espaco2D.distancia_entre_pontos(pontos.get(0).getX(), pontos.get(0).getY(), pontos.get(1).getX(), pontos.get(1).getY());

        Renderizador montar = new Renderizador(new BufferedImage(largura, 500, BufferedImage.TYPE_INT_RGB));

        montar.limpar(mCores.getBranco());

        int lar = 0;

        for (Ponto ePonto : todos_pontos) {

            int altitude = (dadosQTT.getAltura(ePonto.getX(), ePonto.getY()) / 100) * 30;

           // System.out.println(ePonto.getX() + "::" + ePonto.getY() + " -->> " + altitude);

            montar.drawRect_Pintado(lar, 500 - altitude, 1, altitude, mCores.getLaranja());
            lar += 1;

        }

        montar.exportar(LOCAL + "build/perfil_de_relevo.png");

        marcarFim();
        mostrarTempo();
    }
}