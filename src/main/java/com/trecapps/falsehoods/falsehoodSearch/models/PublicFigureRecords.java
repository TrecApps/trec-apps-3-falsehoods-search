package com.trecapps.falsehoods.falsehoodSearch.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Container(containerName = "figures", ru = "400")
@ToString
public class PublicFigureRecords {
    Long figureId;

    //@PartitionKey
    byte partition;

    List<Record> records;
}
