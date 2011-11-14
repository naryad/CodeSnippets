package yn.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author HP
 *
 */
public class JSONExperiments {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		/*ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(System.out, OrderType.UNKNOWN);*/
		
		//mapper.writeValue(System.out, new User("test", "hyd"));
		
		List<List<String>> listListStr = new ArrayList<List<String>>();
		List<String> listOfString = new ArrayList<String>();
		listOfString.add("A");listOfString.add("B");
		listListStr.add(listOfString);
		System.out.println(new GsonBuilder().create().toJson(listListStr));
		
	}

	
	private static class User {
		public User(String name, String city) {
			super();
			this.name = name;
			this.city = city;
		}
		private String name;
		private String city;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	}
	
	public enum OrderType {

		 UNKNOWN(0, "Undefined"), TYPEA(1, "Type A"), TYPEB(2, "Type B"), TYPEC(
				3, "Type C");

		private Integer id;
		private String name;
		
		private OrderType(Integer id, String name) {
			this.id = id;
			this.name = name;
		}
		
	}
}

/*
{
  "rows" : [
  {
     "elements" : [
        {
           "distance" : {
              "text" : "897 mi",
              "value" : 1443464
           },
           "duration" : {
              "text" : "14 hours 32 mins",
              "value" : 52327
           },
           "status" : "OK"
        }
     ]
  },
  {
     "elements" : [
        {
           "distance" : {
              "text" : "378 mi",
              "value" : 607670
           },
           "duration" : {
              "text" : "6 hours 22 mins",
              "value" : 22908
           },
           "status" : "OK"
        }
     ]
  }
],

}
*/
/*
class GeoZipCodesBean2 {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		GeoZipCodesBean2 geo = new GeoZipCodesBean2();
		List<List<Elem>> rows = new ArrayList<List<Elem>>();
		List<Elem> elem = new ArrayList<Elem>();
		Elem e1 = new Elem();
		Distance d = new Distance();
		d.setText("fads");
		d.setValue(1234);
		e1.setDistance(d);
		elem.add(e1);
		rows.add(elem);
		geo.setRows(rows);
		String json = gson.toJson(geo);
		System.out.println(json);
		json = "{\"rows\":[[{\"distance\":{\"text\":\"fads\",\"value\":1234}, \"status\":\"OK\"}]]}";
		GeoZipCodesBean2 geo2 = gson.fromJson(json, GeoZipCodesBean2.class);
		System.out.println(geo2.getRows().get(0).get(0).getDistance().getValue());
	}
    //    private Elem[][] rows;

    //    public Elem[][] getRows() {
    //        return rows;
    //    }
    //
    //    public void setRows(Elem[][] rows) {
    //        this.rows = rows;
    //    }

    private List<List<Elem>>rows;

    public List<List<Elem>> getRows() {
        return rows;
    }

    public void setRows(List<List<Elem>> rows) {
        this.rows = rows;
    }
}
class Elem {
    private Distance distance = new Distance();
    private Duration duration = new Duration();

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}

class Distance {
    private String text;
    private Integer value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

class Duration {
    private String text;
    private Integer value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}*/