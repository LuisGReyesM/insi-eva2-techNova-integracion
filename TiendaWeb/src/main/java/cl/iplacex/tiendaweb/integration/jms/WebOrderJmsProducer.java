package cl.iplacex.tiendaweb.integration.jms;

import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Componente de transporte encargado de la mensajería saliente (Outbound Messaging).
 * Establece la conexión con el servidor ActiveMQ para publicar los pedidos web.
 */

public class WebOrderJmsProducer {

    private static final Logger logger = LoggerFactory.getLogger(WebOrderJmsProducer.class);
    // Configuración de red para el Broker de mensajería Artemis
    private static final String BROKER_URL = "tcp://192.168.1.167:61616";
    // Nombre del destino (Queue) donde se depositan los XML de la tienda web
    private static final String QUEUE_NAME = "lre_web_pedidos";

    /**
     * Realiza el envío del mensaje de forma síncrona.
     * @param xml Cadena de texto que contiene el pedido serializado.
     */
    public void send(String xml) {
        try (ActiveMQConnectionFactory factory =
                     new ActiveMQConnectionFactory(BROKER_URL);
             JMSContext context = factory.createContext()) {

            Queue destination = context.createQueue(QUEUE_NAME);
            context.createProducer().send(destination, xml);

            logger.info("Mensaje enviado exitosamente a la cola: {}", QUEUE_NAME);

        } catch (Exception e) {
            // Captura errores de red o de autenticación con el Broker
            logger.error("Error al enviar mensaje", e);
        }
    }
}
