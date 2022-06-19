package com.trecapps.falsehoods.falsehoodSearch.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class FalsehoodUncommonTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;

    @ManyToOne
    @JoinColumn(name = "falsehood_id")
    Falsehood falsehood;

    String tag;
}
