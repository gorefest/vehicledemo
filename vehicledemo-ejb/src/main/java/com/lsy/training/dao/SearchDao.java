package com.lsy.training.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.lsy.training.model.Vehicle;

@Stateless
public class SearchDao {

	@PersistenceContext(unitName="fachlichkeit")
	EntityManager entityManager;

	public List<Vehicle> searchVehicles(String term) {
		String searchQuery = "modelName:" + term.toString();
		List<Vehicle> result = null;

		QueryParser parser = new QueryParser(Version.LUCENE_35, // Parser holen
				"modelName", new StandardAnalyzer(Version.LUCENE_35));

		org.apache.lucene.search.Query luceneQuery;
		try {
			luceneQuery = parser.parse(searchQuery); 
			FullTextEntityManager ftEm = Search
					.getFullTextEntityManager(entityManager); 
			Query query = ftEm.createFullTextQuery(luceneQuery, Vehicle.class);
			result = query.getResultList();


		} catch (org.apache.lucene.queryParser.ParseException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	

	 /**
    * Hibernate Search Function, durchsucht den Index
    *
    * @param term - der Term
    * @return
    */
   public List<Vehicle> searchVehicles22(String term) {
       List<Vehicle> result = null;

       FullTextEntityManager ftEm =
               Search.getFullTextEntityManager(entityManager);     // FullTextEntityManager holen

       QueryBuilder qb = ftEm.getSearchFactory()                   // QueryBuilder holen
               .buildQueryBuilder().forEntity(Vehicle.class).get();

       org.apache.lucene.search.Query query = qb                   // Query aufbauen
               .keyword()                                          // keyword-Suche auf der entity
               .onFields("fgnr", "modelName","engine.vendor.name", "vendor.name" , "comment", "engine.engine_type", "engine.engine_type_int.en")  // Felder
               .ignoreFieldBridge()                                // FieldBridges ignorieren, sonst krachts
               .matching(term)                                     // match angeben
               .createQuery();                                     // Query erzeugen

       
       
       Query persistenceQuery =                  // in JPA Query umwandeln
               ftEm.createFullTextQuery(query, Vehicle.class);

//execute search

       result = persistenceQuery.getResultList();

       return result;                                              // fertig.
   }
	

	
	public List<Vehicle> searchVehicles2(String term) {
		List<Vehicle> result;
		String[] fields = new String[]{"fgnr", "modelName","engine.vendor.name", "vendor.name", "comment", "engine.engine_type"};
		
		Map<String,Float> boostPerField = new HashMap<String,Float>();
		QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35
				                                ,fields
				                                ,new StandardAnalyzer(Version.LUCENE_35)
				                                ,boostPerField);
		org.apache.lucene.search.Query luceneQuery;
		try {
			luceneQuery = parser.parse(term);
			
			FullTextEntityManager ftEm = Search
					.getFullTextEntityManager(entityManager); 
			Query query = ftEm.createFullTextQuery(luceneQuery, Vehicle.class);
			result = query.getResultList();

			
		} catch (ParseException e) {
		throw new RuntimeException("Unable to parse query: " + term, e);
		}
		return result;
	}
	
	public void reindexData() {
        FullTextEntityManager ftEm =
                Search.getFullTextEntityManager(entityManager);
        try {
            ftEm.createIndexer().startAndWait();                
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
	}

}
