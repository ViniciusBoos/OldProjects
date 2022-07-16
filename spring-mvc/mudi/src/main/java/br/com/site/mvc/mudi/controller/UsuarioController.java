package br.com.site.mvc.mudi.controller;

import br.com.site.mvc.mudi.model.Pedido;
import br.com.site.mvc.mudi.model.PedidoStatus;
import br.com.site.mvc.mudi.model.User;
import br.com.site.mvc.mudi.repository.PedidoRepository;
import br.com.site.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String Userhome(Model model, Principal principal) {

        List<Pedido> pedidos = pedidoRepository.findAllByUsername(principal.getName());
        model.addAttribute("pedidos", pedidos);
        return "usuario/home";
    }

    @GetMapping("/{status}")
    public String status(@PathVariable("status") String status, Model model, Principal principal) {
        String usuario = principal.getName();
        User user = userRepository.findByUsername(usuario);
        List<Pedido> pedidos = pedidoRepository
                .findByStatusAndUser(PedidoStatus.valueOf(status.toUpperCase()), user);

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:usuario/home";
    }
}
