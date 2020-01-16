package com.pyd.postuciapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.bean.Message;
import com.pyd.postuciapp.bean.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private static final String PLACEHOLDER = "%1";

    private Context mContext;

    private List<Patient> mPatients;
    private Map<String, List<Message>> mMessages;

    public PatientAdapter(Context context, List<Patient> patients, Map<String, List<Message>> messages) {
        this.mContext = context;
        this.mPatients = patients;
        this.mMessages = messages;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_row, parent, false);

        return new PatientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        holder.bind(mContext, mPatients.get(position),
                (mMessages.containsKey(mPatients.get(position).getDni()) ? mMessages.get(mPatients.get(position).getDni()) : new ArrayList<Message>()));
    }

    @Override
    public int getItemCount() {
        return mPatients.size();
    }

    class PatientViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView mNameTextView;
        private AppCompatTextView mDniTextView;
        private AppCompatTextView mPendingTestsTextView;
        private AppCompatTextView mDoneTestsTextView;
        private AppCompatTextView mGradeTextView;
        private AppCompatTextView mMessagesTextView;

        private AppCompatButton mViewButton;

        PatientViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTextView = itemView.findViewById(R.id.patient_name);
            mDniTextView = itemView.findViewById(R.id.patient_dni);
            mPendingTestsTextView = itemView.findViewById(R.id.patient_pending_tests);
            mDoneTestsTextView = itemView.findViewById(R.id.patient_done_tests);
            mGradeTextView = itemView.findViewById(R.id.patient_grade);
            mMessagesTextView = itemView.findViewById(R.id.patient_messages);

            mViewButton = itemView.findViewById(R.id.view_patient);
            mViewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        void bind(Context context, Patient patient, List<Message> messages) {
            mNameTextView.setText(patient.getName());
            mDniTextView.setText(patient.getDni());

            mPendingTestsTextView.setText(
                    context.getResources().getString(R.string.patient_num_pending_tests_template)
                            .replaceAll(PLACEHOLDER, Integer.toString(patient.getPendingTests().size())));

            mDoneTestsTextView.setText(
                    context.getResources().getString(R.string.patient_num_done_tests_template)
                            .replaceAll(PLACEHOLDER, Integer.toString(patient.getDoneTests().size())));

            mGradeTextView.setText(
                    context.getResources().getString(R.string.patient_grade_template)
                            .replaceAll(PLACEHOLDER, Integer.toString(patient.calculateSeverity())));

            mMessagesTextView.setText(
                    context.getResources().getString(R.string.patient_messages_template)
                            .replaceAll(PLACEHOLDER, Integer.toString(messages.size())));
        }
    }
}
