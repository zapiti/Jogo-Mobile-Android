package com.developer.nathan.jumper.elements;

import android.content.Context;
import android.graphics.Canvas;

import com.developer.nathan.jumper.graphics.Tela;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by natha on 25/12/2017.
 */

public class Canos {

    public static final int QUANTIDADE_DE_CANOS = 5;
    private List<Cano> canos = new ArrayList<Cano>();
    private static final int DISTANCIA_ENTRE_OS_CANOS = 200;
    private Pontuacao pontuacao;
    private Context context;
    private Tela tela;

    public Canos(Tela tela, Pontuacao pontuacao, Context context) {
        this.tela = tela;
        this.pontuacao = pontuacao;
        this.context = context;
        int posicao = 400;

        for (int i = 0; i < QUANTIDADE_DE_CANOS ; i++) {

            posicao += DISTANCIA_ENTRE_OS_CANOS ;
            Cano cano = new Cano(tela, posicao,context);
            canos.add(cano);

        }
    }

    public void desenhaNo(Canvas canvas) {
        for (Cano cano : canos) {
            cano.desenhaNo(canvas);
        }
    }

    public void move() {
        ListIterator<Cano> iterator = canos.listIterator();

        while(iterator.hasNext()){
            Cano cano = iterator.next();
            cano.move();
            if(cano.saiuDaTela()){

                pontuacao.aumenta();
                //buffer
                iterator.remove();



                //criar outro cano
                Cano outroCano = new Cano(tela, getMaximaPosicao() + DISTANCIA_ENTRE_OS_CANOS,context);
                iterator.add(outroCano);
            }
        }

    }

    private int getMaximaPosicao() {
        int maximo = 0;
        for (Cano cano : canos) {
            maximo = Math.max(cano.getPosicao(),maximo);
        }
        return maximo;
        }


    public boolean temColisao(Passaro passaro) {
        for (Cano cano : canos) {
            if (cano.temColisaoHorizontalCom(passaro)
                    && cano.temColisaoVerticalCom(passaro)) {
                return true;

            }
        }
        return false;

    }
}
