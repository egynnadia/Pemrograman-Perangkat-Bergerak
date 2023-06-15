package com.example.oslearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MateriAdapter extends ArrayAdapter<MateriData> {
    private Context ct;
    private ArrayList<MateriData> arr;
    public MateriAdapter(@NonNull Context context, int resource, @NonNull List<MateriData> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater i = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.single_materi_item, null);
        }
        if(arr.size()>0){
            MateriData m = arr.get(position);
            TextView namaBab = convertView.findViewById(R.id.namaBab);

            namaBab.setText(m.name);
        }
        return convertView;
    }
}
