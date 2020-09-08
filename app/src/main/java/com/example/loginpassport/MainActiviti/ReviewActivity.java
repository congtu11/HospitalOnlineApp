package com.example.loginpassport.MainActiviti;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.loginpassport.AdapterRecyclerView.ReviewAdapter;
import com.example.loginpassport.R;
import com.example.loginpassport.model.Review_model;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
      setContentView(R.layout.review);
      recyclerView = findViewById(R.id.review_doctor);
      LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setHasFixedSize(true);
      DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
      recyclerView.addItemDecoration(dividerItemDecoration);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
      ArrayList<Review_model> review = new ArrayList<>();
      review.add(new Review_model(R.drawable.anhbacsi,"hello"));
      review.add(new Review_model(R.drawable.anhbacsi,"hello"));
      review.add(new Review_model(R.drawable.anhbacsi,"hello"));
      review.add(new Review_model(R.drawable.anhbacsi,"hello"));
      recyclerView.setAdapter(new ReviewAdapter(review,this));

  }

}
