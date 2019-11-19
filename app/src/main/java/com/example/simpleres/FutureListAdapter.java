package com.example.simpleres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FutureListAdapter extends ArrayAdapter<WaitlistEntry> {
    private Context mContext;
    private List<WaitlistEntry> partyList = new ArrayList<>();

    public FutureListAdapter(@NonNull Context context, @LayoutRes ArrayList<WaitlistEntry> list) {
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
        TextView time = (TextView) listItem.findViewById(R.id.timeofFuturereservation);
        time.setText(currentParty.parseTime());

        //display the name of the customer
        TextView name = (TextView) listItem.findViewById(R.id.nameofFutureparty);
        name.setText(currentParty.getName());

        //display the size of the customers
        TextView size = (TextView) listItem.findViewById(R.id.sizeofFutureparty);
        size.setText(Integer.toString(currentParty.getNumberOfPeople()));

        return listItem;
    }
}
