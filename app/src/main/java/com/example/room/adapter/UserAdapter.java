package com.example.room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room.R;
import com.example.room.database.entitas.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewAdapter>{
    private List<User> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.NamaRuang.setText(list.get(position).NamaRuang);
        holder.Kapasitas.setText(list.get(position).Kapasitas);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView NamaRuang, Kapasitas;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            NamaRuang = itemView.findViewById(R.id.NamaRuang);
            Kapasitas = itemView.findViewById(R.id.Kapasitas);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog != null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
