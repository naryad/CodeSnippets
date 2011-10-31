package yn.json;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class MyCustomClass {
	Date date;
	Long value;

	public MyCustomClass() {
	}
	public MyCustomClass(Date date, Long value) {
		this.date = date;
		this.value = value;
	}

	@Override
	public String toString() {
		return "{date: " + date + ", value: " + value + "}";
	}
}

class MyCustomDeserializer implements JsonDeserializer<MyCustomClass> {
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

	@Override
	public MyCustomClass deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext ctx) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		Entry<String, JsonElement> entry = obj.entrySet().iterator().next();
		if (entry == null)
			return null;
		Date date;
		try {
			date = df.parse(entry.getKey());
		} catch (ParseException e) {
			e.printStackTrace();
			date = null;
		}
		Long value = entry.getValue().getAsLong();
		return new MyCustomClass(date, value);
	}
}

class GsonTest {
	public static void main(String[] args) {
		// Note the time zone format tweak (removed the ':')
		String json = "[{\"2011-04-30T00:00:00-0700\":100}, {\"2011-04-29T00:00:00-0700\":200}]";

		Gson gson = new GsonBuilder().registerTypeAdapter(MyCustomClass.class,
				new MyCustomDeserializer()).create();
		Type collectionType = new TypeToken<Collection<MyCustomClass>>() {}.getType();
		Collection<MyCustomClass> myCustomClasses = gson.fromJson(json,
				collectionType);
		System.out.println(myCustomClasses);
		gson = new GsonBuilder().create();		
		MyCustomClass [] myCustomClass = new MyCustomClass[2];
		myCustomClass[0] = new MyCustomClass(new Date(), 111L);
		myCustomClass[1] = new MyCustomClass(new Date(), 111L);
		System.out.println(gson.toJson(myCustomClass));
		MyCustomClass [] mcArray = gson.fromJson(gson.toJson(myCustomClass), MyCustomClass[].class);
		for (MyCustomClass mc : mcArray) {
			System.out.println(mc);
		}
	}
}