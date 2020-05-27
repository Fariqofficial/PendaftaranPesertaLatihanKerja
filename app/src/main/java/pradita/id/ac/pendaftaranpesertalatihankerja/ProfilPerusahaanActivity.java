package pradita.id.ac.pendaftaranpesertalatihankerja;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

public class ProfilPerusahaanActivity extends AppCompatActivity {

    CardView latarBelakang, visiMisi, strukturOrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_perusahaan);

        latarBelakang = findViewById(R.id.latar_belakang);
        visiMisi = findViewById(R.id.visi_misi);
        strukturOrg = findViewById(R.id.struktur);

        latarBelakang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LatarBelakangPerusahaanActivity.class));
            }
        });

        visiMisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VisiDanMisiActivity.class));
            }
        });

        strukturOrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StrukturOrganisasiActivity.class));
            }
        });
    }
}
