package pradita.id.ac.pendaftaranpesertalatihankerja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pradita.id.ac.pendaftaranpesertalatihankerja.Model.ItemAlumni;
import pradita.id.ac.pendaftaranpesertalatihankerja.Model.ItemPartnership;

public class LulusanActivity extends AppCompatActivity {

    RecyclerView rvDaftarAlumni;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lulusan);

        rvDaftarAlumni = findViewById(R.id.rv_lulusan);
        reference= FirebaseDatabase.getInstance().getReference().child("Alumni");
        reference.keepSynced(true);

        rvDaftarAlumni.setHasFixedSize(true);
        rvDaftarAlumni.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ItemAlumni, ItemAlumniViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ItemAlumni, ItemAlumniViewHolder>
                (ItemAlumni.class, R.layout.item_lulusan, ItemAlumniViewHolder.class, reference) {
            @Override
            protected void populateViewHolder(ItemAlumniViewHolder viewHolder, ItemAlumni modelAlumni, int position) {
                viewHolder.setNama_peserta(modelAlumni.getNama_peserta());
                viewHolder.setAngkatan(modelAlumni.getAngkatan());
                viewHolder.setKejuruan(modelAlumni.getKejuruan());
                viewHolder.setNama_pt(modelAlumni.getNama_pt());
                viewHolder.setJenis_pt(modelAlumni.getJenis_pt());
            }
        };

        rvDaftarAlumni.setAdapter(firebaseRecyclerAdapter);
    }
    public static class ItemAlumniViewHolder extends RecyclerView.ViewHolder{
        View view;
        public ItemAlumniViewHolder(View itemView){
            super(itemView);
            view = itemView;
        }
        public void setNama_peserta (String nama_peserta){
            TextView postName = (TextView)view.findViewById(R.id.namaAlumni);
            postName.setText(nama_peserta);
        }
        public void setAngkatan(String angkatan){
            TextView postAngkatan = (TextView)view.findViewById(R.id.angkatanLulusan);
            postAngkatan.setText(angkatan);
        }
        public void setKejuruan(String kejuruan){
            TextView postKejuruan = (TextView)view.findViewById(R.id.lulusanJurusan);
            postKejuruan.setText(kejuruan);
        }
        public void setNama_pt(String nama_pt){
            TextView postNamaPt = (TextView)view.findViewById(R.id.namaPt);
            postNamaPt.setText(nama_pt);
        }
        public void setJenis_pt(String jenis_pt){
            TextView postJenisPt = (TextView)view.findViewById(R.id.jenisPt);
            postJenisPt.setText(jenis_pt);
        }
    }
}
