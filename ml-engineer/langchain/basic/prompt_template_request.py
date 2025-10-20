from langchain_core.prompts import PromptTemplate
from langchain_core.runnables import RunnableSequence
from langchain_ollama import OllamaLLM

TEMPLATE = '''Напиши {style} ответ на тему {topic}'''

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")

    prompt_template = PromptTemplate(
        input_variables=["topic", "style"],
        template=TEMPLATE,
    )

    chain = RunnableSequence(first=llm, last=prompt_template)


    result = chain.invoke(
        {
            "topic": "будущее искусственного интеллекта",
            "style": "научно-популярный"
        }
    )

    print(result["text"])
