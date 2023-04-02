package com.android.documed.Class;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.documed.R;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Patient> patientList;

    public RecyclerViewAdapter(List<Patient> patientList){this.patientList=patientList;}

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        String name = patientList.get(position).getTextViewPatientName();
        String status = patientList.get(position).getTextViewPatientStatus();
        String room = patientList.get(position).getTextViewRoomNumber();

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            patientName = itemView.findViewById(R.id.textViewPatientName);
            patientStatus = itemView.findViewById(R.id.textViewPatientStatus);
            patientRoom = itemView.findViewById(R.id.textViewRoomNumber);
        }

        public void setData(String name, String status, String room) {
            patientName.setText(name);
            patientStatus.setText(status);
            patientRoom.setText(room);
        }
    }
}
