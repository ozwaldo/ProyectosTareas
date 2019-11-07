package edu.itsur.proyectostareas.database;

public class TareaDbSchema {

    public static final class TareaTabla {
        public static final String NOMBRE = "tareas";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITULO = "titulo";
            public static final String FECHA = "fecha";
            //public static final String HORA = "hora";
            public static final String ENTREGADA = "entregada";
        }

    }

}
