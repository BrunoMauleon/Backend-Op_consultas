package org.example.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.Entidades.Datos;
import org.example.Interface.OnePieceInterface;
import org.example.main.mapear;
import org.example.main.LeerFuncionalDificil;
import org.example.main.LeerFuncionalFacil;
import org.example.main.LeerFuncionalMediana;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/OnePiece")
public class OnePieceController {

    OnePieceInterface csvReader = new mapear("src/main/java/org/example/LeerCsv/op.csv");

    LeerFuncionalFacil leerFuncional = new LeerFuncionalFacil(csvReader);
    LeerFuncionalMediana leerFuncionalMediana = new LeerFuncionalMediana(csvReader);
    LeerFuncionalDificil leerFuncionalDificil =new LeerFuncionalDificil(csvReader);

    @GetMapping("/Total")
    public ResponseEntity<Integer> cantidadCapitulos() throws IOException {
        return leerFuncional.cantidadCapitulos()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/TotalCapCanon")
    public ResponseEntity<Integer> cantidadCapitulosCanon() throws IOException {
        return leerFuncional.cantidadCapitulosCanon()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/TotalCapRelleno")
    public ResponseEntity<Integer> cantidadCapitulosRelleno() throws IOException {
        return leerFuncional.cantidadCapitulosRelleno()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/TotalDeArco")
    public ResponseEntity<Integer> cantidadArcos() throws IOException {
        return leerFuncional.cantidadArcos()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/LanzamientoAño")
    public ResponseEntity<Integer> PrimerCapitulo() throws IOException {
        return leerFuncional.primerCapitulo()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/CapituloVotoMax")
    public ResponseEntity<List<String>> capituloVotadoMax() throws IOException {
        List<String> result = leerFuncional.capituloVotadoMax();
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/CapituloVotoMin")
    public ResponseEntity<List<String>> capituloVotadoMin() throws IOException {
        List<String> result = leerFuncional.capituloVotadoMin();
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/CapituloPromedioMax")
    public ResponseEntity<List<String>> capituloPromedioMax() throws IOException {
        List<String> result = leerFuncional.capitulosMayorPromedio();
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/CapituloPromedioMin")
    public ResponseEntity<List<String>> capituloPromedioMin() throws IOException {
        List<String> result = leerFuncional.capitulosMenorPromedio();
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/CantidadCapitulosArco")
    public ResponseEntity<Integer> capitulosArco() throws IOException {
        return leerFuncional.capitulosArco()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/CantidadCapitulosDressrosa")
    public ResponseEntity<Integer> capitulosDressrosa() throws IOException {
        return leerFuncional.capitulosDressrosa()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    //----------------DIFICULTAD: Mediana
    @GetMapping("/arcoMayorNumeroCap")
    public ResponseEntity<String> arcoMayorNumeroCap() throws IOException {
        return leerFuncionalMediana.arcoMayorNumeroCap()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/arcoMenorNumeroCap")
    public ResponseEntity<String> arcoMenorNumeroCap() throws IOException {
        return leerFuncionalMediana.arcoMenorNumeroCap()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/capitulo1016")
    public ResponseEntity<String> capitulo1016() throws IOException {
        return leerFuncionalMediana.capitulo1016()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/capitulo920")
    public ResponseEntity<Integer> capitulo920() throws IOException {
        return leerFuncionalMediana.capitulo920()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/capitulo220")
    public ResponseEntity<String> capitulo220() throws IOException {
        return leerFuncionalMediana.capitulo220()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/capitulo68")
    public ResponseEntity<String> capitulo68() throws IOException {
        return leerFuncionalMediana.capitulo68()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/mejorPromedioArco")
    public ResponseEntity<String> mejorPromedioArco() throws IOException {
        Map.Entry<String, Double> result = leerFuncionalMediana.mejorPromedioArco();

        if (!result.getKey().isEmpty()) {
            String mensaje = "El arco con mejor promedio es: " + result.getKey() + ", promedio: " + result.getValue();
            return ResponseEntity.ok(mensaje);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/mejorAñoOnePiece")
    public ResponseEntity<String> mejorAñoOnePiece() throws IOException {
        Map.Entry<Integer, Double> result = leerFuncionalMediana.mejorAñoOnePiece();

            String mensaje = "El mejor año de one piece es: " + result.getKey() + ", promedio: " + result.getValue();
            return ResponseEntity.ok(mensaje);
        }
    @GetMapping("/peorAñoOnePiece")
    public ResponseEntity<String> peorAñoOnePiece() throws IOException {
        Map.Entry<Integer, Double> result = leerFuncionalMediana.peorAñoOnePiece();

        String mensaje = "El peor año de one piece es: " + result.getKey() + ", promedio: " + result.getValue();
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/Capitulos9_10Prom")
    public ResponseEntity<List<String>>  Capitulos9_10Prom() throws IOException {
        List<String> result = leerFuncionalMediana.Capitulos9_10Prom();
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//--------------------------------------
@GetMapping("/arabasta")
public ResponseEntity<List<Datos>> arabasta() throws IOException{
    List<Datos> arabasta = leerFuncionalDificil.Arabasta();

    if (!arabasta.isEmpty()) {
        return ResponseEntity.ok(arabasta);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @GetMapping("/PunkHazard")
    public ResponseEntity<List<Datos>> PunkHazard() throws IOException{
        List<Datos> PunkHazard = leerFuncionalDificil.PunkHazard();

        if (!PunkHazard.isEmpty()) {
            return ResponseEntity.ok(PunkHazard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ImpelDown")
    public ResponseEntity<List<Datos>> ImpelDown() throws IOException{
        List<Datos> ImpelDown = leerFuncionalDificil.ImpelDown();

        if (!ImpelDown.isEmpty()) {
            return ResponseEntity.ok(ImpelDown);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/WCI")
    public ResponseEntity<List<Datos>> WCI() throws IOException{
        List<Datos> wci = leerFuncionalDificil.WCI();

        if (!wci.isEmpty()) {
            return ResponseEntity.ok(wci);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Canon2005")
    public ResponseEntity<List<Datos>> Canon2005() throws IOException{
        List<Datos> Canon2005 = leerFuncionalDificil.Canon2005();

        if (!Canon2005.isEmpty()) {
            return ResponseEntity.ok(Canon2005);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Marineford")
    public ResponseEntity<List<Datos>> Marineford() throws IOException{
        List<Datos> Marineford = leerFuncionalDificil.Marineford();

        if (!Marineford.isEmpty()) {
            return ResponseEntity.ok(Marineford);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/sumaVotosCapituloRelleno2003")
    public ResponseEntity<Integer> sumaVotosCapituloRelleno2003() throws IOException{
        return leerFuncionalDificil.sumaVotosCapituloRelleno2003()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/sumaVotosCapituloCanon2018")
    public ResponseEntity<Integer> sumaVotosCapituloCanon2018() throws IOException{
        return leerFuncionalDificil.sumaVotosCapituloCanon2018()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/Skypea")
    public ResponseEntity<List<Datos>> Skypea() throws IOException{
        List<Datos> Skypea = leerFuncionalDificil.Skypea();

        if (!Skypea.isEmpty()) {
            return ResponseEntity.ok(Skypea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }






}

