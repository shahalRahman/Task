package com.shahal.assignmentproject.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.shahal.assignmentproject.R;
import com.shahal.assignmentproject.adapter.AnnouncementAdapter;
import com.shahal.assignmentproject.api.AnnouncementApi;
import com.shahal.assignmentproject.listener.OnAnnouncementClickListener;
import com.shahal.assignmentproject.model.AnnouncementData;
import com.shahal.assignmentproject.networkmanager.ServiceGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shahal.assignmentproject.constants.AppConstant.*;
import static com.shahal.assignmentproject.utils.NetworkUtil.isNetworkConnected;

public class MainActivity extends AppCompatActivity implements OnAnnouncementClickListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_announcements)
    RecyclerView rvAnnouncements;

    @BindView(R.id.swipeRefreshHome)
    SwipeRefreshLayout refreshLayout;

    ArrayList<AnnouncementData> announcementData;
    AnnouncementAdapter announcementAdapter;

    private Unbinder unbinder;
    private boolean isThumb = true;
    private Menu mMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        refreshLayout.setOnRefreshListener(this);
        setRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (announcementData == null || announcementData.size() <= 0) {
            refreshLayout.setRefreshing(true);
            getAnnouncements();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setRecyclerView() {
        announcementData = new ArrayList<>();
        rvAnnouncements.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvAnnouncements.setLayoutManager(linearLayoutManager);
        announcementAdapter = new AnnouncementAdapter(this, announcementData, this);
        rvAnnouncements.setAdapter(announcementAdapter);
    }

    @Override
    public void onAnnouncementClicked(String title, String htmlText) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(ANNOUNCEMENT_TITLE, title);
        intent.putExtra(ANNOUNCEMENT_HTML, htmlText);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.full_menu).setChecked(!isThumb);
        menu.findItem(R.id.thumb_menu).setChecked(isThumb);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.full_menu:
                item.setChecked(!item.isChecked());
                isThumb = !item.isChecked();
                mMenu.findItem(R.id.thumb_menu).setChecked(isThumb);
                updateRecyclerView();
                return true;
            case R.id.thumb_menu:
                item.setChecked(!item.isChecked());
                isThumb = item.isChecked();
                mMenu.findItem(R.id.full_menu).setChecked(!isThumb);
                updateRecyclerView();
                return true;
            default:
                return false;
        }

    }

    private void getAnnouncements() {
        if (!isNetworkConnected(this)) {
            Toast.makeText(this, "No network available, please check your WiFi or Data connection", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("TemplateName", "Promotions_ipad.htm");
        hashMap.put("p", "Common.Announcements");
        hashMap.put("Handler", "News");
        hashMap.put("AppName", "EMC");
        hashMap.put("Type", "News");
        hashMap.put("F", "J");
        Call<ArrayList<AnnouncementData>> request = ServiceGenerator.createService(AnnouncementApi.class).getAnnouncements(hashMap);
        request.enqueue(new Callback<ArrayList<AnnouncementData>>() {
            @Override
            public void onResponse(Call<ArrayList<AnnouncementData>> call, Response<ArrayList<AnnouncementData>> response) {
                if (!response.isSuccessful()) {
                    refreshLayout.setRefreshing(false);
                    return;
                }
                announcementData = response.body();
                updateRecyclerView();
            }

            @Override
            public void onFailure(Call<ArrayList<AnnouncementData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });
    }


    private void updateRecyclerView() {
        if (announcementAdapter == null) {
            announcementAdapter = new AnnouncementAdapter(this, announcementData, this);
            rvAnnouncements.setAdapter(announcementAdapter);
        }
        announcementAdapter.updateData(announcementData, isThumb);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        if (announcementAdapter == null) {
            setRecyclerView();
        }
        refreshLayout.setRefreshing(true);
        getAnnouncements();
    }
}
