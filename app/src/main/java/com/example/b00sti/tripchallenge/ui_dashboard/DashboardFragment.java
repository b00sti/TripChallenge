package com.example.b00sti.tripchallenge.ui_dashboard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.ui.mvp_base.MvpFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EFragment(R.layout.fragment_dashboard)
public class DashboardFragment extends MvpFragment<DashboardContract.Presenter> implements DashboardContract.View, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "DashboardFragment";

    @Bean
    DashboardPresenter dashboardPresenter;

    public static Fragment newInstance() {
        return new DashboardFragment_();
    }

    @Click(R.id.connectB)
    void connect() {
        presenter.connect();
    }

    @AfterViews
    void init() {
        ctx = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    protected DashboardContract.Presenter setPresenterView() {
        dashboardPresenter.setView(this);
        return dashboardPresenter;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult.getErrorMessage());
    }
}
/*

    private static final String TAG = "DashboardFragment";
    @ViewById(R.id.listView) public ListView listView;
    @ViewById(R.id.todoText) public EditText text;
    @ViewById public Button addButton;
    @Bean
    FirebaseManager firebaseManager;

    public static Fragment newInstance() {
        return new DashboardFragment_();
    }

    @AfterViews
    void test() {
        if (!firebaseManager.isUserLogged()) {
            loadLogInView();
        } else {

            // Set up ListView
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1);
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
        }
    }

    @Override
    protected DashboardContract.Presenter setPresenterView() {
        return new DashboardPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    private void loadLogInView() {
        Intent intent = new Intent(getActivity(), LogInActivity_.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_logout) {
            firebaseManager.getFirebaseAuth().signOut();
            ActivityUtils.startInnerViewActivity(getActivity(), FragmentBuilder.DASHBOARD, "Sign In");
            //loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }
*/
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*//*

}
*/
