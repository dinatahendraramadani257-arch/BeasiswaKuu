package com.mycompany.beasiswaku;

public class Beasiswa {

    private String nama;
    private String kategori;
    private String penyelenggara;
    private String deskripsi;
    private String persyaratan;
    private String manfaat;
    private String periode;
    private String kontak;

    public Beasiswa(
            String nama,
            String kategori,
            String penyelenggara,
            String deskripsi,
            String persyaratan,
            String manfaat,
            String periode,
            String kontak) {

        this.nama = nama;
        this.kategori = kategori;
        this.penyelenggara = penyelenggara;
        this.deskripsi = deskripsi;
        this.persyaratan = persyaratan;
        this.manfaat = manfaat;
        this.periode = periode;
        this.kontak = kontak;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getPersyaratan() {
        return persyaratan;
    }

    public String getManfaat() {
        return manfaat;
    }

    public String getPeriode() {
        return periode;
    }

    public String getKontak() {
        return kontak;
    }

    @Override
    public String toString() {
        return nama;
    }
}