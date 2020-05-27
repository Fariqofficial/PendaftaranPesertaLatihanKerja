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
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.JadwalTeknikPendingin;

public class PenjadwalanTeknikPendinginActivity extends AppCompatActivity {

    DatabaseReference reference;
    ProgressDialog progressDialog;
    FirebaseUser user;
    TextView jurusanLatTekPndingin, jamLatTekPndingin, tglLatTekPndingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjadwalan_teknik_pendingin);

        jurusanLatTekPndingin = findViewById(R.id.jurusan_latihantekpndingin);
        jamLatTekPndingin = findViewById(R.id.jamLatihan_tekpndingin);
        tglLatTekPndingin = findViewById(R.id.tglLatihan_tekpndingin);

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getJadwalTeknikPendingin();
    }

    private void getJadwalTeknikPendingin() {
        progressDialog.show();
        progressDialog.setMessage("Tunggu sebentar yaa..");

        FirebaseDatabase.getInstance().getReference("Jadwal").child("6")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JadwalTeknikPendingin jadwal = dataSnapshot.getValue(JadwalTeknikPendingin.class);

                        jurusanLatTekPndingin.setText(jadwal.getJurusan());
                        jamLatTekPndingin.setText(jadwal.getJam());
                        tglLatTekPndingin.setText(jadwal.getTanggal());

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
