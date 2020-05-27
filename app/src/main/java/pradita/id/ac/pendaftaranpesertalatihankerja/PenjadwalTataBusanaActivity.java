package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalTataBusana;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalTeknikPendingin;

public class PenjadwalTataBusanaActivity extends AppCompatActivity {

    DatabaseReference reference;
    ProgressDialog progressDialog;
    FirebaseUser user;
    TextView jurusanLatTataBusana, jamLatTataBusana, tglLatTataBusana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjadwal_tata_busana);

        jurusanLatTataBusana = findViewById(R.id.jurusan_latihantatabusana);
        jamLatTataBusana = findViewById(R.id.jamLatihan_tatabusana);
        tglLatTataBusana = findViewById(R.id.tglLatihan_tatabusana);

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getJadwalTataBusana();
    }

    private void getJadwalTataBusana() {
        progressDialog.show();
        progressDialog.setMessage("Tunggu sebentar yaa..");

        FirebaseDatabase.getInstance().getReference("Jadwal").child("4")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JadwalTataBusana jadwal = dataSnapshot.getValue(JadwalTataBusana.class);

                        jamLatTataBusana.setText(jadwal.getJurusan());
                        jamLatTataBusana.setText(jadwal.getJam());
                        tglLatTataBusana.setText(jadwal.getTanggal());

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan saat mengambil data", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }
}
