package com.talents.orange.marvelapi.dto.request.marvel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
