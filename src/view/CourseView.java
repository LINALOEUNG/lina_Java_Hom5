package view;

import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import service.CourseService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class CourseView {
    private CourseService courseService = new CourseService();
    private Scanner scanner = new Scanner(System.in);
    private List<Course> courses = new ArrayList<>();
    private Random random = new Random();

    //Random ID
    private int generateDefaultId() {
        return Stream.generate(() -> random.nextInt(500))
                .filter(id -> !courseService.getAllCourses().stream().anyMatch(course -> course.getId() == id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to generate unique ID"));
    }

    //AddNewCouurse
    public void addNewCourse() {
        Course course = new Course();
        courses.add(course);
        System.out.print("[+]Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("[+]Enter Instructor Names : ");
        List<String> instructors = List.of(scanner.nextLine().split(","));
        System.out.print("[+]Enter Requirements : ");
        List<String> requirements = List.of(scanner.nextLine().split(","));

        Integer id = generateDefaultId();
        LocalDate startDate = LocalDate.now();
        course = new Course(id, title, instructors, requirements, startDate);
        courseService.addCourse(course);
        System.out.println("[!]Course added successfully!");
    }

    public void findCourseById() {
        System.out.print("[+] Enter Course ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Course> courseOptional = courseService.findCourseById(id);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();

            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("TITLE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("INSTRUCTOR", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("REQUIREMENTS", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell("START DATE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.setColumnWidth(0, 25, 40);
            table.setColumnWidth(1, 25, 40);
            table.setColumnWidth(2, 25, 40);
            table.setColumnWidth(3, 25, 40);
            table.setColumnWidth(4, 25, 40);

            String yellowColor = "\u001B[33m"; // Code for column color
            String resetColor = "\u001B[0m"; // Reset color
            table.addCell(yellowColor + course.getId() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getTitle() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getInstructorNames().toString() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getRequirements() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getStartDate() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            System.out.println(table.render());
        } else {
            System.out.println("Course not found!");
        }
    }

    //
    public void findCourseByTitle() {
        System.out.print("[+]Enter Course Name: ");
        String name = scanner.nextLine();
        List<Course> courses = courseService.findCourseByName(name);
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("TITLE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("INSTRYCTOR", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("REQUIREMENTS", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("START DATE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.setColumnWidth(0, 25, 40);
        table.setColumnWidth(1, 25, 40);
        table.setColumnWidth(2, 25, 40);
        table.setColumnWidth(3, 25, 40);
        table.setColumnWidth(4, 25, 40);

        courses.forEach(course -> {
            String yellowColor = "\u001B[33m"; // code for column
            String resetColor = "\u001B[0m"; // Reset color
            table.addCell(yellowColor + course.getId() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getTitle() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getInstructorNames().toString() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getRequirements() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getStartDate() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        });
        System.out.println(table.render());
        if (courses.isEmpty()) {
            System.out.println("No courses found!");
        }
    }

    public void removeCourseById() {
        System.out.print("[+]Enter ID to Remove: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (courseService.removeCourseById(id)) {
            System.out.println("Course removed successfully!");
        } else {
            System.out.println("Course not found!");
        }
    }


    public void ListCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available!");
        } else {
            displayCoursesTable(courses);
        }
    }

    private void displayCoursesTable(List<Course> courses) {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("TITLE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("INSTRYCTOR", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("REQUIREMENTS", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("START DATE", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.setColumnWidth(0, 25, 40);
        table.setColumnWidth(1, 25, 40);
        table.setColumnWidth(2, 25, 40);
        table.setColumnWidth(3, 25, 40);
        table.setColumnWidth(4, 25, 40);

        courses.forEach(course -> {
            String yellowColor = "\u001B[33m"; // code for column
            String resetColor = "\u001B[0m"; // Reset color
            table.addCell(yellowColor + course.getId() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getTitle() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getInstructorNames().toString() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getRequirements() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getStartDate() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));

        });
        System.out.println(table.render());
    }
}



