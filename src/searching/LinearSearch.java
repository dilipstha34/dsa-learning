package searching;

public class LinearSearch {

    public static Student search(Student[] students, int rollNo) {

        // Start from the first element
        for (int i = 0; i < students.length; i++) {

            // Check if Roll No matches
            if (students[i].getRollNo() == rollNo) {
                return students[i];
            }
        }

        // Return null if not found
        return null;
    }
}