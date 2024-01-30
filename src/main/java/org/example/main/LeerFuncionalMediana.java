package org.example.main;

import org.example.Entidades.Datos;
import org.example.Interface.OnePieceInterface;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LeerFuncionalMediana {
    private final OnePieceInterface opRepository;

    public LeerFuncionalMediana(OnePieceInterface opRepository) {
        this.opRepository = opRepository;
    }

    //El nombre del arco con mayor numero de capitulos
    public Optional<String> arcoMayorNumeroCap() throws IOException {
        List<Datos> opList = opRepository.findAllOp();
        String arcoMayorNumeroCap = opList.stream()
                .collect(Collectors.groupingBy(Datos::getArc_Name, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("El arco con mayor capitulos es: " + arcoMayorNumeroCap);

        return Optional.of((String) arcoMayorNumeroCap);
    }
//El arco con el menor numero de capitulos
    public Optional<String> arcoMenorNumeroCap() throws IOException {
        List<Datos> opList = opRepository.findAllOp();
        String arcoMenorNumeroCap = opList.stream()
                .collect(Collectors.groupingBy(Datos::getArc_Name, Collectors.counting()))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("El arco con menor capitulos es: " + arcoMenorNumeroCap);

        return Optional.of((String) arcoMenorNumeroCap);
    }

    //Del capitulo 1016, dime el nombre del capitulo
    public Optional<String> capitulo1016() throws IOException {
        List<Datos> opList = opRepository.findAllOp();
        String cap1016 = opList.stream()
                .filter(cap -> 1016 == cap.getEpisode_Number())
                .map(Datos::getEpisode_Name)
                .findFirst()
                .orElse("No encontrado");

        System.out.println("El nombre del capitulo 1016 es " + cap1016);
        return Optional.of((String) cap1016);
    }
    //Del capitulo 920, en que año se estreno
    public Optional<Integer> capitulo920()throws IOException {
        List<Datos> opList = opRepository.findAllOp();
        int cap920 = opList.stream()
                .filter(cap -> 920 == cap.getEpisode_Number())
                .mapToInt(Datos::getYear)
                .findFirst()
                .orElse(0);

        System.out.println("El año en el que salio el capitulo 920 fue en el año: " + cap920);
        return Optional.of((int) cap920);
    }
//Del capitulo 220, menciona el arco al que pertenece
    public Optional<String> capitulo220() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        String cap220 = opList.stream()
                .filter(cap -> 220 == cap.getEpisode_Number())
                .map(Datos::getArc_Name)
                .findFirst()
                .orElse("");

        System.out.println("El capitulo 220 pertenece al arco: " + cap220);
        return Optional.of((String) cap220);
    }
//Del capitulo 68, menciona si este es Canon o Filter (Relleno)
    public Optional<String> capitulo68() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        String cap68 = opList.stream()
                .filter(cap -> 68 == cap.getEpisode_Number())
                .map(Datos::getEpisode_Type)
                .findFirst().orElse("");

        if (cap68 == "Canon") {
            System.out.println("El capitulo 68 es Canon");
        } else {
            System.out.println("El capitulo 68 es relleno");
        }

        return Optional.of((String) cap68);
    }
    //El mejor promedio de un arco
    public Map.Entry<String, Double> mejorPromedioArco() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        Map.Entry<String, Double> mejorPromedioArco = opList.stream()
                .collect(Collectors.groupingBy(Datos::getArc_Name,
                        Collectors.averagingDouble(Datos::getAverage_Rating)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(new AbstractMap.SimpleEntry<>("", 0.0));

        System.out.println("El arco con mejor promedio es:" + mejorPromedioArco);
        return mejorPromedioArco;
    }
    //El mejor año de one piece (El mejor promedio de un año)
    public Map.Entry<Integer, Double> mejorAñoOnePiece() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        Map.Entry<Integer, Double> mejorAñoOnePiece = opList.stream()
                .collect(Collectors.groupingBy(Datos::getYear,
                        Collectors.averagingDouble(Datos::getAverage_Rating)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(new AbstractMap.SimpleEntry<>(0, 0.0));

        System.out.println("El amejor año de One piece es:" + mejorAñoOnePiece);
        return mejorAñoOnePiece;
    }
    //El peor año de one piece (El peor promedio de un año)
    public Map.Entry<Integer, Double> peorAñoOnePiece() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        Map.Entry<Integer, Double> peorAñoOnePiece = opList.stream()
                .collect(Collectors.groupingBy(Datos::getYear,
                        Collectors.averagingDouble(Datos::getAverage_Rating)))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(new AbstractMap.SimpleEntry<>(0, 0.0));

        System.out.println("El amejor año de One piece es:" + peorAñoOnePiece);
        return peorAñoOnePiece;
    }
    //Todos los capitulos valorados del 9 al 10
    public List<String> Capitulos9_10Prom() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        List<String> cap9_10= opList.stream()
                .collect(Collectors.groupingBy(Datos::getEpisode_Name,
                        Collectors.averagingDouble(Datos::getAverage_Rating)))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= 9.0)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        System.out.println("Los capitulos con promedio de 9 a 10 es de: " + "\n"+ cap9_10);
        return cap9_10;
    }
}
