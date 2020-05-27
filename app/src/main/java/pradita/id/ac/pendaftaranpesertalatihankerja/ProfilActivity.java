package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import pradita.id.ac.pendaftaranpesertalatihankerja.Model.User;

public class ProfilActivity extends AppCompatActivity {

    EditText edtNama, edtEmail, edtnoHp, edtAlamat, edtRt, edtRw, edtKelurahan, edtKecamatan, edtkotaAdm;
    ImageView profilPict;
    TextView ubahPsswrd;
    Button simpan;
    DatabaseReference reference;
    ProgressDialog dialog;

    FirebaseUser user;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://pendaftaran-peserta-kerja-ppkd.appspot.com/");
    Uri imageCamera;

    int PICK_IMAGE_RQ = 111;

    StorageReference storageReference;
    StorageTask task;

    public ProfilActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        edtNama = findViewById(R.id.edit_nama);
        edtEmail = findViewById(R.id.edit_email);
        edtnoHp = findViewById(R.id.edit_nomor);
        edtAlamat = findViewById(R.id.edit_alamat);
        edtRt = findViewById(R.id.edit_rt);
        edtRw = findViewById(R.id.edit_rw);
        edtKelurahan = findViewById(R.id.edit_kelurahan);
        edtKecamatan = findViewById(R.id.edit_kecamatan);
        edtkotaAdm = findViewById(R.id.edit_kota);
        profilPict = findViewById(R.id.photoProfil);
        ubahPsswrd = findViewById(R.id.ubahpassword);
        simpan = findViewById(R.id.save);

        dialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        getDataProfile();

        storageReference = FirebaseStorage.getInstance().getReference("Profil Picture");

        profilPict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bukaGalery();
            }
        });

        ubahPsswrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UbahPasswordActivity.class));
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfil(edtEmail.getText().toString(), edtnoHp.getText().toString(), edtAlamat.getText().toString(), edtRt.getText().toString(), edtRw.getText().toString(), edtKelurahan.getText().toString(), edtKecamatan.getText().toString(), edtkotaAdm.getText().toString());
            }
        });
    }

    private void updateProfil(String email_edit, String nomor_edit, String alamat_edit, String rt_edit, String rw_edit, String kel_edit, String kec_edit, String kota_edit) {
        dialog.show();
        dialog.setMessage("Memperbarui Data..");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email_peserta", email_edit);
        hashMap.put("nohp", nomor_edit);
        hashMap.put("alamat_peserta", alamat_edit);
        hashMap.put("rt", rt_edit);
        hashMap.put("rw", rw_edit);
        hashMap.put("kelurahan", kel_edit);
        hashMap.put("kecamatan", kec_edit);
        hashMap.put("kotaAdministrasi", kota_edit);

        reference.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Data berhasil di update!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
    }

    private void bukaGalery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_RQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_RQ && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageCamera = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imageCamera);
                profilPict.setImageBitmap(bitmap);
                uploadProfilPict();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void uploadProfilPict() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();

        if (imageCamera != null){
            final StorageReference referenceStore = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtention(imageCamera));

            task = referenceStore.putFile(imageCamera);
            task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then (@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception{
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return referenceStore.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        final String mUri = downloadUri.toString();
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("imageUrl", mUri);
                        reference.updateChildren(hashMap);
                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(ProfilActivity.this,"Upload gagal!",Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ProfilActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(ProfilActivity.this, "Pilih gambar terlebih dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

   String getFileExtention(Uri uri) {
       ContentResolver resolver = this.getContentResolver();
       MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
       return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
   }

    private void getDataProfile() {
        dialog.show();
        dialog.setMessage("Tunggu Sebentar Yaa..");
        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid())
           .addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   User user = dataSnapshot.getValue(User.class);

                   edtNama.setText(user.getNamaLengkap());
                   edtEmail.setText(user.getEmail_peserta());
                   edtnoHp.setText(user.getNohp());
                   edtAlamat.setText(user.getAlamat_peserta());
                   edtRt.setText(user.getRt());
                   edtRw.setText(user.getRw());
                   edtKelurahan.setText(user.getKelurahan());
                   edtKecamatan.setText(user.getKecamatan());
                   edtkotaAdm.setText(user.getKotaAdministrasi());

                   if (user.getImageUrl().equals("default")){
                       profilPict.setImageResource(R.drawable.photoprofil);
                   }else{
                       Glide.with(getApplicationContext())
                               .load(user.getImageUrl())
                               .into(profilPict);
                   }
                   dialog.dismiss();
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {
                   Toast.makeText(getApplicationContext(), "Terjadi kesalahan saat mengambil data",Toast.LENGTH_SHORT).show();
                   dialog.dismiss();
               }
           });
    }
}