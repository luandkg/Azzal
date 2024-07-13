package apps.app_azzal;

import libs.luan.EmCada;
import libs.luan.Lista;
import libs.luan.RefInt;
import libs.luan.fmt;

public class VamosCalcular {

    public void init() {

        Lista<RefInt> inteiros = criarListaRefInt(1, 10);

        RefInt taxa = new RefInt(4);

        RefInt somatorio = new RefInt(0);

        inteiros.paraCada(realizar_tabuada(taxa));

        inteiros.paraCada(realizar_soma(somatorio));

        fmt.print("");
        fmt.print("SOMATORIO  = {}", somatorio.get());

    }

    public EmCada<RefInt> realizar_tabuada(RefInt taxa) {

        return new EmCada<RefInt>() {
            @Override
            public void fazer(RefInt valor) {

                RefInt calculado = RefInt.multiplicar(valor, taxa);

                fmt.print("f({}) = {}", valor.get(), calculado.get());

                valor.set(calculado);
            }
        };
    }

    public EmCada<RefInt> realizar_soma(RefInt somatorio) {

        return new EmCada<RefInt>() {
            @Override
            public void fazer(RefInt valor) {
                somatorio.somar(valor);
            }
        };
    }

    public Lista<RefInt> criarListaRefInt(int de, int ate) {
        Lista<RefInt> inteiros = new Lista<RefInt>();
        for (int v = de; v <= ate; v++) {
            inteiros.adicionar(new RefInt(v));
        }
        return inteiros;
    }
}
