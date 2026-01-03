package cl.iplacex.tiendaweb.integration.jms;

import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebOrderJmsProducer {

    private static final Logger logger = LoggerFactory.getLogger(WebOrderJmsProducer.class);
    private static final String BROKER_URL = "tcp://192.168.1.167:61616";
    private static final String QUEUE_NAME = "lre_web_pedidos";

    public void send(String xml) {
        try (ActiveMQConnectionFactory factory =
                     new ActiveMQConnectionFactory(BROKER_URL);
             JMSContext context = factory.createContext()) {

            Queue destination = context.createQueue(QUEUE_NAME);
            context.createProducer().send(destination, xml);

            logger.info("Mensaje enviado exitosamente a la cola: {}", QUEUE_NAME);

        } catch (Exception e) {
            logger.error("Error al enviar mensaje", e);
        }
    }
}
