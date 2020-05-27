package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

import pradita.id.ac.pendaftaranpesertalatihankerja.Fragment.DatePickerFragment;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.User;

import static com.google.common.reflect.Reflection.initialize;

public class PendaftaranPesertaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    EditText no_nik, namaLengkapPeserta, tempatLahirPeserta, tglLahirPeserta, umurPeserta, jurusanSekolah, tahunSelesai, alamatLengkap, alamatEmail, no_hp, kelurahan_peserta, kecamatan_peserta, kotaAdmPeserta ;
    ImageView photoPeserta;
    DatabaseReference reference;
    ProgressDialog dialog;
    FirebaseUser user;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://pendaftaran-peserta-kerja-ppkd.appspot.com/");
    Uri imagePick;
    RadioGroup rdGrup, rdGrupStatus;
    RadioButton rdButton, rdButtonStatus;

    Button btn_daftar;

    int PICK_IMAGE_RQ = 111;

    StorageReference storageReference;
    StorageTask task;
    Spinner minat_jurusan, pendidikan_terakhir, agama;

    String namaCalonPeserta, emailCalonPeserta, noNikPeserta, tempatLahir, tglLahir, umurCalonPeserta, jurusanSekolahPeserta, tahunSelesaiPeserta, alamatCalonPeserta, kelurahanPeserta, kecamatanPeserta, kotaAdminPeserta, noHpCalonPeserta, jenkel, statusHub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran_peserta);

        no_nik = findViewById(R.id.nik);
        namaLengkapPeserta = findViewById(R.id.nama_peserta);
        tempatLahirPeserta = findViewById(R.id.tempat_lahir);
        tglLahirPeserta = findViewById(R.id.tgl_lahir);
        umurPeserta = findViewById(R.id.umur_peserta);
        jurusanSekolah = findViewById(R.id.jurusan);
        tahunSelesai = findViewById(R.id.selesai);
        alamatLengkap = findViewById(R.id.alamat_peserta);
        kelurahan_peserta = findViewById(R.id.kelurahan);
        kecamatan_peserta = findViewById(R.id.kecamatan);
        kotaAdmPeserta = findViewById(R.id.kotaAdministrasi);
        alamatEmail = findViewById(R.id.email_peserta);
        no_hp = findViewById(R.id.hp_peserta);
        btn_daftar = findViewById(R.id.daftar_peserta);
        photoPeserta = findViewById(R.id.photo);
        rdGrup = findViewById(R.id.tabel_jenkel);
        rdGrupStatus = findViewById(R.id.tabel_status);
        minat_jurusan = findViewById(R.id.spinner_kejuruan);
        pendidikan_terakhir = findViewById(R.id.spinner_pendidikan);
        agama = findViewById(R.id.agama);

        dialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        
        ambilDataYangDibutuhkan();
        
        storageReference = FirebaseStorage.getInstance().getReference("Foto Peserta");

        tglLahirPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        minat_jurusan.setOnItemSelectedListener(this);
        String peminatan [] = {"Tata Busana", "Operator Komputer", "Bahasa Inggris", "Teknik Pendingin",
                               "Tata Boga", "Teknik Sepeda Motor", "Perhotelan"};
        minat_jurusan.setPrompt("Pilih");

        ArrayAdapter adapterJurusan = new ArrayAdapter(this, android.R.layout.simple_list_item_1, peminatan);
        minat_jurusan.setOnItemSelectedListener(this);
        minat_jurusan.setAdapter(adapterJurusan);

        pendidikan_terakhir.setOnItemSelectedListener(this);
        String pendidikanTerakhir [] = {"SD", "SMP", "SMA", "SMK", "D1", "D2", "D3", "S1", "S2", "S3"};
        pendidikan_terakhir.setOnItemSelectedListener(this);
        pendidikan_terakhir.setPrompt("Pilih");

        ArrayAdapter adapterPendidikan = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pendidikanTerakhir);
        pendidikan_terakhir.setOnItemSelectedListener(this);
        pendidikan_terakhir.setAdapter(adapterPendidikan);

        agama.setOnItemSelectedListener(this);
        String agamaPeserta [] = {"Islam", "Kristen", "Hindu", "Budha", "Katholik", "Konghucu"};
        agama.setPrompt("Pilih");

        ArrayAdapter adapterAgama = new ArrayAdapter(this, android.R.layout.simple_list_item_1, agamaPeserta);
        agama.setOnItemSelectedListener(this);
        agama.setAdapter(adapterAgama);

        
        photoPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bukaGalery();
            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftarPesertaLatihanKerja();
            }
        });

    }

    private void daftarPesertaLatihanKerja() {
        initialize(); // inilisialisai input ke variabel string
        if (!validate()){
            Toast.makeText(this, "Daftar menjadi peserta latihan gagal!", Toast.LENGTH_SHORT).show();
        } else {
            daftarSukses(namaCalonPeserta, emailCalonPeserta, noNikPeserta, umurCalonPeserta, jurusanSekolahPeserta, tahunSelesaiPeserta, alamatCalonPeserta, kelurahanPeserta, kecamatanPeserta, kotaAdminPeserta, noHpCalonPeserta, jenkel, statusHub, tempatLahir, tglLahir);
        }
    }

    private void daftarSukses(String namaCalonPeserta, String emailCalonPeserta, String noNikPeserta, String umurCalonPeserta, String jurusanSekolahPeserta, String tahunSelesaiPeserta, String alamatCalonPeserta, String kelurahanPeserta, String kecamatanPeserta, String kotaAdminPeserta, String noHpCalonPeserta, String jenkel, String statusHub, String tempatLahir, String tglLahir) {
        dialog.show();
        dialog.setMessage("Tunggu Sebentar Yaa..");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Calon Peserta").child(user.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("peminatan_jurusan", minat_jurusan.getSelectedItem().toString());
        hashMap.put("nik_peserta", noNikPeserta);
        hashMap.put("namaLengkap", namaCalonPeserta);
        hashMap.put("tempatLahir", tempatLahir);
        hashMap.put("tanggalLahir", tglLahir);
        hashMap.put("umurPeserta", umurCalonPeserta);
        hashMap.put("pendidikan_terakhir", pendidikan_terakhir.getSelectedItem().toString());
        hashMap.put("jurusan_sekolah", jurusanSekolahPeserta);
        hashMap.put("tahun_selesai", tahunSelesaiPeserta);
        hashMap.put("jenis_kelamin", jenkel);
        hashMap.put("statusHubungan", statusHub);
        hashMap.put("agama_peserta", agama.getSelectedItem().toString());
        hashMap.put("alamat_peserta", alamatCalonPeserta);
        hashMap.put("kelurahan", kelurahanPeserta);
        hashMap.put("kecamatan", kecamatanPeserta);
        hashMap.put("kotaAdministrasi", kotaAdminPeserta);
        hashMap.put("email_peserta", emailCalonPeserta);
        hashMap.put("nohp", noHpCalonPeserta);

        reference.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Daftar Peserta Latihan Kerja Berhasil!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
    }

    public void rbClick(View view){
        int radioId = rdGrup.getCheckedRadioButtonId();
        rdButton = findViewById(radioId);
    }

    public void rbClickStatus(View view){
        int radioId = rdGrupStatus.getCheckedRadioButtonId();
        rdButtonStatus = findViewById(radioId);
    }
    private boolean validate() {
        boolean valid = true;
        if (noNikPeserta.isEmpty()){
            no_nik.setError("Harap masukkan nomor NIK anda");
            valid = false;
        }
        if (namaCalonPeserta.isEmpty()){
            namaLengkapPeserta.setError("Harap masukkan nama lengkap anda");
            valid = false;
        }
        if (tempatLahir.isEmpty()){
            tempatLahirPeserta.setError("Harap masukkan tempat lahir anda");
            valid = false;
        }
        if (tglLahir.isEmpty()){
            tglLahirPeserta.setError("Harap masukkan tanggal lahir anda");
            valid = false;
        }
        if (umurCalonPeserta.isEmpty()){
            umurPeserta.setError("Harap masukkan umur anda");
            valid = false;
        }
        if (jurusanSekolahPeserta.isEmpty()){
            jurusanSekolah.setError("Harap masukkan juruan sekolah anda");
            valid = false;
        }
        if (tahunSelesaiPeserta.isEmpty()){
            tahunSelesai.setError("Harap masukkan tahun selesai sekolah anda");
            valid = false;
        }
        if (alamatCalonPeserta.isEmpty()){
            alamatLengkap.setError("Harap masukkan alamat rumah anda");
            valid = false;
        }
        if (kelurahanPeserta.isEmpty()){
            kelurahan_peserta.setError("Harap masukkan kelurahan anda");
            valid = false;
        }
        if (kecamatanPeserta.isEmpty()){
            kecamatan_peserta.setError("Harap masukkan kecamatan anda");
            valid = false;
        }
        if (kotaAdminPeserta.isEmpty()){
            kotaAdmPeserta.setError("Harap masukkan kota administrasi anda");
            valid = false;
        }
        if (emailCalonPeserta.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailCalonPeserta).matches()){
            alamatEmail.setError("Harap masukkan alamat email yang valid");
            valid = false;
        }
        if (noHpCalonPeserta.isEmpty()){
            no_hp.setError("Harap masukkan nomor handphone anda");
        }
        return valid;
    }

    public void initialize() {
        noNikPeserta = no_nik.getText().toString().trim();
        namaCalonPeserta = namaLengkapPeserta.getText().toString().trim();
        tempatLahir = tempatLahirPeserta.getText().toString().trim();
        tglLahir = tglLahirPeserta.getText().toString().trim();
        umurCalonPeserta = umurPeserta.getText().toString().trim();
        jurusanSekolahPeserta = jurusanSekolah.getText().toString().trim();
        tahunSelesaiPeserta = tahunSelesai.getText().toString().trim();
        jenkel = rdButton.getText().toString().trim();
        statusHub = rdButtonStatus.getText().toString().trim();
        alamatCalonPeserta = alamatLengkap.getText().toString().trim();
        kelurahanPeserta = kelurahan_peserta.getText().toString().trim();
        kecamatanPeserta = kecamatan_peserta.getText().toString().trim();
        kotaAdminPeserta = kotaAdmPeserta.getText().toString().trim();
        emailCalonPeserta = alamatEmail.getText().toString().trim();
        noHpCalonPeserta = no_hp.getText().toString().trim();
    }

    private void bukaGalery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_RQ);
    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_RQ && resultCode == RESULT_OK && data != null && data.getData() != null){
            imagePick = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imagePick);
                photoPeserta.setImageBitmap(bitmap);
                uploadPhotoPeserta();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
//
    private void uploadPhotoPeserta() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Harap Sabar Yaa..");
        dialog.show();

        if (imagePick != null){
            final StorageReference referenceStore = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtention(imagePick));

            task = referenceStore.putFile(imagePick);
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
                        Toast.makeText(PendaftaranPesertaActivity.this,"Upload gagal!",Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PendaftaranPesertaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(PendaftaranPesertaActivity.this, "Pilih gambar terlebih dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

   String getFileExtention(Uri uri) {
       ContentResolver resolver = this.getContentResolver();
       MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
       return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

    private void ambilDataYangDibutuhkan() {
        dialog.show();
        dialog.setMessage("Loading..");
        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        
                        namaLengkapPeserta.setText(user.getNamaLengkap());
                        alamatLengkap.setText(user.getAlamat_peserta());
                        kelurahan_peserta.setText(user.getKelurahan());
                        kecamatan_peserta.setText(user.getKecamatan());
                        kotaAdmPeserta.setText(user.getKotaAdministrasi());
                        alamatEmail.setText(user.getEmail_peserta());
                        no_hp.setText(user.getNohp());

                        if (user.getImageUrl().equals("default")){
                            photoPeserta.setImageResource(R.drawable.photoprofil);
                        }else{
                            Glide.with(getApplicationContext())
                                    .load(user.getImageUrl())
                                    .into(photoPeserta);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, final long id) {
        String memilih_item = (String) parent.getItemAtPosition(position);

        if (memilih_item == "Custom"){
            final AlertDialog.Builder alertInput = new AlertDialog.Builder(PendaftaranPesertaActivity.this);
            alertInput.setTitle("");

            alertInput.setCancelable(true);
            alertInput.setNegativeButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    String input
                }
            });
            alertInput.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(PendaftaranPesertaActivity.this, "Harap pilih terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alertDialog = alertInput.create();
            alertDialog.show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar clnd = Calendar.getInstance();
        clnd.set(Calendar.YEAR, year);
        clnd.set(Calendar.MONTH, month);
        clnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(clnd.getTime());
        tglLahirPeserta.setText(currentDate);
    }
}