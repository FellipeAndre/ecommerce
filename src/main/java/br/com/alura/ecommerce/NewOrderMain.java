package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;

public class NewOrderMain {

    public static void main(String[] args) {

        var producer = new KafkaProducer<String, String>(properties());
        var value = "15,54815,5481518151";
        /* record é um registro que deixara registrado no Kafka por um determinado tempo configurado*/
        var record = new ProducerRecord<String, String>("ECOMMERCE_NEW_ORDER", value, value);
        producer.send(record, (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println(data.topic() + ":::partition" + data.partition() + "/offset" + data.offset() + "/timeStamp" + data.timestamp());
        });

    }

    private static Properties properties() {

      var properties =  new Properties();
      /*  propriedade do servidor dop Kafka para produzir a mensagem */
      properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        /*  propriedade da chave e do Valor sendo serializados da Mensagem que será produzida*/
      properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

      return properties;
    }
}
