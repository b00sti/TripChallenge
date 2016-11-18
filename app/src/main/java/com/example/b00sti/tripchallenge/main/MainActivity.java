package com.example.b00sti.tripchallenge.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.ui_login.LogInActivity_;
import com.example.skeleton.android_utils.firebase.FirebaseManager;
import com.example.skeleton.android_utils.navigation.drawer.DrawerUtils;
import com.example.skeleton.ui.activity_utils.ActivityUtils;
import com.example.skeleton.ui.activity_utils.BaseMainActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseMainActivity<DrawerItem, DrawerItemView, DrawerAdapter> {
    private static final String TAG = "MainActivity";
    //@ViewById(R.id.listView) public ListView listView;
    //@ViewById(R.id.todoText) public EditText text;
    //@ViewById public Button addButton;
    @Bean
    FragmentBuilder fragmentBuilder;
    @Bean
    FirebaseManager firebaseManager;

    @Bean
    DrawerAdapter drawerAdapter;

    @AfterViews
    void test() {
        setBottomDrawerFragment(TopFrag.newInstance());
        setTopDrawerFragment(TopFrag.newInstance());
        /*if (!firebaseManager.isUserLogged()) {
            loadLogInView();
        } else {

            // Set up ListView
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
            listView.setAdapter(adapter);

            Attraction venece = new Attraction("1", "Pl.św. Marka", false);
            Attraction lisboa = new Attraction("2", "Costa del sol", true);

            DatabaseReference mDatabase = firebaseManager.getMainDatabaseRef();
            String mUserId = firebaseManager.getUserId();

            addButton.setOnClickListener(v -> {
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
        if (!firebaseManager.isUserLogged()) {
            loadLogInView();
        }*/
    }

    @Override
    public int getHomeAsUpIndicatorAsDrawable() {
        return R.drawable.common_plus_signin_btn_icon_dark_pressed;
    }

    @Nullable
    @Override
    public Fragment getFragmentForTab(@DrawerUtils.DrawerTab int tab) {
        Fragment fragment = null;
        switch (tab) {
            case DrawerUtils.TAB_0:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                break;
            case DrawerUtils.TAB_1:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                break;
            case DrawerUtils.TAB_2:
                fragment = fragmentBuilder.newFragment(FragmentBuilder.DASHBOARD);
                break;
            case DrawerUtils.TAB_3:
                break;
            case DrawerUtils.TAB_4:
                break;
            case DrawerUtils.TAB_5:
                break;
            case DrawerUtils.TAB_6:
                break;
            case DrawerUtils.TAB_7:
                break;
            case DrawerUtils.TAB_8:
                break;
            case DrawerUtils.TAB_9:
                break;
            case DrawerUtils.TAB_NO:
                break;
        }
        return fragment;
    }

    @Override
    public List<DrawerItem> getDrawerItems() {
        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_0, R.string.signup_error_title, R.drawable.common_google_signin_btn_icon_dark_focused));
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_1, R.string.signup_error_message, R.drawable.common_full_open_on_phone));
        drawerItems.add(new DrawerItem(DrawerUtils.TAB_2, R.string.login_error_title, R.drawable.common_google_signin_btn_text_dark_disabled));
        return drawerItems;
    }

    @Override
    public DrawerAdapter getDrawerAdapter() {
        return drawerAdapter;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            firebaseManager.getFirebaseAuth().signOut();
            ActivityUtils.startInnerViewActivity(this, FragmentBuilder.DASHBOARD);
            //loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
