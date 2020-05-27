package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import pradita.id.ac.pendaftaranpesertalatihankerja.Adapter.ListPesertaBhsInggrisAdapter;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.ListPesertaBing;

public class ListPesertaBahasaInggrisActivity extends AppCompatActivity {

    RecyclerView rvPesertaBing;
    private ArrayList<ListPesertaBing> listPesertaBinggris;
    private FirebaseRecyclerAdapter<ListPesertaBing, ListPesertaBhsInggrisAdapter> adapter;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_peserta_bahasa_inggris);

        rvPesertaBing = findViewById(R.id.rvPesertaBing);
        rvPesertaBing.setHasFixedSize(true);
        rvPesertaBing.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("Calon Peserta");

        listPesertaBinggris = new ArrayList<ListPesertaBing>();
    }
}
