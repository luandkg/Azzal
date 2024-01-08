package apps.app_citatte.testes;

import apps.app_citatte.AreaAdministravel;
import apps.app_citatte.Citatte;
import apps.app_citatte.engenharia.AvenidaViaria;
import apps.app_citatte.engenharia.EngenhariaRodoviaria;
import libs.azzal.Cores;
import libs.azzal.geometria.Ponto;
import libs.luan.Lista;
import libs.luan.fmt;

public class RenderAvenidasConectadas {

    public static void init(Citatte mCitatte, String arquivo) {

        Cores mCores = new Cores();

        mCitatte.zerar();


        for (AreaAdministravel area : mCitatte.getAreasAdministraveis()) {

            mCitatte.get().drawRect(area.getArea(), mCores.getBranco());

            if (area.getNome().contains("ESPECIAL")) {
                mCitatte.get().drawRect_Pintado(area.getArea(), mCores.getBranco());
            }

        }

        EngenhariaRodoviaria.draw_avenidas_amarelo(mCitatte.get(), mCitatte.getAvenidas());


        Lista<AvenidaViaria> ir_andando = new Lista<AvenidaViaria>();
        ir_andando.adicionar(mCitatte.getAvenidas().get(0));


        int indo = 0;
        boolean passando = true;

        while (passando) {

            int mais = 0;

            for (AvenidaViaria avenida : ir_andando) {
                for (Ponto cruz : avenida.getConexoes()) {

                    Lista<AvenidaViaria> cruz_avenidas = new Lista<AvenidaViaria>();

                    for (AvenidaViaria proc_avenida : mCitatte.getAvenidas()) {
                        if (proc_avenida.estaEm(cruz)) {
                            cruz_avenidas.adicionar(proc_avenida);
                        }
                    }

                    for (AvenidaViaria cruz_avenida : cruz_avenidas) {
                        if (!ir_andando.existe(cruz_avenida)) {
                            ir_andando.adicionar(cruz_avenida);
                            mais += 1;
                        }
                    }


                }
            }

            if (mais == 0) {
                passando = false;
            }

            indo += 1;
            fmt.print("Indo = {} -> {}", indo, mais);
        }


        EngenhariaRodoviaria.draw_avenidas_com_cor(mCitatte.get(), ir_andando, mCores.getVermelho());


        mCitatte.exportar_imagem(arquivo);

    }

}
