package org.bmj.userinsights.search.Dao;

import java.util.List;
import java.util.Set;

import org.bmj.userinsights.entity.InsightDetail;
import org.bmj.userinsights.entity.InsightProduct;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class SearchDao extends HibernateDaoSupport implements ISearchDao{
	public void searchProduct(){
		 List<InsightDetail> list = null;
		   System.out.println("Searching for products...");
		    try {
		    	list = (List<InsightDetail>)this.getHibernateTemplate()
						.find("select distinct id from "+
                              "InsightDetail id LEFT JOIN id.products as ip " +
                              "LEFT JOIN id.projects as ipj " +
                              "LEFT JOIN id.tags as it " +
                              "INNER JOIN ip.product as prod INNER JOIN ipj.project as proj INNER JOIN it.tag as tag " +
                              "where ((id.title like '%insight1%') OR (id.description like '%insight1%') " +
                              "OR (prod.name like '%journal%') OR (proj.name like '%journal%') OR (tag.name like '%journal%')) " +
                              "AND id.insightApplicationID = 1 " +
                              //Below are the conditional additions.
                              "AND id.type = 1 AND id.insightServerity = 1 " +
                              "AND (id.addedDate BETWEEN CAST('2014-02-01' AS DATE) AND CAST('2014-02-28' AS DATE)) " +
                              "AND (id.modifiedDate BETWEEN CAST('2014-02-01' AS DATE) AND CAST('2014-02-28' AS DATE))");        
		       
		       /* for(InsightDetail employee : list) {
		            System.out.println("Emaployee Name: " + employee.getTitle());
		            Set<InsightProduct> mobileSet = employee.getProducts();
		            System.out.println("Mobile Numbers: ");
		            for(InsightProduct mobile : mobileSet) {
		                System.out.println(mobile.getProduct().getName());
		            }
		        }*/
		    	if(null != list){
		    		System.out.println(list.size());
		    	}
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {		       
		    }
		/*List<InsightProduct> list = null;
		   
	    try {
	    	list = (List<InsightProduct>)this.getHibernateTemplate()
					.find("SELECT ids FROM InsightProduct ids");        
	       
	        for(InsightProduct employee : list) {
	            System.out.println("Emaployee Name: " + employee.getProductId());
	            System.out.println("Emaployee Name1: " + employee.getInsightDetail().getTitle());	            
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {		       
	    }*/
	}
}
