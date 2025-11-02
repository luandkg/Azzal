package apps.app_biotzum.movimentacao;

import apps.app_biotzum.Organismo;
import libs.luan.Lista;

public interface Movimentador {
    void andar(Lista<Organismo> outros);
}
