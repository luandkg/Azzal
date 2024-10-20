package libs.arquivos;

import libs.arquivos.binario.ArenaChunk;
import libs.luan.Lista;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.*;

public class Zipper {

    public static void ZIPAR_ARQUIVO(String arquivo_entrada, String arquivo_zipado) {

        try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(arquivo_zipado));
            File fileToZip = new File(arquivo_entrada);
            try {
                zipOut.putNextEntry(new ZipEntry(fileToZip.getName()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Files.copy(fileToZip.toPath(), zipOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void UNZIPAR_ARQUIVO(String arquivo_zipado, String arquivo_saida) {

        try {

            ZipInputStream zis = new ZipInputStream(new FileInputStream(arquivo_zipado));
            // list files in zip
            ZipEntry zipEntry = zis.getNextEntry();


            try (FileOutputStream fos = new FileOutputStream(arquivo_saida)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            }

            zis.closeEntry();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


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

    public static String DESCOMPACTAR(byte bytes[]) {

        String s = "";

        try {

            GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(bytes));

            s = stream_to_string(gzis);

            gzis.close();

        } catch (IOException ex) {

        }

        return s;
    }


    public static byte[] COMPACTAR(byte[] dados) {

        byte[] saida = null;

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(dados.length);
        try {
            GZIPOutputStream zipStream = new GZIPOutputStream(byteStream);
            try {
                zipStream.write(dados);
            } finally {
                zipStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                byteStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        saida = byteStream.toByteArray();

        return saida;
    }
}