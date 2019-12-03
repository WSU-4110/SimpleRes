package com.example.simpleres;

import android.content.Context;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

class ResPartyAdapter extends ArrayAdapter<WaitlistEntry> {
    private final Context mContext;
    private final List<WaitlistEntry> partyList;

    ResPartyAdapter(@NonNull Context context, ArrayList<WaitlistEntry> list) {
        super(context, 0 , list);
        mContext = context;
        partyList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.reslist_item_layout,parent,false);

        WaitlistEntry currentParty = partyList.get(position);

        //display the time of reservation
        TextView time = listItem.findViewById(R.id.timeofreservation);
        time.setText(currentParty.parseTime());

        //display the name of the customer
        TextView name = listItem.findViewById(R.id.nameOfResParty);
        name.setText(currentParty.getName());

        //display the size of the customers
        TextView size = listItem.findViewById(R.id.sizeOfResParty);
        String numberOfPeople = Integer.toString(currentParty.getNumberOfPeople());
        size.setText(numberOfPeople);

        //display the checkbox state in the list
        CheckBox here = listItem.findViewById(R.id.Here);
        here.setChecked(currentParty.getCheckBox()==1);
        return listItem;
    }

}
