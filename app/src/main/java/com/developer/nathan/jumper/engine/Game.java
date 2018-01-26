package com.developer.nathan.jumper.engine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.developer.nathan.jumper.MainActivity;
import com.developer.nathan.jumper.R;
import com.developer.nathan.jumper.dao.JumperDao;
import com.developer.nathan.jumper.elements.Cano;
import com.developer.nathan.jumper.elements.Canos;
import com.developer.nathan.jumper.elements.GameOver;
import com.developer.nathan.jumper.elements.Passaro;
import com.developer.nathan.jumper.elements.Pontuacao;
import com.developer.nathan.jumper.graphics.Tela;


/**
 * Created by natha on 25/12/2017.
 */

public class Game  extends SurfaceView  implements Runnable,View.OnTouchListener {

    private final SurfaceHolder holder = getHolder();
    private final Tela tela;
    private final Context context;
    private final Som som;
    private final String personagemSelecionado;
    private final JumperDao dao;
    private boolean isRunning = true;

    private Passaro passaro;
    private Canvas canvas;
    private Bitmap background;
    private Canos canos;
    private Pontuacao pontuacao;
    private int secs = 1;
    private MainActivity main;


    public Game(Context context,String personagemSelecionado) {
        super(context);
        this.context = context;
        this.tela = new Tela(context);
        this.som = new Som(context);
        this.personagemSelecionado = personagemSelecionado;
        this.dao = new JumperDao(context);

        inicializaElementos();

        setOnTouchListener(this);
    }


    private void inicializaElementos() {
        this.passaro = new Passaro(tela,context,som,personagemSelecionado);
        this.pontuacao = new Pontuacao();
        this.canos = new Canos(tela,pontuacao,context);


        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);
    }

    @Override
    public void run() {
        while(isRunning) {
            if(!holder.getSurface().isValid()) continue;

            canvas = holder.lockCanvas();

            //desenho dos componentes

            canvas.drawBitmap(background,0,0,null);

            passaro.desenhaNo(canvas);
            passaro.cai();


            canos.desenhaNo(canvas);
            canos.move();

            pontuacao.desenhaNo(canvas);

            if(new VerificadorDeColisao(passaro, canos).temColisao()){
                som.toca(Som.COLISAO);
                new GameOver(tela).desenhaNo(canvas);
                isRunning =  false;
                pontuacao.setNome("anonimo");//mecher dps
                dao.salvaPontuacao(pontuacao);

            }

            holder.unlockCanvasAndPost(canvas);
        }

    }

    public void inicia() {
        this.isRunning = true;
    }

    public void cancela() {
        this.isRunning = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        passaro.pula();
        return false;
    }
}
