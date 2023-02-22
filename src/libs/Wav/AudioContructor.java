package libs.Wav;

import libs.arquivos.audio.AreaBinaria;
import libs.arquivos.audio.HZ;
import libs.arquivos.audio.HZControlador;
import libs.arquivos.audio.RepetidorBinario;

public class AudioContructor {

    public static void init(){

        CriarWav mCriarWAV = new CriarWav();
        mCriarWAV.criarArquivo("/home/luan/Música/musicas_hz/luan.wav");

        //mAU.play("/home/luan/Downloads/Music/luan.au");

        AreaBinaria ab = new AreaBinaria();
        //ArrayList<Integer> z0 = ab.getArea("/home/luan/Downloads/Music/luan.au", 0, 4 * 1024);

        //ab.mostrar(z0, 32);

        RepetidorBinario rb = new RepetidorBinario();

        //ArrayList<RepeticaoBinaria> repeticoes = rb.getRepeticoes(z0);

        System.out.println("Repeticoes : ");

        int rep_contagem = 0;

        //for (RepeticaoBinaria rep : repeticoes) {
        //   if (rep.getQuantidade() > 1) {
        //   System.out.println("REP :: " + rep.getValor() + " -->> " + rep.getQuantidade());
        //        rep_contagem += rep.getQuantidade();
        //     }
        //  }

        // System.out.println("Tamanho    :: " + z0.size());
        //  System.out.println("Repeticoes :: " + rep_contagem);
        //  System.out.println("Final      :: " + (z0.size() - rep_contagem));

        //HZControlador.converterToHZ("/home/luan/Downloads/Music/blurred.wav", "/home/luan/Downloads/Music/blurred.hz");
        // VerificarAU.converter("/home/luan/Downloads/Music/blurred.data", "/home/luan/Downloads/Music/blurred.au");


        //HZControlador.converterToHZ("/home/luan/Música/musicas_hz/luan.wav", "/home/luan/Música/musicas_hz/luan.hz");


        // HZControlador.converterToHZ("/home/luan/Música/musicas_hz/blurred.wav", "/home/luan/Música/musicas_hz/blurred.hz");

        //HZControlador.converterToHZ("/home/luan/Música/musicas_hz/Contraste.wav");



        boolean tocar = false;


        if (tocar) {

            String eArquivoHZ = "/home/luan/Música/musicas_hz/Contraste.hz";

            HZ audio = HZControlador.init(eArquivoHZ);

            while (audio.temMais()) {

                System.out.println("Amostra -->> " + audio.getMais() + " :: " + audio.getLendo());

                HZControlador.toque(audio);

            }

        }

    }
}
