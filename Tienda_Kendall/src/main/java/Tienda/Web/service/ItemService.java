package Tienda.Web.service;
import java.util.List;
import Tienda.Web.domain.Item;

public interface ItemService {
    public List<Item> gets();
    
    public Item get(Item item);
    
    public void delete(Item item);
    
    public void save(Item item);
    
    public void update(Item item);
    
    public void facturar();
    
    public double getTotal();
}
