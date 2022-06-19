package com.trecapps.falsehoods.falsehoodSearch.models;

import lombok.*;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Record
{
    String recordType;
    String details;
    Date made;
    Long userId;
    String specifics;
}
