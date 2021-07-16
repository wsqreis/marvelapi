package com.talents.orange.marvelapi.dto.request.marvel;

import lombok.*;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    private  Result[] results;

}
