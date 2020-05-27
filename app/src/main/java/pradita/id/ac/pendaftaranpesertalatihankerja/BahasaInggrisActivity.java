package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BahasaInggrisActivity extends AppCompatActivity {

    TextView pendaftarBing, jadwalLatihanBing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahasa_inggris);

        pendaftarBing = findViewById(R.id.pendaftar_bing);
        jadwalLatihanBing = findViewById(R.id.jadwal_bing);

        jadwalLatihanBing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PenjadwalanBahasaInggrisActivity.class));
            }
        });

        pendaftarBing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListPesertaBahasaInggrisActivity.class));
            }
        });
    }
}
