package yn.http;

public class HTTPExperiments {
	public static void main(String[] args) {
//System.out.println(getRules().size());
		
		/*List<Rule> rules = new ArrayList<Rule>();
		Rule rule = new Rule("VAAI lang:en");
		rules.add(rule);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("rules", JSONUtil.toJson(rules));
		System.out.println(jsonObject.toString());*/

		/*DefaultHttpClient client = new DefaultHttpClient();
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(USER_NAME, PASSWORD);
		client.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);		
		String login = "username" + ":" + "password";
		String base64login = new String(Base64.encodeBase64(login.getBytes()));
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("_method", "delete"));
		URI uri = getRulesURI(qparams);
		System.out.println(uri.toString());
		HttpPost httpPost = new HttpPost(uri);
		httpPost.addHeader("Authorization", "Basic " + base64login);
		
		List<Rule> deletedRules = new ArrayList<Rule>();
		deletedRules.add(new Rule("VAAI lang:en"));
		Map<String, List<Rule>> rules = new HashMap<String, List<Rule>>();
		rules.put("rules", deletedRules);
		String deletedRulesJSON = JSONUtil.toJson(rules);*/
		
		/*StringEntity deletedRulesStringEntity = new StringEntity(deletedRulesJSON, HTTP.UTF_8);
		deletedRulesStringEntity.setContentType("application/json; charset=UTF-8");
		httpPost.setEntity(deletedRulesStringEntity);*/
		/*
		HttpResponse response = client.execute(httpPost);
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode != 200) {
			LOGGER.error("Failed to delete rules with status " + statusCode);
			throw new VerticalEngineException("Error occurred while deleting the gnip rules");
		}
		client.getConnectionManager().shutdown();*/
	}
	
	
}
