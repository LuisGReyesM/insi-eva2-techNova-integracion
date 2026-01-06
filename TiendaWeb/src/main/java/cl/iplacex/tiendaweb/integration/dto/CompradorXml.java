package cl.iplacex.tiendaweb.integration.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * DTO que encapsula la información del cliente que realiza el pedido.
 * Representa la sección de identidad del mensaje XML generado para la integración.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CompradorXml {
    private String nombre;
    private String rut;
    private String telefono;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
