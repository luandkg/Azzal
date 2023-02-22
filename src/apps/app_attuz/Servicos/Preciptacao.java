package apps.app_attuz.Servicos;


import apps.app_attuz.Assessorios.Progressante;
import apps.app_attuz.Assessorios.CadaPonto;
import apps.app_attuz.Assessorios.DadosQTT;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Ferramentas.*;
import apps.app_attuz.Legendas.Legendar;
import apps.app_attuz.Legendas.Legendas;
import libs.imagem.Imagem;
import libs.luan.fmt;
import libs.servittor.Servico;

import java.awt.image.BufferedImage;

public class Preciptacao extends Servico {

    private String LOCAL;

    public Preciptacao(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();

        Escala mEscala = EscalasPadroes.getEscalaPreciptacao();

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        MassaComNormal preciptacao_massa = new MassaComNormal(MassasDados.getTerraAgua(LOCAL), new Normalizador(mEscala.getMaximo()));

        DadosQTT dadosQTT = new DadosQTT(LOCAL);

        Progressante progresso = new Progressante(tectonica.getAltura() * tectonica.getLargura());

        preciptacao_massa.getDados().paraCadaPonto(new CadaPonto() {
            @Override
            public void onPonto(int x, int y) {

                if (tectonica.isTerra(x, y)) {

                    int preciptacao = 500;

                    int altitude = dadosQTT.getAltura(x, y);
                    int distancia = dadosQTT.getDistanciaDaAgua(x, y) * 150;

                    int o_altitude = altitude;
                    int o_distancia = distancia;

                    while (altitude > 200) {
                        altitude -= 200;
                        preciptacao -= 50;
                    }

                    while (distancia > 50) {
                        distancia -= 50;
                        preciptacao -= 25;
                    }
                    if (preciptacao < 0) {
                        preciptacao = 0;
                    }

                    preciptacao_massa.setValor(x, y, preciptacao);

                    String v = fmt.format(" PONTO ( {esq5} , {esq5} ) :: {esq9} com {esq5} igual {esq5}", x, y, o_altitude, o_distancia, preciptacao);

                    progresso.emitir((y * tectonica.getAltura()) + x, v);

                } else {
                    progresso.vazio((y * tectonica.getAltura()) + x);
                }


            }
        });



        BufferedImage mRenderizado = MapaRender.renderizaImagem(Imagem.getImagem(LOCAL + "build/planeta.png"), tectonica, tectonica.getTerra(), preciptacao_massa, mEscala);

        System.out.println("Guardar Preciptacao - QTT");
        MassaToQTT.salvarTerra(tectonica, preciptacao_massa.getDados(), LOCAL + "dados/preciptacao.qtt");

        mRenderizado = Legendar.legendar(mRenderizado, Legendas.getPreciptacao(), EscalasPadroes.getEscalaPreciptacao(), 100, 100);

        Imagem.exportar(mRenderizado, LOCAL + "build/preciptacao.png");


        marcarFim();
        mostrarTempo();
    }


}
