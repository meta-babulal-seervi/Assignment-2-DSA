

import java.util.*;

/**
 * Represents a student applying for a college program.
 */
class Student {
    private String name;
    private int rank;
    private List<String> preferences;

    /**
     * Constructor to initialize student details.
     * @param name Name of the student.
     * @param rank Rank of the student (lower rank means higher priority).
     * @param preferences List of preferred programs in order.
     */
    public Student(String name, int rank, List<String> preferences) {
        this.name = name;
        this.rank = rank;
        this.preferences = preferences;
    }

    public List<String> getPreferences() {
        return preferences;
    }
    public String getName() {
        return name;
    }
    public int getRank() {
        return rank;
    }
}

/**
 * Represents a college program with a name and capacity.
 */
class Program {
    private String name;
    private int capacity;

    /**
     * Constructor to initialize program details.
     * @param name Name of the program.
     * @param capacity Maximum number of students that can be enrolled.
     */
    public Program(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }

    /**
     * Decreases the program capacity when a student is allocated.
     */
    public void decreaseCapacity() {
        if (capacity > 0) {
            capacity--;
        }
    }
}

/**
 * Handles the allocation of students to programs based on their preferences and available capacities.
 */
public class CollegeCounseling {
    /**
     * Processes the student queue and allocates them to programs based on preference and availability.
     * @param students Queue of students sorted by rank.
     * @param programs List of available programs with their capacities.
     */
    public static void programAllocate(Queue<Student> students, List<Program> programs) {
        while (!students.isEmpty()) {
            boolean allocated = false;
            Student student = students.remove(); // Process student with the highest priority

            // Check each preferred program
            for (String preferredProgram : student.getPreferences()) {
                for (Program program : programs) {
                    if (program.getName().equals(preferredProgram) && program.getCapacity() > 0) {
                        allocated = true;
                        program.decreaseCapacity();
                        System.out.println(student.getName() + " is allocated to the " + program.getName() + " program.");
                        break;
                    }
                }
                if (allocated) {
                    break;
                }
            }

            // If no program could be allocated
            if (!allocated) {
                System.out.println(student.getName() + " is not allocated any program due to unavailability.");
            }
        }
    }

    public static void main(String[] args) {
        Queue<Student> studentQueue = new Queue<Student>(10);

        // Creating students with their ranked preferences (5 choices each)
        studentQueue.add(new Student("Ram", 1, Arrays.asList("CSE", "Civil", "EE", "ECE", "ME")));
        studentQueue.add(new Student("Sita", 2, Arrays.asList("Civil", "CSE", "EE", "ECE", "ME")));
        studentQueue.add(new Student("Shyam", 3, Arrays.asList("CSE", "Civil", "EE", "ECE", "ME")));
        studentQueue.add(new Student("Gita", 4, Arrays.asList("CSE", "ECE", "ME", "Civil", "EE")));
        studentQueue.add(new Student("Mohan", 5, Arrays.asList("EE", "CSE", "ME", "Civil", "ECE")));

        // Creating available programs with capacities
        List<Program> programList = new ArrayList<>();
        programList.add(new Program("CSE", 2));
        programList.add(new Program("Civil", 2));
        programList.add(new Program("EE", 2));
        programList.add(new Program("ECE", 1));
        programList.add(new Program("ME", 1));

        // Allocating students to programs
        programAllocate(studentQueue, programList);
    }
}

