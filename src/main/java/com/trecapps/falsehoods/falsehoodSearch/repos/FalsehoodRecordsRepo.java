package com.trecapps.falsehoods.falsehoodSearch.repos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trecapps.base.InfoResource.models.Record;
import com.trecapps.falsehoods.falsehoodSearch.config.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class FalsehoodRecordsRepo //extends CosmosRepository<FalsehoodRecords, BigInteger>
{
    @Autowired
    StorageClient client;

    ObjectMapper mapper = new ObjectMapper();



    public List<Record> retrieveRecords(BigInteger id) throws JsonProcessingException {
        String name = "Falsehood-Records-" + id + ".json";

        String contents = client.getContents(name, "Falsehood").getBody();

        return mapper.readValue(contents, new TypeReference<List<Record>>() {
        });
    }

}
