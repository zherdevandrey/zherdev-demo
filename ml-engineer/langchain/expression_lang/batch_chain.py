from langchain_core.prompts import SystemMessagePromptTemplate, HumanMessagePromptTemplate, ChatPromptTemplate
from langchain_ollama import OllamaLLM

if __name__ == '__main__':
    llm = OllamaLLM(model="llama3.1:8b")

    messages = [
        {"domain": "Медицина","question": "Лечение рака"},
        {"domain": "Космос", "question": "Полет на марс"},
        {"domain": "Поэзия", "question": "Произведения Пушкина"},
    ]

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

    chain = prompt_template | llm

    results = chain.batch(
        inputs = messages,
        config = {
            "max_concurrency": 3,
        }
    )

    for result in results:
        print("Answer: " + result)
        print("=" * 50)
