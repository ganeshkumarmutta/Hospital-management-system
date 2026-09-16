import java.io.*;
import java.util.*;

// =========================
// INTERFACE
// =========================
interface HospitalService {
    void displayDetails();
}



abstract class Person {
    private int id;
    private String name;
    private int age;

    // Constructor
    Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Encapsulation - Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Abstract method
    abstract void showRole();
}

// =========================
// PATIENT CLASS
// =========================
class Patient extends Person implements HospitalService {

    private String disease;
    private String doctor;

    Patient(int id, String name, int age, String disease, String doctor) {
        super(id, name, age);
        this.disease = disease;
        this.doctor = doctor;
    }

    @Override
    void showRole() {
        System.out.println("Role: Patient");
    }

    @Override
    public void displayDetails() {
        System.out.println("--------------------------------");
        System.out.println("Patient ID : " + getId());
        System.out.println("Name       : " + getName());
        System.out.println("Age        : " + getAge());
        System.out.println("Disease    : " + disease);
        System.out.println("Doctor     : " + doctor);
        System.out.println("--------------------------------");
    }

    public String getDisease() {
        return disease;
    }

    public String getDoctor() {
        return doctor;
    }
}

// =========================
// DOCTOR CLASS
// =========================
class Doctor extends Person implements HospitalService {

    private String specialization;

    Doctor(int id, String name, int age, String specialization) {
        super(id, name, age);
        this.specialization = specialization;
    }

    @Override
    void showRole() {
        System.out.println("Role: Doctor");
    }

    @Override
    public void displayDetails() {
        System.out.println("--------------------------------");
        System.out.println("Doctor ID        : " + getId());
        System.out.println("Name             : " + getName());
        System.out.println("Age              : " + getAge());
        System.out.println("Specialization   : " + specialization);
        System.out.println("--------------------------------");
    }

    public String getSpecialization() {
        return specialization;
    }
}

// =========================
// APPOINTMENT CLASS
// =========================
class Appointment {

    private int appointmentId;
    private int patientId;
    private int doctorId;
    private String date;

    Appointment(int appointmentId, int patientId, int doctorId, String date) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public void displayAppointment() {
        System.out.println("--------------------------------");
        System.out.println("Appointment ID : " + appointmentId);
        System.out.println("Patient ID     : " + patientId);
        System.out.println("Doctor ID      : " + doctorId);
        System.out.println("Date           : " + date);
        System.out.println("--------------------------------");
    }
}

// =========================
// BILL CLASS
// =========================
class Bill {

    private int patientId;
    private double consultationFee;
    private double medicineFee;
    private double roomFee;

    Bill(int patientId, double consultationFee,
         double medicineFee, double roomFee) {

        this.patientId = patientId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.roomFee = roomFee;
    }

    // Method overloading
    public double calculateBill() {
        return consultationFee + medicineFee + roomFee;
    }

    public double calculateBill(double discount) {
        double total = consultationFee + medicineFee + roomFee;
        return total - (total * discount / 100);
    }

    public void displayBill() {
        double total = calculateBill();

        System.out.println("--------------------------------");
        System.out.println("Patient ID       : " + patientId);
        System.out.println("Consultation Fee : " + consultationFee);
        System.out.println("Medicine Fee     : " + medicineFee);
        System.out.println("Room Fee         : " + roomFee);
        System.out.println("Total Bill       : " + total);
        System.out.println("--------------------------------");
    }
}

// =========================
// CUSTOM EXCEPTION
// =========================
class InvalidAgeException extends Exception {

    InvalidAgeException(String message) {
        super(message);
    }
}

// =========================
// HOSPITAL CLASS
// =========================
class Hospital {

    // Collections
    ArrayList<Patient> patients = new ArrayList<>();
    ArrayList<Doctor> doctors = new ArrayList<>();
    ArrayList<Appointment> appointments = new ArrayList<>();

    // =========================
    // ADD PATIENT
    // =========================
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient added successfully.");
    }

    // =========================
    // ADD DOCTOR
    // =========================
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added successfully.");
    }

    // =========================
    // SHOW PATIENTS
    // =========================
    public void showPatients() {

        if (patients.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }

        System.out.println("\n===== PATIENT LIST =====");

        for (Patient p : patients) {
            p.displayDetails();
        }
    }

    // =========================
    // SHOW DOCTORS
    // =========================
    public void showDoctors() {

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.println("\n===== DOCTOR LIST =====");

        for (Doctor d : doctors) {
            d.displayDetails();
        }
    }

    // =========================
    // SEARCH PATIENT
    // =========================
    public void searchPatient(int id) {

        for (Patient p : patients) {

            if (p.getId() == id) {
                System.out.println("\nPatient found:");
                p.displayDetails();
                return;
            }
        }

        System.out.println("Patient not found.");
    }

    // =========================
    // SEARCH DOCTOR
    // =========================
    public void searchDoctor(int id) {

        for (Doctor d : doctors) {

            if (d.getId() == id) {
                System.out.println("\nDoctor found:");
                d.displayDetails();
                return;
            }
        }

        System.out.println("Doctor not found.");
    }

    // =========================
    // APPOINTMENT
    // =========================
    public void addAppointment(Appointment appointment) {

        appointments.add(appointment);

        System.out.println("Appointment booked successfully.");
    }

    // =========================
    // SHOW APPOINTMENTS
    // =========================
    public void showAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }

        System.out.println("\n===== APPOINTMENTS =====");

        for (Appointment a : appointments) {
            a.displayAppointment();
        }
    }

    // =========================
    // FILE I/O
    // =========================
    public void savePatientToFile(Patient patient) {

        try {

            FileWriter writer =
                    new FileWriter("patients.txt", true);

            writer.write(
                    patient.getId() + "," +
                    patient.getName() + "," +
                    patient.getAge() + "," +
                    patient.getDisease() + "," +
                    patient.getDoctor() + "\n"
            );

            writer.close();

            System.out.println("Patient saved to file.");

        } catch (IOException e) {

            System.out.println("File error: " + e.getMessage());
        }
    }
}

// =========================
// MULTITHREADING
// =========================
class HospitalThread extends Thread {

    private String task;

    HospitalThread(String task) {
        this.task = task;
    }

    @Override
    public void run() {

        System.out.println(
                Thread.currentThread().getName()
                + " is processing: " + task
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }

        System.out.println(
                Thread.currentThread().getName()
                + " completed: " + task
        );
    }
}

// =========================
// MAIN CLASS
// =========================
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Hospital hospital = new Hospital();

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println("     HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("================================");

            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Show Patients");
            System.out.println("4. Show Doctors");
            System.out.println("5. Search Patient");
            System.out.println("6. Search Doctor");
            System.out.println("7. Book Appointment");
            System.out.println("8. Show Appointments");
            System.out.println("9. Generate Bill");
            System.out.println("10. Save Patient to File");
            System.out.println("11. Start Hospital Threads");
            System.out.println("12. Exit");

            System.out.print("\nEnter your choice: ");

            try {

                choice = sc.nextInt();

                switch (choice) {

                    // =========================
                    // ADD PATIENT
                    // =========================
                    case 1:

                        System.out.print("Enter Patient ID: ");
                        int pid = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Patient Name: ");
                        String pname = sc.nextLine();

                        System.out.print("Enter Patient Age: ");
                        int age = sc.nextInt();

                        try {

                            if (age <= 0 || age > 120) {
                                throw new InvalidAgeException(
                                        "Invalid patient age."
                                );
                            }

                        } catch (InvalidAgeException e) {

                            System.out.println(e.getMessage());
                            break;
                        }

                        sc.nextLine();

                        System.out.print("Enter Disease: ");
                        String disease = sc.nextLine();

                        System.out.print("Enter Doctor Name: ");
                        String doctorName = sc.nextLine();

                        Patient patient = new Patient(
                                pid,
                                pname,
                                age,
                                disease,
                                doctorName
                        );

                        hospital.addPatient(patient);

                        break;

                    // =========================
                    // ADD DOCTOR
                    // =========================
                    case 2:

                        System.out.print("Enter Doctor ID: ");
                        int did = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Doctor Name: ");
                        String dname = sc.nextLine();

                        System.out.print("Enter Doctor Age: ");
                        int dage = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Specialization: ");
                        String specialization = sc.nextLine();

                        Doctor doctor = new Doctor(
                                did,
                                dname,
                                dage,
                                specialization
                        );

                        hospital.addDoctor(doctor);

                        break;

                    // =========================
                    // SHOW PATIENTS
                    // =========================
                    case 3:

                        hospital.showPatients();

                        break;

                    // =========================
                    // SHOW DOCTORS
                    // =========================
                    case 4:

                        hospital.showDoctors();

                        break;

                    // =========================
                    // SEARCH PATIENT
                    // =========================
                    case 5:

                        System.out.print("Enter Patient ID: ");
                        int searchPid = sc.nextInt();

                        hospital.searchPatient(searchPid);

                        break;

                    // =========================
                    // SEARCH DOCTOR
                    // =========================
                    case 6:

                        System.out.print("Enter Doctor ID: ");
                        int searchDid = sc.nextInt();

                        hospital.searchDoctor(searchDid);

                        break;

                    // =========================
                    // BOOK APPOINTMENT
                    // =========================
                    case 7:

                        System.out.print("Enter Appointment ID: ");
                        int aid = sc.nextInt();

                        System.out.print("Enter Patient ID: ");
                        int apid = sc.nextInt();

                        System.out.print("Enter Doctor ID: ");
                        int adid = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Appointment Date: ");
                        String date = sc.nextLine();

                        Appointment appointment =
                                new Appointment(
                                        aid,
                                        apid,
                                        adid,
                                        date
                                );

                        hospital.addAppointment(appointment);

                        break;

                    // =========================
                    // SHOW APPOINTMENTS
                    // =========================
                    case 8:

                        hospital.showAppointments();

                        break;

                    // =========================
                    // BILL
                    // =========================
                    case 9:

                        System.out.print("Enter Patient ID: ");
                        int billPid = sc.nextInt();

                        System.out.print("Consultation Fee: ");
                        double consultation = sc.nextDouble();

                        System.out.print("Medicine Fee: ");
                        double medicine = sc.nextDouble();

                        System.out.print("Room Fee: ");
                        double room = sc.nextDouble();

                        Bill bill = new Bill(
                                billPid,
                                consultation,
                                medicine,
                                room
                        );

                        bill.displayBill();

                        System.out.print(
                                "Enter discount percentage: "
                        );

                        double discount = sc.nextDouble();

                        System.out.println(
                                "Final Bill after discount: "
                                + bill.calculateBill(discount)
                        );

                        break;

                    // =========================
                    // FILE I/O
                    // =========================
                    case 10:

                        if (hospital.patients.isEmpty()) {

                            System.out.println(
                                    "No patients available."
                            );

                        } else {

                            Patient p =
                                    hospital.patients.get(
                                            hospital.patients.size() - 1
                                    );

                            hospital.savePatientToFile(p);
                        }

                        break;

                    // =========================
                    // MULTITHREADING
                    // =========================
                    case 11:

                        HospitalThread t1 =
                                new HospitalThread(
                                        "Patient Registration"
                                );

                        HospitalThread t2 =
                                new HospitalThread(
                                        "Appointment Processing"
                                );

                        HospitalThread t3 =
                                new HospitalThread(
                                        "Billing"
                                );

                        t1.start();
                        t2.start();
                        t3.start();

                        break;

                    // =========================
                    // EXIT
                    // =========================
                    case 12:

                        System.out.println(
                                "Thank you for using Hospital Management System."
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice."
                        );
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input. Please enter the correct data type."
                );

                sc.nextLine();

                choice = 0;
            }

        } while (choice != 12);

        sc.close();
    }
}
