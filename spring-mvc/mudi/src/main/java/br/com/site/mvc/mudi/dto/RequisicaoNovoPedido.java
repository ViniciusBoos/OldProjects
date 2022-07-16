package br.com.site.mvc.mudi.dto;

import br.com.site.mvc.mudi.model.Pedido;
import br.com.site.mvc.mudi.model.PedidoStatus;

import javax.validation.constraints.NotBlank;

public class RequisicaoNovoPedido {

    @NotBlank
    private String nome;

    @NotBlank
    private String urlProduto;

    @NotBlank
    private String urlImagem;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlProduto() {
        return urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pedido novoPedido(){
        Pedido pedido = new Pedido();
        pedido.setNome(this.nome);
        pedido.setUrlImagem(this.urlImagem);
        pedido.setUrlProduto(this.urlProduto);
        pedido.setDescricao(this.descricao);
        pedido.setStatus(PedidoStatus.AGUARDANDO);
        return pedido;
    }
}
