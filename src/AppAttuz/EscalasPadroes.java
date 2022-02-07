package AppAttuz;

import AppAttuz.Ferramentas.Escala;

import java.awt.*;

public class EscalasPadroes {

    public static Escala getEscalaTerrestre() {

        Escala mRelevoTerra = new Escala(1, 10);

        mRelevoTerra.set(10, new Color(100, 0, 0).getRGB());
        mRelevoTerra.set(9, new Color(120, 0, 0).getRGB());
        mRelevoTerra.set(8, new Color(141, 0, 0).getRGB());
        mRelevoTerra.set(7, new Color(230, 90, 50).getRGB());
        mRelevoTerra.set(6, new Color(245, 100, 60).getRGB());
        mRelevoTerra.set(5, new Color(245, 130, 60).getRGB());
        mRelevoTerra.set(4, new Color(253, 189, 130).getRGB());
        mRelevoTerra.set(3, new Color(255, 243, 181).getRGB());
        mRelevoTerra.set(2, new Color(80, 173, 114).getRGB());
        mRelevoTerra.set(1, new Color(111, 173, 114).getRGB());
        mRelevoTerra.set(0, new Color(255, 255, 255).getRGB());

        return mRelevoTerra;
    }

    public static Escala getEscalaAquatica() {

        Escala mRelevo = new Escala(1, 10);

        mRelevo.set(10, new Color(62, 140, 215).getRGB());
        mRelevo.set(9, new Color(62, 150, 215).getRGB());
        mRelevo.set(8, new Color(62, 161, 207).getRGB());
        mRelevo.set(7, new Color(85, 170, 212).getRGB());
        mRelevo.set(6, new Color(106, 177, 215).getRGB());
        mRelevo.set(5, new Color(130, 189, 220).getRGB());
        mRelevo.set(4, new Color(189, 220, 230).getRGB());
        mRelevo.set(3, new Color(208, 231, 234).getRGB());
        mRelevo.set(2, new Color(231, 245, 239).getRGB());
        mRelevo.set(1, new Color(220, 230, 239).getRGB());
        mRelevo.set(0, new Color(255, 255, 255).getRGB());

        return mRelevo;
    }

    public static Escala getEscalaTemperatura() {

        Escala mRelevoTerra = new Escala(1, 12);

        mRelevoTerra.set(12, new Color(250, 0, 0).getRGB());
        mRelevoTerra.set(11, new Color(200, 0, 0).getRGB());
        mRelevoTerra.set(10, new Color(150, 0, 0).getRGB());
        mRelevoTerra.set(9, new Color(130, 0, 0).getRGB());
        mRelevoTerra.set(8, new Color(120, 0, 0).getRGB());

        mRelevoTerra.set(7, new Color(0, 200, 160).getRGB());
        mRelevoTerra.set(6, new Color(0, 160, 100).getRGB());
        mRelevoTerra.set(5, new Color(0, 200, 60).getRGB());

        mRelevoTerra.set(4, new Color(0, 0, 100).getRGB());
        mRelevoTerra.set(3, new Color(0, 0, 150).getRGB());
        mRelevoTerra.set(2, new Color(0, 0, 200).getRGB());
        mRelevoTerra.set(1, new Color(0, 0, 250).getRGB());

        mRelevoTerra.set(0, new Color(255, 255, 255).getRGB());

        return mRelevoTerra;
    }

    public static Escala getEscalaDistancia() {

        Escala mRelevoTerra = new Escala(1, 10);

        mRelevoTerra.set(10, new Color(20, 20, 20).getRGB());
        mRelevoTerra.set(9, new Color(40, 40, 40).getRGB());
        mRelevoTerra.set(8, new Color(60, 60, 60).getRGB());
        mRelevoTerra.set(7, new Color(80, 80, 80).getRGB());
        mRelevoTerra.set(6, new Color(100, 100, 100).getRGB());
        mRelevoTerra.set(5, new Color(130, 130, 130).getRGB());
        mRelevoTerra.set(4, new Color(150, 150, 150).getRGB());
        mRelevoTerra.set(3, new Color(170, 170, 170).getRGB());
        mRelevoTerra.set(2, new Color(190, 190, 190).getRGB());
        mRelevoTerra.set(1, new Color(200, 200, 200).getRGB());
        mRelevoTerra.set(0, new Color(255, 255, 255).getRGB());

        return mRelevoTerra;
    }

    public static Escala getEscalaLatitude() {

        Escala mRelevoTerra = new Escala(1, 20);

        mRelevoTerra.set(1, new Color(0, 250, 0).getRGB());
        mRelevoTerra.set(2, new Color(0, 240, 0).getRGB());
        mRelevoTerra.set(3, new Color(0, 230, 0).getRGB());
        mRelevoTerra.set(4, new Color(0, 220, 0).getRGB());
        mRelevoTerra.set(5, new Color(0, 210, 0).getRGB());
        mRelevoTerra.set(6, new Color(0, 200, 0).getRGB());
        mRelevoTerra.set(7, new Color(0, 190, 0).getRGB());
        mRelevoTerra.set(8, new Color(0, 180, 0).getRGB());
        mRelevoTerra.set(9, new Color(0, 170, 0).getRGB());
        mRelevoTerra.set(10, new Color(0, 160, 0).getRGB());
        mRelevoTerra.set(11, new Color(0, 140, 0).getRGB());
        mRelevoTerra.set(12, new Color(0, 130, 0).getRGB());
        mRelevoTerra.set(13, new Color(0, 120, 0).getRGB());
        mRelevoTerra.set(14, new Color(0, 110, 0).getRGB());
        mRelevoTerra.set(15, new Color(0, 100, 0).getRGB());
        mRelevoTerra.set(16, new Color(0, 90, 0).getRGB());
        mRelevoTerra.set(17, new Color(0, 80, 0).getRGB());
        mRelevoTerra.set(18, new Color(0, 70, 0).getRGB());
        mRelevoTerra.set(19, new Color(0, 60, 0).getRGB());
        mRelevoTerra.set(20, new Color(0, 50, 0).getRGB());
        mRelevoTerra.set(0, new Color(255, 255, 255).getRGB());

        return mRelevoTerra;
    }

    public static Escala getEscalaUmidade() {

        Escala mRelevo = new Escala(1, 14);

        mRelevo.set(14, new Color(62, 140, 215).getRGB());
        mRelevo.set(13, new Color(62, 150, 215).getRGB());
        mRelevo.set(12, new Color(62, 161, 207).getRGB());
        mRelevo.set(11, new Color(85, 170, 212).getRGB());
        mRelevo.set(10, new Color(106, 177, 215).getRGB());
        mRelevo.set(9, new Color(130, 189, 220).getRGB());
        mRelevo.set(8, new Color(189, 220, 230).getRGB());
        mRelevo.set(7, new Color(208, 231, 234).getRGB());
        mRelevo.set(6, new Color(255, 0, 0).getRGB());
        mRelevo.set(5, new Color(200, 0, 0).getRGB());
        mRelevo.set(4, new Color(150, 0, 0).getRGB());
        mRelevo.set(3, new Color(100, 0, 0).getRGB());
        mRelevo.set(2, new Color(50, 0, 0).getRGB());
        mRelevo.set(1, new Color(25, 0, 0).getRGB());
        mRelevo.set(0, new Color(255, 255, 255).getRGB());

        return mRelevo;
    }

    public static Escala getEscalaConveccao() {

        Escala mRelevo = new Escala(1, 4);

        mRelevo.set(3, new Color(62, 140, 215).getRGB());
        mRelevo.set(2, new Color(220, 60, 20).getRGB());
        mRelevo.set(1, new Color(120, 160, 40).getRGB());

        mRelevo.set(0, new Color(255, 255, 255).getRGB());

        return mRelevo;
    }

    public static Escala getEscalaConveccaoMovimento() {

        Escala mRelevo = new Escala(1, 5);

        mRelevo.set(4, new Color(150, 220, 15).getRGB());
        mRelevo.set(3, new Color(200, 200, 30).getRGB());

        mRelevo.set(2, new Color(62, 140, 215).getRGB());
        mRelevo.set(1, new Color(220, 60, 20).getRGB());

        mRelevo.set(0, new Color(255, 255, 255).getRGB());

        return mRelevo;
    }

}
