from langchain_core.messages import HumanMessage
from langchain_ollama import OllamaLLM
from langchain_core.output_parsers import CommaSeparatedListOutputParser

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")

    parser = CommaSeparatedListOutputParser()

    chain = llm | parser
    messages = [HumanMessage("Перечисли основные направления искусственного интеллекта через запятую:")]

    response = chain.invoke(messages)

    for message in response:
        print(message)