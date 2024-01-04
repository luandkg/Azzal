package apps.app_citatte;

import apps.app_citatte.engenharia.*;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.fmt;

public class CitatteConstructor {

    public static void criar_cidade(Renderizador mCidade, Citatte mCitatte) {

        mCitatte.getAvenidas().limpar();
        mCitatte.getAreasAdministraveis().limpar();

        String local_assets = "/home/luan/assets";

        fmt.print("-->> CRIAR CIDADE !");
        Lista<AvenidaViaria> avenidas = EngenhariaRodoviaria.criar_cidade(mCidade);

        fmt.print("-->> ORGANIZAR CRUZAMENTOS !");
        EngenhariaDeObras.organizar_cruzamentos(avenidas);

        fmt.print("-->> POSICIONAR CRUZAMENTOS !");
        EngenhariaRodoviaria.draw_avenidas_analise_cruzamentos(avenidas, mCidade);


        fmt.print("-->> ORGANIZAR HABITATES !");
        Habitacional.init(mCidade, avenidas, mCitatte);


        // EngenhariaRodoviaria.analisar_cruzamentos();

        fmt.print("-->> LOCALIZAR AREAS PUBLICAS !");
        LocalizadorDeAreasPublicas.localizar(mCidade, avenidas, mCitatte);

        //  EgenhariaDeObras.aumentar_avenida_vertical(avenidas, mCidade,true);
        //  EgenhariaDeObras.aumentar_avenida_horizontal(avenidas, mCidade,false);

        mCitatte.getAvenidas().adicionar_varios(avenidas);

        fmt.print("-->> GUARDAR ARQUIVO !");
        to_arquivo(local_assets + "/cidadela.dkg", avenidas, mCitatte.getAreasAdministraveis());

        mCidade.exportarSemAlfa(local_assets + "/cidade_gama.png");

    }


    public static void to_arquivo(String arquivo, Lista<AvenidaViaria> avenidas, Lista<AreaAdministravel> areas) {

        DKG documento = new DKG();
        DKGObjeto documento_raiz = documento.unicoObjeto("Citatte");


        DKGObjeto objeto_avenidas = documento_raiz.unicoObjeto("Avenidas");

        for (AvenidaViaria avenida : avenidas) {
            DKGObjeto objeto_avenida = objeto_avenidas.criarObjeto("Avenida");
            objeto_avenida.identifique("Tipo", avenida.getTipo());
            objeto_avenida.identifique("X", avenida.getX());
            objeto_avenida.identifique("Y", avenida.getY());
            objeto_avenida.identifique("Comprimento", avenida.getComprimento());
        }

        DKGObjeto objeto_areas = documento_raiz.unicoObjeto("Areas");

        for (AreaAdministravel area : areas) {
            DKGObjeto objeto_area = objeto_areas.criarObjeto("Area");
            objeto_area.identifique("Nome", area.getNome());
            objeto_area.identifique("X", area.getLocalizacao().getX());
            objeto_area.identifique("Y", area.getLocalizacao().getY());
            objeto_area.identifique("Largura", area.getArea().getLargura());
            objeto_area.identifique("Altura", area.getArea().getAltura());
        }


        documento.salvar(arquivo);
    }


    public static void abrir(String arquivo, Renderizador mCidade, Citatte mCitatte) {


        Cores mCores = new Cores();

        mCidade.drawRect_Pintado(0, 0, 1500, 900, mCores.getPreto());


        mCitatte.getAreasAdministraveis().limpar();
        mCitatte.getAvenidas().limpar();


        DKG documento = DKG.ABRIR_DO_ARQUIVO(arquivo);
        DKGObjeto documento_raiz = documento.unicoObjeto("Citatte");
        DKGObjeto objeto_avenidas = documento_raiz.unicoObjeto("Avenidas");


        for (DKGObjeto objeto_avenida : objeto_avenidas.getObjetos()) {

            String tipo = objeto_avenida.identifique("Tipo").getValor();
            int px = objeto_avenida.identifique("X").getInteiro(0);
            int py = objeto_avenida.identifique("Y").getInteiro(0);
            int comprimento = objeto_avenida.identifique("Comprimento").getInteiro(0);

            AvenidaViaria av = new AvenidaViaria(mCitatte.getAvenidas().getQuantidade());
            mCitatte.getAvenidas().adicionar(av);


            if (tipo.contentEquals("Vertical")) {

                for (int construindo = 0; construindo < comprimento; construindo++) {
                    av.adicionar(new Ponto(px, py + construindo));
                }
            } else if (tipo.contentEquals("Horizontal")) {

                for (int construindo = 0; construindo < comprimento; construindo++) {
                    av.adicionar(new Ponto(px + construindo, py));
                }
            }


            // fmt.print("TIPO = {}", tipo);

        }


        EngenhariaDeObras.organizar_cruzamentos(mCitatte.getAvenidas());

        for (AvenidaViaria avenida : mCitatte.getAvenidas()) {

            int cruzamentos = avenida.getCruzamentos();

            for (Ponto px : avenida.getPontos()) {
                if (cruzamentos == 0) {
                    mCidade.drawPixel(px.getX(), px.getY(), mCores.getBranco());
                } else if (cruzamentos == 1) {
                    mCidade.drawPixel(px.getX(), px.getY(), mCores.getAzul());
                } else if (cruzamentos == 2) {
                    mCidade.drawPixel(px.getX(), px.getY(), mCores.getAmarelo());
                } else {
                    mCidade.drawPixel(px.getX(), px.getY(), mCores.getVerde());
                }
            }


        }


        //  Habitacional.init(mCidade, mCitatte.getAvenidas(), mCitatte);
        //  LocalizadorDeAreasPublicas.localizar(mCidade, mCitatte.getAvenidas(), mCitatte);

        DKGObjeto objeto_areas = documento_raiz.unicoObjeto("Areas");


        for (DKGObjeto objeto_area : objeto_areas.getObjetos()) {

            String nome = objeto_area.identifique("Nome").getValor();
            int px = objeto_area.identifique("X").getInteiro(0);
            int py = objeto_area.identifique("Y").getInteiro(0);
            int largura = objeto_area.identifique("Largura").getInteiro(0);
            int altura = objeto_area.identifique("Altura").getInteiro(0);

            mCitatte.area_criar(new Retangulo(px, py, largura, altura), nome);
        }

    }


}
