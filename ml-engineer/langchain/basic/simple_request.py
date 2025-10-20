from langchain_ollama import OllamaLLM

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")
    response = llm.invoke("Расскажи о искусственном интеллекте")
    print(response)