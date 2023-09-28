package principal;

public class Passagem {

	 private String id_passagem;
     private String saindo;
     private String indo;
     private String valor;
	
	 public Passagem() {
             // Construtor vazio
         }

         public String getId_passagem() {
             return id_passagem;
         }

         public void setId_passagem(String id_passagem) {
             this.id_passagem = id_passagem;
         }

         public String getSaindo() {
             return saindo;
         }

         public void setSaindo(String saindo) {
             this.saindo = saindo;
         }

         public String getIndo() {
             return indo;
         }

         public void setIndo(String indo) {
             this.indo = indo;
         }

         public String getValor() {
             return valor;
         }

         public void setValor(String valor) {
             this.valor = valor;
         }
     }