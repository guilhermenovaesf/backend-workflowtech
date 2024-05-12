package model.workflow_type;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "workflow_type")
public class Workflow_type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE", nullable = false, length = 500)
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "longtext")
    private String description;

    @Column(name = "CREATED_BY")
    private Integer createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private Date createdOn;

    // Constructors, getters, and setters

    public Workflow_type() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}