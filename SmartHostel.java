import java.util.*;

class User {
    protected int id;
    protected String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void displayUser() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }
}

class Student extends User {
    private String department;
    private String roomPreference;
    private int allocatedRoom = -1;

    public Student(int id, String name, String department, String roomPreference) {
        super(id, name);
        this.department = department;
        this.roomPreference = roomPreference;
    }

    public String getRoomPreference() {
        return roomPreference;
    }

    public void setAllocatedRoom(int roomNo) {
        allocatedRoom = roomNo;
    }

    public int getAllocatedRoom() {
        return allocatedRoom;
    }

    public void displayStudent() {
        displayUser();
        System.out.println("Department: " + department);
        System.out.println("Preference: " + roomPreference);

        if (allocatedRoom == -1) {
            System.out.println("Room Not Allocated");
        } else {
            System.out.println("Allocated Room: " + allocatedRoom);
        }
    }
}

class Room {
    private int roomNumber;
    private String type;
    private boolean occupied;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        occupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void allocate() {
        occupied = true;
    }

    public void displayRoom() {
        System.out.println("Room No: " + roomNumber);
        System.out.println("Room Type: " + type);
        System.out.println("Occupied: " + occupied);
    }
}

class Complaint {
    private int studentId;
    private String complaintText;

    public Complaint(int studentId, String complaintText) {
        this.studentId = studentId;
        this.complaintText = complaintText;
    }

    public void displayComplaint() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Complaint: " + complaintText);
    }
}

class HostelManagement {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Complaint> complaints = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student Added Successfully");
    }

    public void addRoom(Room r) {
        rooms.add(r);
        System.out.println("Room Added Successfully");
    }

    public void allocateRoom(int studentId) {

        for (Student s : students) {

            if (s.id == studentId) {

                for (Room r : rooms) {

                    if (!r.isOccupied() &&
                            r.getType().equalsIgnoreCase(s.getRoomPreference())) {

                        r.allocate();
                        s.setAllocatedRoom(r.getRoomNumber());

                        System.out.println("Room Allocated Successfully");
                        return;
                    }
                }

                System.out.println("No Rooms Available");
                return;
            }
        }

        System.out.println("Student Not Found");
    }

    public void addComplaint(Complaint c) {
        complaints.add(c);
        System.out.println("Complaint Registered");
    }

    public void showStudents() {

        for (Student s : students) {
            System.out.println("-------------------");
            s.displayStudent();
        }
    }

    public void showRooms() {

        for (Room r : rooms) {
            System.out.println("-------------------");
            r.displayRoom();
        }
    }

    public void showComplaints() {

        for (Complaint c : complaints) {
            System.out.println("-------------------");
            c.displayComplaint();
        }
    }
}

public class SmartHostel {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        HostelManagement hm = new HostelManagement();

        hm.addRoom(new Room(101, "Single"));
        hm.addRoom(new Room(102, "Shared"));
        hm.addRoom(new Room(103, "Single"));
        hm.addRoom(new Room(104, "Shared"));

        int choice;

        do {

            System.out.println("\n===== SMART HOSTEL MANAGEMENT =====");

            System.out.println("1. Add Student");
            System.out.println("2. Allocate Room");
            System.out.println("3. Register Complaint");
            System.out.println("4. View Students");
            System.out.println("5. View Rooms");
            System.out.println("6. View Complaints");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    System.out.print("Enter Room Preference (Single/Shared): ");
                    String pref = sc.nextLine();

                    Student s = new Student(id, name, dept, pref);

                    hm.addStudent(s);

                    break;

                case 2:

                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();

                    hm.allocateRoom(sid);

                    break;

                case 3:

                    System.out.print("Enter Student ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Complaint: ");
                    String comp = sc.nextLine();

                    Complaint c = new Complaint(cid, comp);

                    hm.addComplaint(c);

                    break;

                case 4:

                    hm.showStudents();

                    break;

                case 5:

                    hm.showRooms();

                    break;

                case 6:

                    hm.showComplaints();

                    break;

                case 7:

                    System.out.println("Exiting Program...");
                    break;

                default:

                    System.out.println("Invalid Choice");
            }

        } while (choice != 7);

        sc.close();
    }
}