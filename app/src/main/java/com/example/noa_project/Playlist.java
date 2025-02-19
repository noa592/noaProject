package com.example.noa_project;

import java.util.ArrayList;

public class Playlist {

  private   ArrayList<String> ps;

    public Playlist() {
        this.ps = ps;
    }

    public ArrayList<String> getPs() {
        return ps;
    }

    public void setPs(ArrayList<String> ps) {
        this.ps = ps;
    }

    public void add(String song)
    {
        ps.add(song);
    }
}
