package api.endPoints;


//url = https://petstore.swagger.io/v2/user

public class Route {
	
	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	//user model
	public static String postUrl = baseUrl+"/user";
	public static String getUrl = baseUrl+"/user/{username}";
	public static String updateUrl = baseUrl+"/user/{username}";
	public static String deleteUrl = baseUrl+"/user/{username}";

}
