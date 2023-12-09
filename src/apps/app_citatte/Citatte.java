package apps.app_citatte;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_citatte.engenharia.AvenidaViaria;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Opcional;

public class Citatte {

    private Renderizador mCidade;

    private Lista<AvenidaViaria> mAvenidas;

    private Lista<AreaAdministravel> mAreas;

    public Citatte() {

        Cores mCores = new Cores();

        mCidade = new Renderizador(Imagem.criarEmBranco(1500, 900));
        mCidade.drawRect_Pintado(0, 0, 1500, 900, mCores.getPreto());

        mAvenidas = new Lista<AvenidaViaria>();
        mAreas = new Lista<AreaAdministravel>();

    }


    public Citatte(int x, int y) {

        Cores mCores = new Cores();

        mCidade = new Renderizador(Imagem.criarEmBranco(x, y));
        mCidade.drawRect_Pintado(0, 0, x, y, mCores.getPreto());

        mAvenidas = new Lista<AvenidaViaria>();
        mAreas = new Lista<AreaAdministravel>();

    }

    public Renderizador get() {
        return mCidade;
    }

    public Lista<AvenidaViaria> getAvenidas() {
        return mAvenidas;
    }

    public Lista<AreaAdministravel> getAreasAdministraveis() {
        return mAreas;
    }


    public void area_criar(Retangulo area, String eNome) {
        mAreas.adicionar(new AreaAdministravel(area, eNome));
    }


    public Opcional<AreaAdministravel> procurar(int px, int py) {

        for (AreaAdministravel proc : mAreas) {
            if (proc.isDentro(px, py)) {
                return Opcional.OK(proc);
            }
        }

        return Opcional.CANCEL();
    }

    public int contagemAreas() {
        return mAreas.getQuantidade();
    }


    public void zerar() {
        Cores mCores = new Cores();
        mCidade.drawRect_Pintado(0, 0, mCidade.getLargura(), mCidade.getAltura(), mCores.getPreto());
    }


    public boolean tem_construcao(int px, int py, int l, int a) {

        boolean ret = false;

        for (int y = py; y < (py + a); y++) {
            for (int x = px; x < (px + l); x++) {
                for (AreaAdministravel proc : mAreas) {
                    if (proc.isDentro(x, y)) {
                        ret = true;
                        break;
                    }
                }
                if (ret) {
                    break;
                }
            }
            if (ret) {
                break;
            }
        }


        return ret;
    }


    public Lista<AreaAdministravel> getAreasProximasDaAvenida(AvenidaViaria av, int proximidade) {
        Lista<AreaAdministravel> areas = new Lista<AreaAdministravel>();


        for (AreaAdministravel area : getAreasAdministraveis()) {

            for (Ponto pt : av.getPontos()) {
                if (Espaco2D.distancia_entre_pontos(pt.getX(), pt.getY(), area.getCentroLocalizacao().getX(), area.getCentroLocalizacao().getY())<=proximidade){
                    areas.adicionar(area);
                }

            }

        }


        return areas;
    }


    public void exportar_imagem(String arquivo) {
        get().exportarSemAlfa(arquivo);
    }
}
