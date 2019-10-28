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

public class WaitPartyAdapter extends ArrayAdapter<Party> {
    private Context mContext;
    private List<Party> partyList = new ArrayList<>();

    public WaitPartyAdapter(@NonNull Context context, @LayoutRes ArrayList<Party> list) {
        super(context, 0 , list);
        mContext = context;
        partyList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.waitlist_list_item,parent,false);

        Party currentParty = partyList.get(position);

        TextView time = (TextView) listItem.findViewById(R.id.timeofWaitlist);
        time.setText(currentParty.getTime());

        TextView name = (TextView) listItem.findViewById(R.id.nameofWaitparty);
        name.setText(currentParty.getPname());

        TextView size = (TextView) listItem.findViewById(R.id.sizeofWaitparty);
        size.setText(currentParty.getPartySize());

        return listItem;
    }
}
