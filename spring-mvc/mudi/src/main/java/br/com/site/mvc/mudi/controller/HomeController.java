package br.com.site.mvc.mudi.controller;

import br.com.site.mvc.mudi.model.Pedido;
import br.com.site.mvc.mudi.model.PedidoStatus;
import br.com.site.mvc.mudi.repository.PedidoRepository;
import br.com.site.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String home(Model model, Principal principal) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("data").descending());

        List<Pedido> pedidos = pedidoRepository.findByStatus(PedidoStatus.ENTREGUE, pageable);
        model.addAttribute("pedidos", pedidos);
        return "home";
    }
}
