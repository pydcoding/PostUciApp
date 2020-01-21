package com.pyd.postuciapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.pyd.postuciapp.R;
import com.pyd.postuciapp.activity.TestIntroductionActivity;
import com.pyd.postuciapp.activity.TestResultsActivity;
import com.pyd.postuciapp.bean.Test;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    public enum Type {
        PATIENT_PENDING,
        MEDIC_DONE
    }

    private Context mContext;

    private List<Test.TestType> mTests;

    private Type mType;

    public TestAdapter(Type type, Context context, List<Test.TestType> tests) {
        this.mContext = context;
        this.mTests = tests;
        this.mType = type;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_row, parent, false);

        return new TestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.bind(mTests.get(position));
    }

    @Override
    public int getItemCount() {
        return mTests.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView mTestNameTextView;
        private AppCompatButton mDoButton;

        TestViewHolder(@NonNull View itemView) {
            super(itemView);

            mTestNameTextView = itemView.findViewById(R.id.test_name);
            mDoButton = itemView.findViewById(R.id.test_do);
        }

        private void bind(final Test.TestType testType) {
            mTestNameTextView.setText(Test.getNameByType(testType));

            if (mType == Type.PATIENT_PENDING) {
                mDoButton.setText(mContext.getResources().getString(R.string.do_test));
            } else {
                mDoButton.setText(mContext.getResources().getString(R.string.view_results));
            }

            mDoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mType == Type.PATIENT_PENDING) {
                        Intent intent = new Intent(mContext, TestIntroductionActivity.class);
                        intent.putExtra("type", testType);

                        mContext.startActivity(intent);

                    } else {
                        Intent intent = new Intent(mContext, TestResultsActivity.class);

                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
