package ec.edu.espol.utils;

import ec.edu.espol.model.GraphLA;
import ec.edu.espol.tda.Actor;
import ec.edu.espol.tda.Pelicula;
import ec.edu.espol.tda.PeliculaActor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Utils {

    private static final String PATH_ACTORES_TEST = "src/ec/edu/espol/recursos/actores-test.txt";
    private static final String PATH_ACTORES = "src/ec/edu/espol/recursos/actores.txt";
    private static final String PATH_PELICULAS_TEST = "src/ec/edu/espol/recursos/peliculas-test.txt";
    private static final String PATH_PELICULAS = "src/ec/edu/espol/recursos/peliculas-test.txt";
    private static final String PATH_EDGES = "src/ec/edu/espol/recursos/pelicula-actores-test.txt";
    private static final String PATH_PELICULA_ACTOR = "src/ec/edu/espol/recursos/pelicula-actores.txt";

    public static GraphLA<Actor> generarOraculo() {
        HashMap<Integer, Actor> actores_hashmap = new HashMap<>();
        HashMap<Integer, Pelicula> peliculas_hashmap = new HashMap<>();
        HashMap<Pelicula, List<Actor>> actores_en_pelicula = new HashMap<>();
        GraphLA<Actor> nuevo = new GraphLA<>(false);
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
        // Leer el archivo de peliculas
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_PELICULAS_TEST))) {
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
        // Leer el archivo de peli-actores
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
     *
     * @return ArrayList<Actor>
     */
    public static ArrayList<Actor> cargarActoresArrayList() {
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
                final Actor actor_actual = new Actor(id, separado[1]);
                lista.add(actor_actual);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para cargar actores en una LinkedList
     * Este metodo se utiliza para realizar el reporte del proyecto
     *
     * @return LinkedList<Actor>
     */
    public static LinkedList<Actor> cargarActoresLinkedList() {
        LinkedList<Actor> lista = new LinkedList<>();
        cargarActores(lista);
        return lista;
    }

    /**
     * Método para cargar actores en un HashMap
     * Este metodo se utiliza para realizar el reporte del proyecto
     *
     * @return HashMap<Integer   ,       String>
     */
    public static HashMap<Integer, String> cargarActoresMap() {
        HashMap<Integer, String> actores_hashmap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_ACTORES_TEST)))) {
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

    public static ArrayList<Pelicula> cargarPeliculasArrayList() {
        ArrayList<Pelicula> lista = new ArrayList<>();
        cargarPeliculas(lista);
        return lista;
    }

    public static void cargarPeliculas(List<Pelicula> lista){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_PELICULAS), "ISO-8859-1"))) {
            String cadena;
            while ((cadena = in.readLine()) != null) {
                String[] p = cadena.split("\\|");
                lista.add(new Pelicula(Integer.valueOf(p[0]), p[1]));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static LinkedList<Pelicula> cargarPeliculasLinkedList() {
        LinkedList<Pelicula> lista = new LinkedList<>();
        cargarPeliculas(lista);
        return lista;
    }

    public static HashMap<Integer, String> cargarPeliculasMap() {
        HashMap<Integer, String> mapa = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_PELICULAS_TEST), "ISO-8859-1"))) {
            String cadena;
            while ((cadena = in.readLine()) != null) {
                String[] st = cadena.split("\\|");
                mapa.put(Integer.parseInt(st[0]), st[1]);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return mapa;
    }


    public static ArrayList<PeliculaActor> cargarPeliActorArrayList() {
        ArrayList<PeliculaActor> lista = new ArrayList<>();
        cargarPeliActor(lista);
        return lista;
    }

    public static LinkedList<PeliculaActor> cargarPeliActorLinkedList() {
        LinkedList<PeliculaActor> lista = new LinkedList<>();
        cargarPeliActor(lista);
        return lista;
    }

    public static void cargarPeliActor(List<PeliculaActor> lista){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_EDGES)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                final PeliculaActor pelicula_actor = new PeliculaActor(id, Integer.parseInt(separado[1]));
                lista.add(pelicula_actor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, List<PeliculaActor>> cargarPeliActoresMap() {
        HashMap<Integer, List<PeliculaActor>> actores_hashmap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_EDGES)))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] separado = linea.split("\\|");
                int id = Integer.parseInt(separado[0]);
                if (!actores_hashmap.containsKey(id)) {
                    ArrayList<PeliculaActor> a = new ArrayList<>();
                    a.add(new PeliculaActor(id, Integer.valueOf(separado[1])));
                    actores_hashmap.put(id, a);
                } else {
                    actores_hashmap.get(id).add(new PeliculaActor(id, Integer.valueOf(separado[1])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actores_hashmap;
    }

    public static void vincularPeliculaActor(HashMap<Integer, List<PeliculaActor>> mapaPeliculaActor, HashMap<Integer, String> mapaActor, HashMap<Integer, String> mapaPelicula) {
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
        HashMap<Integer, String> mapaActor = cargarActoresMap();
        HashMap<Integer, String> mapaPelicula = cargarPeliculasMap();
        HashMap<Integer, List<PeliculaActor>> mapaPeliActor = cargarPeliActoresMap();
        vincularPeliculaActor(mapaPeliActor, mapaActor, mapaPelicula);
        for (Map.Entry<Integer, String> entry : mapaActor.entrySet()) {
            grafo.addVertex(entry.getKey());
        }
        for (Map.Entry<Integer, List<PeliculaActor>> entry : mapaPeliActor.entrySet()) {
            combinations(entry.getValue(), 2).forEach((v) ->
                    grafo.addEdge(v.get(0).getIdActor(), v.get(1).getIdActor(), v.get(0).getPelicula()));
        }
        return grafo;
    }


}
