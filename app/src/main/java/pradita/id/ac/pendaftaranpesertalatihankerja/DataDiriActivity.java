package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import pradita.id.ac.pendaftaranpesertalatihankerja.Model.User;

public class DataDiriActivity extends AppCompatActivity {

    TextView et_minatKejuruan, et_nik, et_nama, et_tmptLahir, et_ttl, et_umur, et_pendidikan, et_jurusan, et_angkatan, et_jenkel, et_perkawinan, et_agama, et_alamat, et_email, et_hp;
    DatabaseReference reference;
    ProgressDialog progressDialog;
    FirebaseUser user;
    RelativeLayout relay_daftar;
    FloatingActionButton print;
    Bitmap bitmap;

    public DataDiriActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_diri);

        et_minatKejuruan = findViewById(R.id.edt_kejuruan);
        et_nik = findViewById(R.id.edt_nik);
        et_nama = findViewById(R.id.edt_nama);
        et_tmptLahir = findViewById(R.id.edt_tempatLahir);
        et_ttl = findViewById(R.id.edt_ttl);
        et_umur = findViewById(R.id.edt_umur);
        et_pendidikan = findViewById(R.id.edt_pendidikan);
        et_jurusan = findViewById(R.id.edt_jurusan);
        et_angkatan = findViewById(R.id.edt_tamat);
        et_jenkel = findViewById(R.id.edt_jenkel);
        et_perkawinan = findViewById(R.id.edt_perkawinan);
        et_agama = findViewById(R.id.edt_agama);
        et_alamat = findViewById(R.id.edt_alamat);
        et_hp = findViewById(R.id.edt_hp);
        et_email = findViewById(R.id.edt_email);
        print = findViewById(R.id.print);

        relay_daftar = findViewById(R.id.relay_daftar);

        progressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("size"," "+relay_daftar.getWidth() +"  "+relay_daftar.getWidth());
                bitmap = loadBitmapFromView(relay_daftar, relay_daftar.getWidth(), relay_daftar.getHeight());
                createPdf();
            }
        });

        getDataDiri();
    }

    private void getDataDiri() {
        progressDialog.show();
        progressDialog.setMessage("Tunggu sebentar yaa..");

        FirebaseDatabase.getInstance().getReference("Calon Peserta").child(user.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        et_minatKejuruan.setText(user.getPeminatan_jurusan());
                        et_nik.setText(user.getNik_peserta());
                        et_nama.setText(user.getNamaLengkap());
                        et_tmptLahir.setText(user.getTempatLahir());
                        et_ttl.setText(user.getTanggalLahir());
                        et_umur.setText(user.getUmurPeserta());
                        et_pendidikan.setText(user.getPendidikan_terakhir());
                        et_jurusan.setText(user.getJurusan_sekolah());
                        et_angkatan.setText(user.getTahun_selesai());
                        et_jenkel.setText(user.getJenis_kelamin());
                        et_perkawinan.setText(user.getStatusHubungan());
                        et_agama.setText(user.getAgama_peserta());
                        et_alamat.setText(user.getAlamat_peserta());
                        et_email.setText(user.getEmail_peserta());
                        et_hp.setText(user.getNohp());

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Terjadi kesalahan saat mengambil data",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/print.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF of Scroll is created!!!", Toast.LENGTH_LONG).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF(){
        File file = new File("/sdcard/print.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(DataDiriActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
}