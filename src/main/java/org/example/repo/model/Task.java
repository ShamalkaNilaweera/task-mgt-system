package org.example.repo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.Priority;
import org.example.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Task_Id")
    private Integer taskId;
    @Column(name = "Task_Title")
    private String taskTitle;
    @Column(name = "Task_Description")
    private String taskDesc;
    @Column(name = "Due_Date")
    private LocalDate dueDate;
    @Column(name = "Priority")
    private Priority priority;
    @Column(name = "Status")
    private Status status;

}
