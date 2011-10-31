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

public class OfferContainer {

	public Offer offer;

	public OfferContainer() {
	}

	@Override
	public String toString() {
		return offer.toString();
	}

	public String getDescription() {
		return offer.description;
	}

	public String getBusinessName() {
		return offer.business.name;
	}
}

class Offer {
	public Date expiration;
	public Date published;
	public Date valid_from;
	public Date valid_to;
	public Category category;
	public String description;
	public String discount;
	public String rescinded_at;
	public String title;
	public String hook;
	public String id;
	public Business business;
	public Location location;
	public String image_270x155;

	@Override
	public String toString() {
		return String
				.format("[Offer: category=%1$s, description=%2$s, discount=%3$s, expiration=%4$s, published=%5$s, rescinded_at=%6$s, title=%7$s, valid_from=%8$s, valid_to=%9$s, id=%10$s, business=%11$s, location=%12$s]",
						category, description, discount, expiration,
						published, rescinded_at, title, valid_from,
						valid_to, id, business, location);
	}

}

enum Category {
	Salon, Spa, Restaurant, Other
}

class Business {
	public String name;
	public String phone;
	public Address address;

	@Override
	public String toString() {
		return String.format(
				"[Business: name=%1$s, phone=%2$s, address=%3$s]", name,
				phone, address);
	}
}

class Address {
	public String address_1;
	public String address_2;
	public String city;
	public String cross_streets;
	public String state;
	public String zip;

	@Override
	public String toString() {
		return String
				.format("[Address: address_1=%1$s, address_2=%2$s, city=%3$s, cross_streets=%4$s, state=%5$s, zip=%6$s]",
						address_1, address_2, city, cross_streets, state,
						zip);
	}
}

class Location {
	public double latitude;
	public double longitude;

	public String toString() {
		return String.format("[Location: longitude=%1$s, latitude=%2$s]",
				longitude, latitude);
	}

}
class OfferDeserializationTest
{
    public static void main(String[] args)
    {
        // Note the time zone format tweak (removed the ':')
        String json = "[{\"2011-04-30T00:00:00-0700\":100}, {\"2011-04-29T00:00:00-0700\":200}]";

        Gson gson =
            new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .registerTypeAdapter(Offer.class, new OfferDeserializer())
            .create();
        Type collectionType = new TypeToken<Collection<OfferContainer>>(){}.getType();
        Collection<Offer> offerClasses = gson.fromJson(json, collectionType);
        System.out.println(offerClasses);
    }
}
class OfferDeserializer implements JsonDeserializer<Offer>
{
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

    @Override
    public Offer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException
    {
        JsonObject obj = json.getAsJsonObject();
        Entry<String, JsonElement> entry = obj.entrySet().iterator().next();
        if (entry == null) return null;
        Date date;
        try
        {
            date = df.parse(entry.getKey());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            date = null;
        }
        Long value = entry.getValue().getAsLong();
        return new Offer();
    }
}