package Arquivos.Audio;

import Arquivos.Binario.Arquivador;
import Arquivos.Binario.Inteiro;

import java.io.IOException;
import java.util.ArrayList;

public class AreaBinaria {

    public ArrayList<Integer> getArea(String eArquivo, int inicio,int tamanho) {

        Arquivador arquivador = new Arquivador(eArquivo);

        ArrayList<Integer> bins = new ArrayList<Integer>();

        arquivador.setPonteiro(inicio);

        for (int i = 0; i < tamanho; i++) {

            int iByte = Inteiro.byteToInt(arquivador.readByte());
            bins.add(iByte);

        }

        try {
            arquivador.fechar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bins;
    }

    public ArrayList<Integer> getAreaGrande(String eArquivo, long inicio,long tamanho) {

        Arquivador arquivador = new Arquivador(eArquivo);

        ArrayList<Integer> bins = new ArrayList<Integer>();

        arquivador.setPonteiro(inicio);

        for (int i = 0; i < tamanho; i++) {

            if (arquivador.getPonteiro() < arquivador.getLength()){
                int iByte = Inteiro.byteToInt(arquivador.readByte());
                bins.add(iByte);
            }


        }

        try {
            arquivador.fechar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return bins;
    }

    public ArrayList<Integer> getAreaGrandeBuffer(byte[] buffer, long inicio,long tamanho) {


        ArrayList<Integer> bins = new ArrayList<Integer>();

int pt = (int)inicio;

        for (int i = 0; i < tamanho; i++) {

            if (pt < buffer.length){
                int iByte = Inteiro.byteToInt(buffer[pt]);
                bins.add(iByte);
                pt+=1;
            }


        }


        return bins;
    }

    public void mostrar(ArrayList<Integer> bins) {

        int tamanho = bins.size();
        int eLinha = 10;
        int e = 0;

        for (int i = 0; i < tamanho; i++) {

            int iByte = bins.get(i);

            String s1 = String.valueOf(iByte);


            if (s1.length() == 1) {
                s1 = "00" + s1;
            } else if (s1.length() == 2) {
                s1 = "0" + s1;
            }

            System.out.print(s1 + " ");

            e += 1;
            if (e == eLinha) {
                e = 0;
                System.out.print("\n");
            }
        }

    }

    public void mostrar(ArrayList<Integer> bins,int colunas) {

        int tamanho = bins.size();
        int eLinha = colunas;
        int e = 0;

        for (int i = 0; i < tamanho; i++) {

            int iByte = bins.get(i);

            String s1 = String.valueOf(iByte);


            if (s1.length() == 1) {
                s1 = "00" + s1;
            } else if (s1.length() == 2) {
                s1 = "0" + s1;
            }

            System.out.print(s1 + " ");

            e += 1;
            if (e == eLinha) {
                e = 0;
                System.out.print("\n");
            }
        }

    }

    public void mostrarBuffer(byte[] bins) {

        int tamanho = bins.length;
        int eLinha = 10;
        int e = 0;

        for (int i = 0; i < tamanho; i++) {

            int iByte = Inteiro.byteToInt(bins[i]);

            String s1 = String.valueOf(iByte);


            if (s1.length() == 1) {
                s1 = "00" + s1;
            } else if (s1.length() == 2) {
                s1 = "0" + s1;
            }

            System.out.print(s1 + " ");

            e += 1;
            if (e == eLinha) {
                e = 0;
                System.out.print("\n");
            }
        }

    }

}
