package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trecapps.falsehoods.falsehoodSearch.config.StorageClient;
import com.trecapps.falsehoods.falsehoodSearch.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RegionRecordsRepo {

    @Autowired
    StorageClient client;

    ObjectMapper mapper = new ObjectMapper();


    public List<Record> retrieveRecords(long id) throws JsonProcessingException {
        String name = "Region-Records-" + id;

        String contents = client.getContents(name, "Resource").getBody();

        return mapper.readValue(contents, new TypeReference<List<Record>>() {
        });
    }


}
