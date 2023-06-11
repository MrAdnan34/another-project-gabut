// CASE: Traveling Salesman Problem using Branch And Bound
// Link: https://www.geeksforgeeks.org/traveling-salesman-problem-using-branch-and-bound-2/

// Java program to solve Traveling Salesman Problem using Branch and Bound.

import java.util.Arrays;

public class TravelingSalesman {

    // Total kota yang akan dikunjungi
    private static int N = 4;

    // final_path[] menyimpan solusi akhir yaitu jalur salesman.
    private static int final_path[] = new int[N + 1];

    // visited[] melacak kota yang telah dikunjungi dalam suatu jalur tertentu
    private static boolean visited[] = new boolean[N];

    // Menyimpan biaya minimum dari jalur terpendek yang ditemukan
    private static int final_res = Integer.MAX_VALUE;

    // Fungsi untuk menyalin solusi sementara ke solusi akhir
    private static void copyToFinal(int curr_path[]) {
        for (int i = 0; i < N; i++) {
            final_path[i] = curr_path[i];
        }
        final_path[N] = curr_path[0];
    }

    // Fungsi untuk mencari sisi minimum pertama yang berakhir pada vertex i
    private static int firstMin(int adj[][], int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++) {
            if (adj[i][k] < min && i != k) {
                min = adj[i][k];
            }
        }
        return min;
    }

    // Fungsi untuk mencari sisi minimum kedua yang berakhir pada vertex i
    private static int secondMin(int adj[][], int i) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j)
                continue;

            if (adj[i][j] <= first) {
                second = first;
                first = adj[i][j];
            } else if (adj[i][j] <= second && adj[i][j] != first) {
                second = adj[i][j];
            }
        }
        return second;
    }

    // Fungsi yang mengambil argumen:
    // curr_bound -> batasan bawah dari simpul root
    // curr_weight -> menyimpan bobot jalur yang telah dilalui
    // level -> level saat ini dalam pergerakan di pohon ruang pencarian
    // curr_path[] -> tempat penyimpanan solusi yang akan di-copy ke final_path[]
    private static void TSPRec(int adj[][], int curr_bound, int curr_weight, int level, int curr_path[]) {
        // Kasus dasar ketika mencapai level N yang berarti semua kota telah dikunjungi sekali
        if (level == N) {
            // Periksa apakah terdapat sisi dari kota terakhir ke kota awal
            if (adj[curr_path[level - 1]][curr_path[0]] != 0) {
                // curr_res menyimpan bobot total dari solusi yang ditemukan
                int curr_res = curr_weight + adj[curr_path[level - 1]][curr_path[0]];

                // Perbarui hasil akhir dan jalur akhir jika curr_res lebih baik
                if (curr_res < final_res) {
                    copyToFinal(curr_path);
                    final_res = curr_res;
                }
            }
            return;
        }

        // Untuk level lain, lakukan iterasi untuk semua vertex untuk
        // membangun pohon ruang pencarian secara rekursif
        for (int i = 0; i < N; i++) {
            // Jika vertex i belum dikunjungi dan ada sisi dari vertex
            // sebelumnya ke i dalam graf
            if (!visited[i] && adj[curr_path[level - 1]][i] != 0) {
                int temp = curr_bound;
                curr_weight += adj[curr_path[level - 1]][i];

                // Batasan bawah (lower bound) saat node level+1 dikunjungi
                if (level == 1) {
                    curr_bound -= ((firstMin(adj, curr_path[level - 1]) + firstMin(adj, i)) / 2);
                } else {
                    curr_bound -= ((secondMin(adj, curr_path[level - 1]) + firstMin(adj, i)) / 2);
                }

                // Jika batasan bawah (lower bound) plus bobot saat ini lebih kecil dari
                // final_res, lakukan rekursif untuk node berikutnya
                if (curr_bound + curr_weight < final_res) {
                    curr_path[level] = i;
                    visited[i] = true;
                    // Memanggil fungsi rekursif untuk level berikutnya
                    TSPRec(adj, curr_bound, curr_weight, level + 1, curr_path);
                }

                // Mengembalikan bobot saat ini dan kunjungan vertex menjadi aslinya
                curr_weight -= adj[curr_path[level - 1]][i];
                curr_bound = temp;

                // Meng-set/mengatur visited[i] menjadi false saat melakukan backtracking
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++) {
                    visited[curr_path[j]] = true;
                }
            }
        }
    }

    // Fungsi yang memanggil TSPRec dan mencetak solusi akhir
    private static void TSP(int adj[][]) {
        int curr_path[] = new int[N + 1];

        // Hasil awal dari batasan bawah adalah tak terhingga
        int curr_bound = 0;

        // Menginisialisasi array visited[] sebagai false
        Arrays.fill(visited, false);

        // Menghitung batasan bawah awal untuk node root
        // Menggunakan rata-rata sisi minimum pertama dan kedua dari node root
        for (int i = 0; i < N; i++) {
            curr_bound += (firstMin(adj, i) + secondMin(adj, i));
        }

        // Membulatkan nilai batasan bawah (lower bound) ke atas ke bilangan genap
        // terdekat
        curr_bound = (curr_bound == 1) ? curr_bound / 2 + 1 : curr_bound / 2;

        // Memulai dari vertex 1 sehingga membutuhkan indeks 0 dalam path
        visited[0] = true;
        curr_path[0] = 0;

        // Memanggil fungsi rekursif TSPRec untuk perhitungan batasan bawah dan mencari
        // solusi TSP
        TSPRec(adj, curr_bound, 0, 1, curr_path);
    }

    // Fungsi utama untuk menjalankan program
    public static void main(String[] args) {
        // adjacency matrix untuk graf TSP
        int adj[][] = { { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 } };

        TSP(adj);

        System.out.printf("Minimum cost : %d\n", final_res);
        System.out.printf("Path Taken : ");
        for (int i = 0; i <= N; i++) {
            System.out.printf("%d ", final_path[i]);
        }
    }
}

/* This code contributed by PrinciRaj1992 */
