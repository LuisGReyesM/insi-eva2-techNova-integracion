package cl.iplacex.tiendaweb.integration.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * DTO que representa la ficha técnica y comercial de un producto en formato XML.
 * Contiene la información necesaria para identificar el artículo en los sistemas de inventario.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductoXml {

    private String sku;
    private String nombre;
    private Long precioLista;
    private CategoriaXml categoria;

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Long getPrecioLista() { return precioLista; }
    public void setPrecioLista(Long precioLista) { this.precioLista = precioLista; }

    public CategoriaXml getCategoria() { return categoria; }
    public void setCategoria(CategoriaXml categoria) { this.categoria = categoria; }
}
