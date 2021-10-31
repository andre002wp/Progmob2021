package Praktikum.Andre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> implements Filterable {
    Context context;
    private ArrayList<Mahasiswa> mahasiswaDataAll;
    private ArrayList<Mahasiswa> mahasiswaData;
    private EditMahasiswa editMahasiswa;
    private HapusMahasiswa deleteMahasiswa;

    public MahasiswaAdapter(Context context, ArrayList<Mahasiswa> mahasiswaData,EditMahasiswa editMahasiswa, HapusMahasiswa hapusMahasiswa){
        this.context = context;
        this.mahasiswaData = mahasiswaData;
        this.mahasiswaDataAll = new ArrayList<>(mahasiswaData);
        this.editMahasiswa = editMahasiswa;
        this.deleteMahasiswa = hapusMahasiswa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_mahasiswa,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama.setText(mahasiswaData.get(position).getNama());
        holder.alamat.setText(mahasiswaData.get(position).getAlamat());
        holder.prodi.setText(mahasiswaData.get(position).getJurusan());
        holder.hobi.setText(mahasiswaData.get(position).getHobi());
        holder.ketertarikan.setText(mahasiswaData.get(position).getKetertarikan());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMahasiswa.edit(position);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMahasiswa.hapus(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mahasiswaData.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Mahasiswa> filtered = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filtered.addAll(mahasiswaDataAll);
            }
            else {
                for (Mahasiswa ma : mahasiswaDataAll){
                    if(ma.getNama().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filtered.add(ma);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mahasiswaData.clear();
            mahasiswaData.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama;
        public TextView alamat;
        public TextView prodi;
        public TextView hobi;
        public TextView ketertarikan;
        public Button btnEdit;
        public Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.txt_nama);
            alamat = itemView.findViewById(R.id.txtAlamat);
            prodi = itemView.findViewById(R.id.txtProdi);
            hobi = itemView.findViewById(R.id.txtHobi);
            ketertarikan = itemView.findViewById(R.id.txt_ketertarikan);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface EditMahasiswa {
        void edit(int position);
    }

    public interface HapusMahasiswa {
        void hapus(int position);
    }
}
