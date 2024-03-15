/*
 *              MINIPROJECT NAME:SCHOOL MANAGEMENT SYSTEM
 *              UCE2022468: GAYATRI CHOTHE
 *              UCE2022469: RUTUJA GHERDE
 *              UCE2022460: VEDANTI GABALE          
 */
package cummins;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
//Stack to store data
class CustomStack {
     int maxSize;
     int top;
     int[] stackArray;
    public CustomStack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }
    public void push(int item) {
        if (top < maxSize - 1) {
            stackArray[++top] = item;
        } else {
            System.out.println("\tStack is full. Cannot push more elements.");
        }
    }

    public int pop() {
        if (top >= 0) {
            return stackArray[top--];
        } else {
            System.out.println("\tStack is empty. Cannot pop.");
            return -1;
        }
    }

    public int peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            System.out.println("\tStack is empty. No element to peek.");
            return -1;
        }
    }
}



class Student {
	String tempName ;
    int tempAge;
    String tempGender;
    String tempClass ;
    double tempFee;
    Scanner sc=new Scanner(System.in);
    String[] names;
    int[] ages;
    String[] genders;
    String[] classes;
    double[] fees;
    int[] ids;
    Date[] dobs;
    int size;
    CustomStack admissionStack;
	Date tempDob;
    public Student(int maxSize) {
        names = new String[maxSize];
        ages = new int[maxSize];
        genders = new String[maxSize];
        classes = new String[maxSize];
        fees = new double[maxSize];
        ids = new int[maxSize];
        dobs = new Date[maxSize];
        size = 0;
        admissionStack = new CustomStack(maxSize);
    }
    private boolean containsNumber(String input) {
        return input.matches(".*\\d.*");
    }
    //Method for taking name from user
    private String validateName(Scanner scanner) {
        String name;
        do {
            System.out.print("\tEnter name: ");
            name = scanner.nextLine().trim();
            if (containsNumber(name)) {
                System.out.println("\tName should not contain numbers. Please re-enter.");
            }
        } while (containsNumber(name));
        return name;
    }
    //Method to add new student details
    public void addStudent(String name, int age, String gender, String className, double fee, int id, Date dob) {
        names[size] = name;
        ages[size] = age;
        genders[size] = gender;
        classes[size] = className;
        fees[size] = fee;
        ids[size] = id;
        dobs[size] = dob;
        admissionStack.push(id);
        size++;
        System.out.println("\tStudent with ID " + id + " added to the system.");
    }
    //Method to update the student information
    public void updateStudentInformation(int studentId, String name,int age, Date dob) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == studentId) {
                names[i] = name;//To update name
                ages[i] = age;//To update age
                dobs[i] = dob; // Update date of birth
                System.out.println("\tStudent information updated for ID " + studentId);
                return;
            }
            else {
            //If Id not found!
            System.out.println("\tStudent not found with ID: " + studentId);
        }  
        }
}
    //Method for displaying all students
    public void displayAllStudents(SimpleDateFormat sdf) {
    	if (size == 0) {
            System.out.println("\tNo data available to display all students.");
        }
    	else {
        for (int i = 0; i < size; i++) {
            System.out.println("\tDisplaying details of " + (i + 1) + "th student");
            System.out.println("\tStudent ID: " + ids[i]);
            System.out.println("\tStudent name: " + names[i]);
            System.out.println("\tAge: " + ages[i]);
            System.out.println("\tGender: " + genders[i]);
            System.out.println("\tClass: " + classes[i]);
            System.out.println("\tFees: " + fees[i]);
            System.out.println("\tDate of Birth: " + sdf.format(dobs[i])); // Display date of birth using the provided SimpleDateFormat object
        }
    }
    }
    //Method to get the student name
    public String getStudentName(int studentInfoId) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == studentInfoId) {
                return names[i];
            }
        }
        return null; // Return null if the student is not found.
    }
    //validation for gender(Should enter f m or t)
    private String validateGender(String gender) {
        String validatedGender = gender.toLowerCase(); // Convert to lowercase for easier comparison

        if (validatedGender.equals("m") || validatedGender.equals("male")) {
            return "Male";
        } else if (validatedGender.equals("f") || validatedGender.equals("female")) {
            return "Female";
        } else if (validatedGender.equals("t") || validatedGender.equals("transgender")) {
            return "Transgender";
        } else {
            System.out.println("\tInvalid gender input. Please enter 'M' for Male, 'F' for Female, or 'T' for Transgender.");
            return "";
        }
    }
    //To connect dob with age
     boolean validate(Date dob, int age) {
        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int dobYear = dobCalendar.get(Calendar.YEAR);
        // Calculate the age based on the year of birth and the current year
        int calculatedAge = currentYear - dobYear;
        // Check if the calculated age matches the entered age
        return calculatedAge == age;
    }
    // Method to set temporary student information with gender validation
     public int setTemp(String name, int age, String gender, String className, double fee, Date tempDob) {
         tempName = name;
         if (!validate(tempDob, age)) {
             System.out.println("\tInvalid age. Age should be between 6 and 18.");
             return -1;
         }
         tempAge = age;
         tempGender = gender;
         tempClass = className;
         tempFee = fee;
         this.tempDob = tempDob;
         return 0;
     }
    public String getStudentGender(int studentId) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == studentId) {	
                return genders[i];
            }
        }
        return null;
    }
    public String getStudentClass(int studentId) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == studentId) {
                return classes[i];
            }
        }
        return null;
    }
    public double getStudentFee(int studentId) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == studentId) {
                return fees[i];
            }
        }
        return -1;
    }
    public int getStudentAge(int studentId) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == studentId) {
                return ages[i];
            }
        }
        return -1;
    }
    public Date getStudentDob(int studentId) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == studentId) {
                return dobs[i];
            }
        }
        return null;
    }
    //Fee Structure
    public final double[] classFees = { 1000.0, 1200.0, 1400.0, 1600.0, 1800.0, 2000.0, 2200.0, 2400.0, 2600.0, 2800.0 };
    public void displayFeeStructure() {
        System.out.println("\tFee Structure for Classes 1st to 10th (per annum):");
        for (int i = 0; i < classFees.length; i++) {
            System.out.println("\tClass " + (i + 1) + ": Rs." + classFees[i]);
        }
    }
    //For canceling admission
    public void cancelAdmission(int studentIdToDelete) {
        int indexToDelete = -1;

        for (int i = 0; i < size; i++) {
            if (ids[i] == studentIdToDelete) {
                indexToDelete = i;
                break;
            }
        }
        if (indexToDelete != -1) {
            for (int i = indexToDelete; i < size - 1; i++) {
                names[i] = names[i + 1];
                ages[i] = ages[i + 1];
                ids[i] = ids[i + 1];
                dobs[i] = dobs[i + 1];
                genders[i] = genders[i + 1];
                classes[i] = classes[i + 1];
                fees[i] = fees[i + 1];
            }
            size--;
            System.out.println("\tAdmission for student with ID " + studentIdToDelete + " has been canceled.");
        } else {
            System.out.println("\tStudent not found with ID: " + studentIdToDelete);
        }
    }
     //User interaction MENU
     void userInteraction(Student studentSystem, Teacher teacherSystem, Scanner scanner) {
    	 int userChoice;
        do {
            System.out.println("\tUser Menu:");
            System.out.println("\t1. View Student Info");
            System.out.println("\t2. View Teacher Info");
            System.out.println("\t3. Display Courses");
            System.out.println("\t4. Display All teachers");
            System.out.println("\t5.View fee structure");
            System.out.println("\t0. Back to Main Menu");
            System.out.print("\tEnter your choice: ");
            userChoice = scanner.nextInt();
            scanner.nextLine();  

            switch (userChoice) {
                case 1:
                    // View Student Information 
                    System.out.print("\tEnter student ID to view information: ");
                    int studentInfoId = scanner.nextInt();
                    scanner.next();  
                    String studentInfoName = studentSystem.getStudentName(studentInfoId);
                    int studentInfoAge = studentSystem.getStudentAge(studentInfoId);
                    String studentInfoGender = studentSystem.getStudentGender(studentInfoId);
                    String studentInfoClass = studentSystem.getStudentClass(studentInfoId);
                    double studentInfoFee = studentSystem.getStudentFee(studentInfoId);
                    Date studentInfoDob = studentSystem.getStudentDob(studentInfoId);
                    if (studentInfoName != null) {
                        System.out.println("\tStudent Information:");
                        System.out.println("\tName: " + studentInfoName);
                        System.out.println("\tAge: " + studentInfoAge);
                        System.out.println("\tGender: " + studentInfoGender);
                        System.out.println("\tClass: " + studentInfoClass);
                        System.out.println("\tFees: " + studentInfoFee);
                        System.out.println("\t\tID: " + studentInfoId);
                    } else {
                        System.out.println("\tStudent not found with ID: " + studentInfoId);
                    }
                    break;
                case 2:
                    // View Teacher Information (Same as admin's view teacher information)
                    System.out.print("\tEnter teacher ID to view information: ");
                    int teacherInfoId = scanner.nextInt();
                    scanner.next();  
                    String teacherInfoName = teacherSystem.getTeacherName(teacherInfoId);
                    String teacherInfoSubject = teacherSystem.getTeacherSubjects(teacherInfoId);
                    if (teacherInfoName != null) {
                        System.out.println("\tTeacher Information:");
                        System.out.println("\tName: " + teacherInfoName);
                        System.out.println("\tID: " + teacherInfoId);
                        System.out.println("\tSubject: " + teacherInfoSubject);
                    } else {
                        System.out.println("\tTeacher not found with ID: " + teacherInfoId);
                    }
                    break;
                case 3:
              	  String[] courses = teacherSystem.getAvailableSubjects();
              	    if (courses != null && courses.length > 0) {
              	        System.out.println("\tCourses Offered:");
              	        for (String course : courses) {
              	            System.out.println("\t\t"+course);
              	        }
              	    } else {
              	        System.out.println("\tNo courses available at the moment.");
              	    }
              	    break;
                case 4://display all teacher details
                	teacherSystem.displayAllteachers();
                	break;
                case 5:
                	//Display fee structure
                	studentSystem.displayFeeStructure();
                	break;
                case 0:
                    return;
                default:
                    System.out.println("\tInvalid choice. Please enter a valid option.");
                    break;
            }
        }while(userChoice!=0);
    }
      
    //Admin interaction MENU
	void adminInteraction(Student studentSystem,Teacher teacherSystem, SimpleDateFormat sdf, Scanner scanner) {
    	  String studentName;
    	  int studentAge=0;
    	  String studentClass;
    	  double studentFee;
    	  int studentId ;
    	  String dobString;
    	  int adminChoice;
        do {
            System.out.println("\tAdmin Menu:");
            System.out.println("\t1. Add Student");
            System.out.println("\t2. Update Student Information");
            System.out.println("\t3. Cancel Admission");
            System.out.println("\t4. Add Teacher");
            System.out.println("\t5. View Student Info");
            System.out.println("\t6. View Teacher Info");
            System.out.println("\t7. Display all students Info");
            System.out.println("\t8. Display all teachers Info");
            System.out.println("\t9. Add new courses:");
            System.out.println("\t0. Back to Main Menu");

            System.out.print("\tEnter your choice: ");
             adminChoice = sc.nextInt();
            switch (adminChoice) {
                //To take input from user
                case 1:
                	//Name as Input
                    studentName = validateName(scanner);
                    //Age as input
                    System.out.print("\tEnter student age: ");
                    String ageInput = scanner.next();
                    if (ageInput.matches("\\d+")) {
                        studentAge = Integer.parseInt(ageInput);
                    } else {
                        System.out.println("\tInvalid age. Please enter a valid age.");
                        break;
                    }
                    //Gender as input
                    String validatedGender = "";
                    while (validatedGender.equals("")) {
                        System.out.print("\tEnter student gender (M/F/T): ");
                        validatedGender = validateGender(scanner.nextLine());
                    }
                    //Class as input
                    System.out.print("\tEnter student class: ");
                     studentClass = scanner.next();
                     //Fees as input
                     System.out.print("\tEnter student fees: ");
                     String feeInput = scanner.next();
                     try {
                         studentFee = Double.parseDouble(feeInput);
                     } catch (NumberFormatException e) {
                         System.out.println("\tInvalid fees. Please enter a valid fee.");
                         break; 
                     }
                     //Id as input
                    System.out.print("\tEnter student ID: ");
                     studentId = scanner.nextInt();
                     //Dob as input including validations
                    System.out.print("\tEnter student date of birth (yyyy-MM-dd): ");
                    dobString = scanner.next();
                    while (true) {
                        dobString = scanner.next();

                        try {
                            Date studentDob = sdf.parse(dobString);
                            int validationResult = studentSystem.setTemp(studentName, studentAge, validatedGender, studentClass, studentFee, studentDob);

                            if (validationResult == -1) {
                                System.out.println("\tStudent information not added due to age and date of birth mismatch. Please re-enter the date of birth.");
                                System.out.print("\tEnter student date of birth (yyyy-MM-dd): ");
                            } else {
                                studentSystem.addStudent(studentName, studentAge, validatedGender, studentClass, studentFee, studentId, studentDob);
                                break; 
                            }
                        } catch (Exception e) {
                            System.out.println("\tInvalid date format. Please use yyyy-MM-dd. Please re-enter the date of birth.");
                            System.out.print("\tEnter student date of birth (yyyy-MM-dd): ");
                        }
                    }
                    break; 
                    //To update student info
                case 2:
                    int studentIdToUpdate;
                    String updatedName;
                    int updatedAge;
                    double updatedFee;
                    System.out.print("\tEnter student ID to update information: ");
                    studentIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); 
                     // Check if the provided ID exists in the system
                    boolean studentIdExists = false;
                    for (int i = 0; i < size; i++) {
                        if (ids[i] == studentIdToUpdate) {
                            studentIdExists = true;
                            break;
                        }
                    }

                    if (!studentIdExists) {
                        System.out.println("\tStudent not found with ID: " + studentIdToUpdate);
                        break; //
                    }
                    //For updating name
                    System.out.print("\tEnter updated name or press Enter to keep the existing name: ");
                    updatedName = scanner.nextLine();
                    //For updating age
                    System.out.print("\tEnter updated age or press Enter to keep the existing age: ");
                    String updatedAgeString = scanner.nextLine();
                    updatedAge = (updatedAgeString.isEmpty()) ? studentSystem.getStudentAge(studentIdToUpdate) : Integer.parseInt(updatedAgeString);
                   //For updating DOB
                    System.out.print("\tEnter updated date of birth (yyyy-MM-dd) or press Enter to keep the existing date of birth: ");
                    String updatedDobString = scanner.nextLine();

                    Date updatedDob;
                    if (updatedDobString.isEmpty()) {
                        updatedDob = studentSystem.getStudentDob(studentIdToUpdate);
                    } else {
                        try {
                            updatedDob = sdf.parse(updatedDobString);
                        } catch (ParseException e) {
                            System.out.println("\tInvalid date format. Please use yyyy-MM-dd.");
                            continue; // Continue the loop to re-enter the date of birth
                        }
                    }

                    studentSystem.updateStudentInformation(studentIdToUpdate, updatedName, updatedAge, updatedDob);

                    break;
                //Cancellation of Student admission
                case 3:
                	System.out.println("\tEnter the student Id that u want to cancel:\t");
                	int studentIdToDelete=scanner.nextInt();
                   cancelAdmission(studentIdToDelete);
                   
                    break;
                case 4:
                	//Take teacher details as input
                	//Teacher name as input
                    String teacherName = teacherSystem.validateName1();
                    //Teacher Id as input
                    System.out.print("\tEnter teacher ID: ");
                    int teacherId = scanner.nextInt();
                    scanner.nextLine(); 
                    //Subject as input
                    System.out.print("\tEnter teacher subject: ");
                    String subject = scanner.next();
                    teacherSystem.addTeacher(teacherName, teacherId, subject);
                    break;
                //To view details of particular student
                case 5:
                    System.out.print("/tEnter student ID to view information: ");
                    int studentInfoId = scanner.nextInt();
                    scanner.nextLine(); 
                    String studentInfoName = studentSystem.getStudentName(studentInfoId);
                    int studentInfoAge = studentSystem.getStudentAge(studentInfoId);
                    String studentInfoGender = studentSystem.getStudentGender(studentInfoId);
                    String studentInfoClass = studentSystem.getStudentClass(studentInfoId);
                    double studentInfoFee = studentSystem.getStudentFee(studentInfoId);
                    Date studentInfoDob = studentSystem.getStudentDob(studentInfoId);
                    if (studentInfoName != null) {
                        System.out.println("\tStudent Information:");
                        System.out.println("\tName: " + studentInfoName);
                        System.out.println("\tAge: " + studentInfoAge);
                        System.out.println("\tGender: " + studentInfoGender);
                        System.out.println("\tClass: " + studentInfoClass);
                        System.out.println("\tFees: " + studentInfoFee);
                        System.out.println("\tID: " + studentInfoId);
                        System.out.println("\tDate of Birth: " + sdf.format(studentInfoDob));
                    } else {
                        System.out.println("\tStudent not found with ID: " + studentInfoId);
                    }
                    break;
                //To view info of particular teacher
                case 6:
                    System.out.print("\tEnter teacher ID to view information: ");
                    int teacherInfoId = scanner.nextInt();
                    scanner.nextLine();
                    String teacherInfoName = teacherSystem.getTeacherName(teacherInfoId);
                    String teacherInfoSubject = teacherSystem.getTeacherSubjects(teacherInfoId);
                    if (teacherInfoName != null) {
                        System.out.println("\tTeacher Information:");
                        System.out.println("\tName: " + teacherInfoName);
                        System.out.println("\tID: " + teacherInfoId);
                        System.out.println("\tSubject: " + teacherInfoSubject);
                    } else {
                        System.out.println("\tTeacher not found with ID: " + teacherInfoId);
                    }
                    break;
                //To display data of all students
                case 7:
                	System.out.println("\tDisplaying all student's data");
                	displayAllStudents(sdf);
                	break;
                //To display data of all teachers
                case 8:
                	teacherSystem.displayAllteachers();
                	break;
                //For adding new course
                case 9:
                	teacherSystem.addNewCourse( teacherSystem,  scanner);
                	break;
                case 0:
                	break;
                default:
                    System.out.println("\tInvalid choice. Please enter a valid option.");
                    break;
            }
        }while(adminChoice!=0);
    } 
}

class Teacher {
    private String[] names;
    private int[] ids;
    private String[] subjects;
    private int size;
    //Constructor
    public Teacher(int maxSize) {
        names = new String[maxSize];
        ids = new int[maxSize];
        subjects = new String[maxSize];
        size = 0;
    }
    //Method to add new teacher
    public void addTeacher(String name, int id, String subject) {
        names[size] = name;
        ids[size] = id;
        subjects[size] = subject;
        size++;
        System.out.println("\tTeacher with ID " + id + " added to the system.");
    }
    //Method to display all teachers details
    public void displayAllteachers() {
    	if (size == 0) {
            System.out.println("\tNo data available to display all teachers.");
        } 
    	else {
    	for(int i=0;i<size;i++) {
    		System.out.println("\tDisplaying detail of "+(i+1)+"th teacher");
    				System.out.println("\tTeacher ID: " + names[i]);
    		        System.out.println("\tName: " + ids[i]);
    		        System.out.println("\tSubject: " + subjects[i]);
    		        System.out.println();
    	}
    }
    }
    boolean containsNumber(String input) {
        return input.matches(".*\\d.*");
    }
    //Take teacher name as input with validations
     String validateName1() {
    	 Scanner scanner=new Scanner(System.in);
    	 String name;
        do {
            System.out.print("\tEnter name: ");
            name = scanner.nextLine().trim();
            if (containsNumber(name)) {
                System.out.println("\tName should not contain numbers. Please re-enter.");
            }
        } while (containsNumber(name));
        return name;
    }
    public String getTeacherName(int teacherId) {
        for (int i = 0; i < size; i++) {
            if (ids[i] == teacherId) {
                return names[i];
            }
        }
        return null;
    }

    public String getTeacherSubjects(int teacherId) {
        StringBuilder subjectList = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (ids[i] == teacherId) {
                subjectList.append(subjects[i]);
                subjectList.append(", "); // Add a comma to separate subjects
            }
        }
        if (subjectList.length() > 0) {
            // Remove the trailing comma and space
            return subjectList.substring(0, subjectList.length() - 2);
        } else {
            return null; // Return null if the teacher is not found
        }
    }

    public String[] getAvailableSubjects() {
        String[] availableSubjects = new String[size];
        int availableSubjectsCount = 0;
        for (int i = 0; i < size; i++) {
            availableSubjects[availableSubjectsCount] = subjects[i];
            availableSubjectsCount++;
        }
        if (availableSubjectsCount > 0) {
            String[] result = new String[availableSubjectsCount];
            for (int i = 0; i < availableSubjectsCount; i++) {
                result[i] = availableSubjects[i];
            }
            return result;
        } else {
            return null;
        }
    }
    //For adding new course
    public static void addNewCourse(Teacher teacherSystem, Scanner scanner) {
        System.out.print("\tEnter the name of the new course: ");
        String courseName = scanner.next();

        // Check if the course already exists
        for (int i = 0; i < teacherSystem.size; i++) {
            if (teacherSystem.subjects[i].equalsIgnoreCase(courseName)) {
                System.out.println("\tThis course already exists, and it is taught by " + teacherSystem.names[i]);
                return;
            }
        }

        // If the course does not exist, allocate a teacher to it
        System.out.print("\tEnter the name of the teacher for this course: ");
        String teacherName = scanner.next();

        // Find the teacher's ID based on their name
        int teacherId = -1;
        for (int i = 0; i < teacherSystem.size; i++) {
            if (teacherSystem.names[i].equalsIgnoreCase(teacherName)) {
                teacherId = teacherSystem.ids[i];
                break;
            }
        }

        if (teacherId != -1) {
            teacherSystem.addTeacher(teacherName, teacherId, courseName);
            System.out.println("\tThe course '" + courseName + "' has been added and allocated to " + teacherName);
        } else {
            System.out.println("\tTeacher not found with the name " + teacherName + ". Please add the teacher first.");
        }
    }
}

public class StudentManagementSystem {
    public static void main(String[] args) {
        int maxStudents = 100; // Define the maximum number of students
        int maxTeachers = 100; // Define the maximum number of teachers
        Student studentSystem = new Student(maxStudents);
        Teacher teacherSystem = new Teacher(maxTeachers);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String header = "   ╔════════════════════════════════════════════════════════════╗\n"                       +
                "   ║                WELCOME TO VINCENT SCHOOL                   ║\n" +
                "   ║              SCHOOL FOR SECONDARY EDUCATION                ║\n" +
                "   ║                       PUNE                                 ║\n" +
                "   ║                                                            ║\n" +
                "   ║                                                            ║\n" +
                "   ║   Explore, Learn, and Thrive with Our Dedicated Educators  ║\n" +
                "   ║                and Supportive Community.                   ║\n" +
                "   ║                                                            ║\n" +
                "   ║        We're Shaping Tomorrow's Leaders Today.             ║\n" +
                "   ║                                                            ║\n" +
                "   ╚════════════════════════════════════════════════════════════╝";

        
        System.out.println(header);
        while (true) {
            System.out.println("\tSchool Management System Menu:");
            System.out.println("\t1. Admin Interaction");
            System.out.println("\t2. User Interaction");
            System.out.println("\t3. Exit");
            Scanner scanner = new Scanner(System.in);
            System.out.print("\tEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentSystem.adminInteraction(studentSystem, teacherSystem, sdf, scanner);
                    break;
                case 2:
                    studentSystem.userInteraction(studentSystem, teacherSystem, scanner);
                    break;
                case 3:
                    System.out.println("\tExiting the School Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\tInvalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}


