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

import pradita.id.ac.pendaftaranpesertalatihankerja.Model.ItemPartnership;

public class PartnershipActivity extends AppCompatActivity {

    RecyclerView rvPartnership;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partnership);

        rvPartnership = findViewById(R.id.rv_partnerhsip);
        reference= FirebaseDatabase.getInstance().getReference().child("Partnership");
        reference.keepSynced(true);

        rvPartnership.setHasFixedSize(true);
        rvPartnership.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ItemPartnership, ItemPartnershipViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ItemPartnership, ItemPartnershipViewHolder>
                (ItemPartnership.class, R.layout.item_partnership, ItemPartnershipViewHolder.class, reference) {
            @Override
            protected void populateViewHolder(ItemPartnershipViewHolder viewHolder, ItemPartnership model, int position) {
                viewHolder.setNama_perusahaan(model.getNama_perusahaan());
                viewHolder.setWebsite_perusahaan(model.getWebsite_perusahaan());
                viewHolder.setAlamat_perusahaan(model.getAlamat_perusahaan());
            }
        };

        rvPartnership.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ItemPartnershipViewHolder extends RecyclerView.ViewHolder{
        View view;
        public ItemPartnershipViewHolder(View itemView){
            super(itemView);
            view = itemView;
        }
        public void setNama_perusahaan(String nama_perusahaan){
            TextView postName = (TextView)view.findViewById(R.id.namaPerusahaan);
            postName.setText(nama_perusahaan);
        }
        public void setWebsite_perusahaan(String email_perusahaan){
            TextView postWebsite = (TextView)view.findViewById(R.id.webPerusahaan);
            postWebsite.setText(email_perusahaan);
        }
        public void setAlamat_perusahaan(String alamat_perusahaan){
            TextView postAlamat = (TextView)view.findViewById(R.id.alamatPerusahaan);
            postAlamat.setText(alamat_perusahaan);
        }
    }
}
