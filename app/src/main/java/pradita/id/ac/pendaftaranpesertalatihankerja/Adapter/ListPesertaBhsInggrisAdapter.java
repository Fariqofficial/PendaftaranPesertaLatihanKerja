package pradita.id.ac.pendaftaranpesertalatihankerja.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pradita.id.ac.pendaftaranpesertalatihankerja.R;

public class ListPesertaBhsInggrisAdapter extends RecyclerView.ViewHolder {

    public TextView namaPeserta, emailPeserta, jurusanPeserta;


    public ListPesertaBhsInggrisAdapter (@NonNull View itemView) {
        super(itemView);

        namaPeserta = itemView.findViewById(R.id.namaPesertaBing);
        emailPeserta = itemView.findViewById(R.id.emailPesertaBing);
        jurusanPeserta = itemView.findViewById(R.id.jurusanPesertaBing);
    }
}
