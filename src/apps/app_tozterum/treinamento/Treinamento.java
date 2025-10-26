package apps.app_tozterum.treinamento;

import algoritmos.AtividadesLL;
import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.tronarko.Superarkos;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import servicos.ASSETS;

public class Treinamento {

    public static void EXPORTAR(Lista<Entidade> dados, String nome) {


        Cor COR_FUNDO = Cor.getHexCor("#212121");
        Cor BRANCO = new Cor(255, 255, 255);


        Cor COR_VERMELHO = Cor.getHexCor("#B92B27");
        Cor COR_AMARELO = Cor.getHexCor("#FFFC00");
        Cor COR_AZUL = Cor.getHexCor("#0061FF");
        Cor COR_VERDE = Cor.getHexCor("#09B83E");
        Cor COR_LARANJA = Cor.getHexCor("#FF5700");
        Cor COR_ROXA = Cor.getHexCor("#410093");
        Cor COR_ROSA = Cor.getHexCor("#FF0084");
        Cor COR_ESTRANHA = Cor.getHexCor("#DA552F");
        Cor COR_OMBRO = Cor.getHexCor("#25D366");



        Renderizador render = Renderizador.CONSTRUIR(500, 600, COR_FUNDO);
        render.drawRect_Pintado(0, 0, 500, 600, COR_FUNDO);

        Fonte ft_branca_80 = new FonteRunTime(new Cor(255, 255, 255), 60);
        ft_branca_80.setRenderizador(render);

        ft_branca_80.escrevaCentralizado(250, 20, "@TREINO");


        Fonte ft_branca_pequeno = new FonteRunTime(new Cor(255, 255, 255), 10);
        ft_branca_pequeno.setRenderizador(render);

        Fonte ft_verde_pequeno = new FonteRunTime(COR_VERDE, 10);
        ft_verde_pequeno.setRenderizador(render);

        Tozte HOJE = Tronarko.getTozte();

        ft_branca_pequeno.escrevaCentralizado(250, 170, HOJE.getHiperarko_Status().toString() + " de "+HOJE.getTronarko());


        render.drawRect_Pintado(25, 130, 450, 5, BRANCO);

        int barra_px = 25;
        int taxa = 450 / 50;


        Lista<Entidade> atividades = AtividadesLL.ACADEMIA();


        ENTT.EXIBIR_TABELA(atividades);


        boolean tem_peito = false;
        boolean tem_biceps = false;
        boolean tem_triceps =false;
        boolean tem_costas =false;
        boolean tem_ombros =false;
        boolean tem_perna =false;


        for (Entidade exercicio : dados) {
            if (exercicio.is("Tipo", "PEITO")) {
                tem_peito = true;
            }else     if (exercicio.is("Tipo", "BICEPS")) {
                tem_biceps = true;
            }else     if (exercicio.is("Tipo", "TRICEPS")) {
                tem_triceps = true;
            }else     if (exercicio.is("Tipo", "COSTAS")) {
                tem_costas = true;
            }else     if (exercicio.is("Tipo", "OMBROS")) {
                tem_ombros = true;
            }else     if (exercicio.is("Tipo", "PERNA")) {
                tem_perna = true;
            }
        }

        int s = 1;

        Lista<Entidade> evolucao_completa = ENTT.ABRIR(ASSETS.GET_PASTA("coisas").getArquivo("TreinoEvolucao.entts"));

        ENTT.EXIBIR_TABELA(evolucao_completa);

        while (s <= 50) {

            Tozte tozte_corrente = new Tozte(s, HOJE.getHiperarko(), HOJE.getTronarko());

            if(tozte_corrente.getSuperarko_Status()== Superarkos.OMEGA && HOJE.isMaiorIgualQue(tozte_corrente)){

                Entidade e_real = ENTT.GET_SEMPRE(evolucao_completa,"Tozte",tozte_corrente.getTextoZerado());

                if(e_real.atIntOuPadrao("Pontuacao",0)>0){
                    render.drawRect(barra_px, 120-5, 8, 40, COR_VERDE);
                    ft_verde_pequeno.escreva(barra_px, 120-20, "+"+ e_real.atIntOuPadrao("Pontuacao",0));
                }

            }

            Lista<Entidade> atv_tozte = ENTT.COLETAR(atividades, "Tozte", tozte_corrente.getTextoZerado());

            if(tem_peito) {
                if (ENTT.EXISTE(ENTT.COLETAR(atv_tozte, "Peito", "SIM"), "Peito", "SIM")) {
                    render.drawRect_Pintado(barra_px, 120, 2, 30, COR_VERMELHO);
                }
            }
            if(tem_biceps){
                if(ENTT.EXISTE(ENTT.COLETAR(atv_tozte,"Biceps","SIM"),"Biceps","SIM")){
                    render.drawRect_Pintado(barra_px+2, 120, 2, 30, COR_AMARELO);
                }
            }
            if(tem_triceps){
                if(ENTT.EXISTE(ENTT.COLETAR(atv_tozte,"Triceps","SIM"),"Triceps","SIM")){
                    render.drawRect_Pintado(barra_px+4, 120, 2, 30, COR_AZUL);
                }
            }

            if(tem_costas){
                if(ENTT.EXISTE(ENTT.COLETAR(atv_tozte,"Costas","SIM"),"Costas","SIM")){
                    render.drawRect_Pintado(barra_px+6, 120, 2, 30, COR_ROXA);
                }
            }
            if(tem_perna){
                if(ENTT.EXISTE(ENTT.COLETAR(atv_tozte,"Perna","SIM"),"Perna","SIM")){
                    render.drawRect_Pintado(barra_px+8, 120, 2, 30, COR_ROSA);
                }
            }
            if(tem_ombros){
                if(ENTT.EXISTE(ENTT.COLETAR(atv_tozte,"Ombros","SIM"),"Ombros","SIM")){
                    render.drawRect_Pintado(barra_px+10, 120, 2, 30, COR_OMBRO);
                }
            }

            barra_px+=taxa;
            s += 1;
        }


        int py = 200;

        int tem_adbominal = 0;

        for (Entidade exercicio : dados) {

            if (exercicio.is("Tipo", "PEITO")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, COR_VERMELHO);
            } else if (exercicio.is("Tipo", "BICEPS")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, COR_AMARELO);
            } else if (exercicio.is("Tipo", "TRICEPS")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, COR_AZUL);


            } else if (exercicio.is("Tipo", "PERNA")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, COR_ROSA);
            } else if (exercicio.is("Tipo", "COSTAS")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, COR_ROXA);
            } else if (exercicio.is("Tipo", "OMBROS")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, COR_OMBRO);

            } else if (exercicio.is("Tipo", "ABDOMINAL")) {
                tem_adbominal+=1;

                if(tem_adbominal==1){
                    py+=20;
                }

                render.drawRect_Pintado(30, py + 3, 10, 10, BRANCO);
            } else if (exercicio.is("Tipo", "FLEXAO")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, BRANCO);
            } else if (exercicio.is("Tipo", "AQUECIMENTO")) {
                render.drawRect_Pintado(30, py + 3, 10, 10, COR_LARANJA);
            }

            ft_branca_pequeno.escreva(50, py, exercicio.at("Nome"));

            if(exercicio.is("Tipo", "AQUECIMENTO")){
                ft_branca_pequeno.escreva(400, py, exercicio.at("Tempo"));
                py+=20;
            }else{
                ft_branca_pequeno.escreva(400, py, exercicio.at("Series"));
                ft_branca_pequeno.escreva(450, py, exercicio.at("Repeticoes"));
            }


            py += 20;
        }


        Fonte ft_branca_30 = new FonteRunTime(new Cor(255, 255, 255), 20);
        ft_branca_30.setRenderizador(render);

        ft_branca_30.escrevaCentralizado(250, 550, "@luandkg");


        // MARGENS

        render.drawRect_Pintado(0, 0, render.getLargura(), 10, BRANCO);
        render.drawRect_Pintado(0, render.getAltura() - 10, render.getLargura(), 10, BRANCO);

        render.drawRect_Pintado(0, 0, 10, render.getAltura(), BRANCO);
        render.drawRect_Pintado(render.getLargura() - 10, 0, 10, render.getAltura(), BRANCO);

        Imagem.exportar(render.toImagemSemAlfa(), ASSETS.GET_PASTA("coisas").getArquivo(nome+".png"));


    }

}
