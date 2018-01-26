package com.developer.nathan.jumper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.nathan.jumper.dao.JumperDao;
import com.developer.nathan.jumper.elements.Pontuacao;
import com.developer.nathan.jumper.utils.Constantes;

public class InitActivity extends Activity {


    private ImageView imagemJoao;
    private ImageView imagemMariana;
    private TextView topScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        imagemJoao = (ImageView) findViewById(R.id.imageView_joao);
        imagemJoao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciaGame(Constantes.JOAO);
            }
        });
        imagemMariana = (ImageView) findViewById(R.id.imageView_mariana);
        imagemMariana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciaGame(Constantes.MARIA);
            }
        });
        topScores = (TextView) findViewById(R.id.topscores);
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscaTopScore();

    }

    private void buscaTopScore() {
        JumperDao dao = new JumperDao(this);
        Pontuacao pontuacao = dao.buscarMaiorPontuacao();
        topScores.setText(getString(R.string.maior_pontuacao) + pontuacao.getPontos());
    }

    public void iniciaGame(String personagem){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Constantes.PERSONAGEM_SELECIONADO, personagem);
        startActivity(intent);
    }

}
