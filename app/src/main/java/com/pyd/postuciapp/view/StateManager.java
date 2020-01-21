package com.pyd.postuciapp.view;

class StateManager {

    private boolean mIsEnabled;
    private int mProgress;

    StateManager(CircularProgressButton progressButton) {
        mIsEnabled = progressButton.isEnabled();
        mProgress = progressButton.getProgress();
    }

    void saveProgress(CircularProgressButton progressButton) {
        mProgress = progressButton.getProgress();
    }

    private boolean isEnabled() {
        return mIsEnabled;
    }

    private int getProgress() {
        return mProgress;
    }

    void checkState(CircularProgressButton progressButton) {
        if (progressButton.getProgress() != getProgress()) {
            progressButton.setProgress(progressButton.getProgress());
        } else if(progressButton.isEnabled() != isEnabled()) {
            progressButton.setEnabled(progressButton.isEnabled());
        }
    }
}
