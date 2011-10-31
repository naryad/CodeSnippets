package yn.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GeoZipCodesBean2 {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		//String wrongJson = "{ \"destination_addresses\" : [  ], \"origin_addresses\" : [  ], \"rows\" : [ { \"elements\" : [ { \"distance\" : { \"text\" : \"897 mi\", \"value\" : 1443464 }, \"duration\" : { \"text\" : \"14 hours 32 mins\", \"value\" : 52327 }, \"status\" : \"OK\" } ] }, { \"elements\" : [ { \"distance\" : { \"text\" : \"378 mi\", \"value\" : 607670 }, \"duration\" : { \"text\" : \"6 hours 22 mins\", \"value\" : 22908 }, \"status\" : \"OK\" } ] } ], \"status\" : \"OK\" }";
		String correctJson = "{ \"destination_addresses\": [], \"origin_addresses\": [], \"rows\": [ { \"distance\": { \"text\": \"897 mi\", \"value\": 1443464 }, \"duration\": { \"text\": \"14 hours 32 mins\", \"value\": 52327 }, \"status\": \"OK\" }, { \"distance\": { \"text\": \"378 mi\", \"value\": 607670 }, \"duration\": { \"text\": \"6 hours 22 mins\", \"value\": 22908 }, \"status\": \"OK\" } ], \"status\": \"OK\" }";
		GeoZipCodesBean2 geo = gson.fromJson(correctJson, GeoZipCodesBean2.class);
		System.out.println(geo.getRows().get(0).getStatus());
		System.out.println(geo.getRows().get(0).getDistance().getValue());
	}

	private String[] destination_addresses;
	private String[] origin_addresses;
	private String status;
	private List<Elem> rows;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getOriginAddresses() {
		return origin_addresses;
	}

	public void setOriginAddresses(String[] origin_addresses) {
		this.origin_addresses = origin_addresses;
	}

	public String[] getDestinationAddresses() {
		return destination_addresses;
	}

	public void setDestinationAddress(String[] destination_address) {
		this.destination_addresses = destination_addresses;
	}

	public List<Elem> getRows() {
		return rows;
	}

	public void setRows(List<Elem> rows) {
		this.rows = rows;
	}

	/*
	 * { \"destination_addresses\" : [ ], \"origin_addresses\" : [ ], \"rows\" :
	 * [ { \"elements\" : [ { \"distance\" : { \"text\" : \"897 mi\", \"value\"
	 * : 1443464 }, \"duration\" : { \"text\" : \"14 hours 32 mins\", \"value\"
	 * : 52327 }, \"status\" : \"OK\" } ] }, { \"elements\" : [ { \"distance\" :
	 * { \"text\" : \"378 mi\", \"value\" : 607670 }, \"duration\" : { \"text\"
	 * : \"6 hours 22 mins\", \"value\" : 22908 }, \"status\" : \"OK\" } ] } ],
	 * \"status\" : \"OK\" } }
	 */

	public static class Elem {
		private Distance distance;
		private Duration duration;
		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

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

	public static class Distance {
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

	public static class Duration {
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

}