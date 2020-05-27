package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TeknikPendinginActivity extends AppCompatActivity {

    TextView pendaftarTeknikPendingin, jadwalTeknikPendingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teknik_pendingin);

        pendaftarTeknikPendingin = findViewById(R.id.pendaftar_pendingin);
        jadwalTeknikPendingin = findViewById(R.id.jadwal_pendingin);

        jadwalTeknikPendingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PenjadwalanTeknikPendinginActivity.class));
            }
        });

        pendaftarTeknikPendingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListPesertaTeknikPendinginActivity.class));
            }
        });
    }
}
