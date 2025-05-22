package com.mycompany.gamebatangmodular;

import java.util.Scanner;

public class GameBatangModular {
    static Scanner sc = new Scanner(System.in);

    // ===== Fungsi input jumlah batang awal =====
    public static int inputJumlahBatang() {
        int n;
        while (true) {
            System.out.print("Masukkan jumlah batang awal (10-30): ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= 10 && n <= 30) {
                    return n;
                } else {
                    System.out.println("Jumlah batang harus antara 10 dan 30.");
                }
            } else {
                System.out.println("Input harus berupa angka.");
                sc.next(); // buang input salah
            }
        }
    }

    // ===== Fungsi strategi komputer ambil batang =====
    public static int komputerAmbil(int batang) {
        int sisa = batang % 4;
        if (sisa == 0) return 3;
        else if (sisa == 3) return 2;
        else if (sisa == 2) return 1;
        else return 1; // sisa 1, posisi kalah
    }

    // ===== Fungsi giliran komputer =====
    public static int giliranKomputer(int batang) {
        int ambil = komputerAmbil(batang);
        System.out.println("Komputer mengambil " + ambil + " batang.");
        return ambil;
    }

    // ===== Fungsi giliran player =====
    public static int giliranPlayer(int batang) {
        int ambil;
        while (true) {
            System.out.print("Giliran Player. Ambil batang (1-3): ");
            if (sc.hasNextInt()) {
                ambil = sc.nextInt();
                if (ambil < 1 || ambil > 3) {
                    System.out.println("Input tidak valid. Harus antara 1 sampai 3.");
                } else if (ambil > batang) {
                    System.out.println("Input tidak valid. Tidak boleh mengambil lebih dari sisa batang (" + batang + ").");
                } else {
                    return ambil;
                }
            } else {
                System.out.println("Input harus berupa angka.");
                sc.next(); // buang input salah
            }
        }
    }

    // ===== Fungsi utama (main) =====
    public static void main(String[] args) {
        System.out.println("=== Game Batang 10-30 (Ambil 1-3 batang) ===");

        int batang = inputJumlahBatang();

        String giliran;
        if (batang % 4 == 1) {
            giliran = "player";
            System.out.println("Jumlah batang awal mod 4 = 1, Player jalan dulu.");
        } else {
            giliran = "komputer";
            System.out.println("Komputer jalan dulu.");
        }

        while (batang > 0) {
            System.out.println("\nSisa batang: " + batang);

            if (giliran.equals("komputer")) {
                int ambil = giliranKomputer(batang);
                batang -= ambil;
                if (batang == 0) {
                    System.out.println("Komputer mengambil batang terakhir. Komputer KALAH!");
                    break;
                }
                giliran = "player";
            } else {
                int ambil = giliranPlayer(batang);
                batang -= ambil;
                if (batang == 0) {
                    System.out.println("Player mengambil batang terakhir. Player KALAH!");
                    break;
                }
                giliran = "komputer";
            }
        }

        sc.close();
    }
}
