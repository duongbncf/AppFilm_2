package com.example.appfilm_2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.appfilm_2.adapter.ViewPagerAdapter;
import com.example.appfilm_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    // implement movieclick
//    DatabaseReference myRef;
//    ArrayList<DataModel> dataModelList;
//    CategoryAdapter categoryAdapter;
//    RecyclerView rcv;
//    Banner<DataModel> banner;
    BottomNavigationView mNavigationView;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);
        mNavigationView = findViewById(R.id.bottom_nav);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpViewPager();
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_search:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_comingsoon:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_menu:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });


//        banner = findViewById(R.id.banner);
//        rcv = findViewById(R.id.rv_photos);
//        dataModelList = new ArrayList<>();
//        categoryAdapter = new CategoryAdapter(MainActivity.this, this);
//        rcv.setAdapter(categoryAdapter);
//        rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
//        rcv.setHasFixedSize(true);
//        myRef = FirebaseDatabase.getInstance().getReference("trailer");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    DataModel dataModel = data.getValue(DataModel.class);
//                    dataModelList.add(dataModel);
//                    banner.setBannerData(dataModelList);
//                    banner.setOnItemClickListener(new Banner.OnItemClickListener<DataModel>() {
//                        @Override
//                        public void onItemClick(int position, DataModel item) {
//                            Toast.makeText(MainActivity.this, item.getTtitle(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    banner.setOnItemBindListener(new Banner.OnItemBindListener<DataModel>() {
//                        @Override
//                        public void onItemBind(int position, DataModel item, ImageView view) {
//                            Glide.with(view).load(item.getTurl()).into(view);
//                        }
//                    });
//                }
//                //categoryAdapter.setDataModels(dataModelList);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot data : snapshot.getChildren()) {
//                    DataModel dataModel = data.getValue(DataModel.class);
//                    dataModelList.add(dataModel);
//                }
//                categoryAdapter.setDataModels(dataModelList);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//
//    private void setDataModelList() {
//
//    }
//
//    @Override
//    public void onMovieClick(DataModel dataModel, ImageView movieImageView) {
//        Intent intent = new Intent(MainActivity.this,MovieDetailActivity.class);
//        intent.putExtra("title", dataModel.getTtitle());
//        intent.putExtra("url",dataModel.getTurl());
//        intent.putExtra("video",dataModel.getTvid());
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView,"shareName");
//        startActivity(intent,options.toBundle());
//    }
    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mNavigationView.getMenu().findItem(R.id.action_search).setChecked(true);
                        break;
                    case 2:
                        mNavigationView.getMenu().findItem(R.id.action_comingsoon).setChecked(true);
                        break;
                    case 3:
                        mNavigationView.getMenu().findItem(R.id.action_menu).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}