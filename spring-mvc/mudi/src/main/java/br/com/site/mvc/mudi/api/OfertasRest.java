package br.com.site.mvc.mudi.api;

import br.com.site.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.site.mvc.mudi.model.Oferta;
import br.com.site.mvc.mudi.model.Pedido;
import br.com.site.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public Oferta criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {

        Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());

        if(!pedidoBuscado.isPresent()) {
            return null;
        }

        Pedido pedido = pedidoBuscado.get();

        Oferta oferta = requisicao.toOferta();
        oferta.setPedido(pedido);
        pedido.getOfertas().add(oferta);
        pedidoRepository.save(pedido);

        return oferta;
    }
}
