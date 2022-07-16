package br.com.site.mvc.mudi.api;

import br.com.site.mvc.mudi.inteceptor.AcessInteceptor;
import br.com.site.mvc.mudi.inteceptor.AcessInteceptor.Acesso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("acessos")
public class AcessoRest {

    @GetMapping
    private List<Acesso> getAcessos() {
        return AcessInteceptor.acessos;
    }
}
