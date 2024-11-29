package libs.zetta.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;

public class ItemAcessarDireto {


    public static String LER(Arquivador mArquivador,long ponteiro_dados){

        mArquivador.setPonteiro(ponteiro_dados);

        int item_tipo = mArquivador.get_u8();

        String ss = "";

        if (item_tipo == Fazendario.OBJETO_PEQUENO) {

            int texto_tamanhho = mArquivador.get_u32();

            byte[] bytes = mArquivador.get_u8_array(texto_tamanhho);
            ss = Strings.GET_STRING_VIEW(bytes);

        } else if (item_tipo == Fazendario.OBJETO_GRANDE) {

            fmt.print("Ler Grande ---->> ");
            int bytes_quantidade = mArquivador.get_u32();
            int blocos = mArquivador.get_u32();

            fmt.print("\t ++ Tamanho Bytes     :: {}", bytes_quantidade);
            fmt.print("\t ++ Quantidade Blocos :: {}", blocos);


            Lista<Long> blocos_alocados = new Lista<Long>();
            for (int b = 0; b < blocos; b++) {
                long bloco_ref = mArquivador.get_u64();

                fmt.print("\t -- BlocoRef :: {}", bloco_ref);
                blocos_alocados.adicionar(bloco_ref);

            }

            byte[] bytes_completo = new byte[bytes_quantidade];

            int bytes_i = 0;
            int bytes_o = (int) Fazendario.TAMANHO_AREA_ITEM;

            if (bytes_o > bytes_quantidade) {
                bytes_o = bytes_quantidade;
            }

            for (Long bloco : blocos_alocados) {

                mArquivador.setPonteiro(bloco);

                int area_status = mArquivador.get_u8();
                long area_ponteiro = mArquivador.get_u64();
                long area_ponteiro_dados = mArquivador.get_u64();

                mArquivador.setPonteiro(area_ponteiro_dados);

                while (bytes_i < bytes_o) {
                    bytes_completo[bytes_i] = mArquivador.get();
                    bytes_i += 1;
                }

                bytes_o += (int) Fazendario.TAMANHO_AREA_ITEM;
                if (bytes_o > bytes_quantidade) {
                    bytes_o = bytes_quantidade;
                }
            }

            ss = Strings.GET_STRING_VIEW(bytes_completo);


        }

        return ss;

    }
}
