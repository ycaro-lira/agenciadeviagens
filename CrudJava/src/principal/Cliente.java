package principal;

       public class Cliente {

        private String id_Cliente;
        private String nome;
        private String email;
        private String senha;

        public Cliente() {
            // Construtor vazio
        }

        public String getId_Cliente() {
            return id_Cliente;
        }

        public void setId_Cliente(String id_Cliente) {
            this.id_Cliente = id_Cliente;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
    }