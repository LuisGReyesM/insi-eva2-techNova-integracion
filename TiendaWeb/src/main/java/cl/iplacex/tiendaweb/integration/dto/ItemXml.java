package cl.iplacex.tiendaweb.integration.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * DTO que representa una línea individual dentro del detalle del pedido.
 * Relaciona un producto específico con la cantidad adquirida para la integración XML.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemXml {

    private ProductoXml producto;
    private int cantidad;

    public ProductoXml getProducto() { return producto; }
    public void setProducto(ProductoXml producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}