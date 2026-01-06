package cl.iplacex.tiendaweb.integration.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * DTO que representa los datos de destino para la logística de entrega.
 * Define la estructura XML necesaria para que los sistemas de despacho procesen el pedido.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DireccionDespachoXml {
    private String calleYNumero;
    private String comuna;

    public String getCalleYNumero() { return calleYNumero; }
    public void setCalleYNumero(String calleYNumero) { this.calleYNumero = calleYNumero; }

    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }
}
