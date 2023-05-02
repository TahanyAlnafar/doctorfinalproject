package com.doctors.doctorsfinalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doctors.doctorsfinalproject.databinding.CategoryItemBinding;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DoctorsCategoryAdapter extends RecyclerView.Adapter<DoctorsCategoryAdapter.viewHolder> {

    Context context;
    private List<DoctorsCategoryModel> list;

    public DoctorsCategoryAdapter(Context context, List<DoctorsCategoryModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public DoctorsCategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull DoctorsCategoryAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.doctorName.setText(list.get(position).getName());
         holder.binding.doctorMajor.setText(list.get(position).getMajor());
        holder.binding.open.setText(list.get(position).getIsOpen());

        if (list.get(position).getIsOpen().equals("مغلق"))
            holder.binding.open.setTextColor(context.getColor(R.color.red));
         else
            holder.binding.open.setTextColor(context.getColor(R.color.blue));


        if (context != null) {
            Glide.with(context).load(list.get(position).getImage()).placeholder(R.drawable.ic_launcher_background).into((holder).binding.imageDoctor);
        }



    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding binding;

        public viewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}

