package com.example.b00sti.tripchallenge.ui_trip_details;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.main.MainActivity;
import com.example.b00sti.tripchallenge.utils.helpers.GooglePlacesManager;
import com.example.skeleton.ui.mvp_base.MvpFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dominik (b00sti) Pawlik on 2016-11-10
 */

@EFragment(R.layout.fragment_trip_details_top)
public class TripDetailsTopFragment extends MvpFragment<TripDetailsTopContract.Presenter> implements TripDetailsTopContract.View {
    private static final String TAG = "TripDetailsTopFragment";

    @Bean
    TripDetailsTopPresenter tripDetailsTopPresenter;

    @Bean
    GooglePlacesManager googlePlacesManager;

    @ViewById(R.id.main_tripIV)
    ImageView main_tripIV;

    @ViewById(R.id.mainPlaceTV)
    TextView mainPlaceTV;

    public static Fragment newInstance() {
        return new com.example.b00sti.tripchallenge.ui_trip_details.TripDetailsTopFragment_();
    }

    @Click(R.id.connectB)
    void connect() {
        presenter.connect();
        showPlacePicker();
    }

    @AfterViews
    void init() {
        ctx = getActivity();

    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, ctx);
                String toastMsg = String.format("Place: %s", place.getName());
                //mainPlaceTV.setText(place.getName());
                place.getRating();
                Toast.makeText(ctx, toastMsg, Toast.LENGTH_LONG).show();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setCollapsedTitleL(place.getName().toString());
                }
                googlePlacesManager.getPhotoObservable(place)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(bitmap -> {
                            main_tripIV.setImageBitmap(bitmap);
                        });
            }
        }
    }

    @Override
    protected TripDetailsTopContract.Presenter setPresenterView() {
        tripDetailsTopPresenter.setView(this);
        return tripDetailsTopPresenter;
    }

    private void showPlacePicker() {
        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(ctx), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
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
