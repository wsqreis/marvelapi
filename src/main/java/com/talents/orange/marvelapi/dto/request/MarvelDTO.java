package com.talents.orange.marvelapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarvelDTO {

    private com.talents.orange.marvelapi.dto.request.marvel.Data data;

}
