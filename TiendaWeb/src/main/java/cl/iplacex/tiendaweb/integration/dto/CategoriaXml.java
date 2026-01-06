package cl.iplacex.tiendaweb.integration.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * DTO que representa la categoría de un producto dentro del ecosistema de integración.
 * Define la clasificación técnica y comercial necesaria para el sistema receptor.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriaXml {
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}