package com.developer.nathan.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.developer.nathan.jumper.R;
import com.developer.nathan.jumper.engine.Som;
import com.developer.nathan.jumper.graphics.Cores;
import com.developer.nathan.jumper.graphics.Tela;
import com.developer.nathan.jumper.utils.Constantes;

/**
 * Created by natha on 25/12/2017.
 */

public class Passaro {

    public static final int X = 100;
    public static final int RAIO = 50;
    private static final Paint VERMELHO  = Cores.getCorDoPassaro();
    private static Tela tela;
    private static Bitmap passaro;

    private int altura;
    private Som som;

    public Passaro(Tela tela, Context context,Som som, String personagemSelecionado) {
        this.som = som;
        this.altura = 100;
        this.tela = tela;
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(),
                Constantes.JOAO.equals(personagemSelecionado) ? R.drawable.rosto_joao : R.drawable.rosto_mariana);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO * 2, RAIO * 2, false);
    }

    public void cai() {
        boolean chegouNoChao = altura + RAIO > tela.getAltura();
        if (!chegouNoChao) {
            this.altura += 5;
        }

    }

    public void desenhaNo(Canvas canvas) {
       // canvas.drawCircle(X, altura, RAIO, VERMELHO);
        canvas.drawBitmap(passaro, X - RAIO, altura - RAIO,null);

    }

    public void pula() {
        if (altura - RAIO > 0) {
            som.toca(Som.PULO);

            this.altura-=80;
        }

    }

    public int getAltura() {
        return this.altura;
    }
}
