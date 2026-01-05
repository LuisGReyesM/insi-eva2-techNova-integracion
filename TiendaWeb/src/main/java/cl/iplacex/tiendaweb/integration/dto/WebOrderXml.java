package cl.iplacex.tiendaweb.integration.dto;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebOrderXml {

    private String idPedido;
    private String fecha;
    private Long total;

    private CompradorXml comprador = new CompradorXml(); // inicializamos para no dar null
    private DireccionDespachoXml direccionDespacho = new DireccionDespachoXml();

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<ItemXml> items;

    // =====================
    // GETTERS & SETTERS
    // =====================

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getIdPedido() { return idPedido; }
    public void setIdPedido(String idPedido) { this.idPedido = idPedido; }

    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }

    public CompradorXml getComprador() { return comprador; }
    public void setComprador(CompradorXml comprador) { this.comprador = comprador; }

    public DireccionDespachoXml getDireccionDespacho() { return direccionDespacho; }
    public void setDireccionDespacho(DireccionDespachoXml direccionDespacho) { this.direccionDespacho = direccionDespacho; }

    public List<ItemXml> getItems() { return items; }
    public void setItems(List<ItemXml> items) { this.items = items; }

    // Métodos auxiliares para compatibilidad con tu código antiguo
    public void setRutCliente(String rut) {
        if (this.comprador == null) this.comprador = new CompradorXml();
        this.comprador.setRut(rut);
    }

    public String getRutCliente() {
        return (this.comprador != null) ? this.comprador.getRut() : null;
    }
}
