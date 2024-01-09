package libs.arquivos;

import libs.arquivos.binario.ArenaChunk;
import libs.luan.Lista;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zipper {

    public static Lista<DocumentoTexto> EXTRAIR_DOCUMENTOS_COM_EXTENSAO(String eArquivo, String eExtensao) {
        Lista<DocumentoTexto> documentos = new Lista<DocumentoTexto>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(eArquivo))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {

                if (zipEntry.getName().endsWith(eExtensao)) {

                    ArenaChunk chunks = new ArenaChunk();

                    byte[] buffer = new byte[1024];
                    int len = 0;

                    while ((len = zis.read(buffer)) > 0) {
                        chunks.adicionar(buffer, len);
                    }

                    documentos.adicionar(new DocumentoTexto(zipEntry.getName(), new String(chunks.toBytes())));

                }
                zipEntry = zis.getNextEntry();
            }

            zis.closeEntry();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return documentos;
    }

    public static String stream_to_string(InputStream zis) {

        ArenaChunk chunks = new ArenaChunk();

        byte[] buffer = new byte[1024];
        int len = 0;

        try {
            while ((len = zis.read(buffer)) > 0) {
                chunks.adicionar(buffer, len);
            }
        } catch (IOException e) {

        }

        return new String(chunks.toBytes());

    }

    public static String GET_TEXT_FROM_BYTES(byte bytes[]) {

        String s = "";

        try {

            GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(bytes));

            s = stream_to_string(gzis);

            gzis.close();

        } catch (IOException ex) {

        }

        return s;
    }

}
