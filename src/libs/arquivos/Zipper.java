package libs.arquivos;

import libs.luan.Lista;
import libs.xlsx.ArenaChunk;
import libs.xlsx.Documento;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zipper {

    public static Lista<Documento> EXTRAIR_DOCUMENTOS_COM_EXTENSAO(String eArquivo,String eExtensao) {
        Lista<Documento> documentos = new Lista<Documento>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(eArquivo))) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {


                if (zipEntry.getName().endsWith(eExtensao)) {

                    ArenaChunk chunks = new ArenaChunk();

                    byte [] buffer = new byte[1024];
                    int len = 0;

                    while ((len = zis.read(buffer)) > 0) {
                        chunks.adicionar(buffer,len);
                    }


                    documentos.adicionar(new Documento(zipEntry.getName(), new String(chunks.toBytes())));


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

}
