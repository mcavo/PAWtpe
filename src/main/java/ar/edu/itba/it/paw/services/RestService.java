package ar.edu.itba.it.paw.services;

public class RestService {

	/*public static List<Restaurant> getLastWeekRestaurants(){
		return (new RestaurantDAOImpl()).getLastWeekAdded();
	}
	
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> res = (new RestaurantDAOImpl()).getAll();
		if(res!=null)
			res.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return res;
	}
	
	public List<Restaurant> getRestaurants(String typeOfFood) {
		List<Restaurant> res = (new RestaurantDAOImpl()).filterBy(typeOfFood);
		if(res!=null)
			res.sort(new Comparator<Restaurant> () {

				public int compare(Restaurant o1, Restaurant o2) {
					return o1.getName().compareTo(o2.getName());
				}
			
			});
		return res;
	}

	public static Restaurant getRestaurant(String name, String street, String number, String neighborhood, String city, String province, String floor, String apartment) {
		if(name == null){
			return null;
		}	
		return (new RestaurantDAOImpl()).getRestaurant(name, street, Integer.valueOf(number), neighborhood, city, province, Integer.valueOf(floor), apartment);
	}

	public static void addCalification(int usrId, Restaurant rest, Calification q) {
		rest.getQualifications().put(usrId, q);
	}
	
	public static void setRestaurant(String name , String description , String[] types , String timeFrom , String timeTo , String street , String number , String city , String province , String floor , String apartment , String neighborhood, String minimum, String cost) throws Exception {
		ArrayList<String> validTypes;
		int floorV = -1;
		int numberV; 
		float costV;
		double minimumPurchase;
		float from;
		float to;
		try {
			from = Float.valueOf(timeFrom.replace(':', '.'));
			to = Float.valueOf(timeTo.replace(':', '.'));
			if (!floor.isEmpty()) {
				floorV = Integer.valueOf(floor);
				ValidateDataService.validateFloor(floorV);
			}
			numberV = Integer.valueOf(number);
			ValidateDataService.validateStringLength(name, 30);
			if (description != null && !description.isEmpty()) {
				ValidateDataService.validateStringLength(description, 500);	
			}
			ValidateDataService.validateInterval(from, to);
			ValidateDataService.validateStringLength(street, 30);
			ValidateDataService.validateStringLength(city, 30);
			ValidateDataService.validateStringLength(province, 30);
			ValidateDataService.validateStringLength(neighborhood, 40);
			if (apartment != null && !apartment.isEmpty()) {
				ValidateDataService.validateApartment(apartment);	
			}
			validTypes = ValidateDataService.validateTypes(types);
			costV = ValidateDataService.validateCost(cost);
			minimumPurchase = ValidateDataService.validateMinimum(minimum);
			
		} catch (Exception e) {
			throw new Exception("Invalid parameters");
		}
		Address address = new Address(street, numberV, floorV, apartment, neighborhood, city, province);
		Restaurant rest = new Restaurant(-1, name, minimumPurchase, from, to, address, validTypes, null, costV);
		
		(new RestaurantDAOImpl()).setRestaurant(rest);
		
	}

	public static boolean validateId(String restid) {
		int id;
		try {
			id = Integer.parseInt(restid);
			return (new RestaurantDAOImpl()).validateId(id);
		} catch (java.lang.NumberFormatException e) {
			return false;
		}
	}
	*/
}
