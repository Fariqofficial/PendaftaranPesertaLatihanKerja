package pradita.id.ac.pendaftaranpesertalatihankerja;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

public class KejuruanActivity extends AppCompatActivity {
    CardView tata_busana, tata_boga, perhotelan, teknik_motor, teknik_pendingin, teknik_komputer, operator_komputer, bhs_inggris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kejuruan);

        tata_busana = findViewById(R.id.tatabusana);
        tata_boga = findViewById(R.id.tataboga);
        perhotelan = findViewById(R.id.perhotelan);
        teknik_motor = findViewById(R.id.sepedamtr);
        teknik_pendingin = findViewById(R.id.pendingin);
        teknik_komputer = findViewById(R.id.tek_komp);
        operator_komputer = findViewById(R.id.op_komp);
        bhs_inggris = findViewById(R.id.b_ing);

        tata_busana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TataBusanaActivity.class));
            }
        });

        tata_boga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TataBogaActivity.class));
            }
        });

        perhotelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PerhotelanActivity.class));
            }
        });

        teknik_motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeknikMotorActivity.class));
            }
        });

        teknik_pendingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeknikPendinginActivity.class));
            }
        });

        teknik_komputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TeknikKomputerActivity.class));
            }
        });

        operator_komputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OperatorKomputerActivity.class));
            }
        });

        bhs_inggris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BahasaInggrisActivity.class));
            }
        });
    }
}
