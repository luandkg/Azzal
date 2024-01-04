package libs.arquivos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZ {

    public byte[] compactar(byte bytes_descompactados[]) {

        try (ByteArrayOutputStream compactador_bytes = new ByteArrayOutputStream()) {

            GZIPOutputStream compactador = new GZIPOutputStream(compactador_bytes);
            compactador.write(bytes_descompactados);

            return compactador_bytes.toByteArray();

        } catch (IOException e) {
            return null;
        }

    }

    public byte[] descompactar(byte bytes_compactados[]) {

        try (ByteArrayInputStream descompactador_bytes = new ByteArrayInputStream(bytes_compactados)) {

            GZIPInputStream descompactador = new GZIPInputStream(descompactador_bytes);
            return descompactador.readAllBytes();

        } catch (IOException e) {
            return null;
        }

    }
}
