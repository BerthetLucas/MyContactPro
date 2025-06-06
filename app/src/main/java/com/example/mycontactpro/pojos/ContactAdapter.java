package com.example.mycontactpro.pojos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontactpro.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private OnItemClickListener listener;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.tvFirstName.setText(contact.getNom());
        holder.tvLastName.setText(contact.getPrenom());
        holder.tvCompany.setText(contact.getSociete());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Contact contact);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLastName, tvFirstName, tvCompany;

        public ContactViewHolder(View itemView) {
            super(itemView);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
        }
    }
}
