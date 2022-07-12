package com.trecapps.falsehoods.falsehoodSearch.controllers;

import com.trecapps.base.FalsehoodModel.models.Falsehood;
import com.trecapps.base.FalsehoodModel.models.FullFalsehood;
import com.trecapps.falsehoods.falsehoodSearch.services.FalsehoodService;
import com.trecapps.falsehoods.falsehoodSearch.services.SearchFalsehood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/Media")
public class MediaFalsehoodController {

    @Autowired
    FalsehoodService falsehoodService;

    @PostMapping("/Confirmed")
    public List<Falsehood> searchFalsehoodByParams(@RequestBody SearchFalsehood searchObj)
    {
        return falsehoodService.getFalsehoodList(searchObj, 1);
    }

    @PostMapping("/Rejected")
    public List<Falsehood> searchRFalsehoodByParams(@RequestBody SearchFalsehood searchObj)
    {
        return falsehoodService.getFalsehoodList(searchObj,-1);
    }

    @GetMapping("/SearchSubmitted")
    public List<Falsehood> searchSubmittedFalsehoods(@RequestParam(value="size", defaultValue="20", required=false)int size,
                                                     @RequestParam(value="page", defaultValue="0", required=false)int page)
    {
        return falsehoodService.getSubmittedFalsehoods(size, page);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FullFalsehood> GetFalsehood(@PathVariable("id") BigInteger id)
    {
        return falsehoodService.getFalsehoodById(id);
    }
}
