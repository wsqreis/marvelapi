package com.talents.orange.marvelapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comic {

    @Id
    private Long comicId;

    @Column
    private String title;

    @Column
    private Float price;

    @Column(length = 500)
    private String[] creators;

    @Column
    private String isbn;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
