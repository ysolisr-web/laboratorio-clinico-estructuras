import java.util.Objects;

public class MuestraLaboratorio {
    private String codigoMuestra;
    private String nombrePaciente;
    private String tipoExamen;
    private String estado;

    public MuestraLaboratorio(String codigoMuestra, String nombrePaciente, String tipoExamen, String estado) {
        this.codigoMuestra = codigoMuestra;
        this.nombrePaciente = nombrePaciente;
        this.tipoExamen = tipoExamen;
        this.estado = estado;
    }

    public String getCodigoMuestra() {
        return codigoMuestra;
    }

    public void setCodigoMuestra(String codigoMuestra) {
        this.codigoMuestra = codigoMuestra;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "[Código: " + codigoMuestra +
               " | Paciente: " + nombrePaciente +
               " | Examen: " + tipoExamen +
               " | Estado: " + estado + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MuestraLaboratorio otra = (MuestraLaboratorio) obj;
        if (this.codigoMuestra == null || otra.codigoMuestra == null) {
            return false;
        }
        return this.codigoMuestra.equalsIgnoreCase(otra.codigoMuestra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoMuestra != null ? codigoMuestra.toLowerCase() : null);
    }
}