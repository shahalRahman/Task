package com.shahal.assignmentproject.api;

import com.shahal.assignmentproject.model.AnnouncementData;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by shahal on 23-04-2018.
 */

public interface AnnouncementApi  {
    @POST("/EMC/IPDP/ipdpb.ashx")
    Call<ArrayList<AnnouncementData>> getAnnouncements(@QueryMap HashMap<String,String> maps);
}
