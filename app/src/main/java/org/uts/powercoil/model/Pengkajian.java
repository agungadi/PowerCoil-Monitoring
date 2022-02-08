package org.uts.powercoil.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pengkajian  implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("Nama")
    private String Nama;
    @SerializedName("Alamat")
    private String Alamat;
    @SerializedName("Pelaksana")
    private String Pelaksana;
    @SerializedName("pathToFile")
    private String pathToFile;
    @SerializedName("tanggal")
    private String tanggal;

    public Pengkajian(){}

    public Pengkajian(String id, String Nama, String Alamat, String Pelaksana, String pathToFile, String tanggal) {
        this.id = id;
        this.Nama = Nama;
        this.Alamat = Alamat;
        this.Pelaksana = Pelaksana;
        this.pathToFile = pathToFile;
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getPelaksana() {
        return Pelaksana;
    }

    public void setPelaksana(String Pelaksana) {
        this.Pelaksana = Pelaksana;
    }

    public String getPdf() {
        return pathToFile;
    }

    public void setPdf(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
