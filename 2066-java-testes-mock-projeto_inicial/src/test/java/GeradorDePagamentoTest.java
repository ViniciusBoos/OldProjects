import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.service.GeradorDePagamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class GeradorDePagamentoTest {

    private GeradorDePagamento geradorDePagamento;

    @Mock
    private PagamentoDao pagamentoDao;

    @Mock
    private Clock clock;

    @Captor
    private ArgumentCaptor<Pagamento> captor;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.geradorDePagamento = new GeradorDePagamento(pagamentoDao, clock);
    }

    @Test
    void deveriaCriarPagamentoParaVencedorDoLeilao() {
        Leilao leilao = leilao();
        Lance lanceVencedor = leilao.getLanceVencedor();

        LocalDate date = LocalDate.of(2020, 12, 7);

        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();

        Mockito.when(clock.instant()).thenReturn(instant);
        Mockito.when(clock.getZone()).thenReturn(ZoneId.systemDefault());

        geradorDePagamento.gerarPagamento(lanceVencedor);

        Mockito.verify(pagamentoDao).salvar(captor.capture());

        Pagamento pagamento = captor.getValue();

        Assertions.assertEquals(date.plusDays(1), pagamento.getVencimento());
        Assertions.assertEquals(lanceVencedor.getValor(), pagamento.getValor());
        Assertions.assertFalse(pagamento.getPago());
        Assertions.assertEquals(lanceVencedor.getUsuario(), pagamento.getUsuario());
        Assertions.assertEquals(leilao, pagamento.getLeilao());
    }

    private Leilao leilao() {

        Leilao leilao = new Leilao("Celular",
                new BigDecimal("500"),
                new Usuario("Fulano"));

        Lance segundo = new Lance(new Usuario("Ciclano"),
                new BigDecimal("900"));

        leilao.propoe(segundo);
        leilao.setLanceVencedor(segundo);

        return leilao;

    }
}
