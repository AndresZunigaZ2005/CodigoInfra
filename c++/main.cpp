#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <condition_variable>

class Cliente {
public:
    std::string nombre;
    int cantidad_productos;

    Cliente(const std::string& nom, int cant_prod) : nombre(nom), cantidad_productos(cant_prod) {}
};

class Supermercado {
private:
    std::vector<Cliente> clientes;
    int clientes_atendidos = 0;
    std::mutex mutex;

public:
    Supermercado(const std::vector<Cliente>& cli) : clientes(cli) {}

    Cliente* siguiente_cliente() {
        std::lock_guard<std::mutex> lock(mutex);
        if (clientes_atendidos >= clientes.size()) {
            return nullptr;
        }
        return &clientes[clientes_atendidos++];
    }
};

class Cajero {
private:
    std::string nombre;
    Supermercado* supermercado;

public:
    Cajero(const std::string& nom, Supermercado* super) : nombre(nom), supermercado(super) {}

    void operator()() {
        while (true) {
            Cliente* cliente = supermercado->siguiente_cliente();
            if (cliente == nullptr) {
                break;
            }
            std::cout << nombre << " está atendiendo a " << cliente->nombre << std::endl;
            for (int i = 0; i < cliente->cantidad_productos; ++i) {
                std::this_thread::sleep_for(std::chrono::seconds(2));
                std::cout << nombre << " terminó de atender un producto de " << cliente->nombre << std::endl;
            }
            std::cout << nombre << " terminó de atender a " << cliente->nombre << std::endl;
        }
    }
};

int main() {
    std::vector<Cliente> clientes = {
            {"Cliente 1", 4}, {"Cliente 2", 2}, {"Cliente 3", 1}, {"Cliente 4", 6},
            {"Cliente 5", 3}, {"Cliente 6", 9}, {"Cliente 7", 2}, {"Cliente 8", 10},
            {"Cliente 9", 1}, {"Cliente 10", 3}
    };

    Supermercado supermercado(clientes);
    std::vector<std::thread> cajeros;

    for (int i = 1; i <= 3; ++i) {
        cajeros.push_back(std::thread(Cajero("Cajero " + std::to_string(i), &supermercado)));
    }

    for (auto& cajero : cajeros) {
        cajero.join();
    }

    std::cout << "Todos los clientes han sido atendidos" << std::endl;
    return 0;
}