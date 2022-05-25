package Assik4;

import java.util.*;

public class BFS<Vertex> {
    private Graph<Vertex> graph;

    public BFS(Graph<Vertex> graph) {
        this.graph = graph;
    }

    public boolean bfs(Vertex this_v, Vertex dist) {
        Queue<Vertex> search_queue = new LinkedList<Vertex>();
        search_queue.addAll(graph.get(this_v));
        LinkedList<Vertex> searched = new LinkedList<>();
        searched.add(this_v);
        while (!search_queue.isEmpty()) {
            Vertex person = search_queue.poll();
            if (!isIn(person, searched)) {
                if (person.equals(dist)) {
                    System.out.println(dist + "\nend!");
                    return true;
                } else {
                    search_queue.addAll(graph.get(person));
                    System.out.print(person + " ");
                    searched.add(person);
                }
            }
        }
        return false;
    }

    private boolean isIn(Vertex v, LinkedList<Vertex> list) {
        try {
            if (!(list == null)) {
                for (Vertex i : list) {
                    if (i.equals(v)) {
                        return true;
                    }
                }
            }
            else
                return false;
        } catch (Exception e) {}
        return false;
    }

    public boolean Dsf(Vertex this_v, Vertex dist) {
        Stack<Vertex> search_queue = new Stack<Vertex>();
        search_queue.addAll(graph.get(this_v));
        LinkedList<Vertex> searched = new LinkedList<>();
        searched.add(this_v);
        while (!search_queue.isEmpty()) {
            Vertex person = search_queue.pop();
            if (!isIn(person, searched)) {
                if (person.equals(dist)) {
                    System.out.println(dist + "\nend!");
                    return true;
                } else {
                    search_queue.addAll(graph.get(person));
                    System.out.print(person + " ");
                    searched.add(person);
                }
            }
        }
        return false;
    }

    public int dist_to(Vertex this_v, Vertex dist) {
        Queue<Vertex> search_queue = new LinkedList<Vertex>();
        search_queue.addAll(graph.get(this_v));
        LinkedList<Vertex> searched = new LinkedList<>();
        LinkedList<Vertex> way = new LinkedList<>();
        while (!search_queue.isEmpty()) {
            Vertex person = search_queue.poll();
            if (!isIn(person, searched)) {
                if (person.equals(dist)) {
                    way.add(person);
                    if (graph.directed) {
                        way = search_directed(this_v, person, way, searched);
                    } else
                        way = search(this_v, person, way, searched);
//                    print(this_v, way);
                    return way.size();
                } else {
                    search_queue.addAll(graph.get(person));
                    searched.add(person);
                }
            }
        }
        return -1;
    }

    private LinkedList<Vertex> search(Vertex this_v, Vertex step,LinkedList<Vertex> way, LinkedList<Vertex> searched) {
        Vertex v = searched.pollLast();
        if (isIn(this_v, graph.get(step))) {
            return way;
        } else if (isIn(step, graph.get(v))) {
            way.add(v);
            return search(this_v, v, way, searched);
        }
        return search(this_v, step, way, searched);
    }

    private LinkedList<Vertex> search_directed(Vertex this_s, Vertex step,LinkedList<Vertex> way, LinkedList<Vertex> searched) {
        while (!searched.isEmpty()) {
            Vertex v = searched.pollLast();
            if (isIn(this_s, graph.get(step))) {
                break;
            } else if (isIn(step, graph.get(v))){
                way.add(v);
                step = v;
            }
        }
        return way;
    }

    private void print(Vertex start, LinkedList<Vertex> list) {
        for (Vertex v: list) {
            System.out.print(v + " <- ");
        }
        System.out.println(start);
    }
}