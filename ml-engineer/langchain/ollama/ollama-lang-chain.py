from langchain_classic.chains.conversational_retrieval.base import ConversationalRetrievalChain
from langchain_classic.memory import ConversationBufferMemory
from langchain_community.document_loaders import WebBaseLoader
from langchain_community.llms import Ollama
from langchain_text_splitters import RecursiveCharacterTextSplitter
from langchain_community.embeddings import OllamaEmbeddings
from langchain_community.vectorstores import Chroma


def setup_qa_system(url):
    print("# 1. Загрузка данных с вебсайта")
    print("Загружаем данные с вебсайта...")
    loader = WebBaseLoader(url)
    data = loader.load()

    print("# 2. Разделение текста на чанки")
    text_splitter = RecursiveCharacterTextSplitter(
        chunk_size=1000,
        chunk_overlap=200
    )
    # Исправление: split_documents вместо split_text
    splits = text_splitter.split_documents(data)

    print("# 3. Создание векторной базы данных")
    embeddings = OllamaEmbeddings(model="llama3.1:8b")
    vectorstore = Chroma.from_documents(
        documents=splits,
        embedding=embeddings
    )

    print("# 4. Настройка памяти")
    memory = ConversationBufferMemory(
        memory_key="chat_history",
        return_messages=True,
        output_key='answer'
    )

    print("# 5. Инициализация LLM")
    llm = Ollama(
        model="llama3.1:8b",
        temperature=0.1,
        num_predict=512,
        top_k=10,
        top_p=0.7,
        repeat_penalty=1.1,
        num_ctx=512,
        num_thread=8,
        system="Ты - полезный AI ассистент. Отвечай всегда на русском языке. Будь точным и информативным. Используй только русский язык для ответов."
    )

    print("# 6. Создание цепочки")
    qa_chain = ConversationalRetrievalChain.from_llm(
        llm=llm,
        retriever=vectorstore.as_retriever(
            search_type="similarity",
            search_kwargs={"k": 3}
        ),
        memory=memory,
        return_source_documents=True,
        verbose=False
    )

    return qa_chain


if __name__ == '__main__':
    url = "https://nashdom-smart.ru/about-us/"
    qa_system = setup_qa_system(url)

    print("# 7. Запуск чат-бота")
    print("Система готова к работе. Введите ваш вопрос:")

    while True:
        try:
            question = input("\nВведите запрос (или 'выход' для завершения): ")
            if question.lower() in ['выход', 'exit', 'quit']:
                break

            result = qa_system({"question": question})
            print(f"\nОтвет: {result['answer']}")

            if result.get('source_documents'):
                print(f"\nИсточники: {len(result['source_documents'])} документов")
                # Можно вывести первые несколько символов каждого источника для отладки
                for i, doc in enumerate(result['source_documents'][:2]):
                    print(f"  Документ {i + 1}: {doc.page_content[:100]}...")

            print("=" * 50)

        except KeyboardInterrupt:
            print("\nЗавершение работы...")
            break
        except Exception as e:
            print(f"Произошла ошибка: {e}")