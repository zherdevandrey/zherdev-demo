from langchain_classic.chains.conversational_retrieval.base import ConversationalRetrievalChain
from langchain_classic.memory import ConversationBufferMemory
from langchain_community.document_loaders import WebBaseLoader
from langchain_community.llms.ollama import Ollama
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
    splits = text_splitter.split_text("7 лет управления и комплексного обслуживания МКД")

    print("# 3. Создание векторной базы данных")
    embeddings = OllamaEmbeddings(model="mistral")
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
        model="mistral",
        base_url="http://localhost:11434",  # Важно указать правильный URL
        temperature=0.1,
        num_predict=512,  # Ограничиваем длину ответа
        top_k=20,  # Уменьшаем для скорости
        top_p=0.7,
        repeat_penalty=1.1,
        num_ctx=2048,  # Уменьшаем контекст
        num_thread=8,  # Больше потоков
        system="Ты - полезный AI ассистент. Отвечай всегда на русском языке. Будь точным и информативным. Используй только русский язык для ответов."
    )

    print("# 6. Создание цепочки")
    qa_chain = ConversationalRetrievalChain.from_llm(
        llm=llm,
        retriever=vectorstore.as_retriever(
            search_type="similarity",
            search_kwargs={"k": 3}  # Меньше документов для скорости
        ),
        memory=memory,
        return_source_documents=True,
        max_tokens_limit=1000,     # Лимит токенов
        verbose=False              # Отключаем verbose для скорости
    )

    return qa_chain

if __name__ == '__main__':
    url = "https://nashdom-smart.ru/about-us/"
    qa_system = setup_qa_system(url)

    print("# 7. Создание запроса")

    while True:
        question = input("Введите запрос: ")
        result = qa_system({"question": question})
        print(f"Ответ: {result['answer']}")
        print(f"Источники: {len(result['source_documents'])} документов")
        print("=" * 50)