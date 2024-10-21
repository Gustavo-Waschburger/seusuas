package br.com.csrc.seusuas.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static com.mongodb.client.model.Filters.eq;

@Component
@Configuration
public class MongoClientConnection {
    private static final String CONNECTION_STRING = "mongodb+srv://user:passwd@clustershow.lfmm4.mongodb.net/?retryWrites=true&w=majority&appName=ClusterShow";
    private static final String DATABASE_NAME = "seusuas";
    private static final String COLLECTION_NAME = "acolhido";//Poderá ser excluido após o funcionamento do Servvice e Controller

    public MongoClientConnection() {
    }

    public MongoClient getMongoClient() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                .serverApi(serverApi)
                .build();

        return MongoClients.create(settings);
    }
    @Bean
    public MongoDatabase getDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(DATABASE_NAME);
    }
//Estes exemplos de CRUD podem ser excluídos após o funcionameno do Service e do Controller
    private static void createAcolhido(MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        Document acolhido = new Document("nome", "João Pedro")
                .append("dataNascimento", "1980-06-15")
                .append("cpf", "123.456.789-00")
                .append("endereco", new Document("rua", "Rua A")
                        .append("numero", 123)
                        .append("cidade", "São Paulo")
                        .append("estado", "SP"))
                .append("contato", new Document("telefone", "(11) 98765-4321")
                        .append("email", "joao.silva@email.com"))
                .append("dataEntrada", "2024-10-15")
                .append("status", "ativo");

        collection.insertOne(acolhido);
        System.out.println("Acolhido inserido: " + acolhido.toJson());
    }

    private static void readAcolhido(MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        Document acolhido = collection.find(eq("nome", "João da Silva")).first();
        System.out.println("Acolhido encontrado: " + acolhido.toJson());
    }

    private static void updateAcolhido(MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        collection.updateOne(eq("nome", "João da Silva"), new Document("$set", new Document("status", "inativo")));
        System.out.println("Acolhido atualizado: status mudado para inativo");
    }

    private static void deleteAcolhido(MongoDatabase database) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        collection.deleteOne(eq("nome", "João da Silva"));
        System.out.println("Acolhido deletado: João da Silva");
    }
}
