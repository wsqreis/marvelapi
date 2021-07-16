package com.talents.orange.marvelapi.controller;

import com.talents.orange.marvelapi.service.ComicService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comic")
public class ComicController {

    @Autowired
    private final ComicService comicService;

    @GetMapping("/{id}/{comicId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createComic(@PathVariable Long id, @PathVariable Long comicId){
        return comicService.saveMarvelComic(id, comicId);
    }

}
