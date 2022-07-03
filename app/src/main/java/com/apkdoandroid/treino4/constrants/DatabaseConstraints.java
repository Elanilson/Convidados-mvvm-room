package com.apkdoandroid.treino4.constrants;

public class DatabaseConstraints {

    private DatabaseConstraints() {
    }

    public static final String NOME_DB = "DB_convidados1";
    public static final int VERSAO = 1;

    public static class TABELA{
        public static final String NOME = "convidados";
        public  static  class COLUNAS{
            public static final String ID = "id";
            public static final String NOME = "nome";
            public static final String PRESENCA = "presenca";
        }
    }
}
