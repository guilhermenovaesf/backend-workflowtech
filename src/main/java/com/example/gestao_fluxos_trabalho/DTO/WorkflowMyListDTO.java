package com.example.gestao_fluxos_trabalho.DTO;

import java.util.Date;

public class WorkflowMyListDTO {
    private Long id;
    private String title;
    private boolean canceled;
    private boolean finished;
    private Date createdOn;

    public WorkflowMyListDTO(Long id, String title, boolean canceled, boolean finished,Date createdOn) {
        this.id = id;
        this.title = title;
        this.canceled = canceled;
        this.finished = finished;
        this.createdOn = createdOn;

    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
