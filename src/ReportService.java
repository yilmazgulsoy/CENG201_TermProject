public class ReportService {

    // Gonderilen odev listesini boyutuna gore siraladım
    // kolay bulaşım diye buble sort kullandim burd
    public void bubbleSortBySize(Submission[] array, int length) {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                // Buyukten kucuge siralama icin karsilastirma
                if (array[j].getSizeKb() < array[j + 1].getSizeKb()) {
                    Submission temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // binaryserch mantigiyla id arama
    // Calismasi icin dizinin id gore sirali olmasi gerek
    public Submission findStudentBinary(Submission[] sortedArray, int length, String targetId) {
        int left = 0;
        int right = length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = sortedArray[mid].getStudentId().compareTo(targetId);

            if (compareResult == 0) {
                return sortedArray[mid];
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
// wp6 test