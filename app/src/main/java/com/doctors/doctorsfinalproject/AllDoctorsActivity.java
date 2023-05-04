package com.doctors.doctorsfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.doctors.doctorsfinalproject.databinding.ActivityAllDoctorsBinding;
import com.doctors.doctorsfinalproject.databinding.ActivityDoctorsCategoryBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllDoctorsActivity extends AppCompatActivity {
    ActivityAllDoctorsBinding binding;
    AllDoctorsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllDoctorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        getAdapter();


        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchByName(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchByName(newText);
                return false;
            }
        });
    }

    private void getData() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collectionRef = db.collection("AllDoctors");

        collectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<AllDoctorsModel> myDataList = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AllDoctorsModel myData = document.toObject(AllDoctorsModel.class);
                        myDataList.add(myData);
                    }

                    adapter = new AllDoctorsAdapter(getBaseContext(),myDataList);

                    binding.recyclerview.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("TAG", "Error getting documents: ", task.getException());
                }

            }
        });


    }

    private void getAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        binding.recyclerview.setLayoutManager(layoutManager);


    }

    private void searchByName(String query) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference usersRef = db.collection("AllDoctors");
        Query nameQuery = usersRef.whereGreaterThanOrEqualTo("name", query)
                .whereLessThanOrEqualTo("name", query + "\uf8ff");

        nameQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<AllDoctorsModel> userList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AllDoctorsModel user = document.toObject(AllDoctorsModel.class);
                        userList.add(user);
                    }

                    adapter = new AllDoctorsAdapter(getBaseContext(),userList);
                    binding.recyclerview.setAdapter(adapter);
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}