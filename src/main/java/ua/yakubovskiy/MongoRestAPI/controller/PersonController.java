package ua.yakubovskiy.MongoRestAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.yakubovskiy.MongoRestAPI.dto.PersonDetailsDto;
import ua.yakubovskiy.MongoRestAPI.dto.PersonRequestDetailsDto;
import ua.yakubovskiy.MongoRestAPI.dto.RestResponse;
import ua.yakubovskiy.MongoRestAPI.service.PersonService;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/upload")
    private RestResponse uploadFile(@RequestParam("file") MultipartFile file) {
        personService.uploadFile(file);
        return new RestResponse("Public persons have been successfully uploaded to the database");
    }

    @GetMapping("/_searchByFullName")
    public PersonDetailsDto getPeopleByFullName(@RequestBody PersonRequestDetailsDto dto) {
        return null;
    }

}
