package apps.app_llcripto;

import libs.azzal.AzzalUnico;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.llcripto.LLCripto;

public class App_LLCripto extends Cena {


    public static void INIT(){
        AzzalUnico.unico("App_LLCripto", 1100, 900, new App_LLCripto());
    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("LLCripto 1.0");

        // LLCripto.demonstracao();

        String entrada = "Luan Alves Freitas";
        String chave = "1992";

        byte[] valores = LLCripto.criptografar(entrada, chave);

        System.out.println("ALGORITMO LLCRIPTO");
        System.out.println("---------------------------------------");
        System.out.println("CHAVE    :: " + chave);
        System.out.println("ENTRADA  :: " + entrada);
        System.out.println("---------------------------------------");
        System.out.println("CIFRAR   :: " + "{ " + LLCripto.bytes_to_sequencia(valores) + " }");
        System.out.println("DECIFRAR :: " + LLCripto.descriptografar(valores, chave));

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Renderizador g) {

    }
}
