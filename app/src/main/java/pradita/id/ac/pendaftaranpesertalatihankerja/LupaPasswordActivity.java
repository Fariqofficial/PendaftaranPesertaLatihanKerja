package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LupaPasswordActivity extends AppCompatActivity {
    EditText inputEmail;
    Button sendEmail;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        auth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.email_anda);
        sendEmail = findViewById(R.id.reset);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = sendEmail.getText().toString();

                if (TextUtils.isEmpty(emailUser)){
                    Toast.makeText(getApplicationContext(), "Harap masukan alamat email anda", Toast.LENGTH_SHORT).show();
                } else {
                    auth.sendPasswordResetEmail(emailUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Email telah dikirim, harap cek email anda", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(), "Terjadi kesalahan: " + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
