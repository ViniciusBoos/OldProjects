package br.com.site.mvc.mudi.controller;

import br.com.site.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.site.mvc.mudi.model.Pedido;
import br.com.site.mvc.mudi.model.User;
import br.com.site.mvc.mudi.repository.PedidoRepository;
import br.com.site.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public String pedido(RequisicaoNovoPedido requisicao) {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {

        if (result.hasErrors()) {
            return "pedido/formulario";
        }

        Pedido pedido = requisicao.novoPedido();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        pedido.setUser(user);
        pedidoRepository.save(pedido);
        return "redirect:/usuario/home";
    }
}
