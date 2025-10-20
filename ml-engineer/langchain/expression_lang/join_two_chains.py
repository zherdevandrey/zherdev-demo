from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import SystemMessagePromptTemplate, HumanMessagePromptTemplate, ChatPromptTemplate
from langchain_core.runnables import RunnableParallel, RunnablePassthrough
from langchain_ollama import OllamaLLM

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")

    template1 = HumanMessagePromptTemplate.from_template("Какие существуют инструменты в {topic}?")
    template2 = HumanMessagePromptTemplate.from_template("Как изучить {tools}?")

    prompt_template1 = ChatPromptTemplate.from_messages([template1])
    prompt_template2 = ChatPromptTemplate.from_messages([template2])


    chain1 = prompt_template1 | llm | StrOutputParser() | {'tools': RunnablePassthrough()}
    chain2 = prompt_template2 | llm
    chain = chain1 | chain2

    question = {"topic": "машинное обучение"}

    result = chain.invoke(question)

    print(result)