package com.mycompany.beasiswaku;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

    private VBox daftarBeasiswa;
    private TextField pencarian;
    private ComboBox<String> kategori;

    @Override
    public void start(Stage stage) {

        Label logo = new Label("BeasiswaKu");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        logo.setTextFill(Color.WHITE);

        Label slogan = new Label(
                "Pusat informasi beasiswa untuk mahasiswa"
        );
        slogan.setTextFill(Color.WHITE);
        slogan.setFont(Font.font("Arial", 13));

        VBox logoBox = new VBox(3, logo, slogan);

        Button beranda = new Button("Beranda");
        Button tentang = new Button("Tentang");
        Button pengingat = new Button("Pengingat");

        styleMenu(beranda);
        styleMenu(tentang);
        styleMenu(pengingat);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox header = new HBox(
                25,
                logoBox,
                spacer,
                beranda,
                tentang,
                pengingat
        );

        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(18, 30, 18, 30));
        header.setStyle("-fx-background-color: #12355B;");

        Label judul = new Label("Temukan Beasiswa");
        judul.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        judul.setTextFill(Color.web("#12355B"));

        Label deskripsi = new Label(
                "Cari informasi beasiswa berdasarkan nama dan kategori."
        );

        deskripsi.setFont(Font.font("Arial", 14));
        deskripsi.setTextFill(Color.GRAY);

        pencarian = new TextField();
        pencarian.setPromptText(
                "Cari nama beasiswa atau penyelenggara..."
        );
        pencarian.setPrefHeight(42);

        kategori = new ComboBox<>();
        kategori.getItems().addAll(
                "Semua Kategori",
                "Kampus",
                "Swasta",
                "Organisasi Mahasiswa"
        );
        kategori.setValue("Semua Kategori");
        kategori.setPrefHeight(42);

        Button cari = new Button("Cari");
        cari.setPrefHeight(42);
        cari.setPrefWidth(90);

        cari.setStyle(
                "-fx-background-color: #12355B;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-background-radius: 7;"
        );

        HBox searchBox = new HBox(
                10,
                pencarian,
                kategori,
                cari
        );

        HBox.setHgrow(pencarian, Priority.ALWAYS);

        Label judulDaftar = new Label("Daftar Beasiswa");
        judulDaftar.setFont(
                Font.font("Arial", FontWeight.BOLD, 20)
        );
        judulDaftar.setTextFill(Color.web("#12355B"));

        daftarBeasiswa = new VBox(15);
        tampilkanSemua();

        ScrollPane scroll = new ScrollPane(daftarBeasiswa);
        scroll.setFitToWidth(true);
        scroll.setPadding(new Insets(5));

        VBox content = new VBox(
                15,
                judul,
                deskripsi,
                searchBox,
                judulDaftar,
                scroll
        );

        content.setPadding(
                new Insets(30, 45, 20, 45)
        );

        VBox.setVgrow(scroll, Priority.ALWAYS);

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(content);

        root.setStyle(
                "-fx-background-color: #F7F9FC;"
        );

        cari.setOnAction(e -> {

            if (pencarian.getText().trim().isEmpty()) {

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setTitle("Peringatan");
                alert.setHeaderText("Input Belum Diisi");
                alert.setContentText(
                        "Silakan masukkan nama beasiswa "
                        + "atau nama penyelenggara."
                );

                alert.showAndWait();
                return;
            }

            filterBeasiswa();
        });

        pencarian.setOnAction(e -> {

            if (pencarian.getText().trim().isEmpty()) {

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setTitle("Peringatan");
                alert.setHeaderText("Input Belum Diisi");
                alert.setContentText(
                        "Silakan masukkan nama beasiswa "
                        + "atau nama penyelenggara."
                );

                alert.showAndWait();
                return;
            }

            filterBeasiswa();
        });

        kategori.setOnAction(e -> {

            if (
                    pencarian.getText().trim().isEmpty()
                    && kategori.getValue().equals("Semua Kategori")
            ) {
                tampilkanSemua();
            } else {
                filterBeasiswa();
            }
        });

        beranda.setOnAction(e -> {
            pencarian.clear();
            kategori.setValue("Semua Kategori");
            tampilkanSemua();
        });

        tentang.setOnAction(e -> {

            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alert.setTitle("Tentang BeasiswaKu");
            alert.setHeaderText("BeasiswaKu");

            alert.setContentText(
                    "BeasiswaKu adalah sistem informasi "
                    + "yang menyediakan informasi beasiswa "
                    + "bagi mahasiswa secara terpusat.\n\n"
                    + "Informasi meliputi kategori, "
                    + "penyelenggara, persyaratan, manfaat, "
                    + "periode, dan kontak."
            );

            alert.showAndWait();
        });

        pengingat.setOnAction(e -> {

            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alert.setTitle("Pengingat Deadline");
            alert.setHeaderText("Pengingat Beasiswa");

            alert.setContentText(
                    "Fitur pengingat digunakan untuk "
                    + "membantu mahasiswa mengingat "
                    + "periode pendaftaran beasiswa."
            );

            alert.showAndWait();
        });

        Scene scene = new Scene(root, 1100, 700);

        stage.setTitle("BeasiswaKu");
        stage.setScene(scene);
        stage.show();
    }

    private void styleMenu(Button button) {

        button.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: 14px;"
                + "-fx-cursor: hand;"
        );
    }

    private void tampilkanSemua() {

        daftarBeasiswa.getChildren().clear();

        for (Beasiswa b : DataBeasiswa.getData()) {
            buatCard(b);
        }
    }

    private void filterBeasiswa() {

        String kata = pencarian.getText()
                .toLowerCase()
                .trim();

        String pilihKategori = kategori.getValue();

        daftarBeasiswa.getChildren().clear();

        for (Beasiswa b : DataBeasiswa.getData()) {

            boolean cocokNama = b.getNama()
                    .toLowerCase()
                    .contains(kata);

            boolean cocokPenyelenggara =
                    b.getPenyelenggara()
                            .toLowerCase()
                            .contains(kata);

            boolean cocokKategori =
                    pilihKategori.equals("Semua Kategori")
                    || b.getKategori()
                            .equals(pilihKategori);

            if (
                    (cocokNama || cocokPenyelenggara)
                    && cocokKategori
            ) {
                buatCard(b);
            }
        }

        if (daftarBeasiswa.getChildren().isEmpty()) {

            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alert.setTitle("Hasil Pencarian");
            alert.setHeaderText("Beasiswa Tidak Ditemukan");
            alert.setContentText(
                    "Tidak ada beasiswa yang "
                    + "sesuai dengan pencarian."
            );

            alert.showAndWait();
        }
    }

    private void buatCard(Beasiswa b) {

        Label nama = new Label(b.getNama());

        nama.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        nama.setTextFill(
                Color.web("#12355B")
        );

        Label kategoriLabel = new Label(
                "Kategori: " + b.getKategori()
        );

        Label penyelenggara = new Label(
                "Penyelenggara: "
                + b.getPenyelenggara()
        );

        Label periode = new Label(
                "Periode: " + b.getPeriode()
        );

        Button detail = new Button("Lihat Detail");

        detail.setStyle(
                "-fx-background-color: #12355B;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 6;"
                + "-fx-cursor: hand;"
        );

        detail.setOnAction(
                e -> tampilkanDetail(b)
        );

        VBox card = new VBox(
                8,
                nama,
                kategoriLabel,
                penyelenggara,
                periode,
                detail
        );

        card.setPadding(new Insets(18));

        card.setStyle(
                "-fx-background-color: white;"
                + "-fx-border-color: #D9DEE5;"
                + "-fx-border-radius: 10;"
                + "-fx-background-radius: 10;"
        );

        daftarBeasiswa.getChildren().add(card);
    }

    private void tampilkanDetail(Beasiswa b) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION
        );

        alert.setTitle("Detail Beasiswa");
        alert.setHeaderText(b.getNama());

        alert.setContentText(
                "Kategori: "
                + b.getKategori()
                + "\n\nPenyelenggara: "
                + b.getPenyelenggara()
                + "\n\nDeskripsi: "
                + b.getDeskripsi()
                + "\n\nPersyaratan: "
                + b.getPersyaratan()
                + "\n\nManfaat: "
                + b.getManfaat()
                + "\n\nPeriode: "
                + b.getPeriode()
                + "\n\nKontak: "
                + b.getKontak()
        );

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}