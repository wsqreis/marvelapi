package com.talents.orange.marvelapi.dto.response;

import com.talents.orange.marvelapi.dto.request.marvel.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComicDTO {

    private Long comicId;
    private String title;
    private Float price;
    private String[] creators;
    private String isbn;
    private String description;
    private DayOfWeek dayOfDiscount;
    private boolean activeDiscount;

}
