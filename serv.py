import socket
import logging
import sys

# Устанавливаем настройки логирования
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger("BinaryDataLogger")

def start_server(host, port):
    try:
        # Создаем TCP-сокет
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.bind((host, port))
        server_socket.listen(1)
        logger.info(f"Сервер запущен на {host}:{port}")

        while True:
            conn, addr = server_socket.accept()
            logger.info(f"Подключено к {addr}")

            with conn:
                while True:
                    data = conn.recv(1024)  # Принимаем данные
                    if not data:
                        break
                    logger.info(f"Принято бинарных данных: {data}")

    except KeyboardInterrupt:
        logger.info("Завершение работы по запросу пользователя")
        server_socket.close()
        sys.exit()

if __name__ == "__main__":
    HOST = "127.0.0.1"  # Хост для прослушивания
    PORT = 12345  # Порт для прослушивания

    start_server(HOST, PORT)

