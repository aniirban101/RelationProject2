package com.example.android.relationproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NewList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final String[] relation;
    private final Integer[] imageId;
    public Firebase firebase;
    TextView txtname,txtrelation;
    ImageView imageView;

    public NewList(Activity context, String[] name, String[] relation, Integer[] imageId) {
        super(context, R.layout.list_main, name);
        this.context = context;
        this.relation = relation;
        this.name = name;
        this.imageId = imageId;
        Firebase.setAndroidContext(getContext());
        firebase = new Firebase("https://relationproject2.firebaseio.com");

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_main, null, true);

        txtname = (TextView) rowView.findViewById(R.id.tv1);
        txtrelation = (TextView) rowView.findViewById(R.id.tv2);
        imageView = (ImageView) rowView.findViewById(R.id.iv);

        txtname.setText(name[position]);
        txtrelation.setText(relation[position]);
        imageView.setImageResource(imageId[position]);
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                txtname.setText(String.valueOf(dataSnapshot.child(String.valueOf(position)).child("Name").getValue()));
                txtrelation.setText(String.valueOf(dataSnapshot.child(String.valueOf(position)).child("Relation").getValue()));
                Picasso.with(getContext()).load(String.valueOf(dataSnapshot.child(String.valueOf(position)).child("ImageUrl").getValue()))
                        .into(imageView);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        return rowView;
        }
    }