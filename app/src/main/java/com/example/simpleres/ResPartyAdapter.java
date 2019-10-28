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

public class ResPartyAdapter extends ArrayAdapter<Party> {
    private Context mContext;
    private List<Party> partyList = new ArrayList<>();

    public ResPartyAdapter(@NonNull Context context, @LayoutRes ArrayList<Party> list) {
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

        Party currentParty = partyList.get(position);

        TextView time = (TextView) listItem.findViewById(R.id.timeofreservation);
        time.setText(currentParty.getTime());

        TextView name = (TextView) listItem.findViewById(R.id.nameofResparty);
        name.setText(currentParty.getPname());

        TextView size = (TextView) listItem.findViewById(R.id.sizeofResparty);
        size.setText(currentParty.getPartySize());

        return listItem;
    }
}
