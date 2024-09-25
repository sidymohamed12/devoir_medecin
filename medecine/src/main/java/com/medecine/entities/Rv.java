package com.medecine.entities;

import lombok.Data;

@Data
public class Rv {
    private int id;
    private String date;
    private String heure;
    private Medecin medecin;
}
