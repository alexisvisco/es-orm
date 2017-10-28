# ElasticSearch-Orm
Light ORM for ElasticSearch in Java, few functionality.

## Create a Query (sample)
```Java
import fr.maed.website.kwizzy.esorm.ESDriver;
public class Test {
    public static void main(String[] args){
        ESDriver es = new ESDriver(9200, "http://localhost", "maed", "offers");
        /*
        Here we want a document who contain with aproximation blazzer int title or description,
        The price must be lesser than 50 $
        The distance between  lon x, lat y must be lesser than 100 km
        */
        QueryBuilder queryBuilder = new QueryBuilder()
            .addMust(new FullTextSearch("blazzer", "title", "description"))
            .addMust(new Range("price", 50, Range.Opertor.LESSER_THAN))
            .addMust(new GeoDistance("location", 100, 4.62342342344, 2.87697));
                
        
        List<OffersDto> offersDtos = es.searchToDtos(queryBuilder, OffersDto.class);
        offersDtos.forEach(System.out::println);
    }
}
```
