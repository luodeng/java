package com.roden.java.database.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * 向ES增删改查
 * 安装参考：<a href="http://www.oschina.net/translate/elasticsearch-getting-started">ElasticSearch 简单入门  </a>
 * 
 */
public class IndexTest {
    public static void main(String[] argv) {
        Settings settings = ImmutableSettings.settingsBuilder()
                // 指定集群名称
                .put("cluster.name", "elasticsearch")
                // 探测集群中机器状态
                .put("client.transport.sniff", true).build();
        // 创建客户端，所有的操作都由客户端开始，这个就好像是JDBC的Connection对象 用完记得要关闭
        Client client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("192.168.1.216", 9300));

        String json = ESUtils.toJson(new LogModel());
        // 在这里创建我们要索引的对象
        // 添加到索引
        IndexResponse responseAdd = client.prepareIndex("twitter", "tweet")
                // 必须为对象单独指定ID
                .setId("1")
                .setSource(json)
                .execute()
                .actionGet();
        // 多次index这个版本号会变
        System.out.println("response.version():" + responseAdd.getVersion());

        // 从索引查询
        GetResponse response = client.prepareGet("twitter", "tweet", "1")
                .execute().actionGet();
        System.out.println("response.getId():" + response.getId());
        System.out.println("response.getSourceAsString():" + response.getSourceAsString());

        // 从索引删除
        DeleteResponse responseDelete = client.prepareDelete("twitter", "tweet", "1")
                .execute().actionGet();
        System.out.println(responseDelete.getId());
        System.out.println(ESUtils.toJson(responseDelete.getHeaders()));

        client.close();
    }
}