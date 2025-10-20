from langchain_core.runnables import RunnableSequence, RunnableLambda

# RunnableSequence - объединяет несколько действаий над объектов в цепь

if __name__ == '__main__':
    square = RunnableLambda(lambda x: x * x)
    plusTen = RunnableLambda(lambda x: x * 10)

    chain = square | plusTen
    result = chain.invoke(2)
    print(result)