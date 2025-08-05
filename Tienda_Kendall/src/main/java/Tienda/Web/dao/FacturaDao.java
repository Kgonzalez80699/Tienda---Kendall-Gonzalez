package Tienda.Web.dao;
import Tienda.Web.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository <Factura, Long>{
    
}
