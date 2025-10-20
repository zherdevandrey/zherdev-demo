import copy
from langchain_community.embeddings import OllamaEmbeddings

from langchain_community.document_loaders import PyPDFLoader
from langchain_text_splitters import CharacterTextSplitter

if __name__ == '__main__':
    pdf = PyPDFLoader("Introduction_to_Data_and_Data_Science.pdf")
    pages = pdf.load()

    splitter = CharacterTextSplitter(separator=".", chunk_size=500, chunk_overlap=0)

    for i, page in enumerate(pages):
        page.page_content = page.page_content.replace('\n', ' ')

    pages_split = splitter.split_documents(pages)
    pages_split_content = [page.page_content for page in pages_split]

    for page in pages_split:
        print(page)

    embeddings = OllamaEmbeddings(model="nomic-embed-text")
    embeddings.embed_documents(pages_split_content)

