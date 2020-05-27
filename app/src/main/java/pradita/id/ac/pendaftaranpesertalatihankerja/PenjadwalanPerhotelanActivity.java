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

import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalBing;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalPerhotelan;

public class PenjadwalanPerhotelanActivity extends AppCompatActivity {

    DatabaseReference reference;
    ProgressDialog progressDialog;
    FirebaseUser user;
    TextView jurusanPelatihan, jamPelatihan, tanggalPelatihan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjadwalan_perhotelan);

        jurusanPelatihan = findViewById(R.id.jurusan_latihanperhotelan);
        jamPelatihan = findViewById(R.id.jamLatihan_perhotelan);
        tanggalPelatihan = findViewById(R.id.tglLatihan_perhotelan);

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getJadwalPerhotelan();
    }

    private void getJadwalPerhotelan() {
        progressDialog.show();
        progressDialog.setMessage("Tunggu sebentar yaa..");

        FirebaseDatabase.getInstance().getReference("Jadwal").child("3")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JadwalPerhotelan jadwalPerhotelan = dataSnapshot.getValue(JadwalPerhotelan.class);

                        jurusanPelatihan.setText(jadwalPerhotelan.getJurusan());
                        jamPelatihan.setText(jadwalPerhotelan.getJam());
                        tanggalPelatihan.setText(jadwalPerhotelan.getTanggal());

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
