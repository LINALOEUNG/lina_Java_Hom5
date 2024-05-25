package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    private Integer id;
    private String title;
    private List<String> instructorNames;
    private List<String> requirements;
    private LocalDate startDate;
}
