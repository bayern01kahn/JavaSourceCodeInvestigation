package justin.kafka;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer{
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static KafkaProducer<String, String> kafkaProducer;

    public static void main(String[] args) {
        KafkaProducer producer = getKafkaProducer();
//        for (int i = 0; i < 100; i++) {
//            //同步发送
//            producer.send(new ProducerRecord<>("test", Integer.toString(i)));
//
//            //异步发送
//            /*producer.send(new ProducerRecord<String, String>("test", Integer.toString(i + 100)), new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata metadata, Exception exception) {
//                    if (exception != null) {
//                        exception.printStackTrace();
//                    }
//                }
//            });*/
//        }
        //String event = "[{\"header\":{\"type\":\"eventResponse\",\"uuid\":\"c27ab5a0-70db-11e7-ac06-1d9b975468cf\"},\"order\":{\"event\":{\"type\":\"datapointEvent\",\"uuid\":\"c27ab5a0-70db-11e7-ac06-1d9b975468cf\",\"details\":{\"base_type\":\"decimal\",\"oem\":\"0dfc7900\",\"scope\":\"user\",\"closed\":\"false\",\"echo\":\"false\",\"oem_model\":\"MTEST\",\"display_name\":\"TEST FEED PROP\",\"value\":\"1\",\"dsn\":\"dsn-fuxhBRYInJ\",\"property_name\":\"on_off\",\"direction\":\"false\"},\"dpl_event\":{\"metadata\":{\"oem_id\":\"0dfc7900\",\"oem_model\":\"MTEST\",\"dsn\":\"dsn-fuxhBRYInJ\",\"property_name\":\"on_off\",\"display_name\":\"TEST FEED PROP\",\"base_type\":\"decimal\",\"event_type\":\"datapoint\"},\"datapoint\":{\"id\":\"c27ab5a0-70db-11e7-ac06-1d9b975468cf\",\"updated_at\":\"2017-07-25T01:51:22Z\",\"created_at\":\"2017-07-25T01:51:22Z\",\"echo\":false,\"closed\":false,\"value\":\"1\",\"metadata\":{\"foo\":\"bar\",\"quux\":\"thud\"},\"discarded\":false,\"scope\":\"user\",\"direction\":\"input\"},\"timestamp\":\"2017-07-25T01:51:22.000+0000\"},\"metadata\":{\"quux\":\"thud\",\"foo\":\"bar\"},\"timestamp\":1500947482106},\"multi_subject_rule_ids\":[],\"fired_rule_ids\":[],\"fired_diagnostic_rule_ids\":[],\"non_fired_rule_ids\":[],\"non_fired_diagnostic_rule_ids\":[],\"no_data_rule_ids\":[]},\"evaluation_metadata\":{\"type\":\"action\",\"times\":[1603419355404],\"retries\":0,\"errors\":{\"1603419355404\":\"ARE-200\"}},\"errors\":[]},{\"header\":{\"type\":\"eventResponse\",\"uuid\":\"c27ab5a0-70db-11e7-ac06-1d9b975468cf\"},\"order\":{\"event\":{\"type\":\"datapointEvent\",\"uuid\":\"c27ab5a0-70db-11e7-ac06-1d9b975468cf\",\"details\":{\"base_type\":\"decimal\",\"oem\":\"0dfc7900\",\"scope\":\"user\",\"closed\":\"false\",\"echo\":\"false\",\"oem_model\":\"MTEST\",\"display_name\":\"TEST FEED PROP\",\"value\":\"1\",\"dsn\":\"dsn-fuxhBRYInJ\",\"property_name\":\"on_off\",\"direction\":\"false\"},\"dpl_event\":{\"metadata\":{\"oem_id\":\"0dfc7900\",\"oem_model\":\"MTEST\",\"dsn\":\"dsn-fuxhBRYInJ\",\"property_name\":\"on_off\",\"display_name\":\"TEST FEED PROP\",\"base_type\":\"decimal\",\"event_type\":\"datapoint\"},\"datapoint\":{\"id\":\"c27ab5a0-70db-11e7-ac06-1d9b975468cf\",\"updated_at\":\"2017-07-25T01:51:22Z\",\"created_at\":\"2017-07-25T01:51:22Z\",\"echo\":false,\"closed\":false,\"value\":\"1\",\"metadata\":{\"foo\":\"bar\",\"quux\":\"thud\"},\"discarded\":false,\"scope\":\"user\",\"direction\":\"input\"},\"timestamp\":\"2017-07-25T01:51:22.000+0000\"},\"metadata\":{\"quux\":\"thud\",\"foo\":\"bar\"},\"timestamp\":1500947482106},\"multi_subject_rule_ids\":[],\"fired_rule_ids\":[],\"fired_diagnostic_rule_ids\":[],\"non_fired_rule_ids\":[],\"non_fired_diagnostic_rule_ids\":[],\"no_data_rule_ids\":[]},\"evaluation_metadata\":{\"type\":\"action\",\"times\":[1603419355476],\"retries\":0,\"errors\":{\"1603419355476\":\"ARE-200\"}},\"errors\":[]}]";
        String event = "{\"metadata\":{\"oem_id\":\"0dfc7900\",\"oem_model\":\"MTEST\",\"dsn\":\"dsn-fuxhBRYInJ\",\"property_name\":\"on_off\",\"display_name\":\"TEST FEED PROP\",\"base_type\":\"decimal\",\"event_type\":\"datapoint\"},\"datapoint\":{\"id\":\"c27ab5a0-70db-11e7-ac06-1d9b975468cf\",\"updated_at\":\"2017-07-25T01:51:22Z\",\"created_at\":\"2017-07-25T01:51:22Z\",\"echo\":false,\"closed\":false,\"value\":\"1\",\"metadata\":{\"foo\":\"bar\",\"quux\":\"thud\"},\"discarded\":false,\"scope\":\"user\",\"direction\":\"input\"}";

        String message1 = "{\n"
            + "    \"metadata\": {\n"
            + "        \"oem_id\": \"0dfc7900\", \n"
            + "        \"oem_model\": \"linuxex1\", \n"
            + "        \"dsn\": \"TESTDSN_701885_000\", \n"
            + "        \"property_name\": \"Blue_LED\", \n"
            + "        \"display_name\": \"Blue Led\", \n"
            + "        \"base_type\": \"decimal\", \n"
            + "        \"resource_tags\": [ ], \n"
            + "        \"event_type\": \"datapoint\"\n"
            + "    }, \n"
            + "    \"datapoint\": {\n"
            + "        \"id\": \"01505d00-688e-11e9-bf4b-2f5d32e8659a\", \n"
            + "        \"created_at_from_device\": null, \n"
            + "        \"updated_at\": \"2017-07-25T01:51:22Z\", \n"
            + "        \"created_at\": \"2017-07-25T01:51:22Z\", \n"
            + "        \"user_id\": null, \n"
            + "        \"user_uuid\": null, \n"
            + "        \"echo\": false, \n"
            + "        \"closed\": false, \n"
            + "        \"value\": 0, \n"
            + "        \"discarded\": false, \n"
            + "        \"scope\": \"user\", \n"
            + "        \"direction\": \"input\", \n"
            + "        \"metadata\": {\n"
            + "            \"foo\": \"bar\", \n"
            + "            \"quux\": \"thud\"\n"
            + "        }\n"
            + "    }\n"
            + "}";

        String message2 = "{  \"metadata\" : {   \"oem_id\" : \"oem-mwIMh\",  \"oem_model\" : \"ledevb-test\",  \"dsn\" : \"dsn-eXIjcVVbom1\",          \"event_type\" : \"connectivity\"        },        \"timestamp\" : 1501015937000,        \"connection\" : {          \"event_time\" : \"2017-07-25T20:52:17Z\",          \"user_uuid\" : \"e03c39a6-79b1-11e5-94b1-0ea626ed4846\",          \"status\" : \"Onfline\",          \"metadata\" : {            \"oem_id\" : \"oem-mwIMh\",            \"oem_model\" : \"ledevb-test\",   \"dsn\" : \"dsn-eXIjcVVbom1\",   \"event_type\" : \"connectivity\"          }        }      }";

        String actionMessage = "{\"header\":{\"type\":\"evaluationRequest\",\"uuid\":\"01505d00-688e-11e9-bf4b-2f5d32e8659a\"},\"order\":{\"event\":{\"type\":\"datapointEvent\",\"uuid\":\"01505d00-688e-11e9-bf4b-2f5d32e8659a\",\"details\":{\"base_type\":\"decimal\",\"oem\":\"0dfc7900\",\"scope\":\"user\",\"closed\":\"false\",\"echo\":\"false\",\"oem_model\":\"linuxex1\",\"display_name\":\"Blue Led\",\"value\":\"0\",\"dsn\":\"TESTDSN_701885_000\",\"property_name\":\"Blue_LED\",\"direction\":\"false\"},\"dpl_event\":{\"metadata\":{\"oem_id\":\"0dfc7900\",\"oem_model\":\"linuxex1\",\"dsn\":\"TESTDSN_701885_000\",\"property_name\":\"Blue_LED\",\"display_name\":\"Blue Led\",\"base_type\":\"decimal\",\"event_type\":\"datapoint\"},\"datapoint\":{\"id\":\"01505d00-688e-11e9-bf4b-2f5d32e8659a\",\"updated_at\":\"2017-07-25T01:51:22Z\",\"created_at\":\"2017-07-25T01:51:22Z\",\"echo\":false,\"closed\":false,\"value\":\"0\",\"metadata\":{\"foo\":\"bar\",\"quux\":\"thud\"},\"discarded\":false,\"scope\":\"user\",\"direction\":\"input\"},\"timestamp\":1500947482000},\"metadata\":{\"quux\":\"thud\",\"foo\":\"bar\"},\"timestamp\":1556329472819},\"multi_subject_rule_ids\":[],\"fired_rule_ids\":[395],\"fired_diagnostic_rule_ids\":[],\"non_fired_rule_ids\":[],\"non_fired_diagnostic_rule_ids\":[],\"no_data_rule_ids\":[],\"subject_values\":{\"DATAPOINT(TESTDSN_701885_000,Blue_LED)\":\"false\"}},\"evaluation_metadata\":{\"type\":\"action\",\"times\":[1603779260029],\"retries\":0,\"errors\":{\"1603779260029\":\"ARE-200\"}},\"errors\":[],\"cycle\":0}";

        producer.send(new ProducerRecord<>("DataPipelineStaging", message1));
//        producer.send(new ProducerRecord<>("rules-evaluation-kafka-topic-local", message2));
//        producer.send(new ProducerRecord<>("rules-action-kafka-topic-local", actionMessage));


        producer.close();
    }

    /**
     * 获取kafka客户端
     *
     * @return
     */
    public static KafkaProducer getKafkaProducer() {
        if (kafkaProducer == null) {
            synchronized (Producer.class) {
                try {
                    if (kafkaProducer == null) {
                        kafkaProducer = initKafkaProducer();
                    }
                } catch (Exception e) {
                    logger.error("KafkaClient创建失败...." + kafkaProducer, e);
                }
            }
        }
        return kafkaProducer;
    }

    /**
     * 初始化kafka客户端
     *
     * @return
     */
    private static KafkaProducer initKafkaProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 0异步 1同步(主) -1/all同步(主,副本)
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        //失败允许重试的次数
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        //每个批次发送多大的数据
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
        //定时发送, 达到1ms发送
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //缓存的大小
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
        //自定义分区
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer producer = new KafkaProducer(props);
        return producer;
    }

}