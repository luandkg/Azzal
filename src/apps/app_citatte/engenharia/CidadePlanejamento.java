package apps.app_citatte.engenharia;

import libs.azzal.geometria.Ponto;
import libs.luan.Lista;

public class CidadePlanejamento {

    public  Lista<AvenidaViaria> avenidas ;

    public  int direcao = EngenhariaRodoviaria.DIRECAO_DIREITA;
    public  int direcao_anterior = -1;

    public AvenidaViaria avenida_anterior = null;

    public Ponto inicio;

    public int limite_acima = 0;

    public int limite_esquerda = 0;
    public   int maximo_direita = 0;
    public   int maximo_descendo =0;


}
