package libs.zetta.fazendario;

import libs.arquivos.binario.Arquivador;
import libs.luan.Lista;
import libs.luan.fmt;

public class PlantacaoAcessoDireto {

    public static void REMOVER(Arquivador mArquivador) {


        int bytes_quantidade = mArquivador.get_u32();
        int blocos = mArquivador.get_u32();

        Lista<Long> blocos_alocados = new Lista<Long>();
        for (int b = 0; b < blocos; b++) {
            long bloco_ref = mArquivador.get_u64();

            fmt.print("\t -- BlocoRef :: {}", bloco_ref);
            blocos_alocados.adicionar(bloco_ref);

        }

        for (Long bloco : blocos_alocados) {

            mArquivador.setPonteiro(bloco);
            mArquivador.set_u8((byte) Fazendario.ESPACO_VAZIO_E_JA_ALOCADO);

        }


    }

}
