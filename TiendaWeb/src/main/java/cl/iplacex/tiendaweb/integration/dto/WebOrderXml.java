package cl.iplacex.tiendaweb.integration.dto;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "pedido")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebOrderXml {

    private String idPedido;
    private String rutCliente;
    private Long total;

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

