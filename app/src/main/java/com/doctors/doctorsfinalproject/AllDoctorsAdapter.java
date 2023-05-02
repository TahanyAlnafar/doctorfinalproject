package com.doctors.doctorsfinalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doctors.doctorsfinalproject.databinding.CategoryItemBinding;
import com.doctors.doctorsfinalproject.databinding.DoctorsItemBinding;

import java.util.List;

public class AllDoctorsAdapter extends RecyclerView.Adapter<AllDoctorsAdapter.viewHolder> {

    Context context;
    private List<AllDoctorsModel> list;

    public AllDoctorsAdapter(Context context, List<AllDoctorsModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AllDoctorsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DoctorsItemBinding binding = DoctorsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new viewHolder(binding);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull AllDoctorsAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.doctorName.setText(list.get(position).getName());
         holder.binding.doctorMajor.setText(list.get(position).getMajor());
        holder.binding.rate.setText(list.get(position).getRate());



        if (context != null) {
            Glide.with(context).load(list.get(position).getImage()).placeholder(R.drawable.ic_launcher_background).into((holder).binding.imageDoctor);
        }



    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        DoctorsItemBinding binding;

        public viewHolder(DoctorsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }


}

