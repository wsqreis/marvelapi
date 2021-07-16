package com.talents.orange.marvelapi.dto.request.marvel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Long id;
    private String title;
    private String description;
    private String isbn;
    private Price[] prices;
    private Creators creators;

}
