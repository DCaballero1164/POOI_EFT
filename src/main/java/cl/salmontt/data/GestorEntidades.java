package cl.salmontt.data;

import cl.salmontt.model.entities.Registrable;
import java.util.ArrayList;
import java.util.List;

public class GestorEntidades {
    private List<Registrable> entidades;
    private RepositorioArchivos gestorDatos;

    public GestorEntidades() {
        this.entidades = new ArrayList<>();
        this.gestorDatos = new RepositorioArchivos();

        // Carga masiva de todas las fuentes de datos
        entidades.addAll(gestorDatos.cargarEntidades("personas.txt"));
        entidades.addAll(gestorDatos.cargarEntidades("unidadesoperativas.txt"));
        entidades.addAll(gestorDatos.cargarEntidades("productos.txt"));
        entidades.addAll(gestorDatos.cargarEntidades("ordenes.txt"));
    }

    public List<Registrable> ConsultarEntidades() {
        return entidades;
    }

    public void agregarEntidade(Registrable entidad) {
        entidades.add(entidad);
    }

    public void guardarCambios() {
        gestorDatos.guardarTodo(entidades);
    }
}
