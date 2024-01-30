package org.example.main;

import org.example.Entidades.Datos;
import org.example.Interface.OnePieceInterface;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LeerFuncionalFacil {
    private final OnePieceInterface opRepository;

    public LeerFuncionalFacil(OnePieceInterface opRepository) {
        this.opRepository = opRepository;
    }


    //Cantidad de capitulos de One piece
    public Optional<Integer> cantidadCapitulos() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        return Optional.of((int)opList.size());
    }
    //Cantidad de capitulos Canon
    public Optional<Integer> cantidadCapitulosCanon() {
        List<Datos> opList = opRepository.findAllOp();

        long cantidadCanon = opList.stream()
                .filter(canon -> "Canon".equals(canon.getEpisode_Type()))
                .count();

        System.out.println("La cantidad de capítulos canon es de: " + cantidadCanon);

        return Optional.of((int) cantidadCanon);
    }
    //Cantidad de capitulos Relleno
    public Optional<Integer> cantidadCapitulosRelleno()throws IOException{

        List<Datos> opList = opRepository.findAllOp();

        long cantidadRelleno =  opList.stream()
                .filter(relleno -> "Filler".equals(relleno.getEpisode_Type())).count();

        System.out.println("La cantidad de capitulos relleno es de: " + cantidadRelleno);

        return Optional.of((int) cantidadRelleno);
    }
    //Cantidad de arcos o temporadas
    public Optional<Integer> cantidadArcos() throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        int cantidadArcos = (int) opList.stream()
                .map(Datos::getArc_Name)
                .distinct()
                .count();

        System.out.println("La cantidad de arcos es de: " + cantidadArcos);

        return Optional.of((int) cantidadArcos);
    }
    //Año en el que salio el primer capitulo
    public Optional<Integer> primerCapitulo() throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        int primerCapitulo=  opList.stream()
                .map(Datos::getYear)
                .distinct()
                .findFirst().orElse(null);


        System.out.println("El año en que salio el capitulo es:"+primerCapitulo);
        return Optional.of((int) primerCapitulo);

    }

    //¿Que capitulo es donde la gente voto mas?
    public List<String> capituloVotadoMax() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        List<String> capituloMax =  opList.stream()
                .filter(capMax -> capMax.getTotal_Vote() == opList.stream()
                        .mapToInt(Datos::getTotal_Vote)
                        .max().orElse(0))
                .map(Datos::getEpisode_Name)
                .collect(Collectors.toList());

        System.out.println("el capitulo más votado es el:"+capituloMax);
        return capituloMax;
    }
    //Capitulos donde la gente voto menos
    public List<String> capituloVotadoMin() {
        List<Datos> opList = opRepository.findAllOp();
        List<String> capituloMin =  opList.stream()
                .filter(capMin -> capMin.getTotal_Vote() == opList.stream()
                        .mapToInt(Datos::getTotal_Vote)
                        .min().orElse(0))
                .map(Datos::getEpisode_Name)
                .collect(Collectors.toList());

        System.out.println("Lista de capitulos menos votado es el:"+capituloMin);
        return capituloMin;
    }

    //Cuales son los capitulos con el mayor promedio?
    public List<String> capitulosMayorPromedio()throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        List<String> capPromMaxList = opList.stream()
                .filter(d -> d.getAverage_Rating() == opList.stream()
                        .mapToDouble(Datos::getAverage_Rating)
                        .max().orElse(0))
                .map(Datos::getEpisode_Name)
                .collect(Collectors.toList());

        System.out.println("Los capítulos con el mayor promedio son: " + capPromMaxList);


        return capPromMaxList;
    }
    //Cuales son los capitulos con el menor promedio?
    public List<String> capitulosMenorPromedio()throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        List<String> capPromMinList = opList.stream()
                .filter(d -> d.getAverage_Rating() == opList.stream()
                        .mapToDouble(Datos::getAverage_Rating)
                        .min().orElse(0))
                .map(Datos::getEpisode_Name)
                .collect(Collectors.toList());

        System.out.println("Los capítulos con el mayor promedio son: " + capPromMinList);


        return capPromMinList;
    }

    public Optional<Integer> capitulosArco() throws IOException{

        String arco= "Water 7 Arc";

        List<Datos> opList = opRepository.findAllOp();
        int capWaterSeven = (int) opList.stream().
                filter(w7 -> arco.equals(w7.getArc_Name())).count();

        System.out.println("La cantidad de capitulos que tiene el arco Water 7 arc es de:"+ capWaterSeven);

        return Optional.of((int) capWaterSeven);
    }

    public Optional<Integer> capitulosDressrosa() throws IOException{
        List<Datos> opList = opRepository.findAllOp();
        int capDressrosa = (int) opList.stream().
                filter(w7 -> "Dressrosa Arc".equals(w7.getArc_Name())).count();

        System.out.println("La cantidad de capitulos que tiene el arco Dressrosa Arc es de:"+ capDressrosa);

        return Optional.of((int) capDressrosa);
    }







}
