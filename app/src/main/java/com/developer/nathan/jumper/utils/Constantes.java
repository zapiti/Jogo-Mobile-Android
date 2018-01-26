package com.developer.nathan.jumper.utils;

/**
 * Created by natha on 26/12/2017.
 */

public class Constantes {

    public static final String PONTUACAO_ID = "id";
    public static final String PONTUACAO_NOME = "nome";
    public static final String PONTUACAO_DATA = "data";
    public static final String PONTUACAO_PONTOS = "pontos";
    public static final String TABELA_PONTUACAO = "pontuacao";
    public static String TAG = "Jumper";

    public static final String PERSONAGEM_SELECIONADO = "personagem";
    public static final String JOAO = "joao";
    public static final String MARIA = "maria";
    public static final String DATABASE = "jumper";
    public static final int DATABASE_VERSION = 1;

    public static String[] CREATE_TABLE() {
        return new String[] {CREATE_PONTUACAO};
    }

    public static String CREATE_PONTUACAO = " CREATE TABLE pontuacao ( "
            + " id INTEGER CONSTRAINT 'PK_PONTUACAO' PRIMARY KEY AUTOINCREMENT, "
            + " nome VARCHAR(100) NOT NULL, "
            + " data DATE NOT NULL, "
            + " pontos INT  NOT NULL); ";

    public static final String[] COLUNAS = new String[]{PONTUACAO_ID, PONTUACAO_NOME, PONTUACAO_DATA, PONTUACAO_PONTOS};
}
