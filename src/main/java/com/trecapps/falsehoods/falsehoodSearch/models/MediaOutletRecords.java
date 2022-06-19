package com.trecapps.falsehoods.falsehoodSearch.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Container(containerName = "outlets", ru = "400")
@ToString
public class MediaOutletRecords {
    Integer outletId;

    //@PartitionKey
    byte partition;

    List<Record> records;
}
