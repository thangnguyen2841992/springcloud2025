package com.thang.account_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class StatisticDTO {
    private Long id;
//    @NonNull
    private String message;
//    @NonNull
    private Date createdDate;

    public StatisticDTO(Long id, String message, Date createdDate) {
        this.id = id;
        this.message = message;
        this.createdDate = createdDate;
    }

    public StatisticDTO(String message, Date createdDate) {
        this.message = message;
        this.createdDate = createdDate;
    }

    public StatisticDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
