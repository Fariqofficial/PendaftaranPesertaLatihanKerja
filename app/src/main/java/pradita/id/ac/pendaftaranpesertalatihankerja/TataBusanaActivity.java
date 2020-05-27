package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TataBusanaActivity extends AppCompatActivity {

    TextView pendaftar, jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tata_busana);

        pendaftar = findViewById(R.id.pendaftar_tabun);
        jadwal = findViewById(R.id.jadwal_tabun);

        jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PenjadwalanActivity.class));
            }
        });

        pendaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListPesertaActivity.class));
            }
        });
    }
}
