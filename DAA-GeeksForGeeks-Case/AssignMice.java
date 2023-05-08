// CASE 4: Assign Mice to Holes

// Java program to find the minimum time to place all mice in all holes.
import java.util.ArrayList;
import java.util.Collections;

public class AssignMice {
	// Returns minimum time required to place mice in holes.
	public int assignHole(ArrayList<Integer> mice, ArrayList<Integer> holes) {

        // -> Jika jumlah tikus dan lobang tidak sama, maka kriteria soal tidak terpenuhi
		if (mice.size() != holes.size()) {
            return -1;
        }

		/* Sort the lists */
        // -> Diurutkan agar memudahkan masing-masing tikus menemukan lobang paling dekat dengan koordinatnya
        // -> Diurutkan dari yang terkecil ke terbesar karena yang menjadi tolak ukur itu koordinat x-nya, bukan indexnya.
		Collections.sort(mice);
		Collections.sort(holes);

        // -> EXAMPLE
        // mice = [4, -4, 2] => mice = [-4, 2, 4]
        // holes = [4, 0, 10] => holes = [0, 4, 10]

        // -> Deklarasikan berapa banyak tikus dan lobang yang ada.
        // -> Bisa juga menggunakan holes.size() karena pasti length atau size-nya SAMA.
		int size = mice.size();

		/* finding max difference between ith mice and hole */
        // -> Deklarasi waktu paling lama dibutuhkan
		int max = 0;

        // -> Perulangan untuk melakukan proses mencari selisih terbesar.
		for (int i = 0; i < size; i++) {
            // System.out.println("max before = " + max);
            // System.out.println("Math.abs(mice.get(i)-holes.get(i)) = " + Math.abs(mice.get(i) - holes.get(i)));
            // -> Kondisi untuk mencari nilai selisih terbesar.
			if (max < Math.abs(mice.get(i) - holes.get(i))) {
                max = Math.abs(mice.get(i) - holes.get(i));
                // System.out.println("Ditemukan nilai max baru, yaitu = " + max);
            }  // else {
            //     System.out.println("Tidak ditemukan nilai max baru");
            // }
            // System.out.println("max after = " + max);
            // System.out.println();
        }

        // -> Dimutlakkan untuk jaga-jaga kalau misalkan output waktu bernilai negatif. Karena nilai waktu tidak boleh negatif
		return Math.abs(max);
	}

	/* Driver Function to test other functions */
	public static void main(String[] args) {
		AssignMice am = new AssignMice();

		ArrayList<Integer> mice = new ArrayList<Integer>();
		mice.add(4);
		mice.add(-4);
		mice.add(2);
        // mice = [4, -4, 2]

		ArrayList<Integer> holes= new ArrayList<Integer>();
		holes.add(4);
		holes.add(0);
		holes.add(10);
        // holes = [4, 0, 10]

		System.out.println("The last mouse gets into "+ "the hole in time: "+ am.assignHole(mice, holes));
	}
}
