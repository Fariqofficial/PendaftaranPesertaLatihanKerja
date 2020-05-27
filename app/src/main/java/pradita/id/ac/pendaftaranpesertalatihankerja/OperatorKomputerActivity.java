package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OperatorKomputerActivity extends AppCompatActivity {

    TextView pendaftarOperatorKom, jadwalLatihanOperatorKom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_komputer);

        pendaftarOperatorKom = findViewById(R.id.pendaftar_opkom);
        jadwalLatihanOperatorKom = findViewById(R.id.jadwal_opkom);

        jadwalLatihanOperatorKom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PenjadwalanOperatorKomputerActivity.class));
            }
        });

//        pendaftarOperatorKom.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(getApplicationContext(), ListPesertaPerhotelanActivity.class));
////            }
////        });
    }
}
