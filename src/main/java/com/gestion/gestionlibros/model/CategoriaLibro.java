package com.gestion.gestionlibros.model;

public enum CategoriaLibro {
    // Científico, literario, biografía, monografía
    CIENTIFICO("Científico"), LITERARIO("Literario"), BIOGRAFIA("Biografía"), MONOGRAFIA("Monografía");

    private final String categoria;

    private CategoriaLibro(String categoria ) {
        this.categoria = categoria;
    }
    private CategoriaLibro() {
        this.categoria = "";
    }

    public String getCategoria() {
        return categoria;
    }

    public static CategoriaLibro getCategoriaLibro(String categoria) {
        for (CategoriaLibro c : values()) {
            if (c.getCategoria().equals(categoria)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
