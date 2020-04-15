package com.techtrickbd.bdhotels.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.techtrickbd.bdhotels.models.HotelModel;
import com.techtrickbd.bdhotels.repoisotory.Hotelrepository;

import java.util.List;

public class HotelViewModel extends ViewModel {
    private MutableLiveData<List<HotelModel>> mhotels;
    private Hotelrepository mrepo;

    public void init(){
        if (mhotels !=null)
        {
            return;
        }
        mrepo = Hotelrepository.getInstance();
        mhotels = mrepo.getAllHotels();

    }

    public LiveData<List<HotelModel>> getHotels(){
        return mhotels;
    }
}
