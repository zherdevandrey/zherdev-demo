from langchain_core.messages import HumanMessage
from langchain_ollama import OllamaLLM
from langchain_core.output_parsers import StrOutputParser

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")

    chain = llm | StrOutputParser()
    messages = [HumanMessage("Расскажи о искусственном интеллекте")]

    response = chain.invoke(messages)

    print("Response :" + response)