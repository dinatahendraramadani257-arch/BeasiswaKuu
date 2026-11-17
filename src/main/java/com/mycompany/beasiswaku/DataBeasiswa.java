package com.mycompany.beasiswaku;

import java.util.ArrayList;
import java.util.List;

public class DataBeasiswa {

    public static List<Beasiswa> getData() {

        List<Beasiswa> data = new ArrayList<>();

        data.add(new Beasiswa(
                "Beasiswa Unggulan",
                "Kampus",
                "Universitas Lampung",
                "Beasiswa untuk mahasiswa berprestasi.",
                "Mahasiswa aktif dan memenuhi persyaratan.",
                "Bantuan biaya pendidikan.",
                "30 November 2026",
                "Bagian Kemahasiswaan UNILA"
        ));

        data.add(new Beasiswa(
                "Beasiswa Bank Indonesia",
                "Swasta",
                "Bank Indonesia",
                "Beasiswa bagi mahasiswa berprestasi.",
                "Mahasiswa aktif dengan IPK minimal 3.00.",
                "Bantuan pendidikan dan pengembangan diri.",
                "15 Desember 2026",
                "Kantor Perwakilan Bank Indonesia"
        ));

        data.add(new Beasiswa(
                "Beasiswa Organisasi Mahasiswa",
                "Organisasi Mahasiswa",
                "Organisasi Mahasiswa UNILA",
                "Beasiswa bagi mahasiswa yang aktif berorganisasi.",
                "Mahasiswa aktif UNILA.",
                "Bantuan pendidikan dan pengembangan organisasi.",
                "10 Januari 2027",
                "Sekretariat Organisasi Mahasiswa"
        ));

        return data;
    }
}