package org.example.main;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j2;
import org.example.Entidades.Datos;
import org.example.Interface.OnePieceInterface;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class mapear implements OnePieceInterface {
    private static final String SEPARADOR = ",";
    private final String ruta;

    public mapear(String ruta) {
        this.ruta = ruta;
    }


    public Datos mapeo(String [] args){
        int Episode_Number = (args[0]!="")?Integer.parseInt(args[0]):null;
        String Episode_Name = args[1];
        String Episode_Type = args[2];
        String Arc_Name = args[3];
        int Year = (args[4]!="")?Integer.parseInt(args[4]):null;
        int Total_Vote = (args[5]!="")?Integer.parseInt(args[5]):null;
        String convertir=args[6].replace(",", "");
        int Average_Rating=(int)Double.parseDouble(convertir);
        return new Datos(Episode_Number, Episode_Name,Episode_Type,Arc_Name,Year,Total_Vote, Average_Rating);
    }
    @Override
    public List<Datos> findAllOp() {
        log.info("Obteniendo datos");
        Path path = Path.of(ruta);
        try (CSVReader reader = new CSVReader(new FileReader(path.toString()))) {
            List<String[]> lines = reader.readAll();
            return lines.stream()
                    .map(this::mapeo)
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


}
