
import view.CourseView;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        CourseView courseView = new CourseView();
        Scanner scanner = new Scanner(System.in);
        List<Consumer<CourseView>> actions = Arrays.asList(
                CourseView::addNewCourse,
                CourseView::ListCourses,
                CourseView::findCourseById,
                CourseView::findCourseByTitle,
                CourseView::removeCourseById
        );

        while (true) {
            printMenu();

            int option = getUserOption(scanner);
            if (option == 6) {
                System.out.println("Exiting...");
                System.exit(0);
            }

            if (option >= 1 && option <= 5) {
                actions.get(option - 1).accept(courseView);
            } else {
                System.out.println("[!]Invalid option. Please enter a number between 1 and 6.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("=".repeat(30));
        System.out.println("1. Add new Course");
        System.out.println("2. List Courses");
        System.out.println("3. Find Course by ID");
        System.out.println("4. Find Course by Name");
        System.out.println("5. Remove Course by ID");
        System.out.println("6. Exit");
        System.out.println("=".repeat(30));
    }

    private static int getUserOption(Scanner scanner) {
        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("[+] Insert option: ");
                option = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("[!!]Invalid input. Please Try  enter a valid number Again .");
            }
        }

        return option;
    }
}


