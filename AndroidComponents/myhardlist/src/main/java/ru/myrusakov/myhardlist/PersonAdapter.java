package ru.myrusakov.myhardlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private LayoutInflater lif;
    private int resource;
    private List<Person> persons;

    public PersonAdapter(@NonNull Context context, int resource, List<Person> persons) {
        super(context, resource, persons);
        this.lif = LayoutInflater.from(context);
        this.resource = resource;
        this.persons = persons;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = lif.inflate(this.resource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();
        final Person p = persons.get(position);
        viewHolder.nameView.setText(p.getName());
        viewHolder.ageView.setText("" + p.getAge());

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persons.remove(p);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        final TextView nameView, ageView;
        final Button button;

        public ViewHolder(View view) {
            nameView = view.findViewById(R.id.name);
            ageView = view.findViewById(R.id.age);
            button = view.findViewById(R.id.delete);
        }

    }

}
