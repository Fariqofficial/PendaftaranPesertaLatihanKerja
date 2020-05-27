package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeknikKomputerActivity extends AppCompatActivity {

    TextView pendaftarTeknikKomp, jadwalLatihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teknik_komputer);

        pendaftarTeknikKomp = findViewById(R.id.pendaftar_tekkomp);
        jadwalLatihan = findViewById(R.id.jadwal_tekkomp);

        jadwalLatihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PenjadwalanTeknikKomputerActivity.class));
            }
        });

        pendaftarTeknikKomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListPesertaTeknikKomputerActivity.class));
            }
        });
    }
}
