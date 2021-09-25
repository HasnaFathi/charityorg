package com.example.myapplication;

import android.net.Uri;

public class ProjectImage {

    private Uri uri;
    private int PID;

    public ProjectImage() {
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public ProjectImage(Uri uri, int PID) {
        this.uri = uri;
        this.PID = PID;
    }
}
