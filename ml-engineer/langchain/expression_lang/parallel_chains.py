from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import SystemMessagePromptTemplate, HumanMessagePromptTemplate, ChatPromptTemplate
from langchain_core.runnables import RunnableParallel, RunnablePassthrough
from langchain_ollama import OllamaLLM

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")

    template1 = HumanMessagePromptTemplate.from_template("Какие курсы посмотреть для изучения языка {language}?")
    template2 = HumanMessagePromptTemplate.from_template("Какие книги прочитать для изучения языка {language}?")

    prompt_template1 = ChatPromptTemplate.from_messages([template1])
    prompt_template2 = ChatPromptTemplate.from_messages([template2])


    chain1 = prompt_template1 | llm | StrOutputParser()
    chain2 = prompt_template2 | llm | StrOutputParser()

    chain = RunnableParallel({"cources":chain1,"books":chain2})

    result = chain.invoke("Java")

    print(result)