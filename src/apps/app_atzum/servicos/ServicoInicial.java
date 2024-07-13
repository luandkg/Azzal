package apps.app_atzum.servicos;

import apps.app_atzum.AtzumCreator;
import apps.app_atzum.utils.AtzumCreatorInfo;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.imagem.Imagem;
import libs.luan.fmt;

import java.awt.image.BufferedImage;

public class ServicoInicial {

    private static final String SERVICO_NOME = "ServicoInicial";


    public static void INIT() {
        AtzumCreatorInfo.iniciar(SERVICO_NOME + ".INIT");

        fmt.print(">> Organizando planeta");

        BufferedImage imagem_mapa_planeta = Imagem.GET_IMAGEM_POR_PIXEL_RGB(AtzumCreator.LOCAL_GET_ARQUIVO("dados_brutos/atzum_mapa_planeta.png"));

        Renderizador mapa_planeta=new Renderizador(imagem_mapa_planeta);

        fmt.print("\t + Largura   : {}",mapa_planeta.getLargura());
        fmt.print("\t + Altura    : {}",mapa_planeta.getAltura());


        Cores mCores = new Cores();

        AtzumCreator.NORMALIZAR_2_CORES_ABAIXO_DE(mapa_planeta, 100, mCores.getVermelho(), mCores.getPreto());


        Imagem.exportar(mapa_planeta.toImagemSemAlfa(),AtzumCreator.LOCAL_GET_ARQUIVO("build/planeta/atzum_planeta.png"));


        AtzumCreatorInfo.terminar(SERVICO_NOME + ".INIT");
        AtzumCreatorInfo.exibir_item(SERVICO_NOME + ".INIT");
    }


}
