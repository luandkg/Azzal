package apps.app_attuz.IDW;

import apps.app_attuz.Camadas.Massas;
import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_attuz.Ferramentas.Normalizador;
import apps.app_attuz.Assessorios.Progressante;
import azzal.Formatos.Ponto;

import java.util.ArrayList;

public class AlgoritmoIDW {

    public void init() {

        ArrayList<Ponto> meus = new ArrayList<Ponto>();
        meus.add(new Ponto(2, 2));
        meus.add(new Ponto(4, 4));
        meus.add(new Ponto(6, 6));
        meus.add(new Ponto(8, 8));

        ArrayList<Ponto> refs = new ArrayList<Ponto>();
        refs.add(new Ponto(3, 3));
        refs.add(new Ponto(10, 10));

        for (Ponto ponto : meus) {

            int soma = 0;

            for (Ponto ponto_r : refs) {
                System.out.println("(" + ponto.getX() + "," + ponto.getY() + ")->(" + ponto_r.getX() + "," + ponto_r.getY() + ")  :: " + Espaco2D.distancia_entre_pontos(ponto.getX(), ponto.getY(), ponto_r.getX(), ponto_r.getY()));

                int valor = 0;

                if (ponto_r.getX() == 3) {
                    valor = 5;
                } else {
                    valor = 10;
                }

                soma += (valor / Espaco2D.distancia_entre_pontos(ponto.getX(), ponto.getY(), ponto_r.getX(), ponto_r.getY()));

            }

            System.out.println("(" + ponto.getX() + "," + ponto.getY() + ") -->> " + soma);

        }

    }

    public static void aplicar(Massas tectonica, int TECTONICA_VALOR, Massas massa, Normalizador normalizador, ArrayList<PontoIDW> eixos) {


        Progressante progresso = new Progressante(massa.getAltura() * massa.getLargura());

        int maior = 0;

        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {

                int ponto_valor = massa.getValor(x, y);
                if (ponto_valor > maior) {
                    maior = ponto_valor;
                }


                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {

                    progresso.emitir((y * massa.getAltura()) + x, " -->> APLICANDO IDW");

                    double taxa = 0;

                    for (PontoIDW eixo : eixos) {

                        if (eixo.isDiferente(x, y)) {
                            int distancia = Espaco2D.distancia_entre_pontos(x, y, eixo.getX(), eixo.getY());
                            taxa += (double) eixo.getValor() / (double) distancia;
                        }

                    }

                    if (taxa == 0) {
                        taxa = 1;
                    }

                    normalizador.adicionar((int) taxa);
                    massa.setValor(x, y, (int) taxa);


                }

            }
        }

        System.out.println("Maior Ponto :: " + maior);
    }

    public static ArrayList<PontoIDW> getEixos(Massas tectonica, int TECTONICA_VALOR, Massas massa) {

        ArrayList<PontoIDW> eixos = new ArrayList<PontoIDW>();

        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {
                if (tectonica.getValor(x, y) == TECTONICA_VALOR) {

                    int ponto_valor = massa.getValor(x, y);

                    if (ponto_valor > 1) {
                        eixos.add(new PontoIDW(x, y, 0, ponto_valor));
                    }

                }
            }
        }

        return eixos;
    }
}
