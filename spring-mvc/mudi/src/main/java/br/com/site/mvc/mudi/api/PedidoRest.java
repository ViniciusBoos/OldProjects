package br.com.site.mvc.mudi.api;

import br.com.site.mvc.mudi.model.Pedido;
import br.com.site.mvc.mudi.model.PedidoStatus;
import br.com.site.mvc.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("aguardando")
    public List<Pedido> getPedidosAguardando() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());

        return pedidoRepository.findByStatus(PedidoStatus.AGUARDANDO, pageable);
    }
}
