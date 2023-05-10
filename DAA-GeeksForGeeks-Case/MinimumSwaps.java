// CASE 2: Minimum Swaps For Bracket Balancing
// Link: https://www.geeksforgeeks.org/minimum-swaps-bracket-balancing/

// Java Program to count swaps required to balance string
public class MinimumSwaps {
	// -> Mengembalikan total berapa kali swap yang dibutuhkan agar semua karakter dalam String bisa seimbang atau simetris
	static int swapCount(String s) {
		char[] chars = s.toCharArray();

		// -> EXAMPLE
		// s = "[][][]";
		// chars = ['[', ']', '[', ']', '[', ']']

		// stores total number of Left and Right brackets encountered
		// -> Variabel untuk menampung kurung buka '[' dan kurung tutup ']'
		int countLeft = 0, countRight = 0;

		// swap stores the number of swaps required
		// -> swap menyimpan berapa kali pertukaran yang akan dibutuhkan
		int swap = 0;

		// imbalance maintains the number of imbalance pair
		// -> imbalance menyimpan berapa jumlah kurung yang tidak seimbang
		int imbalance = 0;

		for(int i = 0; i< chars.length; i++) {
			// -> Untuk sementara, jumlah kurung tidak seimbang dianggap tidak ada (0)

			if (chars[i] == '[') {
				// increment count of Left bracket
				// -> Menambah jumlah kurung kiri
				countLeft++;

				// -> Terdeteksi adanya kurung tidak seimbang
				if (imbalance > 0) {
					// swaps count is last swap count + total number imbalanced brackets
					// -> Setelah memasuki kondisi adanya kurung tidak seimbang, maka jumlah swap (pertukaran posisi) harus ditambah dengan berapa jumlah kurung tidak seimbang.
					swap += imbalance;

					// imbalance decremented by 1 as it solved only one imbalance of Left and Right
					// -> Setelah ditukar, maka jumlah kurung tidak seimbang dikurangi 1
					imbalance--;
				}
			} else if (chars[i] == ']') {
				// increment count of Right bracket
				// -> Menambah jumlah kurung kiri
				countRight++;

				// imbalance is reset to current difference between Left and Right brackets
				// -> imbalance dikurangi kurung kanan dengan kurung kiri KARENA untuk menginisialisasi.
				// -> Alasan kanan dikurang dengan kiri adalah karena ini berada dalam kondisi jika char[i] == ']'. Setelah ini, maka konidisi if(imbalance > 0) di atas bisa berjalan.
				imbalance = (countRight - countLeft);
				// -> Inisialisasi ulang, bukan menambah nilai (+=)
			}
		}
		return swap;
	}

	public static void main(String args[]) {
		String s = "[]][][";
		// "[][]]["
		// "[][][]"
		System.out.println(swapCount(s));

		s = "]][[";
		// "][]["
		// "[]]["
		// "[][]"
		System.out.println(swapCount(s));

		s = "[[][]]";
		System.out.println(swapCount(s));
	}
}
// This code is contributed by Janmejaya Das.
