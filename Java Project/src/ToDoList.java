import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

        private static final ArrayList<String> tasks = new ArrayList<>();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenu();
                int choice = getValidatedChoice(scanner);

                switch (choice) {
                    case 1 -> viewTasks();
                    case 2 -> addTask(scanner);
                    case 3 -> editTask(scanner);
                    case 4 -> deleteTask(scanner);
                    case 5 -> {
                        System.out.println("Thank you for using the To-Do List App. Goodbye!");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private static void displayMenu() {
            System.out.println("\n====================================");
            System.out.println("           To-Do List App           ");
            System.out.println("====================================");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Edit Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
        }

        private static int getValidatedChoice(Scanner scanner) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
            }
            return scanner.nextInt();
        }

        private static void viewTasks() {
            System.out.println("\n------------------------------------");
            System.out.println("           Your Tasks               ");
            System.out.println("------------------------------------");
            if (tasks.isEmpty()) {
                System.out.println("Your to-do list is empty.");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
            System.out.println("------------------------------------");
        }

        private static void addTask(Scanner scanner) {
            System.out.print("Enter the task to add: ");
            scanner.nextLine(); // Consume newline
            String task = scanner.nextLine();
            if (task.isEmpty()) {
                System.out.println("Task cannot be empty. Please try again.");
            } else {
                tasks.add(task);
                System.out.println("Task added successfully!");
            }
        }

        private static void editTask(Scanner scanner) {
            viewTasks();
            if (tasks.isEmpty()) {
                return;
            }

            System.out.print("Enter the task number to edit: ");
            int taskNumber = getValidatedTaskNumber(scanner);

            if (taskNumber != -1) {
                System.out.print("Enter the updated task: ");
                scanner.nextLine(); // Consume newline
                String updatedTask = scanner.nextLine();
                if (updatedTask.isEmpty()) {
                    System.out.println("Updated task cannot be empty. Please try again.");
                } else {
                    tasks.set(taskNumber - 1, updatedTask);
                    System.out.println("Task updated successfully!");
                }
            }
        }

        private static void deleteTask(Scanner scanner) {
            viewTasks();
            if (tasks.isEmpty()) {
                return;
            }

            System.out.print("Enter the task number to delete: ");
            int taskNumber = getValidatedTaskNumber(scanner);

            if (taskNumber != -1) {
                tasks.remove(taskNumber - 1);
                System.out.println("Task deleted successfully!");
            }
        }

        private static int getValidatedTaskNumber(Scanner scanner) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid task number.");
                scanner.next();
            }
            int taskNumber = scanner.nextInt();
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                System.out.println("Invalid task number. Please try again.");
                return -1;
            }
            return taskNumber;
        }


}
