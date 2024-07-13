package apps.app_atzum.servicos;

import apps.app_attuz.Ferramentas.Espaco2D;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import apps.app_atzum.utils.AtzumCreatorInfo;
import libs.arquivos.QTT;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.HSV;
import libs.imagem.Imagem;
import libs.luan.fmt;

public class ServicoCorrelacionar {

    private static final String SERVICO_NOME = "ServicoCorrelacionar";


    public static void INIT() {

        AtzumCreatorInfo.iniciar(SERVICO_NOME + ".INIT");

        Cores mCores = new Cores();

        AtzumTerra planeta = new AtzumTerra();
        Renderizador render_c1 = Renderizador.construir(planeta.getLargura(), planeta.getAltura(), mCores.getPreto());
        Renderizador render_c2 = Renderizador.construir(planeta.getLargura(), planeta.getAltura(), mCores.getPreto());

        fmt.print("\t >> Obtendo dados");

        QTT umidade = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("umidade.qtt"));
        QTT temperatura_t1 = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("temperatura_t1.qtt"));
        QTT temperatura_t2 = QTT.getTudo(AtzumCreator.DADOS_GET_ARQUIVO("temperatura_t2.qtt"));

        fmt.print("\t >> Processando");

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isTerra(x, y)) {

                    int umidade_valor = umidade.getValor(x, y);
                    int temperatura_valor1 = temperatura_t1.getValor(x, y);
                    int temperatura_valor2 = temperatura_t2.getValor(x, y);

                    int cor = 0;
                    int intensidade_1 = 0;
                    int intensidade_2 = 0;

                    if (umidade_valor >= 0 && umidade_valor < 25) {
                        cor = 30;
                    } else if (umidade_valor >= 25 && umidade_valor < 50) {
                        cor = 100;
                    } else if (umidade_valor >= 50 && umidade_valor < 75) {
                        cor = 200;
                    } else {
                        cor = 280;
                    }

                    if(temperatura_valor1<15) {
                        intensidade_1=30;
                    }else if(temperatura_valor1>30){
                        intensidade_1=70;
                    }else{
                        intensidade_1=50;
                    }

                    if(temperatura_valor2<15) {
                        intensidade_2=30;
                    }else if(temperatura_valor2>30){
                        intensidade_2=70;
                    }else{
                        intensidade_2=50;
                    }

                    render_c1.setPixel(x, y, new HSV(cor,50,intensidade_1));
                    render_c2.setPixel(x, y, new HSV(cor,50,intensidade_2));


                }
            }
        }

        Imagem.exportar(render_c1.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/umidade_vs_temperatura_1.png"));
        Imagem.exportar(render_c2.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/umidade_vs_temperatura_2.png"));


        AtzumCreatorInfo.terminar(SERVICO_NOME + ".INIT");
        AtzumCreatorInfo.exibir_item(SERVICO_NOME + ".INIT");

    }

}
