package com.example.contactmvvmandroom.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmvvmandroom.R;
import com.example.contactmvvmandroom.data.Models.ModelUser;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.Holder> {
    private ArrayList<ModelUser> list;
    setOnContactListener onContactListener;

    public void setList(ArrayList<ModelUser> list) {
        this.list = list;
    }

    public void setOnContactListener(setOnContactListener onContactListener) {
        this.onContactListener = onContactListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact_recycler, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String fullName = list.get(position).getFirstName() + " " + list.get(position).getLastName();
        holder.textName.setText(fullName);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textName;
        CircleImageView delete;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            delete = itemView.findViewById(R.id.btn_delete);
            textName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onContactListener != null) {
                        onContactListener.onItemClick(list.get(getLayoutPosition()).getFirstName()
                                , list.get(getLayoutPosition()).getLastName(),
                                list.get(getLayoutPosition()).getNumber()
                                , list.get(getLayoutPosition()).getId()
                        );

                    }

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onContactListener != null) {
                        onContactListener.onDeleteClick(list.get(getLayoutPosition()));
                    }
                }
            });

        }

    }

    public interface setOnContactListener {
        void onItemClick(String fName, String lName, String number, int id);

        void onDeleteClick(ModelUser modelUser);


    }

}
