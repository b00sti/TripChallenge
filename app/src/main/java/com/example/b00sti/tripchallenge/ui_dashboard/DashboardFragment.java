package com.example.b00sti.tripchallenge.ui_dashboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.ui.mvp_base.MvpFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;

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

    private GoogleApiClient mGoogleApiClient;

    public static Fragment newInstance() {
        return new DashboardFragment_();
    }

    @Click(R.id.connectB)
    void connect() {
        presenter.connect();
        showProgressBar();
    }

    @AfterViews
    void init() {
        ctx = getActivity();
        mGoogleApiClient = new GoogleApiClient
                .Builder(ctx)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                //.enableAutoManage(ctx, this)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void showProgressBar() {
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Log.d(TAG, "showProgressBar: ng");
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
        } else {
            Log.d(TAG, "showProgressBar: gr");
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);

            result.setResultCallback(likelyPlaces -> {
                Log.d(TAG, "showProgressBar: size " + likelyPlaces.getCount());
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getLikelihood()));
                }
                likelyPlaces.release();
            });
        }


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
