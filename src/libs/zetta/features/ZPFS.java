package libs.zetta.features;

import libs.arquivos.IM;
import libs.arquivos.binario.ByteChunk;
import libs.luan.Opcional;
import libs.zetta.ZettaArquivo;
import libs.zetta.ZettaPasta;

import java.awt.image.BufferedImage;

public class ZPFS {

    public static Opcional<BufferedImage> GET_IMAGEM_INTERNA(ZettaPasta pasta, String arquivo_interno) {
        Opcional<ZettaArquivo> op = pasta.procurar_arquivo(arquivo_interno);
        if (op.isOK()) {

            byte[] bytes = op.get().getBytes();
            ByteChunk bc = new ByteChunk(bytes, bytes.length);

            return Opcional.OK(IM.ler_bytes(bc));
        }
        return Opcional.CANCEL();
    }

}
