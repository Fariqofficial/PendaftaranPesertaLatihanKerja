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

public class PenjadwalanBahasaInggrisActivity extends AppCompatActivity {

    DatabaseReference reference;
    ProgressDialog progressDialog;
    FirebaseUser user;
    TextView jurusan, jam, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjadwalan_bahasa_inggris);

        jurusan = findViewById(R.id.jurusan_latihanbing);
        jam = findViewById(R.id.jamLatihan_bing);
        tanggal = findViewById(R.id.tglLatihan_bing);

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getJadwalBahasaInggris();

    }

    private void getJadwalBahasaInggris() {

        progressDialog.show();
        progressDialog.setMessage("Tunggu sebentar yaa..");

        FirebaseDatabase.getInstance().getReference("Jadwal").child("1")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JadwalBing jadwal = dataSnapshot.getValue(JadwalBing.class);

                        jurusan.setText(jadwal.getJurusan());
                        jam.setText(jadwal.getJam());
                        tanggal.setText(jadwal.getTanggal());

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
