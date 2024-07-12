package api;

import com.theokanning.openai.completion.chat.ChatCompletionRequest.ChatCompletionRequestFunctionCall;
import com.theokanning.openai.completion.chat.*;

/**
 * The ChatGPT API client object
 * */
public class ChatGPT implements GPTInterface{

    String prompt;
    String category;
    String location;

    @Override
    public String getPrompt() {
        return prompt;
    }

    @Override
    public void setPrompt(String userPrompt) {
        this.prompt = userPrompt;
    }

    @Override
    public void pickCategory() {
        ;;
    }
    @Override
    public String getCategory() {
        return "a";
    }
    @Override
    public void pickVibe(String location) {
        System.out.println();
    }
    @Override
    public String getVibe() {
        return "a";
    }
}
