package Tienda.Web.dao;
import Tienda.Web.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaDao extends JpaRepository<Venta, Long>{
    
}
