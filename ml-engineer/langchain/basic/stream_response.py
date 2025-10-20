from kubernetes.stream import stream
from langchain_core.messages import SystemMessage, HumanMessage
from langchain_ollama import OllamaLLM

if __name__ == '__main__':
    llm = OllamaLLM(
        model="llama3.1:8b",
        temperature=0.5,
        num_predict=1000
    )

    prompt = [
        SystemMessage("Ты - полезный AI ассистент. Отвечай всегда на русском языке. Будь точным и информативным. Используй только русский язык для ответов."),
        HumanMessage("Кто такой Пушкин?")
    ]

    stream_response = llm.stream(prompt)
    for chunk in stream_response:
        print(chunk, end="", flush=True)
    print("\n" + "-" * 50)