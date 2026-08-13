package searching;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class CollegeAppGUI extends JFrame {

    private Student[] students;

    private JTextArea displayArea;
    private JTextField rollNoField;
    private JTextField studentNameField;

    public CollegeAppGUI() {

        setTitle("College Student Search System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
    }

    private void createGUI() {

        setLayout(new BorderLayout(10, 10));

        // ================= TOP PANEL =================

        JPanel topPanel = new JPanel();

        JButton generateButton = new JButton("Generate Student Data");

        topPanel.add(generateButton);

        add(topPanel, BorderLayout.NORTH);

        // ================= DISPLAY AREA =================

        displayArea = new JTextArea();
        displayArea.setFont(new Font("Arial", Font.PLAIN, 15));
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(scrollPane, BorderLayout.CENTER);

        // ================= SEARCH PANEL =================

        JPanel searchPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        JLabel rollNoLabel = new JLabel("Roll No:");

        rollNoField = new JTextField();

        JButton rollSearchButton = new JButton("Linear Search");

        JLabel nameLabel = new JLabel("Student Name:");

        studentNameField = new JTextField();

        JButton nameSearchButton = new JButton("Binary Search");

        searchPanel.add(rollNoLabel);
        searchPanel.add(rollNoField);
        searchPanel.add(rollSearchButton);

        searchPanel.add(nameLabel);
        searchPanel.add(studentNameField);
        searchPanel.add(nameSearchButton);

        add(searchPanel, BorderLayout.SOUTH);

        // ================= BUTTON ACTIONS =================

        generateButton.addActionListener(e -> generateStudents());

        rollSearchButton.addActionListener(e -> searchRollNo());

        nameSearchButton.addActionListener(e -> searchStudentName());
    }

    // =====================================================
    // GENERATE RANDOM STUDENT DATA
    // =====================================================

    private void generateStudents() {

        Random random = new Random();

        String[] names = {
                "Dilip",
                "Mishek",
                "Upakar",
                "Aashish",
                "Ramos",
                "Paurakh",
                "Gaurab",
                "Sudhanshu",
                "Nischal",
                "Suman"
        };

        students = new Student[names.length];

        for (int i = 0; i < students.length; i++) {

            int rollNo = i + 1;

            double tuitionFee =
                    50000 + random.nextInt(100001);

            students[i] =
                    new Student(
                            rollNo,
                            names[i],
                            tuitionFee
                    );
        }

        displayStudents();
    }

    // =====================================================
    // DISPLAY STUDENTS
    // =====================================================

    private void displayStudents() {

        displayArea.setText("");

        displayArea.append(
                "Roll No\tStudent Name\tTuition Fee\n"
        );

        displayArea.append(
                "--------------------------------------------------\n"
        );

        for (Student student : students) {

            displayArea.append(
                    student.getRollNo() + "\t" +
                            student.getStudentName() + "\t\t" +
                            student.getTuitionFee() + "\n"
            );
        }
    }

    // =====================================================
    // LINEAR SEARCH - ROLL NO
    // =====================================================

    private void searchRollNo() {

        if (students == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please click Generate first."
            );

            return;
        }

        try {

            int rollNo =
                    Integer.parseInt(rollNoField.getText());

            Student result =
                    LinearSearch.search(
                            students,
                            rollNo
                    );

            if (result != null) {

                displayArea.append(
                        "\n\nLinear Search Result:\n"
                );

                displayArea.append(
                        "Roll No: " +
                                result.getRollNo() +
                                "\n"
                );

                displayArea.append(
                        "Student Name: " +
                                result.getStudentName() +
                                "\n"
                );

                displayArea.append(
                        "Tuition Fee: " +
                                result.getTuitionFee() +
                                "\n"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Roll No " +
                                rollNo +
                                " not found."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid Roll No."
            );
        }
    }

    // =====================================================
    // BINARY SEARCH - STUDENT NAME
    // =====================================================

    private void searchStudentName() {

        if (students == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please click Generate first."
            );

            return;
        }

        String name =
                studentNameField.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a student name."
            );

            return;
        }

        /*
         * Binary Search requires sorted data.
         * Therefore, sort students alphabetically
         * according to student name.
         */

        Student[] sortedStudents =
                Arrays.copyOf(
                        students,
                        students.length
                );

        Arrays.sort(
                sortedStudents,
                Comparator.comparing(
                        Student::getStudentName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        Student result =
                BinarySearch.search(
                        sortedStudents,
                        name
                );

        if (result != null) {

            displayArea.append(
                    "\n\nBinary Search Result:\n"
            );

            displayArea.append(
                    "Roll No: " +
                            result.getRollNo() +
                            "\n"
            );

            displayArea.append(
                    "Student Name: " +
                            result.getStudentName() +
                            "\n"
            );

            displayArea.append(
                    "Tuition Fee: " +
                            result.getTuitionFee() +
                            "\n"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Student " +
                            name +
                            " not found."
            );
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CollegeAppGUI app =
                    new CollegeAppGUI();

            app.setVisible(true);
        });
    }
}