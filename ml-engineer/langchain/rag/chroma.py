from langchain_community.embeddings import OllamaEmbeddings
from langchain_community.document_loaders import PyPDFLoader
from langchain_community.vectorstores import Chroma
from langchain_text_splitters import CharacterTextSplitter

if __name__ == '__main__':
    # Загрузка PDF
    pdf = PyPDFLoader("Introduction_to_Data_and_Data_Science.pdf")
    pages = pdf.load()

    # Разделение на чанки
    splitter = CharacterTextSplitter(separator=".", chunk_size=500, chunk_overlap=50)  # Добавлен overlap

    # Очистка текста
    for i, page in enumerate(pages):
        page.page_content = page.page_content.replace('\n', ' ')

    pages_split = splitter.split_documents(pages)

    # Инициализация эмбеддингов
    embeddings = OllamaEmbeddings(model="nomic-embed-text")

    # Создание векторной базы (исправленный синтаксис)
    vector_store = Chroma.from_documents(
        documents=pages_split,
        embedding=embeddings,  # Исправлено: embedding вместо embeddings
        persist_directory="./intro-to-ds-lecture"
    )

    print(f"✅ Векторная база создана с {len(pages_split)} документами")

    # Загрузка из директории
    vector_store_loaded = Chroma(
        persist_directory="./intro-to-ds-lecture",
        embedding_function=embeddings  # Исправлено: embedding_function вместо embeddings
    )

    # Тестирование поиска
    query = "что такое data science"
    results = vector_store_loaded.similarity_search(query, k=3)

    print(f"\n🔍 Результаты поиска для '{query}':")
    for i, doc in enumerate(results):
        print(f"{i + 1}. {doc.page_content[:100]}...")