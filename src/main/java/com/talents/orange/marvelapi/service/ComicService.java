package com.talents.orange.marvelapi.service;

import com.talents.orange.marvelapi.dto.request.MarvelDTO;
import com.talents.orange.marvelapi.dto.request.marvel.Item;
import com.talents.orange.marvelapi.dto.request.marvel.Price;
import com.talents.orange.marvelapi.dto.request.marvel.Result;
import com.talents.orange.marvelapi.entity.Comic;
import com.talents.orange.marvelapi.entity.User;
import com.talents.orange.marvelapi.repository.ComicRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class ComicService {

    @Autowired
    private final ComicRepository comicRepository;

    @Autowired
    private final UserService userService;

    public String saveMarvelComic(Long id, Long comicId){
        Long ts = System.currentTimeMillis();
        String publicKey = "85e8e4a44643e841556c97f5c03ab169";
        String privateKey = "207e2dc2587d1366ed133f792fdf1da048c90530";
        String toHash = ts + privateKey + publicKey;

        String hash = DigestUtils.md5DigestAsHex(toHash.getBytes());

        String url = "https://gateway.marvel.com/v1/public/comics/"
                + comicId.toString()
                + "?ts=" + ts
                + "&apikey=" + publicKey
                + "&hash=" + hash;

        RestTemplate restTemplate;
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        MarvelDTO marvelDTO = restTemplate.getForObject(url, MarvelDTO.class);

        User user = userService.findById(id);

        if(marvelDTO != null) {
            Result[] result = marvelDTO.getData().getResults();

            String title = result[0].getTitle();

            Price[] priceArray = result[0].getPrices();
            Float price = priceArray[0].getPrice();

            Item[] creatorsArray = result[0].getCreators().getItems();
            String[] creators = Arrays.stream(creatorsArray).map(Item::toString).toArray(String[]::new);

            String isbn = result[0].getIsbn();

            String description = result[0].getDescription();

            Comic comicToSave = new Comic(comicId, title, price, creators, isbn, description, user);
            comicRepository.save(comicToSave);

            return title + " comic registered with ID:" + comicId;
        }
        return "An error occurred while registering the comic with ID:" + comicId;
    }
}
