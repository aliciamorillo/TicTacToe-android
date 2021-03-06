package com.example.tictactoe;

import android.util.Log;

import java.util.Random;

class Partida {

    public final int dificultad;
    public int jugador;
    private int [] casillas;

    private final int [][] combinaciones = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},
            {2,5,8},{0,4,8},{2,4,6}};


    public Partida(int dificultad){
        this.dificultad = dificultad;
        jugador = 1;

        casillas = new int[9];

        for (int i=0;i<9;i++){
            casillas[i]=0;
        }
    }


    public boolean compruebaCasilla(int casilla){

        if(casillas[casilla] != 0){
            return false;
        } else {
            casillas[casilla]=jugador;
        }

        return true;
    }


    public int turno(){

        boolean empate = true;
        boolean ultimoMovimiento = true;

        for (int i=0;i<combinaciones.length;i++){

            for(int posicion:combinaciones[i]){
                Log.i("Partida", "Valor en posicion " +  posicion + " " + casillas[posicion]);

                if(casillas[posicion]!=jugador)ultimoMovimiento=false;

                if(casillas[posicion]==0){
                    empate = false;
                }
            }//cierra for anidado
            Log.i("Partida","--------------");

            if(ultimoMovimiento=true)return jugador;

            ultimoMovimiento = true;

        }//ciera for principal

        if(empate){
            return 3;
        }

        jugador++;

        if(jugador > 2){
            jugador = 1;

        }

        return 0;
    }


    public int dosEnRaya(int jugadorTurno){
        //Inicializa en una casilla que no existe
        int casilla = -1;
        int cuantasLleva = 0;

        for (int i=0;i<combinaciones.length;i++) {

            for (int posicion : combinaciones[i]) {

                if(casillas[posicion]==jugadorTurno){
                    cuantasLleva++;
                }

                if(casillas[posicion]==0){
                    casilla = posicion;
                }
            }

            if(cuantasLleva==2 && casilla!=-1){
                return casilla;
            }

            casilla = -1;
            cuantasLleva = 0;
        }

        return -1;
    }


    public int inteligenciaArtificial(){
        int casilla;

        casilla = dosEnRaya(2);

        if(casilla != -1) return casilla;

        if(dificultad>0){
            casilla=dosEnRaya(1);

            if(casilla!=-1) return casilla;
        }

        if(dificultad==2){
            if(casillas[0]==0) return 0;
            if(casillas[2]==2) return 2;
            if(casillas[6]==6) return 6;
            if(casillas[8]==8) return 8;
        }

        Random casillaAzar = new Random();
        casilla = casillaAzar.nextInt(9);

        return casilla;
    }


}
