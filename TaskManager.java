import java.io.*;
import java.util.*;

class Task {
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return description + (completed ? " (Completed)" : "");
    }
}

public class TaskManager {
    private List<Task> tasks;
    private static final String FILE_NAME = "tasks.txt";

    public TaskManager() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(new Task(line));
            }
        } catch (IOException e) {
           
        }
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        saveTasksToFile();
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void completeTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markAsCompleted();
            System.out.println("Task \"" + task.getDescription() + "\" marked as completed.");
            saveTasksToFile();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(description);
                    break;
                case 2:
                    taskManager.displayTasks();
                    break;
                case 3:
                    System.out.print("Enter task index to mark as completed: ");
                    int index = scanner.nextInt();
                    taskManager.completeTask(index);
                    break;
                case 4:
                    System.out.println("Exiting Task Manager...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
