package br.com.site.mvc.mudi.dto;

import br.com.site.mvc.mudi.model.Oferta;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequisicaoNovaOferta {

    private Long pedidoId;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}")
    @NotNull
    private String data;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotNull
    private String valor;

    private String comentario;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Oferta toOferta() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Oferta oferta = new Oferta();
        oferta.setComentario(this.comentario);
        oferta.setValor(new BigDecimal(this.valor));
        oferta.setData(LocalDate.parse(this.data, formatter));

        return oferta;
    }
}
