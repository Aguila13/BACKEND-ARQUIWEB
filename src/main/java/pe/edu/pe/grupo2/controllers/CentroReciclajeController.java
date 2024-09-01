package pe.edu.pe.grupo2.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.pe.grupo2.dtos.CentroReciclajeDTO;
import pe.edu.pe.grupo2.serviceinterfaces.ICentroReciclajeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/centros-de-reciclaje")
public class CentroReciclajeController {

    @Autowired
    private ICentroReciclajeService tA;
    @GetMapping
    public List<CentroReciclajeDTO> listar() {

        return tA.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x, CentroReciclajeDTO.class);
        }).collect(Collectors.toList());

    @PostMapping
    public void insertar(@RequestBody VehicleDTO dto) {
            ModelMapper m = new ModelMapper();
            Vehicle ve = m.map(dto, Vehicle.class);
            tA.insert(ve);

    }
}
