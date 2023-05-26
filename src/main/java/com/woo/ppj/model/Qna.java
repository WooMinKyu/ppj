package com.woo.ppj.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String content;
    @Column(insertable = false, updatable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
