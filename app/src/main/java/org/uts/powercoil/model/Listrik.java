package org.uts.powercoil.model;

import com.google.gson.annotations.SerializedName;

public class Listrik {


    @SerializedName("id")
    private String id;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kapasitas")
    private String kapasitas;
    @SerializedName("terisi")
    private String terisi;
    @SerializedName("arus_dc")
    private String arus_dc;
    @SerializedName("gambar_url")
    private String gambar_url;
    @SerializedName("radiasi_matahari")
    private String radiasi_matahari;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("jumlah_panel")
    private String jumlah_panel;
    @SerializedName("kordinat")
    private String kordinat;

    public Listrik(){}

    public Listrik(String id, String nama, String kapasitas, String terisi, String arus_dc, String gambar_url, String radiasi_matahari, String deskripsi, String jumlah_panel, String kordinat) {
        this.id = id;
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.terisi = terisi;
        this.arus_dc = arus_dc;
        this.gambar_url = gambar_url;
        this.radiasi_matahari = radiasi_matahari;
        this.deskripsi = deskripsi;
        this.jumlah_panel = jumlah_panel;
        this.kordinat = kordinat;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(String kapasitas) {
        this.kapasitas = kapasitas;
    }

    public String getTerisi() {
        return terisi;
    }

    public void setTerisi(String terisi) {
        this.terisi = terisi;
    }

    public String getRadiasi_matahari() {
        return radiasi_matahari;
    }

    public void setRadiasi_matahari(String radiasi_matahari) {
        this.radiasi_matahari = radiasi_matahari;
    }

    public String getJumlah_panel() {
        return jumlah_panel;
    }
    public void setJumlah_panel(String jumlah_panel) {
        this.jumlah_panel = jumlah_panel;
    }
}

