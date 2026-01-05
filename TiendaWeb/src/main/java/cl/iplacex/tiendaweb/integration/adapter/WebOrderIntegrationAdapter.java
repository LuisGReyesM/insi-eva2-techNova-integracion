package cl.iplacex.tiendaweb.integration.adapter;

import cl.iplacex.tiendaweb.integration.dto.*;
import cl.iplacex.tiendaweb.integration.jms.WebOrderJmsProducer;
import cl.iplacex.tiendaweb.ext.carrito.domain.Carrito;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class WebOrderIntegrationAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebOrderIntegrationAdapter.class);
    private final WebOrderJmsProducer producer = new WebOrderJmsProducer();

    public void publish(WebOrderXml order) {

        try {
            ServletRequestAttributes attr =
                    (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attr.getRequest();
            HttpSession session = request.getSession(false);

            logger.info("Iniciando integración WEB → XML");

            /* ============================
             * FECHA
             * ============================ */
            order.setFecha(LocalDateTime.now().toString());

            /* ============================
             * COMPRADOR
             * ============================ */
            CompradorXml comprador = new CompradorXml();
            comprador.setNombre(request.getParameter("comprador.nombre"));
            comprador.setRut(request.getParameter("comprador.rut"));
            comprador.setTelefono(request.getParameter("comprador.telefono"));
            order.setComprador(comprador);

            /* ============================
             * DIRECCIÓN DESPACHO
             * ============================ */
            DireccionDespachoXml direccion = new DireccionDespachoXml();
            direccion.setCalleYNumero(request.getParameter("direccionDespacho.calleYNumero"));
            direccion.setComuna(request.getParameter("direccionDespacho.comuna"));
            order.setDireccionDespacho(direccion);

            /* ============================
             * ITEMS DESDE CARRITO
             * ============================ */
            if (session != null) {
                Carrito carrito = (Carrito) session.getAttribute("carrito");

                if (carrito != null && carrito.getLineasPedido() != null) {

                    var items = carrito.getLineasPedido().stream().map(lp -> {

                        ProductoXml producto = new ProductoXml();
                        producto.setSku(lp.getProducto().getSku());
                        producto.setNombre(lp.getProducto().getNombre());
                        producto.setPrecioLista(lp.getProducto().getPrecioFinal());

                        ItemXml item = new ItemXml();
                        item.setProducto(producto);
                        item.setCantidad(lp.getCantidad());

                        return item;

                    }).collect(Collectors.toList());

                    order.setItems(items);
                }
            }

            /* ============================
             * MARSHALLING JAXB
             * ============================ */
            JAXBContext context = JAXBContext.newInstance(WebOrderXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(order, writer);

            logger.info("XML generado:\n{}", writer);
            System.out.println(writer.toString());

            /* ============================
             * ENVÍO A ACTIVEMQ
             * ============================ */
            producer.send(writer.toString());

        } catch (Exception e) {
            logger.error("Error crítico en integración WEB", e);
        }
    }
}
