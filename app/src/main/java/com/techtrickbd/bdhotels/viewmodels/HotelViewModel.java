package com.techtrickbd.bdhotels.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.techtrickbd.bdhotels.models.HotelModel;
import com.techtrickbd.bdhotels.repoisotory.Hotelrepository;

import java.util.List;

public class HotelViewModel extends ViewModel {
    private MutableLiveData<List<HotelModel>> mhotels;
    private Hotelrepository mrepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mhotels !=null)
        {
            return;
        }
        mrepo = Hotelrepository.getInstance();
        mhotels = mrepo.getAllHotels();

    }

    public void addnewValue(){
        mIsUpdating.setValue(true);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<HotelModel>> getHotels(){
        return mhotels;
    }


    public LiveData<Boolean> getIsUpdating()
    {
        return mIsUpdating;
    }
}
