// CASE 1: Policemen Catch Thieves
// Link: https://www.geeksforgeeks.org/policemen-catch-thieves/

// Java program to find maximum number of thieves caught
// import java.io.*;
import java.util.ArrayList;

public class PoliceCatchThieves {
    // Returns maximum number of thieves that can be caught.
    // -> Mengembalikan jumlah berapa banyak pencuri yang dapat tertangkap
    static int policeThief(char arr[], int n, int k) {
        // -> res merupakan variabel yang akan di-return. Bisa dianggap saja res = response
        int res = 0;
        ArrayList<Integer> thi = new ArrayList<Integer>();
        ArrayList<Integer> pol = new ArrayList<Integer>();

        // store indices in the ArrayList
        // -> Memasukkan character Police maupun Thief ke masing-masing ArrayList-nya
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'P') {
                pol.add(i);
            } else if (arr[i] == 'T') {
                thi.add(i);
            }
        }

        // -> CEK OUTPUT ArrayList
        // for (Integer integer : pol) {
        //     System.out.println("Output pol: " + integer);
        // }
        // for (Integer integer : thi) {
        //     System.out.println("Output thi: " + integer);
        // }

        // track lowest current indices of
        // thief: thi[l], police: pol[r]
        // -> Karena tidak menggunakan perulangan for, maka diinisialisasi masing-masing key untuk mengakses value (yang menjadi index) tiap-tiap ArrayList (l untuk thi dan r untuk pol)
        // -> Yang mana ke depannya akan terus bertambah di masing-masing kondisi
        int l = 0, r = 0;

        // -> Perulangan while yang terus menerus dilakukan selama tiap index polisi dan index pencuri dalam ArrayList masing-masing belum dieksekusi dalam program.
        while (l < thi.size() && r < pol.size()) {
            // can be caught
            // -> Kondisi jika pencuri berada di dekat polisi dan polisi tersebut bisa menangkapnya karena memenuhi batas melangkah (k). 
            if (Math.abs(thi.get(l) - pol.get(r)) <= k) {
                // -> Maka secara resmi pencuri tertangkap. Jadi variabel res yang nantinya akan di-return bertambah 1
                res++;
                // -> Selain nilai res, l dan r juga ditambah agar dapat diakses ke index selanjutnya.
                l++;
                r++;
                // increment the minimum index
            } else if (thi.get(l) < pol.get(r)) { // -> Jika kondisi "<= k" tidak terpenuhi, maka lanjut cek apakah posisi pencuri lebih kecil dari posisi polisi dalam index yang sama.
                // -> Maka lanjut ke index posisi pencuri selanjutnya di dalam ArrayList thi.
                l++;
            } else { // -> Kondisi terakhir jika semua di atas tidak terpenuhi.
                // -> Maka index posisi polisi selanjutnya yang akan dianalisis.
                r++;
            }
        }
        return res;
    }

    public static void main(String args[]) {
        // long startTime = System.currentTimeMillis();

        int k, n;
        char arr1[] = new char[] { 'P', 'T', 'T', 'P', 'T' };
        k = 2;
        n = arr1.length;
        System.out.println("Maximum thieves caught: " + policeThief(arr1, n, k)); // 2

        char arr2[] = new char[] { 'T', 'T', 'P', 'P', 'T', 'P' };
        k = 2;
        n = arr2.length;
        System.out.println("Maximum thieves caught: " + policeThief(arr2, n, k)); // 3

        char arr3[] = new char[] { 'P', 'T', 'P', 'T', 'T', 'P' };
        k = 3;
        n = arr3.length;
        System.out.println("Maximum thieves caught: " + policeThief(arr3, n, k)); // 3

        // long endTime = System.currentTimeMillis();
        // long executionTime = endTime - startTime;
        // System.out.println("Waktu eksekusi kode program: " + executionTime + " ms");
    }
}

// This code is contributed by Aditya Kumar (adityakumar129)