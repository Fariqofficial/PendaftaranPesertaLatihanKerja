package pradita.id.ac.pendaftaranpesertalatihankerja;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText  namaPeserta, alamatEmail, psswrd, cpass;
    ProgressDialog progressDialog;
    Button create_acc;
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseAuth auth;
    String namaLengkap, emailPeserta, password_akun, konfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        namaPeserta = findViewById(R.id.nama);
        alamatEmail = findViewById(R.id.alamatEmail);
        psswrd = findViewById(R.id.password_akun);
        cpass = findViewById(R.id.konfirmpass);
        create_acc = findViewById(R.id.buat_akun);

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bikinAkun();
//                String namaLengkap = namaPeserta.getText().toString();
//                String emailPeserta = alamatEmail.getText().toString();
//                String password_akun = psswrd.getText().toString();
//
//                if (namaLengkap.isEmpty() || emailPeserta.isEmpty() | password_akun.isEmpty()){
//                    Toast.makeText(getApplicationContext(), "Data masih ada yang kosong!", Toast.LENGTH_LONG).show();
//                } else if (password_akun.length() < 8) {
//                    Toast.makeText(getApplicationContext(), "Password harus terdiri dari 8 karakter!", Toast.LENGTH_LONG).show();
//                } else {
//                    bikinAkun(namaLengkap, emailPeserta, password_akun);
//                }
            }
        });
    }

    private void bikinAkun() {
        initialize(); // inilisialisai input ke variabel string
        if (!validate()){
            Toast.makeText(this, "Buat akun gagal!", Toast.LENGTH_SHORT).show();
        } else {
            buatAkunSukses(namaLengkap, emailPeserta, password_akun, konfirmPass);
        }
    }

    private void buatAkunSukses(final String namaLengkap, final String emailPeserta, final String password_akun, final String konfirmPass) {
        progressDialog.show();
        progressDialog.setMessage("Membuat Akun..");
        auth.createUserWithEmailAndPassword(emailPeserta, password_akun)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            assert user != null;

                            String userId = user.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("user_id", userId);
                            hashMap.put("imageUrl", "default");
                            hashMap.put("namaLengkap", namaLengkap);
                            hashMap.put("email_peserta", emailPeserta);
                            hashMap.put("pass", password_akun);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                        startActivity(intent);
                                        progressDialog.dismiss();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getApplicationContext(), "Buat akun gagal!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }
                });
        //TODO what will go after the valid input
    }

    public boolean validate(){
        boolean valid = true;
        if (namaLengkap.isEmpty()){
             namaPeserta.setError("Harap masukkan nama lengkap anda");
             valid = false;
        }
        if (emailPeserta.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailPeserta).matches()){
            alamatEmail.setError("Harap masukkan alamat email yang valid");
            valid = false;
        }
        if (password_akun.isEmpty() || password_akun.length() < 8){
            psswrd.setError("Harap masukkan password anda lebih dari 8 karakter");
            valid = false;
        }
        if (konfirmPass.isEmpty()){
            cpass.setError("Pasword tidak sama");
        }
        return valid;
    }

    public void initialize(){
        namaLengkap = namaPeserta.getText().toString().trim();
        emailPeserta = alamatEmail.getText().toString().trim();
        password_akun = psswrd.getText().toString().trim();
        konfirmPass = cpass.getText().toString().trim();
    }
//    void bikinAkun(final String namaLengkap, final String emailPeserta, final String password_akun) {
//        progressDialog.show();
//        progressDialog.setMessage("Membuat Akun..");
//        auth.createUserWithEmailAndPassword(emailPeserta, password_akun)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            FirebaseUser user = auth.getCurrentUser();
//                            assert user != null;
//
//                            String userId = user.getUid();
//                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
//
//                            HashMap<String,String> hashMap = new HashMap<>();
//                            hashMap.put("user_id", userId);
//                            hashMap.put("imageUrl", "default");
//                            hashMap.put("namaLengkap", namaLengkap);
//                            hashMap.put("email_peserta", emailPeserta);
//                            hashMap.put("pass", password_akun);
//
//                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                                        startActivity(intent);
//                                        progressDialog.dismiss();
//                                    }
//                                }
//                            });
//
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Buat akun gagal!", Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
//                        }
//
//                    }
//                });
//        }
}