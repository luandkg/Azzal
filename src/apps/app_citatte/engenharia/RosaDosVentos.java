package apps.app_citatte.engenharia;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_citatte.engenharia.AvenidaViaria;
import libs.azzal.geometria.Ponto;
import libs.luan.Lista;
import libs.luan.fmt;

public class RosaDosVentos {

    public Ponto noroeste;
    public Ponto sudoeste;

    public Ponto nordeste;
    public Ponto sudeste;

    public Ponto norte;
    public Ponto sul;

    public Ponto oeste;
    public Ponto leste;

    public Ponto centro;


    public RosaDosVentos(Lista<AvenidaViaria> avenidas) {


        int menor_x = avenidas.get(0).getX();
        int maior_x = avenidas.get(0).getX();
        int menor_y = avenidas.get(0).getY();
        int maior_y = avenidas.get(0).getY();

        for (AvenidaViaria av : avenidas) {
            if (av.getX() < menor_x) {
                menor_x = av.getX();
            }
            if (av.getX() > maior_x) {
                maior_x = av.getX();

                if (av.isHorizontal()) {
                    maior_y = av.getY() + av.getComprimento();
                }
            }

            if (av.getY() < menor_y) {
                menor_y = av.getY();
            }
            if (av.getY() > maior_y) {
                maior_y = av.getY();
                if (av.isVertical()) {
                    maior_y = av.getY() + av.getComprimento();
                }
            }
        }

        fmt.print(">> Enderecar areas");
        fmt.print("Cidade Espaco ->> [{} - {}] -- [{} - {}]", menor_x, maior_x, menor_y, maior_y);


        int largura = maior_x - menor_x;
        int altura = maior_y - menor_y;


        int l3 = largura / 3;
        int a3 = altura / 3;

        int min_x = menor_x;
        int min_y = menor_y;


        noroeste = new Ponto(min_x + (l3 / 2), min_y + (a3 / 3));
        sudoeste = new Ponto(min_x + ((l3 / 2)), min_y + ((a3 * 2) + (a3 / 2)));

        nordeste = new Ponto(min_x + ((2 * l3) + (l3 / 2)), min_y + (a3 / 3));
        sudeste = new Ponto(min_x + ((2 * l3) + (l3 / 2)), min_y + ((a3 * 2) + (a3 / 2)));

        norte = new Ponto(min_x + (l3 + (l3 / 2)), min_y + (a3 / 3));
        sul = new Ponto(min_x + (l3 + (l3 / 2)), min_y + (a3 * 2) + (a3 / 2));

        oeste = new Ponto(min_x + (l3 / 2), min_y + (a3) + (a3 / 2));
        leste = new Ponto(min_x + ((2 * l3) + (l3 / 2)), min_y + (a3) + (a3 / 2));

        centro = new Ponto(min_x + ((l3) + (l3 / 2)), min_y + (a3) + (a3 / 2));

        fmt.print("\t ->> NOROESTE ->> [{} - {}]]", noroeste.getX(), noroeste.getY());
        fmt.print("\t ->> NORTE    ->> [{} - {}]]", norte.getX(), norte.getY());
        fmt.print("\t ->> NORDESTE ->> [{} - {}]]", nordeste.getX(), nordeste.getY());

        fmt.print("\t ->> SUDOESTE ->> [{} - {}]]", sudoeste.getX(), sudoeste.getY());
        fmt.print("\t ->> SUL      ->> [{} - {}]]", sul.getX(), sul.getY());
        fmt.print("\t ->> SUDESTE  ->> [{} - {}]]", sudeste.getX(), sudeste.getY());

        fmt.print("\t ->> OESTE    ->> [{} - {}]]", oeste.getX(), oeste.getY());
        fmt.print("\t ->> LESTE    ->> [{} - {}]]", leste.getX(), leste.getY());

        fmt.print("\t ->> CENTRO    ->> [{} - {}]]", centro.getX(), centro.getY());

    }


    public String getStatus(Ponto localizacao) {

        int distancia_noroeste = Espaco2D.distancia_entre_pontos(noroeste, localizacao);
        int distancia_norte = Espaco2D.distancia_entre_pontos(norte, localizacao);
        int distancia_nordeste = Espaco2D.distancia_entre_pontos(nordeste, localizacao);

        int distancia_sudoeste = Espaco2D.distancia_entre_pontos(sudoeste, localizacao);
        int distancia_sul = Espaco2D.distancia_entre_pontos(sul, localizacao);
        int distancia_sudeste = Espaco2D.distancia_entre_pontos(sudeste, localizacao);

        int distancia_oeste = Espaco2D.distancia_entre_pontos(oeste, localizacao);
        int distancia_leste = Espaco2D.distancia_entre_pontos(leste, localizacao);
        int distancia_centro = Espaco2D.distancia_entre_pontos(centro, localizacao);


        String status = "NOROESTE";
        int valor = distancia_noroeste;

        if (distancia_norte < valor) {
            valor = distancia_norte;
            status = "NORTE";
        }

        if (distancia_nordeste < valor) {
            valor = distancia_nordeste;
            status = "NORDESTE";
        }

        if (distancia_sudoeste < valor) {
            valor = distancia_sudoeste;
            status = "SUDOESTE";
        }

        if (distancia_sul < valor) {
            valor = distancia_sul;
            status = "SUL";
        }

        if (distancia_sudeste < valor) {
            valor = distancia_sudeste;
            status = "SUDESTE";
        }

        if (distancia_oeste < valor) {
            valor = distancia_oeste;
            status = "OESTE";
        }

        if (distancia_leste < valor) {
            valor = distancia_leste;
            status = "LESTE";
        }

        if (distancia_centro < valor) {
            valor = distancia_centro;
            status = "CENTRO";
        }


        return status;
    }


    public static Lista<String> GET_SENTIDOS() {

        Lista<String> sentidos = new Lista<String>();

        sentidos.adicionar("NORTE");
        sentidos.adicionar("SUL");
        sentidos.adicionar("LESTE");
        sentidos.adicionar("OESTE");

        sentidos.adicionar("NORDESTE");
        sentidos.adicionar("NOROESTE");
        sentidos.adicionar("SUDESTE");
        sentidos.adicionar("SUDOESTE");

        sentidos.adicionar("CENTRO");


        return sentidos;
    }
}
