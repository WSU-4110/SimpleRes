package com.example.simpleres;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
//It fits and connects the info of the customer to layout design of the row Item in the reservation list
public class ResPartyAdapter extends ArrayAdapter<WaitlistEntry> {
    private Context mContext;
    private List<WaitlistEntry> partyList = new ArrayList<>();

    public ResPartyAdapter(@NonNull Context context, @LayoutRes ArrayList<WaitlistEntry> list) {
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
        TextView time = (TextView) listItem.findViewById(R.id.timeofreservation);
        time.setText(currentParty.ParseTime());

        //display the name of the customer
        TextView name = (TextView) listItem.findViewById(R.id.nameofResparty);
        name.setText(currentParty.getName());

        //display the size of the customers
        TextView size = (TextView) listItem.findViewById(R.id.sizeofResparty);
        size.setText(Integer.toString(currentParty.getNumberOfPeople()));

        return listItem;
    }
}
