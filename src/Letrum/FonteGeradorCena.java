package Letrum;

import Azzal.Cenarios.Cena;
import Azzal.Formatos.Retangulo;
import Azzal.Renderizador;
import Azzal.Teclado;
import Azzal.Utils.Cor;
import Azzal.Windows;
import Letrum.Maker.FonteGerador;
import Letrum.Fontes.G10;
import Letrum.Fontes.G20;
import Letrum.Fontes.G30;

import java.awt.*;


public class FonteGeradorCena extends Cena {

    private G10 T10;
    private G20 T20;
    private G30 T30;
    private G30 V30;

    private EditorDeTexto mEditorDeTexto;
    private Teclado mTeclado;


    public FonteGeradorCena() {

        FonteGerador eFonteGerador = new FonteGerador();
        eFonteGerador.gerarFonteImagemEJava("/home/luan/Imagens/G10.png", "/home/luan/Imagens/G10.java", 10);
        // eFonteGerador.gerarFonteImagemEJava("/home/luan/Imagens/G20.png", "/home/luan/Imagens/G20.java", 20);


        eFonteGerador.gerarFonteJava("/home/luan/Imagens/G15.java", 15);
        eFonteGerador.gerarFonteJava("/home/luan/Imagens/G20.java", 20);
        eFonteGerador.gerarFonteJava("/home/luan/Imagens/G30.java", 30);


    }


    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Auto Gerador de Fonte");
        mTeclado = eWindows.getTeclado();

        T10 = new G10();
        T20 = new G20();
        T30 = new G30();
        V30 = new G30(new Cor(100, 200, 0));

        mEditorDeTexto = new EditorDeTexto(500, 600, T30);
        mEditorDeTexto.setTexto("MAÇÃ");

    }

    @Override
    public void update(double dt) {

        mEditorDeTexto.update();
        mEditorDeTexto.receberTeclado(mTeclado);
    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        T10.setRenderizador(mRenderizador);
        T20.setRenderizador(mRenderizador);
        T30.setRenderizador(mRenderizador);
        V30.setRenderizador(mRenderizador);

        mRenderizador.drawRect_Pintado(new Retangulo(100, 250, 600, 200), new Cor(255, 0, 0));

        T10.escreva(100, 120, "Meu nome é Luan Alves Freitas");
        T20.escreva(100, 150, "Meu nome é Luan Alves Freitas");
        T30.escreva(100, 180, "Meu nome é Luan Alves Freitas");

        V30.escreva(100, 300, "1 + 3 = 4 >>  { [ ( 2 + 2 ) / 2 ] * 4 }");

        T10.escreva(100, 400, "Meu nome é Luan Alves Freitas");

        mEditorDeTexto.render(mRenderizador);
    }


}
