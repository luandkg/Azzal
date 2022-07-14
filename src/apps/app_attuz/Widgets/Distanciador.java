package apps.app_attuz.Widgets;

import apps.app_attuz.Mapa.Local;
import apps.app_attuz.Politicamente.Cidades;
import apps.app_attuz.Politicamente.EquipamentosDeEngenharia;
import azzal.Formatos.Ponto;
import azzal.Renderizador;
import azzal.Utils.Cor;
import apps.appLetrum.Fonte;
import apps.appLetrum.Maker.FonteRunTime;
import azzal_ui.Interface.Acao;
import azzal_ui.Interface.BotaoCor;
import azzal_ui.Interface.Clicavel;

import java.awt.*;
import java.util.ArrayList;

public class Distanciador {

    private Fonte pequeno;
    private boolean temPrimeiro;
    private boolean temSegundo;
    private Ponto p1;
    private Ponto p2;

    private boolean m_isSelecionado;
    private boolean m_primeiro;
    private boolean m_segundo;


    public Distanciador(Clicavel eClicavel) {
        pequeno = new FonteRunTime(Cor.getRGB(Color.BLACK), 12);

        temPrimeiro = false;
        temSegundo = false;

        p1 = null;
        p2 = null;
        m_isSelecionado = false;
        m_primeiro = false;
        m_segundo = false;

        BotaoCor BTN_PRIMEIRO = eClicavel.criarBotaoCor(new BotaoCor(2000, 600, 50, 50, new Cor(26, 188, 156)));

        BTN_PRIMEIRO.setAcao(new Acao() {
            @Override
            public void onClique() {
                m_isSelecionado = true;
                m_primeiro = true;
                m_segundo = false;
            }
        });

        BotaoCor BTN_SEGUNDO = eClicavel.criarBotaoCor(new BotaoCor(2060, 600, 50, 50, new Cor(26, 188, 156)));

        BTN_SEGUNDO.setAcao(new Acao() {
            @Override
            public void onClique() {
                m_isSelecionado = true;
                m_primeiro = false;
                m_segundo = true;
            }
        });

    }

    public boolean isSelecionando() {
        return m_isSelecionado;
    }

    public void marcar(int px, int py) {
        if (m_primeiro) {
            p1 = new Ponto(px, py);
            temPrimeiro = true;
        } else if (m_segundo) {
            p2 = new Ponto(px, py);
            temSegundo = true;
        }
    }

    public void draw(Renderizador render, ArrayList<Local> mLocais) {

        pequeno.setRenderizador(render);


        if (temPrimeiro) {
            render.drawRect_Pintado(2000 + 15, 670, 10, 10, new Cor(0, 255, 0));

            String cidade = Cidades.getNomeMaisProximo(mLocais, p1.getX(), p1.getY(), 50);

            if (cidade.length() > 0) {
                pequeno.escreva(2000 + 25, 670 - 5, "Ponto 1 = " + p1.getX() + ":" + p1.getY() + " -- " + cidade);
            } else {
                pequeno.escreva(2000 + 25, 670 - 5, "Ponto 1 = " + p1.getX() + ":" + p1.getY());
            }
        }

        if (temSegundo) {

            render.drawRect_Pintado(2000 + 15, 690, 10, 10, new Cor(0, 255, 0));

            String cidade = Cidades.getNomeMaisProximo(mLocais, p2.getX(), p2.getY(), 50);

            if (cidade.length() > 0) {
                pequeno.escreva(2000 + 25, 690 - 5, "Ponto 2 = " + p2.getX() + ":" + p2.getY() + " -- " + cidade);

            } else {
                pequeno.escreva(2000 + 25, 690 - 5, "Ponto 2 = " + p2.getX() + ":" + p2.getY());
            }

        }

        if (temPrimeiro && temSegundo) {

            int distancia = EquipamentosDeEngenharia.distancia(p1.getX(), p1.getY(), p2.getX(), p2.getY());

            int velocidade_rapido = 80;
            int velocidade_lento = 35;

            float tempo_rapido = EquipamentosDeEngenharia.tempo_de_viagem(distancia, velocidade_rapido);
            float tempo_lento = EquipamentosDeEngenharia.tempo_de_viagem(distancia, velocidade_lento);

            // pequeno.escreva(2000 + 15, 710, "Valor = " + valor);
            pequeno.escreva(2000 + 15, 710, "Distância = " + EquipamentosDeEngenharia.distanciaComUnidade(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
            pequeno.escreva(2000 + 15, 750, EquipamentosDeEngenharia.getDescricaoViagem(velocidade_rapido, tempo_rapido));
            pequeno.escreva(2000 + 15, 770, EquipamentosDeEngenharia.getDescricaoViagem(velocidade_lento, tempo_lento));

        }

    }

    public Ponto getP1() {
        return p1;
    }

    public Ponto getP2() {
        return p2;
    }

    public boolean temP1() {
        return temPrimeiro;
    }

    public boolean temP2() {
        return temSegundo;
    }

    public boolean temPontos() {
        if (temPrimeiro && temSegundo) {
            return true;
        } else {
            return false;
        }
    }
}
