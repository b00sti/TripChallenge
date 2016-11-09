package com.example.b00sti.tripchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.b00sti.tripchallenge.data.Attraction;
import com.example.b00sti.tripchallenge.firebase.FirebaseManager;
import com.example.b00sti.tripchallenge.utils.FragmentSwitcher;
import com.example.b00sti.tripchallenge.utils.drawer.DrawerAdapter;
import com.example.b00sti.tripchallenge.utils.drawer.DrawerUtils;
import com.example.b00sti.tripchallenge.utils.drawer.SwitchFragmentEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @ViewById(R.id.drawer_layout) public DrawerLayout drawer;
    @ViewById(R.id.drawer_recycler_view) public RecyclerView drawerRecyclerView;
    @ViewById(R.id.listView) public ListView listView;
    @ViewById(R.id.todoText) public EditText text;
    @ViewById(R.id.addButton) public Button button;
    @ViewById(R.id.drawer_layout) public DrawerLayout drawerLay;
    public ActionBarDrawerToggle toggle;
    @ViewById Toolbar toolbar;
    @DrawerUtils.DrawerTab private int drawerCurrentlySelectedTab = DrawerUtils.NO_TAB;

    @AfterViews
    void test() {

        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);

        initDrawer();

        if (!FirebaseManager.getInstance().isUserLogged()) {
            loadLogInView();
        } else {

            // Set up ListView
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
            listView.setAdapter(adapter);

            Attraction venece = new Attraction("1", "Pl.św. Marka", false);
            Attraction lisboa = new Attraction("2", "Costa del sol", true);

            DatabaseReference mDatabase = FirebaseManager.getInstance().getMainDatabaseRef();
            String mUserId = FirebaseManager.getInstance().getUserId();

            button.setOnClickListener(v -> {
                mDatabase.child("users").child(mUserId).child("attractions").push().child("name").setValue(venece.getName());
                mDatabase.child("users").child(mUserId).child("attractions").push().child("id").setValue(venece.getId());
                mDatabase.child("users").child(mUserId).child("attractions").push().child("isVisited").setValue("" + venece.isVisited());

                mDatabase.child("users").child(mUserId).child("attractions").push().child("name").setValue(lisboa.getName());
                mDatabase.child("users").child(mUserId).child("attractions").push().child("isVisited").setValue("" + lisboa.isVisited());
                mDatabase.child("users").child(mUserId).child("attractions").push().child("id").setValue(lisboa.getId());
                mDatabase.child("users").child(mUserId).child("attract").push().child("new").setValue(lisboa);
                text.setText("");
            });

            // Use Firebase to populate the list.
            mDatabase.child("users").child(mUserId).child("attractions").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Object str = dataSnapshot.child("name").getValue();
                    if (str != null) {
                        adapter.add((String) str);
                    }
                    Log.d(TAG, "onChildAdded: " + dataSnapshot.child("name").getValue());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    //adapter.remove((String) dataSnapshot.child("name").getValue());
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            // Delete items when clicked
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mDatabase.child("users").child(mUserId).child("attractions")
                            .orderByChild("name")
                            .equalTo((String) listView.getItemAtPosition(position))
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.hasChildren()) {
                                        DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                        firstChild.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                }
            });
        }
        if (!FirebaseManager.getInstance().isUserLogged()) {
            loadLogInView();
        }
    }

    private void initDrawer() {
        prepareDrawerMenuRecyclerView();

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.common_google_signin_btn_icon_dark_pressed));
        toggle.setToolbarNavigationClickListener(view -> {
            if (drawer.isDrawerVisible(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                //ViewUtils.hideKeyboard(this);
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    private void prepareDrawerMenuRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        drawerRecyclerView.setLayoutManager(layoutManager);
        drawerRecyclerView.setHasFixedSize(true);

        // set dashboard as initial tab if no tab is selected
        if (drawerCurrentlySelectedTab < 0) drawerCurrentlySelectedTab = getDefaultTabId();
        // IN ORDER TO CHANGE MENU ITEMS GO THERE
        drawerRecyclerView.setAdapter(DrawerUtils.initDrawerItemsAdapter(drawerRecyclerView, drawer, drawerCurrentlySelectedTab, toolbar, this));
    }

    public
    @DrawerUtils.DrawerTab
    int getDefaultTabId() {
        return DrawerUtils.DASHBOARD_TAB;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        if (drawerLay.isDrawerOpen(GravityCompat.START)) {
            drawerLay.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity_.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Subscribe
    public void onEvent(SwitchFragmentEvent event) {
        this.drawerCurrentlySelectedTab = event.getDrawerItemSelected();

        Fragment targetFragment = event.getTargetFragment();

        switch (this.drawerCurrentlySelectedTab) {
            //BUTTONS
            case DrawerUtils.NO_TAB:
                drawerRecyclerView.setAdapter(new DrawerAdapter(DrawerUtils.getDataSet(), DrawerUtils.NO_TAB, this));
                break;
            //TABS
            case DrawerUtils.DASHBOARD_TAB:
            case DrawerUtils.SETTINGS_TAB:
            default:
                drawerRecyclerView.setAdapter(new DrawerAdapter(DrawerUtils.getDataSet(), this.drawerCurrentlySelectedTab, this));
        }
        FragmentSwitcher.addFragmentToActivity(getSupportFragmentManager(), targetFragment, R.id.activity_main_placeholder);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            FirebaseManager.getInstance().getFirebaseAuth().signOut();
            loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
