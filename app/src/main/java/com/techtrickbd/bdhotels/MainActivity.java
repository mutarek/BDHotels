package com.techtrickbd.bdhotels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyllerView);
        hotelModelList = new ArrayList<>();
        list.add("imageone");
        list.add("imageone");
        list.add("imageone");
        list.add("imageone");
        list.add("imageone");
        general.add("hellogeneral");
        general.add("hellogeneral");
        misc.add("hellomisc");
        misc.add("hellomisc");
        misc.add("hellomisc");
        service.add("helloservice");
        service.add("helloservice");
        service.add("helloservice");
        hotelModel.setDescription("Test desc");
        hotelModel.setHotelimages((ArrayList<String>) list);
        hotelModel.setRoom_exist(true);
        hotelModel.setName("helloname");
        hotelModel.setRating("4.6");
        facilities.setExtra("extra");
        facilities.setGeneral((ArrayList<String>) general);
        facilities.setInternet(true);
        facilities.setMiscellaneous((ArrayList<String>) misc);
        facilities.setParking(false);
        facilities.setServices((ArrayList<String>) service);
        hotelModel.setFacilities(facilities);
        hotelModel.setPrice(1200);
        hotelModel.setLocation(new GeoPoint(1.2, 1.3));


    }

    public void adddata(View view) {
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
    }

    public void loaddata(View view) {
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
                       },hotelModelList,context);

                        recyclerView.setAdapter(hotelsAdapter);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);

                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG",""+e.getMessage().toString());
            }
        });
    }
}
