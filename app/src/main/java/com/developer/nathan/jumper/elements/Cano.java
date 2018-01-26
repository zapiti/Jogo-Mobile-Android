package com.developer.nathan.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.developer.nathan.jumper.R;
import com.developer.nathan.jumper.graphics.Cores;
import com.developer.nathan.jumper.graphics.Tela;

/**
 * Created by natha on 25/12/2017.
 */

public class Cano {

    private static final Paint VERDE = Cores.getCorDoCano();
    private final Context context;
    private final Bitmap canoInferior;
    private final Bitmap canoSuperior;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;

    private Tela tela;
    private static final int TAMANHO_DO_CANO = 150;
    private static final int LARGURA_DO_CANO = 100;
    private int posicao;


    public Cano(Tela tela, int posicao, Context context) {
        this.tela = tela;
        this.context = context;
        this.posicao = posicao;
        alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false);
        canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
    }

    private int valorAleatorio() {
        return (int)(Math.random() * 150);
    }



    public void desenhaNo(Canvas canvas) {
        desenhaCanoSuperiorNo(canvas);
        desenhaCanoInferiorNo(canvas);
    }

    private void desenhaCanoSuperiorNo(Canvas canvas) {
       // canvas.drawRect(posicao,0,posicao + LARGURA_DO_CANO,alturaDoCanoSuperior , VERDE);
        canvas.drawBitmap(canoSuperior,posicao,0,null);
    }

    private void desenhaCanoInferiorNo(Canvas canvas) {
        //canvas.drawRect(posicao,alturaDoCanoInferior,posicao + LARGURA_DO_CANO,tela.getAltura(), VERDE);
        canvas.drawBitmap(canoInferior,posicao,alturaDoCanoInferior,null);
    }

    public void move() {


        this.posicao -= 5;
    }

    public boolean saiuDaTela() {

        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao() {
        return posicao;
    }

    public boolean temColisaoHorizontalCom(Passaro passaro) {
        return this.posicao < passaro.X + passaro.RAIO;
    }

    public boolean temColisaoVerticalCom(Passaro passaro) {
        return  passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior
                || passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }
}
