package ec.edu.espol.utils;

import ec.edu.espol.model.GraphLA;
import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {

    private static final String PATH_ACTORES_TEST = "src/ec/edu/espol/recursos/actores-test.txt";
    private static final String PATH_ACTORES = "src/ec/edu/espol/recursos/actores.txt";
    private static final String PATH_PELICULAS = "src/ec/edu/espol/recursos/peliculas-test.txt";
    private static final String PATH_EDGES = "src/ec/edu/espol/recursos/pelicula-actores-test.txt";

    public static GraphLA<Actor> generarOraculo() {
        HashMap<Integer, Actor> actores_hashmap = new HashMap<>();
        HashMap<Integer, Pelicula> peliculas_hashmap = new HashMap<>();
        HashMap<Pelicula, List<Actor>> actores_en_pelicula = new HashMap<>();
        GraphLA<Actor> nuevo = new GraphLA<>();
        // Leer el archivo de actores
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ACTORES_TEST))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                final Actor actor_actual = new Actor(id, separado[1]);
                actores_hashmap.put(id, actor_actual);
                nuevo.addVertex(actor_actual);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_PELICULAS))) {
            String linea;
            final Pattern p = Pattern.compile("([0-9]+)\\\\|([^(]*)\\\\(([0-9]+)\\\\)");
            while ((linea = br.readLine()) != null) {
                final Matcher matcher = p.matcher(linea);
                final int id = Integer.parseInt(matcher.group(1));
                final Pelicula peli = new Pelicula(id, matcher.group(2).trim(), matcher.group(3));
                peliculas_hashmap.put(id, peli);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_EDGES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                final Pelicula pelicula = peliculas_hashmap.get(Integer.parseInt(separado[0]));
                final Actor actor = actores_hashmap.get(Integer.parseInt(separado[1]));
                final List<Actor> lista_actores = actores_en_pelicula.getOrDefault(pelicula, new LinkedList<>());
                lista_actores.add(actor);
                actores_en_pelicula.putIfAbsent(pelicula, lista_actores);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Pelicula, List<Actor>> entry : actores_en_pelicula.entrySet()) {
            final List<Actor> value = entry.getValue();
            combinations(value, 2).forEach((v) -> nuevo.addEdge(v.get(0), v.get(1), entry.getKey()));
        }
        return nuevo;
    }

    /*
    https://stackoverflow.com/questions/28515516/enumeration-combinations-of-k-elements-using-java-8
     */
    public static <E> Stream<List<E>> combinations(List<E> l, int size) {
        if (size == 0) {
            return Stream.of(Collections.emptyList());
        } else {
            return IntStream.range(0, l.size()).boxed().
                    flatMap(i -> combinations(l.subList(i + 1, l.size()), size - 1).map(t -> pipe(l.get(i), t)));
        }
    }

    private static <E> List<E> pipe(E head, List<E> tail) {
        List<E> newList = new ArrayList<>(tail);
        newList.add(0, head);
        return newList;
    }
    
    /**
     * Método para cargar actores en un arrayList 
     * Este metodo se utiliza para realizar el reporte del proyecto
     * @return ArrayList<Actor>
     */
    public static ArrayList<Actor> cargarActoresArrayList() {
        ArrayList<Actor> lista = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ACTORES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                final Actor actor_actual = new Actor(id, separado[1]);
                lista.add(actor_actual);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Método para cargar actores en una LinkedList 
     * Este metodo se utiliza para realizar el reporte del proyecto
     * @return LinkedList<Actor>
     */
    public static LinkedList<Actor> cargarActoresLinkedList() {
        LinkedList<Actor> lista = new LinkedList();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ACTORES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                final Actor actor_actual = new Actor(id, separado[1]);
                lista.add(actor_actual);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Método para cargar actores en un HashMap
     * Este metodo se utiliza para realizar el reporte del proyecto
     * @return HashMap<Integer, String>
     */
    public static HashMap<Integer, String> cargarActoresMap() {
        HashMap<Integer, String> actores_hashmap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_ACTORES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                actores_hashmap.put(id, separado[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actores_hashmap;
    }
}
