package org.example.main;

import org.example.Entidades.Datos;
import org.example.Interface.OnePieceInterface;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LeerFuncionalDificil {
    private final OnePieceInterface opRepository;

    public LeerFuncionalDificil(OnePieceInterface opRepository) {
        this.opRepository = opRepository;
    }

    //Los primeros 10 capitulos del arco arabasta, pero solo los que son del año 2001, mencionando todos sus datos.
    public List<Datos> Arabasta() throws IOException {
        List<Datos> opList = opRepository.findAllOp();

        List<Datos> Arabasta = opList.stream()
                .filter(datos -> "Arabasta Arc".equals(datos.getArc_Name()))
                .filter(cap -> 2001 == cap.getYear())
                .limit(10)
                .collect(Collectors.toList());

        return Arabasta;
    }
    //Todos los capitulos del arco PunkHazard donde se limita a mostrar solo desde el capitulo 600 al 650
    public List<Datos> PunkHazard()throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        List<Datos> punkHazard = opList.stream()
                .filter(datos -> "Punk Hazard Arc".equals(datos.getArc_Name()))
                .filter(cap -> 600 >= cap.getEpisode_Number())
                .limit(50)
                .collect(Collectors.toList());

        return punkHazard;

    }
    //Todos los capitulos de ImpelDown pero que salieron en 2009 con votos arriba de 600
    public List<Datos> ImpelDown()throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        List<Datos> ImpelDown = opList.stream()
                .filter(datos -> "Impel Down Arc".equals(datos.getArc_Name()))
                .filter(cap -> 2009 == cap.getYear())
                .filter(cap -> 600 >= cap.getTotal_Vote())
                .collect(Collectors.toList());
        return ImpelDown;

    }
    //Todos los capitulos del arco Whole Cake Island Arc pero solo los que tienen votos entre 8-9
    public List<Datos> WCI()throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        List<Datos> WCI = opList.stream()
                .filter(datos -> "Whole Cake Island Arc".equals(datos.getArc_Name()))
                .filter(cap -> cap.getAverage_Rating() >= 8 && cap.getAverage_Rating() <= 9)
                .collect(Collectors.toList());
        return WCI;

    }
    //Todos los capitulos Canon del año 2005
    public List<Datos> Canon2005()throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        List<Datos> Canon2005 = opList.stream()
                .filter(datos -> "Canon".equals(datos.getEpisode_Type()))
                .filter(cap -> 2005 == cap.getYear())
                .collect(Collectors.toList());
        return Canon2005;

    }
    //Todos los capitulos del arco Marineford pero solos que tienen votos arriba de 1000
    public List<Datos> Marineford()throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        List<Datos> Marineford = opList.stream()
                .filter(datos -> "Marineford Arc".equals(datos.getArc_Name()))
                .filter(cap -> 1000 <= cap.getTotal_Vote())
                .collect(Collectors.toList());

        return Marineford;

    }

    public Optional<Integer> sumaVotosCapituloRelleno2003()throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        int sumaVotosCapituloRelleno2003 = opList.stream()
                .filter(datos -> "Filler".equals(datos.getEpisode_Type()))
                .filter(cap -> 2003 == cap.getYear())
                .mapToInt(Datos::getTotal_Vote)
                .sum();

        return Optional.of((int) sumaVotosCapituloRelleno2003);

    }

    public Optional<Integer> sumaVotosCapituloCanon2018()throws IOException{
        List<Datos> opList = opRepository.findAllOp();

        int sumaVotosCapituloCanon2018 = opList.stream()
                .filter(datos -> "Canon".equals(datos.getEpisode_Type()))
                .filter(cap -> 2018 == cap.getYear())
                .mapToInt(Datos::getTotal_Vote)
                .sum();

        return Optional.of((int) sumaVotosCapituloCanon2018);
    }

//capitulos del arco skypea pero solo los votos arriba de 600 y canon y los primeros 15
public List<Datos> Skypea()throws IOException{
    List<Datos> opList = opRepository.findAllOp();

    List<Datos> Skypea = opList.stream()
            .filter(datos -> "Skypiea Arc".equals(datos.getArc_Name()))
            .filter(cap -> 600 <= cap.getTotal_Vote())
            .filter(cap -> "Canon".equals(cap.getEpisode_Type()))
            .limit(15)
            .collect(Collectors.toList());
    return Skypea;

}


    }

