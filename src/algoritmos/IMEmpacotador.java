package algoritmos;

import libs.arquivos.binario.Arquivador;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.fs.PastaFS;
import libs.luan.Strings;
import libs.luan.fmt;

public class IMEmpacotador {

    public static void empacotar(){

    //    DS.limpar("/home/luan/Imagens/atzum/build/tronarko/eita/eita.ds");
        //   DS.limpar("/home/luan/Imagens/atzum/build/tronarko/eita/eita_v2.ds");

        for (String arquivo : Strings.ORDENAR(new PastaFS("/home/luan/Imagens/atzum/build/tronarko/eita").getArquivosCaminhos())) {
            if(arquivo.endsWith(".png")){
                String nome = Strings.GET_DEPOIS(arquivo,"t");
                nome = Strings.GET_DEPOIS(nome,"t");
                nome = Strings.GET_DEPOIS(nome," ");
                nome = Strings.GET_ATE(nome,"C");

                fmt.print("{} -- {}", nome, arquivo);
                DS.adicionar("/home/luan/Imagens/atzum/build/tronarko/eita/eita.ds",nome.trim()+".png", Arquivador.GET_BYTES(arquivo));

                //  BufferedImage imagem = Imagem.GET_IMAGEM_POR_PIXEL_RGB(arquivo);
                //    DS.adicionar("/home/luan/Imagens/atzum/build/tronarko/eita/eita_v2.ds",nome.trim()+".im", IM.salvar_to_bytes(imagem));

            }else{
                fmt.print("#PULAR -->> {}",arquivo);
            }

        }

    }

    public static void desempacotar(){

        for (DSItem arquivo : DS.ler_todos("/home/luan/Imagens/atzum/build/tronarko/eita/eita.ds")) {
            Arquivador.CONSTRUIR_ARQUIVO("/home/luan/Imagens/atzum/build/tronarko/eita/"+arquivo.getNome(),arquivo.getBytes());
        }

    }

}
