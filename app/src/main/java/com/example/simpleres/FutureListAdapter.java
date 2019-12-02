package com.example.simpleres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FutureListAdapter extends ArrayAdapter<WaitlistEntry> {
    private Context mContext;
    private List<WaitlistEntry> partyList;

    FutureListAdapter(@NonNull Context context, ArrayList<WaitlistEntry> list) {
        super(context, 0 , list);
        mContext = context;
        partyList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.futurelist_item,parent,false);

        WaitlistEntry currentParty = partyList.get(position);
        //display the time of reservation
        TextView time = listItem.findViewById(R.id.timeofFuturereservation);
        time.setText(currentParty.parseTime());

        //display the name of the customer
        TextView name = listItem.findViewById(R.id.nameOfFutureParty);
        name.setText(currentParty.getName());

        //display the size of the customers
        TextView size = listItem.findViewById(R.id.sizeofFutureparty);
        String numberOfPeople = Integer.toString(currentParty.getNumberOfPeople());
        size.setText(numberOfPeople);

        return listItem;
    }
}
