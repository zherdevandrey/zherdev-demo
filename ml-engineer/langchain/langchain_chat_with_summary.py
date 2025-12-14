from langchain_core.messages import AIMessage, HumanMessage, RemoveMessage, SystemMessage
from langchain_core.prompt_values import PromptValue
from langchain_ollama import OllamaLLM
from langgraph.constants import START, END
from langgraph.graph import StateGraph, MessagesState
from typing import Literal

MESSAGES = "messages"

class State(MessagesState):
    summary:str

def ask_question(state: State) -> State:
    print_state(state, "ask_question")
    question = "Какой у вас вопрос?"
    state[MESSAGES].extend([
        AIMessage(question),
        HumanMessage(input(question))
    ])
    return state

def ask_another_question(state: State) -> State:
    question = "У вас еще есть вопросы?"
    return State(messages = [AIMessage(question), HumanMessage(input(question))])

def trim_messages(state: State) -> State:
    if len(state["messages"]) > 3:
         state["messages"]  = state["messages"][-3:]
         return state
    else:
        return state

def summarize_messages(state: State) -> State:

    new_conversation = ""
    for i in state["messages"]:
        new_conversation += f"{i.type}: {i.content}\n\n"

    print("New conversation: " + new_conversation)

    summary_instructions = f''',
        Update the ongoing summary by incorporating the new lines of conversation below.",
        Build upon the previous summary rather than repeating it so that the result",
        reflects the most recent context and developments.

        New conversation:
        {new_conversation}
    '''

    print("Summary instructions: " + summary_instructions)

    summary = llm.invoke([HumanMessage(summary_instructions)])

    print("Chat summary: " + summary)

    remove_message = [RemoveMessage(id = i.id) for i in state["messages"][:]]

    print("Remove messages: " + remove_message.__str__())

    return State(summary=summary, messages=remove_message)

def routing_function(state: State) -> Literal["summarize_messages", "__end__"]:
    if state["messages"][-1].content == "да":
        return "summarize_messages"
    else:
        return "__end__"

def chatbot(state: State) -> State:
    chat_request = []
    if state.get("summary", "") == "":
        chat_request = state["messages"]
    else:
        system_message = f'''
            Here's a quick question of what's been discussed so far:
            {state.get("summary", "")}
            Keep this in mind as your answer the next question.
        '''
        chat_request = [SystemMessage(system_message)] + state["messages"]

    print("Chat request: " + chat_request.__str__())
    response =llm.invoke(chat_request)
    print("Chat response: " + response.__str__())
    return State(messages = [response])

def print_state(state: State, method_name: str):
    print(f"Method: {method_name}: {state[MESSAGES]}")
    print("========================================")
    print("")
    print("")

def init_ollama():
    return OllamaLLM(
        model="llama3.1:8b",
        temperature=0.1,  # чем ниже, тем точнее
        num_predict=64,  # Ограничиваем длину ответа
        top_k=10,  # Уменьшаем для скорости
        top_p=0.7,
        repeat_penalty=1.1,
        num_ctx=512,  # Уменьшаем контекст
        num_thread=8,  # Больше потоков
    )

if __name__ == '__main__':
    llm = init_ollama()

    graph = StateGraph(State)

    # Добавляем узлы
    graph.add_node("ask_question", ask_question)
    graph.add_node("chatbot", chatbot)
    graph.add_node("ask_another_question", ask_another_question)
    graph.add_node("trim_messages", trim_messages)
    graph.add_node("summarize_messages", summarize_messages)

    # Настраиваем граф
    graph.add_edge(START, "ask_question")
    graph.add_edge("ask_question", "chatbot")
    graph.add_edge("chatbot", "trim_messages")
    graph.add_edge("trim_messages", "ask_another_question")
    graph.add_conditional_edges("ask_another_question", routing_function)
    graph.add_edge("summarize_messages", "ask_question")

    graph_compiled = graph.compile()

    graph_compiled.invoke(MessagesState(messages = []))


