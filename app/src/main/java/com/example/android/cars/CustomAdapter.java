package com.example.android.cars;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<Vehicles> vehicles_91214;

    public CustomAdapter(Context context, List<Vehicles> vehicles_91214) {
        this.context = context;
        this.vehicles_91214 = vehicles_91214;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card,viewGroup,false);
       return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.vehiclename.setText(vehicles_91214.get(i).getVehiclename());
        viewHolder.vehicleplate.setText(vehicles_91214.get(i).getVehicleplate());
        viewHolder.enginecapacity.setText(vehicles_91214.get(i).getEnginecapacity());
        viewHolder.odometer.setText(vehicles_91214.get(i).getOdometer());
    }

    @Override
    public int getItemCount() {
        return vehicles_91214.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView vehiclename;
        public TextView vehicleplate;
        public TextView enginecapacity;
        public TextView odometer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehiclename=(TextView)itemView.findViewById((R.id.vehiclename));
            vehicleplate=(TextView)itemView.findViewById((R.id.vehicleplate));
            enginecapacity=(TextView)itemView.findViewById((R.id.enginecapacity));
            odometer=(TextView)itemView.findViewById((R.id.odometer));

        }
    }
}
