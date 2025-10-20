
from langchain_core.runnables import chain

@chain
def runnable1(x) : return x ** 2

@chain
def runnable2(x) : return x + 2


if __name__ == '__main__':
    chain = runnable1 | runnable2
    print(chain.invoke(4))