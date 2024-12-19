package com.subodhin.photoz.clone.web;

import com.subodhin.photoz.clone.model.Photo;
import com.subodhin.photoz.clone.service.PhotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {


    @Autowired
    private PhotosService photosService;



  //  private List<Photo> db = List.of(new Photo("01","heeloo.png"));

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/photos")
    public Iterable<Photo> get(){
       return photosService.get();
    }

    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable Integer id){
        Photo photo = photosService.get(id);
       if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);;
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete (@PathVariable Integer id){
        photosService.remove(id);
       /// if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);;

       // return photo;
    }

    @PostMapping("/photos")
    public Photo create (@RequestPart("data") MultipartFile file) throws IOException {

        Photo photo = new Photo();

//      photo.setId(UUID.randomUUID().toString());
//      photo.setFileName(file.getOriginalFilename());
//      photo.setData(file.getBytes());
        return photosService.save(file.getOriginalFilename(), file.getContentType(),file.getBytes());

    }




}
