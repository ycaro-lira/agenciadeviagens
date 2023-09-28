package principal;

public class Compra {
    private String id_compra;
    private String data;
    private Passagem passagem;
    private Cliente cliente;

    public Compra(String id_compra, String data, Passagem passagem, Cliente cliente) {
        this.id_compra = id_compra;
        this.data = data;
        this.passagem = passagem;
        this.cliente = cliente;
    }

    public Compra() {
        // Construtor vazio
    }

    public String getId_compra() {
        return id_compra;
    }

    public void setId_compra(String id_compra) {
        this.id_compra = id_compra;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
