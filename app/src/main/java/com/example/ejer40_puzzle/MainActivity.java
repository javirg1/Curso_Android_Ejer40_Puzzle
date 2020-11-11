package com.example.ejer40_puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /***********************************************************************************************
     Atributos
     **********************************************************************************************/

    private final String TAG = "Ejer_40";
    private JPuzzle juegoPuzzle;

    private int contadorClicks;
    private int idImg1;
    private int idImg2;
    private int posicion_clicada1;
    private int posicion_clicada2;
    private String imagenEnCasilla2;

    private boolean sePuedeSeguir;

    private ImageView casillaClicada1;

    private Button btnNuevaPartida;

    private TextView tvCasillasCorrectas;

    /***********************************************************************************************
     Funciones
     **********************************************************************************************/

    // --------------------------------------------------------------------------------------------
    // nuevaPartida() - La usamos en el onCreate y cuando pulsamos en el botón Nueva Partida
    // --------------------------------------------------------------------------------------------
    public void nuevaPartida() {

        juegoPuzzle = new JPuzzle();

        sePuedeSeguir = true;

        for (int i = 0; i <= 8; i++) {

            // Enlazamos con los ImageView del MainActivity
            String nRes = "iv" + i;
            int idRes = getResources().getIdentifier(nRes, "id", getPackageName());
            ImageView casilla = findViewById(idRes);

            // Recorremos el array del constructor de la clase JPuzzle utilizando el mismo bucle for
            // Montamos un string con el nombre del recurso imagen y obtenemos el id del recurso
            String numImg = "img_" + juegoPuzzle.getCasillas().get(i);
            int idResImg = getResources().getIdentifier(numImg, "drawable", getPackageName());

            // Cambiamos la imagen de cada ImageView
            casilla.setImageResource(idResImg);

            // Registramos el evento click de cada ImgeView
            casilla.setOnClickListener(MainActivity.this);

            // Refrescamos la pantalla
            startActivity(getIntent());
        }
    }

    /***********************************************************************************************
     onCreate
     **********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        juegoPuzzle = new JPuzzle();

        nuevaPartida();

        tvCasillasCorrectas = findViewById(R.id.tvCasillasCorrectas);

        btnNuevaPartida = findViewById(R.id.btnNuevaPartida);

        btnNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaPartida();
                btnNuevaPartida.setVisibility(View.INVISIBLE);
            }
        });

        contadorClicks = 0;
    }

    /***********************************************************************************************
     onClick - Clicks en las casillas
     **********************************************************************************************/

    @Override
    public void onClick(View view) {

        ImageView casilla_clicada = (ImageView) view;

        if (sePuedeSeguir == true) {

            contadorClicks++;

            if (contadorClicks == 1) {

                // Resaltamos la primera casilla clicada con un borde (archivo borde.xml)
                Drawable highlight = getResources().getDrawable( R.drawable.borde);
                casilla_clicada.setBackground(highlight);

                // Identificamos la casilla clicada
                posicion_clicada1 = Integer.valueOf(casilla_clicada.getTag().toString());
                Log.e(TAG, "posicion_clicada1 " + posicion_clicada1);

                // Obtenemos el recurso id de la casilla clicada
                String imagenEnCasilla = "img_" + juegoPuzzle.getCasillas().get(posicion_clicada1);
                idImg1 = getResources().getIdentifier(imagenEnCasilla, "drawable", getPackageName());

                // Recordamos la casilla clicada
                casillaClicada1 = casilla_clicada;
            }
            if (contadorClicks == 2) {

                // Quitamos el borde que resalta la primera casilla clicada
                casillaClicada1.setBackground(null);

                // Identificamos la casilla clicada
                posicion_clicada2 = Integer.valueOf(casilla_clicada.getTag().toString());

                // Obtenemos el recurso id de la casilla clicada
                imagenEnCasilla2 = "img_" + juegoPuzzle.getCasillas().get(posicion_clicada2);
                idImg2 = getResources().getIdentifier(imagenEnCasilla2, "drawable", getPackageName());

                // Intercambiamos imágenes en cada casilla
                casilla_clicada.setImageResource(idImg1);
                casillaClicada1.setImageResource(idImg2);

                // Refrescamos la pantalla
                startActivity(getIntent());

                // Contador de clicks a 0
                contadorClicks = 0;

                // Intercambiamos posiciones en el array
                juegoPuzzle.cambiaPosiciones(posicion_clicada1, posicion_clicada2);

                // Comprobamos si hemos completado el puzzle
                if (juegoPuzzle.isOrdenada()) {
                    sePuedeSeguir = false;
                    tvCasillasCorrectas.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, R.string.msg_final_partida, Toast.LENGTH_SHORT).show();
                    Handler retardo = new Handler();
                    retardo.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnNuevaPartida.setVisibility(View.VISIBLE);
                        }
                    }, 2500);
                }else{
                    // Mostramos las casillas correctas
                    tvCasillasCorrectas.setText(String.format(getString(R.string.msg_casillas_correctas), juegoPuzzle.getCasillasCorrectas()));
                    tvCasillasCorrectas.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}