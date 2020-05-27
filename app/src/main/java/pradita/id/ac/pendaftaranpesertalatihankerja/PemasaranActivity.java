package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PemasaranActivity extends AppCompatActivity {

    CardView listPartnership, daftarLulusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasaran);

        listPartnership = findViewById(R.id.partnership);
        daftarLulusan = findViewById(R.id.lulusan);

        listPartnership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PartnershipActivity.class));
            }
        });

        daftarLulusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LulusanActivity.class ));
            }
        });
    }
}
