package searching;

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch {

    public static Student search(Student[] students, String studentName) {

        // Sort students by name
        Arrays.sort(
                students,
                Comparator.comparing(
                        Student::getStudentName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        int low = 0;
        int high = students.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int comparison =
                    students[mid].getStudentName()
                            .compareToIgnoreCase(studentName);

            if (comparison == 0) {
                return students[mid];
            }
            else if (comparison < 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        return null;
    }
}