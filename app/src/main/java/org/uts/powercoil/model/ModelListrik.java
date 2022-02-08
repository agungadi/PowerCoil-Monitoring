package org.uts.powercoil.model;


import java.io.Serializable;


public class ModelListrik implements Serializable {

    private String txtId,txtNama,txtKapasitas,txtTerisi, txtArus, Gambar,Radiasi,txtDeskripsi,txtJumlah, Koordinat;

    public String getTxtId() {
        return txtId;
    }

    public void setTxtId(String txtNama) {
        this.txtId = txtNama;
    }

    public String getTxtNama() {
        return txtNama;
    }

    public void setTxtNama(String txtNama) {
        this.txtNama = txtNama;
    }

    public String getTxtKapasitas() {
        return txtKapasitas;
    }

    public void  setTxtKapasitas(String txtKapasitas) {
        this.txtKapasitas = txtKapasitas;
    }

    public String getTxtTerisi() {
        return txtTerisi;
    }

    public void setTxtTerisi(String txtTerisi) {
        this.txtTerisi = txtTerisi;
    }

    public String getTxtArus() {
        return txtArus;
    }

    public void setTxtArus(String txtArus) {
        this.txtArus = txtArus;
    }
    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        this.Gambar = gambar;
    }

    public String getTxtRadiasi() {
        return Radiasi;
    }

    public void setTxtRadiasi(String Radiasi) {
        this.Radiasi = Radiasi;
    }

    public String getTxtDeskripsi() {
        return txtDeskripsi;
    }

    public void setTxtDeskripsi(String txtDeskripsi) {
        this.txtDeskripsi = txtDeskripsi;
    }

    public String getTxtJumlah() {
        return txtJumlah;
    }

    public void setTxtJumlah(String txtJumlah) {
        this.txtJumlah = txtJumlah;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }



}
