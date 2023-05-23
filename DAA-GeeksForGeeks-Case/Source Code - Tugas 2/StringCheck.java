// CASE: Check if a given string is sum-string
// Link: https://www.geeksforgeeks.org/check-given-string-sum-string/

public class StringCheck {
    // Method/Fungsi untuk menjumlahkan dua bilangan dalam bentuk string
    public static String stringSum(String str1, String str2) {
        if (str1.length() < str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }
        int m = str1.length();
        int n = str2.length();
        // Deklarasi variabel yang mana akan menjadi nilai return
        String ans = "";

        // Inisialisasi nilai awal dengan 0
        int carry = 0;
        for (int i = 0; i < n; i++) {
            // Menjumlahkan digit/angka saat ini dari kedua string
            int ds = ((str1.charAt(m - 1 - i) - '0') + (str2.charAt(n - 1 - i) - '0') + carry) % 10;
            carry = ((str1.charAt(m - 1 - i) - '0') + (str2.charAt(n - 1 - i) - '0') + carry) / 10;

            ans = Character.toString((char)(ds + '0')) + ans;
        }

        for (int i = n; i < m; i++) {
            // Menjumlahkan digit saat ini dengan carry jika ada
            int ds = (str1.charAt(m - 1 - i) - '0' + carry) % 10;
            carry = (str1.charAt(m - 1 - i) - '0' + carry) / 10;
            ans = Character.toString((char)(ds + '0')) + ans;
        }

        if (carry != 0) {
            // Jika masih ada carry setelah perulangan, tambahkan ke hasil
            ans = Character.toString((char)(carry + '0')) + ans;
        }

        return ans;
    }

    // Method/Fungsi lagi untuk memeriksa apakah dua substring dapat menghasilkan jumlah yang benar
    public static boolean checkSumStrUtil(String str, int beg, int len1, int len2) {
        String s1 = str.substring(beg, beg + len1);
        String s2 = str.substring(beg + len1, beg + len1 + len2);
        // Memanfaatkan method yang telah dibuat sebelumnya
        String s3 = stringSum(s1, s2);

        // Deklarasi variable yang mana isinya yaitu mendapatkan panjang/ukuran variabel dari hasil method sebelumnya (ada di String s3)
        int s3Len = s3.length();

        // Cek dulu apakah jumlah digit s3 lebih besar dari sisa string yang tersedia
        if (s3Len > str.length() - len1 - len2 - beg) {
            // Berarti dia tidak dianggap benar
            return false;
        }

        // Memeriksa apakah s3 sama dengan substring berikutnya dalam string utama
        if (s3.equals(str.substring(beg + len1 + len2, beg + len1 + len2 + s3Len))) {
            if (beg + len1 + len2 + s3Len == str.length()) {
                // Jika sudah mencapai akhir string, maka langsung return true
                return true;
            }

            // BRUT
            // Pemanggilan rekursif untuk memeriksa substring berikutnya
            return checkSumStrUtil(str, beg + len1, len2, s3Len);
        }

        return false; // Default return tetap dianggap false atau salah
    }

    // Method/Fungsi utama untuk memeriksa apakah string adalah sum-string atau bukan
    public static boolean isSumStr(String str) {
        int n = str.length();

        for (int i = 1; i < n; i++) {
            for (int j = 1; i + j < n; j++) {
                // Memanggil fungsi checkSumStrUtil() untuk setiap kemungkinan substring
                if (checkSumStrUtil(str, 0, i, j))
                    return true;
            }
        }

        return false; // Default
    }

    public static void main(String[] args) {
        boolean result;

        // Contoh pertama
        result = isSumStr("1212243660");
        System.out.println(result == true ? "True" : "False"); // Ternary Operator

        // Contoh pengujian kedua
        result = isSumStr("123456787");
        System.out.println(result == true ? "True" : "False");
    }
}
