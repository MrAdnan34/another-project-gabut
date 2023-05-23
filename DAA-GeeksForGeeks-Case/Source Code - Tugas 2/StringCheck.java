// import java.util.*;

public class StringCheck {
    // Fungsi untuk menjumlahkan dua bilangan dalam bentuk String
    public static String string_sum(String str1, String str2) {
        if (str1.length() < str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }
        int m = str1.length();
        int n = str2.length();
        String ans = "";

        int carry = 0;
        for (int i = 0; i < n; i++) {
            // Menjumlahkan digit saat ini dari kedua string
            int ds = ((str1.charAt(m - 1 - i) - '0') + (str2.charAt(n - 1 - i) - '0') + carry) % 10;
            carry = ((str1.charAt(m - 1 - i) - '0') + (str2.charAt(n - 1 - i) - '0') + carry) / 10;

            ans = Character.toString((char)(ds + '0')) + ans;
        }

        for (int i = n; i < m; i++) {
            // Menjumlahkan digit angka saat ini dengan carry jika ada
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

    // Fungsi untuk memeriksa apakah dua substring dapat menghasilkan jumlah yang benar
    public static boolean checkSumStrUtil(String str, int beg, int len1, int len2) {
        String s1 = str.substring(beg, beg + len1);
        String s2 = str.substring(beg + len1, beg + len1 + len2);
        String s3 = string_sum(s1, s2);

        int s3_len = s3.length();

        if (s3_len > str.length() - len1 - len2 - beg)
            return false;

        if (s3.equals(str.substring(beg + len1 + len2, beg + len1 + len2 + s3_len))) {
            if (beg + len1 + len2 + s3_len == str.length()) {
                // Jika sudah mencapai akhir string, return true
                return true;
            }

            // Pemanggilan rekursif untuk memeriksa substring berikutnya
            return checkSumStrUtil(str, beg + len1, len2, s3_len);
        }

        return false;
    }

    // Fungsi utama untuk memeriksa apakah string adalah sum-string atau bukan
    public static boolean isSumStr(String str) {
        int n = str.length();

        for (int i = 1; i < n; i++) {
            for (int j = 1; i + j < n; j++) {
                // Memanggil fungsi checkSumStrUtil() untuk setiap kemungkinan substring
                if (checkSumStrUtil(str, 0, i, j))
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        boolean result;

        // Pengujian pertama
        result = isSumStr("1212243660");
        System.out.println(result == true ? "True" : "False");

        // Pengujian kedua
        result = isSumStr("123456787");
        System.out.println(result == true ? "True" : "False");
    }
}
