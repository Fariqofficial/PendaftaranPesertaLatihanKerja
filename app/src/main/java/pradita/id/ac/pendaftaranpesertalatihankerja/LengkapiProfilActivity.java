package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LengkapiProfilActivity extends AppCompatActivity {

    EditText alamatPeserta, rtPeserta, rwPeserta, kelurahanPeserta, kecamatanPeserta, kotaAdmPeserta;
    Button nextStep;
    DatabaseReference reference;
    ProgressDialog dialog;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lengkapi_profil);

        alamatPeserta = findViewById(R.id.alamatRumah);
        rtPeserta = findViewById(R.id.rtRumah);
        rwPeserta = findViewById(R.id.rwRumah);
        kelurahanPeserta = findViewById(R.id.kelurahanRumah);
        kecamatanPeserta = findViewById(R.id.kecamatanRumah);
        kotaAdmPeserta = findViewById(R.id.kotaAdm);
        nextStep = findViewById(R.id.lengkapi);

        dialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lengkapiProfil(alamatPeserta.getText().toString(), rtPeserta.getText().toString(), rwPeserta.getText().toString(), kelurahanPeserta.getText().toString(), kecamatanPeserta.getText().toString(), kotaAdmPeserta.getText().toString());
            }
        });


    }

    private void lengkapiProfil(String alamat_rumah, String rt_rumah, String rw_rumah, String kel_rumah, String kec_rumah, String kota_Adm) {
        dialog.show();
        dialog.setMessage("Membuat Akun..");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("alamat_peserta", alamat_rumah);
        hashMap.put("rt", rt_rumah);
        hashMap.put("rw", rw_rumah);
        hashMap.put("kelurahan", kel_rumah);
        hashMap.put("kecamatan", kec_rumah);
        hashMap.put("kotaAdministrasi", kota_Adm);

        reference.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
    }
}
