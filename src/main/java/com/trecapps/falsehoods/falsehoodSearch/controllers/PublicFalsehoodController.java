package com.trecapps.falsehoods.falsehoodSearch.controllers;


import com.trecapps.falsehoods.falsehoodSearch.models.FullPublicFalsehood;
import com.trecapps.falsehoods.falsehoodSearch.models.PublicFalsehood;
import com.trecapps.falsehoods.falsehoodSearch.services.PublicFalsehoodService;
import com.trecapps.falsehoods.falsehoodSearch.services.SearchFalsehood;
import com.trecapps.falsehoods.falsehoodSearch.services.SearchPublicFalsehood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/Public")
public class PublicFalsehoodController {

    @Autowired
    PublicFalsehoodService publicFalsehoodService;

    @PostMapping("/Confirmed")
    public List<PublicFalsehood> searchFalsehoodByParams(@RequestBody SearchPublicFalsehood searchObj)
    {
        return publicFalsehoodService.getFalsehoodList(searchObj,1);
    }

    @PostMapping("/Rejected")
    public List<PublicFalsehood> searchRFalsehoodByParams(@RequestBody SearchPublicFalsehood searchObj)
    {
        return publicFalsehoodService.getFalsehoodList(searchObj,1);
    }

    @GetMapping("/SearchSubmitted")
    public List<PublicFalsehood> searchSubmittedFalsehoods(@RequestParam(value="size", defaultValue="20", required=false)int size,
                                                     @RequestParam(value="page", defaultValue="0", required=false)int page)
    {
        return publicFalsehoodService.getSubmittedFalsehoods(size, page);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FullPublicFalsehood> GetFalsehood(@PathVariable("id") BigInteger id)
    {
        return publicFalsehoodService.getFalsehoodById(id);
    }
}
