package pradita.id.ac.pendaftaranpesertalatihankerja;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.User;

public class HomeActivity extends AppCompatActivity {
    TextView username, edit_profil;
    CardView profilPerusahaan, jurusan, pemasaran, jadwal, pendaftaran, dataDiri, listPeserta, signOut;
    DatabaseReference reference;
    ProgressDialog dialog;
    FirebaseUser user;
    CircleImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edit_profil = findViewById(R.id.editProfil);
        profilPerusahaan = findViewById(R.id.profil_cmpny);
        jurusan = findViewById(R.id.kejuruan);
        pemasaran = findViewById(R.id.ctrlPerusahan);
        pendaftaran = findViewById(R.id.pendaftaran);
        username = findViewById(R.id.namaPengguna);
        avatar = findViewById(R.id.fotoProfil);
        dataDiri = findViewById(R.id.printData);
        signOut = findViewById(R.id.logout);

        dialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getNamaPengguna();

        edit_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
            }
        });

        dataDiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DataDiriActivity.class));
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("Apa anda yakin ingin keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finish();
                            }
                        }).setNegativeButton("Tidak", null).setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
             }
        });

        profilPerusahaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfilPerusahaanActivity.class));
            }
        });

        jurusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KejuruanActivity.class));
            }
        });

        pemasaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PemasaranActivity.class));
            }
        });


        pendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PersyaratanActivity.class));
            }
        });

    }

    private void getNamaPengguna() {
        dialog.show();
        dialog.setMessage("Tunggu Sebentar Yaa..");
        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);

                        username.setText(user.getNamaLengkap());

                        if (user.getImageUrl().equals("default")){
                            avatar.setImageResource(R.drawable.photoprofil);
                        } else {
                            Glide.with(getApplicationContext())
                                    .load(user.getImageUrl())
                                    .into(avatar);
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
