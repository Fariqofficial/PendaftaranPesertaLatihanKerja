package pradita.id.ac.pendaftaranpesertalatihankerja;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView daftar_akun, lupaPass_akun;
    EditText email_login, password_login;
    Button Login;
    FirebaseAuth auth;
    ProgressDialog dialog;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daftar_akun = findViewById(R.id.daftar);
        email_login = findViewById(R.id.email);
        password_login = findViewById(R.id.password);
        Login = findViewById(R.id.masuk);
        lupaPass_akun = findViewById(R.id.lupapass);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        daftar_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        lupaPass_akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LupaPasswordActivity.class));
            }
        });

        dialog = new ProgressDialog(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                dialog.setMessage("Loading..");
                String email_akun = email_login.getText().toString();
                String password_akun = password_login.getText().toString();

                if (email_akun.isEmpty() || password_akun.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data masih ada yang kosong!", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(email_akun, password_akun).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), " Login gagal!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
