package ec.edu.espol.utils;

import ec.edu.espol.model.GraphLA;
import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;
import ec.edu.espol.tda.PeliculaActor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String PATH_ACTORES = "src/ec/edu/espol/recursos/actores.txt";
    private static final String PATH_PELICULAS = "src/ec/edu/espol/recursos/peliculas.txt";
    private static final String PATH_PELICULA_ACTOR = "src/ec/edu/espol/recursos/pelicula-actores.txt";

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

    public static List<Actor> cargarActoresArrayList() {
        ArrayList<Actor> lista = new ArrayList<>();
        cargarActores(lista);
        return lista;
    }

    public static void cargarActores(List<Actor> lista) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_ACTORES)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                final Actor actorActual = new Actor(id, separado[1].toLowerCase());
                lista.add(actorActual);
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Log");
            logger.log(Level.INFO, e.toString());
        }
    }

    /**
     * Metodo para cargar actores en una LinkedList Este metodo se utiliza para
     * realizar el reporte del proyecto
     * @return LinkedList
     */
    public static List<Actor> cargarActoresLinkedList() {
        LinkedList<Actor> lista = new LinkedList<>();
        cargarActores(lista);
        return lista;
    }

    public static Map<Integer, String> cargarActoresMap() {
        HashMap<Integer, String> actoresHashmap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_ACTORES)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                actoresHashmap.put(id, separado[1].toLowerCase());
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Log");
            logger.log(Level.INFO, e.toString());
        }
        return actoresHashmap;
    }

    public static List<Pelicula> cargarPeliculasArrayList() {
        ArrayList<Pelicula> lista = new ArrayList<>();
        cargarPeliculas(lista);
        return lista;
    }

    public static void cargarPeliculas(List<Pelicula> lista) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_PELICULAS), "ISO-8859-1"))) {
            String cadena;
            while ((cadena = in.readLine()) != null) {
                String[] p = cadena.split("\\|");
                lista.add(new Pelicula(Integer.valueOf(p[0]), p[1].toLowerCase()));
            }
        } catch (Exception ex) {
            Logger logger = Logger.getLogger("Log");
            logger.log(Level.INFO, ex.toString());
        }
    }

    public static List<Pelicula> cargarPeliculasLinkedList() {
        LinkedList<Pelicula> lista = new LinkedList<>();
        cargarPeliculas(lista);
        return lista;
    }

    public static Map<Integer, String> cargarPeliculasMap() {
        HashMap<Integer, String> mapa = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_PELICULAS), "ISO-8859-1"))) {
            String cadena;
            while ((cadena = in.readLine()) != null) {
                String[] st = cadena.split("\\|");
                mapa.put(Integer.parseInt(st[0]), st[1]);
            }
        } catch (Exception ex) {
            Logger logger = Logger.getLogger("Log");
            logger.log(Level.INFO, ex.toString());
        }
        return mapa;
    }

    public static List<PeliculaActor> cargarPeliActorArrayList() {
        ArrayList<PeliculaActor> lista = new ArrayList<>();
        cargarPeliActor(lista);
        return lista;
    }

    public static List<PeliculaActor> cargarPeliActorLinkedList() {
        LinkedList<PeliculaActor> lista = new LinkedList<>();
        cargarPeliActor(lista);
        return lista;
    }

    public static void cargarPeliActor(List<PeliculaActor> lista) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_PELICULA_ACTOR)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                final PeliculaActor peliculaActor = new PeliculaActor(id, Integer.parseInt(separado[1]));
                lista.add(peliculaActor);
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Log");
            logger.log(Level.INFO, e.toString());
        }
    }

    public static Map<Integer, List<PeliculaActor>> cargarPeliActoresMap() {
        HashMap<Integer, List<PeliculaActor>> actoresHashmap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_PELICULA_ACTOR)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                if (!actoresHashmap.containsKey(id)) {
                    ArrayList<PeliculaActor> a = new ArrayList<>();
                    a.add(new PeliculaActor(id, Integer.valueOf(separado[1])));
                    actoresHashmap.put(id, a);
                } else {
                    actoresHashmap.get(id).add(new PeliculaActor(id, Integer.valueOf(separado[1])));
                }
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Log");
            logger.log(Level.INFO, e.toString());
        }
        return actoresHashmap;
    }

    public static Map<Integer, List<Integer>> cargarPeliActoresMapNew() {
        HashMap<Integer, List<Integer>> actoresHashmap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_PELICULA_ACTOR)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                if (!actoresHashmap.containsKey(id)) {
                    ArrayList<Integer> a = new ArrayList<>();
                    a.add(Integer.valueOf(separado[1]));
                    actoresHashmap.put(id, a);
                } else {
                    actoresHashmap.get(id).add(Integer.valueOf(separado[1]));
                }
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger("Log");
            logger.log(Level.INFO, e.toString());
        }
        return actoresHashmap;
    }

    public static void vincularPeliculaActor(Map<Integer, List<PeliculaActor>> mapaPeliculaActor, Map<Integer, String> mapaActor, Map<Integer, String> mapaPelicula) {
        for (Map.Entry<Integer, List<PeliculaActor>> entry : mapaPeliculaActor.entrySet()) {
            Pelicula pelicula = Pelicula.buscarPelicula(mapaPelicula, entry.getKey());
            if (pelicula != null) {
                for (PeliculaActor peliAct : entry.getValue()) {
                    Actor actor = Actor.buscarActor(mapaActor, peliAct.getIdActor());
                    if (actor != null) {
                        peliAct.setActor(actor);
                        peliAct.setPelicula(pelicula);
                    }
                }
            }
        }
    }

    public static void vincularPeliculaActorList(List<PeliculaActor> listPeliculaActor, List<Actor> listActor, List<Pelicula> listPelicula) {
        for (PeliculaActor pa : listPeliculaActor) {
            Pelicula pelicula = Pelicula.buscarPeliculaList(listPelicula, pa.getIdPelicula());
            Actor actor = Actor.buscarActorList(listActor, pa.getIdActor());
            if (pelicula != null && actor != null) {
                pa.setActor(actor);
                pa.setPelicula(pelicula);
            }
        }
    }

    public static GraphLA<Integer> generarGrafo() {
        GraphLA<Integer> grafo = new GraphLA<>(false);
        Map<Integer, String> mapaActor = cargarActoresMap();
        Map<Integer, String> mapaPelicula = cargarPeliculasMap();
        Map<Integer, List<PeliculaActor>> mapaPeliActor = cargarPeliActoresMap();
        vincularPeliculaActor(mapaPeliActor, mapaActor, mapaPelicula);
        for (Map.Entry<Integer, String> entry : mapaActor.entrySet()) {
            grafo.addVertex(entry.getKey());
        }
        for (Map.Entry<Integer, List<PeliculaActor>> entry : mapaPeliActor.entrySet()) {
            combinations(entry.getValue(), 2).forEach(v -> grafo.addEdge(v.get(0).getIdActor(), v.get(1).getIdActor(), v.get(0).getPelicula()));
        }
        return grafo;
    }

    public static GraphLA<Integer> generarGrafo(Map<Integer, String> mapaActor,
            Map<Integer, List<Integer>> mapaPeliActor) {
        GraphLA<Integer> grafo = new GraphLA<>(false);
        for (Map.Entry<Integer, String> entry : mapaActor.entrySet()) {
            grafo.addVertex(entry.getKey());
        }
        for (Map.Entry<Integer, List<Integer>> entry : mapaPeliActor.entrySet()) {
            combinations(entry.getValue(), 2).forEach(v -> grafo.addEdge(v.get(0), v.get(1), entry.getKey()));
        }
        return grafo;
    }

}
