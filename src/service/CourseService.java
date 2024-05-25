package service;
import model.Course;
import repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseService {
    private CourseRepository courseRepository = new CourseRepository();

    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public Optional<Course> findCourseById(int id) {
        return courseRepository.findCourseById(id);
    }

    public List<Course> findCourseByName(String name) {
        return courseRepository.findCourseByName(name);
    }

    public boolean removeCourseById(int id) {
        return courseRepository.removeCourseById(id);
    }
}
