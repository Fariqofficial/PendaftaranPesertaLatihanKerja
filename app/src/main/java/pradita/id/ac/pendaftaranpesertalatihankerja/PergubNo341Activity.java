package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PergubNo341Activity extends AppCompatActivity {

    PDFView pergubNo341;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergub_no341);

        pergubNo341 = findViewById(R.id.pdfPergubNo341);

        pergubNo341.fromAsset("PERGUB_NO._341_TAHUN_2016_.pdf").load();
    }
}
