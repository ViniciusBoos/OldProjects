package br.com.site.mvc.mudi.repository;

import br.com.site.mvc.mudi.model.Pedido;
import br.com.site.mvc.mudi.model.PedidoStatus;
import br.com.site.mvc.mudi.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username")
    List<Pedido> findAllByUsername(@Param("username") String username);

    @Cacheable("pedidos")
    List<Pedido> findByStatus(PedidoStatus status, Pageable page);
    List<Pedido> findByUser(User user);

    List<Pedido> findByStatusAndUser(PedidoStatus status, User user);
}
