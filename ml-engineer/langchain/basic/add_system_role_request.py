from kubernetes.stream import stream
from langchain_core.messages import SystemMessage, HumanMessage
from langchain_ollama import OllamaLLM

if __name__ == '__main__':
    llm = OllamaLLM(
        model="llama3.1:8b",
        temperature=0.5,
        num_predict=100
    )

    messages = [
        SystemMessage("Ты - полезный AI ассистент. Отвечай всегда на русском языке. Будь точным и информативным. Используй только русский язык для ответов."),
        HumanMessage("Кто такой Пушкин?")
    ]

    response = llm.invoke(messages)
    print(response)