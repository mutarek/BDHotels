package com.techtrickbd.bdhotels.repoisotory;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techtrickbd.bdhotels.activities.FullHotelsActivity;
import com.techtrickbd.bdhotels.adapters.HotelsAdapter;
import com.techtrickbd.bdhotels.interfaces.HotelsClick;
import com.techtrickbd.bdhotels.models.Facilities;
import com.techtrickbd.bdhotels.models.HotelModel;

import java.util.ArrayList;
import java.util.List;

public class Hotelrepository {
    private static Hotelrepository instance;
    private ArrayList<HotelModel> dataset = new ArrayList<>();
    private HotelsAdapter hotelsAdapter;
    private Context context;

    public static Hotelrepository getInstance()
    {
        if (instance ==null)
        {
            instance = new Hotelrepository();
        }
        return instance;
    }

    public MutableLiveData<List<HotelModel>> getAllHotels() {
        setHotels();
        MutableLiveData<List<HotelModel>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }

    private void setHotels()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("test");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        HotelModel hotelModel = document.toObject(HotelModel.class);
                        hotelModel.setFacilities(new Facilities());
                        dataset.add(hotelModel);
                        hotelsAdapter = new HotelsAdapter(new HotelsClick() {
                            @Override
                            public void OnHotelsClick(View hotelView, int hotelPosition) {
                                Intent intent = new Intent(context, FullHotelsActivity.class);
                                intent.putExtra("desc",dataset.get(hotelPosition).getDescription());
                                context.startActivity(intent);
                            }
                        }, dataset, context);

                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "" + e.getMessage().toString());
            }
        });
    }
}
