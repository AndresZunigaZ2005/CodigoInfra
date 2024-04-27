import threading
import time
import random

class Cliente:
    def __init__(self, nombre, cantidad_productos):
        self.nombre = nombre
        self.cantidad_productos = cantidad_productos

class Cajero(threading.Thread):
    def __init__(self, nombre, supermercado):
        super().__init__()
        self.nombre = nombre
        self.supermercado = supermercado

    def run(self):
        while True:
            cliente = self.supermercado.siguiente_cliente()
            if cliente is None:
                break
            print(f"{self.nombre} está atendiendo a {cliente.nombre}")
            for _ in range(cliente.cantidad_productos):
                time.sleep(2)
                print(f"{self.nombre} terminó de atender un producto de {cliente.nombre}")
            print(f"{self.nombre} terminó de atender a {cliente.nombre}")

class Supermercado:
    def __init__(self, clientes):
        self.clientes = clientes
        self.clientes_atendidos = 0
        self.mutex = threading.Lock()

    def siguiente_cliente(self):
        with self.mutex:
            if self.clientes_atendidos >= len(self.clientes):
                return None
            cliente = self.clientes[self.clientes_atendidos]
            self.clientes_atendidos += 1
            return cliente

cliente1 = Cliente("Cliente 1", 4)
cliente2 = Cliente("Cliente 2", 2)
cliente3 = Cliente("Cliente 3", 1)
cliente4 = Cliente("Cliente 4", 6)
cliente5 = Cliente("Cliente 5", 3)
cliente6 = Cliente("Cliente 6", 9)
cliente7 = Cliente("Cliente 7", 2)
cliente8 = Cliente("Cliente 8", 10)
cliente9 = Cliente("Cliente 9", 1)
cliente10 = Cliente("Cliente 10", 3)

clientes = [cliente1, cliente2, cliente3, cliente4, cliente5, cliente6, cliente7, cliente8, cliente9, cliente10]


supermercado = Supermercado(clientes)
cajeros = [Cajero(f"Cajero {i}", supermercado) for i in range(1,4)]

for cajero in cajeros:
    cajero.start()

for cajero in cajeros:
    cajero.join()

print("Todos los clientes han sido atendidos")
