package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PergubNo33Activity extends AppCompatActivity {

    PDFView pergubNo33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergub_no33);

        pergubNo33 = findViewById(R.id.pdfPergubNo33);

        pergubNo33.fromAsset("PERGUB_NO_33_TAHUN_2015.pdf").load();
    }
}
