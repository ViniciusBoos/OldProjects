package br.com.site.pessoa.controller;

import br.com.site.pessoa.dto.EstadoDto;
import br.com.site.pessoa.model.Cidade;
import br.com.site.pessoa.model.Estado;
import br.com.site.pessoa.model.Pais;
import br.com.site.pessoa.repository.CidadeRepository;
import br.com.site.pessoa.repository.EstadoRepository;
import br.com.site.pessoa.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnderecoController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/cidadesDirecionadas")
    public List<Cidade> listCidadeDirecionada(@RequestParam String codigo) {

        return cidadeRepository.findAllCidadesInState(codigo);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/estadosDirecionados")
    public List<Estado> listEstadoDirecionado(@RequestParam String codigo) {

        return estadoRepository.findAllEstadosInCountry(Long.parseLong(codigo));
    }


    @CrossOrigin("http://localhost:3000")
    @GetMapping("/paises")
    @Cacheable(value = "PaisesCache")
    public List<Pais> listPais() {

        return paisRepository.findAll();
    }


}
