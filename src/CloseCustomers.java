import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class CloseCustomers {
	
	//Global variables
	static final double EARTH_RADIUS = 6371;
	static final double INTERCOM_LATITUDE = 37.788802;
	static final double INTERCOM_LONGITUDE = -122.4025067;
	private ArrayList<Customer> closeCustomers = new ArrayList<Customer>();
	
	
	//Converts degrees to radians
	static double toRad(double x) {
	    return x * Math.PI / 180;
	}
		
	//Checks if given latitude and longitude are within 100km of Intercom SF
	static boolean isWithin100(double customerLatitude, double customerLongitude) {
	    double x1 = INTERCOM_LATITUDE-customerLatitude;
	    double dLat = toRad(x1);  
	    double x2 = INTERCOM_LONGITUDE-customerLongitude;
	    double dLon = toRad(x2);  
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) + 
	                    Math.cos(toRad(customerLatitude)) * Math.cos(toRad(INTERCOM_LATITUDE)) * 
	                    Math.sin(dLon/2) * Math.sin(dLon/2);  
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	    double d = EARTH_RADIUS * c; 
	    return d < 100;
	}
	
	//Reads given txt file of JSON objects
	//Creates new txt file with JSON object array
	void makeValidTXTFile(String fileName) {
		
		File file = new File(fileName);

        BufferedReader br = null;

        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            br = new BufferedReader(fr);

            StringBuilder result = new StringBuilder("[");
            String line;

            while( (line = br.readLine()) != null ) {
                result.append(line + ",");
            }
            
            result.append("]");
            
            String correctJSONArr = result.toString();
            
            File newFile = new File("formattedJSONArr.txt");
            newFile.createNewFile();
            FileWriter writer = new FileWriter(newFile);
            writer.write(correctJSONArr);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file.toString());
        }
        finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Unable to close file: " + file.toString());
            }
            catch(NullPointerException ex) {
            }
        }
	}
	
	//Parses JSON object array txt file and creates array of close customers
	void parseFile(String fileName) {
		//Create parser
		JSONParser parser = new JSONParser();
		
		
		try {
			//open the file and parse it to get the array of JSON Objects
			JSONArray customers = (JSONArray)parser.parse(new FileReader("formattedJSONArr.txt"));
			
			//Use an interator to iterate over each element of the array
			Iterator<?> iter = customers.iterator();
			
			//Iterate while there are more objects in array
			while (iter.hasNext()) {
				//get the next JSON object
				JSONObject customerJObj = (JSONObject) iter.next();
				double latitude = Double.parseDouble((String) customerJObj.get("latitude"));
				double longitude = Double.parseDouble((String) customerJObj.get("longitude"));
				if (isWithin100(latitude, longitude)) {
					int userID = ((Long) customerJObj.get("user_id")).intValue();
					String name = (String) customerJObj.get("name");
					Customer customer = new Customer(userID, name);
					closeCustomers.add(customer);
				}
				
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Customer> getCloseCustomers() {
		return (ArrayList<Customer>) closeCustomers.clone();
	}
	
	public void print() {
		for (Customer customer: closeCustomers) {
			System.out.println(customer);
		}
	}
	
	public CloseCustomers(String fileName) {
		makeValidTXTFile(fileName);
		parseFile("formattedJSONArr.txt");
		Collections.sort(closeCustomers);
	}

}
