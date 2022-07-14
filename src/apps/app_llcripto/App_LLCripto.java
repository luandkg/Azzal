package apps.app_llcripto;

import azzal.Cenarios.Cena;
import azzal.Renderizador;
import azzal.Windows;
import libs.llcripto.LLCripto;

public class App_LLCripto extends Cena {

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("LLCripto 1.0");

        // LLCripto.demonstracao();

        byte[] valores = LLCripto.criptografar("Luan Alves Freitas", "1992");

        System.out.println("CIFRAR   :: " + LLCripto.bytes_to_sequencia(valores));
        System.out.println("DECIFRAR :: " + LLCripto.descriptografar(valores, "1992a"));

    }

    @Override
    public void update(double dt) {

    }

    @Override
    public void draw(Renderizador g) {

    }
}
