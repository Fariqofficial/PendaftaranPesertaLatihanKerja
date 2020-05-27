package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeknikMotorActivity extends AppCompatActivity {

    TextView pendaftarTeknikMotor, jadwalTeknikMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teknik_motor);

        pendaftarTeknikMotor = findViewById(R.id.pendaftar_tsm);
        jadwalTeknikMotor = findViewById(R.id.jadwal_tsm);

        jadwalTeknikMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PenjadwalanTeknikMotorActivity.class));
            }
        });

        pendaftarTeknikMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListPesertaTeknikMotorActivity.class));
            }
        });
    }
}
