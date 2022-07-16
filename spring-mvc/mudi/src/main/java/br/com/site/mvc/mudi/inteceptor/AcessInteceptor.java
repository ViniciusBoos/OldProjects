package br.com.site.mvc.mudi.inteceptor;


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class AcessInteceptor extends HandlerInterceptorAdapter{

    public static List<Acesso> acessos = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Acesso acesso = new Acesso();
        acesso.path = request.getRequestURI();
        acesso.tempo = LocalDateTime.now();
        request.setAttribute("acesso", acesso);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Acesso acesso =(Acesso) request.getAttribute("acesso");
        acesso.duracao = Duration.between(acesso.tempo, LocalDateTime.now());
        acessos.add(acesso);
    }

    public static class Acesso{

        private String path;
        private LocalDateTime tempo;
        private Duration duracao;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public LocalDateTime getTempo() {
            return tempo;
        }

        public void setTempo(LocalDateTime tempo) {
            this.tempo = tempo;
        }

        public Duration getDuracao() {
            return duracao;
        }

        public void setDuracao(Duration duracao) {
            this.duracao = duracao;
        }
    }
}
