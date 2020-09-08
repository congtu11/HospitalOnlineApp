package com.example.loginpassport.MainActiviti;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpassport.AdapterRecyclerView.CategoryDoctorListAdapter;
import com.example.loginpassport.R;
import com.example.loginpassport.model.CategoryDoctorList_Model;


import java.util.ArrayList;
import java.util.List;

public class CategoryDoctorList extends AppCompatActivity {

    RecyclerView rvItems;
    List<CategoryDoctorList_Model> item_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_doctor_list);

        rvItems = (RecyclerView) findViewById(R.id.rv_items);
        item_category= new ArrayList<>();
        item_category.add(new CategoryDoctorList_Model("Bác sĩ nội khoa",R.drawable.doctor_mainmenu,1));
        item_category.add(new CategoryDoctorList_Model("Bác sĩ tim mạch",R.drawable.drugs,2));
        item_category.add(new CategoryDoctorList_Model("Bác sĩ da liễu ",R.drawable.sos,3));
        item_category.add(new CategoryDoctorList_Model("Bác sĩ tiểu đường",R.drawable.blood_bank,4));
        item_category.add(new CategoryDoctorList_Model("Bác sĩ nha khoa",R.drawable.question,5));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvItems.setLayoutManager(mLayoutManager);
        rvItems.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(15), true));
        rvItems.setItemAnimator(new DefaultItemAnimator());
        rvItems.setHasFixedSize(true);
        rvItems.setAdapter(new CategoryDoctorListAdapter(this,item_category));

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }

    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
