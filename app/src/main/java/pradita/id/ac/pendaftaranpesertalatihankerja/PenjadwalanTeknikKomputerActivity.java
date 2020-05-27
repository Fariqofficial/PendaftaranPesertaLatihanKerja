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

import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalPerhotelan;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalTeknikKomputer;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalTeknikMotor;

public class PenjadwalanTeknikKomputerActivity extends AppCompatActivity {

    DatabaseReference reference;
    ProgressDialog progressDialog;
    FirebaseUser user;
    TextView jurusanLat, jamLat, tglLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjadwalan_teknik_komputer);

        jurusanLat = findViewById(R.id.jurusan_latihantekkomp);
        jamLat = findViewById(R.id.jamLatihan_tekkomp);
        tglLat = findViewById(R.id.tglLatihan_tekkomp);

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getJadwalTeknikKomputer();
    }

    private void getJadwalTeknikKomputer() {
        progressDialog.show();
        progressDialog.setMessage("Tunggu sebentar yaa..");

        FirebaseDatabase.getInstance().getReference("Jadwal").child("7")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JadwalTeknikKomputer jadwal = dataSnapshot.getValue(JadwalTeknikKomputer.class);

                        jurusanLat.setText(jadwal.getJurusan());
                        jamLat.setText(jadwal.getJam());
                        tglLat.setText(jadwal.getTanggal());

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
