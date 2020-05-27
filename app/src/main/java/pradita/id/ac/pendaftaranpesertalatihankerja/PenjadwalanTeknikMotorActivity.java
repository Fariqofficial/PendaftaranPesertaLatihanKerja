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
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalTeknikMotor;

public class PenjadwalanTeknikMotorActivity extends AppCompatActivity {

    DatabaseReference reference;
    ProgressDialog progressDialog;
    FirebaseUser user;
    TextView jurusanTsm, jamLatihanTsm, tanggalLatihanTsm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjadwalan_teknik_motor);

        jurusanTsm = findViewById(R.id.jurusan_latihantsm);
        jamLatihanTsm = findViewById(R.id.jamLatihan_tsm);
        tanggalLatihanTsm = findViewById(R.id.tglLatihan_tsm);

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getJadwalTeknikMotor();
    }

    private void getJadwalTeknikMotor() {
        progressDialog.show();
        progressDialog.setMessage("Tunggu sebentar yaa..");

        FirebaseDatabase.getInstance().getReference("Jadwal").child("5")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JadwalTeknikMotor jadwal = dataSnapshot.getValue(JadwalTeknikMotor.class);

                        jurusanTsm.setText(jadwal.getJurusan());
                        jamLatihanTsm.setText(jadwal.getJam());
                        tanggalLatihanTsm.setText(jadwal.getTanggal());

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
