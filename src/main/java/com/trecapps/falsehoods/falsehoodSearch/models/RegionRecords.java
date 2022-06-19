package com.trecapps.falsehoods.falsehoodSearch.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Container(containerName = "regions", ru = "400")
@ToString
public class RegionRecords {
    Long regionId;

    //@PartitionKey
    byte partition;

    List<Record> records;
}
