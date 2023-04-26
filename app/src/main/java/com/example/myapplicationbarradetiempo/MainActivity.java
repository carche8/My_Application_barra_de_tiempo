package com.example.myapplicationbarradetiempo;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplicationbarradetiempo.R;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private Button play;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 30000; // 30 segundos
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        progressBar = findViewById(R.id.progressBar);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    // El temporizador ya está en marcha
                    Toast.makeText(MainActivity.this, "El temporizador ya está en marcha.", Toast.LENGTH_SHORT).show();
                } else {
                    startTimer();
                }
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;

                // Obtener el número de segundos restantes
                int seconds = (int) (millisUntilFinished / 1000);

                // Verificar si quedan 5 segundos o menos
                if (seconds <= 5) {
                    // Mostrar los segundos restantes en la pantalla
                    Toast.makeText(MainActivity.this, String.valueOf(seconds), Toast.LENGTH_SHORT).show();
                }

                updateProgressBar();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateProgressBar();
                Toast.makeText(MainActivity.this, "El temporizador ha terminado.", Toast.LENGTH_SHORT).show();
            }
        }.start();

        timerRunning = true;
        updateProgressBar();
    }

    private void updateProgressBar() {
        progressBar.setMax(30000);
        progressBar.setProgress((int) (30000 - timeLeftInMilliseconds));
        progressBar.setVisibility(View.VISIBLE);
    }
}