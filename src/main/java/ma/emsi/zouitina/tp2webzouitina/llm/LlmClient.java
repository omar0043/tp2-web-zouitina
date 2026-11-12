package ma.emsi.zouitina.tp2webzouitina.llm;

import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.time.Duration;

@Dependent
public class LlmClient implements Serializable {

    private transient Assistant assistant;
    private transient ChatMemory memoire;
    private String historique = "[]";

    public void setSystemRole(String role) {
        initialiser();
        memoire.clear();

        if (role != null && !role.isBlank()) {
            memoire.add(SystemMessage.from(role));
        }

        sauvegarderHistorique();
    }

    public String chat(String question) {
        initialiser();
        String reponse = assistant.chat(question);
        sauvegarderHistorique();
        return reponse;
    }

    private void initialiser() {
        if (assistant != null) {
            return;
        }

        String cle = System.getenv("GEMINI_KEY");
        if (cle == null || cle.isBlank()) {
            throw new IllegalStateException("La variable GEMINI_KEY est absente.");
        }

        var modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite")
                .timeout(Duration.ofSeconds(90))
                .maxRetries(0)
                .build();

        memoire = MessageWindowChatMemory.withMaxMessages(20);
        ChatMessageDeserializer.messagesFromJson(historique).forEach(memoire::add);

        assistant = AiServices.builder(Assistant.class)
                .chatModel(modele)
                .chatMemory(memoire)
                .build();
    }

    private void sauvegarderHistorique() {
        historique = ChatMessageSerializer.messagesToJson(memoire.messages());
    }
}