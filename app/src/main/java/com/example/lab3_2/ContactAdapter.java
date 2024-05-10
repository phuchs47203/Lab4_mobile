package com.example.lab3_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Activity context;
    public ContactAdapter(Activity context, int layoutID, List<Contact> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, null, false);
        }

        Contact contact = getItem(position);

        TextView tvID = (TextView) convertView.findViewById(R.id.tv_id);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvPhoneNo = (TextView) convertView.findViewById(R.id.tv_phoneno);
        tvID.setText(String.valueOf(contact.getID()));
        tvName.setText(contact.getName());
        tvPhoneNo.setText(contact.getPhoneNumber());

        return convertView;
    }
}
