package repository;
import model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findCourseById(int id) {
        return courses.stream().filter(course -> course.getId() == id).findFirst();
    }

    public List<Course> findCourseByName(String name) {
        return courses.stream().filter(course -> course.getTitle().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    public boolean removeCourseById(int id) {
        return courses.removeIf(course -> course.getId() == id);
    }
}
