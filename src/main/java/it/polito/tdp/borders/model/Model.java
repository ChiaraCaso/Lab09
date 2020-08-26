package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	
	BordersDAO dao ;
	Graph<Country, DefaultEdge> grafo ;
	Map<Integer, Country> idMap;
	List <Border> confini ;
	
	
	public Model() {
		this.dao = new BordersDAO();
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		this.confini = new ArrayList<Border>();
		this.idMap = new HashMap<Integer, Country>();
	}

	
	public void creaGrafo(Integer anno) {
		dao.loadAllCountries(idMap);
		
		this.confini = dao.getConfini(idMap, anno);
		
		
		for (Border b : confini) {
			grafo.addVertex(b.getC1());
			grafo.addVertex(b.getC2());
			grafo.addEdge(b.getC1(), b.getC2());
		}
 	}


	public int nVertici() {
		return grafo.vertexSet().size();
	}


	public int nArchi() {
		return grafo.edgeSet().size();
	}
	
	public String elencoStati () {
		String s = "";
		
		for(Country c : this.grafo.vertexSet()) {
			s+= c.getStateName() + " " + Graphs.neighborListOf(this.grafo, c).size() + "\n";
		}
		return s;
	}
	
	public int connesse() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(grafo);
		
		return ci.connectedSets().size();
	}
}
