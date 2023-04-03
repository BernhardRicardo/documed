package com.android.documed.Class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.documed.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Patient> patientList;

    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewAdapter(List<Patient> patientList, RecyclerViewInterface recyclerViewInterface){
        this.patientList=patientList;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient,parent,false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        String name = patientList.get(position).getName();
        String status = patientList.get(position).getStatus();
        String room = patientList.get(position).getRoomNumber();

        holder.setData(name,status,room);
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView patientName;
        private TextView patientStatus;
        private TextView patientRoom;

        private Patient patient;
        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            patientName = itemView.findViewById(R.id.textViewPatientName);
            patientStatus = itemView.findViewById(R.id.textViewPatientStatus);
            patientRoom = itemView.findViewById(R.id.textViewRoomNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAbsoluteAdapterPosition();
                        ContainerAndGlobal.setClickedPatient(patient);
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }

        public void setData(String name, String status, String room) {
            patientName.setText(name);
            patientStatus.setText(status);
            patientRoom.setText(room);
        }
    }
}
