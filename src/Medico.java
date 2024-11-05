public class Medico {
    private String nomeMedico;
    private boolean disponibilidade;
    private String especializacao;
    private int contadorDeConsultas;
    private Object emailMedico;
        
        public Medico(String nomeMedico, boolean disponibilidade, String especializacao, String especializacao1, int par) {
            this.nomeMedico = nomeMedico;
            this.emailMedico = emailMedico;
        this.disponibilidade = disponibilidade;
        this.especializacao = especializacao;
        this.contadorDeConsultas = 0;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public String getEmailMedico() {
        return (String) emailMedico; 
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public String getEspecializacao() {
        return especializacao;
    }

    public int getContadorDeConsultas() {
        return contadorDeConsultas;
    }

    public void setEmailMedico(String emailMedico) {
        this.emailMedico = emailMedico; 
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public void estaDisponivel() {
        if (disponibilidade) {
            System.out.println("    Disponibilidade: Disponível");
        } else {
            System.out.println("    Disponibilidade: Indisponível");
        }
    }

    public void incrementarContadorDeConsultas() {
        this.contadorDeConsultas++;
    }
}
