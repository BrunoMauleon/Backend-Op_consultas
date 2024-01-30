package org.example.Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Datos {
    private int Episode_Number;
    private String Episode_Name;
    private String Episode_Type;
    private String Arc_Name;
    private int Year;
    private int Total_Vote;
    private double Average_Rating;


}
