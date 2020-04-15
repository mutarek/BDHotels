package com.techtrickbd.bdhotels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.LocusId;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techtrickbd.bdhotels.adapters.HotelsAdapter;
import com.techtrickbd.bdhotels.interfaces.HotelsClick;
import com.techtrickbd.bdhotels.models.Facilities;
import com.techtrickbd.bdhotels.models.HotelModel;
import com.techtrickbd.bdhotels.viewmodels.HotelViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("test");
    HotelModel hotelModel = new HotelModel();
    Facilities facilities = new Facilities();
    List<String> list = new ArrayList<>();
    List<String> general = new ArrayList<>();
    List<String> misc = new ArrayList<>();
    List<String> service = new ArrayList<>();
    private RecyclerView recyclerView;
    private List<HotelModel> hotelModelList;
    private HotelsClick hotelsClick;
    private Context context;
    private HotelsAdapter hotelsAdapter;
    private HotelViewModel mhotelViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyllerView);
        mhotelViewModel = ViewModelProviders.of(this).get(HotelViewModel.class);
        mhotelViewModel.init();
        mhotelViewModel.getHotels().observe(this, new Observer<List<HotelModel>>() {
            @Override
            public void onChanged(List<HotelModel> hotelModels) {
                hotelsAdapter.notifyDataSetChanged();
            }
        });

        initRecyller();

    }

    private void initRecyller() {
        hotelsAdapter = new HotelsAdapter( hotelsClick,mhotelViewModel.getHotels().getValue(),this);
        recyclerView.setAdapter(hotelsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    /*public void adddata(View view) {
        collectionReference.add(hotelModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "add", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "failed" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    /*public void loaddata(View view) {
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        HotelModel hotelModel = document.toObject(HotelModel.class);
                        hotelModel.setFacilities(new Facilities());
                        hotelModelList.add(hotelModel);
                        hotelsAdapter = new HotelsAdapter(new HotelsClick() {
                            @Override
                            public void OnHotelsClick(View hotelView, int hotelPosition) {

                            }
                        }, hotelModelList, context);

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
        });*/
}
