package com.talents.orange.marvelapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserComicsDTO {

    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Date birthDate;
    private List<ComicDTO> comics;

    public void setDiscount(List<ComicDTO> comicDTO){
        for (ComicDTO comic: comicDTO){
            String isbn = comic.getIsbn();

            if(!isbn.equals("")) {
                char lastChar = isbn.charAt((isbn.length() - 1));

                if (lastChar == '0' || lastChar == '1') {
                    comic.setDayOfDiscount(DayOfWeek.MONDAY);
                } else if (lastChar == '2' || lastChar == '3') {
                    comic.setDayOfDiscount(DayOfWeek.TUESDAY);
                } else if (lastChar == '4' || lastChar == '5') {
                    comic.setDayOfDiscount(DayOfWeek.WEDNESDAY);
                } else if (lastChar == '6' || lastChar == '7') {
                    comic.setDayOfDiscount(DayOfWeek.THURSDAY);
                } else if (lastChar == '8' || lastChar == '9') {
                    comic.setDayOfDiscount(DayOfWeek.FRIDAY);
                }
            }

            LocalDate currentDate = LocalDate.now();
            comic.setActiveDiscount(currentDate.getDayOfWeek() == comic.getDayOfDiscount());
            if (comic.isActiveDiscount()) {comic.setPrice((float) (comic.getPrice() * 0.9));}
        }
    }

}
