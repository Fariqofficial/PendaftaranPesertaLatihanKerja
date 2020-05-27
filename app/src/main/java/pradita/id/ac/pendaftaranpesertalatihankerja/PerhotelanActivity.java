package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PerhotelanActivity extends AppCompatActivity {

    TextView pendaftarPerhotelan, jadwalLatihanPerhotelan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhotelan);

        pendaftarPerhotelan = findViewById(R.id.pendaftar_perhotelan);
        jadwalLatihanPerhotelan = findViewById(R.id.jadwal_perhotelan);

        jadwalLatihanPerhotelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PenjadwalanPerhotelanActivity.class));
            }
        });

        pendaftarPerhotelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListPesertaPerhotelanActivity.class));
            }
        });
    }
}
