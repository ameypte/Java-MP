import java.util.ArrayList;
import java.util.Scanner;

class EmployeeDetails {
    protected int empId;
    protected String eName, contactNo;
    protected double sal;
    protected String designation;

    public EmployeeDetails(int empId, String eName, double sal, String contactNo, String designation) {
        this.empId = empId;
        this.eName = eName;
        this.sal = sal;
        this.contactNo = contactNo;
        this.designation = designation;
    }


    public void showEmployeeDetails() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Employee Name: " + eName);
        System.out.println("Employee Designation: " + designation);
        System.out.println("Employee Salary: " + sal);
        System.out.println("Contact No: " + contactNo);
    }

    public void updateEmployee() {
        int ch;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select what you want to update: ");
        System.out.print("\t[1] Employee Id\n\t[2] Employee name\n\t[3] Employee Designation \n\t[4] Employee Salary\n\t[5] Contact details\n\tInput: ");
        ch = sc.nextInt();
        sc.nextLine();
        switch (ch) {
            case 1:
                System.out.print("Enter new Employee Id: ");
                empId = sc.nextInt();
                break;
            case 2:
                System.out.print("Enter new Employee Name: ");
                eName = sc.nextLine();
                break;
            case 3:
                String[] des = {"Manager", "Programmer", "Designer", "Tester"};
                int des_no;
                while (true) {
                    System.out.print("Select Employee Designation:\n\t\t[1] MANAGER\n\t\t[2] PROGRAMMER\n\t\t[3] Designer\n\t\t[4] Tester\n\tINPUT:");
                    des_no = sc.nextInt();
                    if (des_no <= des.length && des_no >= 1) {
                        designation = des[des_no - 1];
                        break;
                    } else
                        System.out.println("Invalid Input!!");
                }
                break;
            case 4:
                System.out.print("Enter new Employee salary: ");
                sal = sc.nextDouble();
                break;
            case 5:
                System.out.print("Enter new Contact Number: ");
                contactNo = sc.nextLine();
                break;
            default:
                System.out.println("Please enter valid Input!!");
                updateEmployee();
        }
    }
}

class Employee {
    static ArrayList<EmployeeDetails> Emp = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    Employee() {

    }

    public static boolean actions(int ch) {
        switch (ch) {
            case 0:
                System.out.println("Exiting program...\n\n\t\tTHANK YOU FOR USING OUR SYSTEM!");
                return false;
            case 1:
                addEmployee();
                break;
            case 2:
                if (!showEmployeeList())
                    break;
                System.out.print("Enter Employee Id of employee which you want to update the details: ");
                int n = sc.nextInt();
                for (EmployeeDetails emp : Emp) {
                    if (emp.empId == n) {
                        emp.updateEmployee();
                        return true;
                    }
                }
                System.out.println("Employee not found with id " + n);
                break;
            case 3:
                deleteEmployee();
                break;
            case 4:
                if (!showEmployeeList())
                    break;
                System.out.print("Enter Employee Id of employee which you want to see the details: ");
                int m = sc.nextInt();
                for (EmployeeDetails emp : Emp) {
                    if (emp.empId == m) {
                        emp.showEmployeeDetails();
                        return true;
                    }
                }
                System.out.println("Employee not found with id " + m);
                break;
            case 5:
                showEmployeeList();
                break;
            default:
                System.out.println("Please Enter the Valid Input!!");
                break;
        }
        return true;
    }

    public static void addEmployee() {
        System.out.print("Enter Employee Id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Employee name: ");
        //clear line buffer
        String name = sc.nextLine();

        String[] des = {"Manager", "Programmer", "Designer", "Tester"};
        int des_no;
        while (true) {
            System.out.print("Select Employee Designation:\n\t\t[1] MANAGER\n\t\t[2] PROGRAMMER\n\t\t[3] Designer\n\t\t[4] Tester\n\tINPUT:");
            des_no = sc.nextInt();
            if (des_no <= des.length && des_no >= 1)
                break;
            else
                System.out.println("Invalid Input!!");
        }

        System.out.print("Enter Employee Salary: ");
        double sal = sc.nextDouble();
        sc.nextLine();
        String contactNo;
        while (true) {
            System.out.print("Enter Contact No: ");
            contactNo = sc.nextLine();
            if (contactNo.length() == 10)
                break;
            else
                System.out.println("Please Enter valid contact number!!");
        }

        //Creating an employee object
        EmployeeDetails emp = new EmployeeDetails(id, name, sal, contactNo, des[des_no - 1]);
        //Adding employee to array list
        Emp.add(emp);
        System.out.println("Employee added successfully!!");
    }

    public static boolean showEmployeeList() {
        if (Emp.size() == 0) {
            System.out.println("Nothing to display, please add employee in the system!");
            return false;
        }
        for (int i = 0; i < Emp.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + Emp.get(i).empId + " " + Emp.get(i).eName);
        }
        return true;
    }

    public static void deleteEmployee() {
        if (!showEmployeeList())
            return;
        System.out.print("Enter Employee Id of employee which you want to delete: ");
        int n = sc.nextInt();
        for (int i = 0; i < Emp.size(); i++) {
            if (Emp.get(i).empId == n) {
                System.out.print("Are you sure, you want to delete " + Emp.get(i).eName + "(y/n)? ");
                char ch = sc.next().charAt(0);
                if (ch == 'y' || ch == 'Y') {
                    Emp.remove(i);
                    System.out.println("Employee Deleted!!");
                } else {
                    System.out.println("Process Canceled!!");
                }
                return;
            }
        }
        System.out.println("Employee not found with id " + n);
    }

}

class EMS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("\n\n---------- WELCOME TO EMPLOYEE MANAGEMENT SYSTEM ----------\n\n");
        do {
            System.out.println("=============================================");
            System.out.println("\t\t[0] EXIT");
            System.out.println("\t\t[1] ADD EMPLOYEE");
            System.out.println("\t\t[2] UPDATE EMPLOYEE DETAILS");
            System.out.println("\t\t[3] DELETE EMPLOYEE");
            System.out.println("\t\t[4] SHOW EMPLOYEE DETAILS");
            System.out.println("\t\t[5] SHOW LIST OF EMPLOYEE");
            System.out.print("\t\tEnter your choice: ");
            choice = sc.nextInt();

        } while (Employee.actions(choice));
    }
}
