package com.trecapps.falsehoods.falsehoodSearch.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Container(containerName = "pFalsehoods", ru = "400")
@ToString
public class PublicFalsehoodRecords {
    @Id
    BigInteger falsehoodId;

    //@PartitionKey
    byte partition;

    List<Record> records;
}
