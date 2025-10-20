import copy
from langchain_community.embeddings import OllamaEmbeddings
import numpy as np

if __name__ == '__main__':
    embeddings = OllamaEmbeddings(model="nomic-embed-text")

    sentence1 = "любовь"
    sentence2 = "ненависть"

    vector1 = embeddings.embed_query(sentence1)
    vector2 = embeddings.embed_query(sentence2)

    norma1 = np.linalg.norm(vector1)
    norma2 = np.linalg.norm(vector2)

    cos = np.dot(vector1 / norma1, vector2 / norma2)

    angle = np.degrees(np.arccos(np.clip(cos, -1, 1)))

    print(f"cos={cos}, angle={angle}")