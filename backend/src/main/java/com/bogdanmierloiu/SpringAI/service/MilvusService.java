package com.bogdanmierloiu.SpringAI.service;

import io.milvus.client.MilvusClient;
import io.milvus.common.clientenum.ConsistencyLevelEnum;
import io.milvus.grpc.DataType;
import io.milvus.grpc.MutationResult;
import io.milvus.param.R;
import io.milvus.param.collection.CreateCollectionParam;
import io.milvus.param.collection.DropCollectionParam;
import io.milvus.param.collection.FieldType;
import io.milvus.param.dml.InsertParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MilvusService {


    private final VectorStore vectorStore;
    private final MilvusClient milvusClient;
    private final OpenAiEmbeddingsService openAiEmbeddingsService;
    private final EmbeddingClient embeddingClient;

    public void saveEmbeddings(String content, List<float[]> embeddings) {
        List<Document> documents = embeddings.stream()
                .map(embeddingArray -> {
                    Map<String, Object> fields = new HashMap<>();
                    fields.put("embedding", embeddingArray);
                    return new Document(UUID.randomUUID().toString(), content, fields);
                })
                .toList();
        try {
            vectorStore.add(documents);
            documents.forEach(doc -> log.info("Embedding saved successfully: {}", doc.getId()));
        } catch (Exception e) {
            log.error("Failed to save embeddings", e);
        }
    }

    public void addInVectorStore(String content, String textToEmbedAndAdd) {
        Document document = new Document(UUID.randomUUID().toString(), content, Map.of("embedding", textToEmbedAndAdd));
        try {
            vectorStore.add(List.of(document));
            log.info("Embedding saved successfully: {}", document.getId());
        } catch (Exception e) {
            log.error("Failed to save embeddings", e);
        }
    }

    public void addWithMilvusClient(String text) throws Exception {

        FieldType idField = FieldType.newBuilder()
                .withName("id")
                .withDataType(DataType.Int64)
                .withPrimaryKey(true)
                .withAutoID(true)
                .build();

        Map<String, String> typeParams = new HashMap<>();
        typeParams.put("dim", String.valueOf(1536));
        FieldType vectorField = FieldType.newBuilder()
                .withName("embedding")
                .withDataType(DataType.FloatVector)
                .withDimension(1536)
                .withTypeParams(typeParams)
                .build();

        FieldType partitionKeyField = FieldType.newBuilder()
                .withName("partition_key")
                .withDataType(DataType.VarChar)
                .withMaxLength(128)
                .withPartitionKey(true)
                .build();

        CreateCollectionParam createCollectionParam = CreateCollectionParam.newBuilder()
                .withCollectionName("default_agent")
                .withShardsNum(1)
                .withDescription("Collection for default agent")
                .addFieldType(idField)
                .addFieldType(partitionKeyField)
                .addFieldType(vectorField)
                .withPartitionsNum(1)
                .withConsistencyLevel(ConsistencyLevelEnum.STRONG)
                .withDatabaseName("default")
                .build();
        milvusClient.createCollection(createCollectionParam);

        List<Float> embeddingList = openAiEmbeddingsService.getEmbeddingList(text);

        InsertParam.Field idFieldInsert = InsertParam.Field.builder()
                .name("id")
                .values(List.of(0L))  // Assuming an ID, as it's auto-generated
                .build();

        InsertParam.Field embeddingField = InsertParam.Field.builder()
                .name("embedding")
                .values(List.of(embeddingList))  // Insert float vector as List<Float>
                .build();

        InsertParam.Field partitionKeyFieldInsert = InsertParam.Field.builder()
                .name("partition_key")
                .values(List.of("partition_key_value"))  // Add a suitable partition key value
                .build();

        InsertParam insertParam = InsertParam.newBuilder()
                .withDatabaseName("default")
                .withCollectionName("default_agent")
                .withFields(List.of(embeddingField, partitionKeyFieldInsert))
                .build();
        R<MutationResult> insert = milvusClient.insert(insertParam);
        log.info("Insert result: {}", insert);
    }

    public List<Document> similaritySearch(String query) {
        try {
            return vectorStore.similaritySearch(
                    SearchRequest.defaults()
                            .withQuery(query)
                            .withTopK(4)
                            .withSimilarityThreshold(0.5f));
        } catch (Exception e) {
            log.error("Failed to search embeddings", e);
            return List.of();
        }
    }

    public void dropCollection(String collectionName, String databaseName) {
        milvusClient.dropCollection(DropCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .withDatabaseName(databaseName)
                .build());
    }

}
