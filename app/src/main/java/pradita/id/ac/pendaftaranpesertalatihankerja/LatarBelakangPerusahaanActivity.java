package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LatarBelakangPerusahaanActivity extends AppCompatActivity {

    Button btnPergub33, btnPergub341;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latar_belakang_perusahaan);

        btnPergub33 = findViewById(R.id.pergub_33);
        btnPergub341 = findViewById(R.id.pergub_341);

        btnPergub33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PergubNo33Activity.class));
            }
        });

        btnPergub341.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PergubNo341Activity.class));
            }
        });
    }
}
