from langchain_core.prompts import SystemMessagePromptTemplate, HumanMessagePromptTemplate, ChatPromptTemplate
from langchain_core.runnables import RunnableParallel, RunnablePassthrough
from langchain_ollama import OllamaLLM

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")

    messages = {
        "domain": "искусственного интеллекта",
        "question": "Какие существуют основные подходы в машинном обучении?"
    }

    system_template = SystemMessagePromptTemplate.from_template(
        "Ты - эксперт в области {domain}. Отвечай подробно и профессионально."
    )
    human_template = HumanMessagePromptTemplate.from_template(
        "{question}"
    )

    prompt_template = ChatPromptTemplate.from_messages([
        system_template,
        human_template
    ])

    chain = RunnableParallel({
        "original_input": RunnablePassthrough(),
        "ai_response": prompt_template | llm
    })


    result = chain.invoke(messages)

    print("Оригинальный запрос:", result["original_input"])

    print("=" * 100)

    print("Ответ ИИ:", result["ai_response"])

